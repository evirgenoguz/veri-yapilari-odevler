package com.company;

public class DersNode {
    /**
     * @param dersKodu ders kodunu  tutar
     * @param dersAdi ders adını tutar
     * @param donem dersin hangi dönemde olduğu bilgisini tutar
     * @param next bağlantılı listedeki bir sonraki donemi tutar
     * @param previous bağlantılı listedeki bir önceki dönemi tutar
     */
    private String dersKodu;
    private String dersAdi;
    private int donem;
    public DersNode next;
    public DersNode previous;

    public DersNode(String dersKodu, String dersAdi, int donem) {
        this.dersKodu = dersKodu;
        this.dersAdi = dersAdi;
        this.donem = donem;
    }

    public String getDersKodu() {
        return dersKodu;
    }

    public void setDersKodu(String dersKodu) {
        this.dersKodu = dersKodu;
    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public int getDonem() {
        return donem;
    }

    public void setDonem(int donem) {
        this.donem = donem;
    }

    @Override
    public String toString() {
        return "Ders Kodu : " + dersKodu + " " +
                " Ders Adi : " + dersAdi +  " " +
                " Dönem : " + donem;
    }
}
