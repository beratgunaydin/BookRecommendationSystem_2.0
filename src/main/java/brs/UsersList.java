package brs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Verisetindeki kullanıcıların okunmasını ve bir listeye aktarılmasını sağlayan sınıf
 * @author BeratGunaydin
 * @version 1.0.0 
 */
public class UsersList {
	/**
	 * Kullanıcıların listesini temsil eden bir statik ArrayList oluşturuldu.
	 */
	private static List<User> usersList = new ArrayList<User>();

	/**
	 * Kullanıcı listesine ulaşmak için get metodu
	 * @return Mevcut kullanıcı listesi
	 */
	public static List<User> getUsersList() {
		return usersList;
	}

	/**
	 * Parametre olarak gelen kullanıcı listesini mevcut kullanıcı listesine atayan set metodu
	 * @param usersList Mevcut kullanıcı listesine atanacak kullanıcı listesi
	 */
	public static void setUsersList(List<User> usersList) {
		UsersList.usersList = usersList;
	}
	
	/**
	 * Csv dosyasından kullanıcı bilgilerinin okunup kullanıcı listesine eklendiği metottur.
	 * @throws IOException Dosyaya erişmeye çalışırken oluşan özel durumları belirler
	 */
	public static void ReadUsersFromCSVFile() throws IOException {
		// CSV dosyasının yolu belirlendi.
		String path = "src/main/java/dataset/UsersList.csv";
		// Satırı temsil eden bir değişken tanımlandı.
		String line = "";
		
		// Dosya okuyucusu oluşturuldu.
		BufferedReader reader = new BufferedReader(new FileReader(path));
		
		// Dosyanın sonuna gelinene kadar satır satır okuma yapıldı.
		while((line = reader.readLine()) != null) {
			// Satırı virgüllerle ayrıştırıp özellikleri içeren bir dizi oluşturuldu.
			String usersProperties[] = line.split(",");
			
			// Yeni bir User nesnesi oluşturuldu.
			User user = new User();
			
			// User nesnesinin özellikleri belirlendi.
			user.setUser_id(Integer.parseInt(usersProperties[0]));
			user.setUser_name(usersProperties[1]);
			user.setUser_password(usersProperties[2]);
			
			// User nesnesi kullanıcı listesine eklendi.
			usersList.add(user);
		}
	}
	
	/**
	 * Parametre olarak gelen kullanıcı adı ve şifreyi kullanıcı listesi içerisinde arayan metottur.
	 * @param userName Kullanıcı listesi içerisinde aranacak kullanıcının adı
	 * @param password Kullanıcı listesi içerisinde aranacak kullanıcı şifresi
	 * @return Liste içerisinde bulunan kullanıcı nesnesi
	 */
	public static User searchUserInList(String userName, String password) {
		// Boş bir User nesnesi oluşturuldu.
		User user = new User();
		
		// Kullanıcı listesi içerisinde gezinildi.
		for(User users : UsersList.getUsersList()) {
			// Kullanıcının kullanıcı adı ve şifresi eşleşiyorsa
			if (users.getUser_name().equals(userName) && users.getUser_password().equals(password)) {
				// Bulunan kullanıcı user değişkenine atandı.
				user = users;
				// Döngüden çıkıldı.
				break;
			}
		}
		// Bulunan kullanıcı geri döndürüldü.
		return user;
	}
}
