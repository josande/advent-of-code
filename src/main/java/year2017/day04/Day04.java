package year2017.day04;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day04 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day04());
    }

    @Override
    public Object solveA(List<String> input) {
        int validCounter=0;
        for(String row : input) {
            String[] parts = row.split(" ");
            HashSet<String> passwords = new HashSet<>(List.of(parts));
            if(parts.length== passwords.size()) validCounter++;
        }
        return validCounter;
    }

    @Override
    public Object solveB(List<String> input) {
        int validCounter=0;
        for(String row : input) {
            HashSet<String> passwords = new HashSet<>();
            String[] parts = row.split(" ");
            for(String part:parts) {
                char[] chars=part.toCharArray();
                Arrays.sort(chars);
                passwords.add(new String(chars));
            }
            if(parts.length== passwords.size()) validCounter++;
        }
        return validCounter;
    }
}
