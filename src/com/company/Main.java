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
@@@@@@@@@@@@@'    (☞ﾟヮﾟ)☞                   `@@@@@@@@@@@@@@@
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

    public static boolean details = true;
    public static boolean progres = true;

    public static ArrayList TD = new ArrayList<>(); // Testowe Dane wejściowe(uczące)
    public static ArrayList TDM = new ArrayList<>(); // Testowe Dane wyjsciowe (uczące)
    public static ArrayList Weight = new ArrayList<>(); // Wagi synaps
    public static ArrayList HLOne = new ArrayList<>(); // Wyniki w pierwszej warstwie ukrytej
    public static ArrayList HLTwo = new ArrayList<>(); // Wyniki w drugiej warstwie ukrytej
    public static ArrayList TDOUT = new ArrayList<>(); // Dane wyjściowe po przeliczeniu

    public static int lengthOfLine = 0;
    public static int lOW = 0;//length of weights
    public static int lOI = 1;//length of inputs

    public static Random generator = new Random();

    public static void main(String[] args) {

        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>>--------- Sieć Neuronowa ---------<<<");
        System.out.println(">>>------ Inteligentny Magazyn ------<<<");
        System.out.println(">>>------- Propagacja wsteczna ------<<<");
        System.out.println(">>>------------ ver 1.0 -------------<<<");
        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>>------ Aleksander Sinkowski ------<<<");
        System.out.println(">>>--------- Serhii Rizychuk --------<<<");
        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>>------ Input: 4 ------------------<<<");
        System.out.println(">>>------ Output: 1 -----------------<<<");
        System.out.println(">>>------ Connects: 26 --------------<<<");
        System.out.println(">>>------ Hidden Layouts: 2 ---------<<<");
        System.out.println(">>>------ Hidden Neurons: 6 ---------<<<");
        System.out.println(">>>------ Learning items: 75 --------<<<");
        System.out.println(">>>----------------------------------<<<");
        System.out.println(">>Wyświetlić szczegóły uruchamiania? y/n");
        System.out.print(">");
//
//        answer = scan.next();
//        if (answer.equals("y"))details = true;
//        System.out.println();

// Wczytanie pliku uczącego
        ReadDataFile();
        if (progres == false)return;
        if (details==true)checkArrayListOfTestData();

// Wylosowanie wag synaps
        CreateWeight();
        if (details==true)checkWeight();
        System.out.println(">>Uczenie sieci...");

// Uczenie sieci
        Learning();
        System.out.println(">>>-------------The End--------------<<<");
    }


