package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brs.Book;
import brs.RatingsDB;
import brs.User;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.event.ChangeEvent;
import java.awt.Button;
import javax.swing.ImageIcon;

/**
 * Kullanıcının seçili kitaba değerlendirme puanı vermesini sağlayan arayüz
 * sınıfı
 * 
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class AddBookToUser extends JFrame {
	/**
	 * İçerik paneli
	 */
	private JPanel contentPane;
	/**
	 * ISBN etiketi
	 */
	private JLabel isbnLabel;
	/**
	 * Başlık etiketi
	 */
	private JLabel titleLabel;
	/**
	 * Yazar etiketi
	 */
	private JLabel authorLabel;
	/**
	 * Yayın tarihi etiketi
	 */
	private JLabel publicationDateLabel;
	/**
	 * Yayınevi etiketi
	 */
	private JLabel publisherLabel;
	/**
	 * Değerlendirme puanı etiketi
	 */
	private JLabel ratingScoreLabel;
	/**
	 * Uygula düğmesi
	 */
	private Button applyButton;
	/**
	 * Yıldız etiketi 0
	 */
	private JLabel starLabel0;
	/**
	 * Yıldız etiketi 1
	 */
	private JLabel starLabel1;
	/**
	 * Yıldız etiketi 2
	 */
	private JLabel starLabel2;
	/**
	 * Yıldız etiketi 3
	 */
	private JLabel starLabel3;
	/**
	 * Yıldız etiketi 4
	 */
	private JLabel starLabel4;
	/**
	 * Yıldız etiketi 5
	 */
	private JLabel starLabel5;
	/**
	 * Yıldız etiketi 6
	 */
	private JLabel starLabel6;
	/**
	 * Yıldız etiketi 7
	 */
	private JLabel starLabel7;
	/**
	 * Yıldız etiketi 8
	 */
	private JLabel starLabel8;
	/**
	 * Yıldız etiketi 9
	 */
	private JLabel starLabel9;
	/**
	 * Yıldız etiketi 10
	 */
	private JLabel starLabel10;

	/**
	 * Değerlendirme yapacak kullanıcı
	 */
	private User user;
	/**
	 * Derecelendirme yapılacak kitap
	 */
	private Book book;

	/**
	 * Kitabın bilgilerini ve mevcut kullanıcıyı ayarlayan metot
	 * 
	 * @param book Derecelendirme yapılacak kitap
	 * @param user Derecelendirme yapacak kullanıcı
	 */
	public void setBooksInformations(Book book, User user) {
		this.user = user;
		this.book = book;
		// ISBN etiketine kitabın ISBN değerini atar
		isbnLabel.setText(book.getIsbn());
		// Başlık etiketine kitabın başlığını atar
		titleLabel.setText(book.getTitle());
		// Yazar etiketine kitabın yazarını atar
		authorLabel.setText(book.getAuthor());
		// Yayın tarihi etiketine kitabın yayın tarihini atar
		publicationDateLabel.setText(String.valueOf(book.getPublication_date()));
		// Yayınevi etiketine kitabın yayınevini atar
		publisherLabel.setText(book.getPublisher());
	}

	/**
	 * Proje başlatıldığında ilk çalışacak ana metot
	 * 
	 * @param args Komut satırı argümanları
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookToUser frame = new AddBookToUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sayfanın arayüzünü oluşturan ve kullanıcının arayüz üzerinden seçili kitaba
	 * puan vermesini sağlayan yapıcı metottur.
	 */
	public AddBookToUser() {
		// Pencere özellikleri ayarlandı
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 575);
		// İçerik paneli oluşturuldu ve düzenlemesi ayarlandı
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Sol panel oluşturuldu ve düzenlemesi ayarlandı
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 291, 575);
		contentPane.add(panel);
		panel.setLayout(null);

		// Etiketler oluşturuldu ve düzenlemesi ayarlandı

		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 76, 259, 49);
		panel.add(lblNewLabel);

		JLabel lblTtle = new JLabel("TITLE");
		lblTtle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTtle.setForeground(Color.WHITE);
		lblTtle.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblTtle.setBounds(10, 135, 259, 49);
		panel.add(lblTtle);

		JLabel lblAuthor = new JLabel("AUTHOR");
		lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthor.setForeground(Color.WHITE);
		lblAuthor.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblAuthor.setBounds(10, 194, 259, 49);
		panel.add(lblAuthor);

		JLabel lblPublcatonDate = new JLabel("PUBLICATION DATE");
		lblPublcatonDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPublcatonDate.setForeground(Color.WHITE);
		lblPublcatonDate.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblPublcatonDate.setBounds(10, 253, 259, 49);
		panel.add(lblPublcatonDate);

		JLabel lblPublsher = new JLabel("PUBLISHER");
		lblPublsher.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPublsher.setForeground(Color.WHITE);
		lblPublsher.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblPublsher.setBounds(10, 312, 259, 49);
		panel.add(lblPublsher);

		JLabel lblRateTheBook = new JLabel("RATE THE BOOK");
		lblRateTheBook.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRateTheBook.setForeground(Color.WHITE);
		lblRateTheBook.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblRateTheBook.setBounds(10, 371, 259, 49);
		panel.add(lblRateTheBook);

		isbnLabel = new JLabel("");
		isbnLabel.setHorizontalAlignment(SwingConstants.LEFT);
		isbnLabel.setForeground(Color.DARK_GRAY);
		isbnLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		isbnLabel.setBounds(301, 77, 530, 49);
		contentPane.add(isbnLabel);

		titleLabel = new JLabel("");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setForeground(Color.DARK_GRAY);
		titleLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		titleLabel.setBounds(301, 136, 530, 49);
		contentPane.add(titleLabel);

		authorLabel = new JLabel("");
		authorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		authorLabel.setForeground(Color.DARK_GRAY);
		authorLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		authorLabel.setBounds(301, 195, 530, 49);
		contentPane.add(authorLabel);

		publicationDateLabel = new JLabel("");
		publicationDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		publicationDateLabel.setForeground(Color.DARK_GRAY);
		publicationDateLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		publicationDateLabel.setBounds(301, 254, 530, 49);
		contentPane.add(publicationDateLabel);

		publisherLabel = new JLabel("");
		publisherLabel.setHorizontalAlignment(SwingConstants.LEFT);
		publisherLabel.setForeground(Color.DARK_GRAY);
		publisherLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		publisherLabel.setBounds(301, 313, 530, 49);
		contentPane.add(publisherLabel);

		// Çıkış etiketi oluşturuldu ve düzenlemesi ayarlandı
		JLabel exitLabel = new JLabel("X");
		exitLabel.addMouseListener(new MouseAdapter() {
			// Çıkış etiketine basıldığında çıkış yapar
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		exitLabel.setForeground(Color.GRAY);
		exitLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		exitLabel.setBounds(813, 0, 64, 46);
		contentPane.add(exitLabel);

		// Değerlendirme puanının gösterildiği etiket ayarlandı
		ratingScoreLabel = new JLabel("0");
		ratingScoreLabel.setForeground(Color.DARK_GRAY);
		ratingScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ratingScoreLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 29));
		ratingScoreLabel.setBounds(386, 436, 64, 49);
		contentPane.add(ratingScoreLabel);

		// JOptionPane görünümü düzenlendi
		UIManager.put("OptionPane.background", Color.DARK_GRAY);
		UIManager.put("OptionPane.messageForeground", Color.GRAY);
		UIManager.put("OptionPane.foreground", Color.WHITE);

		// Uygula düğmesi oluşturuldu ve düzenlemesi ayarlandı
		applyButton = new Button("Apply");
		applyButton.addMouseListener(new MouseAdapter() {
			// Butonun mouse ile üzerine gelindiğinde rengi değiştirildi
			@Override
			public void mouseEntered(MouseEvent e) {
				applyButton.setBackground(new Color(76, 145, 192));
			}

			// Butonun üzerindeki mouse çıkarıldığında rengi normale döner
			@Override
			public void mouseExited(MouseEvent e) {
				applyButton.setBackground(new Color(0, 128, 192));
			}

			// Butona tıklanıldığında kitap ve değerlendirme puanı kullanıcının listesine
			// eklenir ve kitabın başarıyla eklendiği konusunda bir bilgilendirme penceresi
			// çıkarır.
			@Override
			public void mouseClicked(MouseEvent e) {
				RatingsDB rdb = new RatingsDB();
				JOptionPane.showMessageDialog(null, "Book added successfully", "", JOptionPane.INFORMATION_MESSAGE);
				rdb.addRatingToDatabase(user, book, Integer.parseInt(ratingScoreLabel.getText()));
				dispose();

			}
		});
		applyButton.setForeground(Color.WHITE);
		applyButton.setBackground(new Color(0, 128, 192));
		applyButton.setBounds(619, 482, 192, 46);
		contentPane.add(applyButton);

		// Puanlama sistemini içeren panel oluşturuldu
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(301, 372, 553, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 0 puan etiketi oluşturuldu
		starLabel0 = new JLabel("");
		starLabel0.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-filled-star-48.png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanını belirtmiyorsa tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) != 0) {
					starLabel0.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				}
			}

			// Siyah yıldız ikonuna tıklanıldığında derecelendirme puanını 0 olarak ayarlar
			// ve diğer tüm yıldızları içi boş yıldız haline getirir.
			@Override
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("0");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-filled-star-48.png")));
				starLabel1.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel2.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel3.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel4.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel5.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel6.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel7.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel8.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel9.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
			}
		});
		starLabel0.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-filled-star-48.png")));
		starLabel0.setBounds(0, 0, 50, 50);
		panel_1.add(starLabel0);

		// 1 puan etiketi oluşturuldu
		starLabel1 = new JLabel("");
		starLabel1.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel1
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanından büyükse tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) < 1) {
					starLabel1.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				}
			}

			// Sarı yıldız ikonuna tıklanıldığında derecelendirme puanını 1 olarak ayarlar,
			// siyah yıldızı ve kendinden büyük değer taşıyan diğer tüm yıldızları içi boş
			// yıldız haline getirir.
			@Override
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("1");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				starLabel1
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
				starLabel2.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel3.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel4.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel5.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel6.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel7.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel8.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel9.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
			}
		});
		starLabel1.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
		starLabel1.setBounds(50, 0, 50, 50);
		panel_1.add(starLabel1);

		// 2 puan etiketi oluşturuldu
		starLabel2 = new JLabel("");
		starLabel2.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel1.dispatchEvent(e);
				starLabel2
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanından büyükse tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) < 2) {
					starLabel1.dispatchEvent(e);
					starLabel2.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				}
			}

			// Sarı yıldız ikonuna tıklanıldığında derecelendirme puanını 2 olarak ayarlar,
			// siyah yıldızı ve kendinden büyük değer taşıyan diğer tüm yıldızları içi boş
			// yıldız haline getirir.
			@Override
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("2");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				starLabel2
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
				starLabel3.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel4.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel5.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel6.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel7.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel8.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel9.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
			}
		});
		starLabel2.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
		starLabel2.setBounds(100, 0, 50, 50);
		panel_1.add(starLabel2);

		starLabel3 = new JLabel("");
		starLabel3.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel2.dispatchEvent(e);
				starLabel3
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanından büyükse tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) < 3) {
					starLabel2.dispatchEvent(e);
					starLabel3.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				}
			}

			// Sarı yıldız ikonuna tıklanıldığında derecelendirme puanını 3 olarak ayarlar,
			// siyah yıldızı ve kendinden büyük değer taşıyan diğer tüm yıldızları içi boş
			// yıldız haline getirir.
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("3");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				starLabel3
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
				starLabel4.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel5.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel6.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel7.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel8.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel9.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
			}
		});
		starLabel3.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
		starLabel3.setBounds(150, 0, 50, 50);
		panel_1.add(starLabel3);

		starLabel4 = new JLabel("");
		starLabel4.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel3.dispatchEvent(e);
				starLabel4
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanından büyükse tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) < 4) {
					starLabel3.dispatchEvent(e);
					starLabel4.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				}
			}

			// Sarı yıldız ikonuna tıklanıldığında derecelendirme puanını 4 olarak ayarlar,
			// siyah yıldızı ve kendinden büyük değer taşıyan diğer tüm yıldızları içi boş
			// yıldız haline getirir.
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("4");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				starLabel4
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
				starLabel5.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel6.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel7.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel8.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel9.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
			}
		});
		starLabel4.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
		starLabel4.setBounds(200, 0, 50, 50);
		panel_1.add(starLabel4);

		starLabel5 = new JLabel("");
		starLabel5.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel4.dispatchEvent(e);
				starLabel5
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanından büyükse tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) < 5) {
					starLabel4.dispatchEvent(e);
					starLabel5.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				}
			}

			// Sarı yıldız ikonuna tıklanıldığında derecelendirme puanını 5 olarak ayarlar,
			// siyah yıldızı ve kendinden büyük değer taşıyan diğer tüm yıldızları içi boş
			// yıldız haline getirir.
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("5");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				starLabel5
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
				starLabel6.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel7.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel8.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel9.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
			}
		});
		starLabel5.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
		starLabel5.setBounds(250, 0, 50, 50);
		panel_1.add(starLabel5);

		starLabel6 = new JLabel("");
		starLabel6.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel5.dispatchEvent(e);
				starLabel6
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanından büyükse tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) < 6) {
					starLabel5.dispatchEvent(e);
					starLabel6.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				}
			}

			// Sarı yıldız ikonuna tıklanıldığında derecelendirme puanını 6 olarak ayarlar,
			// siyah yıldızı ve kendinden büyük değer taşıyan diğer tüm yıldızları içi boş
			// yıldız haline getirir.
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("6");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				starLabel6
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
				starLabel7.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel8.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel9.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
			}
		});
		starLabel6.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
		starLabel6.setBounds(300, 0, 50, 50);
		panel_1.add(starLabel6);

		starLabel7 = new JLabel("");
		starLabel7.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel6.dispatchEvent(e);
				starLabel7
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanından büyükse tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) < 7) {
					starLabel6.dispatchEvent(e);
					starLabel7.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				}
			}

			// Sarı yıldız ikonuna tıklanıldığında derecelendirme puanını 7 olarak ayarlar,
			// siyah yıldızı ve kendinden büyük değer taşıyan diğer tüm yıldızları içi boş
			// yıldız haline getirir.
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("7");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				starLabel7
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
				starLabel8.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel9.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
			}
		});
		starLabel7.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
		starLabel7.setBounds(350, 0, 50, 50);
		panel_1.add(starLabel7);

		starLabel8 = new JLabel("");
		starLabel8.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel7.dispatchEvent(e);
				starLabel8
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanından büyükse tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) < 8) {
					starLabel7.dispatchEvent(e);
					starLabel8.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				}
			}

			// Sarı yıldız ikonuna tıklanıldığında derecelendirme puanını 8 olarak ayarlar,
			// siyah yıldızı ve kendinden büyük değer taşıyan diğer tüm yıldızları içi boş
			// yıldız haline getirir.
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("8");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				starLabel8
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
				starLabel9.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
			}
		});
		starLabel8.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
		starLabel8.setBounds(400, 0, 50, 50);
		panel_1.add(starLabel8);

		starLabel9 = new JLabel("");
		starLabel9.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel8.dispatchEvent(e);
				starLabel9
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanından büyükse tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) < 9) {
					starLabel8.dispatchEvent(e);
					starLabel9.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				}
			}

			// Sarı yıldız ikonuna tıklanıldığında derecelendirme puanını 9 olarak ayarlar,
			// siyah yıldızı ve kendinden büyük değer taşıyan diğer tüm yıldızları içi boş
			// yıldız haline getirir.
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("9");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				starLabel9
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
				starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
			}
		});
		starLabel9.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
		starLabel9.setBounds(450, 0, 50, 50);
		panel_1.add(starLabel9);

		starLabel10 = new JLabel("");
		starLabel10.addMouseListener(new MouseAdapter() {
			// İçi boş yıldız ikonunun mouse ile üzerine gelindiğinde içi dolu yıldız
			// ikonunu gösterir.
			@Override
			public void mouseEntered(MouseEvent e) {
				starLabel9.dispatchEvent(e);
				starLabel10
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}

			// İkonun üzerindeki mouse çıkarıldığında eğer değerlendirme puanı mevcut
			// yıldızın puanından büyükse tekrar içi boş yıldız gösterilir.
			@Override
			public void mouseExited(MouseEvent e) {
				if (Integer.parseInt(ratingScoreLabel.getText()) < 10) {
					starLabel9.dispatchEvent(e);
					starLabel10.setIcon(
							new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
				}
			}

			// Sarı yıldız ikonuna tıklanıldığında derecelendirme puanını 10 olarak ayarlar,
			// siyah yıldızı ve kendinden büyük değer taşıyan diğer tüm yıldızları içi boş
			// yıldız haline getirir.
			public void mouseClicked(MouseEvent e) {
				ratingScoreLabel.setText("10");
				starLabel0.setIcon(
						new ImageIcon(AddBookToUser.class.getResource("/images/icons8-black-blank-star-48.png")));
				starLabel10
						.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-filled-star-48 .png")));
			}
		});
		starLabel10.setIcon(new ImageIcon(AddBookToUser.class.getResource("/images/icons8-blank-star-48.png")));
		starLabel10.setBounds(500, 0, 50, 50);
		panel_1.add(starLabel10);
	}
}
