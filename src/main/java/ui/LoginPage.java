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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Kullanıcı arayüzü kullanıcı hesabına giriş kısmını, uygulama başlatıldığında ilk oluşturulan sayfayı içeren sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class LoginPage extends JFrame {
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
	 * Başarılı veya başarısız işlemlerin arayüzden gösterildiği etiket
	 */
	private JLabel lblNewLabel_2;
	/**
	 * Kullanıcı hesabına giriş butonu
	 */
	private Button loginButton;
	/**
	 * Kayıt olma sayfasına yönlendirme butonu
	 */
	private Button signUpButton;
	/**
	 * Yönetici sayfasına yönlendirme butonu
	 */
	private Button adminButton;
	
	/**
	 * Konum bilgisi
	 */
	Point location;
	/**
	 * Fare olaylarını takip etmek için kullanılan değişken
	 */
	MouseEvent pressed;
	/**
	 * Giriş yapan kullanıcı
	 */
	User user;
	/**
	 * Şifreyi kaydetme butonu
	 */
	private Button mySQLPasswordButton;

	/**
	 * Proje başlatıldığında ilk çalışacak ana metot
	 * @param args Komut satırı argümanları
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sayfanın arayüzünü oluşturan ve kullanıcının arayüz üzerinden hesabına giriş yaptığı yapıcı metottur.
	 */
	public LoginPage() {
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
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 431, 527);
		contentPane.add(panel);
		
		// Menü ikonu
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginPage.class.getResource("/images/pexels-cottonbro-studioo-2925304.png")));
		lblNewLabel_1.setBounds(0, 0, 431, 527);
		panel.add(lblNewLabel_1);
		
		// Bilgilendirme etiketi
		lblNewLabel_2 = new JLabel("Username or password is invalid. Please try again.");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(496, 343, 426, 27);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		// Giriş yap butonu
		loginButton = new Button("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			// Butona tıklanıldığında girilen kullanıcı bilgileri, veritabanında aranır. Eğer bir bilgiye ulaşılamazsa hata mesajı, ulaşılırsa başarılı işlem mesajı gösterilir. Ve kullanıcının hesabı ile birlikte ana sayfaya yönlendirilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				UsersDB udb = new UsersDB();
				String userName = userNameTextField.getText();
				char[] password = passwordField.getPassword();
				String passwordInput = "";
				
				for (char passwordKey : password) {
					passwordInput += passwordKey;
				}
				
				user =udb.searchUserInDatabase(userName, passwordInput);
				
				if(user.getUser_name() == null) {
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
				else {
					if (user.getUser_name().equals("admin")) {
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
					
					else {
						dispose();
						HomePage hp = new HomePage();
						hp.setUser(user);
						hp.setUndecorated(true);
						hp.setVisible(true);
					}
				}
			}
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setBackground(new Color(241, 90, 110));
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBackground(new Color(241, 57, 83));
			}
		});
		loginButton.setBackground(new Color(241, 57, 83));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBounds(598, 278, 192, 46);
		contentPane.add(loginButton);
		
		// Kullanıcı adının girileceği metin giriş alanı
		userNameTextField = new JTextField();
		userNameTextField.setForeground(new Color(70, 70, 70));
		userNameTextField.setBackground(new Color(192, 192, 192));
		userNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userNameTextField.setBounds(542, 85, 307, 40);
		contentPane.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setForeground(new Color(80, 80, 80));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(542, 48, 108, 27);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(80, 80, 80));
		separator.setBounds(512, 147, 358, 2);
		contentPane.add(separator);
		
		// Kayıt ol sayfasına yönlendirme butonu
		signUpButton = new Button("Sign Up");
		signUpButton.addMouseListener(new MouseAdapter() {
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				signUpButton.setBackground(new Color(57, 147, 255));
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				signUpButton.setBackground(new Color(0, 128, 255));
			}
			// Butona tıklanıldığında mevcut sayfa kapatılıp kayıt ol sayfası açılır.
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				SignUpPage sup = new SignUpPage();
				sup.setUndecorated(true);
				sup.setVisible(true);
			}
		});
		signUpButton.setForeground(Color.WHITE);
		signUpButton.setBackground(new Color(0, 128, 255));
		signUpButton.setBounds(448, 392, 192, 46);
		contentPane.add(signUpButton);
		
		// Yönetici sayfasına yönlendirme butonu
		adminButton = new Button("Admin");
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Butona tıklanıldığında mevcut sayfa kapatılıp yönetici giriş sayfası açılır.
				dispose();
				AdminLoginPage alp = new AdminLoginPage();
				alp.setUndecorated(true);
				alp.setVisible(true);
			}
		});
		adminButton.addMouseListener(new MouseAdapter() {
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				adminButton.setBackground(new Color(76, 145, 192));
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				adminButton.setBackground(new Color(0, 128, 192));
			}
		});
		adminButton.setForeground(Color.WHITE);
		adminButton.setBackground(new Color(0, 128, 192));
		adminButton.setBounds(708, 392, 192, 46);
		contentPane.add(adminButton);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(80, 80, 80));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(542, 168, 108, 27);
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
		passwordField.setBounds(542, 205, 307, 40);
		contentPane.add(passwordField);
		
		// MySQL veritabanı bağlantısı için gereken kullanıcının şifresini girmesini sağlayan sayfaya yönlendiren buton
		mySQLPasswordButton = new Button("MySQL Password");
		mySQLPasswordButton.addMouseListener(new MouseAdapter() {
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				mySQLPasswordButton.setBackground(Color.GRAY);
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				mySQLPasswordButton.setBackground(Color.DARK_GRAY);
			}
			// MySQL veritabanı bağlantısı için gereken kullanıcının şifresini girmesini sağlayan sayfaya yönlendirildi.
			@Override
			public void mouseClicked(MouseEvent e) {
				MySQLPasswordPage mpp = new MySQLPasswordPage();
				mpp.setVisible(true);
			}
		});
		mySQLPasswordButton.setForeground(Color.WHITE);
		mySQLPasswordButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mySQLPasswordButton.setBackground(Color.DARK_GRAY);
		mySQLPasswordButton.setBounds(762, 453, 181, 36);
		contentPane.add(mySQLPasswordButton);
	}
}
