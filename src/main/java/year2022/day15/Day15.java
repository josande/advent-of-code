package year2022.day15;

import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day15 {

    static Object solveA(List<String> values) {
        return solveA(values, 2000000);
    }
    static Object solveA(List<String> values, int row) {
        HashMap<Point, Integer> sensors = new HashMap<>();
        HashSet<Point> beacons = new HashSet<>();
        for (String str : values) {
            String[] parts = str.split(" ");
            int x1 = Integer.parseInt(parts[2].substring(2, parts[2].length() - 1));
            int y1 = Integer.parseInt(parts[3].substring(2, parts[3].length() - 1));
            int x2 = Integer.parseInt(parts[8].substring(2, parts[8].length() - 1));
            int y2 = Integer.parseInt(parts[9].substring(2, parts[9].length()));

            Point s = new Point(x1, y1);
            Point b = new Point(x2, y2);
            int distance = s.getManhattanDistance(b);

            sensors.put(s, distance);
            beacons.add(b);
        }

    int maxDistance = 0;

        for(Map.Entry<Point, Integer> e : sensors.entrySet()) {
            maxDistance = Math.max(maxDistance, e.getValue());
        }


        int counter=0;
        for(int x=MapUtil.getMinX(sensors)-maxDistance; x<=MapUtil.getMaxX(sensors)+maxDistance; x++) {
            boolean tooClose=false;
            Point p = new Point(x, row);
            if(beacons.contains(p)) continue;
            if(sensors.containsKey(p)) continue;
            for(Map.Entry<Point, Integer> e : sensors.entrySet()) {
                if(p.getManhattanDistance(e.getKey()) <= e.getValue()) {
                    tooClose=true;
                    break;
                }
            }
            if (tooClose) {
                counter++;
            }
        }

        return counter;
    }

    static Object solveB(List<String> values) {
        return solveB(values, 4000000);
    }
    static Object solveB(List<String> values, int max ) {


       HashMap<Point, Integer> sensors = new HashMap<>();
       HashSet<Point> borders = new HashSet<>();

       for (String str : values) {
           String[] parts = str.split(" ");
           int x1 = Integer.parseInt(parts[2].substring(2, parts[2].length() - 1));
           int y1 = Integer.parseInt(parts[3].substring(2, parts[3].length() - 1));
           int x2 = Integer.parseInt(parts[8].substring(2, parts[8].length() - 1));
           int y2 = Integer.parseInt(parts[9].substring(2, parts[9].length()));

           Point s = new Point(x1, y1);
           Point b = new Point(x2, y2);
           int distance = s.getManhattanDistance(b);

           sensors.put(s, distance);
           borders.addAll(MapUtil.getPointsAtManhattanDistance(s, distance+1, 0, max, 0, max ));
        }

        borders.removeAll(sensors.keySet());

        for(Point p : borders) {
            boolean tooClose = false;

            for(Map.Entry<Point, Integer> e : sensors.entrySet()) {
                if(p.getManhattanDistance(e.getKey()) <= e.getValue()) {
                    tooClose = true;
                    break;
                }
            }
            if(!tooClose)
                return (long) p.getX()*4000000+p.getY();
       }
       return -1;
    }
    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //5688618
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //12625383204261
    }
}
