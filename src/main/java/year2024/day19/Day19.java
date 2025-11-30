package year2024.day19;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day19 implements AdventOfCode {
    public static void main(){

        Reporter.report(new Day19());
    }

    HashMap<String, Long> knownWays = new HashMap<>();

    @Override
    public Object solveA(List<String> input) {
        List<String> towels = Arrays.stream(input.getFirst().split(", ")).toList();
        List<String> designs = new ArrayList<>();
        for(int i=2; i<input.size(); i++) designs.add(input.get(i));
        return designs.stream().filter(d->waysToMatch(d, towels)>0).count();
    }

    @Override
    public Object solveB(List<String> input) {
        List<String> towels = Arrays.stream(input.getFirst().split(", ")).toList();
        List<String> designs = new ArrayList<>();
        for(int i=2; i<input.size(); i++) designs.add(input.get(i));
        return designs.stream().mapToLong(d->waysToMatch(d, towels)).sum();
    }

    private long waysToMatch(String design, List<String> towels) {
        if(knownWays.containsKey(design)) return knownWays.get(design);
        if(design.isEmpty()) return 1;
        long numberOfWays = towels.stream().filter(design::startsWith).mapToLong(t-> waysToMatch(design.substring(t.length()), towels)).sum();
        knownWays.put(design, numberOfWays);
        return numberOfWays;
    }
}
