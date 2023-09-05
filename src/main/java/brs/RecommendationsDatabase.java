package brs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Öneriler için veritabanı işlemlerinin yapıldığı sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class RecommendationsDatabase {
	/**
	 * Veritabanı bağlantısının yapıldığı sınıftan alınan örnek
	 */
	DatabaseConnection dc = new DatabaseConnection();
	
	/**
	 * Bu metot, bir kullanıcının kitaplarını ve bu kitaplara verdikleri puanları almak için kullanılır. usersBookRatingList adında bir HashMap oluşturulur. Bu harita, kullanıcının kitaplarını ve bu kitaplara verdikleri puanları tutar. Belirli bir user_id değerine sahip olan kullanıcının kitaplarını almak için bir SQL sorgusu hazırlanır. Sorgu, "ratings" adlı tabloda user_id sütunuyla eşleşen kayıtları alır. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. user_id parametresi atanır. Sorgu çalıştırılır ve sonuç kümesi (result) alınır. Sonuç kümesi üzerinde döngü oluşturulur ve her bir sonuç için usersBookRatingList haritasına kitap ISBN'sini anahtar olarak ve kitap puanını değer olarak eklenir. usersBookRatingList haritası döndürülür, bu sayede kullanıcının kitaplarını ve puanlarını içeren bir veri yapısı elde edilir.
	 * @param user_id Veritabanından kitaplarına ve bu kitaplara verdiği puanlara ulaşılacak kullanıcı
	 * @return Kullanıcının kitapları ve bu kitaplara verdiği puanlar listesi
	 */
	public Map<String, Integer> fetchUsersBooksList(int user_id) {
		// Kullanıcının kitap değerlendirmelerini tutmak için bir usersBookRatingList haritası oluşturuldu.
		Map<String, Integer> usersBookRatingList = new HashMap<String, Integer>();
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Belirli bir kullanıcının kitaplarını ve değerlendirmelerini almak için bir SQL sorgusu hazırlandı.
		String sql = "SELECT * FROM ratings WHERE user_id=?";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setInt(1, user_id);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde döngü oluşturuldu.
			while(result.next()) {
				// Her bir kitap değerlendirmesi usersBookRatingList Listesine eklendi.
				usersBookRatingList.put(result.getString("isbn"), result.getInt("book_rating"));
			}
			// Sonuç kümesi kapatıldı, ifade kapatıldı ve veritabanı bağlantısı kapatıldı.
			result.close();
            statement.close();
            con.close();
		} catch(Exception e) {		
		}
		// usersBookRatingList Listesi döndürüldü.
		return usersBookRatingList;
	}
	
	/**
	 * Bu metot, bir kullanıcının kitaplarının ISBN numaralarını almak için kullanılır. usersBookRatingList adında bir ArrayList oluşturulur. Bu liste, kullanıcının kitaplarının ISBN numaralarını tutar. Belirli bir user_id değerine sahip olan kullanıcının kitaplarının ISBN numaralarını almak için bir SQL sorgusu hazırlanır. Sorgu, "ratings" adlı tabloda user_id sütunuyla eşleşen kayıtları alır. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. user_id parametresi atanır. Sorgu çalıştırılır ve sonuç kümesi (result) alınır. Sonuç kümesi üzerinde döngü oluşturulur ve her bir sonuç için usersBookRatingList listesine kitap ISBN numarası eklenir. usersBookRatingList listesi döndürülür, bu sayede kullanıcının kitaplarının ISBN numaralarını içeren bir liste elde edilir.
	 * @param user_id Veritabanından kitaplarının ISBN numaraları çekilecek kullanıcının ID numarası
	 * @return Kullanıcının kitaplarının ISBN numaraları
	 */
	public List<String> fetchUsersBooksISBNList(int user_id) {
		// Kullanıcının kitap ISBN numaralarını tutmak için bir usersBookRatingList listesi oluşturuldu.
		List<String> usersBookRatingList = new ArrayList<String>();
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Belirli bir kullanıcının ISBN numaralarını almak için bir SQL sorgusu hazırlandı.
		String sql = "SELECT * FROM ratings WHERE user_id=?";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setInt(1, user_id);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde döngü oluşturuldu.
			while(result.next()) {
				// Her bir kitap ISBN numarasını usersBookRatingList listesine eklendi.
				usersBookRatingList.add(result.getString("isbn"));
			}
			// Sonuç kümesi kapatıldı, ifade kapatıldı ve veritabanı bağlantısı kapatıldı.
			result.close();
            statement.close();
            con.close();
		} catch(Exception e) {
			
		}
		// usersBookRatingList listesini döndürüldü.
		return usersBookRatingList;
	}
	
	/**
	 * Bu metot, iki kullanıcı arasında paylaşılan kitapların ISBN numaralarını almak için kullanılır. sharedBooksISBNs adında bir ArrayList oluşturulur. Bu liste, iki kullanıcı arasında paylaşılan kitapların ISBN numaralarını tutar. İki kullanıcı arasında paylaşılan kitapların ISBN numaralarını almak için bir SQL sorgusu hazırlanır. Sorgu, "ratings" adlı tabloda user_id sütunu user_id1 veya user_id2 değerlerinden biri olan kayıtları alır ve isbn sütununa göre gruplandırır. Gruplanmış sonuçlar arasında yalnızca iki farklı user_id'ye sahip olanları seçer. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. user_id1 ve user_id2 parametreleri sırasıyla atanır. Sorgu çalıştırılır ve sonuç kümesi (result) alınır. Sonuç kümesi üzerinde döngü oluşturulur ve her bir sonuç için sharedBooksISBNs listesine kitap ISBN numarası eklenir. sharedBooksISBNs listesi döndürülür, bu sayede iki kullanıcı arasında paylaşılan kitapların ISBN numaralarını içeren bir liste elde edilir.
	 * @param user_id1 Veritabanından sahip olduğu ortak kitaplar getirilecek ilk kullanıcı
	 * @param user_id2 Veritabanından sahip olduğu ortak kitaplar getirilecek ikinci kullanıcı
	 * @return İki kullanıcının sahip olduğu ortak kitapların ISBN numaraları listesi
	 */
	public List<String> fetchSharedBooksBetweenTwoUsers(int user_id1, int user_id2) {
		// Paylaşılan kitapların ISBN numaralarını tutmak için bir sharedBooksISBNs listesi oluşturuldu.
		List<String> sharedBooksISBNs = new ArrayList<String>();
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// İki kullanıcı arasında paylaşılan kitapların ISBN numaralarını almak için bir SQL sorgusu hazırlandı.
		String sql = "SELECT isbn FROM ratings WHERE user_id IN (?, ?) GROUP BY isbn HAVING COUNT(DISTINCT user_id) = 2;";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setInt(1, user_id1);
			statement.setInt(2, user_id2);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde döngü oluşturuldu.
			while(result.next()) {
				// Her bir paylaşılan kitap ISBN numarasını sharedBooksISBNs listesine eklendi.
				sharedBooksISBNs.add(result.getString("isbn"));
			}
			// Sonuç kümesi kapatıldı, ifade kapatıldı ve veritabanı bağlantısı kapatıldı.
			result.close();
            statement.close();
            con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		// sharedBooksISBNs listesini döndürüldü.
		return sharedBooksISBNs;
	}
	
	/**
	 * Bu metot, belirli bir ISBN numarası ve kullanıcı kimliğiyle kullanıcının veritabanında kaydedilmiş olan değerlendirmesini almak için kullanılır. user_rating adında bir değişken oluşturulur ve başlangıç değeri olarak 0 atanır. Bu değişken, kullanıcının değerlendirmesini tutar. Belirli bir ISBN numarası ve kullanıcı kimliğiyle kullanıcının değerlendirmesini almak için bir SQL sorgusu hazırlanır. Sorgu, "ratings" adlı tabloda isbn sütunu isbn değeriyle ve user_id sütunu user_id değeriyle eşleşen kayıtları alır. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. isbn ve user_id parametreleri sırasıyla atanır. Sorgu çalıştırılır ve sonuç kümesi (result) alınır. Sonuç kümesi üzerinde döngü oluşturulur ve her bir sonuç için kullanıcının değerlendirmesi user_rating değişkenine atanır. user_rating değişkeni döndürülür, bu sayede belirli bir ISBN numarası ve kullanıcı kimliğiyle kullanıcının veritabanında kaydedilmiş olan değerlendirmesi elde edilir.
	 * @param isbn Veritabanından çekilecek kitap ISBN numarası
	 * @param user_id Veritabanından çekilecek kullanıcı ID numarası
	 * @return Kullanıcının kitap için değerlendirmesi
	 */
	public int fetchUsersRatingFromDatabaseWithISBN(String isbn, int user_id) {
		// Kullanıcının değerlendirmesini tutmak için bir user_rating değişkeni oluşturuldu ve başlangıç değeri 0 olarak atandı.
		int user_rating = 0;
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Belirli bir ISBN numarası ve kullanıcı kimliğiyle kullanıcının değerlendirmesini almak için bir SQL sorgusu hazırlandı.
		String sql = "SELECT * FROM ratings WHERE isbn=? AND user_id=?;";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setString(1, isbn);
			statement.setInt(2, user_id);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde döngü oluşturuldu.
			while(result.next()) {
				// Kullanıcının değerlendirmesi user_rating değişkenine atandı.
				user_rating = result.getInt("book_rating");
			}
			// Sonuç kümesi kapatıldı, ifade kapatıldı ve veritabanı bağlantısı kapatıldı.
			result.close();
            statement.close();
            con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Kullanıcının değerlendirmesi döndürüldü.
		return user_rating;
	}
	
	/**
	 * Bu metod, verilen ISBN numarasına sahip kitabın ISBN numarasını ve ismini veritabanından alır. Bunun için veritabanı bağlantısı kurulur, gerekli SQL sorgusu hazırlanır ve çalıştırılır. Sonuç kümesi üzerinde döngü oluşturularak kitabın ISBN numarası ve ismi alınır. Sonuç kümesi, ifade ve veritabanı bağlantısı kapatılır. Hata durumunda e.printStackTrace() kullanılarak hata takibi sağlanır. En sonunda, kitabın ISBN numarası ve ismi bir metin olarak döndürülür.
	 * @param isbn Veritabanından çekilecek kitabın ISBN numarası
	 * @return Kitabın ISBN numarası ve ismini içeren bir metin
	 */
	public String fetchBooksISBNAndNameFromISBN(String isbn) {
		// Kitabın ISBN numarası ve ismini tutmak için bir booksISBNAndName değişkeni oluşturuldu.
		String booksISBNAndName = "";
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Veritabanından belirli bir ISBN numarasına sahip kitabın bilgilerini almak için bir SQL sorgusu hazırlandı.
		String sql = "SELECT * FROM books WHERE isbn=?";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setString(1, isbn);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde döngü oluşturuldu.
			while(result.next()) {
				// Kitabın ISBN numarası ve ismi booksISBNAndName değişkenine atandı.
				booksISBNAndName = result.getString("isbn") + ", " + result.getString("title");
			}
			// Sonuç kümesi, ifade ve veritabanı bağlantısı kapatıldı.
			result.close();
            statement.close();
            con.close();
		} catch(Exception e) {
			
		}	
		// Kitabın ISBN numarası ve ismi döndürüldü.
		return booksISBNAndName;
	}
	
	/**
	 * Bu metod, belirli bir kullanıcının verilen ISBN numarasına sahip bir kitaba sahip olup olmadığını kontrol eder. Bunun için veritabanı bağlantısı kurulur, gerekli SQL sorgusu hazırlanır ve çalıştırılır. Sonuç kümesinde bir sonraki kayıt varsa, kullanıcının kitaba sahip olduğu anlamına gelir ve doesUserHaveBook değişkeni true olarak ayarlanır. Sonuç kümesi, ifade ve veritabanı bağlantısı kapatılır. Hata durumunda e.printStackTrace() kullanılarak hata takibi sağlanır. En sonunda, kullanıcının kitaba sahip olup olmadığını belirten bir değer döndürülür.
	 * @param isbn Veritabanında kullanıcının sahip olup olmadığı kontrol edilecek kitabın ISBN numarası
	 * @param user_id Veritabanında bir kitaba sahip olup olmadığı kontrol edilecek kullanıcının ID numarası
	 * @return Kullanıcının kitaba sahip olup olmadığını belirten bir değer
	 */
	public boolean checkUserHasTheBook(String isbn, int user_id) {
		// Kullanıcının kitaba sahip olup olmadığını belirtmek için bir doesUserHaveBook değişkeni oluşturuldu ve başlangıç değeri false olarak atandı.
		boolean doesUserHaveBook = false;
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Belirli bir kullanıcının verilen ISBN numarasına sahip bir kitaba sahip olup olmadığını kontrol etmek için bir SQL sorgusu hazırlandı.
		String sql = "SELECT * FROM ratings WHERE user_id=? AND isbn=?;";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setInt(1, user_id);
			statement.setString(2, isbn);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesinde bir sonraki kayıt varsa, kullanıcının kitaba sahip olduğu anlamına gelir ve doesUserHaveBook değişkeni true olarak ayarlanır.
			if (result.next()) {
				doesUserHaveBook = true;
			}
			// Sonuç kümesi, ifade ve veritabanı bağlantısı kapatıldı.
	        result.close();
	        statement.close();
	        con.close();
		} catch (SQLException e) {
			System.out.println("Kullanıcıda kitap mevcut");
			e.printStackTrace();
		}
		// Kullanıcının kitaba sahip olup olmadığı belirtilen değer döndürülür.
		return doesUserHaveBook;
	}
}
