package year2024.day01;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Day01 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day01());
    }

    @Override
    public Object solveA(List<String> input) {
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (String line : input) {
            String[] a = line.split(" +");
            left.add(Integer.parseInt(a[0]));
            right.add(Integer.parseInt(a[1]));
        }
        Collections.sort(left);
        Collections.sort(right);

        return IntStream.range(0, left.size()).map(i -> Math.abs(right.get(i)-left.get(i))).sum(); //2344935
    }

    @Override
    public Object solveB(List<String> input) {
        ArrayList<Integer> left = new ArrayList<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        for (String line : input) {
            String[] a = line.split(" +");
            left.add(Integer.parseInt(a[0]));
            int r = Integer.parseInt(a[1]);
            right.put(r, right.getOrDefault(r,0)+1);
        }
        return left.stream().mapToInt(l -> l*right.getOrDefault(l,0)).sum(); //27647262
    }
}
