package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import brs.Book;
import brs.DatabaseConnection;
import brs.Library;
import brs.LibraryDB;
import brs.User;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.Button;

/**
 * Kullanıcı arayüzü tüm kitap listesi ve kullanıcı listesine kitap ekleme içeren sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class BooksPage extends JFrame {
	/**
	 * İçerik paneli
	 */
	private JPanel contentPane;
	/**
	 * Mevcut kullanıcının kitap listesi sayfasına yönlendirme paneli
	 */
	private JPanel myBookListPanel;
	/**
	 * Mevcut panel
	 */
	private JPanel BooksPanel;
	/**
	 * Ayarlar sayfasına yönlendirme paneli
	 */
	private JPanel SettingsPanel;
	/**
	 * Mevcut kullanıcıya verilen kitap öneri listesi sayfasına yönlendirme paneli
	 */
	private JPanel RecommendPanel;
	/**
	 * Mevcut kullanıcı hesabından çıkış paneli
	 */
	private JPanel LogOutPanel;
	/**
	 * Kitap Listesi
	 */
	private JList<String> booksJList;
	/**
	 * Kitap listesi için kaydırma paneli
	 */
	private JScrollPane booksListScrollPane;
	/**
	 * Veritabanında kitap aratıldığı metin alanı
	 */
	private JTextField searchTextField;
	/**
	 * Arama butonu
	 */
	private Button searchButton;
	/**
	 * Seçili kitabın veritabanından bilgilerini görüntüleme sayfasına yönlendirme butonu
	 */
	private Button showSelectedBooksInformationButton;
	/**
	 * Seçili kitabı değerlendirme ve kullanıcının kitap listesine ekleme sayfasına yönlendirme butonu
	 */
	private Button addSelectedBookButton;
	
	/**
	 * Konum bilgisi
	 */
	private Point location;
	/**
	 * Fare olaylarını takip etmek için kullanılan değişken
	 */
	private MouseEvent pressed;
	/**
	 * Giriş yapan kullanıcı
	 */
	private User user;
	/**
	 * Kitap listesinin arayüzde gösterilmesi için DefaultListModel
	 */
	private DefaultListModel<String> dlm;
	/**
	 * Aramanın tutulduğu static metin değişkeni
	 */
	private static String searchInput;
	/**
	 * Kütüphane veritabanına ulaşmak için nesne örneği
	 */
	LibraryDB ldb = new LibraryDB();
	/**
	 * Başarılı veya başarısız işlemlerin arayüzden gösterildiği etiket
	 */
	private JLabel infoLabel;
	
	/**
	 * Giriş yapan kullanıcının bilgilerini mevcut sayfaya getiren metottur.
	 * @param user Mevcut kullanıcı
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Proje başlatıldığında ilk çalışacak ana metot
	 * @param args Komut satırı argümanları
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BooksPage frame = new BooksPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sayfanın arayüzünü oluşturan ve kullanıcının arayüz üzerinde listesine kitap ekleme, kitap bilgilerini görüntüleme ve tüm kitapları görüntüleme işlemlerini içeren yapıcı metottur.
	 */
	public BooksPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1064, 572);
		contentPane = new JPanel();
		this.addMouseListener(new MouseAdapter() {
			// Pencereye tıklandığında tıklanılan noktanın konumunu değişkene kaydeder.
			@Override
			public void mousePressed(MouseEvent e) {
				pressed = e;
			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			// Pencere mouse ile sürüklendiğinde mouse un konumu ve pencerenin konumu üzerinden pencerenin sürüklenebilmesini sağlar.
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
		
		// Menü paneli
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(Color.DARK_GRAY);
		menuPanel.setBounds(0, 0, 330, 613);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		// Menü ikonu
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HomePage.class.getResource("/images/icons8-book-100.png")));
		lblNewLabel.setBounds(111, 70, 121, 154);
		menuPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BOOK RECOMMENDATION SYSTEM");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 35, 310, 60);
		menuPanel.add(lblNewLabel_1);
		
		// Kullanıcının listesindeki kitaplar paneli
		myBookListPanel = new JPanel();
		myBookListPanel.addMouseListener(new MouseAdapter() {
			// Panelin üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				myBookListPanel.setBackground(new Color(100, 100, 100));
			}
			// Panelin üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				myBookListPanel.setBackground(new Color(128, 128, 128));
			}
			// Panele tıklanıldığında mevcut sayfa kapatılıp kullanıcının listesindeki kitaplar sayfasına yönlendirilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				MyBookListPage mblp = new MyBookListPage();
				mblp.setUndecorated(true);
				mblp.setUser(user);
				mblp.setVisible(true);
			}
		});
		myBookListPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		myBookListPanel.setBackground(new Color(128, 128, 128));
		myBookListPanel.setBounds(0, 220, 330, 50);
		menuPanel.add(myBookListPanel);
		myBookListPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("MY BOOK LIST");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(85, 10, 245, 30);
		myBookListPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(HomePage.class.getResource("/images/icons8-user-50.png")));
		lblNewLabel_3.setBounds(10, 0, 53, 50);
		myBookListPanel.add(lblNewLabel_3);
		
		// Tüm kitaplar ve kullanıcının listesine kitap ekleme paneli
		BooksPanel = new JPanel();
		BooksPanel.setBackground(new Color(80, 80, 80));
		BooksPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		BooksPanel.setBounds(0, 270, 330, 50);
		menuPanel.add(BooksPanel);
		BooksPanel.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("BOOKS");
		lblNewLabel_2_1.setBounds(85, 10, 245, 30);
		BooksPanel.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(HomePage.class.getResource("/images/icons8-open-book-50.png")));
		lblNewLabel_3_1.setBounds(10, 0, 53, 50);
		BooksPanel.add(lblNewLabel_3_1);
		
		// Kitap önerileri listesi paneli
		RecommendPanel = new JPanel();
		RecommendPanel.addMouseListener(new MouseAdapter() {
			// Panelin üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				RecommendPanel.setBackground(new Color(100, 100, 100));
			}
			// Panelin üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				RecommendPanel.setBackground(new Color(128, 128, 128));
			}
			// Panele tıklanıldığında mevcut sayfa kapatılıp kitap önerileri listesi sayfasına yönlendirilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				RecommendBookPage rp = new RecommendBookPage();
				rp.setUndecorated(true);
				rp.setUser(user);
				rp.setVisible(true);
			}
		});
		RecommendPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		RecommendPanel.setBackground(Color.GRAY);
		RecommendPanel.setBounds(0, 320, 330, 50);
		menuPanel.add(RecommendPanel);
		RecommendPanel.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("RECOMMEND BOOK");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		lblNewLabel_2_2.setBounds(85, 10, 245, 30);
		RecommendPanel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.setIcon(new ImageIcon(HomePage.class.getResource("/images/icons8-working-with-a-computer-50.png")));
		lblNewLabel_3_2.setBounds(10, 0, 53, 40);
		RecommendPanel.add(lblNewLabel_3_2);
		
		// Ayarlar paneli
		SettingsPanel = new JPanel();
		SettingsPanel.addMouseListener(new MouseAdapter() {
			// Panelin üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				SettingsPanel.setBackground(new Color(100, 100, 100));
			}
			// Panelin üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				SettingsPanel.setBackground(new Color(128, 128, 128));
			}
			// Panele tıklanıldığında mevcut sayfa kapatılıp ayarlar sayfasına yönlendirilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				SettingsPage sp = new SettingsPage();
				sp.setUndecorated(true);
				sp.setUser(user);
				sp.setVisible(true);
			}
		});
		SettingsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		SettingsPanel.setBackground(Color.GRAY);
		SettingsPanel.setBounds(0, 370, 330, 50);
		menuPanel.add(SettingsPanel);
		SettingsPanel.setLayout(null);
		
		JLabel lblNewLabel_2_3 = new JLabel("SETTINGS");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		lblNewLabel_2_3.setBounds(85, 10, 245, 30);
		SettingsPanel.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_3_3 = new JLabel("");
		lblNewLabel_3_3.setIcon(new ImageIcon(HomePage.class.getResource("/images/icons8-services-50.png")));
		lblNewLabel_3_3.setBounds(10, 0, 53, 50);
		SettingsPanel.add(lblNewLabel_3_3);
		
		// Kullanıcı hesabından çıkış paneli
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
		LogOutPanel.setBounds(0, 420, 330, 50);
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
		
		JLabel lblNewLabel_1_1 = new JLabel("BOOKS");
		lblNewLabel_1_1.setBounds(10, 480, 310, 46);
		menuPanel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
		
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
		
		// Hata veya başarı mesajının gösterildiği etiket
		infoLabel = new JLabel("");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setForeground(new Color(255, 0, 0));
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		infoLabel.setBounds(380, 422, 620, 27);
		contentPane.add(infoLabel);
		infoLabel.setVisible(false);
		
		// Aranan kitabın adının girildiği metin giriş alanı
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchTextField.setForeground(new Color(70, 70, 70));
		searchTextField.setBackground(new Color(192, 192, 192));
		searchTextField.setBounds(380, 57, 494, 36);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		// Veritabanında girilen kitap adını arayacak buton
		searchButton = new Button("Search");
		searchButton.addMouseListener(new MouseAdapter() {
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				searchButton.setBackground(Color.GRAY);
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				searchButton.setBackground(Color.DARK_GRAY);
			}
			// Girilen metin, tıklanıldığında veritabanında aranır ve uyuşan kitap adları liste şeklinde arayüzde gösterilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				searchInput = searchTextField.getText();
				dlm = ldb.searchBookInDatabase(searchInput);
				createBooksList(dlm);
			}
		});
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchButton.setForeground(new Color(255, 255, 255));
		searchButton.setBackground(Color.DARK_GRAY);
		searchButton.setBounds(896, 57, 104, 36);
		contentPane.add(searchButton);
		
		// Tüm kitaplar veya aranan kitapların gösterildiği liste
		dlm = ldb.searchBookInDatabase(searchInput);
		booksJList = new JList<String>(dlm);
		booksJList.setForeground(new Color(70, 70, 70));
		booksJList.setBackground(new Color(192, 192, 192));
		booksJList.setBounds(380, 105, 626, 307);
		booksListScrollPane = new JScrollPane();
		booksListScrollPane.setBounds(380, 105, 627, 307);
		contentPane.add(booksListScrollPane);
		booksListScrollPane.setViewportView(booksJList);
		
		// Veritabanından kitabın bilgilerinin getirilmesini sağlayan buton
		showSelectedBooksInformationButton = new Button("Show Information Of The Selected Book");
		showSelectedBooksInformationButton.addMouseListener(new MouseAdapter() {
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				showSelectedBooksInformationButton.setBackground(new Color(76, 145, 192));
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				showSelectedBooksInformationButton.setBackground(new Color(0, 128, 192));
			}
			// Seçilen kitap, tıklanıldığında veritabanında aranır ve seçili kitabın bilgilerinin görüntülendiği sayfa açılır. Kitap seçilmemişse uyarı mesajı, seçilmişse silinme mesajı gösterilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!booksJList.isSelectionEmpty()) {
					String item = booksJList.getSelectedValue();
					Book book = ldb.findPropertiesOfTheSelectedBook(item);
					
					SelectedBooksInformations sbi = new SelectedBooksInformations();
					sbi.setBooksInformations(book);
					sbi.setUndecorated(true);
					sbi.setVisible(true);
				}
				else {
					infoLabel.setText("Please select the book whose information you would like to view.");
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
				}
			}
		});
		showSelectedBooksInformationButton.setForeground(Color.WHITE);
		showSelectedBooksInformationButton.setBackground(new Color(0, 128, 192));
		showSelectedBooksInformationButton.setBounds(360, 468, 305, 46);
		contentPane.add(showSelectedBooksInformationButton);
		
		// Kullanıcının listesine seçili kitabın eklenmesini sağlayan buton
		addSelectedBookButton = new Button("Rate and Add Selected Book To My Book List");
		addSelectedBookButton.addMouseListener(new MouseAdapter() {
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				addSelectedBookButton.setBackground(new Color(76, 145, 192));
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				addSelectedBookButton.setBackground(new Color(0, 128, 192));
			}
			// Seçilen kitap, tıklanıldığında veritabanında aranır ve değerlendirme sayfasına yönlendirilir. Kitap seçilmemişse veya kitap kullanıcının listesinde mevcutsa uyarı mesajı, seçilmişse silinme mesajı gösterilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!booksJList.isSelectionEmpty()) {
					String item = booksJList.getSelectedValue();
					Book book = ldb.findPropertiesOfTheSelectedBook(item);
					
					boolean doesUserHaveBook = ldb.checkUserHasTheBook(book, user);
					
					if (!doesUserHaveBook) {
						AddBookToUser abtu = new AddBookToUser();
						abtu.setBooksInformations(book, user);
						abtu.setUndecorated(true);
						abtu.setVisible(true);
					}
					
					else {
						infoLabel.setText("This book is available on your list. Please select another book.");
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
					}
				}
				else {
					infoLabel.setText("Please select the book you want to add to your list.");
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
				}
			}
		});
		addSelectedBookButton.setForeground(Color.WHITE);
		addSelectedBookButton.setBackground(new Color(0, 128, 192));
		addSelectedBookButton.setBounds(695, 468, 305, 46);
		contentPane.add(addSelectedBookButton);
	}
	
	/**
	 * Parametre olarak gönderilen listenin DefaultListModel'e aktarıldığı metottur.
	 * @param defaultListModel Arayüzde gösterilmek istenen kitap listesi
	 */
	public void createBooksList(DefaultListModel<String> defaultListModel) {
		booksJList.setModel(defaultListModel);
	}
}