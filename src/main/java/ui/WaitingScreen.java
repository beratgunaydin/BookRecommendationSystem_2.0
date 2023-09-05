package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

/**
 * Belirli bir işlem sırasında bekleme ekranı gösterebilen sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class WaitingScreen extends JFrame {
	/**
	 * İçerik paneli
	 */
	private JPanel contentPane;
	/**
	 * Sayfa ikonu etiketi
	 */
	private JLabel lblNewLabel;
	/**
	 * Sayfada görüntülenecek mesaj alanı
	 */
	private JTextArea txtrCreatingASuggestion;

	/**
	 * Proje başlatıldığında ilk çalışacak ana metot
	 * @param args Komut satırı argümanları
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaitingScreen frame = new WaitingScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Belirtilen bilgi metnini görüntülemek için metin alanını ayarlar.
	 * @param informationText Görüntülenecek bilgi metni
	 */
	public void setInformationText(String informationText) {
		// JTextArea nesnesi oluşturuluyor
		txtrCreatingASuggestion = new JTextArea();
		// Metin rengi ayarlanıyor
		txtrCreatingASuggestion.setForeground(Color.DARK_GRAY);
		// Metin alanının düzenlenebilirliği devre dışı bırakılıyor
		txtrCreatingASuggestion.setEditable(false);
		// Kelimelerin satır sonunda bölünmesi etkinleştiriliyor
		txtrCreatingASuggestion.setWrapStyleWord(true);
		// Metin alanına belirtilen bilgi metni atanıyor
		txtrCreatingASuggestion.setText(informationText);
		// Metin fontu ayarlanıyor
		txtrCreatingASuggestion.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
		// Satır sonunda otomatik olarak satır kaydırma yapılıyor
		txtrCreatingASuggestion.setLineWrap(true);
		// Metin alanının konumu ve boyutu ayarlanıyor
		txtrCreatingASuggestion.setBounds(46, 70, 225, 80);
		// Metin alanı içerik paneline ekleniyor
		contentPane.add(txtrCreatingASuggestion);
	}

	/**
	 * WaitingScreen sınıfının yapıcı metodu. Pencere kapatıldığında programın sonlandırılmasını sağlar. Pencere boyutu ve özellikleri ayarlanır. Arka plan ve içerik paneli oluşturulur. Gerekli bileşenler eklenir ve konumları ayarlanır.
	 */
	public WaitingScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(WaitingScreen.class.getResource("/images/icons8-sand-timer.gif")));
		lblNewLabel.setBounds(305, 70, 85, 80);
		contentPane.add(lblNewLabel); 
	}
}
