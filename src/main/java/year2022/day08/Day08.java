package year2022.day08;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day08 {

    static Object solveA(List<String> values) {
        HashMap<Point, Integer> map=new HashMap<>();
        for (int y=0; y<values.size(); y++) {
            for (int x =0; x<values.get(y).length(); x++) {
                int c = Integer.parseInt(""+values.get(y).charAt(x));
                Point p = new Point(x,y);
                map.put(p, c);
            }
        }
        int result=0;
        for(Point e : map.keySet()) {

            if(isSeen(e, map))
                result++;

        }

        return result;
    }
    static boolean isSeen(Point p, HashMap<Point, Integer> map) {
        return canBeSeenFromNorth(p, map) ||
               canBeSeenFromEast(p, map) ||
               canBeSeenFromSouth(p, map) ||
               canBeSeenFromWest(p, map);
    }
    public static boolean canBeSeenFromNorth(Point p, HashMap<Point, Integer> map) {
        int value = map.get(p);
        Point north = p.north();
        while (map.containsKey(north)) {
            if(map.get(north) < value) {
                north=north.north();
            } else {
                return false;
            }
        }
        return true;
    }
    public static boolean canBeSeenFromEast(Point p, HashMap<Point, Integer> map) {
        int value = map.get(p);
        Point east = p.east();
        while (map.containsKey(east)) {
            if(map.get(east) < value) {
                east=east.east();
            } else {
                return false;
            }
        }
        return true;
    }
    public static  boolean canBeSeenFromWest(Point p, HashMap<Point, Integer> map) {
        int value = map.get(p);
        Point west = p.west();
        while (map.containsKey(west)) {
            if(map.get(west) < value) {
                west=west.west();
            } else {
                return false;
            }
        }
        return true;
    }
    public static  boolean canBeSeenFromSouth(Point p, HashMap<Point, Integer> map) {
        int value = map.get(p);
        Point south = p.south();
        while (map.containsKey(south)) {
            if(map.get(south) < value) {
                south = south.south();
            } else {
                return false;
            }
        }
        return true;
    }

    static Object solveB(List<String> values) {
        HashMap<Point, Integer> map=new HashMap<>();
        for (int y=0; y<values.size(); y++) {
            for (int x =0; x<values.get(y).length(); x++) {
                int c = Integer.parseInt(""+values.get(y).charAt(x));
                Point p = new Point(x,y);
                map.put(p, c);
            }
        }
        long bestSoFar=0;
        for(Point p : map.keySet()) {
            long currentScore=pointsNorth(p, map) *
                              pointsEast(p, map) *
                              pointsSouth(p, map) *
                              pointsWest(p, map);
            bestSoFar = Math.max(bestSoFar, currentScore);

        }
        return bestSoFar;
    }


    public static long pointsNorth(Point p, HashMap<Point, Integer> map) {
        int value = map.get(p);
        Point nextPoint = p.north();
        long score=0;
        while (map.containsKey(nextPoint)) {
            score++;
            if(map.get(nextPoint) < value) {
                nextPoint=nextPoint.north();
            } else {
                return score;
            }
        }
        return score;
    }    public static long pointsEast(Point p, HashMap<Point, Integer> map) {
        int value = map.get(p);
        Point nextPoint = p.east();
        long score=0;
        while (map.containsKey(nextPoint)) {
            score++;
            if(map.get(nextPoint) < value) {
                nextPoint=nextPoint.east();
            } else {
                return score;
            }
        }
        return score;
    }
    public static long pointsSouth(Point p, HashMap<Point, Integer> map) {
        int value = map.get(p);
        Point nextPoint = p.south();
        long score=0;
        while (map.containsKey(nextPoint)) {
            score++;
            if(map.get(nextPoint) < value) {
                nextPoint=nextPoint.south();
            } else {
                return score;
            }
        }
        return score;
    }
    public static long pointsWest(Point p, HashMap<Point, Integer> map) {
        int value = map.get(p);
        Point nextPoint = p.west();
        long score=0;
        while (map.containsKey(nextPoint)) {
            if(map.get(nextPoint) < value) {
                score++;
                nextPoint=nextPoint.west();
            } else {
                return score;
            }
        }
        return score;
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //1870
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //517440
    }
}
