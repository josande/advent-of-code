package year2025.day01;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

public class Day01 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day01());
    }

    @Override
    public Object solveA(List<String> input) {
        int result = 0, current = 50;
        for(String row : input) {
            boolean left = row.startsWith("L");
            var value = Integer.parseInt(row.substring(1));
            if(left) current = (current - value) % 100;
            if(!left) current = (current + value) % 100;
            if (current == 0) result++;
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        int result = 0, current = 50;
        for(String row : input) {
            boolean left = row.startsWith("L");
            var value = Integer.parseInt(row.substring(1));
            result+=value/100;
            value%=100;
            if(value==0) continue;
            if(left) {
                var temp =current - value;
                if (temp<=0) result++;
                current = (100 + temp) % 100;
            }
            if(!left) {
                var temp =current + value;
                if (temp>=100) result++;
                current = (100 + temp) % 100;
            }
        }

        return result;
    }
}
