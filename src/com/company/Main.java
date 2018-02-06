package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/*
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@'~~~     ~~~`@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@'                     `@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@'                           `@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@'                               `@@@@@@@@@@@@@@@
@@@@@@@@@@@'                                   `@@@@@@@@@@@@@
@@@@@@@@@@'                                     `@@@@@@@@@@@@
@@@@@@@@@'                                       `@@@@@@@@@@@
@@@@@@@@@                          Aleks          @@@@@@@@@@@
@@@@@@@@'                      n,      i          `@@@@@@@@@@
@@@@@@@@                     _/ | _   Serhii       @@@@@@@@@@
@@@@@@@@                    /'  `'/                @@@@@@@@@@
@@@@@@@@a                 <~    .'  Przedstawiają a@@@@@@@@@@
@@@@@@@@@                 .'    |                 @@@@@@@@@@@
@@@@@@@@@a              _/      |                a@@@@@@@@@@@
@@@@@@@@@@a           _/      `.`.              a@@@@@@@@@@@@
@@@@@@@@@@@a     ____/ '   \__ | |______       a@@@@@@@@@@@@@
@@@@@@@@@@@@@a__/___/      /__\ \ \     \___.a@@@@@@@@@@@@@@@
@@@@@@@@@@@@@/  (___.'\_______)\_|_|        \@@@@@@@@@@@@@@@@
@@@@@@@@@@@@|\________                       ~~~~~\@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/

public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static String answer = "y";
    public static boolean details = false;
    public static ArrayList TD = new ArrayList<>();
    public static ArrayList Weight = new ArrayList<>();
    public static ArrayList TDOUT = new ArrayList<>();
    public static boolean progres = true;
    public static int lengthOfLine = 0;
    public static Random generator = new Random();

    public static void main(String[] args) {

        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>>--------- Sieć Neuronowa ---------<<<");
        System.out.println(">>>------ Inteligentny Magazyn ------<<<");
        System.out.println(">>>------- Propagacja wsteczna ------<<<");
        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>>------ Aleksander Sinkowski ------<<<");
        System.out.println(">>>--------- Serhii Rizychuk --------<<<");
        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>>------ Input: 4 ------------------<<<");
        System.out.println(">>>------ Output: 1 -----------------<<<");
        System.out.println(">>>------ Connects: 24 --------------<<<");
        System.out.println(">>>------ Hidden Layouts: 2 ---------<<<");
        System.out.println(">>>------ Hidden Neurons: 6 ---------<<<");
        System.out.println(">>>------ Learning items: 75 --------<<<");
        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>Wyświetlić szczegóły uruchamiania? y/n");
        System.out.print(">");

        answer = scan.next();
        if (answer.equals("y"))details = true;
        System.out.println();

/*
  |\      _,,,--,,_  ,)
  /,`.-'`'   -,  ;-;;'
 |,4-  ) )-,_ ) /\
'---''(_/--' (_/-'
>>>Loading data from .txt file to arraylist
*/
        ReadDataFile();
        if (progres == false)return;
        if (details==true)checkArrayListOfTestData();
        CreateWeight();
        if (details==true)checkWeight();
        System.out.println(">>Uczenie sieci...");
        System.out.println(">In progress...");


    }


    /*
      |\      _,,,--,,_  ,)
      /,`.-'`'   -,  ;-;;'
     |,4-  ) )-,_ ) /\
    '---''(_/--' (_/-'
    >>>Create weights list
    */
    private static void CreateWeight() {
        System.out.println(">>Generowanie 24 wag...");
        double tmp = 0;
        double tmp2 = 0;
        for(int i = 0; i<24; i++){
            tmp = generator.nextInt();
            if(tmp % 2 == 0) {
                tmp2 = generator.nextDouble();
            }else{
                tmp2 = generator.nextDouble() * -1;
            }
            Weight.add(tmp2);
        }
        System.out.println(">OK");
    }



    /*
      |\      _,,,--,,_  ,)
      /,`.-'`'   -,  ;-;;'
     |,4-  ) )-,_ ) /\
    '---''(_/--' (_/-'
    >>>Check weights list
    */
    private static void checkWeight() {
        System.out.println(">>Sprawdzanie wag: ");
        for(int i = 0; i<24; i++){
            if (i % 3 == 0)System.out.println();
            System.out.print("   " + Weight.get(i));
        }
        System.out.println();
        System.out.println(">>Koniec sprawdzania.");
    }


    /*
      |\      _,,,--,,_  ,)
      /,`.-'`'   -,  ;-;;'
     |,4-  ) )-,_ ) /\
    '---''(_/--' (_/-'
    >>>Checking ArrayList with explosive test data
    */
    private static void checkArrayListOfTestData() {
        System.out.println(">>Sprawdzanie listy tst...");
        int licz = 0;
        for(int i = 0; i<TD.size(); i++){
            if(licz == lengthOfLine){
                licz = 0;
                System.out.println();
            }
            if (licz!=0)System.out.print(" " + TD.get(i));
            licz++;
        }
        System.out.println();
        System.out.println(">>Koniec sprawdzania.");
    }


    /*
      |\      _,,,--,,_  ,)
      /,`.-'`'   -,  ;-;;'
     |,4-  ) )-,_ ) /\
    '---''(_/--' (_/-'
    >>>Loading data from .txt file to ArrayList
    */
    public static void ReadDataFile() {
        System.out.println(">>Rozpoczęto wczytywanie tst.txt...");
        try {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            s += "\\tst.txt";
            System.out.println(">>Ustalenie ścieżki: " + s);
            System.out.println(">OK");
            BufferedReader br = new BufferedReader(new FileReader(s));
            String line;
            System.out.println(">>Wczytywanie danych uczących...");
            while ((line = br.readLine()) != null) {
                String[] exploded = line.split(";");
                lengthOfLine = exploded.length;
                for(int i = 0; i<exploded.length; i++){
                    TD.add(exploded[i]);
                }
            }
            br.close();
            System.out.println(">OK");
            progres = true;
        } catch (Exception ex) {
            System.out.println(">-------------------------------<");
            System.out.println(">Błąd podczas wczytywania pliku!<");
            System.out.println(">" + ex);
            System.out.println(">-------------------------------<");
            progres = false;
        }
    }
}
