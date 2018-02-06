package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public static String answer;
    public static ArrayList TD = new ArrayList<>();
    public static boolean progres = true;
    public static int lengthOfLine = 0;

    public static void main(String[] args) {

        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>>--------- Sieć Neuronowa ---------<<<");
        System.out.println(">>>------ Inteligentny Magazyn ------<<<");
        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>>------ Aleksander Sinkowski ------<<<");
        System.out.println(">>>--------- Serhii Rizychuk --------<<<");
        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>Wczytywanie plików projektu...");
        System.out.println(">OK");
/*
  |\      _,,,--,,_  ,)
  /,`.-'`'   -,  ;-;;'
 |,4-  ) )-,_ ) /\
'---''(_/--' (_/-'
>>>Loading data from .txt file to arraylist
*/
        ReadDataFile();
        if (progres == false)return;
        checkArrayListOfTestData();

        System.out.println(">>Uczenie sieci...");
        System.out.println(">OK");
        System.out.println(">>System w pełni sprawny.");
        System.out.println(">>>");
        System.out.println(">>>");
        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>>      1 - Sprzety techniczne      <<<");
        System.out.println(">>>      2 - RTV/AGD                 <<<");
        System.out.println(">>>      3 - Odziez                  <<<");
        System.out.println(">>>      4 - Spozywcze               <<<");
        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>>Prosze podac nr kategorii produktu<<<");
        System.out.println(">>>----------------------------------<<<");

//        System.out.print(">Numer: ");
//        answer = scan.next();


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
            System.out.print(" " + TD.get(i));
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
    >>>Loading data from .txt file to arraylist
    */
    public static void ReadDataFile() {
        System.out.println(">>Wczytywanie danych uczących...");
        try {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            s += "\\tst.txt";
            System.out.println(">>>>Ustalenie ścieżki: " + s);
            System.out.println(">>>OK");
            BufferedReader br = new BufferedReader(new FileReader(s));
            String line;
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
