package k.means;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KMeans {
    public static double[][] randomkmerkez(int kümesayısı,int özniteliksayisi){
        double[][] merkezkümem=new double[kümesayısı][özniteliksayisi];
        for (int i = 0; i < kümesayısı; i++) {
            for (int j = 0; j < özniteliksayisi; j++) {
                Random nokta1=new Random();
                double nokta=nokta1.nextInt(100);
                merkezkümem[i][j]=nokta;
            }
        }
        return merkezkümem;
    }
    public static double[][] randomkmerkezcicek(int kümesayısı,int özniteliksayisi){
        double[][] merkezkümem=new double[kümesayısı][özniteliksayisi];
        for (int i = 0; i < kümesayısı; i++) {
            for (int j = 0; j < özniteliksayisi; j++) {
                Random nokta1=new Random();
                double nokta=nokta1.nextInt(8);
                merkezkümem[i][j]=nokta;
            }
        }
        return merkezkümem;
    }
    public static double [] öklithesabı(double[][] noktalar,double[][] merkezler){
        double[] enkısauzaklık=new double[noktalar.length];
        
        for (int i = 0; i < noktalar.length; i++) {
            double min=merkezler.length*10000;
            for (int j = 0; j < merkezler.length; j++) {
                double farklar=0;
                for (int k = 0; k < noktalar[i].length; k++) {
            
                    farklar+=Math.pow((merkezler[j][k]-noktalar[i][k]), 2);
                    
                }
                if (farklar<min) {
                    min=farklar;
                    enkısauzaklık[i]=j;
                }
            }
        }
        return enkısauzaklık;
    }
    public static void kümelelemanları(double[][] noktalar,int kümesayısı,double[] uzaklıklar,int özniteliksayısı,double [][] merkez){
        for (int i = 0; i < kümesayısı; i++) {
            ArrayList<Integer> lol=kümebul(uzaklıklar, i);
            System.out.print((i+1)+". kümenin elemenları");
            System.out.println("");
            if (lol.size()==0) {
                System.out.println("Bu kümede veri bulunmamaktadır...");
            }
            for (int j = 0; j <lol.size(); j++) {
                System.out.println("");
                double farklar=0;
                System.out.print("Verinin öznitelikleri:");
//                System.out.println("listeler:"+lol.get(j));
                for (int k = 0; k < özniteliksayısı; k++) {
                    farklar+=Math.pow((merkez[i][k]-noktalar[lol.get(j)][k]), 2);
                    System.out.print(noktalar[lol.get(j)][k]+" , ");
                }System.out.print(" Verinin küme merkezine olan uzaklığı:"+Math.sqrt(farklar));
                System.out.println("");
            }System.out.println("");
        }
    }
    public static ArrayList kümebul(double[] uzaklıklar,int arg){
        ArrayList<Integer> list=new ArrayList<Integer>();
        for (int i = 0; i < uzaklıklar.length; i++) {
            if (uzaklıklar[i]==arg) {
                list.add(i);
            }
        }
        return list;
    }
    public static double[][] ağırlıkmerkezi(double[][] noktalar,int kümesayısı,double[] uzaklıklar,int özniteliksayısı){
        double[][] ağırlıkmrkz=new double[kümesayısı][özniteliksayısı];
        for (int i = 0; i < kümesayısı; i++) {
            ArrayList<Integer> lol=kümebul(uzaklıklar, i);
            for (int j = 0; j <özniteliksayısı; j++) {
                double toplam=0;
                for (int k = 0; k < lol.size(); k++) {
                    toplam+=noktalar[lol.get(k)][j];
                    ağırlıkmrkz[i][j]=toplam/lol.size();
                }
            }
        }
        return ağırlıkmrkz;
    }
    
    public static boolean güncelleme(double[][] eskimerkez,double[][] yenimerkez){
        boolean değişim=false;
        for (int i = 0; i < eskimerkez.length; i++) { 
            for (int j = 0; j < yenimerkez[i].length; j++) {
                if (!değişim&&eskimerkez[i][j]!=yenimerkez[i][j]) {
                    eskimerkez[i][j]=yenimerkez[i][j];  
                    değişim=true;
                }else if (eskimerkez[i][j]!=yenimerkez[i][j]) {
                    eskimerkez[i][j]=yenimerkez[i][j];  
                }
            }
        }return değişim;
    }
    public static void main(String[] args) {
        System.out.println("VERİ KÜMELEME PROGRAMINA HOŞGELDİNİZ!!!");
        Scanner scanner=new Scanner(System.in);
        System.out.println("Sosyal Ağ kümelemek için 1'e basınız:");
        System.out.println(" Çiçek kümelemek için 2'ye basınız:");
        System.out.println("Çıkış yapmak için 3'e basınız:");
        int secim=scanner.nextInt();
        double[][] noktalar={{90,3,5},{53,37,35},{48,52,40},{85,7,2},{5,55,4},{93,8,25},
            {11,75,6},{8,9,80},{5,19,80},{9,3,91}};
        if (secim==3) {
            System.exit(0);
        }double [][] cicekverileri = 
        {
            {5.1,3.5,1.4,0.2},
            {4.9,3.0,1.4,0.2},
            {4.7,3.2,1.3,0.2},
            {4.6,3.1,1.5,0.2},
            {5.0,3.6,1.4,0.2},
            {5.4,3.9,1.7,0.4},
            {4.6,3.4,1.4,0.3},
            {5.0,3.4,1.5,0.2},
            {4.4,2.9,1.4,0.2},
            {4.9,3.1,1.5,0.1},
            {5.4,3.7,1.5,0.2},
            {4.8,3.4,1.6,0.2},
            {4.8,3.0,1.4,0.1},
            {4.3,3.0,1.1,0.1},
            {5.8,4.0,1.2,0.2},
            {5.7,4.4,1.5,0.4},
            {5.4,3.9,1.3,0.4},
            {5.1,3.5,1.4,0.3},
            {5.7,3.8,1.7,0.3},
            {5.1,3.8,1.5,0.3},
            {5.4,3.4,1.7,0.2},
            {5.1,3.7,1.5,0.4},
            {4.6,3.6,1.0,0.2},
            {5.1,3.3,1.7,0.5},
            {4.8,3.4,1.9,0.2},
            {5.0,3.0,1.6,0.2},
            {5.0,3.4,1.6,0.4},
            {5.2,3.5,1.5,0.2},
            {5.2,3.4,1.4,0.2},
            {4.7,3.2,1.6,0.2},
            {4.8,3.1,1.6,0.2},
            {5.4,3.4,1.5,0.4},
            {5.2,4.1,1.5,0.1},
            {5.5,4.2,1.4,0.2},
            {4.9,3.1,1.5,0.1},
            {5.0,3.2,1.2,0.2},
            {5.5,3.5,1.3,0.2},
            {4.9,3.1,1.5,0.1},
            {4.4,3.0,1.3,0.2},
            {5.1,3.4,1.5,0.2},
            {5.0,3.5,1.3,0.3},
            {4.5,2.3,1.3,0.3},
            {4.4,3.2,1.3,0.2},
            {5.0,3.5,1.6,0.6},
            {5.1,3.8,1.9,0.4},
            {4.8,3.0,1.4,0.3},
            {5.1,3.8,1.6,0.2},
            {4.6,3.2,1.4,0.2},
            {5.3,3.7,1.5,0.2},
            {5.0,3.3,1.4,0.2},
            {7.0,3.2,4.7,1.4},
            {6.4,3.2,4.5,1.5},
            {6.9,3.1,4.9,1.5},
            {5.5,2.3,4.0,1.3},
            {6.5,2.8,4.6,1.5},
            {5.7,2.8,4.5,1.3},
            {6.3,3.3,4.7,1.6},
            {4.9,2.4,3.3,1.0},
            {6.6,2.9,4.6,1.3},
            {5.2,2.7,3.9,1.4},
            {5.0,2.0,3.5,1.0},
            {5.9,3.0,4.2,1.5},
            {6.0,2.2,4.0,1.0},
            {6.1,2.9,4.7,1.4},
            {5.6,2.9,3.6,1.3},
            {6.7,3.1,4.4,1.4},
            {5.6,3.0,4.5,1.5},
            {5.8,2.7,4.1,1.0},
            {6.2,2.2,4.5,1.5},
            {5.6,2.5,3.9,1.1},
            {5.9,3.2,4.8,1.8},
            {6.1,2.8,4.0,1.3},
            {6.3,2.5,4.9,1.5},
            {6.1,2.8,4.7,1.2},
            {6.4,2.9,4.3,1.3},
            {6.6,3.0,4.4,1.4},
            {6.8,2.8,4.8,1.4},
            {6.7,3.0,5.0,1.7},
            {6.0,2.9,4.5,1.5},
            {5.7,2.6,3.5,1.0},
            {5.5,2.4,3.8,1.1},
            {5.5,2.4,3.7,1.0},
            {5.8,2.7,3.9,1.2},
            {6.0,2.7,5.1,1.6},
            {5.4,3.0,4.5,1.5},
            {6.0,3.4,4.5,1.6},
            {6.7,3.1,4.7,1.5},
            {6.3,2.3,4.4,1.3},
            {5.6,3.0,4.1,1.3},
            {5.5,2.5,4.0,1.3},
            {5.5,2.6,4.4,1.2},
            {6.1,3.0,4.6,1.4},
            {5.8,2.6,4.0,1.2},
            {5.0,2.3,3.3,1.0},
            {5.6,2.7,4.2,1.3},
            {5.7,3.0,4.2,1.2},
            {5.7,2.9,4.2,1.3},
            {6.2,2.9,4.3,1.3},
            {5.1,2.5,3.0,1.1},
            {5.7,2.8,4.1,1.3},
            {6.3,3.3,6.0,2.5},
            {5.8,2.7,5.1,1.9},
            {7.1,3.0,5.9,2.1},
            {6.3,2.9,5.6,1.8},
            {6.5,3.0,5.8,2.2},
            {7.6,3.0,6.6,2.1},
            {4.9,2.5,4.5,1.7},
            {7.3,2.9,6.3,1.8},
            {6.7,2.5,5.8,1.8},
            {7.2,3.6,6.1,2.5},
            {6.5,3.2,5.1,2.0},
            {6.4,2.7,5.3,1.9},
            {6.8,3.0,5.5,2.1},
            {5.7,2.5,5.0,2.0},
            {5.8,2.8,5.1,2.4},
            {6.4,3.2,5.3,2.3},
            {6.5,3.0,5.5,1.8},
            {7.7,3.8,6.7,2.2},
            {7.7,2.6,6.9,2.3},
            {6.0,2.2,5.0,1.5},
            {6.9,3.2,5.7,2.3},
            {5.6,2.8,4.9,2.0},
            {7.7,2.8,6.7,2.0},
            {6.3,2.7,4.9,1.8},
            {6.7,3.3,5.7,2.1},
            {7.2,3.2,6.0,1.8},
            {6.2,2.8,4.8,1.8},
            {6.1,3.0,4.9,1.8},
            {6.4,2.8,5.6,2.1},
            {7.2,3.0,5.8,1.6},
            {7.4,2.8,6.1,1.9},
            {7.9,3.8,6.4,2.0},
            {6.4,2.8,5.6,2.2},
            {6.3,2.8,5.1,1.5},
            {6.1,2.6,5.6,1.4},
            {7.7,3.0,6.1,2.3},
            {6.3,3.4,5.6,2.4},
            {6.4,3.1,5.5,1.8},
            {6.0,3.0,4.8,1.8},
            {6.9,3.1,5.4,2.1},
            {6.7,3.1,5.6,2.4},
            {6.9,3.1,5.1,2.3},
            {5.8,2.7,5.1,1.9},
            {6.8,3.2,5.9,2.3},
            {6.7,3.3,5.7,2.5},
            {6.7,3.0,5.2,2.3},
            {6.3,2.5,5.0,1.9},
            {6.5,3.0,5.2,2.0},
            {6.2,3.4,5.4,2.3},
            {5.9,3.0,5.1,1.8}
        };

        while (secim==1||secim==2) {    
            System.out.println("Kaç farklı kümeye ayırmak istersiniz:");
                int kümesayisi=scanner.nextInt();
            if (secim==1) {               
                double[][] rmerkez=randomkmerkez(kümesayisi, 3);
                double[] enkısauzaklık=öklithesabı(noktalar, rmerkez);
                double[][] ağırlıkmerkezi=ağırlıkmerkezi(noktalar, kümesayisi, enkısauzaklık, 3);
                boolean dogvumu=güncelleme(rmerkez, ağırlıkmerkezi);
                while (dogvumu) {            
                    double[] ksıauzkalık=öklithesabı(noktalar, rmerkez);
                    double[][] ağırlıkmerkez=ağırlıkmerkezi(noktalar, kümesayisi, ksıauzkalık, 3);
                    dogvumu=güncelleme(rmerkez, ağırlıkmerkez);
                    
                }kümelelemanları(noktalar, kümesayisi, enkısauzaklık, 3, rmerkez);
            }
            else if (secim==2) {
                double[][] rmerkez=randomkmerkezcicek(kümesayisi, 4);
                double[] enkısauzaklık=öklithesabı(cicekverileri, rmerkez);
                double[][] ağırlıkmerkezi=ağırlıkmerkezi(cicekverileri, kümesayisi, enkısauzaklık, 4);
                boolean dogvumu=güncelleme(rmerkez, ağırlıkmerkezi);
                while (dogvumu) {            
                    double[] ksıauzkalık=öklithesabı(cicekverileri, rmerkez);
                    double[][] ağırlıkmerkez=ağırlıkmerkezi(cicekverileri, kümesayisi, ksıauzkalık, 4);
                    dogvumu=güncelleme(rmerkez, ağırlıkmerkez);
                }kümelelemanları(cicekverileri, kümesayisi, enkısauzaklık, 4, rmerkez);
                double hata=0;
                for (int i = 0; i < kümesayisi; i++) {
                    ArrayList<Integer> elemansay=kümebul(enkısauzaklık, i);
                    if (elemansay.size()>50) {
                        hata+=((elemansay.size()-50)/150.0)*100;
                    }
                }
                System.out.printf("Çiçek verisetinin hata oranı:"+"%.2f  ",hata);                
            }System.out.println("VERİ KÜMELEME PROGRAMINA HOŞGELDİNİZ!!!");
            System.out.println("Sosyal Ağ kümelemek için 1'e basınız:");
            System.out.println(" Çiçek kümelemek için 2'ye basınız:");
            System.out.println("Çıkış yapmak için 3'e basınız:");
            secim=scanner.nextInt();
        }
    }
}
