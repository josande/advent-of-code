package year2017.day17;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.LinkedList;
import java.util.List;

public class Day17 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day17());
    }

    @Override
    public Object solveA(List<String> input) {
        int length = Integer.parseInt(input.get(0));
        LinkedList<Integer> values = new LinkedList<>();
        int pos = 0;
        values.add(0);
        for(int i=1; i<=2017; i++) {
            pos = (pos+length)% values.size()+1;
            values.add(pos, i);
        }
        return values.get(pos+1);
    }

    @Override
    public Object solveB(List<String> input) {
        int length = Integer.parseInt(input.get(0));
        int pos = 0;
        int iterations = 50000000;
        int result=0;
        for(int i=1; i<=iterations; i++) {
            pos = (pos+length)% i+1;
            if(pos==1) result=i;
        }
        return result;
    }
}
