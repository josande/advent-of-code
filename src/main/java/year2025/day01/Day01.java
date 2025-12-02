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
            var value = Integer.parseInt(row.substring(1));
            value*=row.startsWith("L")?-1:1;
            current = (100+current + value%100) % 100;
            result+=current==0?1:0;
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        int result = 0, current = 50;
        for(String row : input) {
            var value = Integer.parseInt(row.substring(1));
            result+=value/100;
            value%=100;
            value*=row.startsWith("L")?-1:1;
            var temp = current + value;
            result+= ((current > 0 && temp<=0) || temp>99)?1:0;
            current = (100+temp)%100;
        }
        return result;
    }
}
