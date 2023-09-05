package brs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Verisetleri üzerinde gerekli düzeltme ve düzenlemelerin gerçekleştirildiği sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class Dataset {

	/**
	 * İşlemlerin gerçekleştirileceği ana metot
	 * @param args Komut satırı argümanları
	 * @throws IOException Dosyaya erişmeye çalışırken oluşan özel durumları belirler
	 */
	public static void main(String[] args) throws IOException {
		// Library sınıfındaki ReadBooksFromCSVFile metodu kullanılarak csv dosyasındaki kitap bilgileri okundu.
		Library.ReadBooksFromCSVFile();
		// Rating sınıfındaki ReadRatingsFromCSVFile metodu kullanılarak csv dosyasındaki puanlama bilgileri okundu.
		Rating.ReadRatingsFromCSVFile();
		// Puanlama bilgilerinin düzenlenip farklı bir csv dosyasına aktarılması için EditRatingsDataset motuduna gidildi.
		EditRatingsDataset();
		// Kitap bilgilerinin düzenlenip farklı bir csv dosyasına aktarılması için EditBooksDataset motuduna gidildi.
		EditBooksDataset();
	}
	
	/**
	 * Kitap bilgileri düzenlenip farklı bir csv dosyasına aktarılır. Bunun sebebi kullanılan verisetinin tam olarak doğru formatta olmamasıdır.
	 * @throws IOException Dosyaya erişmeye çalışırken oluşan özel durumları belirler
	 */
	private static void EditBooksDataset() throws IOException {
		List<Book> bookList = Library.getBooksList();
		
		File csvFile = new File("dataset/BooksDataset.csv");
		FileWriter fileWriter = new FileWriter(csvFile);
		
		for(Book book : bookList) {
			StringBuilder line = new StringBuilder();
			line.append(book.getIsbn());
			line.append(',');
			line.append(book.getTitle());
			line.append(',');
			line.append(book.getAuthor());
			line.append(',');
			line.append(String.valueOf(book.getPublication_date()));
			line.append(',');
			line.append(book.getPublisher());
			line.append("\n");
			fileWriter.write(line.toString());
		}
		fileWriter.close();
	}
	
	/**
	 * Puanlama bilgileri düzenlenip farklı bir csv dosyasına aktarılır. Bunun sebebi kullanılan verisetinin tam olarak doğru formatta olmamasıdır.
	 * @throws IOException Dosyaya erişmeye çalışırken oluşan özel durumları belirler
	 */
	private static void EditRatingsDataset() throws IOException {
		//List<Rating> ratingList = RecommendationEngine.getRatingsList();
		List<Book> bookList = Library.getBooksList();
		
		File csvFile = new File("dataset/RatingsDataset.csv");
		FileWriter fileWriter = new FileWriter(csvFile);
		
		/*for(Rating rating : ratingList) {
			StringBuilder line = new StringBuilder();
			boolean checkISBN = false;
			
			for(Book book : bookList) {
				if(rating.getIsbn().equals(book.getIsbn())) {
					checkISBN = true;
					break;
				}
			}
			if(checkISBN) {
				line.append(String.valueOf(rating.getUser_id()));
				line.append(',');
				line.append(rating.getIsbn());
				line.append(',');
				line.append(String.valueOf(rating.getBook_rating()));
				line.append("\n");
				fileWriter.write(line.toString());
			}
		}*/
		System.out.println("Finished");
		fileWriter.close();
	}
}
