package brs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Bir puanlamanın sahip olması gereken bilgileri içeren sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class Rating {
	/**
	 * Puanlama yapan kullanıcının ID'si
	 */
	private int user_id;
	/**
	 * Puanlanan kitabın ISBN numarası
	 */
	private String isbn;
	/**
	 * Kullanıcının kitaba verdiği puan
	 */
	private int book_rating;
	
	/**
	 * Mevcut puanlama yapan kullanıcının ID'sine ulaşmak için get metodu
	 * @return Mevcut puanlama yapan kullanıcının ID'si
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * Parametre olarak gelen puanlama yapan kullanıcının ID'sini mevcut kullanıcının ID'sine atamak için set metodu
	 * @param user_id Mevcut kullanıcının ID'sine atanacak puanlama yapan kullanıcının ID'Si
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * Mevcut puanlanan kitabın ISBN numarasına ulaşmak için get metodu
	 * @return Mevcut puanlanan kitabın ISBN numarası
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * Parametre olarak gelen puanlanan kitabın ISBN numarasını mevcut puanlanan kitabın ISBN numarasına atamak için set metodu
	 * @param isbn Mevcut puanlanan kitabın ISBN numarasına atanacak puanlanan kitabın ISBN numarası
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * Mevcut kullanıcının kitaba verdiği puana ulaşmak için get metodu
	 * @return Mevcut kullanıcının kitaba verdiği puan
	 */
	public int getBook_rating() {
		return book_rating;
	}
	/**
	 * Parametre olarak gelen kullanıcının kitaba verdiği puanı mevcut kullanıcının kitaba verdiği puana atamak için set metodu
	 * @param book_rating Mevcut kullanıcının kitaba verdiği puana atanacak kullanıcının kitaba verdiği puan
	 */
	public void setBook_rating(int book_rating) {
		this.book_rating = book_rating;
	}
	
	/**
	 * Csv dosyasından puanlama bilgilerinin okunup puanlama listesine eklendiği metottur.
	 * @throws IOException Dosyaya erişmeye çalışırken oluşan özel durumları belirler
	 */
	public static void ReadRatingsFromCSVFile() throws IOException {
		// String path = "data/Ratings.csv";
		String path = "src/main/java/dataset/RatingsDataset.csv";
		String line;
		
		BufferedReader reader = new BufferedReader(new FileReader(path));
		
		int counter = 0;
		while((line = reader.readLine()) != null) {
			String[] ratingsProperties = line.split(",");
			
			Rating rating = new Rating();
			
			rating.setUser_id(Integer.parseInt(ratingsProperties[0]));
			rating.setIsbn(ratingsProperties[1]);
			rating.setBook_rating(Integer.parseInt(ratingsProperties[2]));
			
			//RecommendationEngine.AddRatingsToRatingsList(rating);
		}
	}
}
