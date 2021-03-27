package model;

import com.company.Kategori;
import com.company.Kullanici;
import com.company.Oda;

import javax.lang.model.type.NullType;
import java.sql.*;
import java.util.ArrayList;


/**
 * @author Oğuz Evirgen
 * @class Veri Tabanı bağlantısını sağlandığım sınıf VeriKaynagi
 * @since 2021-03-12
 */


//Data Base ile iletişim bu sınıfta gerçekleştirilecek
public class VeriKaynagi {
    /**
     * alttaki büyük harflerle tanıtılmış değişkenlerin hepsi ileride veri tabanında bir değişiklik olması durumunda
     * koddaki tüm değişiklikleri tek tek değiştirmemek için kullanılmıştır
     * Buradaki değişkenlere atananların hepsi Tablo adı veya sutunlarının adlarıdır.
     * @param DB_NAME veri tabanı isimlendirmesi
     * @param CONNECTİON_STRING veri tabanı ile bağlantı kurmak için  kullandığım değişken sqlite kullandığım için jdbc:sqlite
     */
    public static final String DB_NAME = "otel.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    //Tablolar için ayrı ayrı değişkenler oluşturuyorum ki projemin ilerleyen aşamalarında
    //Tablo ismi ve ya sutun isimlerinde değişiklik olursa tek hareketle düzeltebilmek için


    //Odalar tablosu için
    public static final String TABLO_ODALAR = "Odalar";
    public static final String SUTUN_ODAID = "OdaId";
    public static final String SUTUN_ODALAR_KATEGORIID = "KategoriId";
    public static final String SUTUN_REZERVEMI = "RezerveMi";

    //Kategori Tablosu için
    public static final String TABLO_KATEGORILER = "Kategoriler";
    public static final String SUTUN_KATEGORIID = "KategoriId";
    public static final String SUTUN_KATEGORIADI = "KategoriAdi";
    public static final String SUTUN_GUNLUKFIYAT = "GunlukFiyat";

    //Kullanıcılar Tablosu için
    public static final String TABLO_KULLANICILAR = "Kullanicilar";
    public static final String SUTUN_KULLANICIID = "KullanıcıId";
    public static final String SUTUN_KULLANICIADI = "KullanıcıAdi";
    public static final String SUTUN_KULLANICISIFRE = "KullaniciSifre";
    public static final String SUTUN_KULLANICITURU = "KullaniciTuru";
    public static final String SUTUN_KULLANICI_ODAID = "Odaıd";

    /**
     * Connection baglantı nesnesi Veri tabanına bağlanmak için kullanıdığım Connection sınıfından oluşturulmuş nesne
     */
    private Connection baglanti;

    /**
     * veriTabanıAc metodum veritabanıyla işim başladığını bildirmek ve erişim sağlamak için kullandım
     * @return true false döndürerek veritabanının açılıp açılmadığını bildirir
     * @exception  e tabanı bağlantısında sorun çıkması durumunda exception döndürecekti
     */
    //Veri tabanına ulaşmak için bu fonksiyonu çalıştıracağım
    public boolean veriTabaniAc(){
        try{
            baglanti = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("VeriTabanına erişim sağlandı");
            return true;
        }catch (SQLException e){
            System.out.println("VeriTabanına Bağlanılamadı");
            return false;
        }
    }

    /**
     * veriTabaniniKapat metodum ise veri tabanı ile işim bittiğini bildirmek için kullandığım metod.
     * @exception e tabanı bağlantısında sorun çıkması durumunda exception döndürecekti
     */
    //Veri tabanını kapatmak için kullancağım fonksiyon bu.
    public void veriTabaniniKapat(){

        try{
            if(baglanti != null){
                System.out.println("Veri tabanı Kapandı");
                baglanti.close();
            }
        }catch (SQLException e){
            System.out.println("VeriTabanı kapatılamadı");
            e.printStackTrace();
        }
    }

