# HGS Geçiş Yönetim Sistemi

## Açıklama
HGS geçişlerini, bakiye yönetimini ve geçiş geçmişini yöneten Java tabanlı bir sistemdir. Kullanıcılar, bakiye görüntüleme, para yatırma ve geçiş geçmişini takip etme işlemlerini yapabilirler.

## Özellikler
- Hesap bakiyesi görüntüleme
- Hesaba para yatırma
- Geçiş işlemlerini yönetme
- Geçiş geçmişini görüntüleme

## Sınıflar ve Arayüzler
- **BalanceDisplayable**: Bakiye görüntüleme ve para yatırma işlemleri için arayüz.
- **RouteDisplayable**: Geçiş güzergahlarını görüntüleme arayüzü.
- **HGSAccount**: HGS hesap detaylarını ve geçiş işlemlerini yöneten soyut sınıf.
- **HGSPassage**: Geçişleri temsil eden soyut sınıf.
- **IndividualHGSAccount**: Bireysel HGS hesabını temsil eden somut sınıf.
- **Passage**: Tek bir geçişi temsil eden somut sınıf.

## Gereksinimler
- Java 8 veya daha yeni sürüm

## Çalıştırma
1. Depoyu yerel makinenize klonlayın.
2. `main.java` dosyasını derleyin.
3. Sistemi görmek için `main` sınıfını çalıştırın.

## Lisans
Bu proje MIT Lisansı altında lisanslanmıştır.
