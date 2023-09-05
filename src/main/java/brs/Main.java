package brs;

import java.io.IOException;
import java.util.List;

import ui.LoginPage;
import ui.MySQLPasswordPage;

/**
 * Uygulamanın başlatıldığı ana sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class Main {

	/**
	 * Uygulamanın başlatıldığı ana metot
	 * @param args Komut satırı argümanları
	 * @throws IOException Dosyaya erişmeye çalışırken oluşan özel durumları belirler
	 */
	public static void main(String[] args) throws IOException {
		//Library.ReadBooksFromCSVFile();
		//Rating.ReadRatingsFromCSVFile();
		//RatingsDB rdb = new RatingsDB();
		//rdb.exportRatingListToDatabase();
		
		//UsersDB udb = new UsersDB();
		//udb.registerNewUserRating();

		
		
		//UsersList.ReadUsersFromCSVFile();
		//LibraryDB libraryDB = new LibraryDB();
		
		//libraryDB.initialize();
		
		//System.out.println(Library.getBooksList().size());
		//System.out.println(RecommendationEngine.getRatingsList().size());
		//System.out.println(UsersList.getUsersList().size());
		
		/*for(User user : UsersList.getUsersList()) {
			System.out.println(user.getUser_id() + ", " + user.getUser_name() + ", " + user.getUser_password());
		}*/
		
		// Uygulamanın ilk sayfası olan giriş ekranının örneği alındı.
		LoginPage lp = new LoginPage();
		// Arayüz sayfasının tasarımı baştan dizayn edildiği için başlangıç tasarımı kapatıldı.
		lp.setUndecorated(true);
		// Sayfa görünür olarak ayarlandı.
		lp.setVisible(true);
		
		// MySQL şifresi girilecek sayfanın örneği alındı.
		MySQLPasswordPage mpp = new MySQLPasswordPage();
		// Sayfa görünür olarak ayarlandı.
		mpp.setVisible(true);
		
		/*UsersDB udb = new UsersDB();
		int count = udb.getUsersCountInDatabase();
		System.out.print(count);*/
	}
}
