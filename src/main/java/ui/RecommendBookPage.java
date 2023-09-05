package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import brs.RecommendationEngine;
import brs.SettingsDB;
import brs.User;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

/**
 * Kullanıcı arayüzü kitap öneri listesini içeren sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class RecommendBookPage extends JFrame {
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
	 * Ayarlar sayfasına yönlendirme paneli
	 */
	private JPanel SettingsPanel;
	/**
	 * Mevcut panel
	 */
	private JPanel RecommendPanel;
	/**
	 * Mevcut kullanıcı hesabından çıkış paneli
	 */
	private JPanel LogOutPanel;
	/**
	 * Arayüzde gösterilecek kitap öneri listesi
	 */
	private JList booksJList;
	/**
	 * Kitap listesi için kaydırma paneli
	 */
	private JScrollPane booksListScrollPane;
	
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
	 * Kitap öneri listesi
	 */
	private List<String> recommendationList;
	/**
	 * Arkaplanda bazı işlemler yapabilmek için Task sınıfının nesnesi örneği
	 */
	private Task task;
	
	/**
	 * Ana thread gerçekleşirken arka planda bazı işlemlerin gerçekleştirilmesini sağlayan sınıf
	 * @author BeratGunaydin
	 * @version 1.0.0
	 */
	class Task extends SwingWorker<Void, Void> {
		User userr;
		WaitingScreen ws = new WaitingScreen();
		
		/**
		 * Mevcut kullanıcının bilgisini getiren metottur.
		 * @param user Mevcut kullanıcı
		 */
		public Task(User user) {
			this.userr = user;
		}

		/**
		 * İşlem tamamlandığında çalışacak metod
		 */
		@Override
		protected void done() {
			ws.setVisible(false);
		}

		/**
		 * Arkaplanda yapılacak işlemi içeren metod
		 */
		@Override
		protected Void doInBackground() throws Exception {
			// Bekleme ekranına bilgi metni ayarla ve görünür yap
			ws.setInformationText("Creating a suggestion list. Please wait.");
			ws.setVisible(true);
			
			// Ayarları veritabanından al
			SettingsDB settings = new SettingsDB();
			RecommendationEngine re = new RecommendationEngine();
			
			// Öneri listesini oluştur
			List<String> recommendationList = re.recommendBook(user, (double)settings.fetchSimilarityThresholdFromDatabase() / 100);
			
			// DefaultListModel oluştur ve öneri listesini ekleyerek doldur
			DefaultListModel<String> dlm = new DefaultListModel<String>();
			for (String book : recommendationList) {
				dlm.addElement(book);
			}
			
			// Öneri listesi
			booksJList = new JList<String>(dlm);
			booksJList.setForeground(new Color(70, 70, 70));
			booksJList.setBackground(new Color(192, 192, 192));
			booksJList.setBounds(380, 100, 626, 307);
			booksListScrollPane = new JScrollPane();
			booksListScrollPane.setBounds(380, 100, 627, 307);
			contentPane.add(booksListScrollPane);
			booksListScrollPane.setViewportView(booksJList);
			
			return null;
		}
		
	}
	
	/**
	 * Giriş yapan kullanıcının bilgilerini mevcut sayfaya getiren metottur.
	 * @param user Mevcut kullanıcı
	 */
	public void setUser(User user) {
		this.user = user;
		task = new Task(user);
		task.execute();
	}

	/**
	 * Proje başlatıldığında ilk çalışacak ana metot
	 * @param args Komut satırı argümanları
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecommendBookPage frame = new RecommendBookPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sayfanın arayüzünü oluşturan ve kullanıcının arayüz üzerinden kendisine özel kitap öneri listesine erişmesini içeren yapıcı metottur.
	 */
	public RecommendBookPage() {
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
		RecommendPanel.setBackground(new Color(80, 80, 80));
		RecommendPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
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
		
		JLabel lblNewLabel_1_1 = new JLabel("RECOMMEND BOOK");
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
	}
}