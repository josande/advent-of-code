package year2021.day05;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day05 {

    static Object solveA(List<String> values) {
        HashMap<Point, Integer> vents = new HashMap<>();
        for(var val : values) {
            String[] pipe = val.split(" -> ");
            Point start = new Point(pipe[0].split(","));
            Point end = new Point(pipe[1].split(","));

            if(start.getX()==end.getX()) {
                for(int y = Math.min(start.getY(), end.getY()); y<=Math.max(start.getY(), end.getY()); y++) {
                    Point p = new Point(start.getX(), y);
                    vents.put(p, vents.getOrDefault(p, 0) + 1);
                }
            } else if (start.getY()==end.getY()) {
                for(int x = Math.min(start.getX(), end.getX()); x<=Math.max(start.getX(), end.getX()); x++) {
                    Point p = new Point(x, start.getY());
                    vents.put(p, vents.getOrDefault(p, 0)+1);
                }
            }
        }
        return (int) vents.values().stream().filter(i -> i >= 2).count();
    }
    static Object solveB(List<String> values) {
        HashMap<Point, Integer> vents = new HashMap<>();
        for(var val : values) {
            String[] pipe = val.split(" -> ");
            Point start = new Point(pipe[0].split(","));
            Point end = new Point(pipe[1].split(","));


            if(start.getX()==end.getX()) {
                for(int y = Math.min(start.getY(), end.getY()); y<=Math.max(start.getY(), end.getY()); y++) {
                    Point p = new Point(start.getX(), y);
                    vents.put(p, vents.getOrDefault(p, 0) + 1);
                }
            } else if (start.getY()==end.getY()) {
                for(int x = Math.min(start.getX(), end.getX()); x<=Math.max(start.getX(), end.getX()); x++) {
                    Point p = new Point(x, start.getY());
                    vents.put(p, vents.getOrDefault(p, 0)+1);
                }
            } else {
                int y = start.getY();
                int x = start.getX();
                for(int i=0; i<=Math.abs(start.getX()-end.getX()); i++){
                    Point p = new Point(x, y);
                    vents.put(p, vents .getOrDefault(p, 0) + 1);
                    if (start.getX()>end.getX()) x--;
                    else x++;
                    if (start.getY()>end.getY()) y--;
                    else y++;
                }
            }
        }
        return (int) vents.values().stream().filter(i -> i >= 2).count();
    }

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //7297
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //21038
    }
}