    /**
     * Veri tabanından tüm kullanıcıları çekmek için kullandığım metod
     * @return veri tipi Kullanici olan arraylist döndürür
     */
    //Bu methodu Test klasımda cağırdığımda tum kullaniciları gösterebileceğim
    public ArrayList<Kullanici> tumKullanıcılarıGoster() {

        ArrayList<Kullanici> tumKullanicilar = new ArrayList<Kullanici>();

        try(Statement statement = baglanti.createStatement();
        ResultSet sonuc = statement.executeQuery("Select * from " + TABLO_KULLANICILAR )){



            while(sonuc.next()){
                Kullanici kullanici = new Kullanici();
                kullanici.setKullaniciId(sonuc.getInt(SUTUN_KULLANICIID));
                kullanici.setKullaniciAdi(sonuc.getString(SUTUN_KULLANICIADI));
                kullanici.setKullaniciSifre(sonuc.getString(SUTUN_KULLANICISIFRE));
                kullanici.setKullaniciTuru(sonuc.getInt(SUTUN_KULLANICI_ODAID));

                tumKullanicilar.add(kullanici); //Burada ise döndürceğim arrayliste databaseden çektiğim veriyi aktardım

            }

        } catch (SQLException e) {
            System.out.println("Sorgu Başarısız");
            e.printStackTrace();
            return null;
        }

        return tumKullanicilar;

    }

    /**
     * Veri tabanımdaki tüm odaları çekmek için kullandığım method
     * @return veri tipi oda olan arraylist döndürür
     */
    //Boş odaları göstermek için kullandığım method
    public ArrayList<Oda> odalariGoster(){

        ArrayList<Oda> odalar = new ArrayList<Oda>();

        try(Statement statement = baglanti.createStatement();
            ResultSet sonuc = statement.executeQuery("Select * from " + TABLO_ODALAR + " Inner Join " + TABLO_KATEGORILER + " On " + TABLO_ODALAR + ".KategoriId = " + TABLO_KATEGORILER + ".KategoriId")){



            while(sonuc.next()){
                Oda oda = new Oda();
                Kategori kategori = new Kategori();

                oda.setOdaId(sonuc.getInt(SUTUN_ODAID));
                oda.setKategoriId(sonuc.getInt(SUTUN_ODALAR_KATEGORIID));
                oda.setRezerveMi(sonuc.getInt(SUTUN_REZERVEMI));
                oda.setGunlukFiyat(sonuc.getInt(SUTUN_GUNLUKFIYAT));
                oda.setKategoriAdi(sonuc.getString(SUTUN_KATEGORIADI));

                odalar.add(oda); //Burada ise döndürceğim arrayliste databaseden çektiğim veriyi aktardım

            }

        } catch (SQLException e) {
            System.out.println("Sorgu Başarısız");
            e.printStackTrace();
            return null;
        }

        return odalar;

    }

