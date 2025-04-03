import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

// BalanceDisplayable Interface (Bakiye Görüntüleme)
interface BalanceDisplayable {
    void displayBalance();
    void depositBalance(double amount);
}

// RouteDisplayable Interface (Geçiş Güzergahlarını Görüntüleme)
interface RouteDisplayable {
    void displayPassages();
}

// Abstract HGSAccount Class (Soyut HGS Hesap Sınıfı)
abstract class HGSAccount implements BalanceDisplayable, RouteDisplayable {
    protected String firstName;
    protected String lastName;
    protected String id;
    protected String vehicleType;
    protected double balance;
    protected ArrayList<Passage> passageHistory = new ArrayList<>();

    public HGSAccount(String firstName, String lastName, String id, String vehicleType, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.vehicleType = vehicleType;
        this.balance = balance;
    }

    @Override
    public void displayBalance() {
        System.out.println(firstName + " " + lastName + " - " + vehicleType);
        System.out.println("Kimlik: " + id);
        System.out.println("Hesaptaki bakiye: " + balance + " TL\n");
    }

    @Override
    public void depositBalance(double amount) {
        balance += amount;
        System.out.println("Hesaba " + amount + " TL eklendi. Yeni bakiye: " + balance + " TL\n");
    }

    public abstract void handlePassage(Passage passage);

    @Override
    public void displayPassages() {
        System.out.println("Geçiş geçmişi:");
        for (Passage passage : passageHistory) {
            System.out.println(passage.getPassageInfo());
        }
        System.out.println();
    }
}

// Abstract HGSPassage Class (Soyut Geçiş Sınıfı)
abstract class HGSPassage {
    protected Date passageDate;
    protected String startPoint;
    protected String endPoint;
    protected double passageFee;

    public HGSPassage(Date passageDate, String startPoint, String endPoint, double passageFee) {
        this.passageDate = passageDate;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.passageFee = passageFee;
    }

    public double getFee() {
        return passageFee;
    }

    public String getPassageInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Tarih: " + dateFormat.format(passageDate) + ", Başlangıç: " + startPoint + ", Varış: " + endPoint + ", Ücret: " + passageFee + " TL";
    }

    public abstract void processPassage(HGSAccount account);
}

// IndividualHGSAccount Class (Bireysel HGS Hesabı Sınıfı)
class IndividualHGSAccount extends HGSAccount {

    public IndividualHGSAccount(String firstName, String lastName, String id, String vehicleType, double balance) {
        super(firstName, lastName, id, vehicleType, balance);
    }

    @Override
    public void handlePassage(Passage passage) {
        if (balance >= passage.getFee()) {
            balance -= passage.getFee();
            passageHistory.add(passage);
            System.out.println(passage.getFee() + " TL geçiş ücreti alındı. Yeni bakiye: " + balance + " TL");
        } else {
            System.out.println("Geçiş yapılamadı: Yetersiz bakiye.");
        }
        System.out.println("Geçiş kaydı: " + passage.getPassageInfo() + "\n");
    }
}

// Passage Class (Geçiş Sınıfı)
class Passage extends HGSPassage {

    public Passage(Date passageDate, String startPoint, String endPoint, double passageFee) {
        super(passageDate, startPoint, endPoint, passageFee);
    }

    @Override
    public void processPassage(HGSAccount account) {
        if (account.balance >= this.passageFee) {
            account.balance -= this.passageFee;
            System.out.println("Geçiş tamamlandı. Alınan ücret: " + this.passageFee + " TL.");
        } else {
            System.out.println("Geçiş başarısız: Yetersiz bakiye.");
        }
    }
}

// Main Class
public class main {
    public static void main(String[] args) {
        // Yeni bireysel hesap oluşturma
        IndividualHGSAccount user = new IndividualHGSAccount("Mehmet", "Kaya", "987654321", "Otomobil", 120.0);

        // Bakiye görüntüleme
        user.displayBalance();

        // Bakiye artırma
        user.depositBalance(80.0);

        // Geçiş işlemleri
        Passage passage1 = new Passage(new Date(2024 - 1900, 9, 18), "İzmir", "İstanbul", 35.0);
        user.handlePassage(passage1);

        Passage passage2 = new Passage(new Date(2024 - 1900, 9, 19), "İstanbul", "Ankara", 45.0);
        user.handlePassage(passage2);

        Passage passage3 = new Passage(new Date(2024 - 1900, 9, 20), "Ankara", "Bursa", 30.0);
        user.handlePassage(passage3);

        // Geçmişteki tüm geçişlerin görüntülenmesi
        user.displayPassages();
    }
}
