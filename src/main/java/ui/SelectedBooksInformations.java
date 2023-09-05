package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brs.Book;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Kullanıcının seçili kitabın bilgilerini görmesini sağlayan arayüz sınıfı
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class SelectedBooksInformations extends JFrame {
	/**
	 * İçerik paneli
	 */
	private JPanel contentPane;
	/**
	 * ISBN etiketi
	 */
	private JLabel isbnLabel;
	/**
	 * Başlık etiketi
	 */
	private JLabel titleLabel;
	/**
	 * Yazar etiketi
	 */
	private JLabel authorLabel;
	/**
	 * Yayın tarihi etiketi
	 */
	private JLabel publicationDateLabel;
	/**
	 * Yayınevi etiketi
	 */
	private JLabel publisherLabel;
	
	/**
	 * Kitabın bilgilerini ayarlayan metot
	 * @param book Bilgileri görüntülenecek kitap
	 */
	public void setBooksInformations(Book book) {
		// ISBN etiketine kitabın ISBN değerini atar
		isbnLabel.setText(book.getIsbn());
		// Başlık etiketine kitabın başlığını atar
		titleLabel.setText(book.getTitle());
		// Yazar etiketine kitabın yazarını atar
		authorLabel.setText(book.getAuthor());
		// Yayın tarihi etiketine kitabın yayın tarihini atar
		publicationDateLabel.setText(String.valueOf(book.getPublication_date()));
		// Yayınevi etiketine kitabın yayınevini atar
		publisherLabel.setText(book.getPublisher());
	}

	/**
	 * Proje başlatıldığında ilk çalışacak ana metot
	 * @param args Komut satırı argümanları
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectedBooksInformations frame = new SelectedBooksInformations();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sayfanın arayüzünü oluşturan ve seçili kitabın bilgilerinin görüntülenmesini sağlayan yapıcı metottur.
	 */
	public SelectedBooksInformations() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Sol panel oluşturuldu ve düzenlemesi ayarlandı
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 291, 505);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Etiketler oluşturuldu ve düzenlemesi ayarlandı
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 146, 259, 49);
		panel.add(lblNewLabel);
		
		JLabel lblTtle = new JLabel("TITLE");
		lblTtle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTtle.setForeground(Color.WHITE);
		lblTtle.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblTtle.setBounds(10, 205, 259, 49);
		panel.add(lblTtle);
		
		JLabel lblAuthor = new JLabel("AUTHOR");
		lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthor.setForeground(Color.WHITE);
		lblAuthor.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblAuthor.setBounds(10, 264, 259, 49);
		panel.add(lblAuthor);
		
		JLabel lblPublcatonDate = new JLabel("PUBLICATION DATE");
		lblPublcatonDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPublcatonDate.setForeground(Color.WHITE);
		lblPublcatonDate.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblPublcatonDate.setBounds(10, 323, 259, 49);
		panel.add(lblPublcatonDate);
		
		JLabel lblPublsher = new JLabel("PUBLISHER");
		lblPublsher.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPublsher.setForeground(Color.WHITE);
		lblPublsher.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblPublsher.setBounds(10, 382, 259, 49);
		panel.add(lblPublsher);
		
		isbnLabel = new JLabel("");
		isbnLabel.setHorizontalAlignment(SwingConstants.LEFT);
		isbnLabel.setForeground(Color.DARK_GRAY);
		isbnLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		isbnLabel.setBounds(301, 146, 530, 49);
		contentPane.add(isbnLabel);
		
		titleLabel = new JLabel("");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setForeground(Color.DARK_GRAY);
		titleLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		titleLabel.setBounds(301, 205, 530, 49);
		contentPane.add(titleLabel);
		
		authorLabel = new JLabel("");
		authorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		authorLabel.setForeground(Color.DARK_GRAY);
		authorLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		authorLabel.setBounds(301, 264, 530, 49);
		contentPane.add(authorLabel);
		
		publicationDateLabel = new JLabel("");
		publicationDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		publicationDateLabel.setForeground(Color.DARK_GRAY);
		publicationDateLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		publicationDateLabel.setBounds(301, 323, 530, 49);
		contentPane.add(publicationDateLabel);
		
		publisherLabel = new JLabel("");
		publisherLabel.setHorizontalAlignment(SwingConstants.LEFT);
		publisherLabel.setForeground(Color.DARK_GRAY);
		publisherLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		publisherLabel.setBounds(301, 382, 530, 49);
		contentPane.add(publisherLabel);
		
		// Çıkış etiketi oluşturuldu ve düzenlemesi ayarlandı
		JLabel exitLabel = new JLabel("X");
		exitLabel.addMouseListener(new MouseAdapter() {
			// Çıkış etiketine basıldığında çıkış yapar
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		exitLabel.setForeground(Color.GRAY);
		exitLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		exitLabel.setBounds(813, 0, 64, 46);
		contentPane.add(exitLabel);
	}
}