/*                          [ 01 ]
  |\      _,,,--,,_  ,)
  /,`.-'`'   -,  ;-;;'
 |,4-  ) )-,_ ) /\
'---''(_/--' (_/-'
>>>Learning NN
*/
    private static void Learning() {
        double path = 0;
        int z = 0;

            System.out.println(">>>-------------Item " + z + "--------------<<<");
            //Pobranie danych wejściowych dla jednego ciągu uczącego (np: 1 2 0 1)

            HLOne.clear();
            HLTwo.clear();

//------- Warstwa ukryta 1

            int inputA = Integer.parseInt((String) TD.get(lOI));
            int inputB = Integer.parseInt((String) TD.get(lOI + 1));
            int inputC = Integer.parseInt((String) TD.get(lOI + 2));
            int inputD = Integer.parseInt((String) TD.get(lOI + 3));
            lOI += 4;

            System.out.println(">>Obliczanie pierwszej warstwy ukrytej z 4 neuronami...");
            for (int i = 0; i < 4; i++) {
                path = (inputA * (double) Weight.get(lOW)) + (inputB * (double) Weight.get(lOW + 1)) + (inputC * (double) Weight.get(lOW + 2)) + (inputD * (double) Weight.get(lOW + 3));
                lOW += 4;
                HLOne.add(path);//dodanie kolejno 4 wartości neuronów do wrastwy ukrytej pierwszej
            }
            System.out.println("Sprawdzenie...");
            for (int i = 0; i < 4; i++) {
                System.out.println("L1N" + i + ": " + HLOne.get(i));//dodanie kolejno 4 wartości neuronów z wrastwy ukrytej pierwszej
            }

            System.out.println(">Ukończono.");

//------- Warstwa ukryta 2

            System.out.println(">>Obliczanie drugiej warstwy ukrytej z 2 neuronami...");

            double inputE = (double) HLOne.get(0);
            double inputF = (double) HLOne.get(1);
            double inputG = (double) HLOne.get(2);
            double inputH = (double) HLOne.get(3);

            for (int i = 0; i < 2; i++) {
                path = (inputE * (double) Weight.get(lOW)) + (inputF * (double) Weight.get(lOW + 1)) + (inputG * (double) Weight.get(lOW + 2)) + (inputH * (double) Weight.get(lOW + 3));
                lOW += 4;
                HLTwo.add(path);//dodanie kolejno 2 wartości neuronów do wrastwy ukrytej drugiej
            }
            System.out.println("Sprawdzenie...");
            for (int i = 0; i < 2; i++) {
                System.out.println("L2N" + i + ": " + HLTwo.get(i));//dodanie kolejno 4 wartości neuronów z wrastwy ukrytej pierwszej
            }

            System.out.println(">Ukończono.");

//------- Wyjście

            System.out.println(">>Obliczanie wyjścia...");

            double inputI = (double) HLTwo.get(0);
            double inputJ = (double) HLTwo.get(1);

            path = (inputI * (double) Weight.get(lOW)) + (inputJ * (double) Weight.get(lOW + 1));
            lOW += 2;
            TDOUT.add(path);//dodanie kolejno 2 wartości neuronów do wrastwy ukrytej drugiej
            System.out.println("Output: " + TDOUT.get(z));//dodanie kolejno 4 wartości neuronów z wrastwy ukrytej pierwszej

            System.out.println(">Ukończono.");

//------- Propagacja wsteczna



    }



    /*                          [ 02 ]
  |\      _,,,--,,_  ,)
  /,`.-'`'   -,  ;-;;'
 |,4-  ) )-,_ ) /\
'---''(_/--' (_/-'
>>>Create weights list
*/
    private static void CreateWeight() {
        System.out.println(">>Generowanie 26 wag...");
        double tmp = 0;
        double tmp2 = 0;
        for(int i = 0; i<26; i++){
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



/*                          [ 03 ]
  |\      _,,,--,,_  ,)
  /,`.-'`'   -,  ;-;;'
 |,4-  ) )-,_ ) /\
'---''(_/--' (_/-'
>>>Check weights list
*/
    private static void checkWeight() {
        System.out.println(">>Sprawdzanie wag: ");
        int input = 1;
        int neuro = 1;
        int neuro2 = 1;
        int lay1 = 0;
        for(int i = 0; i<26; i++){
            System.out.println();
            lay1++;
            if (lay1 < 17) {
                if (input < 5) {
                    System.out.print("IN: " + input + " -> L1 N" + neuro + ": ");
                    input++;
                } else {
                    input = 1;
                    neuro++;
                    System.out.print("---------------------------");
                    System.out.println();
                    System.out.print("IN: " + input + " -> L1 N" + neuro + ": ");
                    input++;
                }
            }
            if(lay1 == 17){
                neuro=1;
                System.out.println("---------------------------");
            }
            if (lay1 >= 17 && lay1 <25) {
                if (neuro < 5) {
                    System.out.print("L1 N" + neuro + " -> L2 N" + neuro2 + ": ");
                    neuro++;
                } else {
                    neuro = 1;
                    neuro2++;
                    System.out.print("---------------------------");
                    System.out.println();
                    System.out.print("L1 N" + neuro + " -> L2 N" + neuro2 + ": ");
                    neuro++;
                }
            }

            if(lay1 == 25){
                System.out.println("---------------------------");
                System.out.print("L2 N1 -> Output 1: ");
            }

            if(lay1 == 26){
                System.out.println("---------------------------");
                System.out.print("L2 N2 -> Output 1: ");
            }
            System.out.print(Weight.get(i));
        }
        System.out.println();
        System.out.println();
        System.out.println(">>Koniec sprawdzania.");
    }


/*                          [ 04 ]
  |\      _,,,--,,_  ,)
  /,`.-'`'   -,  ;-;;'
 |,4-  ) )-,_ ) /\
'---''(_/--' (_/-'
>>>Checking ArrayList with explosive test data
*/
    private static void checkArrayListOfTestData() {
        System.out.println(">>Sprawdzanie listy tst...");
        int licz = 0;
        int forTDM = 0;
        for(int i = 0; i<TD.size(); i++){
            if(licz == 4 || i == TD.size()){
                System.out.print(" " + TDM.get(forTDM));
                licz = 0;
                System.out.println();
                forTDM++;
            }
            System.out.print(" " + TD.get(i));
            licz++;
        }
        System.out.print(" " + TDM.get(forTDM));

        System.out.println();
        System.out.println(">>Koniec sprawdzania.");
    }


/*                          [ 05 ]
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
                    if(i != 0 && i != lengthOfLine - 1)TD.add(exploded[i]);
                    if(i == lengthOfLine - 1)TDM.add(exploded[i]);
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
