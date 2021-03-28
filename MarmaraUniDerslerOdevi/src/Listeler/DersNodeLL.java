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
            if (pointer.getDersKodu() == dersKodu){

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

}
