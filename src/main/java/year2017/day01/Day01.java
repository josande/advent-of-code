package year2017.day01;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

public class Day01 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day01());
    }

    @Override
    public Object solveA(List<String> input) {
        String digits = input.get(0);
        int sum=0;
        int length=digits.length();
        for(int i=0; i<digits.length();i++) {
            if(digits.charAt(i) == digits.charAt((i+1)%length)) {
                sum+=Integer.parseInt(""+digits.charAt(i));
            }
        }

        return sum;
    }

    @Override
    public Object solveB(List<String> input) {
        String digits = input.get(0);
        int sum=0;
        int length=digits.length();
        for(int i=0; i<digits.length();i++) {
            if(digits.charAt(i) == digits.charAt((i+length/2)%length)) {
                sum+=Integer.parseInt(""+digits.charAt(i));
            }
        }

        return sum;
    }
}
