package year2025.day05;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day05 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day05());
    }

    @Override
    public Object solveA(List<String> input) {
        List<Long> lowerBounds = new ArrayList<>();
        List<Long> upperBounds = new ArrayList<>();
        var result = 0L;
        Set<Long> skipped = new HashSet<>();

        for(String line : input){
            if(line.isBlank()) continue;
            if(line.contains("-")) {
                String[] parts = line.split("-");
                lowerBounds.add(Long.parseLong(parts[0]));
                upperBounds.add(Long.parseLong(parts[1]));
            } else {
                var ingredient = Long.parseLong(line);
                if(skipped.contains(ingredient)) continue;
                for(int i=0; i<lowerBounds.size(); i++) {
                    if(ingredient >= lowerBounds.get(i) && ingredient <= upperBounds.get(i)) {
                        result++;
                    }
                }
                skipped.add(ingredient);
            }
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        Stack<Pair<Long, Long>> sections = new Stack<>();
        for(String line : input){
            if(line.isBlank()) continue;
            if(line.contains("-")) {
                String[] parts = line.split("-");
                sections.push(new ImmutablePair<>(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
            }
        }
        long result =0L;

        while(!sections.isEmpty()) {
            var section = sections.pop();
            var foundMatch = false;
            for(int i=sections.size()-1; i>=0 && !foundMatch; i--) {
                var otherSection = sections.get(i);
                Pair<Long, Long> merged = mergeIfPossible(section, otherSection);
                if(merged != null) {
                    sections.set(i, merged);
                    foundMatch=true;
                }
            }
            if(!foundMatch) {
                result+=section.getRight()-section.getLeft()+1;
            }
        }
        return result;
    }
    
    private Pair<Long, Long> mergeIfPossible(Pair<Long, Long> pair1, Pair<Long, Long> pair2) {
        if(pair1.getLeft() >= pair2.getLeft()-1 && pair1.getLeft() <= pair2.getRight()+1) return merge(pair1, pair2);
        if(pair2.getLeft() >= pair1.getLeft()-1 && pair2.getLeft() <= pair1.getRight()+1) return merge(pair1, pair2);

        if(pair1.getRight() >= pair2.getLeft()-1 && pair1.getRight() <= pair2.getRight()+1) return merge(pair1, pair2);
        if(pair2.getRight() >= pair1.getLeft()-1 && pair2.getRight() <= pair1.getRight()+1) return merge(pair1, pair2);

        if(pair1.getLeft() >= pair2.getLeft()-1 && pair1.getRight() <= pair2.getRight()+1) return merge(pair1, pair2);
        if(pair1.getLeft() <= pair2.getLeft()+1 && pair1.getRight() >= pair2.getRight()-1) return merge(pair1, pair2);
        return null;
    }

    private Pair<Long, Long> merge(Pair<Long, Long> pair1, Pair<Long, Long> pair2) {
        return new ImmutablePair<>(Math.min(pair1.getLeft(), pair2.getLeft()), Math.max(pair1.getRight(), pair2.getRight()));
    }


}
