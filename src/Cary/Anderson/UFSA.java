package Cary.Anderson;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Universal Finite State Automaton class
 * Created by Cary Anderson
 */
public class UFSA {

    File file;
    int numberOfStates = -1;
    HashMap<TwoTuple, Character> transitionTable = new HashMap<TwoTuple, Character>();
    List<Character> alphabet = new ArrayList<Character>();
    List<Character> finalStates = new ArrayList<Character>();
    ArrayList<String> sequences = new ArrayList<String>();

    public UFSA() {

    }

    public UFSA(File f, boolean startImmediately) {
        file = f;
        if (startImmediately)
            start();
    }

    public void setFile(File f) {
        file = f;
    }

    /*
    * Scan the lines and change state accordingly.
    * */
    public void printResults() {
        System.out.print("(1) number of states: " + numberOfStates);
        System.out.print("\n(2) final states: ");
        for (Character c : finalStates)
            System.out.print("" + c + " ");
        System.out.print("\n(3) alphabet: ");
        for (Character c : alphabet)
            System.out.print("" + c + " ");
        System.out.println("\n(4) transitions: ");
        for (Map.Entry<TwoTuple, Character> entry : transitionTable.entrySet()) {
            String key = entry.getKey().toString();
            Character value = entry.getValue();
            System.out.println("key, " + key + " value " + value );
        }

        System.out.println("\nStrings: ");
        for (String line : sequences) {
            Character state = '0';
            System.out.print(line);
            for (char c : line.toCharArray()) {
                //System.out.println(" beginning state: " + state);
                TwoTuple tempTuple = new TwoTuple(state, c);
                if (!alphabet.contains(c)) {
                    System.out.println("alphabet is messed up!");
                    break;
                }
                //System.out.println(" Consuming: " + tempTuple.toString());
                //System.out.println(" contains?: " + transitionTable.get(tempTuple));
                if (transitionTable.containsKey(tempTuple)) {
                    state = transitionTable.get(tempTuple);
                } else {
                    state = '-';
                    //System.out.println(" Consuming: " + tempTuple.toString());
                    System.out.print(" Trap state! " + state);
                    break;
                }
            }
            if (finalStates.contains(state)) {
                System.out.println(" Accept");
            } else {
                System.out.println(" Reject");
            }
        }
    }

    /*
    * Process file and change state accordingly.
    * */
    public void start() {
        if (file == null)
            return;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int count = 0;
            /*
            * Process each line of the file.
            * */
            while ((line = br.readLine()) != null) {
                line = line.trim().replaceAll(" ", "");
                if (line.equals(""))
                    continue; // Skip blank lines completely

                /*
                * Set the number of states using the first line of the input file.
                * */
                if (count == 0) {
                    for (char c : line.toCharArray()) {
                        if (Character.isDigit(c)) {
                            numberOfStates = Integer.parseInt(Character.toString(c)); // What a mess..
                            break;
                        }
                    }
                    if (numberOfStates == -1) {
                        System.out.println("Error! Unable to parse number of states!");
                    }
                    count ++;
                    continue;
                }
                /*
                * Build the array of final states using the second line of the input file.
                * */
                if (count == 1) {
                    for (char c : line.toCharArray()) {
                        if (c == ' ' || c == ',' || c =='.')
                            continue;
                        finalStates.add(c);
                    }

                    count++;
                    continue;
                }
                /*
                * Build the alphabet, using the third line of the input file.
                * */
                if (count == 2) {
                    for (char c : line.toCharArray()) {
                        if (c == ' ') {
                            continue;
                        }
                        else {
                            alphabet.add(c);
                        }
                    }
                    count++;
                    continue; // Move along.
                }
                /*
                * If it is a tuple then build the internal table.
                * */
                if (line.charAt(0) == '(') {
                    char[] temp = {' ', ' ', ' '};
                    for (char c : line.toCharArray()) {
                        if (c == ',' || c == ' ' || c == '(' || c == ')')
                            continue;
                        if (temp[0] == ' ') {
                            temp[0] = c;
                        } else if (temp[1] == ' ') {
                            temp[1] = c;
                        } else if (temp[2] == ' ') {
                            temp[2] = c;
                        }
                    }
                    transitionTable.put(new TwoTuple(temp[0], temp[1]), temp[2]);
                } else {
                    /*
                    * Else if it's just numbers in a line, build a sequence to be evaluated later.
                    * */
                    String sequence = "";
                    for (char c : line.toCharArray()) {
                        if (c == ' ')
                            continue;
                        sequence += c;
                    }
                    sequences.add(sequence);
                 }
                count++;
            }
            br.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.out.println("Error, File not Found.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Error reading line.");
        }
    }
}
