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
 * Kullanıcının seçili kitabın bilgilerini ve kullanıcının kitaba değerlendirmesini görmesini sağlayan arayüz sınıfı
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class ShowUsersRatingAndBooksInformations extends JFrame {
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
	 * Kullanıcının seçili kitaba değerlendirme puanının görüntülendiği etiket
	 */
	private JLabel yourRatingLabel;
	
	/**
	 * Kitabın bilgilerini ve kullanıcının değerlendirme puanını ayarlayan metot
	 * @param book Bilgileri görüntülenecek kitap
	 * @param rating Seçili kitap için görüntülenecek değerlendirme puanı
	 */
	public void setBooksInformations(Book book, int rating) {
		isbnLabel.setText(book.getIsbn());
		titleLabel.setText(book.getTitle());
		authorLabel.setText(book.getAuthor());
		publicationDateLabel.setText(String.valueOf(book.getPublication_date()));
		publisherLabel.setText(book.getPublisher());
		yourRatingLabel.setText(String.valueOf(rating));
	}

	/**
	 * Proje başlatıldığında ilk çalışacak ana metot
	 * @param args Komut satırı argümanları
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowUsersRatingAndBooksInformations frame = new ShowUsersRatingAndBooksInformations();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sayfanın arayüzünü oluşturan ve seçili kitabın bilgilerinin ve seçili kitap için kullanıcı değerlendirme puanının görüntülenmesini sağlayan yapıcı metottur.
	 */
	public ShowUsersRatingAndBooksInformations() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Sol panel oluşturuldu ve düzenlemesi ayarlandı
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 291, 545);
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
		
		JLabel lblYour = new JLabel("YOUR RATING");
		lblYour.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYour.setForeground(Color.WHITE);
		lblYour.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblYour.setBounds(10, 441, 259, 49);
		panel.add(lblYour);
		
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
		
		yourRatingLabel = new JLabel("");
		yourRatingLabel.setHorizontalAlignment(SwingConstants.LEFT);
		yourRatingLabel.setForeground(Color.DARK_GRAY);
		yourRatingLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		yourRatingLabel.setBounds(301, 442, 530, 49);
		contentPane.add(yourRatingLabel);
	}
}
