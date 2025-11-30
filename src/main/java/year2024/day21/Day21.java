package year2024.day21;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.*;
import java.util.stream.Collectors;


public class Day21 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day21());
    }

    final HashMap<Character, HashMap<Character, List<String>>> numpadFromTo = new HashMap<>();
    final HashMap<Character, HashMap<Character, Set<String>>> dpadFromTo = new HashMap<>();

    @Override
    public Object solveA(List<String> input) {
        HashMap<Pair<String, Integer>, Long> cache = new HashMap<>();

        var numPadLayout = MapUtil.asMap(Arrays.stream(
                        """
                        789
                        456
                        123
                        #0A
                        """
                        .split("\n"))
                        .filter(s->!s.isEmpty())
                        .map(String::valueOf)
                        .collect(Collectors.toList()));

        var dpadLayout = MapUtil.asMap(Arrays.stream(
                        """
                        #^A
                        <v>
                        """
                        .split("\n"))
                        .filter(s->!s.isEmpty())
                        .map(String::valueOf)
                        .collect(Collectors.toList()));

        String numpadValues = "1234567890A";
        String dpadValues = "<^v>A";

        for(char from : numpadValues.toCharArray()) {
            var targets =  new HashMap<Character, List<String>>();
            for (char to : numpadValues.toCharArray()) {
                List<String> paths = new ArrayList<>(getShortPaths(from, to, numPadLayout));
                targets.put(to, paths);
            }
            numpadFromTo.put(from, targets);
        }
        for(char from : dpadValues.toCharArray()) {

            var targets = new HashMap<Character, Set<String>>();
            for (char to : dpadValues.toCharArray()) {
                targets.put(to, getShortPaths(from, to, dpadLayout));
            }
            dpadFromTo.put(from, targets);
        }

        long complexity=0;

        for(var line : input) {
            var numPadPaths = new ArrayList<String>();
            numPadPaths.add("");

            char[] code = line.toCharArray();
            char from = 'A';
            for(int i=0; i<line.length(); i++) {
                char to = code[i];
                var pathsAfter = new ArrayList<String>();
                for (var path : numpadFromTo.get(from).get(to)) {
                    for(String pathBefore : numPadPaths) {
                        pathsAfter.add(pathBefore+path);
                    }
                }
                from = to;
                numPadPaths = pathsAfter;
            }
            long shortestPath = Long.MAX_VALUE;
            for(String path : numPadPaths) {

                long length = getLength(path, 0 , 2, cache);
                if(length<shortestPath) {
                    shortestPath = length;
                }
            }
            complexity+= (Long.parseLong(line.substring(0,3))*shortestPath);
        }
        return complexity;
    }

    private long getLength(String string, int level, int maxDepth, HashMap<Pair<String, Integer>, Long> cache) {
        if (level == maxDepth) return string.length();
        Pair<String, Integer> pair = new ImmutablePair<>(string, level);
        if(cache.containsKey(pair)) return cache.get(pair);

        char from='A';
        long length=0;
        for(char to : string.toCharArray()) {
            long partLength=Long.MAX_VALUE;
            for(String pathOption : dpadFromTo.get(from).get(to)) {
                partLength = Math.min(partLength, getLength(pathOption, level+1, maxDepth, cache));
            }
            from = to;
            length+=partLength;
        }
        cache.put(pair, length);
        return length;
    }

    private Set<String> getShortPaths (char from, char to, HashMap<Point, Character> map){
        Point startPoint = map.entrySet().stream().filter(e ->e.getValue()==from).findAny().map(Map.Entry::getKey).orElseThrow();
        Point endPoint = map.entrySet().stream().filter(e ->e.getValue()==to).findAny().map(Map.Entry::getKey).orElseThrow();

        StringBuilder horizontal = new StringBuilder();
        StringBuilder vertical = new StringBuilder();

        vertical.append(">".repeat(Math.max(0, endPoint.getX() - startPoint.getX())));
        vertical.append("<".repeat(Math.max(0, startPoint.getX() - endPoint.getX())));
        horizontal.append("v".repeat(Math.max(0, endPoint.getY() - startPoint.getY())));
        horizontal.append("^".repeat(Math.max(0, startPoint.getY() - endPoint.getY())));

        HashSet<String> paths = new HashSet<>();
        String p1 = vertical.toString() +horizontal+"A";
        String p2 = horizontal+ vertical.toString() +"A";

        if(testWalk(startPoint, endPoint, p1, map)) paths.add(vertical.toString() + horizontal + "A");
        if(testWalk(startPoint, endPoint, p2, map)) paths.add(horizontal + vertical.toString() + "A");
        return paths;
    }
    private boolean testWalk(Point startPoint, Point endPoint, String path, HashMap<Point, Character> map) {
        Point current = new Point(startPoint);
        for(char c : path.toCharArray()) {
            if (map.getOrDefault(current, '#').equals('#')) return false;
            switch (c) {
                case '<' -> current = current.west();
                case '^' -> current = current.north();
                case 'v' -> current = current.south();
                case '>' -> current = current.east();
                case 'A' -> {
                    return current.equals(endPoint);
                }
                default -> throw new IllegalStateException("Unexpected value: " + c);
            }
        }
        throw new IllegalStateException("Path does not contain A:" + path);
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<Pair<String, Integer>, Long> cache = new HashMap<>();
        var numPadLayout = MapUtil.asMap(Arrays.stream(
                        """
                        789
                        456
                        123
                        #0A
                        """
                        .split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList()));

        var dpadLayout = MapUtil.asMap(Arrays.stream(
                        """
                        #^A
                        <v>
                        """
                                .split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList()));

        String numpadValues = "1234567890A";
        String dpadValues = "<^v>A";

        for(char from : numpadValues.toCharArray()) {
            var targets =  new HashMap<Character, List<String>>();
            for (char to : numpadValues.toCharArray()) {
                List<String> paths = new ArrayList<>(getShortPaths(from, to, numPadLayout));
                targets.put(to, paths);
            }
            numpadFromTo.put(from, targets);
        }
        for(char from : dpadValues.toCharArray()) {

            var targets = new HashMap<Character, Set<String>>();
            for (char to : dpadValues.toCharArray()) {
                targets.put(to, getShortPaths(from, to, dpadLayout));
            }
            dpadFromTo.put(from, targets);
        }

        long complexity=0;

        for(var line : input) {
            var numPadPaths = new ArrayList<String>();
            numPadPaths.add("");

            char[] code = line.toCharArray();
            char from = 'A';
            for(int i=0; i<line.length(); i++) {
                char to = code[i];
                var pathsAfter = new ArrayList<String>();
                for (var path : numpadFromTo.get(from).get(to)) {
                    for(String pathBefore : numPadPaths) {
                        pathsAfter.add(pathBefore+path);
                    }
                }
                from = to;
                numPadPaths = pathsAfter;
            }
            long shortestPath = Long.MAX_VALUE;
            for(String path : numPadPaths) {

                long length = getLength(path, 0 , 25, cache);
                if(length<shortestPath) {
                    shortestPath = length;
                }
            }
            complexity+= (Long.parseLong(line.substring(0,3))*shortestPath); //836956270 to low
        }
        return complexity;
    }

}
