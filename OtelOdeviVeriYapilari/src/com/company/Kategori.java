package com.company;

/**
 * bu sınıfımızda VeriTabanımızdaki Kategoriler tablosundaki kategorilerin her biri için ayrı
 * nesne oluşturmak amacıyla oluşturulmuştur.
 *
 * değişkenlerimiz veri tabanımızdaki sutunlar ile eşleşir.
 *
 */
public class Kategori {
    private int KategoriId;
    private String KategoriAdi;
    private int GunlukFiyat;


    /**
     * Getter ve setter methodlarımız
     */
    //Getter setter metodları ile private değişkenlerde işlem yapabilmek için
    public int getKategoriId() {
        return KategoriId;
    }

    public void setKategoriId(int kategoriId) {
        KategoriId = kategoriId;
    }

    public String getKategoriAdi() {
        return KategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        KategoriAdi = kategoriAdi;
    }

    public int getGunlukFiyat() {
        return GunlukFiyat;
    }

    public void setGunlukFiyat(int gunlukFiyat) {
        GunlukFiyat = gunlukFiyat;
    }
}
