package brs;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * Puanlamalar için veritabanı işlemlerinin yapıldığı sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class RatingsDB {
	/**
	 * Veritabanı bağlantısının yapıldığı sınıftan alınan örnek
	 */
	public DatabaseConnection dc = new DatabaseConnection();
	
	/**
	 * Veritabanına puanlama kaydının yapıldığı metottur. Girilen sql sorgusu ile ratings tablosundaki alanlar, verilen bilgiler ile doldurulur. Bu metotta puanlama listesindeki puanlar veritabanına kaydedilir.
	 * @param user Veritabanına kaydedilecek puanlama yapan kullanıcı
	 * @param selectedBook Veritabanına kaydedilecek puanlanan kitap
	 * @param rating_score Veritabanına kaydedilecek puan
	 */
	public void addRatingToDatabase(User user, Book selectedBook, int rating_score) {
		// Veritabanı bağlantısı için DatabaseConnection sınıfından veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Girilen bilgiler için ratings tablosundaki user_id, isbn ve book_rating alanları verilen bilgilerle dolduruldu.
		String sql = "INSERT INTO ratings (user_id, isbn, book_rating) VALUES (?, ?, ?)";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setInt(1, user.getUser_id());
			statement.setString(2, selectedBook.getIsbn());
			statement.setInt(3, rating_score);
				
			// Sorgu çalıştırıldı ve etkilenen satır sayısı alındı.
			int result = statement.executeUpdate();
			
			// Sorgu başarılı döndüğünde mesaj yazdırıldı.
			if (result > 0) {
				System.out.println("");
			}
		} catch (SQLException e) {
			System.out.println("Eklenemedi");
			//e.printStackTrace();
		}
	}
	
	/**
	 * Veritabanından, parametre olarak gelen isbn numarasıyla kitap adı ve kullanıcı nesnesi üzerinden puanlama bilgilerinin çekildiği metottur. Bir SQL sorgusu ile aranan kullanıcı ID'si ve isbn numarasına sahip puanlamayı bulur ve kullanıcının kitap için değerlendirme puanını döndürür. 
	 * @param book_isbn_and_name Puanlanan kitabın isbn numarası ve adı
	 * @param user Puanlama yapan kullanıcı
	 * @return Seçili kitaba verilen puan
	 */
	public int findRatingsAndPropertiesForTheSelectedBook(String book_isbn_and_name, User user) {
		// Gelen parametre virgülle ayırılarak ISBN ve kitap adı elde edildi.
		String[] arr = book_isbn_and_name.split(",");
		// Ayırılan veriden ilki isbn olarak atandı.
		String isbn = arr[0];
		// Değerlendirme başlangıç değeri atandı.
		int rating = 0;
		// Veritabanı bağlantısı sağlandı.
		Connection con = dc.initialize();
		// Belirli isbn numarasına ve kullanıcı adına sahip puanlamayı seçmek için SQL sorgusu oluşturuldu.
		String sql = "SELECT * FROM ratings WHERE user_id=? AND isbn=?;";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setInt(1, user.getUser_id());
			statement.setString(2, isbn);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde dönerek bulunan kitabın özellikleri örneklenen kitap nesnesine atandı.
			while (result.next()) {
				rating = result.getInt("book_rating");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Değerlendirme puanı döndürüldü.
		return rating;
	}
	
	/*public void exportRatingListToDatabase() {
		Connection con = dc.initialize();
		String sql = "INSERT INTO ratings (user_id, isbn, book_rating) SELECT * FROM (SELECT ?, ?, ?) AS tmp WHERE NOT EXISTS (SELECT user_id FROM ratings WHERE user_id = ? AND isbn = ?);";
		PreparedStatement statement;
		
		for (Rating rating : RecommendationEngine.getRatingsList()) {
			try {
				statement = con.prepareStatement(sql);
				statement.setInt(1, rating.getUser_id());
				statement.setString(2, rating.getIsbn());
				statement.setInt(3, rating.getBook_rating());
				statement.setInt(4, rating.getUser_id());
				statement.setString(5, rating.getIsbn());
				
				
				int result = statement.executeUpdate();
				
				if (result > 0) {
					System.out.println("Ekleeeeeeeeeeeeeeeeeeeeee");
				}
			
			} catch (SQLException e) {
				System.out.println("Eklenemedi");
				e.printStackTrace();
			}
		}
	}*/
}
