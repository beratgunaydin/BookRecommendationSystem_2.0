package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import brs.Book;
import brs.LibraryDB;
import brs.User;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Button;

/**
 * Yöneticinin veritabanından arayüz üzerinden kitap sildiği sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class AdminDeleteBookFromDBPage extends JFrame {

	/**
	 * İçerik paneli
	 */
	private JPanel contentPane;
	/**
	 * Veritabanına kitap ekleme sayfasına yönlendirme paneli
	 */
	private JPanel addBookToDatabasePanel;
	/**
	 * Mevcut sayfa paneli
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
	 * Arama butonu
	 */
	private Button searchButton;
	/**
	 * Kitap Listesi
	 */
	private JList<String> booksJList;
	/**
	 * Kitap listesi için kaydırma paneli
	 */
	private JScrollPane booksListScrollPane;
	/**
	 * Seçili kitabı veritabanından silme butonu
	 */
	private Button deleteBookFromDBButton;
	/**
	 * Başarılı veya başarısız işlemlerin arayüzden gösterildiği etiket
	 */
	private JLabel infoLabel;
	
	/**
	 * Konum bilgisi
	 */
	Point location;
	/**
	 * Fare olaylarını takip etmek için kullanılan değişken
	 */
	MouseEvent pressed;
	/**
	 * Veritabanından kitap silecek olan yönetici
	 */
	private User user;
	/**
	 * Veritabanında kitap aratıldığı metin alanı
	 */
	private JTextField searchTextField;
	/**
	 * Aramanın tutulduğu static metin değişkeni
	 */
	private static String searchInput;
	/**
	 * Kütüphane veritabanına ulaşmak için nesne örneği
	 */
	LibraryDB ldb = new LibraryDB();
	/**
	 * Kitap listesinin arayüzde gösterilmesi için DefaultListModel
	 */
	private DefaultListModel<String> dlm;
	
	/**
	 * Giriş yapan yöneticinin bilgilerini mevcut sayfaya getiren metottur.
	 * @param user Mevcut yönetici
	 */
	public void setAdmin(User user) {
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
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sayfanın arayüzünü oluşturan ve yöneticinin arayüz üzerinden veritabanından kitap silmesini sağlayan yapıcı metottur.
	 */
	public AdminDeleteBookFromDBPage() {
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
		addBookToDatabasePanel.addMouseListener(new MouseAdapter() {
			// Panelin üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				addBookToDatabasePanel.setBackground(new Color(100, 100, 100));
			}
			// Panelin üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				addBookToDatabasePanel.setBackground(new Color(128, 128, 128));
			}
			// Panele tıklanıldığında mevcut sayfa kapatılıp veritabanından kitap ekleme sayfasına yönlendirilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminAddBookToDBPage aabtdp = new AdminAddBookToDBPage();
				aabtdp.setUndecorated(true);
				aabtdp.setAdmin(user);
				aabtdp.setVisible(true);
			}
		});
		addBookToDatabasePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		addBookToDatabasePanel.setBackground(new Color(128, 128, 128));
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
		deleteBookFromDatabasePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		deleteBookFromDatabasePanel.setBackground(new Color(80, 80, 80));
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
			// Panele tıklanıldığında mevcut sayfa kapatılıp veritabanından kullanıcı silme sayfasına yönlendirilir.
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
		
		JLabel lblNewLabel_1_1 = new JLabel("DELETE BOOK FROM DATABASE");
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
		
		// Silinecek kitabın adının girildiği metin giriş alanı
		searchTextField = new JTextField();
		searchTextField.setForeground(new Color(70, 70, 70));
		searchTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchTextField.setColumns(10);
		searchTextField.setBackground(Color.LIGHT_GRAY);
		searchTextField.setBounds(351, 54, 494, 36);
		contentPane.add(searchTextField);
		
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
		searchButton.setForeground(Color.WHITE);
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchButton.setBackground(Color.DARK_GRAY);
		searchButton.setBounds(882, 54, 104, 36);
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
		
		// Veritabanından kitabın silinmesini sağlayan buton
		deleteBookFromDBButton = new Button("Delete Selected Book From Database");
		deleteBookFromDBButton.addMouseListener(new MouseAdapter() {
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteBookFromDBButton.setBackground(new Color(76, 145, 192));
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				deleteBookFromDBButton.setBackground(new Color(0, 128, 192));
			}
			// Seçilen kitap, tıklanıldığında veritabanında aranır ve veritabanından silinir. Kitap seçilmemişse uyarı mesajı, seçilmişse silinme mesajı gösterilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!booksJList.isSelectionEmpty()) {
					String item = booksJList.getSelectedValue();
					boolean isBookDeletedSuccesfully = ldb.deleteBookAndBooksRatingsFromDatabase(item);
					
					if (isBookDeletedSuccesfully) {
						infoLabel.setText("The book was successfully deleted. You are redirected to the home page.");
						infoLabel.setForeground(new Color(60, 207, 60));
						infoLabel.setVisible(true);
						
						Thread thread = new Thread() {
		                    @Override
		                    public void run() {
		                        try {
		                            Thread.sleep(2000);
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
					else {
						infoLabel.setText("Book deletion failed. Please try again later.");
						infoLabel.setForeground(new Color(255, 0, 0));
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
					infoLabel.setText("Please select the book you want to delete from the database.");
					infoLabel.setForeground(new Color(255, 0, 0));
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
		deleteBookFromDBButton.setForeground(Color.WHITE);
		deleteBookFromDBButton.setBackground(new Color(0, 128, 192));
		deleteBookFromDBButton.setBounds(723, 468, 305, 46);
		contentPane.add(deleteBookFromDBButton);
		
		// Hata veya başarı mesajının gösterildiği etiket
		infoLabel = new JLabel("");
		infoLabel.setForeground(new Color(60, 207, 60));
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		infoLabel.setBounds(380, 422, 627, 27);
		contentPane.add(infoLabel);
	}
	
	/**
	 * Parametre olarak gönderilen listenin DefaultListModel'e aktarıldığı metottur.
	 * @param defaultListModel Arayüzde gösterilmek istenen kitap listesi
	 */
	public void createBooksList(DefaultListModel<String> defaultListModel) {
		booksJList.setModel(defaultListModel);
	}
}
