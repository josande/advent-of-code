package year2023.day08;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day08 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day08());
    }

    @Override
    public Object solveA(List<String> input) {
        var maps = new HashMap<String, Pair<String, String>>();
        String pattern = input.get(0);
        for(int i=1; i<input.size(); i++) {
            if(input.get(i).isEmpty()) continue;
            String[] parts = input.get(i).replaceAll("[(=,)]+", "").split(" +");
            maps.put(parts[0], new ImmutablePair<>(parts[1], parts[2]));
        }
        String current="AAA";
        for(int steps=0; ; steps++) {
            if(current.equals("ZZZ")) return steps;
            if(pattern.charAt(steps%pattern.length()) == 'L')
                current = maps.get(current).getLeft();
            else
                current = maps.get(current).getRight();
        }

    }

    @Override
    public Object solveB(List<String> input) {
        var maps = new HashMap<String, Pair<String, String>>();
        String pattern = input.get(0);
        for(int i=1; i<input.size(); i++) {
            if(input.get(i).isEmpty()) continue;
            String[] parts = input.get(i).replaceAll("[(=,)]+", "").split(" +");
            maps.put(parts[0], new ImmutablePair<>(parts[1], parts[2]));
        }
        List<String> current = maps.keySet().stream().filter(c -> c.endsWith("A")).toList();

        List<Pair<Integer, Integer>> cycleLengths = new ArrayList<>();
        for(String node : current) {
            cycleLengths.add(getCycleLength(pattern, node, maps));
        }

        List<List<Integer>> allMatches = new ArrayList<>();
        for(int i=0; i<current.size(); i++) {
            allMatches.add(getStepsEndingInZ(current.get(i), cycleLengths.get(i), pattern, maps));
        }

        int shortestCycle=Integer.MAX_VALUE;
        int start =0;
        for(int i=0; i<cycleLengths.size(); i++) {
            if(cycleLengths.get(i).getLeft()<shortestCycle) {
                shortestCycle = cycleLengths.get(i).getLeft();
                start = allMatches.get(i).get(0);
            }
        }

        for(long l = start; ;l+=shortestCycle) {
            boolean allOk=true;
            for(int i=0; i< cycleLengths.size(); i++) {
                if ((l - allMatches.get(i).get(allMatches.get(i).size()-1) )% cycleLengths.get(i).getLeft() != 0 ) {
                    allOk = false;
                    break;
                }

            }
            if (allOk)
                return l;
        }
    }
    private Pair<Integer, Integer> getCycleLength(String pattern, String start, HashMap<String, Pair<String, String>> maps) {
        HashMap<Pair<String, Integer>, Integer> positions = new HashMap<>();
        String current = start;
        for(int steps=0; ; steps++) {
            var pos = new ImmutablePair<>(current, steps%pattern.length());
            if(positions.containsKey(pos)) {
                int cycleLength = steps-positions.get(pos);
                int offset = steps-cycleLength;
                return new ImmutablePair<>(cycleLength, offset);
            }
            positions.put(pos, steps);

            if(pattern.charAt(steps%pattern.length()) == 'L')
                current = maps.get(current).getLeft();
            else
                current = maps.get(current).getRight();
        }
    }
    List<Integer> getStepsEndingInZ(String node, Pair<Integer, Integer> cycle, String pattern, HashMap<String, Pair<String, String>> maps) {
        List<Integer> matches = new ArrayList<>();
        for(int step=0; step<cycle.getLeft()+cycle.getRight(); step++) {
            if(step>cycle.getRight() && node.endsWith("Z")) matches.add(step);
            if(pattern.charAt(step%pattern.length()) == 'L')
                node = maps.get(node).getLeft();
            else
                node = maps.get(node).getRight();
        }
        return matches;
    }
}
