package brs;

/**
 * Bir kitabın sahip olması gereken bilgileri içeren sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class Book {
	/**
	 * Kitabın benzersiz olan isbn numarası
	 */
	private String isbn;
	/**
	 * Kitabın adı
	 */
	private String title;
	/**
	 * Kitabın yazarı
	 */
	private String author;
	/**
	 * Kitabın yayın tarihi
	 */
	private int publication_date;
	/**
	 * Kitabın yayıncısı
	 */
	private String publisher;
	
	/**
	 * Mevcut kitabın benzersiz isbn numarasına ulaşmak için get metodu
	 * @return Mevcut kitabın benzersiz isbn numarası
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * Parametre olarak gelen benzersiz isbn numarasını mevcut kitabın benzersiz isbn numarasına atamak için set metodu
	 * @param isbn Mevcut kitabın benzersiz isbn numarasına atanacak benzersiz isbn numarası
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * Mevcut kitabın adına ulaşmak için get metodu
	 * @return Mevcut kitabın adı
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Parametre olarak gelen kitabın adını mevcut kitabın adına atamak için set metodu
	 * @param title Mevcut kitabın adına atanacak kitabın adı
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Mevcut kitabın yazarına ulaşmak için get metodu
	 * @return Mevcut kitabın yazarı
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * Parametre olarak gelen kitabın yazarını mevcut kitabın yazarına atamak için set metodu
	 * @param author Mevcut kitabın yazarına atanacak kitabın yazarı
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * Mevcut kitabın yayın tarihine ulaşmak için get metodu
	 * @return Mevcut kitabın yayın tarihi
	 */
	public int getPublication_date() {
		return publication_date;
	}
	/**
	 * Parametre olarak gelen kitabın yayın tarihini mevcut kitabın yayın tarihine atamak için set metodu
	 * @param publication_date Mevcut kitabın yayın tarihine atanacak kitabın yayın tarihi
	 */
	public void setPublication_date(int publication_date) {
		this.publication_date = publication_date;
	}
	/**
	 * Mevcut kitabın yayıncısına ulaşmak için get metodu
	 * @return Mevcut kitabın yayıncısı
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * Parametre olarak gelen kitabın yayıncısını mevcut kitabın yayıncısına atamak için set metodu
	 * @param publisher Mevcut kitabın yayıncısına atanacak kitabın yayıncısı
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
}
