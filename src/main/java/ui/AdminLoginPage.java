package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brs.User;
import brs.UsersDB;
import brs.UsersList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Point;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Yöneticinin arayüz üzerinden yönetici hesabına giriş yaptığı sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class AdminLoginPage extends JFrame {
	/**
	 * İçerik paneli
	 */
	private JPanel contentPane;
	/**
	 * Kullanıcı adı metin giriş alanı
	 */
	private JTextField userNameTextField;
	/**
	 * Şifre metin giriş alanı
	 */
	private JPasswordField passwordField;
	/**
	 * Bilgi etiketi
	 */
	private JLabel lblNewLabel_2;
	/**
	 * Yönetici hesabı giriş butonu
	 */
	private Button loginAdminButton;
	/**
	 * Kullanıcı giriş sayfası paneli
	 */
	private JPanel loginPagePanel;
	
	/**
	 * Konum bilgisi
	 */
	Point location;
	/**
	 * Fare olaylarını takip etmek için kullanılan değişken
	 */
	MouseEvent pressed;
	/**
	 * Giriş yapacak yönetici
	 */
	User user;

	/**
	 * Proje başlatıldığında ilk çalışacak ana metot
	 * @param args Komut satırı argümanları
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPage frame = new SignUpPage();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sayfanın arayüzünü oluşturan ve yöneticinin arayüz üzerinden yönetici sayfasına girmesini sağlayan yapıcı metottur.
	 */
	public AdminLoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 957, 526);
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Menü paneli
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 431, 527);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Menü ikonu
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AdminLoginPage.class.getResource("/images/preferences-g0db36ff3d_640.png")));
		lblNewLabel_1.setBounds(77, 122, 276, 256);
		panel.add(lblNewLabel_1);
		
		JLabel lblSignUp_1 = new JLabel("ADMIN");
		lblSignUp_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp_1.setForeground(Color.WHITE);
		lblSignUp_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 28));
		lblSignUp_1.setBounds(43, 42, 316, 46);
		panel.add(lblSignUp_1);
		
		// Kullanıcı girişi sayfasına geçiş paneli
		loginPagePanel = new JPanel();
		loginPagePanel.addMouseListener(new MouseAdapter() {
			// Panelin üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				loginPagePanel.setBackground(new Color(100, 100, 100));
			}
			// Panelin üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				loginPagePanel.setBackground(new Color(128, 128, 128));
			}
			// Panele tıklanıldığında mevcut sayfa kapatılıp kullanıcı girişi sayfasına yönlendirilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				LoginPage lp = new LoginPage();
				lp.setUndecorated(true);
				lp.setVisible(true);
			}
		});
		loginPagePanel.setBackground(Color.GRAY);
		loginPagePanel.setBounds(0, 392, 431, 60);
		panel.add(loginPagePanel);
		loginPagePanel.setLayout(null);
		
		// Kullanıcı girişi sayfasına geçiş ikonu
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(SignUpPage.class.getResource("/images/icons8-left-arrow-50.png")));
		lblNewLabel_3.setBounds(10, 0, 60, 60);
		loginPagePanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("GO BACK TO LOGIN SCREEN");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(80, 10, 324, 40);
		loginPagePanel.add(lblNewLabel_4);
		
		// Bilgilendirme etiketi
		lblNewLabel_2 = new JLabel("Username or password is invalid. Please try again.");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(441, 346, 492, 27);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		// Yönetici girişi butonu
		loginAdminButton = new Button("Login");
		loginAdminButton.addMouseListener(new MouseAdapter() {
			// Girilen bilgiler, tıklanıldığında veritabanında aranır ve uyuşan yönetici kaydı vasıtasıyla yönetici ana sayfasına geçilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				UsersDB udb = new UsersDB();
				String userName = userNameTextField.getText();
				char[] password = passwordField.getPassword();
				String passwordInput = "";
				
				for (char passwordKey : password) {
					passwordInput += passwordKey;
				}
				
				// Yönetici, kullanıcı adı ve şifre ile veritabanı içerisinde aranır.
				user =udb.searchUserInDatabase(userName, passwordInput);
				
				// Eğer bir kullanıcı adı dönüşü olmamışsa
				if(user.getUser_name() == null) {
					// Uyarı mesajı görüntülenir.
					lblNewLabel_2.setVisible(true);
					
					Thread thread = new Thread() {
	                    @Override
	                    public void run() {
	                        try {
	                            Thread.sleep(2000);
	                            lblNewLabel_2.setVisible(false);
	                        } catch (InterruptedException e1) {
	                            e1.printStackTrace();
	                        }
	                    }
	                };
	                thread.start();
				}
				// Bir kullanıcı adı dönüşü olmuşsa ve kullanıcı adı adminse
				else {
					if (user.getUser_name().equals("admin")) {
						//Mevcut sayfa kapanır ve yönetici ana sayfası açılır
						dispose();
						AdminPage ap = new AdminPage();
						ap.setAdmin(user);
						ap.setUndecorated(true);
						ap.setVisible(true);
					}
					
					else {
						// Uyarı mesajı görüntülenir.
						lblNewLabel_2.setVisible(true);
						
						Thread thread = new Thread() {
		                    @Override
		                    public void run() {
		                        try {
		                            Thread.sleep(2000);
		                            lblNewLabel_2.setVisible(false);
		                        } catch (InterruptedException e1) {
		                            e1.printStackTrace();
		                        }
		                    }
		                };
		                thread.start();
					}
				}
			}
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				loginAdminButton.setBackground(new Color(241, 90, 110));
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				loginAdminButton.setBackground(new Color(241, 57, 83));
			}
		});
		loginAdminButton.setBackground(new Color(241, 57, 83));
		loginAdminButton.setForeground(new Color(255, 255, 255));
		loginAdminButton.setBounds(598, 398, 192, 46);
		contentPane.add(loginAdminButton);
		
		// Kullanıcı adı metin giriş alanı
		userNameTextField = new JTextField();
		userNameTextField.setForeground(new Color(70, 70, 70));
		userNameTextField.setBackground(new Color(192, 192, 192));
		userNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userNameTextField.setBounds(542, 147, 307, 40);
		contentPane.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setForeground(new Color(80, 80, 80));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(542, 110, 108, 27);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(80, 80, 80));
		separator.setBounds(511, 214, 358, 2);
		contentPane.add(separator);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(80, 80, 80));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(542, 237, 108, 27);
		contentPane.add(lblPassword);
		
		// Çıkış etiketi oluşturuldu ve düzenlemesi ayarlandı
		JLabel loginCloseLabel = new JLabel("X");
		loginCloseLabel.addMouseListener(new MouseAdapter() {
			// Çıkış etiketine basıldığında çıkış yapar
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		loginCloseLabel.setForeground(new Color(128, 128, 128));
		loginCloseLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		loginCloseLabel.setBounds(916, 0, 64, 46);
		contentPane.add(loginCloseLabel);
		
		// Şifre metin giriş alanı
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(70, 70, 70));
		passwordField.setBackground(new Color(192, 192, 192));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(542, 274, 307, 40);
		contentPane.add(passwordField);
	}
}