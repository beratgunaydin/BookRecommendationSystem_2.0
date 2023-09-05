package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import brs.LibraryDB;
import brs.User;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Button;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Yöneticinin veritabanına arayüz üzerinden kitap eklediği sınıf
 * 
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class AdminAddBookToDBPage extends JFrame {
	/**
	 * İçerik paneli
	 */
	private JPanel contentPane;
	/**
	 * Mevcut sayfa paneli
	 */
	private JPanel addBookToDatabasePanel;
	/**
	 * Veritabanından kitap silme sayfasına yönlendirme paneli
	 */
	private JPanel deleteBookFromDatabasePanel;
	/**
	 * Veritabanından kullanıcı silme sayfasına yönlendirme paneli
	 */
	private JPanel DeleteUserFromDatabasePanel;
	/**
	 * Yönetici hesabından çıkış paneli
	 */
	private JPanel LogOutPanel;
	/**
	 * Veritabanına kitap ekleme butonu
	 */
	private Button addBookToDBButton;
	/**
	 * Girilen ISBN numarasının sistemde kayıtlı olup olmadığı hakkında bilgi veren
	 * etiket
	 */
	private JLabel isbnControlLabel;
	/**
	 * Konum bilgisi
	 */
	Point location;
	/**
	 * Fare olaylarını takip etmek için kullanılan değişken
	 */
	MouseEvent pressed;
	/**
	 * Veritabanına kitap ekleyecek olan yönetici
	 */
	private User user;
	/**
	 * ISBN metin giriş alanı
	 */
	private JTextField isbnTextField;
	/**
	 * Kitap başlık metin giriş alanı
	 */
	private JTextField titleTextField;
	/**
	 * Yazar metin giriş alanı
	 */
	private JTextField authorTextField;
	/**
	 * Yayın tarihi metin giriş alanı
	 */
	private JTextField publicationDateTextField;
	/**
	 * Yayınevi metin giriş alanı
	 */
	private JTextField publisherTextField;
	/**
	 * Bilgi etiketi
	 */
	private JLabel infoLabel;

	/**
	 * ISBN değeri
	 */
	private String isbn;
	/**
	 * Kitap adı
	 */
	private String title;
	/**
	 * Yazar
	 */
	private String author;
	/**
	 * Yayın tarihi
	 */
	private int publication_date;
	/**
	 * Yayınevi
	 */
	private String publisher;

	/**
	 * Giriş yapan yöneticinin bilgilerini mevcut sayfaya getiren metottur.
	 * 
	 * @param user Mevcut yönetici
	 */
	public void setAdmin(User user) {
		this.user = user;
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
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sayfanın arayüzünü oluşturan ve yöneticinin arayüz üzerinden veritabanına
	 * kitap eklemesini sağlayan yapıcı metottur.
	 */
	public AdminAddBookToDBPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1064, 572);
		contentPane = new JPanel();
		// Pencereye tıklandığında tıklanılan noktanın konumunu değişkene kaydeder.
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pressed = e;
			}
		});

		// Pencere mouse ile sürüklendiğinde mouse un konumu ve pencerenin konumu
		// üzerinden pencerenin sürüklenebilmesini sağlar.
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Component component = e.getComponent();
				location = component.getLocation(location);
				int x = location.x - pressed.getX() + e.getX();
				int y = location.y - pressed.getY() + e.getY();
				component.setLocation(x, y);
			}
		});
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Menüyü içeren panel
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(Color.DARK_GRAY);
		menuPanel.setBounds(0, 0, 330, 613);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		// Menü ikonu
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AdminPage.class.getResource("/images/icons8-administrator-100.png")));
		lblNewLabel.setBounds(111, 70, 121, 154);
		menuPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("BOOK RECOMMENDATION SYSTEM");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 35, 310, 60);
		menuPanel.add(lblNewLabel_1);

		// Veritabanına kitap ekleme paneli
		addBookToDatabasePanel = new JPanel();
		addBookToDatabasePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		addBookToDatabasePanel.setBackground(new Color(80, 80, 80));
		addBookToDatabasePanel.setBounds(0, 220, 330, 50);
		menuPanel.add(addBookToDatabasePanel);
		addBookToDatabasePanel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("ADD BOOK TO DATABASE");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(85, 10, 245, 30);
		addBookToDatabasePanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(AdminPage.class.getResource("/images/icons8-add-properties-48.png")));
		lblNewLabel_3.setBounds(10, 0, 53, 50);
		addBookToDatabasePanel.add(lblNewLabel_3);

		// Veritabanından kitap silme paneli
		deleteBookFromDatabasePanel = new JPanel();
		deleteBookFromDatabasePanel.addMouseListener(new MouseAdapter() {
			// Panelin üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteBookFromDatabasePanel.setBackground(new Color(100, 100, 100));
			}

			// Panelin üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				deleteBookFromDatabasePanel.setBackground(new Color(128, 128, 128));
			}

			// Panele tıklanıldığında mevcut sayfa kapatılıp veritabanından kitap silme
			// sayfasına yönlendirilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminDeleteBookFromDBPage adbfdp = new AdminDeleteBookFromDBPage();
				adbfdp.setUndecorated(true);
				adbfdp.setAdmin(user);
				adbfdp.setVisible(true);
			}
		});
		deleteBookFromDatabasePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		deleteBookFromDatabasePanel.setBackground(Color.GRAY);
		deleteBookFromDatabasePanel.setBounds(0, 270, 330, 50);
		menuPanel.add(deleteBookFromDatabasePanel);
		deleteBookFromDatabasePanel.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("DELETE BOOK FROM DATABASE");
		lblNewLabel_2_1.setBounds(85, 10, 245, 30);
		deleteBookFromDatabasePanel.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));

		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(AdminPage.class.getResource("/images/icons8-delete-document-48.png")));
		lblNewLabel_3_1.setBounds(10, 0, 53, 50);
		deleteBookFromDatabasePanel.add(lblNewLabel_3_1);

		// Veritabanından kullanıcı silme paneli
		DeleteUserFromDatabasePanel = new JPanel();
		DeleteUserFromDatabasePanel.addMouseListener(new MouseAdapter() {
			// Panelin üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				DeleteUserFromDatabasePanel.setBackground(new Color(100, 100, 100));
			}

			// Panelin üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				DeleteUserFromDatabasePanel.setBackground(new Color(128, 128, 128));
			}

			// Panele tıklanıldığında mevcut sayfa kapatılıp veritabanından kullanıcı silme
			// sayfasına yönlendirilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminDeleteUserFromDBPage adufdp = new AdminDeleteUserFromDBPage();
				adufdp.setUndecorated(true);
				adufdp.setAdmin(user);
				adufdp.setVisible(true);
			}
		});
		DeleteUserFromDatabasePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		DeleteUserFromDatabasePanel.setBackground(Color.GRAY);
		DeleteUserFromDatabasePanel.setBounds(0, 320, 330, 50);
		menuPanel.add(DeleteUserFromDatabasePanel);
		DeleteUserFromDatabasePanel.setLayout(null);

		JLabel lblNewLabel_2_2 = new JLabel("DELETE USER FROM DATABASE");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(85, 10, 245, 30);
		DeleteUserFromDatabasePanel.add(lblNewLabel_2_2);

		JLabel lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.setIcon(new ImageIcon(AdminPage.class.getResource("/images/icons8-delete-user-male-48.png")));
		lblNewLabel_3_2.setBounds(10, 0, 53, 40);
		DeleteUserFromDatabasePanel.add(lblNewLabel_3_2);

		// Yönetici hesabından çıkış paneli
		LogOutPanel = new JPanel();
		LogOutPanel.addMouseListener(new MouseAdapter() {
			// Panelin üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				LogOutPanel.setBackground(new Color(100, 100, 100));
			}

			// Panelin üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				LogOutPanel.setBackground(new Color(128, 128, 128));
			}

			// Panele tıklanıldığında mevcut sayfa kapatılıp giriş sayfasına yönlendirilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				LoginPage lp = new LoginPage();
				lp.setUndecorated(true);
				lp.setVisible(true);
			}
		});
		LogOutPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		LogOutPanel.setBackground(Color.GRAY);
		LogOutPanel.setBounds(0, 370, 330, 50);
		menuPanel.add(LogOutPanel);
		LogOutPanel.setLayout(null);

		JLabel lblNewLabel_2_4 = new JLabel("LOG OUT");
		lblNewLabel_2_4.setForeground(Color.WHITE);
		lblNewLabel_2_4.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		lblNewLabel_2_4.setBounds(85, 10, 245, 30);
		LogOutPanel.add(lblNewLabel_2_4);

		JLabel lblNewLabel_3_4 = new JLabel("");
		lblNewLabel_3_4.setIcon(new ImageIcon(HomePage.class.getResource("/images/icons8-log-out-50.png")));
		lblNewLabel_3_4.setBounds(10, 0, 53, 50);
		LogOutPanel.add(lblNewLabel_3_4);

		JLabel lblNewLabel_1_1 = new JLabel("ADD BOOK TO DATABASE");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(10, 445, 310, 60);
		menuPanel.add(lblNewLabel_1_1);

		// Çıkış etiketi oluşturuldu ve düzenlemesi ayarlandı
		JLabel homePageCloseLabel = new JLabel("X");
		homePageCloseLabel.addMouseListener(new MouseAdapter() {
			// Çıkış etiketine basıldığında çıkış yapar
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		homePageCloseLabel.setForeground(Color.GRAY);
		homePageCloseLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		homePageCloseLabel.setBounds(1016, 0, 64, 46);
		contentPane.add(homePageCloseLabel);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setForeground(new Color(80, 80, 80));
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIsbn.setBounds(340, 16, 108, 27);
		contentPane.add(lblIsbn);

		// Kitap ISBN numarası girilecek metin alanı
		isbnTextField = new JTextField();
		isbnTextField.addKeyListener(new KeyAdapter() {
			// Bir tuşa basıldığında mevcut metnin veritabanında kayıtlı bir ISBN ile
			// eşleşip eşleşmediği kontrol edilir.
			@Override
			public void keyReleased(KeyEvent e) {
				LibraryDB ldb = new LibraryDB();
				String isbn = isbnTextField.getText();

				boolean isBookRegisteredInDatabase = ldb.checkISBNInDatabase(isbn);

				// Eşleşmiyorsa
				if (!isBookRegisteredInDatabase) {
					// Tik işareti olan ikon gösterildi.
					isbnControlLabel
							.setIcon(new ImageIcon(SignUpPage.class.getResource("/images/icons8-checkmark-48.png")));
				}
				// Eşleşiyorsa
				else {
					// Çarpı işareti olan ikon gösterildi.
					isbnControlLabel
							.setIcon(new ImageIcon(SignUpPage.class.getResource("/images/icons8-cancel-48.png")));
				}
			}
		});
		isbnTextField.setForeground(new Color(70, 70, 70));
		isbnTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		isbnTextField.setColumns(10);
		isbnTextField.setBackground(Color.LIGHT_GRAY);
		isbnTextField.setBounds(350, 47, 530, 40);
		contentPane.add(isbnTextField);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(80, 80, 80));
		separator.setBounds(340, 97, 687, 2);
		contentPane.add(separator);

		JLabel lblTtle = new JLabel("TITLE");
		lblTtle.setForeground(new Color(80, 80, 80));
		lblTtle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTtle.setBounds(340, 105, 108, 27);
		contentPane.add(lblTtle);

		// Kitap adı girilecek metin alanı
		titleTextField = new JTextField();
		titleTextField.setForeground(new Color(70, 70, 70));
		titleTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titleTextField.setColumns(10);
		titleTextField.setBackground(Color.LIGHT_GRAY);
		titleTextField.setBounds(350, 135, 530, 40);
		contentPane.add(titleTextField);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(80, 80, 80));
		separator_1.setBounds(340, 185, 687, 2);
		contentPane.add(separator_1);

		JLabel lblAuthor = new JLabel("AUTHOR");
		lblAuthor.setForeground(new Color(80, 80, 80));
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuthor.setBounds(340, 197, 108, 27);
		contentPane.add(lblAuthor);

		// Yazar girilecek metin alanı
		authorTextField = new JTextField();
		authorTextField.setForeground(new Color(70, 70, 70));
		authorTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		authorTextField.setColumns(10);
		authorTextField.setBackground(Color.LIGHT_GRAY);
		authorTextField.setBounds(350, 225, 530, 40);
		contentPane.add(authorTextField);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(new Color(80, 80, 80));
		separator_1_1.setBounds(340, 275, 687, 2);
		contentPane.add(separator_1_1);

		JLabel lblPublcatonDate = new JLabel("PUBLICATION DATE");
		lblPublcatonDate.setForeground(new Color(80, 80, 80));
		lblPublcatonDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPublcatonDate.setBounds(340, 287, 163, 27);
		contentPane.add(lblPublcatonDate);

		// Yayın tarihi girilecek metin alanı
		publicationDateTextField = new JTextField();
		publicationDateTextField.addFocusListener(new FocusAdapter() {
			// Metin alanı üzerindeki focus kaybedilirse metin alanını tekrar düzenlenebilir
			// yap.
			@Override
			public void focusLost(FocusEvent e) {
				publicationDateTextField.setEditable(true);
			}
		});
		publicationDateTextField.addKeyListener(new KeyAdapter() {
			// Herhangi bir tuşa basıldığında basılan tuşun sayı olup olmadığını kontrol
			// eder.
			@Override
			public void keyPressed(KeyEvent e) {
				String value = publicationDateTextField.getText();
				int l = value.length();
				// Sayı girişi yapılmışsa veya silme tuşuna basılmışsa
				if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
					// Metin alanını düzenlenebilir yap ve girilen sayıyı metin alanına ekle
					publicationDateTextField.setEditable(true);
				}
				// Sayı girişi yapılmamışsa veya silme tuşuna basılmamışsa
				else {
					// Metin alanını düzenlenemez yap ve girilen karakteri metin alanına ekleme
					publicationDateTextField.setEditable(false);
				}
			}
		});
		publicationDateTextField.setForeground(new Color(70, 70, 70));
		publicationDateTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		publicationDateTextField.setColumns(10);
		publicationDateTextField.setBackground(Color.LIGHT_GRAY);
		publicationDateTextField.setBounds(350, 319, 530, 40);
		contentPane.add(publicationDateTextField);

		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setForeground(new Color(80, 80, 80));
		separator_1_1_1.setBounds(340, 369, 687, 2);
		contentPane.add(separator_1_1_1);

		JLabel lblPublsher = new JLabel("PUBLISHER");
		lblPublsher.setForeground(new Color(80, 80, 80));
		lblPublsher.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPublsher.setBounds(340, 381, 163, 27);
		contentPane.add(lblPublsher);

		// Yayınevi girilecek metin alanı
		publisherTextField = new JTextField();
		publisherTextField.setForeground(new Color(70, 70, 70));
		publisherTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		publisherTextField.setColumns(10);
		publisherTextField.setBackground(Color.LIGHT_GRAY);
		publisherTextField.setBounds(350, 410, 530, 40);
		contentPane.add(publisherTextField);

		// Bilgileri girilen kitabı veritabanına ekleme butonu
		addBookToDBButton = new Button("Add Book To Database");
		addBookToDBButton.addMouseListener(new MouseAdapter() {
			// Butonun mouse ile üzerine gelindiğinde rengi değiştirildi
			@Override
			public void mouseEntered(MouseEvent e) {
				addBookToDBButton.setBackground(new Color(76, 145, 192));
			}

			// Butonun üzerindeki mouse çıkarıldığında rengi normale döner
			@Override
			public void mouseExited(MouseEvent e) {
				addBookToDBButton.setBackground(new Color(0, 128, 192));
			}

			// Butona tıklanıldığında hiçbir alan boş bırakılmamışsa, tarih alanı doğru
			// formatta girilmişse ve girilen ISBN veritabanında kayıtlı değilse bilgileri
			// girilen kitabı veritabanına ekler. Bu şartlardan herhangi birinin
			// karşılanmaması durumunda ekrana mesaj gösterilir ve veritabanına kayıt
			// eklenmez.
			@Override
			public void mouseClicked(MouseEvent e) {
				LibraryDB ldb = new LibraryDB();
				isbn = isbnTextField.getText();
				title = titleTextField.getText();
				author = authorTextField.getText();
				publication_date = 0;
				if (!publicationDateTextField.getText().isEmpty()) {
					publication_date = Integer.parseInt(publicationDateTextField.getText());
				}
				publisher = publisherTextField.getText();

				if (isbn.isEmpty() || title.isEmpty() || author.isEmpty() || publisher.isEmpty()
						|| publicationDateTextField.getText().isEmpty()) {
					infoLabel.setForeground(new Color(255, 0, 0));
					infoLabel.setText("Book information fields cannot be left blank.");
					infoLabel.setVisible(true);

					Thread thread = new Thread() {
						@Override
						public void run() {
							try {
								Thread.sleep(2000);
								infoLabel.setVisible(false);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					};
					thread.start();
				} else {
					System.out.println(publication_date);
					if (!((publication_date <= LocalDateTime.now().getYear()) && publication_date > 1000)) {
						infoLabel.setForeground(new Color(255, 0, 0));
						infoLabel.setText("Book publication date is not valid.");
						infoLabel.setVisible(true);

						Thread thread = new Thread() {
							@Override
							public void run() {
								try {
									Thread.sleep(2000);
									infoLabel.setVisible(false);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
						};
						thread.start();
					} else {
						if (ldb.checkISBNInDatabase(isbn)) {
							infoLabel.setForeground(new Color(255, 0, 0));
							infoLabel
									.setText("Isbn number is available in the system. Please try another isbn number.");
							infoLabel.setVisible(true);

							Thread thread = new Thread() {
								@Override
								public void run() {
									try {
										Thread.sleep(2000);
										infoLabel.setVisible(false);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							};
							thread.start();
						} else {
							infoLabel.setForeground(new Color(60, 207, 60));
							infoLabel.setText("Registration is successful. You are redirected to the homepage.");
							infoLabel.setVisible(true);

							Thread thread = new Thread() {
								@Override
								public void run() {
									try {
										Thread.sleep(2000);
										LibraryDB ld = new LibraryDB();
										ld.insertBookToDataBase(isbn, title, author, publication_date, publisher);
										dispose();
										AdminPage ap = new AdminPage();
										ap.setUndecorated(true);
										ap.setAdmin(user);
										ap.setVisible(true);
										infoLabel.setVisible(false);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							};
							thread.start();
						}
					}
				}
			}
		});
		addBookToDBButton.setForeground(Color.WHITE);
		addBookToDBButton.setBackground(new Color(0, 128, 192));
		addBookToDBButton.setBounds(816, 479, 224, 46);
		contentPane.add(addBookToDBButton);

		// ISBN numarasını kontrol eden etiket
		isbnControlLabel = new JLabel("");
		isbnControlLabel.setIcon(new ImageIcon(AdminAddBookToDBPage.class.getResource("/images/icons8-cancel-48.png")));
		isbnControlLabel.setBounds(889, 36, 56, 54);
		contentPane.add(isbnControlLabel);

		infoLabel = new JLabel("");
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		infoLabel.setBounds(343, 479, 449, 27);
		contentPane.add(infoLabel);
	}
}
