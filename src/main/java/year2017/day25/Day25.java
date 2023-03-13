package year2017.day25;


import lombok.AllArgsConstructor;
import lombok.Data;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day25 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day25());
    }


    @Data
    @AllArgsConstructor
    private static class State {

        private int writeIf0, writeIf1;
        private int moveIf0, moveIf1;
        private char stateIf0, getStateIf1;

        int write(int currentValue) {
            return currentValue==0?writeIf0:writeIf1;
        }
        int move(int currentValue) {
            return currentValue==0?moveIf0:moveIf1;
        }
        char nextState(int currentValue) {
            return currentValue==0?stateIf0:getStateIf1;
        }
    }
    @Override
    public Object solveA(List<String> input) {
        HashMap<Character, State> states = new HashMap<>();


        for(int i=3; i<input.size();i+=10) {
            char stateName = input.get(i).charAt(9);
            int writeIf0 = Integer.parseInt(""+input.get(i+2).charAt(22));
            int writeIf1 = Integer.parseInt(""+input.get(i+6).charAt(22));
            int moveIf0 = input.get(i+3).endsWith("right.")?1:-1;
            int moveIf1 = input.get(i+7).endsWith("right.")?1:-1;
            char stateIf0 = input.get(i+4).charAt(26);
            char stateIf1 = input.get(i+8).charAt(26);
            State state = new State( writeIf0, writeIf1, moveIf0,moveIf1, stateIf0, stateIf1 );
            states.put(stateName, state);
        }
        State current = states.get(input.get(0).charAt(15));
        int iterations = Integer.parseInt(input.get(1).split(" ")[5]);
        int position=0;
        HashSet<Integer> tape = new HashSet<>();
        for(int i =0; i<iterations; i++) {
            int currValue = tape.contains(position)?1:0;
            if(current.write(currValue)==1) { tape.add(position); }
            else { tape.remove(position); }
            position+= current.move(currValue);
            current = states.get(current.nextState(currValue));
        }
        return tape.size();
    }

    @Override
    public Object solveB(List<String> input) {
        return "Merry X-mas!";
    }
}
