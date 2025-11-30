package year2021.day13;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day13 {

    static Object solveA(List<String> values) {

        List<String> foldInstructions = new ArrayList<>();

        HashSet<Point> map = new HashSet<>();

        for(var val : values) {
            if (val.isEmpty()) {
                continue;
            }

            if(val.startsWith("fold along")) {
                foldInstructions.add(val.split(" ")[2]);
            } else {
                String x= val.split(",")[0];
                String y= val.split(",")[1];
                Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
                map.add(p);
            }
        }

        map = doInstructions(map, foldInstructions, true);

        return map.size();
    }

    private static HashSet<Point> doInstructions(HashSet<Point> map, List<String> foldInstructions, boolean onlyFirst) {
        boolean first=true;
        for (String instruction : foldInstructions) {
            if (onlyFirst) {
                if (first) {
                    first = false;
                } else {
                    continue;
                }
            }
            int length = Integer.parseInt(instruction.split("=")[1]);
            boolean alongX = instruction.startsWith("x");
            HashSet<Point> newMap = new HashSet<>();

            if (alongX) {
                for(Point p : map) {
                    if (p.getX() > length) {
                        int diff = Math.abs(length - p.getX());
                        newMap.add(new Point(length - diff, p.getY()));
                    } else {
                        newMap.add(new Point(p.getX(), p.getY()));
                    }
                }
            } else {
                for(Point p : map) {
                    if(p.getY() > length) {
                        int diff = Math.abs(length-p.getY());
                        newMap.add(new Point(p.getX(), length-diff));
                    } else {
                        newMap.add(new Point(p.getX(), p.getY()));
                    }
                }
            }
            map = newMap;
        }
        return map;

    }

    static Object solveB(List<String> values) {
        List<String> foldInstructions = new ArrayList<>();
        HashSet<Point> map = new HashSet<>();

        for(var val : values) {
            if (val.isEmpty()) {
                continue;
            }

            if(val.startsWith("fold along")) {
                foldInstructions.add(val.split(" ")[2]);
            } else {
                String x= val.split(",")[0];
                String y= val.split(",")[1];
                Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
                map.add(p);
            }
        }

        map = doInstructions(map, foldInstructions, false);

        Point.print(map);
        return null;
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //708
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //EBLUBRFH
    }
}
