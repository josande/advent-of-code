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
    public static void main(String[] args){
        Reporter.report(new Day21());
    }

    HashMap<Character, HashMap<Character, List<String>>> numpadFromTo = new HashMap<>();
    HashMap<Character, HashMap<Character, Set<String>>> dpadFromTo = new HashMap<>();

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
                List<String> paths = new ArrayList<>();
                paths.addAll(getShortPaths(from, to, numPadLayout));
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

    private int getPaths(String path, int level) {
        if (level == 0) return path.length();



        return 0;
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
        Point startPoint = map.entrySet().stream().filter(e ->e.getValue()==from).findAny().map(e->e.getKey()).orElseThrow();
        Point endPoint = map.entrySet().stream().filter(e ->e.getValue()==to).findAny().map(e->e.getKey()).orElseThrow();

        String hotizontal ="";
        String vertical ="";


        if(startPoint.getX()<endPoint.getX()) {
            for(int x = startPoint.getX(); x<endPoint.getX(); x++) {
                vertical+=">";
            }
        }
        if(startPoint.getX()>endPoint.getX()) {
            for(int x = startPoint.getX(); x>endPoint.getX(); x--) {
                vertical+="<";
            }
        }
        if(startPoint.getY()<endPoint.getY()) {
            for(int y = startPoint.getY(); y<endPoint.getY(); y++) {
                hotizontal+="v";
            }
        }
        if(startPoint.getY()>endPoint.getY()) {
            for(int y = startPoint.getY(); y>endPoint.getY(); y--) {
                hotizontal+="^";
            }
        }
        HashSet<String> paths = new HashSet<>();
        String p1 = vertical+hotizontal+"A";
        String p2 = hotizontal+vertical+"A";

        if(testWalk(startPoint, endPoint, p1, map))
            paths.add(vertical+hotizontal+"A");
        if(testWalk(startPoint, endPoint, p2, map))
            paths.add(hotizontal + vertical + "A");
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
                List<String> paths = new ArrayList<>();
                paths.addAll(getShortPaths(from, to, numPadLayout));
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
