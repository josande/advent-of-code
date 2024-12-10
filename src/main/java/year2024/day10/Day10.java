package year2024.day10;

import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.*;
import java.util.stream.Collectors;

public class Day10 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day10());
    }

    @Override
    public Object solveA(List<String> input) {
        var map = MapUtil.asMap(input);
        Set<Point> values = map.entrySet().stream().filter(e -> e.getValue()=='0').map(Map.Entry::getKey).collect(Collectors.toSet());
        String order = "123456789";
        int result=0;
        for(var value : values) {
            HashSet<Point> outs = new HashSet<>();
            outs.add(value);
            for (char nextValue : order.toCharArray()) {
                outs = new HashSet<>(getNextPoints(map, new ArrayList<>(outs), nextValue));
            }
            result+=outs.size();
        }

        return result;
    }

    private ArrayList<Point> getNextPoints(HashMap<Point, Character> map, ArrayList<Point> values, char nextValue) {
        ArrayList<Point> result = new ArrayList<>();
        for(Point p : values) {
            for(var n : p.getOrthogonalNeighbours2d()) {
                if(map.containsKey(n) && map.get(n) == nextValue) {
                    result.add(n);
                }
            }
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        var map = MapUtil.asMap(input);
        Set<Point> values = map.entrySet().stream().filter(e -> e.getValue()=='0').map(Map.Entry::getKey).collect(Collectors.toSet());
        String order = "123456789";
        int result=0;
        for(var value : values) {
            ArrayList<Point> outs = new ArrayList<>();
            outs.add(value);
            for (char nextValue : order.toCharArray()) {
                outs = getNextPoints(map, outs, nextValue);
            }
            result+=outs.size();
        }

        return result;
    }
}
