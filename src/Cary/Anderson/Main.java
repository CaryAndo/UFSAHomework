/**
* CS 311 Project #1
* Cary Anderson - Completed on 26 January, 2015
* To run, include the five text files in the same directory and run Main.java
* e.g. javac Main.java && java Main
* */

package Cary.Anderson;

import java.io.File;


public class Main {

    public static void main(String[] args) {

        System.out.println("\n\nFirst Automaton");
        File firstFile = new File("first.txt");
        UFSA firstAuto = new UFSA(firstFile, true);
        firstAuto.printResults();

        System.out.println("\n\nSecond Automaton");
        File secondFile = new File("second.txt");
        UFSA secondAuto = new UFSA(secondFile, true);
        secondAuto.printResults();

        System.out.println("\n\nThird Automaton");
        File thirdFile = new File("third.txt");
        UFSA thirdAuto = new UFSA(thirdFile, true);
        thirdAuto.printResults();

        System.out.println("\n\nFourth Automaton");
        File fourthFile = new File("fourth.txt");
        UFSA fourthAuto = new UFSA(fourthFile, true);
        fourthAuto.printResults();

        System.out.println("\n\nFifth Automaton");
        File fifthFile = new File("fifth.txt");
        UFSA fifthAuto = new UFSA(fifthFile, true);
        fifthAuto.printResults();
    }
}
