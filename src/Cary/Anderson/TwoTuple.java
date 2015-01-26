/**
 * CS 311 Project #1
 * Cary Anderson - Completed on 26 January, 2015
 * To run, include the five text files in the same directory and run Main.java
 * e.g. javac Main.java && java Main
 * */

package Cary.Anderson;

/**
 * A class representing a two tuple.
 * Used as a key in a hashmap to map
 * (state, value) pairs to next transition states.
 *
 * Created by cary on 1/23/15.
 */
public class TwoTuple {


    private char state;
    private char input;

    public TwoTuple() {

    }

    public TwoTuple(char st, char in) {
        state = st;
        input = in;
    }

    public char getInput() {
        return input;
    }

    public void setInput(char input) {
        this.input = input;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TwoTuple)) return false;

        TwoTuple twoTuple = (TwoTuple) o;

        if (input != twoTuple.input) return false;
        if (state != twoTuple.state) return false;

        return true;
    }

    @Override
    public String toString() {
        return "state: " + state + " input: " + input;
    }

    @Override
    public int hashCode() {
        int result = (int) state;
        result = 31 * result + (int) input;
        return result;
    }
}
