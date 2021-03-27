package com.company;

/**
 * bu sınıfımızda VeriTabanımızdaki Kullanıcılar tablosundaki kullanıcıların her biri için ayrı
 * nesne oluşturmak amacıyla oluşturulmuştur.
 *
 * değişkenlerimiz veri tabanımızdaki sutunlar ile eşleşir.
 *
 */

public class Kullanici {
    private int KullaniciId;
    private String KullaniciAdi;
    private String KullaniciSifre;
    private int KullaniciTuru; //Kullanıcı türü 0 için resepsiyonist 1 için müşteri
    private int OdaId;


    /**
     *
     * Getter ve Setter Methodlarımız
     */
    //Getter setter metodları ile private değişkenlerde işlem yapabilmek için
    public int getKullaniciId() {
        return KullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        KullaniciId = kullaniciId;
    }

    public String getKullaniciAdi() {
        return KullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        KullaniciAdi = kullaniciAdi;
    }

    public String getKullaniciSifre() {
        return KullaniciSifre;
    }

    public void setKullaniciSifre(String kullaniciSifre) {
        KullaniciSifre = kullaniciSifre;
    }

    public int getKullaniciTuru() {
        return KullaniciTuru;
    }

    public void setKullaniciTuru(int kullaniciTuru) {
        KullaniciTuru = kullaniciTuru;
    }

    public int getOdaId() {
        return OdaId;
    }

    public void setOdaId(int odaId) {
        OdaId = odaId;
    }
}


