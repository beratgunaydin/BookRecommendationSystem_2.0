package brs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Öneri motorunun oluşturulduğu sınıf
 * @author BeratGunaydin
 * @version 1.0.0
 */
public class RecommendationEngine {
	// Öneriler veritabanı nesnesi oluşturuldu.
	RecommendationsDatabase rdb = new RecommendationsDatabase();
	// Kullanıcılar veritabanı nesnesi oluşturuldu.
	UsersDB udb = new UsersDB();
	// Ayarlar veritabanı nesnesi oluşturuldu.
	SettingsDB sdb = new SettingsDB();
	
	/**
	 * Bu metot, bir kullanıcıya benzer kullanıcıların listesini oluşturur ve verilen benzerlik eşiğine göre önerilen kitapları liste olarak döndürür. createSimiliarUsersList metodu kullanılarak, verilen kullanıcının benzerlik eşiğine göre benzer kullanıcıların bir listesi oluşturulur. Bu liste, önerilecek kitaplar için potansiyel kaynak oluşturur. recommendedBooksList adında yeni bir boş liste oluşturulur. Bu liste, kullanıcıya önerilecek kitapların ISBN ve adlarını içerecektir. counter adında bir sayaç değişkeni oluşturulur. recommendedBooksISBNList içindeki her bir ISBN için döngü oluşturulur. Bu, benzer kullanıcılardan elde edilen potansiyel kitapların ISBN'lerini temsil eder. Her bir ISBN için, rdb.fetchBooksISBNAndNameFromISBN(isbn) metodu kullanılarak ISBN'e karşılık gelen kitabın adı elde edilir ve recommendedBooksList listesine eklenir. Döngü tamamlandıktan sonra, recommendedBooksList önerilen kitapların tam listesini içeren bir liste olarak döndürülür.
	 * @param user Öneri listesi sunulacak kullanıcı
	 * @param similarityThreshold Kullanıcıların benzerlik düzeyini kontrol etmek için benzerlik eşik değeri
	 * @return Kullanıcıya önerilen kitaplar listesi
	 */
	public List<String> recommendBook(User user, double similarityThreshold) {
		// Benzer kullanıcılar listesi oluşturuldu.
		List<String> recommendedBooksISBNList = createSimiliarUsersList(user.getUser_id(), similarityThreshold);
		// Önerilen kitapların listesi oluşturuldu.
		List<String> recommendedBooksList = new ArrayList<String>();
		// Counter değişkeni oluşturuldu.
		int counter = 0;
		for (String isbn : recommendedBooksISBNList) {
			// Önerilen kitapların ISBN ve adı çekildi ve listeye eklendi.
			recommendedBooksList.add(rdb.fetchBooksISBNAndNameFromISBN(isbn));
		}
		
		// Önerilen kitapların listesi döndürüldü.
		return recommendedBooksList;
	}
	
