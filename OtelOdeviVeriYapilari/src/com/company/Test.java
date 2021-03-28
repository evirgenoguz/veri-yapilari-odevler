package com.company;

import model.VeriKaynagi;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Test sınıfımız olarak oluşturdum fakat aslında tüm iş kodlarım bu sınıf altındaki metodlarda oldu
 * kod refactor edilirse ilk düzenleceğim bu sınıf ayrımı olacaktır.
 */



public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //veri aynağı clasından bir nesne üretip veri tabanı işlemlerini
        //burada yapabilirim ama bunları daha sonra ayrı bir class a alıcam

        VeriKaynagi veriKaynagi = new VeriKaynagi();

        veriKaynagi.veriTabaniAc(); //Veri tabanına erişim sağladık

        System.out.println("Dirmil Otel'e Hoşgeldiniz");

        //Giriş için kullanılan değişkenler
        System.out.println("Lütfen Giriş Yapınız");
        System.out.println("Kullanıcı Adı : ");
        String girisKullaniciAdi = scanner.nextLine();
        System.out.println("Şifre Giriniz");
        String girisKullaniciSifre = scanner.nextLine();
        //Bu değişkenleri veri tabanındakilerle karşılaştıracağım
        //veri tabanından getirdiğim kullanıcılar
        ArrayList<Kullanici> tumKullanicilar = veriKaynagi.tumKullanıcılarıGoster();


        Kullanici girisYapanKullanici = null; //sistemdeki kullanici olarak kullanacağım

        //giriş yapan kullanıcıyı bulmak için veri tabanını tarama işlemi
        for (Kullanici kullanici : tumKullanicilar){
            //System.out.println("Deneme");
            if(kullanici.getKullaniciAdi().equals(girisKullaniciAdi) && kullanici.getKullaniciSifre().equals(girisKullaniciSifre) ){
                //System.out.println("Deneme2");
                girisYapanKullanici = kullanici;
                System.out.println("Hoşgeldiniz " + kullanici.getKullaniciAdi());
                System.out.println(kullanici.getKullaniciTuru()); //deneme burada sorun yok
            }
        }
        /**
         * @param musteriSecenegi musteri nesnesinin seçtiği seçenek arayüz olmadığı için
         * @param resepsiyonSecenegi resepsiyonistin seçtiği seçenek arayüz olmadığı için
         */
        int musteriSecenegi;
        int resepsiyonSecenegi;

        //kullanıcıların yapabileceği işlemler
        if(girisYapanKullanici.getKullaniciTuru() == 0){
            System.out.println("Yapmak istediğiniz işlemi seçiniz ");
            System.out.println("1 - Oda Rezerve Et");
            System.out.println("2 - Oda İptal Et");
            System.out.println("3 - Check - in Yap");
            System.out.println("4 - Check - out Yap");
            System.out.println("5 - Çıkış");

            resepsiyonSecenegi = scanner.nextInt();

            switch (resepsiyonSecenegi) {
                case 1:
                    System.out.println("Oda rezerve işlemi yapılıyor Resepsiyon");
                    Oda secilenBosOda = bosOdalariGoster(veriKaynagi); //seçilen odayı methodumuz ile geri döndürdük şimdi veri tabanındakini işlemini yapacağız
                    veriKaynagi.odaRezerveEt(secilenBosOda); //bu method ile rezerve mi durumu değişecek
                    System.out.println("Oda Rezerve Edildi");
                    break;
                case 2:
                    System.out.println("Oda iptal işlemi yapılıyor Resepsiyon");
                    Oda secilenDoluOda =  doluOdalariGoster(veriKaynagi);
                    veriKaynagi.odaIptalEt(secilenDoluOda);
                    System.out.println("Oda İptal Edildi");
                    break;
                case 3:
                    System.out.println("Check - in işlemi yapılıyor");
                    System.out.println("Check in yapacak Kullanıcı Id : "); //resepsiyonist işlemi yapacağı için ıd ile aramayı tercih ettim
                    int checkInKullaniciId = scanner.nextInt();
                    checkIn(checkInKullaniciId, veriKaynagi);
                    break;
                case 4:
                    System.out.println("Check - out işlemi yapılıyor");
                    System.out.println("Check out yapacak Kullanici Id : ");
                    int checkOutKullaniciId =  scanner.nextInt();
                    System.out.println("Check out yapacak Kullanicinin Oda ıd si : ");
                    int checkOutOdaId = scanner.nextInt();

                    checkOut(checkOutKullaniciId, checkOutOdaId, veriKaynagi);

                    break;
                case 5:
                    System.out.println("Çıkış Yapıldı");
                    veriKaynagi.veriTabaniniKapat(); //System.exit ten önce veritabanını kapatıyorum
                    System.exit(0);
                    break;
            }


        }else if(girisYapanKullanici.getKullaniciTuru() == 1){
            System.out.println("1 - Oda Rezerve Et");
            System.out.println("2 - Oda İptal Et");
            System.out.println("3 - Çıkış");

            musteriSecenegi = scanner.nextInt();

            switch (musteriSecenegi){
                case 1:
                    System.out.println("Müşteriler İçin Boş Odalar");
                    Oda secilenBosOdaMusteri = bosOdalariGoster(veriKaynagi);
                    veriKaynagi.odaRezerveEt(secilenBosOdaMusteri, girisYapanKullanici);
                    System.out.println("Oda Rezerve Edildi");
                    break;
                case 2:
                    System.out.println("İptal Etmek İstediğiniz Oda : ");
                    Oda iptalEdilecekOda = odamıGoster(girisYapanKullanici, veriKaynagi); //Oda nesnemi iptalEdilecekOda değişkenine atadıktan sonra VeriKaynagında Güncelleme işlemi yapıyorum
                    veriKaynagi.odaIptalEt(iptalEdilecekOda);
                    System.out.println("Oda İptal Edildi");
                    break;
                case 3:
                    System.out.println("Çıkış Yapıldı");
                    veriKaynagi.veriTabaniniKapat(); //System.exit ten önce veritabanını kapatıyorum
                    System.exit(0);
                    break;
            }

        }


        //tumOdalariGoster(veriKaynagi); //en başta verikaynağına erişip erişemediğimi test etmek için yazdım.


        veriKaynagi.veriTabaniniKapat(); //Veri tabanını program sonunda kapatıyorum.
    }

    /**
     * bu metod odasını iptal etmek isteyen müsteriye gösterilecek odaları önüne getirmek için kullanılan
     * @param girisYapanKullanici hangi kullanıcının odasını iptal etmek istediği
     * @param veriKaynagi veritabanı ile ilişki aynı nesne üzerinden sağlamak için
     * @return iptal edilecek oda nesnesini döndürür bu nesne daha sonra kullanıcıdan silinir
     */
    private static Oda odamıGoster(Kullanici girisYapanKullanici, VeriKaynagi veriKaynagi) {

        ArrayList<Oda> doluOdalar = veriKaynagi.odalariGoster();
        int i = 1;
        for(Oda oda : doluOdalar){
            if(oda.getOdaId() == girisYapanKullanici.getOdaId()){
                System.out.println(i + ". Oda Türü ve Fiyati : ");
                System.out.println(oda.getKategoriAdi() + "   " + oda.getGunlukFiyat());
                System.out.println("Durum : Dolu");
                System.out.println("*******************");
                i++;
            }
        }
        System.out.println("İptal Etmek İStediğiniz Odayı Seçin :");
        Scanner scanner = new Scanner(System.in);

        int iptalEdilecekOda = scanner.nextInt();


        return doluOdalar.get(iptalEdilecekOda);
    }

    /**
     * check out işlemi için kullanılacak metod
     * @param kullaniciId check out yapan kullanıcıya erişmek için
     * @param odaId check out yapılacak odaya erişmek için
     * @param veriKaynagi veri kaynağıyla iletişimi aynı nesne üzerinden sağlamak için
     */
    private static void checkOut(int kullaniciId, int odaId, VeriKaynagi veriKaynagi) {
        veriKaynagi.checkOutYapilacakMusteri(kullaniciId, odaId);
        System.out.println("Check out Başarılı.");

    }

    /**
     * check in işlemi yapılırken kullanılacak metod
     * @param kullaniciId check yapan kullanıcıya erişmek
     * @param veriKaynagi veritabanı ile ilişkiyi aynı nesne üzerinden sağlamak için
     */
    private static void checkIn(int kullaniciId, VeriKaynagi veriKaynagi) {
        Kullanici checkInYapilacakKullanici = veriKaynagi.checkInYapilacakMusteri(kullaniciId);
        if(checkInYapilacakKullanici.getKullaniciId() == kullaniciId){
            System.out.println("Check-in işlemi başarılı");
        }
    }

    /**
     * bu metodumda resepsiyonistin odayı iptal edebilmesi için, dolu odaların hepsini getirmek için kullandığım metod
     * @param veriKaynagi veri kaynağı ile ilişkiyi aynı nesne üzerinden sağlamak için
     * @return burada seçim yaparak iptal edilecek odayı geri döndürür bu daha sonra veri tabanından silinir
     */
    private static Oda doluOdalariGoster(VeriKaynagi veriKaynagi) {
        ArrayList<Oda> doluOdalar = veriKaynagi.odalariGoster();
        int i = 1;
        for(Oda oda : doluOdalar){
            if(oda.getRezerveMi() == 1){
                System.out.println(i + ". Oda Türü ve Fiyati : ");
                System.out.println(oda.getKategoriAdi() + "   " + oda.getGunlukFiyat());
                System.out.println("Durum : Dolu");
                System.out.println("*******************");
                i++;
            }
        }
        System.out.println("Seçmek istediğinizi Sırasıyla Seçiniz :");
        Scanner scanner = new Scanner(System.in);

        int secilenOda = scanner.nextInt();

        return doluOdalar.get(secilenOda - 1);
    }

    /**
     * resepsiyonistin müşteriye oda ayarladığı metod
     * @param veriKaynagi veri kaynağı ile ilişkiyi aynı nesne üzerinden sağlamak için
     * @return dönüş değeri olarak Oda nesnesi alır bu oda nesnesi müşteriyle ilişkilendirilir.
     */
    private static Oda bosOdalariGoster(VeriKaynagi veriKaynagi) {
        ArrayList<Oda> bosOdalar = veriKaynagi.odalariGoster();
        int i = 1;
        for (Oda oda : bosOdalar) {

            if (oda.getRezerveMi() == 0) {
                System.out.println(i + ". Oda Türü ve Fiyati : ");
                System.out.println(oda.getKategoriAdi() + "   " + oda.getGunlukFiyat());
                System.out.println("Durum : Boş");
                System.out.println("*******************");
                i++;
            }
        }
        System.out.println("Seçmek istediğinizi Sıra Numarasına Göre Seçiniz :");
        Scanner scanner = new Scanner(System.in);

        int secilenOda = scanner.nextInt();

        return bosOdalar.get(secilenOda - 1);

    }

    /**
     * en başta veri tabanımın düzgün çalışıp çalışmadığını kontrol ettiğim metod
     * @param veriKaynagi veri kaynağı ile ilişkiyi aynı nesne üzerinden sağlamak için
     */
    private static void tumOdalariGoster(VeriKaynagi veriKaynagi) {
        ArrayList<Oda> tumOdalar = veriKaynagi.odalariGoster();

        for (Oda oda : tumOdalar){
            System.out.println("Oda Türü ve Fiyati : ");
            System.out.println(oda.getKategoriAdi() + "   " + oda.getGunlukFiyat());
            if(oda.getRezerveMi() == 0){
                System.out.println("Durum : Boş");
            }
            else{
                System.out.println("Durum : Dolu");
            }

            System.out.println("*************");
        }
    }
}