    /**
     * Gönderilen oda nesnesinin boş olup olmadığına bakarak odaya kullanıcıyı yerleştirir.
     * @param oda gönderilen odanın özelliklerine erişmek için
     */
    //bu method ile gönderilen oda eğer boşşa ki boş oda göndereceğim
    //odanın rezervemi değişkenini 0 dan 1 e çekecek.
    public void odaRezerveEt(Oda oda){
        try {
            Statement statement = baglanti.createStatement();
            if(oda.getRezerveMi() == 0){
                statement.executeQuery("Update " + TABLO_ODALAR + " Set " + SUTUN_REZERVEMI + " = " + 1 + " Where " + SUTUN_ODAID + " = " + oda.getOdaId());
                //bu statement ile rezervemş 0 olan kısmı 1 yaptım nerede yaptım odaId ler birbiriyle eşleşiyorsa yaptım.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * metod overloading yaptım imzaları farklı olan 2. methodum
     * bu metod ile kullanıcının yaptığı oda rezerve işlemini yaptım
     * @param oda Oda nesnesi özelliklerini değişitrmek için
     * @param kullanici Kullanıcı nesnesi hangi kullanıcının rezerve ettiğini anlamak için
     */
    public void odaRezerveEt(Oda oda, Kullanici kullanici){
        try {
            Statement statement = baglanti.createStatement();
            statement.executeQuery("UPDATE " + TABLO_KULLANICILAR + " SET " + SUTUN_KULLANICI_ODAID + " = " + oda.getOdaId() + " Where " + SUTUN_KULLANICIID + " = " + kullanici.getKullaniciId() );
            statement.executeQuery("UPDATE " + TABLO_ODALAR + " SET " + SUTUN_REZERVEMI + " = " + 1 + " Where " + SUTUN_ODAID + " = " + oda.getOdaId());
            //ilk statemin ile kullanıcılar tablosunu ikinci ile odalar tablosundaki rezerve mi değişkenini güncelledim.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * İptal edilecek odayı boşaltmak için kullandığım method
     * @param oda iptal edilecek oda nesnesi
     */
    //bu method ile gönderilen oda eğer doluysa ki dolu oda göndereceğim
    //odanın rezervemi değişkenini 1 den 0 a çekecek (sqlite ta bool seçeneği bulamadığım için 0 ve 1 integer değerlerini kullanıyorum)
    public void odaIptalEt(Oda oda){
        try {
            Statement statement = baglanti.createStatement();
            if(oda.getRezerveMi() == 1){
                statement.executeQuery("UPDATE " + TABLO_ODALAR + " Set " + SUTUN_REZERVEMI + " = " + 0 + " Where " + SUTUN_ODAID + " = " + oda.getOdaId());
                statement.executeQuery("UPDATE  " + TABLO_KULLANICILAR + " SET " + SUTUN_KULLANICI_ODAID + " = " + null + " Where " + SUTUN_KULLANICI_ODAID + " = " + oda.getOdaId());
                //ilk statement ile odalar tablosunda iptal ettim ikinci statementta ise kullanıcılar tablosundaki odaıd sutununu null yaptım.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * resepsiyoonistin yaptığı check in işlemi için kullanıcıId sine göre kullanıcyı çektim
     * @param kullaniciId bu ıd ye göre veri tabanından verimi Kullanıcı verilerimi aldım
     * @return Kullanıcı nesnesi döndürdük.
     */
    public Kullanici checkInYapilacakMusteri(int kullaniciId) {
        Kullanici kullanici = new Kullanici(); //tek kullanıcım olacağı için burda yazmak bana bir sıkıntı yaratmaz

        try {
            Statement statement = baglanti.createStatement();
            ResultSet sonuc = statement.executeQuery("Select * from " + TABLO_KULLANICILAR + " WHERE " + SUTUN_KULLANICIID + " = " + kullaniciId);


            while(sonuc.next()){
                kullanici.setKullaniciId(sonuc.getInt(SUTUN_KULLANICIID));
                kullanici.setKullaniciAdi(sonuc.getString(SUTUN_KULLANICIADI));
                kullanici.setKullaniciTuru(sonuc.getInt(SUTUN_KULLANICITURU));
                kullanici.setOdaId(sonuc.getInt(SUTUN_KULLANICI_ODAID));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kullanici;
    }

    /**
     * bu metodumuzda ise yaptığımız kullanıcı ıdsine göre kullanıcılar tablosunda odaıd kısmını null a çevirmek
     * odaıd ile kullanıcılar tablosundaki odaid nin eşleşip eşleşmediğini kontrolunu yaptık
     * @param kullaniciId hangi kullanıcının check out yaptıgını anlamak için
     * @param odaId hangi odanın boşaldığı
     */
    public void checkOutYapilacakMusteri(int kullaniciId, int odaId) {
        try {
            Statement statement = baglanti.createStatement();
            statement.executeQuery("Update " + TABLO_KULLANICILAR + " Set " + SUTUN_KULLANICI_ODAID + " = " + null + " Where " + SUTUN_KULLANICIID + " = " + kullaniciId + " And " + SUTUN_KULLANICI_ODAID + " = " + odaId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
