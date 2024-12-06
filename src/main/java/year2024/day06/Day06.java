package year2024.day06;

import utils.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;



public class Day06 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day06());
    }

    private record Position(Point point, Direction direction) {

    }
    @Override
    public Object solveA(List<String> input) {
        var map = MapUtil.asMap(input);
        Position currentPos = getStartPos(map);
        HashSet<Point> visited = new HashSet<>();


        while(currentPos != null) {
            visited.add(currentPos.point);
            currentPos = getNextPos(map, currentPos);

        }

        return visited.size();
    }

    private Position getNextPos(HashMap<Point, Character> map, Position currentPos) {

        if (!map.containsKey(currentPos.point.getNext(currentPos.direction)))
            return null;

        if (map.get(currentPos.point.getNext(currentPos.direction)) == '#')
            return new Position(currentPos.point, currentPos.direction.right());

        return new Position(currentPos.point.getNext(currentPos.direction), currentPos.direction);
    }

    private Position getStartPos(HashMap<Point, Character> map) {
        for(var e : map.entrySet()) {
            if(e.getValue()=='^') return new Position(e.getKey(), Direction.NORTH);
        }
        throw new IllegalStateException("Unknown Start!");
    }

    @Override
    public Object solveB(List<String> input) {
        var startMap = MapUtil.asMap(input);
        Position startPos = getStartPos(startMap);
        Position currentPos = new Position(startPos.point, startPos.direction);
        HashSet<Point> visited = new HashSet<>();

        while(currentPos != null) {
            visited.add(currentPos.point);
            currentPos = getNextPos(startMap, currentPos);
        }

        HashSet<Point> createsLoops = new HashSet<>();

        for(Point p : visited) {
            if(p.equals(startPos.point)) continue;
            var newMap = new HashMap<>(startMap);
            newMap.put(p, '#');
            if(containsLoop(newMap))
                createsLoops.add(p);
        }
        return createsLoops.size();
    }
    private boolean containsLoop(HashMap<Point, Character> map) {
        HashSet<Position> loopSet = new HashSet<>();
        Position currentPos = getStartPos(map);
        while(currentPos != null) {
            if(!loopSet.add(currentPos))
                return true;
            currentPos = getNextPos(map, currentPos);
        }
        return false;
    }
}
