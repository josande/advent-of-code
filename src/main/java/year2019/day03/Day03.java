package year2019.day03;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

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
                int length=Integer.parseInt(instruction.substring(1));
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
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        HashMap<Point, Integer> wireA= stringToPoints(inputs.get(0));
        HashMap<Point, Integer> wireB = stringToPoints(inputs.get(1));

        System.out.println("Day03A "+getClosestDistance(wireA, wireB)); // 3229
        System.out.println("Day03B "+getShortestWalk(wireA, wireB)); // 32132
    }
}
