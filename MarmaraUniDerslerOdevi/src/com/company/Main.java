package com.company;

import Listeler.DersNodeLL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Oguz Evirgen
 * @since 26.03.2021
 * @see Main test sınıfım programın çalışacağı main methodunu içerir.
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DersNodeLL dersler = new DersNodeLL();
        dersler.Add(new DersNode("blm1001", "Bilgisayar Mühendisliğine Giriş", 1));
        dersler.Add(new DersNode("blm1003", "Algoritma ve Programlamaya Giriş", 1));
        dersler.Add(new DersNode("fzk1071", "Fizik 1", 1));
        dersler.Add(new DersNode("kmy1020", "Kimya", 1));
        dersler.Add(new DersNode("mat1085", "Matematik 1", 1));
        dersler.Add(new DersNode("mat1087", "Lineer Cebir", 1));
        dersler.Add(new DersNode("trd121", "Türk Dili 1", 1));
        dersler.Add(new DersNode("ydzx121", "Yabanci Dil", 1));

        dersler.Add(new DersNode("blm1002", "Bilgisayar Programlama 1", 2));
        dersler.Add(new DersNode("blm1004", "Bilgisayar Donanımı", 2));
        dersler.Add(new DersNode("fzk1072", "Fizik 2", 2));
        dersler.Add(new DersNode("isg1081", "İş Sağlığı Ve Güvenliği", 2));
        dersler.Add(new DersNode("mat1086", "Matematik 2", 2));
        dersler.Add(new DersNode("tf1020", "Bilimsel Araştırma ve Sunum Teknikleri", 2));
        dersler.Add(new DersNode("ydzx122", "Yabancı Dil 2", 2));


        dersler.writeList();


        System.out.println("********************************************");
        int islem;

        System.out.println(" 1 - Ders kodu ile ders çağırma");
        System.out.println(" 2 - Seçilen Sömestrdaki dersleri çağırma");
        System.out.println(" 3 - Seçilen Dönemler dahil olmak üzere aradaki tüm dersleri cağırma");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Hangi işlemi yapmak istiyorsunuz : ");

        islem = scanner.nextInt();


        switch (islem){
            case 1:
                String dersKodu;
                System.out.println("Aramak istediğiniz dersin kodunuz Giriniz (blm1003)");
                dersKodu = scanner.next();
                DersNode dersNode = dersler.getByCode(dersKodu);
                System.out.println(dersNode);
                break;
            case 2:
                int donem;
                System.out.println("Hangi Dönemin Derslerini Görmek istersiniz");
                donem = scanner.nextInt();
                ArrayList<DersNode> donemDersleri = (ArrayList<DersNode>) dersler.listSemesterCourses(donem);
                for (DersNode ders : donemDersleri){
                    System.out.println(ders.toString());
                }

                break;
            case 3:
                int ilk, son;
                System.out.println("Dönem aralığını giriniz önce ilk sonra son şeklinde");
                ilk = scanner.nextInt();
                son = scanner.nextInt();

                ArrayList<DersNode> istenilenDersler = (ArrayList<DersNode>) dersler.getByRange(ilk, son);

                for (DersNode ders : istenilenDersler){
                    System.out.println(ders);
                }

                break;
            default:
                System.out.println("Geçersiz işlem seçildi");
        }

    }



}
