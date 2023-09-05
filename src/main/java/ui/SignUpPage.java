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
 * Kullanıcının arayüz üzerinden kullanıcı hesabına giriş yaptığı sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class SignUpPage extends JFrame {
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
	 * Kullanıcı hesabı kayıt butonu
	 */
	private Button signUpButton;
	/**
	 * Kullanıcı giriş sayfası paneli
	 */
	private JPanel loginPagePanel;
	/**
	 * Girilen kullanıcı adının sistemde kayıtlı olup olmadığı hakkında bilgi veren etiket
	 */
	private JLabel usernameControlLabel;
	
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
	 * Sayfanın arayüzünü oluşturan ve kullanıcının arayüz üzerinden kayıt olmasını sağlayan yapıcı metottur.
	 */
	public SignUpPage() {
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
		lblNewLabel_1.setIcon(new ImageIcon(SignUpPage.class.getResource("/images/sign-up.png")));
		lblNewLabel_1.setBounds(94, 122, 276, 256);
		panel.add(lblNewLabel_1);
		
		JLabel lblSignUp_1 = new JLabel("SIGN UP");
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
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(441, 346, 492, 27);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		// Kullanıcı veritabanına kayıt butonu
		signUpButton = new Button("Sign Up");
		signUpButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				UsersDB udb = new UsersDB();
				// Kullanıcı adı alınıyor
				String userName = userNameTextField.getText();
				// Şifre alınıyor
				char[] password = passwordField.getPassword();
				String passwordInput = "";
				
				for (char passwordKey : password) {
					passwordInput += passwordKey;
				}
				
				// Kullanıcı adı veya şifre boşsa
				if(userName.isEmpty() || passwordInput.isEmpty()) {
					lblNewLabel_2.setForeground(new Color(255, 0, 0));
					lblNewLabel_2.setText("Username or password field can't be blank.");
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
				
				// Şifrenin uzunluğu 6 ila 16 karakter arasında değilse
				else {
					if(passwordInput.length() < 6 || passwordInput.length() > 16) {
						lblNewLabel_2.setForeground(new Color(255, 0, 0));
						lblNewLabel_2.setText("Password must be minimum 6 and maximum 16 characters length.");
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
						// Yeni kullanıcı kaydediliyor
						boolean isAccountRegisteredInDatabase = udb.registerNewUser(userName, passwordInput);
						
						// Kayıt işlemi başarısız olduysa
						if(!isAccountRegisteredInDatabase) {
							lblNewLabel_2.setForeground(new Color(255, 0, 0));
							lblNewLabel_2.setText("The username must be unique. Please try another username.");
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
						// Kayıt işlemi başarılı olduysa
						else {
							lblNewLabel_2.setForeground(new Color(60, 207, 60));
							lblNewLabel_2.setText("Registration successful. You are directed to the login screen.");
							lblNewLabel_2.setVisible(true);
								
							Thread thread = new Thread() {
				                   @Override
				                   public void run() {
				                       try {
				                           Thread.sleep(3000);
				                           lblNewLabel_2.setVisible(false);
				                            
				                           dispose();
				                           LoginPage lp = new LoginPage();
				                           lp.setUndecorated(true);
				                           lp.setVisible(true);
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
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				signUpButton.setBackground(new Color(241, 90, 110));
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				signUpButton.setBackground(new Color(241, 57, 83));
			}
		});
		signUpButton.setBackground(new Color(241, 57, 83));
		signUpButton.setForeground(new Color(255, 255, 255));
		signUpButton.setBounds(598, 398, 192, 46);
		contentPane.add(signUpButton);
		
		// Kullanıcı adı metin giriş alanı
		userNameTextField = new JTextField();
		userNameTextField.addKeyListener(new KeyAdapter() {
			// Klavye etkileşimleri dinlenir.
			@Override
			public void keyReleased(KeyEvent e) {
				UsersDB udb = new UsersDB();
				// Kullanıcı adı alınıyor
				String userName = userNameTextField.getText();
				// Kullanıcı adının veritabanında kayıtlı olup olmadığı kontrol ediliyor
				boolean isAccountRegisteredInDatabase = udb.checkUserNameInDatabase(userName);
				
				// Eğer kullanıcı adı veritabanında kayıtlı değilse
				if(!isAccountRegisteredInDatabase) {
					// Kontrol işaretini doğru işaretiyle değiştir
					usernameControlLabel.setIcon(new ImageIcon(SignUpPage.class.getResource("/images/icons8-checkmark-48.png")));
				}
				// Kullanıcı veritabanında kayıtlı ise
				else {
					//Kontrol işaretini yanlış işaretiyle değiştir
					usernameControlLabel.setIcon(new ImageIcon(SignUpPage.class.getResource("/images/icons8-cancel-48.png")));
				}
			}
		});
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
		passwordField.setToolTipText("Password must be minimum 6 and maximum 16 characters length.");
		contentPane.add(passwordField);
		
		// Kullanıcı adı kontrol etiketi
		usernameControlLabel = new JLabel("");
		usernameControlLabel.setIcon(new ImageIcon(SignUpPage.class.getResource("/images/icons8-cancel-48.png")));
		usernameControlLabel.setBounds(859, 140, 56, 54);
		contentPane.add(usernameControlLabel);
	}
}