package year2023.day09;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day09 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day09());
    }

    @Override
    public Object solveA(List<String> input) {
        return input.stream().map(this::getLastValue).reduce(0L, Long::sum);
    }
    @Override
    public Object solveB(List<String> input) {
        return input.stream().map(this::getFirstValue).reduce(0L, Long::sum);
    }
    private Long getLastValue(String line) {
        return getValue(line, true);
    }
    private Long getFirstValue(String line) {
        return getValue(line, false);
    }

    private long getValue(String input, boolean last) {
        ArrayList<Long> values = new ArrayList<>(Arrays.stream(input.split(" ")).map(Long::valueOf).toList());
        ArrayList<ArrayList<Long>> allLists = new ArrayList<>();
        allLists.add(values);
        ArrayList<Long> lastRow = new ArrayList<>(values);

        while(hasDifferentValues(lastRow)) {
            ArrayList<Long> nextRow = new ArrayList<>();
            for(int i=0; i<lastRow.size()-1; i++) {
                nextRow.add(lastRow.get(i+1)-lastRow.get(i));
            }
            allLists.add(nextRow);
            lastRow=nextRow;
        }

        if(last) {
            return allLists.stream().map(l ->l.get(l.size()-1)).reduce(0L, Long::sum);
        }
        Long result=0L;
        for(int i=allLists.size()-1; i>=0; i--)
            result=allLists.get(i).get(0)-result;
        return result;
    }
    private boolean hasDifferentValues(ArrayList<Long> values) {
        if(values.isEmpty()) return false;
        return !values.stream().allMatch(s -> s.equals(values.get(0)));
    }
}