	/**
	 * Bu metot, bir kullanıcının benzerlik eşiğine göre benzer kullanıcıların önerdiği kitapların listesini oluşturur. recommendationList adında boş bir liste oluşturulur. Bu liste, önerilecek kitapların ISBN'lerini içerecektir. udb.fetchSimiliarUsersIDListFromDatabase metodu kullanılarak, veritabanından benzer kullanıcıların ID listesi alınır. usersIDList listesinin boyutu yazdırılır. Bu, benzer kullanıcıların sayısını gösterir. counter adında bir sayaç değişkeni oluşturulur. usersIDList içindeki her bir kullanıcı ID için döngü oluşturulur. computePearsonCorrelationSimilarity metodu kullanılarak, mevcut kullanıcı ID'si ve karşılaştırılan kullanıcı ID'si arasındaki Pearson korelasyon benzerliği hesaplanır. counter ve kullanıcı ID'si yazdırılır. Eğer hesaplanan benzerlik, belirlenen benzerlik eşiğini geçerse; benzerlik değeri yazdırılır, karşılaştırılan kullanıcının kitaplarının ISBN listesi çekilir ve counterForCompared adında bir sayaç oluşturulur. comparedUsersBooksISBNList içindeki her bir ISBN için döngü oluşturulur. Öneri listesinde olmayan ve mevcut kullanıcının sahip olmadığı kitaplar kontrol edilir. Bu kitaplar, karşılaştırılan kullanıcının önerdiği potansiyel kitaplardır. Eğer kitap öneri listesinde bulunmuyor ve mevcut kullanıcı tarafından sahip olunmuyorsa; counterForCompared arttırılır ve kitap, öneri listesine eklenir. counterForCompared, her kullanıcı için önerilecek kitap sayısı sınırına ulaştığında, döngüden çıkılır. Tüm benzer kullanıcılar üzerindeki döngü tamamlandıktan sonra, recommendationList önerilen kitapların tam listesini içeren bir liste olarak döndürülür.
	 * @param currentUsersID Mevcut kullanıcının ID numarası
	 * @param similarityThreshold Kullanıcıların benzerlik düzeyini kontrol etmek için benzerlik eşik değeri
	 * @return Önerilen kitapların tam listesi
	 */
	private List<String> createSimiliarUsersList(int currentUsersID, double similarityThreshold) {
		// Öneri listesi oluşturuldu.
		List<String> recommendationList = new ArrayList<String>();
		// Veritabanından benzer kullanıcıların ID listesi çekildi.
		List<Integer> usersIDList = udb.fetchSimiliarUsersIDListFromDatabase(currentUsersID);
		// Kullanıcı ID listesinin boyutu yazdırıldı.
		System.out.println(usersIDList.size());
		// Counter değişkeni oluşturuldu.
		int counter = 0;
		
		for(int userID : usersIDList) {
			// Pearson korelasyon benzerliği hesaplandı.
			double similarity = computePearsonCorrelationSimilarity(currentUsersID, userID);
			// Counter arttırıldı.
			counter++;
			// Counter ve kullanıcı ID'si yazdırıldı.
			System.out.println(counter + ", " + userID);
			// Benzerlik eşiği geçiliyorsa
			if (similarity > similarityThreshold) {
				// Benzerlik değeri yazdırılıyor.
				System.out.println(similarity);
				// Karşılaştırılan kullanıcının kitaplarının ISBN listesi çekildi.
				List<String> comparedUsersBooksISBNList = rdb.fetchUsersBooksISBNList(userID);
				// Karşılaştırma için counter oluşturuldu.
				int counterForCompared = 0;
				for (String isbn : comparedUsersBooksISBNList) {
					// Öneri listesinde olmayan ve mevcut kullanıcının sahip olmadığı kitaplar kontrol edildi.
					if (!recommendationList.contains(isbn) && !rdb.checkUserHasTheBook(isbn, currentUsersID)) {
						// Counter arttırıldı.
						counterForCompared++;
						// Kitap öneri listesine eklendi.
						recommendationList.add(isbn);
					}
					
					// Önerilecek kitap sayısı sınırına ulaşıldıysa
					if(counterForCompared == sdb.fetchNumberOfBooksToBeRecommendedByEachUserFromDatabase()) {
						// Döngüden çıkıldı.
						break;
					}
				}
			}
		}
		// Öneri listesi döndürüldü.
		return recommendationList;
	}
	
