package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brs.DatabaseConnection;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Veritabanına bağlantı şifresinin girilmesini gerçekleştiren sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class MySQLPasswordPage extends JFrame {

	/**
	 * İçerik paneli
	 */
	private JPanel contentPane;
	/**
	 * MySQL sunucusuna bağlantı için gereken şifrenin metin giriş alanı
	 */
	private JTextField textField;
	/**
	 * Şifreyi kaydetme butonu
	 */
	private Button applyButton;

	/**
	 * Proje başlatıldığında ilk çalışacak ana metot
	 * @param args Komut satırı argümanları
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySQLPasswordPage frame = new MySQLPasswordPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * MySQL sunucusuna bağlantı için gereken şifrenin girileceği alanı ve sayfanın genel yapısını oluşturan yapıcı metottur.
	 */
	public MySQLPasswordPage() {
		setTitle("MySQL Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MySQL Password");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(172, 40, 186, 36);
		contentPane.add(lblNewLabel);
		
		// MySQL sunucusuna bağlantı için gereken şifrenin girileceği metin giriş alanı
		textField = new JTextField();
		textField.setForeground(new Color(70, 70, 70));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(119, 108, 320, 36);
		contentPane.add(textField);
		
		// Veritabanı bağlantısı için gereken şifreyi kaydeden buton
		applyButton = new Button("Apply");
		applyButton.addMouseListener(new MouseAdapter() {
			// Butonun üzerine gelindiğinde arkaplan rengi değişir.
			@Override
			public void mouseEntered(MouseEvent e) {
				applyButton.setBackground(Color.GRAY);
			}
			// Butonun üzerinden çıkıldığında arkaplan rengi eski haline gelir.
			@Override
			public void mouseExited(MouseEvent e) {
				applyButton.setBackground(Color.DARK_GRAY);
			}
			// Girilen metin, tıklanıldığında veritabanı bağlantısında kullanılacak şifre için geçici olarak kaydedilir.
			@Override
			public void mouseClicked(MouseEvent e) {
				String mysqlPassword = textField.getText();
				DatabaseConnection db = new DatabaseConnection();
				db.setPassword(mysqlPassword);
				dispose();
			}
		});
		applyButton.setForeground(Color.WHITE);
		applyButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		applyButton.setBackground(Color.DARK_GRAY);
		applyButton.setBounds(212, 198, 104, 36);
		contentPane.add(applyButton);
	}
}
