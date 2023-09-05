package brs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Verisetindeki kitapların okunmasını ve bir listeye aktarılmasını sağlayan sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class Library {
	/**
	 * Kitap bilgilerinin tutulduğu liste
	 */
	private static List<Book> BooksList = new ArrayList<Book>();
	
	/**
	 * Kitap bilgilerine erişmek için kullanılan get metodu
	 * @return Mevcut kitap listesi
	 */
	public static List<Book> getBooksList() {
		return BooksList;
	}

	/**
	 * Parametre olarak gelen kitap listesini mevcut kitap listesine atayan set metodu
	 * @param booksList Mevcut kitap listesine atanacak kitap listesi
	 */
	public static void setBooksList(List<Book> booksList) {
		BooksList = booksList;
	}


	/**
	 * Csv dosyasından kitap bilgilerinin okunup kitap listesine eklendiği metottur. Bu metotta kitap bilgilerinden boş alanlara sahip olanları uygulamanın hata vermemesi için kontrol sağlanıp listeye eklenmemiştir. Bunun kontrolü için de kaç adet kitap bilgisi dönndürüldüğüne bakılmıştır.
	 * @throws IOException Dosyaya erişmeye çalışırken oluşan özel durumları belirler
	 */
	public static void ReadBooksFromCSVFile() throws IOException {
		//String path = "data/Books.csv";
		String path = "src/main/java/dataset/BooksDataset.csv";
		String line;
		
		BufferedReader reader = new BufferedReader(new FileReader(path));
		
		int counter = 0;
		while((line = reader.readLine()) != null) {
			String[] booksProperties = line.split(",");
			
			Book book = new Book();
			
			book.setIsbn(booksProperties[0]);
			book.setTitle(booksProperties[1]);
			book.setAuthor(booksProperties[2]);
			book.setPublication_date(Integer.parseInt(booksProperties[3]));
			book.setPublisher(booksProperties[4]);
			
			BooksList.add(book);
			
			/*if (booksProperties.length == 8) {
				counter++;
				book.setIsbn(booksProperties[0]);
				book.setTitle(booksProperties[1]);
				book.setAuthor(booksProperties[2]);
				book.setPublication_date(Integer.parseInt(booksProperties[3]));
				book.setPublisher(booksProperties[4]);
				
				BooksList.add(book);
			}*/
		}
		System.out.println(counter);
	}
}
