package year2017.day05;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.List;

public class Day05 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day05());
    }

    @Override
    public Object solveA(List<String> input) {
        ArrayList<Integer> values = new ArrayList<>();
        int position=0;
        int steps=0;
        for(String row : input) {
            values.add(Integer.parseInt(row));
        }
        while(position>=0 && position<values.size()) {
            values.set(position, values.get(position)+1);
            position+=values.get(position)-1;
            steps++;
        }
        return steps;
    }

    @Override
    public Object solveB(List<String> input) {
        ArrayList<Integer> values = new ArrayList<>();
        int position=0;
        int steps=0;
        for(String row : input) {
            values.add(Integer.parseInt(row));
        }
        while(position>=0 && position<values.size()) {
            int offset=values.get(position);
            if(offset>=3) {
                values.set(position, values.get(position)-1);
            } else {
                values.set(position, values.get(position)+1);
            }
            position+=offset;
            steps++;
        }
        return steps;
    }
}
