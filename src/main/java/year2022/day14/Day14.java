package year2022.day14;

import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day14 {

    static Object solveA(List<String> values) {

        HashMap<Point, Character> map = new HashMap<>();

        for(String s : values) {
            for(Point p : asPoints(s)) {
                map.put(p, '#');
            }
        }

        addSand(map);

        int sandCount=0;
        for(Map.Entry<Point, Character> e : map.entrySet()) {
            if(e.getValue()=='o')
                sandCount++;
        }
       return sandCount;
    }

    static void addSand(HashMap<Point, Character> map) {
        Point sand = new Point(500,0);
        int maxY = MapUtil.getMaxY(map);
        while(true) {
            if(sand.getY() >= maxY) {
                return;
            }
            if(map.containsKey(sand)) {
                return;
            }
            if (!map.containsKey(sand.south())) {
                sand = sand.south();
            } else if(!map.containsKey(sand.south().west())) {
                sand = sand.south().west();
            } else if(!map.containsKey(sand.south().east())) {
                sand = sand.south().east();
            } else {
                map.put(sand, 'o');
                sand = new Point(500,0);
            }
        }


    }



    static Object solveB(List<String> values) {

        HashMap<Point, Character> map = new HashMap<>();

        for(String s : values) {
            for(Point p : asPoints(s)) {
                map.put(p, '#');
            }
        }

        int maxY= MapUtil.getMaxY(map);

        int minX= MapUtil.getMinX(map);
        int maxX= MapUtil.getMaxX(map);

        String floor =  (minX-(maxY+2))+","+(maxY+2)+" -> "+(maxX+(maxY+2))+","+(maxY+2);
            for(Point p : asPoints(floor)) {
                map.put(p, '#');
            }

        addSand(map);

        int sandCount=0;
        for(Map.Entry<Point, Character> e : map.entrySet()) {
            if(e.getValue()=='o')
                sandCount++;
        }
        return sandCount;
    }
    static HashSet<Point> asPoints(String string) {

        String[] parts = string.split(" -> ");

        HashSet<Point> points = new HashSet<>();

        for(int i=1;i<parts.length; i++) {
            Point start = new Point(parts[i-1]);
            Point end = new Point(parts[i]);

            points.add(start);

            int x = start.getX();
            int y = start.getY();

            while(x < end.getX()) {
                x++;
                points.add(new Point(x,y));
            }
            while(x > end.getX()) {
                x--;
                points.add(new Point(x,y));
            }
            while(y < end.getY()) {
                y++;
                points.add(new Point(x,y));
            }
            while(y > end.getY()) {
                y--;
                points.add(new Point(x,y));
            }

        }
        return points;
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //964
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //32041
    }
}
