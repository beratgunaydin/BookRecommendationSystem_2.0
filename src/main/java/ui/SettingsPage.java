package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brs.SettingsDB;
import brs.User;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Button;
import javax.swing.UIManager;
import javax.swing.JSeparator;
/**
 * Kullanıcı arayüzü ayarlar sayfasını içeren sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class SettingsPage extends JFrame {
	/**
	 * İçerik paneli
	 */
	private JPanel contentPane;
	/**
	 * Mevcut kullanıcının kitap listesi sayfasına yönlendirme paneli
	 */
	private JPanel myBookListPanel;
	/**
	 * Tüm kitaplar ve kitap ekleme sayfasına yönlendirme paneli
	 */
	private JPanel BooksPanel;
	/**
	 * Mevcut panel
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
	 * Benzerlik eşik değerinin ayarlanabileceği slider
	 */
	private JSlider similarityThresholdSlider;
	/**
	 * Benzerlik eşik değerinin görüntülendiği etiket
	 */
	private JLabel similarityThresholdLabel;
	/**
	 * Yapılan değişiklikleri onaylayıp veritabanına kaydeden buton
	 */
	private Button applyAndSaveButton;
	/**
	 * Başarılı veya başarısız işlemlerin arayüzden gösterildiği etiket
	 */
	private JLabel infoLabel;
	/**
	 * Her benzer kullanıcıdan kaç kitap önerileceğini belirleyen slider
	 */
	private JSlider numberOfBooksSlider;
	/**
	 * Her benzer kullanıcıdan kaç kitap önerileceğini gösteren etiket
	 */
	private JLabel numberOfBooksLabel;
	
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
	private User user;
	/**
	 * Ayarlar veritabanına ulaşmak için nesne örneği
	 */
	private SettingsDB settings = new SettingsDB();
	
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
					SettingsPage frame = new SettingsPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sayfanın arayüzünü oluşturan ve kullanıcının arayüz üzerinde benzerlik eşik değerini ayarlama ve her kullanıcıdan kaç kitap önerisi alınacağını ayarlama işlemlerini içeren yapıcı metottur.
	 */
	public SettingsPage() {
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
		BooksPanel.addMouseListener(new MouseAdapter() {
			// Panelin üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				BooksPanel.setBackground(new Color(100, 100, 100));
			}
			// Panelin üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				BooksPanel.setBackground(new Color(128, 128, 128));
			}
			// Panele tıklanıldığında mevcut sayfa kapatılıp tüm kitaplar ve kullanıcının listesine kitap ekleme sayfasına yönlendirilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				BooksPage bp = new BooksPage();
				bp.setUndecorated(true);
				bp.setUser(user);
				bp.setVisible(true);
			}
		});
		BooksPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		BooksPanel.setBackground(Color.GRAY);
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
		SettingsPanel.setBackground(new Color(80, 80, 80));
		SettingsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
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
		
		JLabel lblNewLabel_1_1 = new JLabel("SETTINGS");
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
		
		JLabel lblNewLabel_4 = new JLabel("Similarity Threshold");
		lblNewLabel_4.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
		lblNewLabel_4.setForeground(Color.DARK_GRAY);
		lblNewLabel_4.setBounds(350, 52, 204, 32);
		contentPane.add(lblNewLabel_4);
		
		// similarityThresholdSlider oluşturuluyor ve değeri veritabanından alınıyor
		similarityThresholdSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, settings.fetchSimilarityThresholdFromDatabase());
		// similarityThresholdSlider'ın değeri değiştiğinde bir ChangeListener ekleniyor
		similarityThresholdSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = similarityThresholdSlider.getValue();
				double tValue = (double) value / 100;
				// similarityThresholdLabel güncelleniyor
				similarityThresholdLabel.setText(String.valueOf(tValue));
			}
		});
		similarityThresholdSlider.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		similarityThresholdSlider.setBounds(370, 104, 437, 32);
		contentPane.add(similarityThresholdSlider);
		
		// Benzerlik eşik değeri etiketi slider'a göre ayarlanır.
		similarityThresholdLabel = new JLabel(String.valueOf((double)similarityThresholdSlider.getValue() / 100));
		similarityThresholdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		similarityThresholdLabel.setForeground(Color.DARK_GRAY);
		similarityThresholdLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		similarityThresholdLabel.setBounds(836, 104, 45, 32);
		contentPane.add(similarityThresholdLabel);
		
		// applyAndSaveButton oluşturuluyor ve etiketi "Apply and Save Changes" olarak ayarlanıyor
		applyAndSaveButton = new Button("Apply and Save Changes");
		applyAndSaveButton.addMouseListener(new MouseAdapter() {
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				applyAndSaveButton.setBackground(new Color(76, 145, 192));
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				applyAndSaveButton.setBackground(new Color(0, 128, 192));
			}
			// Butona tıklandığında veritabanındaki değerleri günceller. İşlem başarılı değilse uyarı mesajı, başarılıysa başarı mesajı görüntülenir.
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean isSimilarityThresholdUpdated = settings.updateSimilarityThresholdInDatabase(similarityThresholdSlider.getValue());
				boolean isNumberOfBooksToBeRecommendedByEachUserUpdated = settings.updateNumberOfBooksToBeRecommendedByEachUserInDatabase(numberOfBooksSlider.getValue());
				
				// Benzerlik eşiği ve kullanıcı başına önerilecek kitap sayısı güncellenmediyse
				if (!isSimilarityThresholdUpdated && !isNumberOfBooksToBeRecommendedByEachUserUpdated) {
					infoLabel.setForeground(new Color(255, 0, 0));
					infoLabel.setText("Process failed. Please try again later.");
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
				else {
					// Benzerlik eşiği ve kullanıcı başına önerilecek kitap sayısı başarıyla güncellendi
					infoLabel.setForeground(new Color(60, 207, 60));
					infoLabel.setText("Process successful. You are directed to the home screen.");
					infoLabel.setVisible(true);
						
					Thread thread = new Thread() {
		                   @Override
		                   public void run() {
		                       try {
		                           Thread.sleep(3000);
		                           infoLabel.setVisible(false);
		                            
		                           dispose();
		                           HomePage hp = new HomePage();
		                           hp.setUndecorated(true);
		                           hp.setUser(user);
		                           hp.setVisible(true);
		                       } catch (InterruptedException e1) {
		                           e1.printStackTrace();
		                       }
		                   }
		                };
		            thread.start();
				}
			}
		});
		applyAndSaveButton.setForeground(Color.WHITE);
		applyAndSaveButton.setBackground(new Color(0, 128, 192));
		applyAndSaveButton.setBounds(758, 464, 271, 46);
		contentPane.add(applyAndSaveButton);
		
		infoLabel = new JLabel("");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		infoLabel.setForeground(new Color(255, 0, 0));
		infoLabel.setBounds(340, 413, 700, 32);
		contentPane.add(infoLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(80, 80, 80));
		separator.setBounds(340, 161, 687, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_4_1 = new JLabel("Number of Books to be Recommended by Each User");
		lblNewLabel_4_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_4_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
		lblNewLabel_4_1.setBounds(350, 178, 498, 32);
		contentPane.add(lblNewLabel_4_1);
		
		// numberOfBooksSlider oluşturuluyor ve değeri veritabanından alınıyor
		numberOfBooksSlider = new JSlider(SwingConstants.HORIZONTAL, 1, 30, settings.fetchNumberOfBooksToBeRecommendedByEachUserFromDatabase());
		// numberOfBooksSlider'ın değeri değiştiğinde bir ChangeListener ekleniyor
		numberOfBooksSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = numberOfBooksSlider.getValue();
				// numberOfBooksLabel güncelleniyor
				numberOfBooksLabel.setText(String.valueOf(value));
			}
		});
		numberOfBooksSlider.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		numberOfBooksSlider.setBounds(370, 220, 437, 32);
		contentPane.add(numberOfBooksSlider);
		
		// Her benzer kullanıcıdan kaç adet kitap önerileceği etiketi slider'a göre ayarlanır.
		numberOfBooksLabel = new JLabel(String.valueOf(numberOfBooksSlider.getValue()));
		numberOfBooksLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numberOfBooksLabel.setForeground(Color.DARK_GRAY);
		numberOfBooksLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		numberOfBooksLabel.setBounds(836, 220, 64, 32);
		contentPane.add(numberOfBooksLabel);
	}
}