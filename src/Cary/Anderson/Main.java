package Cary.Anderson;

import java.io.File;


public class Main {

    public static void main(String[] args) {
	// write your code here

        File test = new File("third.txt");
        UFSA primary = new UFSA(test, true);
        primary.printResults();
    }
}
