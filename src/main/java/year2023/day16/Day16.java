
package year2023.day16;

import utils.*;

import java.util.*;

public class Day16 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day16());
    }

    @Override
    public Object solveA(List<String> input) {
        var map = MapUtil.asMap(input);
        Point start = new Point(-1,0);
        Direction direction = Direction.EAST;

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(start, direction));
        HashSet<Point> allVisited = new HashSet<>();
        HashSet<State> visitedStates = new HashSet<>();
        while(!queue.isEmpty()) {
            State current = queue.poll();
            if(visitedStates.contains(current)) continue;
            visitedStates.add(current);
            allVisited.add(current.point);
            queue.addAll(getNextStates(current, map));
        }

        return allVisited.size()-1;
    }
    private List<State> getNextStates(State state, HashMap<Point, Character> map) {
        Point nextPoint = state.point.getNext(state.direction);
        if(!map.containsKey(nextPoint)) return List.of();
        char c = map.get(nextPoint);
        Direction direction = state.direction;
        switch (c) {
            case '.' -> {return List.of(new State(nextPoint, direction));}
            case '/' -> {
                switch ((direction)) {
                    case NORTH -> {return List.of(new State(nextPoint, Direction.EAST));}
                    case SOUTH -> {return List.of(new State(nextPoint, Direction.WEST));}
                    case EAST -> {return List.of(new State(nextPoint, Direction.NORTH));}
                    case WEST -> {return List.of(new State(nextPoint, Direction.SOUTH));}
                }
            }
            case '\\' -> {
                switch ((direction)) {
                    case NORTH -> {return List.of(new State(nextPoint, Direction.WEST));}
                    case SOUTH -> {return List.of(new State(nextPoint, Direction.EAST));}
                    case EAST -> {return List.of(new State(nextPoint, Direction.SOUTH));}
                    case WEST -> {return List.of(new State(nextPoint, Direction.NORTH));}
                }
            }
            case '|' -> {
                switch ((direction)) {
                    case NORTH, SOUTH -> { return List.of(new State(nextPoint, direction)); }
                    case EAST, WEST -> { return List.of(new State(nextPoint, Direction.NORTH), new State(nextPoint, Direction.SOUTH)); }
                }
            }
            case '-' -> {
                switch ((direction)) {
                    case NORTH, SOUTH -> { return List.of(new State(nextPoint, Direction.WEST), new State(nextPoint, Direction.EAST));}
                    case EAST, WEST -> { return List.of(new State(nextPoint, direction)); }
                }
            }
            default -> throw new IllegalArgumentException("Unknown character "+ c);
        }

        throw new IllegalStateException();
    }
    record State(Point point, Direction direction){}

    @Override
    public Object solveB(List<String> input) {
        var map = MapUtil.asMap(input);

        int highestResult=0;
        for(int y=0; y<MapUtil.getMaxY(map); y++) {
            Point start = new Point(-1,y);
            Direction direction = Direction.EAST;

            Queue<State> queue = new LinkedList<>();
            queue.add(new State(start, direction));
            HashSet<Point> allVisited = new HashSet<>();
            HashSet<State> visitedStates = new HashSet<>();
            while(!queue.isEmpty()) {
                State current = queue.poll();
                if(visitedStates.contains(current)) continue;
                visitedStates.add(current);
                allVisited.add(current.point);
                queue.addAll(getNextStates(current, map));
            }
            highestResult= Math.max(allVisited.size(), highestResult);
        }
        for(int y=0; y<MapUtil.getMaxY(map); y++) {
            Point start = new Point(MapUtil.getMaxX(map)+1,y);
            Direction direction = Direction.WEST;

            Queue<State> queue = new LinkedList<>();
            queue.add(new State(start, direction));
            HashSet<Point> allVisited = new HashSet<>();
            HashSet<State> visitedStates = new HashSet<>();
            while(!queue.isEmpty()) {
                State current = queue.poll();
                if(visitedStates.contains(current)) continue;
                visitedStates.add(current);
                allVisited.add(current.point);
                queue.addAll(getNextStates(current, map));
            }
            highestResult= Math.max(allVisited.size(), highestResult);
        }
        for(int x=0; x<MapUtil.getMaxX(map); x++) {
            Point start = new Point(x,-1);
            Direction direction = Direction.SOUTH;

            Queue<State> queue = new LinkedList<>();
            queue.add(new State(start, direction));
            HashSet<Point> allVisited = new HashSet<>();
            HashSet<State> visitedStates = new HashSet<>();
            while(!queue.isEmpty()) {
                State current = queue.poll();
                if(visitedStates.contains(current)) continue;
                visitedStates.add(current);
                allVisited.add(current.point);
                queue.addAll(getNextStates(current, map));
            }
            highestResult= Math.max(allVisited.size(), highestResult);
        }
        for(int x=0; x<MapUtil.getMaxX(map); x++) {
            Point start = new Point(x,MapUtil.getMaxY(map)+1);
            Direction direction = Direction.NORTH;

            Queue<State> queue = new LinkedList<>();
            queue.add(new State(start, direction));
            HashSet<Point> allVisited = new HashSet<>();
            HashSet<State> visitedStates = new HashSet<>();
            while(!queue.isEmpty()) {
                State current = queue.poll();
                if(visitedStates.contains(current)) continue;
                visitedStates.add(current);
                allVisited.add(current.point);
                queue.addAll(getNextStates(current, map));
            }
            highestResult= Math.max(allVisited.size(), highestResult);
        }
        return highestResult-1;
    }
}
