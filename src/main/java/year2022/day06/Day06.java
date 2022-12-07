package year2022.day06;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day06 {

    static Object solveA(List<String> values) {
        int length=4;
        return getIndexOfUniqueSubstring(values.get(0), length);
    }

    static Object solveB(List<String> values) {
        int length=14;
        return getIndexOfUniqueSubstring(values.get(0), length);
    }
    static int getIndexOfUniqueSubstring(String input, int length) {
        for(int i=0; i<=input.length()-length; i++) {
            HashSet<Character> letters = new HashSet<>();
            for(char c : input.substring(i, i+length).toCharArray()) {
                letters.add(c);
            }

              if(letters.size() == length) {
                return i+length;
            }
        }
        return -1;
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //1848
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //2308
    }
}
