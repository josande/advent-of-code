package year2022.day03;

import utils.ArrayHelper;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

public class Day03 {

    static Object solveA(List<String> values) {
        int score=0;
        for(String row : values) {
            String partA = (row.substring(0, row.length()/2));
            String partB = row.substring(row.length()/2);

            Set<Character> chars = partA.codePoints().mapToObj(c -> (char) c).collect(Collectors.toSet());
            for(char c : chars) {
                if(partB.contains(""+c)) {
                    score += getValueOf(c);
                }
            }
        }
        return score;
    }
    private static int getValueOf(char c) {
        if (c > 91) {
            return c-96;
        }

        return c-38;
    }
    static Object solveB(List<String> values) {
        int score=0;
        for(int i=0; i<values.size(); i+=3) {
            Set<Character> chars = values.get(i).codePoints().mapToObj(c -> (char) c).collect(Collectors.toSet());

            for(char c: chars) {
                if(values.get(i+1).contains(""+c) && values.get(i+2).contains(""+c)) {
                    score+=getValueOf(c);
                }
            }
        }
        return score;

    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //8109
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //2738
    }
}
