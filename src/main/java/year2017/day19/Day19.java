package year2017.day19;

import utils.AdventOfCode;
import utils.Direction;
import utils.Point;
import utils.Reporter;

import java.util.HashMap;
import java.util.List;

public class Day19 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day19());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<Point,Character> map = new HashMap<>();
        for(int y=0; y < input.size(); y++) {
            for(int x=0; x<input.get(y).length(); x++) {
                char c = input.get(y).charAt(x);
                if(c != ' ') map.put(new Point(x, y), c);
            }
        }

        Point current = map.keySet().stream().filter(p -> p.getY()==0).findAny().orElseThrow(IllegalArgumentException::new);
        StringBuilder result = new StringBuilder();
        Direction direction = Direction.SOUTH;
        while(map.containsKey(current)) {
            Character c = map.get(current);
            if(c=='+') {
                if(map.containsKey(getNext(current, direction.left()))) { direction = direction.left(); }
                else if (map.containsKey(getNext(current, direction.right()))) { direction = direction.right();}
                else { throw new IllegalStateException("Don't know where to go from "+current); }
            } else if (c=='|' || c=='-') {
            } else { result.append(c); }
            current = getNext(current, direction);
        }
        return result.toString();
    }
    private Point getNext(Point point, Direction direction) {
        switch (direction) {
            case NORTH -> {return point.north(); }
            case EAST  -> {return point.east(); }
            case SOUTH -> {return point.south(); }
            case WEST -> {return point.west(); }
            default -> throw new IllegalStateException("Unknown direction "+direction);
        }
    }


    @Override
    public Object solveB(List<String> input) {
        HashMap<Point,Character> map = new HashMap<>();
        for(int y=0; y < input.size(); y++) {
            for(int x=0; x<input.get(y).length(); x++) {
                char c = input.get(y).charAt(x);
                if(c != ' ') map.put(new Point(x, y), c);
            }
        }

        Point current = map.keySet().stream().filter(p -> p.getY()==0).findAny().orElseThrow(IllegalArgumentException::new);
        Direction direction = Direction.SOUTH;
        int steps=0;
        while(map.containsKey(current)) {
            steps++;
            Character c = map.get(current);
            if(c=='+') {
                if(map.containsKey(getNext(current, direction.left()))) { direction = direction.left(); }
                else if (map.containsKey(getNext(current, direction.right()))) { direction = direction.right(); }
                else { throw new IllegalStateException("Don't know where to go from "+current); }
            }
            current = getNext(current, direction);
        }
        return steps;
    }
}
