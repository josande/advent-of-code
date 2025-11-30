package year2023.day10;

import utils.*;

import java.util.*;

public class Day10 implements AdventOfCode {

    public static void main(){
        Reporter.report(new Day10());
    }

    @Override
    public Object solveA(List<String> input) {
        var map = MapUtil.asMap(input);
        Point point = map.entrySet().stream().filter(e -> e.getValue() == 'S').findAny().orElseThrow().getKey();

        int steps =0;
        for(Direction direction : Direction.values()) {
            Direction incomingDirection = direction;
            if(getDirections(map.get(point.getNext(direction))).contains(direction.reverse())) {
                while(map.get(point) != 'S' || steps==0) {
                    Direction nextDirection = getNextDirection(incomingDirection, map.get(point));

                    point = point.getNext(nextDirection);

                    steps++;
                    incomingDirection = nextDirection;
                }
                return steps/2;
            }

        }
        return -1;
    }

    List<Direction> getDirections(Character c) {
        switch (c) {
            case '|' -> { return List.of(Direction.NORTH, Direction.SOUTH); }
            case '-' -> { return List.of(Direction.EAST, Direction.WEST); }
            case 'L' -> { return List.of(Direction.NORTH, Direction.EAST); }
            case 'J' -> { return List.of(Direction.NORTH, Direction.WEST); }
            case '7' -> { return List.of(Direction.SOUTH, Direction.WEST); }
            case 'F' -> { return List.of(Direction.SOUTH, Direction.EAST); }
            default-> {return List.of();}
        }
    }
    private Direction getNextDirection(Direction incoming, Character c) {
        switch (c) {
            case '|' -> { return incoming == Direction.NORTH ? Direction.NORTH : Direction.SOUTH; }
            case '-' -> { return incoming == Direction.EAST ? Direction.EAST : Direction.WEST; }
            case 'L' -> { return incoming == Direction.SOUTH ? Direction.EAST : Direction.NORTH; }
            case 'J' -> { return incoming == Direction.SOUTH ? Direction.WEST : Direction.NORTH; }
            case '7' -> { return incoming == Direction.EAST ? Direction.SOUTH : Direction.WEST; }
            case 'F' -> { return incoming == Direction.NORTH ? Direction.EAST : Direction.SOUTH; }
            case 'S' -> { return incoming; }
            default -> throw  new IllegalArgumentException("Unknown character");
        }
    }

    @Override
    public Object solveB(List<String> input) {
        var map = MapUtil.asMap(input);
        Point point = map.entrySet().stream().filter(e -> e.getValue() == 'S').findAny().orElseThrow().getKey();

        int steps =0;
        HashSet<Point> visited = new HashSet<>();
        for(Direction direction : Direction.values()) {
            Direction incomingDirection = direction;
            if(map.containsKey(point.getNext(direction)) &&
                    getDirections(map.get(point.getNext(direction))).contains(direction.reverse())) {
                while(map.get(point) != 'S' || steps==0) {
                    Direction nextDirection = getNextDirection(incomingDirection, map.get(point));

                    point = point.getNext(nextDirection);

                    visited.add(point);
                    steps++;
                    incomingDirection = nextDirection;
                }
            }
        }

        boolean west = map.containsKey(point.west()) && getDirections(map.get(point.west())).contains(Direction.EAST);
        boolean east = map.containsKey(point.east()) && getDirections(map.get(point.east())).contains(Direction.WEST);
        boolean north = map.containsKey(point.north()) && getDirections(map.get(point.north())).contains(Direction.SOUTH);
        boolean south = map.containsKey(point.south()) && getDirections(map.get(point.south())).contains(Direction.NORTH);

        if(west && east) map.put(point, '-');
        if(west && north) map.put(point, 'J');
        if(west && south) map.put(point, '7');

        if(east && south) map.put(point, 'F');
        if(east && north) map.put(point, 'L');

        if(south && north) map.put(point, '|');




        HashMap<Point, Character> cleanMap = MapUtil.getNewMap(MapUtil.getMaxX(map), MapUtil.getMaxY(map), '.');
        int minY=Integer.MAX_VALUE;
        Point topPoint=null;
        for(Point p : visited) {
            cleanMap.put(p, map.get(p));
            if(p.getY()<minY && map.get(p) == 'F') {
                minY=p.getY();
                topPoint=p;
            }
        }
        for(Direction outsideDir : getOutsides(map.get(topPoint), Direction.NORTH)) {
            if(cleanMap.getOrDefault(point.getNext(outsideDir), 'X') == '.') {
                cleanMap.put(point.getNext(outsideDir), 'O');
            }
        }
        if(topPoint == null) throw new IllegalArgumentException();
        point = topPoint.east();

        Direction incomingDirection = Direction.EAST;
        while(!point.equals(topPoint)) {
            Direction nextDirection = getNextDirection(incomingDirection, map.get(point));
            point = point.getNext(nextDirection);
            for(Direction outsideDir : getOutsides(map.get(point), nextDirection)) {
                if(cleanMap.getOrDefault(point.getNext(outsideDir), 'X') == '.') {
                    cleanMap.put(point.getNext(outsideDir), 'O');
                }
            }
            incomingDirection = nextDirection;
        }

        HashMap<Point,Character> newMap = new HashMap<>(cleanMap);
        boolean changed = true;
        while (changed) {
            changed = false;
            for (var entry : newMap.entrySet()) {
                if (entry.getValue() == '.') {
                    for (Point p : entry.getKey().getOrthogonalNeighbours2d()) {
                        if (newMap.containsKey(p) && newMap.get(p) == 'O') {
                            newMap.put(entry.getKey(), 'O');
                            changed = true;
                        }
                    }
                }
            }
        }
        return newMap.values().stream().filter(c -> c=='.').count();
    }

    private List<Direction> getOutsides(Character c, Direction direction) {
        switch (c) {
            case '|' -> { return direction == Direction.NORTH ? List.of(Direction.WEST) : List.of(Direction.EAST); }
            case '-' -> { return direction == Direction.EAST ? List.of(Direction.NORTH) : List.of(Direction.SOUTH); }
            case 'L' -> { return direction == Direction.SOUTH ? List.of() : List.of(Direction.SOUTH, Direction.WEST); }
            case 'J' -> { return direction == Direction.SOUTH ? List.of(Direction.WEST, Direction.SOUTH) : List.of(); }
            case '7' -> { return direction == Direction.EAST ? List.of(Direction.NORTH, Direction.EAST) : List.of(); }
            case 'F' -> { return direction == Direction.NORTH ? List.of(Direction.WEST, Direction.NORTH) : List.of(); }
            case 'S' -> { return List.of(); }
            default -> throw new IllegalArgumentException("Unknown character");
        }
    }
}
