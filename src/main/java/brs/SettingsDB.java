package brs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ayarlar için veritabanı işlemlerinin yapıldığı sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class SettingsDB {
	/**
	 * Veritabanı bağlantısının yapıldığı sınıftan alınan örnek
	 */
	DatabaseConnection dc = new DatabaseConnection();
	
	/**
	 * Bu metot, veritabanından benzerlik eşiğini almak için kullanılır. similarityThreshold adında bir tamsayı değişkeni oluşturulur ve başlangıç değeri 0 olarak atanır. Benzerlik eşiğini almak için bir SQL sorgusu hazırlanır. Sorgu, "settings" adlı bir tablodan ID'si 1 olan kaydı seçer. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. Sorgu çalıştırılır ve sonuç kümesi (result) alınır. Sonuç kümesi üzerinde döngü oluşturulur. Genellikle, bu döngü tek bir sonuç satırı için kullanılır, çünkü "id=1" koşulu ile yalnızca bir kaydın alınması beklenir. Her bir sonuç satırında, "value" sütunundaki değer alınır ve similarityThreshold değişkenine atanır. similarityThreshold değişkeni döndürülür, bu sayede benzerlik eşiği değeri elde edilebilir.
	 * @return Benzerlik eşik değeri
	 */
	public int fetchSimilarityThresholdFromDatabase() {
		// Benzerlik eşiğini tutmak için bir similarityThreshold değişkeni oluşturuldu.
		int similarityThreshold = 0;
		// Veritabanı bağlantısı için DatabaseConnection sınıfından veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Benzerlik eşiğini veritabanından almak için bir SQL sorgusu hazırlandı.
		String sql = "SELECT * FROM settings WHERE id=1";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde döngü oluşturuldu.
			while (result.next()) {
				// "value" sütunundaki değer alındı ve similarityThreshold değişkenine atandı.
				similarityThreshold = result.getInt("value");
			}
			// Sonuç kümesi, ifade ve bağlantı kapatıldı.
			result.close();
            statement.close();
            con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		// similarityThreshold değişkenini döndürüldü.
		return similarityThreshold;
	}
	
	/**
	 * Bu metot, veritabanında benzerlik eşiğini güncellemek için kullanılır. isSimilarityThresholdUpdated adında bir boolean değişkeni oluşturulur ve başlangıç değeri false olarak atanır. Bu değişken, güncelleme işleminin başarılı olup olmadığını belirtir. Benzerlik eşiğini güncellemek için bir SQL sorgusu hazırlanır. Sorgu, "settings" adlı tabloda ID'si 1 olan kaydı günceller. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. Parametre olarak newSimilarityThreshold değeri belirlenir. Sorgu çalıştırılır ve etkilenen satır sayısı (result) alınır. Etkilenen satır sayısı 0'dan büyükse, güncelleme işlemi başarılı kabul edilir ve isSimilarityThresholdUpdated değişkeni true olarak ayarlanır. isSimilarityThresholdUpdated değişkeni döndürülür, bu sayede güncelleme işleminin başarılı olup olmadığı bilgisi elde edilebilir.
	 * @param newSimilarityThreshold Veritabanındaki kaydı güncellenecek benzerlik eşik değeri
	 * @return Benzerlik eşik değerinin başarılı bir şekilde güncellenip güncellenmediği bilgisi
	 */
	public boolean updateSimilarityThresholdInDatabase(int newSimilarityThreshold) {
		// İşlemin başarılı olup olmadığını belirtmek için bir isSimilarityThresholdUpdated değişkeni oluşturuluyor
		boolean isSimilarityThresholdUpdated = false;
		// Veritabanı bağlantısı sağlandı.
		Connection con = dc.initialize();
		// Veritabanında benzerlik eşiğini güncellemek için bir SQL sorgusu hazırlandı.
		String sql = "UPDATE settings SET value=? WHERE settings.id=1";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setInt(1, newSimilarityThreshold);
			
			// Sorgu çalıştırıldı ve etkilenen satır sayısı alındı.
			int result = statement.executeUpdate();
			
			// Etkilenen satır sayısı 0'dan büyükse, güncelleme başarılı olarak kabul edildi.
			if (result > 0) {
				isSimilarityThresholdUpdated = true;
			}
			
			// İfade ve veritabanı bağlantısı kapatıldı.
            statement.close();
            con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		// isSimilarityThresholdUpdated değişkeni döndürüldü.
		return isSimilarityThresholdUpdated;
	}
	
	/**
	 * Bu metot, veritabanından her kullanıcı için önerilecek kitap sayısını almak için kullanılır. numberOfBooksToBeRecommendedByEachUser adında bir tamsayı değişkeni oluşturulur ve başlangıç değeri 0 olarak atanır. Bu değişken, her kullanıcı için önerilecek kitap sayısını temsil eder. Her kullanıcı için önerilecek kitap sayısını almak için bir SQL sorgusu hazırlanır. Sorgu, "settings" adlı tabloda ID'si 2 olan kaydı seçer. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. Sorgu çalıştırılır ve sonuç kümesi (result) alınır. Sonuç kümesi üzerinde döngü oluşturulur. Her bir sonuç satırında, "value" sütunundaki değer numberOfBooksToBeRecommendedByEachUser değişkenine atanır. numberOfBooksToBeRecommendedByEachUser değişkeni döndürülür, bu sayede her kullanıcı için önerilecek kitap sayısı elde edilebilir.
	 * @return Her bir kullanıcıdan önerilecek kitap sayısı
	 */
	public int fetchNumberOfBooksToBeRecommendedByEachUserFromDatabase() {
		// Önerilecek kitap sayısını tutmak için bir numberOfBooksToBeRecommendedByEachUser değişkeni oluşturuluyor
		int numberOfBooksToBeRecommendedByEachUser = 0;
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Veritabanından her bir kullanıcı için önerilecek kitap sayısını almak için bir SQL sorgusu hazırlandı.
		String sql = "SELECT * FROM settings WHERE id=2";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Sorgu çalıştırıldı ve sonuç kümesi alındı.
			ResultSet result = statement.executeQuery();
			
			// Sonuç kümesi üzerinde döngü oluşturuldu.
			while (result.next()) {
				// "value" sütunundaki değeri al ve numberOfBooksToBeRecommendedByEachUser değişkenine atandı.
				numberOfBooksToBeRecommendedByEachUser = result.getInt("value");
			}
			// Sonuç kümesi kapatıldı, ifade kapatıldı ve veritabanı bağlantısı kapatıldı.
			result.close();
            statement.close();
            con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// numberOfBooksToBeRecommendedByEachUser değişkeni döndürüldü.
		return numberOfBooksToBeRecommendedByEachUser;
	}
	
	/**
	 * Bu metot, veritabanında her bir kullanıcı için önerilecek kitap sayısını güncellemek için kullanılır. isNumberOfBooksToBeRecommendedByEachUserUpdated adında bir boolean değişkeni oluşturulur ve başlangıç değeri false olarak atanır. Bu değişken, güncelleme işleminin başarılı olup olmadığını belirtir. Her bir kullanıcı için önerilecek kitap sayısını güncellemek için bir SQL sorgusu hazırlanır. Sorgu, "settings" adlı tabloda ID'si 2 olan kaydı günceller. Parametreleriyle birlikte çalıştırılabilmesi için hazırlanmış bir ifade (statement) oluşturulur. Parametre olarak newNumberOfBooksToBeRecommendedByEachUser değeri atanır. Sorgu çalıştırılır ve etkilenen satır sayısı (result) alınır. Etkilenen satır sayısı 0'dan büyükse, güncelleme işlemi başarılı olarak kabul edilir ve isNumberOfBooksToBeRecommendedByEachUserUpdated değişkeni true olarak ayarlanır. Herhangi bir istisna durumunda hata izleri (e.printStackTrace()) görüntülenir. isNumberOfBooksToBeRecommendedByEachUserUpdated değişkeni döndürülür, bu sayede güncelleme işleminin başarılı olup olmadığı kontrol edilebilir.
	 * @param newNumberOfBooksToBeRecommendedByEachUser Her bir kullanıcıdan önerilecek kitap sayısı
	 * @return Her bir kullanıcıdan önerilecek kitap sayısının güncellenip güncellenmediği bilgisi
	 */
	public boolean updateNumberOfBooksToBeRecommendedByEachUserInDatabase(int newNumberOfBooksToBeRecommendedByEachUser) {
		// İşlemin başarılı olup olmadığını belirtmek için bir isNumberOfBooksToBeRecommendedByEachUserUpdated değişkeni oluşturuldu.
		boolean isNumberOfBooksToBeRecommendedByEachUserUpdated = false;
		// Veritabanı bağlantısı başlatıldı.
		Connection con = dc.initialize();
		// Veritabanında her bir kullanıcı için önerilecek kitap sayısını güncellemek için bir SQL sorgusu hazırlandı.
		String sql = "UPDATE settings SET value=? WHERE settings.id=2";
		//SQL sorgularını parametrelerle birlikte çalıştırmak için PreparedStatement sınıfından statement örneği alındı.
		PreparedStatement statement;
		
		try {
			// Veritabanı sorgusu hazırlandı.
			statement = con.prepareStatement(sql);
			
			// Parametreler belirlendi.
			statement.setInt(1, newNumberOfBooksToBeRecommendedByEachUser);
			
			// Sorgu çalıştırıldı ve etkilenen satır sayısı alındı.
			int result = statement.executeUpdate();
			
			// Etkilenen satır sayısı 0'dan büyükse, güncelleme başarılı olarak kabul edildi.
			if (result > 0) {
				isNumberOfBooksToBeRecommendedByEachUserUpdated = true;
			}
			// İfade ve veritabanı bağlantısı kapatıldı.
            statement.close();
            con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// isNumberOfBooksToBeRecommendedByEachUserUpdated değişkenini döndürüldü.
		return isNumberOfBooksToBeRecommendedByEachUserUpdated;
	}
}
