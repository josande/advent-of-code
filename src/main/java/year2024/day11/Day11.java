package year2024.day11;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day11 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day11());
    }

    @Override
    public Object solveA(List<String> input) {
        List<State> states = Arrays.stream(input.getFirst().split(" ")).map(value-> new State(Long.valueOf(value),0)).toList();

        int maxDepth=25;
        long result=0L;

        HashMap<State, Long> stateStones = new HashMap<>();
        for(State state : states) {
            result+=getStones(state, maxDepth, stateStones);
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        List<State> states = Arrays.stream(input.getFirst().split(" ")).map(value-> new State(Long.valueOf(value),0)).toList();

        int maxDepth=75;
        long result=0L;

        HashMap<State, Long> stateStones = new HashMap<>();
        for(State state : states) {
            result+=getStones(state, maxDepth, stateStones);
        }
        return result;
    }


    private Long getStones(State state, int maxDepth, HashMap<State, Long> stateStones) {
        if(stateStones.containsKey(state)) return stateStones.get(state);
        if(state.depth==maxDepth) {
            return 1L;
        }
        Long result=0L;
        for(var nextStone : state.getNextStates()) {
            result+=getStones(nextStone, maxDepth, stateStones);
        }
        stateStones.put(state, result);
        return result;
    }

    private record State(Long value, Integer depth) {
        private List<State> getNextStates() {

            ArrayList<State> results = new ArrayList<>();
            if (value == 0)
                results.add(new State(1L, depth + 1));
            else {
                String asString = "" + value;
                if (asString.length() % 2 == 0) {
                    results.add(new State(Long.valueOf(asString.substring(0, asString.length() / 2)), depth + 1));
                    results.add(new State(Long.valueOf(asString.substring(asString.length() / 2)), depth + 1));
                } else {
                    results.add(new State(value * 2024, depth + 1));
                }
            }
            return results;
        }
    }
}