	/**
	 * Bu metot, iki kullanıcı arasındaki Pearson korelasyon benzerliğini hesaplar. sharedBooksISBNs adında bir liste oluşturulur ve bu liste, iki kullanıcı arasında paylaşılan kitapların ISBN'lerini içerir. Eğer paylaşılan kitap yoksa (yani sharedBooksISBNs boşsa), benzerlik sıfır olarak döndürülür. currentUsersBookListForSharedBooks ve comparedUsersBookListForSharedBooks adında iki adet eşleştirme (mapping) oluşturulur. Bu eşleştirmeler, mevcut kullanıcının ve karşılaştırılan kullanıcının paylaşılan kitapların ISBN'lerine göre puanlarını içerir. Dört adet toplam değişkeni oluşturulur: sumUser1, sumUser2, sumOfUser1Pow2, sumOfUser2Pow2. Bu değişkenler, kullanıcıların puanları ve puanların karelerinin toplamını hesaplamak için kullanılır. sumOfUser1XUser2 adında bir değişken oluşturulur ve bu değişken, mevcut kullanıcının puanları ile karşılaştırılan kullanıcının puanlarının çarpımlarının toplamını tutar. sharedBooksISBNs içindeki her bir ISBN için; mevcut kullanıcının puanları toplamı ve puanların karelerinin toplamı hesaplanır ve mevcut kullanıcının puanları ile karşılaştırılan kullanıcının puanlarının çarpımlarının toplamı hesaplanır. comparedUsersBookListForSharedBooks içindeki her bir ISBN için; karşılaştırılan kullanıcının puanları toplamı ve puanların karelerinin toplamı hesaplanır. Korelasyonun pay kısmı (numeratorOfCorrelation) ve payda kısmı (denominatorOfCorrelation) hesaplanır. Payda kısmının karekökü alınır. Korelasyon değeri (correlation) hesaplanır. Korelasyon değeri döndürülür.
	 * @param currentUsersID Mevcut kullanıcının ID numarası
	 * @param comparedUsersID Karşılaştırılacak kullanıcının ID numarası
	 * @return İki kullanıcı arasındaki korelasyon katsayısı
	 */
	private double computePearsonCorrelationSimilarity(int currentUsersID, int comparedUsersID) {
		// İki kullanıcı arasında paylaşılan kitapların ISBN listesi çekildi.
		List<String> sharedBooksISBNs = rdb.fetchSharedBooksBetweenTwoUsers(currentUsersID, comparedUsersID);
		
		// Paylaşılan kitap yoksa
		if(sharedBooksISBNs.isEmpty()) {
			// Benzerlik sıfır olarak döndürüldü.
			return 0.0;
		}
		
		// Mevcut kullanıcının paylaşılan kitapların listesi ve puanları eşleştirmesi oluşturuldu.
		Map<String, Integer> currentUsersBookListForSharedBooks = new HashMap<String, Integer>();
		// Karşılaştırılan kullanıcının paylaşılan kitapların listesi ve puanları eşleştirmesi oluşturuldu.
		Map<String, Integer> comparedUsersBookListForSharedBooks = new HashMap<String, Integer>();
		
		for (String isbn : sharedBooksISBNs) {
			// Mevcut kullanıcının paylaşılan kitaplarının puanları çekildi ve eşleştirmeye eklendi.
			currentUsersBookListForSharedBooks.put(isbn, rdb.fetchUsersRatingFromDatabaseWithISBN(isbn, currentUsersID));
			// Karşılaştırılan kullanıcının paylaşılan kitaplarının puanları çekildi ve eşleştirmeye eklendi.
			comparedUsersBookListForSharedBooks.put(isbn, rdb.fetchUsersRatingFromDatabaseWithISBN(isbn, comparedUsersID));
		}
		
		// Mevcut kullanıcının puanları toplamı
		double sumUser1 = 0.0; 
		// Karşılaştırılan kullanıcının puanları toplamı
		double sumUser2 = 0.0;
		// Mevcut kullanıcının puanlarının karesi toplamı
		double sumOfUser1Pow2 = 0.0;
		// Karşılaştırılan kullanıcının puanlarının karesi toplamı
		double sumOfUser2Pow2 = 0.0;
		// Mevcut kullanıcının puanları ile karşılaştırılan kullanıcının puanlarının çarpımlarının toplamı
		double sumOfUser1XUser2 = 0.0;
		// Ortak kitapların sayısı
		int n = sharedBooksISBNs.size();
		
		for (String key : currentUsersBookListForSharedBooks.keySet()) {
			// Mevcut kullanıcının puanları toplamı hesaplandı.
			sumUser1 += currentUsersBookListForSharedBooks.get(key);
			// Mevcut kullanıcının puanlarının karesi toplamı hesaplandı.
			sumOfUser1Pow2 += Math.pow(currentUsersBookListForSharedBooks.get(key), 2);
			// Mevcut kullanıcının puanları ile karşılaştırılan kullanıcının puanlarının çarpımlarının toplamı hesaplanıyor.
			sumOfUser1XUser2 += currentUsersBookListForSharedBooks.get(key) * comparedUsersBookListForSharedBooks.get(key);
		}
		
		for (String key : comparedUsersBookListForSharedBooks.keySet()) {
			// Karşılaştırılan kullanıcının puanları toplamı hesaplandı.
			sumUser2 += comparedUsersBookListForSharedBooks.get(key);
			// Karşılaştırılan kullanıcının puanlarının karesi toplamı hesaplandı.
			sumOfUser2Pow2 += Math.pow(comparedUsersBookListForSharedBooks.get(key), 2);
		}
		
		// Korelasyonun pay kısmı hesaplandı.
		double numeratorOfCorrelation = ((n * sumOfUser1XUser2) - (sumUser1 * sumUser2));
		// Korelasyonun payda kısmı hesaplandı.
		double denominatorOfCorrelation = (n * sumOfUser1Pow2 - Math.pow(sumUser1, 2)) * (n * sumOfUser2Pow2 - Math.pow(sumUser2, 2));
		
		double correlation = 0.0;
		
		// Eğer payda 0 çıkmazsa
		if (denominatorOfCorrelation != 0) {
			// Payda kısmının karekökü alındı.
		    denominatorOfCorrelation = Math.sqrt(denominatorOfCorrelation);
		    // Korelasyon hesaplandı.
		    correlation = numeratorOfCorrelation / denominatorOfCorrelation;
		    System.out.println(correlation);
		    // Korelasyon döndürüldü.
		    return correlation;
		} else {
		    return 0.0;
		}
	}
}
