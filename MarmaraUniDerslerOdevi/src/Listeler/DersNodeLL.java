package Listeler;

import com.company.DersNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @see DersNodeLL dersNode ların olduğu linked list
 */
public class DersNodeLL {
    /**
     * @param head değişkenim listenin basındaki elemanı tutacak.
     * @param last değişkenim listenin son elemanını tutucak
     */
    DersNode head;
    DersNode last;

    public DersNodeLL() {
        head = null;
        last = null;
    }

    /**
     *
     * @param dersNode linked liste ekleme yapacağım nesne
     */
    public void Add(DersNode dersNode){
        DersNode yeniDers = new DersNode(dersNode.getDersKodu(), dersNode.getDersAdi(), dersNode.getDonem());
        if (isEmpty()){
            //doluysa

            last.next = yeniDers;
            last.previous = last;
            last = yeniDers;
        }
        else{
            //boşsa
            head = yeniDers;
            last = yeniDers;
        }
    }

    /**
     *
     * @param index hangi indise eklemek istersiniz
     * @param dersNode listeye hangi nesneyi eklemek istersiniz
     */
    public void Add(int index, DersNode dersNode){
        DersNode yeniDers = new DersNode(dersNode.getDersKodu(), dersNode.getDersAdi(), dersNode.getDonem());
        if (isEmpty()){
            //doluysa
            if (index == 0){
                yeniDers.next = head;
                head = yeniDers;
            }
            else{
                DersNode pointer1 = null;
                DersNode pointer2 = head;
                int tempIndex = 0;
                while (pointer2 != null && tempIndex < index){
                    pointer1 = pointer2;
                    pointer2 = pointer2.next;
                    tempIndex++;
                }
                if (pointer2 == null){
                    //listenin sonuna ekleme
                    Add(yeniDers);
                }
                else{
                    yeniDers.next = pointer2;
                    pointer1.next = yeniDers;
                }
            }

        }
        else{
            //boşsa
            head = yeniDers;
            last = yeniDers;
        }
    }

    public void Remove(DersNode dersNode){
        if (dersNode == last){
            //son elemansa
            last = dersNode.previous;
            dersNode = null;
        }
        else if (dersNode == head){
            //ilk elemansa
            dersNode.next = head;
            dersNode = null;
        }
        else{
            //ara elemansa
            DersNode temp1 = null;
            DersNode temp2 = null;
            temp1 = dersNode.previous;
            temp2 = dersNode.next;
            dersNode = null;
            temp1.next = temp2;
            temp2.previous = temp1;
        }
    }

    public DersNode Next(DersNode dersNode){
        if(dersNode != last){
            return dersNode.next;
        }
        else {
            System.out.println("Son elemandan sonra eleman yok kardeş");
            return null;
        }
    }

    public int Size(){
        DersNode pointer = head;
        int sayac = 0;
        while (pointer != null){
            sayac++;
            pointer = pointer.next;
        }
        return sayac;
    }

    /**
     * @param isEmpty methodum listenin boş olup olmadığını kontrol eder
     * @return doluysa true boşsa false döndürür
     */
    public boolean isEmpty(){
        if(head != null){
            return true;
        }
        return false;
    }

    public void writeList(){
        DersNode pointer = head;
        while (pointer != null){
            System.out.println("Ders Kodu : " + pointer.getDersKodu() + "  Ders Adi : " + pointer.getDersAdi()  + " Dönem : " + pointer.getDonem());
            pointer = pointer.next;
        }
    }

    /**
     * @param dersKodu bize verilen dersin kodu1
     * @return verilen dersKodu ile eşlesen ders kodu varsa o DersNode nesnesini döndürür
     */
    public DersNode getByCode(String dersKodu){
        DersNode pointer = head;
        while (pointer != null){
            if (pointer.getDersKodu().equals(dersKodu)){
                return pointer;
            }
            pointer = pointer.next;
        }
        return null;
    }

    /**
     * @param donem hangi dönem için işlem yapılacak
     * @return verilen dönemin derslerini liste halinde döndürür
     */
    public List<DersNode> listSemesterCourses(int donem){
        DersNode pointer = head;
        List<DersNode> donemDersleri = new ArrayList<>();
        while (pointer != null){
            if (pointer.getDonem() == donem){
                donemDersleri.add(pointer);
            }
            pointer = pointer.next;
        }
        return donemDersleri;
    }

    /**
     * @param ilk hangi dönemler arasındanın başlangıcı
     * @param son hangi dönemler arasındanın bitişi
     * @return o dönemler arasındaki derslerin hepsini liste şeklinde döndürür.
     */
    public List<DersNode> getByRange(int ilk, int son){
        ArrayList<DersNode> istenilenDersler = new ArrayList<>();
        DersNode pointer = head;
        while (pointer != null){
            if(pointer.getDonem() >= ilk && pointer.getDonem() <= son){
                istenilenDersler.add(pointer);
            }
            pointer = pointer.next;
        }
        return istenilenDersler;
    }
}
