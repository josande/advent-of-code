package year2019.day03;

import utils.FileHelper;
import utils.Point;

import java.util.HashMap;
import java.util.List;

public class Day03 {

    static int getClosestDistance(HashMap<Point, Integer>  wireA, HashMap<Point, Integer>  wireB) {
        wireA.keySet().retainAll(wireB.keySet());
        Point start = new Point(0,0);
        int distance = Integer.MAX_VALUE;
        for (Point p : wireA.keySet()) {
            distance = Math.min(distance, p.getManhattanDistance(start));
        }
        return distance;//
    }
    static int getShortestWalk(HashMap<Point, Integer> wireA, HashMap<Point, Integer> wireB) {
        wireA.keySet().retainAll(wireB.keySet());
        int walk = Integer.MAX_VALUE;
        for (Point p : wireA.keySet()) {
            walk = Math.min(walk, wireA.get(p) + wireB.get(p));
        }
        return walk;
    }
        static HashMap<Point, Integer> stringToPoints(String string) {
            HashMap<Point, Integer> wire=new HashMap<>();
            int x=0, y=0, steps=0;
            for (String instruction : string.split(",")) {
                char direction = instruction.charAt(0);
                int length=Integer.valueOf(instruction.substring(1));
                for (int s=0; s<length;s++) {
                    steps++;
                    if (direction == 'R') { x++; }
                    if (direction == 'L') { x--; }
                    if (direction == 'U') { y--; }
                    if (direction == 'D') { y++; }
                    wire.putIfAbsent(new Point(x,y), steps);
                }
            }
        return wire;
    }

    public static void main(String[] args){
        List<String> wires = new FileHelper().readFile("year2019/day03/input.txt");
        HashMap<Point, Integer> wireA= stringToPoints(wires.get(0));
        HashMap<Point, Integer> wireB = stringToPoints(wires.get(1));

        System.out.println("Day03A "+getClosestDistance(wireA, wireB));
        System.out.println("Day03B "+getShortestWalk(wireA, wireB));
    }
}
