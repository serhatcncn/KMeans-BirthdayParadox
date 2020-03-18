package birthdayparadox;

import java.util.Random;
import java.util.Scanner;

public class BirthdayParadox {

    public static void main(String[] args) {
        int[] n = {50, 100, 250, 1000};
        int[] ay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] ayAd = {"Ocak   | ", "Şubat  | ", "Mart   | ", "Nisan  | ",
            "Mayıs  | ", "Haziran| ", "Temmuz | ", "Ağustos| ", "Eylül  | ",
            "Ekim   | ", "Kasım  | ", "Aralık | "};        
        Scanner scan = new Scanner(System.in);
        System.out.println("1)Birthday Paradox deneyi sonuçları.");
        System.out.println("2)Kasabadaki her 20. doğumun ikiz olma olasılığına göre Birthday Paradox deneyi sonuçları.");
        System.out.println("3)Çıkış.");
        System.out.print("(Lütfen seçiminizin numarasını giriniz): ");
        String secim = scan.nextLine();
        int ikizlik = 0;
        while(true){
            if(secim.equals("1")){}
            else if(secim.equals("2")){ikizlik = 1;}
            else if(secim.equals("3")){System.exit(0);}
            else{
                System.out.print("Hatalı giriş! Lütfen tekrar bir numara giriniz: ");
                secim = scan.nextLine();
                continue;
            }
            break;            
        }
        System.out.println("...");
        int cakismaMatris[][] = new int[21][4];
        double cakismaMatrisOrt[][] = new double[1][4];
        for(int i=0; i<n.length; i++){
            for(int j=0; j<20; j++){
                int[][] jaggedArr = jaggedArrOlusturma(ay);
                randomAyGun(jaggedArr, ay, n, i, ikizlik);
                System.out.println(n[i] + " KİŞİLİK KASABA");
                jaggedArrBastirma(jaggedArr, ayAd);
                System.out.println("BİR SONRAKİ DENEYİN SONUCU İÇİN 'ENTER' A BASINIZ...");
                scan.nextLine();
                matrisOlusturma(jaggedArr, ay, cakismaMatris, i, j);  
                cakismaMatris[20][i]+=cakismaMatris[j][i];
            }
            cakismaMatrisOrt[0][i] = (double)cakismaMatris[20][i]/20;
        }
        matrisBastirma(cakismaMatris, cakismaMatrisOrt);
    }
    public static int [][] jaggedArrOlusturma(int [] ay){
        int jaggedArr[][] = new int[ay.length][];
        for(int a=0; a<ay.length; a++){
            if(a==3 || a==5 || a==8 || a==10){
                jaggedArr[a] = new int[30];
                for(int x=0; x<30; x++){
                    jaggedArr[a][x] = 0;
                }
            }
            else if(a==1){
                jaggedArr[a] = new int[28];
                for(int x=0; x<28; x++){
                    jaggedArr[a][x] = 0;
                }
            }
            else{
                jaggedArr[a] = new int[31];
                for(int x=0; x<31; x++){
                    jaggedArr[a][x] = 0;
                }
            }
        }
        return jaggedArr;
    }
    public static int [][] randomAyGun(int [][] jaggedArr, int [] ay, int[] n,
            int i, int ikizlik){
        
        Random random = new Random();
        int randAy, randGun;
        for(int k=0; k<n[i]; k++){
            randAy = random.nextInt(ay.length);                    
            if(randAy==3 || randAy==5 || randAy==8 || randAy==10){
                randGun = random.nextInt(30);                    
            }
            else if(randAy==1){
                randGun = random.nextInt(28);
            }
            else{
                randGun = random.nextInt(31);
            }
            if(ikizlik == 1){
                if(k%20 == 0){
                    int ikizlikDurumu = random.nextInt(2);
                    if(ikizlikDurumu == 1){
                        jaggedArr[randAy][randGun]+=1;
                        k++;
                    }
                }
            }
            jaggedArr[randAy][randGun]+=1;                    
        }
        return jaggedArr;
    }
    public static void jaggedArrBastirma(int [][] jaggedArr, String[] ayAd){
        System.out.print("Günler |");
        for(int t=1; t<32; t++){
            if(t<10){System.out.print(" "+ t + "  ");}
            else{System.out.print(" "+ t + " ");}
        }
        System.out.println("");
        for(int x=0; x<132; x++){System.out.print("-");}
        System.out.println("");
        for(int y=0; y<jaggedArr.length; y++) {
            System.out.print(ayAd[y]);
            for (int z=0; z<jaggedArr[y].length; z++){
                if(jaggedArr[y][z] > 9){
                    System.out.print(jaggedArr[y][z] + "| ");
                }
                else{
                    System.out.print(jaggedArr[y][z] + " | ");
                }
            }
            System.out.println("");
            for(int x=0; x<132; x++){System.out.print("-");}
            System.out.println("");
        }
    }
    public static int [][] matrisOlusturma(int [][] jaggedArr, int [] ay,
        int cakismaMatris[][], int i, int j){
        for(int q=0; q<ay.length; q++){
            for(int w=0; w<jaggedArr[q].length; w++){
                if(jaggedArr[q][w]>1){
                    cakismaMatris[j][i]+=(jaggedArr[q][w]-1);
                }
            }
        }
        return cakismaMatris;
    }
    public static void matrisBastirma(int cakismaMatris[][], double cakismaMatrisOrt[][]){
        System.out.println("");
        System.out.println("       İSTATİSTİKLER       ");
        System.out.println("       -------------       ");
        System.out.println("50  |  100  |  250  |  1000");
        System.out.println("---------------------------");
        for(int b=0; b<(cakismaMatris.length-1); b++) { 
            for (int m=0; m<cakismaMatris[b].length; m++){
                if(m == 1 && cakismaMatris[b][m] < 10){
                    System.out.print(cakismaMatris[b][m] + "       ");
                }
                else{
                    System.out.print(cakismaMatris[b][m] + "      ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("        ORTALAMALAR        ");
        System.out.println("        -----------        ");
        for(int u=0; u<4; u++){System.out.print(cakismaMatrisOrt[0][u] + "   ");}
    }
}