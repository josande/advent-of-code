package year2017.day02;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day02());
    }

    @Override
    public Object solveA(List<String> input) {
        int checkSum=0;
        for(String row : input) {
            String[] parts = row.split("[ \t]+");
            List<Integer> numbers = Arrays.stream(parts).map(Integer::parseInt).sorted().collect(Collectors.toList());
            checkSum+=numbers.get(numbers.size()-1)-numbers.get(0);
        }
        return checkSum;
    }

    @Override
    public Object solveB(List<String> input) {
        int checkSum=0;
        for(String row : input) {
            String[] parts = row.split("[ \t]+");
            List<Integer> numbers = Arrays.stream(parts).map(Integer::parseInt).sorted().collect(Collectors.toList());
            for(int i=numbers.size()-1; i>0; i--) {
                for(int o=i-1; o>=0; o--) {
                    if(numbers.get(i)%numbers.get(o)==0)
                        checkSum+=numbers.get(i)/numbers.get(o);
                }
            }
        }
        return checkSum;
    }
}
