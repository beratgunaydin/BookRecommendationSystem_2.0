package brs;

/**
 * Bir kullanıcının sahip olması gereken bilgileri içeren sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class User {
	/**
	 * Kullanıcının ID numarası
	 */
	private int user_id;
	/**
	 * Kullanıcının adı
	 */
	private String user_name;
	/**
	 * Kullanıcının şifresi
	 */
	private String user_password;
	
	/**
	 * Mevcut kullanıcının ID numarasına ulaşmak için get metodu
	 * @return Mevcut kullanıcının ID numarası
	 */
	public int getUser_id() {
		return user_id;
	}
	
	/**
	 * Parametre olarak gelen ID numarasını mevcut kullanıcının ID numarasına atamak için set metodu
	 * @param user_id Mevcut kullanıcının ID numarasına atanacak ID numarası
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * Mevcut kullanıcının adına ulaşmak için get metodu
	 * @return Mevcut kullanıcının adı
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * Parametre olarak gelen kullanıcının adını mevcut kullanıcının adına atamak için set metodu
	 * @param user_name Mevcut kullanıcının adına atanacak kullanıcı adı
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * Mevcut kullanıcının şifresine ulaşmak için get metodu
	 * @return Mevcut kullanıcının şifresi
	 */
	public String getUser_password() {
		return user_password;
	}
	
	/**
	 * Parametre olarak gelen kullanıcının şifresini mevcut kullanıcının şifresine atamak için set metodu
	 * @param user_password Mevcut kullanıcının şifresine atanacak kullanıcı şifresi
	 */
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}	
}
