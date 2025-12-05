package year2025.day05;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.Reporter;

import java.time.chrono.MinguoDate;
import java.util.*;

public class Day05 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day05());
    }

    @Override
    public Object solveA(List<String> input) {
        List<Long> lowerBounds = new ArrayList<>();
        List<Long> upperBounds = new ArrayList<>();
        List<Long> ingredients = new ArrayList<>();
        Set<Long> freshIngredients = new HashSet<>();

        for(String line : input){
            if(line.isBlank()) continue;
            if(line.contains("-")) {
                String[] parts = line.split("-");
                lowerBounds.add(Long.parseLong(parts[0]));
                upperBounds.add(Long.parseLong(parts[1]));
            } else ingredients.add(Long.parseLong(line));
        }
        for(Long ingredient : ingredients) {
            if(freshIngredients.contains(ingredient)) continue;
            for(int i=0; i<lowerBounds.size(); i++) {
                if(ingredient >= lowerBounds.get(i) && ingredient <= upperBounds.get(i)) {
                    freshIngredients.add(ingredient);
                    break;
                }
            }
        }
        return freshIngredients.size();
    }

    @Override
    public Object solveB(List<String> input) {
        List<Long> lowerBounds = new ArrayList<>();
        List<Long> upperBounds = new ArrayList<>();
        for(String line : input){
            if(line.isBlank()) continue;
            if(line.contains("-")) {
                String[] parts = line.split("-");
                lowerBounds.add(Long.parseLong(parts[0]));
                upperBounds.add(Long.parseLong(parts[1]));
            }
        }

        Pair<Integer, Integer> merged;
        do {
            merged=null;
            for(int i=0; i<lowerBounds.size(); i++) {
                for(int j = i+1; j<lowerBounds.size(); j++) {
                    if ((lowerBounds.get(j) >= lowerBounds.get(i)   && lowerBounds.get(j) <= upperBounds.get(i)+1)
                     || (upperBounds.get(j) >= lowerBounds.get(i)-1 && upperBounds.get(j) <= upperBounds.get(i)  )
                     || (lowerBounds.get(j) >= lowerBounds.get(i)   && upperBounds.get(j) <= upperBounds.get(i))
                     || (lowerBounds.get(j) <= lowerBounds.get(i)   && upperBounds.get(j) >= upperBounds.get(i))) {
                        merged=new ImmutablePair<>(i,j);
                        break;
                    }
                }
                if(merged != null) break;
            }
            if(merged != null) {
                Long newLowerBound = Math.min(lowerBounds.get(merged.getLeft()), lowerBounds.get(merged.getRight()));
                Long newUpperBound = Math.max(upperBounds.get(merged.getLeft()), upperBounds.get(merged.getRight()));
                lowerBounds.remove(Math.max(merged.getLeft(), merged.getRight()));
                upperBounds.remove(Math.max(merged.getLeft(), merged.getRight()));
                lowerBounds.remove(Math.min(merged.getLeft(), merged.getRight()));
                upperBounds.remove(Math.min(merged.getLeft(), merged.getRight()));
                lowerBounds.add(newLowerBound);
                upperBounds.add(newUpperBound);
            }
        } while(merged != null);

        long result = 0L;
        for(int i=0; i<lowerBounds.size(); i++) {
            result+=upperBounds.get(i)-lowerBounds.get(i)+1;
        }
        return result;
    }
}
