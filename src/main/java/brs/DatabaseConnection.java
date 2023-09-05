package brs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Veritabanı ile bağlantıyı kuran sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class DatabaseConnection {
	/**
	 * Veritabanına bağlanmak için belirlenen kullanıcı adı
	 */
	private String userName = "root";
	/**
	 * Veritabanına bağlanmak için belirlenen şifre
	 */
	private static String password = "";
	/**
	 * Veritabanına bağlanmak için belirlenen host adresi
	 */
	private String host = "localhost";
	
	/**
	 * Bağlanılacak veritabanının adı
	 */
	private String db = "bookrecommendationsystem";
	/**
	 * Bağlanılacak port numarası
	 */
	private int port = 3306;
	
	/**
	 * Veritabanına bağlantıyı sağlamak için kullanılan Connection nesnesi
	 */
	private Connection con = null;
	
	/**
	 * Veritabanına bağlantı için gereken şifreyi belirleyen set metodu
	 * @param password Şifre
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Veritabanı bağlantısını gerçekleştiren metottur. Belirlenen host adresi, port numarası, veritabanı adı gibi bilgileri kullanarak veritabanı sunucusuna bağlanır. Veritabanı bağlantısını sağlamak için mysql-connector-java Connector'unu kullanılır. Bağlantı başarısızlıkla sonuçlanırsa konsol üzerinden hata mesajı gönderir.
	 * @return Bağlantının kurulmasını sağlayan Connection nesnesi
	 */
	public Connection initialize() {
		// Bağlantının kurulacağı sunucu adresi belirlendi.
		String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db;
		
		try {
			// Belirtilen kütüphaneye ulaşım sağlanıp sağlanmadığının kontrolü sağlandı.
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}
		catch(ClassNotFoundException e) {
			// Belirtilen kütüphaneye erişim sağlanmıyorsa bir hata mesajı yazdırıldı.
			System.out.println("MySQL Connector Yok");
			e.printStackTrace();
		}
		
		try {
			// Mevcut Connection türündeki con nesnesine bağlantı sağlanması için gereken sunucu adresi, kullanıcı adı, şifre bilgileri eklendi.
			this.con = DriverManager.getConnection(url, userName, password);
		}
		catch (SQLException e) {
			// Bilgilerde bir eksiklik veya yanlışlık varsa, ya da bağlantı sağlanamıyorsa bir hata mesajı yazdırıldı.
			System.out.println("Baglanti Basarisiz");
			// Şifre yanlış girilmişse veya girilmemişse ekrana hata mesajı verilir.
			JOptionPane.showMessageDialog(null, "The password required to connect to the mysql server is incorrect.", "", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		// Eğer con nesnesine herhangi bir bağlantı bilgileri eklenmişse
		if(con != null) {
			//System.out.println("Basarili");
		}
		// Eğer con nesnesine herhangi bir bağlantı bilgileri eklenmemişse
		else {
			System.out.println("Basarisiz");
		}
		
		// con bağlantı nesnesi döndürüldü.
		return con;
	}
}
