package brs;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;

import java.sql.Connection;

/**
 * Kitaplar için veritabanı işlemlerinin yapıldığı sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class LibraryDB {
	/**
	 * Veritabanı bağlantısının yapıldığı sınıftan alınan örnek
	 */
	DatabaseConnection dc = new DatabaseConnection();
	
	/**
	 * Veritabanına kitap kaydının yapıldığı metottur. Girilen sql sorgusu ile books tablosundaki alanlar, verilen bilgiler ile doldurulur. Bu metotta kitap listesindeki kitaplar veritabanına kaydedilir. Her başarılı kayıt yapıldığında konsola yeni kayıt eklendi mesajı yazdırılır. 
	 */
	public void insertBookToDataBase() {
		// Veritabanı bağlantısı için DatabaseConnection sınıfından veritabanı bağlantısı başlatıldı.
		Connection conn = dc.initialize();
		// Girilen bilgiler için books tablosundaki isbn, title, author, publication_date ve publisher alanları verilen bilgilerle dolduruldu.
		String sql = "INSERT INTO books (isbn, title, author, publication_date, publisher) VALUES (?, ?, ?, ?, ?)";
		// Bir sayaç oluşturuldu ve başlangıç olarak 0 değeri atandı.
		int counter = 0;
		// Bir for döngüsü ile kitap listesi içerisinde gezildi.
		for (Book book : Library.getBooksList()) {
			//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
			PreparedStatement statement;
			try {
				// Veritabanı sorgusu hazırlandı.
				statement = conn.prepareStatement(sql);
				
				// Parametreler belirlendi.
				statement.setString(1, book.getIsbn());
				statement.setString(2, book.getTitle());
				statement.setString(3, book.getAuthor());
				statement.setInt(4, book.getPublication_date());
				statement.setString(5, book.getPublisher());
				
				// Sorgu çalıştırıldı ve etkilenen satır sayısı alındı.
				int rowsInserted = statement.executeUpdate();
				
				// Sorgu başarılı döndüğünde mesaj yazdırıldı.
	            if (rowsInserted > 0) {
	                System.out.println("Yeni kayıt eklendi!");
	            }
			} catch (SQLException e) {
				counter++;
				e.printStackTrace();
			}
		}
		// Toplam kaç kitap nesnesinin eklendiği mesaj olarak yazdırıldı.
		System.out.println(counter);
	}
	
	/**
	 * Veritabanına kitap kaydının yapıldığı metottur. Girilen sql sorgusu ile books tablosundaki alanlar, verilen bilgiler ile doldurulur. Bu metotta parametre olarak gelen kitap bilgileri veritabanına kaydedilir.
	 * @param isbn Veritabanına kaydedilecek kitabın benzersiz isbn numarası
	 * @param title Veritabanına kaydedilecek kitabın adı
	 * @param author Veritabanına kaydedilecek kitabın yazarı
	 * @param publication_date Veritabanına kaydedilecek kitabın yayın tarihi
	 * @param publisher Veritabanına kaydedilecek kitabın yayıncısı
	 * @return Kitabın başarılı bir şekilde veritabanına kaydedilip kaydedilmediği bilgisi
	 */
	public boolean insertBookToDataBase(String isbn, String title, String author, int publication_date, String publisher) {
		// Veritabanı bağlantısı oluşturuldu.
		Connection con = dc.initialize();
		// Veri tabanına eklenecek kitap için SQL sorgusu oluşturuldu.
		String sql = "INSERT INTO books (isbn, title, author, publication_date, publisher) VALUES (?, ?, ?, ?, ?)";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		// Kayıt işleminin başarılı olup olmadığını tutan değişken oluşturuldu.
		boolean isRegistrationSuccessfull = false;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setString(1, isbn);
			statement.setString(2, title);
			statement.setString(3, author);
			statement.setInt(4, publication_date);
			statement.setString(5, publisher);
			
			// Sorgu çalıştırıldı ve etkilenen satır sayısı alındı.
			int result = statement.executeUpdate();
			
			// Etkilenen satır sayısına göre kayıt işleminin başarılı olup olmadığı belirlendi.
			if (result > 0) {
				isRegistrationSuccessfull = true;
			}
		} catch (SQLException e) {
			System.out.println("Kitap eklenemedi");
			e.printStackTrace();
		}
		// Kayıt işleminin başarılı olup olmadığı döndürüldü.
		return isRegistrationSuccessfull;
	}
	
	/**
	 * Veritabanından kitap kaydının silindiği metottur. Girilen sql sorgusu ile books tablosundan ilgili isbn numarasına sahip kitap kaydı silinir.
	 * @param book_isbn_and_name Silinmesi istenen kitabın isbn numarası ve adı
	 * @return Kitabın başarılı bir şekilde veritabanından silinip silinmediği bilgisi
	 */
	public boolean deleteBookAndBooksRatingsFromDatabase(String book_isbn_and_name) {
		// Kitabın başarılı bir şekilde silinip silinmediğini tutan değişken oluşturuldu.
		boolean isBookDeletedSuccessfully = false;
		
		// Gelen parametre kitabın isbn numarasını ve adını içerdiği için ayrıştırıldı.
		String[] arr = book_isbn_and_name.split(",");
		// Ayrıştırılan veriden isbn numarası alındı.
		String isbn = arr[0];
		
		// Veritabanı bağlantısı oluşturuldu.
		Connection con = dc.initialize();
		
		// Kitabı ve kitabın derecelendirmelerini silmek için SQL sorgusu oluşturuldu.
		String sql = "DELETE books, ratings FROM books LEFT JOIN ratings ON books.isbn = ratings.isbn WHERE books.isbn = ?;";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setString(1, isbn);
			
			// Sorgu çalıştırıldı ve etkilenen satır sayısı alındı.
			int result = statement.executeUpdate();
			// Etkilenen satır sayısına göre kitabın başarılı bir şekilde silinip silinmediği belirlendi.
			if (result > 0) {
				isBookDeletedSuccessfully = true;
	        } else {
	            System.out.println("Sorgudan sonuç dönmüyor");
	        }
		} catch (SQLException e) {
			System.out.println("Kitap Silinemedi");
			e.printStackTrace();
		}
		// Kitabın başarılı bir şekilde silinip silinmediği döndürüldü.
		return isBookDeletedSuccessfully;	
	}
	
	/**
	 * Veritabanında kitap araması yapılan metottur. Eğer bir arama verisi girilmişse bu arama ile ilgili sorguyla arama sonucundan bir liste oluşturur ve bunu geri döndürür. Eğer bir arama verisi girilmemişse books tablosundaki ilk 100 veri ile bir liste oluşturur ve bunu geri döndürür.
	 * @param search Kitap adları içerisinde aranacak giriş
	 * @return Arama sonucu oluşturulan kitap listesi
	 */
	public DefaultListModel<String> searchBookInDatabase(String search) {
		// Arama sonuçlarını tutacak bir DefaultListModel oluşturuldu.
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		// Veritabanı bağlantısı oluşturuldu
		Connection con = dc.initialize();
		// SQL sorgusu için bir değişken oluşturuldu ve başlangıç değeri olarak null atandı.
		String sql = null;
		
		// Arama parametresi null değilse, ilgili sorgu oluşturuldu.
		if(search != null) {
			sql = "SELECT * FROM books WHERE title COLLATE UTF8MB4_GENERAL_CI LIKE '" + search + "%' LIMIT 100";
		}
		
		// Arama parametresi null ise, tüm kitapları getirecek sorguyu oluşturuldu.
		else {
			sql = "SELECT * FROM books ORDER BY RAND() LIMIT 100";
		}
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde dönerek bulunan kitaplar DefaultListModel'e eklendi.
			while (result.next()) {
				dlm.addElement(result.getString("isbn") + ", " + result.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		// DefaultListModel döndürüldü.
		return dlm;
	}
	
	/**
	 * Veritabanından kullanıcının puanlama yaptığı kitapları çeken metottur. Parametre olarak gelen kullanıcının veritabanında kayıtlı olan kitaplara verdiği puanlara göre kullanıcının kitap listesini oluşturur.
	 * @param user Puanladığı kitap listesi oluşturulacak kullanıcı
	 * @return Parametre olarak gelen kullanıcının puanladığı kitapların listesi
	 */
	public DefaultListModel<String> createUsersBooksList(User user) {
		// Kullanıcının kitaplarını tutacak bir DefaultListModel oluşturuldu.
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		// Veritabanı bağlantısı oluşturuldu.
		Connection con = dc.initialize();
		// Belirli ID'ye sahip kullanıcının puanlama yaptığı kitapları seçmek için SQL sorgusu oluşturuldu.
		String sql = "SELECT * FROM ratings INNER JOIN books ON ratings.isbn = books.isbn WHERE ratings.user_id =?";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setInt(1, user.getUser_id());
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde dönerek bulunan kitaplar DefaultListModel'e eklendi.
			while (result.next()) {
				dlm.addElement(result.getString("ratings.isbn") + ", " + result.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DefaultListModel döndürüldü.
		return dlm;
	}
	
	/**
	 * Veritabanından, parametre olarak gelen isbn numarası ve kitap adı üzerinden kitabın bilgilerinin çekildiği metottur. Bir SQL sorgusu ile aranan isbn numarasına sahip kitabı bulur ve bilgilerini Book türündeki nesneye atar.
	 * @param book_isbn_and_name Özelliklerine erişilmek istenen kitabın isbn numarası ve adı
	 * @return Parametre olarak gelen kitabın özelliklerini içeren Book türündeki nesne
	 */
	public Book findPropertiesOfTheSelectedBook(String book_isbn_and_name) {
		// Gelen parametre virgülle ayırılarak ISBN ve kitap adı elde edildi.
		String[] arr = book_isbn_and_name.split(",");
		// Ayırılan veriden ilki isbn olarak atandı.
		String isbn = arr[0];
		// Kitap nesnesi oluşturuldu.
		Book book = new Book();
		// Veritabanı bağlantısı sağlandı.
		Connection con = dc.initialize();
		// Belirli isbn numarasına sahip kitabı seçmek için SQL sorgusu oluşturuldu.
		String sql = "SELECT * FROM books WHERE isbn=?;";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			// Parametreler belirlendi.
			statement.setString(1, isbn);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde dönerek bulunan kitabın özellikleri örneklenen kitap nesnesine atandı.
			while (result.next()) {
				book.setIsbn(result.getString("isbn"));
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setPublication_date(result.getInt("publication_date"));
				book.setPublisher(result.getString("publisher"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Kitap nesnesi döndürüldü.
		return book;
	}
	
	/**
	 * Veritabanını kontrol ederek belirli kullanıcının belirli kitaba daha önce puan verip vermediğini belirleyen metottur. Parametre olarak gelen Book ve User türündeki nesnelerin özellikleri ile belirli bir kitaba belirli kullanıcının puanlama yapıp yapmadığını bir SQL sorgusu ile kontrol eder.
	 * @param book Parametre olarak gelen Book türündeki kitap nesnesi
	 * @param user Parametre olarak gelen User türündeki kullanıcı nesnesi
	 * @return Parametre olarak gelen kullanıcının parametre olarak gelen kitaba daha önce puan verip vermediği bilgisi
	 */
	public boolean checkUserHasTheBook(Book book, User user) {
		// Kullanıcının kitaba sahip olup olmadığını tutacak bir değişken oluşturuldu.
		boolean doesUserHaveBook = false;
		// Veritabanı bağlantısı sağlandı.
		Connection con = dc.initialize();
		// Belirli kullanıcı id'sine ve kitap isbn numarasına sahip puanlamayı seçen SQL sorgusu oluşturuldu.
		String sql = "SELECT * FROM ratings WHERE user_id=? AND isbn=?";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setInt(1, user.getUser_id());
			statement.setString(2, book.getIsbn());
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Etkilenen satır sayısına göre kullanıcının kitaba daha önce puan verip vermediği belirlendi.
			if (result.next()) {
				doesUserHaveBook = true;
			}
		} catch (SQLException e) {
			System.out.println("Kullanıcıda kitap mevcut");
			//e.printStackTrace();
		}
		// Kullanıcının kitaba daha önce puan verip vermediği döndürüldü.
		return doesUserHaveBook;
	}
	
	/**
	 * Yöneticinin veritabanına kitap kaydı eklerken başka bir kitabın isbn numarasıyla çakışıp çakışmadığını kontrol eden metottur. Parametre olarak gelen isbn numarasının veritabanında kayıtlı olup olmadığını bir SQL sorgusu ile kontrol eder.
	 * @param isbn Parametre olarak gelen veritabanına kayıt için kontrol edilecek isbn numarası
	 * @return Parametre olarak gelen isbn numarasının veritabanında kayıtlı olup olmadığı bilgisi
	 */
	public boolean checkISBNInDatabase(String isbn) {
		// ISBN'nin veritabanında kayıtlı olup olmadığını tutacak bir değişken oluşturuldu.
		boolean isISBNRegisteredInDatabase = false;
		// Veritabanı bağlantısı sağlandı.
		Connection con = dc.initialize();
		
		// books tablosundan belirli isbn numarasına sahip kayıtların sayısını seçen bir SQL sorgusu oluşturuldu.
		String sql = "SELECT COUNT(*) FROM books WHERE isbn = ?";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			// Parametreler belirlendi.
			statement.setString(1, isbn);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Etkilenen satır sayısına göre kullanıcının kitaba daha önce puan verip vermediği belirlendi.
			if (result.next()) {
				// Sonuç kümesindeki sayı alındı.
	            int count = result.getInt(1);
	         // ISBN'nin veritabanında kayıtlı olup olmadığı kontrol edildi.
	            isISBNRegisteredInDatabase = count > 0;
	        } else {
	            System.out.println("Sorgudan sonuç dönmüyor");
	        }			
		} catch (SQLException e) {
			System.out.println("ISBN Mevcut");
			e.printStackTrace();
		}
		// ISBN numarasının veritabanında kayıtlı olup olmadığı döndürüldü.
		return isISBNRegisteredInDatabase;
	}
}
