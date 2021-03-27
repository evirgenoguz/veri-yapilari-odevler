package com.company;

/**
 * bu sınıfımızda VeriTabanımızdaki Odalar tablosundaki odaların her biri için ayrı
 * nesne oluşturmak amacıyla oluşturulmuştur.
 *
 * değişkenlerimiz veri tabanımızdaki sutunlar ile eşleşir.
 *
 */

public class Oda {
    private int OdaId;
    private int KategoriId;
    private int RezerveMi; //sqlite da bool seçeneği bulamadığım için bunları int aldım
    private String KategoriAdi; //oda turu kategoriAdı değişkenine gelicek
    private int GunlukFiyat; //bu veriyi kategorideki günlük fiyatı joinle almak için kullanacağım

    /**
     * Getter ve setter methodlarımız
     */
    //Getter setter metodları ile private değişkenlerde işlem yapabilmek için
    public int getOdaId() {
        return OdaId;
    }

    public void setOdaId(int odaId) {
        OdaId = odaId;
    }

    public int getKategoriId() {
        return KategoriId;
    }

    public void setKategoriId(int kategoriId) {
        KategoriId = kategoriId;
    }

    public int getRezerveMi() {
        return RezerveMi;
    }

    public void setRezerveMi(int rezerveMi) {
        RezerveMi = rezerveMi;
    }

    public int getGunlukFiyat() {
        return GunlukFiyat;
    }

    public void setGunlukFiyat(int gunlukFiyat) {
        GunlukFiyat = gunlukFiyat;
    }

    public String getKategoriAdi() {
        return KategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        KategoriAdi = kategoriAdi;
    }
}
