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
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Yöneticinin arayüzü ana sayfasını içeren sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class AdminPage extends JFrame {

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
	 * Veritabanından kullanıcı silme sayfasına yönlendirme paneli
	 */
	private JPanel DeleteUserFromDatabasePanel;
	/**
	 * Yönetici hesabından çıkış paneli
	 */
	private JPanel LogOutPanel;
	/**
	 * Ekranda hoşgeldiniz yazdıran etiket
	 */
	private JLabel welcomeLabel;
	
	/**
	 * Konum bilgisi
	 */
	Point location;
	/**
	 * Fare olaylarını takip etmek için kullanılan değişken
	 */
	MouseEvent pressed;
	/**
	 * Giriş yapan yönetici
	 */
	private User user;
	
	/**
	 * Giriş yapan yöneticinin bilgilerini mevcut sayfaya getiren metottur.
	 * @param user Mevcut yönetici
	 */
	public void setAdmin(User user) {
		this.user = user;
		welcomeLabel.setText("Welcome " + user.getUser_name() + ".");
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
	 * Sayfanın arayüzünü oluşturan ve yöneticinin arayüz üzerinde diğer sayfalara erişim sağlayabildiği yönetici ana sayfasını içeren metottur.
	 */
	public AdminPage() {
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
		
		JLabel lblNewLabel_1_1 = new JLabel("ADMIN PAGE");
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
		
		// Karşılama etiketi
		welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setForeground(new Color(128, 128, 128));
		welcomeLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		welcomeLabel.setBounds(361, 25, 531, 36);
		contentPane.add(welcomeLabel);
	}
}
