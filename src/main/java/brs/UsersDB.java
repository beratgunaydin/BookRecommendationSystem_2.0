package brs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Kullanıcılar için veritabanı işlemlerinin yapıldığı sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class UsersDB {
	/**
	 * Veritabanı bağlantısının yapıldığı sınıftan alınan örnek
	 */
	private DatabaseConnection dc = new DatabaseConnection();
	
	/**
	 * Bu metot, kullanıcı adı ve şifre bilgileriyle veritabanında bir kullanıcı arar ve eşleşen kullanıcı bilgilerini döndürür. Bir User nesnesi oluşturulur. Veritabanı bağlantısı (con) başlatılır. SQL sorgusu oluşturulur, kullanıcı adı ve şifre bilgileriyle eşleşen kullanıcıyı sorgular. Hazırlanmış bir ifade (statement) oluşturulur ve SQL sorgusu bu ifadeye atanır. Sorgu sonucu (result) alınır. Sonuçta birden fazla satır varsa; kullanıcı nesnesine ilgili sütunlardaki değerler atanır: user_id, user_name, user_password. Kullanıcı nesnesi döndürülür.
	 * @param username Veritabanında aranacak kullanıcı adı
	 * @param password Veritabanında aranacak kullanıcı şifresi
	 * @return Veritabanında aranan kullanıcı varsa atandığı User nesnesi
	 */
	public User searchUserInDatabase(String username, String password) {
		// Yeni bir User nesnesi oluşturuldu.
		User user = new User();
		
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		
		//Verilen kullanıcı adı ve şifreyle eşleşen bir kullanıcıyı aramak için SQL sorgusu hazırlandı.
		String sql = "SELECT * FROM users WHERE user_name=? AND user_password=?";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setString(1, username);
			statement.setString(2, password);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde dönerek bulunan kullanıcının özellikleri örneklenen kullanıcı nesnesine atandı.
			while (result.next()) {
				user.setUser_id(result.getInt("user_id"));
				user.setUser_name(result.getString("user_name"));
				user.setUser_password(result.getString("user_password"));
			}
			// Sonuç kümesi, ifade ve bağlantı kapatıldı.
			result.close();
            statement.close();
            con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Kullanıcı nesnesi döndürüldü.
		return user;
	}
	
	/**
	 * Bu metot, veritabanında belirtilen kullanıcı kimliğine (user_id) sahip kullanıcıyı arar ve kullanıcı kimliği ve kullanıcı adını birleştirerek bir dize olarak döndürür. Boş bir user_id_and_name dizesi oluşturulur. SQL sorgusu oluşturulur. Hazırlanmış bir ifade (statement) oluşturulur ve SQL sorgusu bu ifadeye atanır. İfadeye kullanıcı kimliği (user_id) parametresi atanır. Sorgu sonucu (result) alınır. Eğer sonuçta bir sonuç varsa; user_id_and_name dizesi oluşturulur ve kullanıcı kimliği (user_id) ve kullanıcı adı (user_name) birleştirilir. Aksi takdirde, "Sorgudan sonuç dönmüyor" mesajı yazdırılır. Kullanıcı bulunamazsa, "Kullanıcı Bulunamadı" mesajı yazdırılır ve hata izleri (e.printStackTrace()) görüntülenir. user_id_and_name dizesi döndürülür.
	 * @param user_id Veritabanında aranacak kullanıcının ID numarası
	 * @return Veritabanında kullanıcı bulunursa kullanıcı ID numarası ve kullanıcı adı
	 */
	public String searchUserIDInDatabase(int user_id) {
		// Boş bir user_id_and_name değişkeni oluşturuldu.
		String user_id_and_name = "";
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// user_id'ye göre kullanıcıyı aramak için SQL sorgusu hazırlandı.
		String sql = "SELECT * FROM users WHERE user_id=?";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);	
			
			// Parametreler belirlendi.
			statement.setInt(1, user_id);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Eğer sonuç kümesi varsa
			if (result.next()) {
				// user_id_and_name değişkenine kullanıcının user_id'si ve user_name'i eklendi.
				user_id_and_name = result.getInt("user_id") + "," + result.getString("user_name");
	        } else {
	            System.out.println("Sorgudan sonuç dönmüyor");
	        }
		} catch (SQLException e) {
			System.out.println("Kullanıcı Bulunamadı");
			e.printStackTrace();
		}
		
		// user_id_and_name değişkeni döndürüldü.
		return user_id_and_name;
	}
	
	/**
	 * Bu metot, belirtilen kullanıcı kimliği ve kullanıcı adı bilgilerine dayanarak veritabanından bir kullanıcıyı siler. isUserDeletedSuccessfully adında bir boolean değişkeni oluşturulur ve başlangıçta false olarak ayarlanır. user_id_and_name parametresi virgülle ayrılır ve arr adlı bir diziye atanır. Kullanıcı kimliği (user_id), dizi elemanlarından alınarak bir tamsayıya dönüştürülür. Kullanıcıyı ve ilgili derecelendirmeleri silmek için bir SQL sorgusu oluşturulur. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. Parametre olarak kullanıcı kimliği (user_id) atanır. Sorgu çalıştırılır ve etkilenen satır sayısı (result) alınır. Eğer etkilenen satır sayısı 0'dan büyükse; isUserDeletedSuccessfully değişkeni true olarak ayarlanır. Aksi takdirde, "Sorgudan sonuç dönmüyor" mesajı yazdırılır. Kullanıcı silinemezse, "Kullanıcı Silinemedi" mesajı yazdırılır ve hata izleri (e.printStackTrace()) görüntülenir. isUserDeletedSuccessfully değişkeni döndürülür.
	 * @param user_id_and_name Veritabanından silinmek istenen kullanıcının ID numarası ve adı
	 * @return Kullanıcının veritabanından silinip silinemediği bilgisi
	 */
	public boolean deleteUserFromDatabase(String user_id_and_name) {
		// Kullanıcının başarıyla silinip silinmediğini belirten bir isUserDeletedSuccessfully değişkeni oluşturuldu.
		boolean isUserDeletedSuccessfully = false;
		// user_id_and_name parametresi virgülle ayrıldı ve bir diziye atandı.
		String arr[] = user_id_and_name.split(",");
		// user_id değeri diziden alınarak integer'a dönüştürüldü.
		int user_id = Integer.parseInt(arr[0]);
		
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Kullanıcıyı ve ilgili derecelendirmeleri silmek için bir SQL sorgusu hazırlandı.
		String sql = "DELETE users, ratings FROM users LEFT JOIN ratings ON users.user_id = ratings.user_id WHERE users.user_id = ?;";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);	
			
			// Parametreler belirlendi.
			statement.setInt(1, user_id);
			
			// Sorgu çalıştırıldı ve etkilenen satır sayısı alındı.
			int result = statement.executeUpdate();
			
			// Eğer etkilenen satır sayısı 0'dan büyükse
			if (result > 0) {
				// isUserDeletedSuccessfully değişkeni true olarak ayarlandı.
				isUserDeletedSuccessfully = true;
	        } else {
	            System.out.println("Sorgudan sonuç dönmüyor");
	        }
		} catch (SQLException e) {
			System.out.println("Kullanıcı Silinemedi");
			e.printStackTrace();
		}
		
		// isUserDeletedSuccessfully değişkeni döndürüldü.
		return isUserDeletedSuccessfully;
	}
	
	/**
	 * Bu metot, yeni bir kullanıcıyı veritabanına kaydetmek için kullanılır. isAccountRegisteredInDatabase adında bir boolean değişkeni oluşturulur ve başlangıçta false olarak ayarlanır. Kullanıcı adı ve şifresini veritabanına kaydetmek için bir SQL sorgusu oluşturulur. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. Parametre olarak kullanıcı adı (username) ve şifre (password) atanır. Sorgu çalıştırılır ve etkilenen satır sayısı (result) alınır. Eğer etkilenen satır sayısı 0'dan büyükse; isAccountRegisteredInDatabase değişkeni true olarak ayarlanır. Kayıt işlemi başarısız olursa, "Kullanıcı mevcut" mesajı yazdırılır ve hata izleri (e.printStackTrace()) görüntülenir. isAccountRegisteredInDatabase değişkeni döndürülür. 
	 * @param username Veritabanına kayıt edilecek kullanıcının adı
	 * @param password Veritabanına kayıt edilecek kullanıcının şifresi
	 * @return Kullanıcının veritabanına kaydedilip kaydedilmediği bilgisi
	 */
	public boolean registerNewUser(String username, String password) {
		// Hesabın veritabanında kaydedilip kaydedilmediğini belirten bir isAccountRegisteredInDatabase değişkeni oluşturuldu.
		boolean isAccountRegisteredInDatabase = false;
		
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Yeni bir kullanıcıyı veritabanına kaydetmek için bir SQL sorgusu hazırlandı.
		String sql = "INSERT INTO users (user_name, user_password) VALUES (?, ?)";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setString(1, username);
			statement.setString(2, password);
			
			// Sorgu çalıştırıldı ve etkilenen satır sayısı alındı.
			int result = statement.executeUpdate();
			
			// Eğer etkilenen satır sayısı 0'dan büyükse
			if (result > 0) {
				// isAccountRegisteredInDatabase değişkeni true olarak ayarlandı.
				isAccountRegisteredInDatabase = true;
			}
		} catch (SQLException e) {
			System.out.println("Kullanıcı mevcut");
			//e.printStackTrace();
		}
		
		// isAccountRegisteredInDatabase değişkeni döndürüldü.
		return isAccountRegisteredInDatabase;
	}
	
	/**
	 * Bu metot, veritabanında belirli bir kullanıcı adının kayıtlı olup olmadığını kontrol etmek için kullanılır. isAccountRegisteredInDatabase adında bir boolean değişkeni oluşturulur ve başlangıçta false olarak ayarlanır. Kullanıcı adını kontrol etmek için bir SQL sorgusu hazırlanır. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. Kullanıcı adı (username) parametresi atanır. Sorgu çalıştırılır ve sonuç kümesi (result) alınır. Eğer sonuç kümesi varsa; ilk sütundaki değer alınır ve count değişkenine atılır ve isAccountRegisteredInDatabase değişkeni, count değerinin 0'dan büyük olup olmadığına göre ayarlanır. Sonuç kümesi yoksa, "Sorgudan sonuç dönmüyor" mesajı yazdırılır. Kayıt işlemi başarısız olursa, "Kullanıcı mevcut" mesajı yazdırılır ve hata izleri (e.printStackTrace()) görüntülenir. isAccountRegisteredInDatabase değişkeni döndürülür.
	 * @param username Veritabanında kontrol edilecek kullanıcının adı
	 * @return Veritabanında kullanıcının kayıtlı olup olmadığı bilgisi
	 */
	public boolean checkUserNameInDatabase(String username) {
		// Kullanıcının veritabanında kayıtlı olup olmadığını belirten bir isAccountRegisteredInDatabase değişkeni oluşturuldu.
		boolean isAccountRegisteredInDatabase = false;
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		
		// Verilen kullanıcı adının veritabanında kaç kez bulunduğunu kontrol etmek için bir SQL sorgusu hazırlandı.
		String sql = "SELECT COUNT(*) FROM users WHERE user_name = ?";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setString(1, username);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Eğer sonuç kümesi varsa
			if (result.next()) {
				// İlk sütunda bulunan değer alındı ve count değişkenine atandı.
	            int count = result.getInt(1);
	         // isAccountRegisteredInDatabase değişkeni, count değerinin 0'dan büyük olup olmadığına göre ayarlandı.
	            isAccountRegisteredInDatabase = count > 0;
	        } else {
	            System.out.println("Sorgudan sonuç dönmüyor");
	        }
		} catch (SQLException e) {
			System.out.println("Kullanıcı mevcut");
			e.printStackTrace();
		}
		
		// isAccountRegisteredInDatabase değişkeni döndürüldü.
		return isAccountRegisteredInDatabase;
	}
	/**
	 * Bu metot, veritabanında kayıtlı olan kullanıcıların sayısını döndürmek için kullanılır. usersCount adında bir tamsayı değişkeni oluşturulur ve başlangıçta 0 olarak ayarlanır. Kullanıcı sayısını almak için bir SQL sorgusu hazırlanır. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. Sorgu çalıştırılır ve sonuç kümesi (result) alınır. result.next() ile sonuç kümesinin ilk satırına ilerlenir. Kullanıcı sayısı (usersCount), sonuç kümesindeki ilk sütundaki değere atanır. usersCount değişkeni döndürülür, bu sayede veritabanındaki kullanıcı sayısı elde edilir.
	 * @return Veritabanına kayıtlı kullanıcı sayısı
	 */
	public int getUsersCountInDatabase() {
		// Veritabanındaki kullanıcı sayısını tutmak için bir usersCount değişkeni oluşturuldu.
		int usersCount = 0;
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Veritabanında kullanıcıların sayısını almak için bir SQL sorgusu hazırlandı.
		String sql = "SELECT COUNT(*) FROM users";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			// Sonuç kümesinin bir sonraki satırına ilerlenildi.
			result.next();
			// İlk sütundaki değer alındı ve usersCount değişkenine atandı.
			usersCount = result.getInt(1);
			
			// Sonuç kümesi kapatıldı, ifade kapatıldı ve veritabanı bağlantısı kapatıldı.
			result.close();
            statement.close();
            con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// usersCount değişkenini döndürüldü
		return usersCount;
	}
	
	/**
	 * Bu metot, veritabanından belirli bir kullanıcının benzer kullanıcılarının ID'lerini almak için kullanılır. usersIDList adında bir tamsayı listesi oluşturulur. Benzer kullanıcıların ID'lerini almak için bir SQL sorgusu hazırlanır. Sorgu, iki ayrı değerlendirme tablosu (ratings) arasında birleştirme (JOIN) yaparak, belirli bir kullanıcının (r1.user_id) ve diğer kullanıcıların (r2.user_id) aynı ISBN'e sahip değerlendirmeleri içeren kayıtları seçer. Ayrıca, belirli kullanıcının kendi ID'sini hariç tutar. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. Sorgu çalıştırılır ve sonuç kümesi (result) alınır. Sonuç kümesi üzerinde döngü oluşturulur. Her bir sonuç satırında, "user_id" sütunundaki değer alınır ve usersIDList listesine eklenir. usersIDList listesi döndürülür, bu sayede benzer kullanıcıların ID'lerine erişilebilir.
	 * @param user_id Veritabanında benzer kullanıcılar bulunacak kullanıcı
	 * @return Benzer kullanıcıların ID numaraları listesi
	 */
	public List<Integer> fetchSimiliarUsersIDListFromDatabase(int user_id) {
		// Benzer kullanıcıların ID'lerini tutmak için bir usersIDList Listesi oluşturuldu.
		List<Integer> usersIDList = new ArrayList<Integer>();
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Benzer kullanıcıların ID'lerini almak için bir SQL sorgusu hazırlandı.
		String sql = "SELECT DISTINCT r2.user_id FROM ratings r1 JOIN ratings r2 ON r1.isbn = r2.isbn WHERE r1.user_id = " + user_id + " AND r1.user_id <> r2.user_id";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde döngü oluşturuldu.
			while(result.next()) {
				// "user_id" sütunundaki değer alındı ve usersIDList Listesine eklendi.
				usersIDList.add(result.getInt("user_id"));
			}
			
			// Sonuç kümesi kapatıldı, ifade kapatıldı ve veritabanı bağlantısı kapatıldı.
			result.close();
            statement.close();
            con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// usersIDList Listesi döndürüldü.
		return usersIDList;
	}
	
	/*public void registerNewUserRating() {
		UsersDB udb = new UsersDB();
		Rating rating = new Rating();
		Random rnd = new Random();
		
		Connection con = dc.initialize();
		boolean isAccountRegisteredInDatabase = false;	
		
		List<Rating> ratingList = RecommendationEngine.getRatingsList();
		String karakterler = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int kullaniciAdi_uzunluk = 0;
		int sifre_uzunluk = 0;
		
		for (Rating r : ratingList) {
			kullaniciAdi_uzunluk = rnd.nextInt(3, 11);
			sifre_uzunluk = rnd.nextInt(4, 7);
			char[] kullaniciAdi = new char[kullaniciAdi_uzunluk];
			char[] sifre = new char[sifre_uzunluk];
			
			for(int i = 0; i < kullaniciAdi_uzunluk; i++) {
				Random rastgeleKarakter = new Random();
				kullaniciAdi[i] += karakterler.charAt(rastgeleKarakter.nextInt(karakterler.length()));
			}
			
			for(int i = 0; i < sifre_uzunluk; i++) {
				Random rastgeleKarakter = new Random();
				sifre[i] += karakterler.charAt(rastgeleKarakter.nextInt(karakterler.length()));
			}
			
			String sifreString = "";
			String kullaniciAdiString = "";
			
			for (int i = 0; i < sifre.length; i++) {
				sifreString += sifre[i];
			}
			
			for (int i = 0; i < kullaniciAdi.length; i++) {
				kullaniciAdiString += kullaniciAdi[i];
			}	
			
			String sql = "INSERT INTO users (user_id, user_name, user_password) VALUES (?, ?, ?)";
			PreparedStatement statement;
			
			try {
				statement = con.prepareStatement(sql);
				statement.setInt(1, r.getUser_id());
				statement.setString(2, kullaniciAdiString);
				statement.setString(3, sifreString);
				
				int result = statement.executeUpdate();
				
				if (result > 0) {
					isAccountRegisteredInDatabase = true;
				}
			} catch (SQLException e) {
				System.out.println("Kullanıcı mevcut");
				//e.printStackTrace();
			}
		}
	}*/
}
