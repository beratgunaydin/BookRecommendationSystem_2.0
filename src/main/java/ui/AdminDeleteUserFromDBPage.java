package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import brs.User;
import brs.UsersDB;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Yöneticinin veritabanından arayüz üzerinden kullanıcı sildiği sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class AdminDeleteUserFromDBPage extends JFrame {

	/**
	 * İçerik paneli
	 */
	private JPanel contentPane;
	/**
	 * Veritabanına kitap ekleme sayfasına yönlendirme paneli
	 */
	private JPanel addBookToDatabasePanel;
	/**
	 * Veritabanından kitap silme sayfasına yönlendirme paneli
	 */
	private JPanel deleteBookFromDatabasePanel;
	/**
	 * Mevcut sayfa paneli
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
	 * Aranan kullanıcı bilgisini arayüzde gösteren etiket
	 */
	private JLabel userInfoLabel;
	/**
	 * Aranan kullanıcıyı veritabanından silme butonu
	 */
	private Button deleteUserFromDBButton;
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
	 * Veritabanından kullanıcı silecek olan yönetici
	 */
	private User user;
	/**
	 * Veritabanında kitap aratıldığı metin alanı
	 */
	private JTextField searchTextField;
	
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
	 * Sayfanın arayüzünü oluşturan ve yöneticinin arayüz üzerinden veritabanından kullanıcı silmesini sağlayan yapıcı metottur.
	 */
	public AdminDeleteUserFromDBPage() {
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
			// Panele tıklanıldığında mevcut sayfa kapatılıp veritabanından kitap silme sayfasına yönlendirilir.
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
		DeleteUserFromDatabasePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		DeleteUserFromDatabasePanel.setBackground(new Color(80, 80, 80));
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
		
		JLabel lblNewLabel_1_1 = new JLabel("DELETE USER FROM DATABASE");
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
		
		// Silinecek kullanıcının ID numarasının girildiği metin alanı
		searchTextField = new JTextField();
		searchTextField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				searchTextField.setEditable(true);
			}
		});
		searchTextField.addKeyListener(new KeyAdapter() {
			// Herhangi bir tuşa basıldığında basılan tuşun sayı olup olmadığını kontrol eder.
			@Override
			public void keyPressed(KeyEvent e) {
				String value = searchTextField.getText();
				int l = value.length();
				// Sayı girişi yapılmışsa veya silme tuşuna basılmışsa
				if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
					// Metin alanını düzenlenebilir yap ve girilen sayıyı metin alanına ekle
					searchTextField.setEditable(true);
		        } 
				// Sayı girişi yapılmamışsa veya silme tuşuna basılmamışsa
				else {
					// Metin alanını düzenlenemez yap ve girilen karakteri metin alanına ekleme
					searchTextField.setEditable(false);
		        }
			}
		});
		searchTextField.setForeground(new Color(70, 70, 70));
		searchTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchTextField.setColumns(10);
		searchTextField.setBackground(Color.LIGHT_GRAY);
		searchTextField.setBounds(385, 59, 494, 36);
		contentPane.add(searchTextField);
		
		JLabel lblNewLabel_4 = new JLabel("Enter the ID of the User You Want to Delete");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setForeground(new Color(80, 80, 80));
		lblNewLabel_4.setBounds(366, 25, 366, 24);
		contentPane.add(lblNewLabel_4);
		
		// Veritabanında girilen kullanıcı ID numarasını arayacak buton
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
			// Girilen sayı, tıklanıldığında veritabanında aranır ve uyuşan kullanıcı ID numarası vasıtasıyla kullanıcı ID numarası ve adı arayüzde gösterilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				UsersDB udb = new UsersDB();
				String usersInfos = udb.searchUserIDInDatabase(Integer.parseInt(searchTextField.getText()));
				
				// Kullanıcı ID numarası boşsa veya 1 ise (Yöneticiye ait id)
				if(usersInfos.isEmpty() || Integer.parseInt(searchTextField.getText()) == 1) {
					userInfoLabel.setForeground(new Color(255, 0, 0));
					userInfoLabel.setText("There is no user record with the entered ID in database.");
					deleteUserFromDBButton.setVisible(false);
				}
				else {
					userInfoLabel.setForeground(Color.DARK_GRAY);
					userInfoLabel.setText(usersInfos);
					deleteUserFromDBButton.setVisible(true);
				}
			}
		});
		searchButton.setForeground(Color.WHITE);
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchButton.setBackground(Color.DARK_GRAY);
		searchButton.setBounds(899, 59, 104, 36);
		contentPane.add(searchButton);
		
		userInfoLabel = new JLabel("");
		userInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userInfoLabel.setForeground(Color.DARK_GRAY);
		userInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		userInfoLabel.setBounds(353, 157, 650, 115);
		contentPane.add(userInfoLabel);
		
		// Veritabanından kullanıcının silinmesini sağlayan buton
		deleteUserFromDBButton = new Button("Delete User From Database");
		deleteUserFromDBButton.addMouseListener(new MouseAdapter() {
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteUserFromDBButton.setBackground(new Color(76, 145, 192));
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				deleteUserFromDBButton.setBackground(new Color(0, 128, 192));
			}
			// Belirlenen kullanıcı, tıklanıldığında veritabanında aranır ve veritabanından silinir.
			@Override
			public void mouseClicked(MouseEvent e) {
				UsersDB udb = new UsersDB();
				boolean isUserDeletedSuccessfully = udb.deleteUserFromDatabase(userInfoLabel.getText());
				
				// Silinme başarılı ise
				if (isUserDeletedSuccessfully) {
					infoLabel.setText("The user was successfully deleted. You are redirected to the home page.");
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
				// Silinme başarısızsa
				else {
					infoLabel.setText("User deletion failed. Please try again later.");
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
		deleteUserFromDBButton.setForeground(Color.WHITE);
		deleteUserFromDBButton.setBackground(new Color(0, 128, 192));
		deleteUserFromDBButton.setBounds(754, 461, 269, 46);
		contentPane.add(deleteUserFromDBButton);
		
		// Hata veya başarı mesajının gösterildiği etiket
		infoLabel = new JLabel("");
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setForeground(new Color(255, 0, 0));
		infoLabel.setBounds(353, 347, 650, 36);
		contentPane.add(infoLabel);
		deleteUserFromDBButton.setVisible(false);
	}
}
