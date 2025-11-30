package year2023.day14;

import utils.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day14());
    }

    @Override
    public Object solveA(List<String> input) {
        var map = MapUtil.asMap(input);

        map = move(Direction.NORTH, map);

        int maxY = MapUtil.getMaxY(map);
        return map.entrySet().stream().filter(e -> e.getValue()=='O').mapToInt(e-> maxY+1 - e.getKey().getY()).sum();
    }
    private HashMap<Point, Character> move (Direction direction, HashMap<Point, Character> map) {
        HashMap<Point, Character> newMap = new HashMap<>(map);
        boolean moved = true;
        while(moved) {
            moved=false;
            for(Point p : newMap.entrySet().stream().filter(e -> e.getValue().equals('O')).map(Map.Entry::getKey).toList()) {
                if(newMap.getOrDefault(p.getNext(direction), '#') == '.') {
                    newMap.put(p, '.');
                    newMap.put(p.getNext(direction), 'O');
                    moved=true;
                }
            }
        }
        return newMap;
    }

    @Override
    public Object solveB(List<String> input) {

        var map = MapUtil.asMap(input);

        List<Direction> order = List.of(Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST);
        map = move(Direction.NORTH, map);

        HashMap<HashMap<Point, Character>, Long> cache = new HashMap<>();
        long target= 1000000000L;

        for(long cycle = 0; cycle < target; cycle++) {
            for(Direction direction : order) {
                map = move(direction, map);
            }
            var cachedValue = cache.put(map, cycle);
            if (cachedValue != null) {
                long cycleLength = cycle - cachedValue;
                long skipCycles = (target - cycle)/cycleLength;
                cycle += skipCycles*cycleLength;
            }
        }
        int maxY = MapUtil.getMaxY(map);
        return map.entrySet().stream().filter(e -> e.getValue()=='O').mapToInt(e-> maxY+1 - e.getKey().getY()).sum();
    }
}
