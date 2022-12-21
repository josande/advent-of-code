package year2022.day18;

import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day18 {

    static Object solveA(List<String> values) {
        HashSet<Point> points = new HashSet<>();
        for(String string : values) {
            points.add(new Point(string));
        }
        int result=0;
        for(Point p : points) {
            for(Point n : p.getOrthogonalNeighbours3d()) {
                if(!points.contains(n)) {
                    result++;
                }
            }
        }

        return result;
    }
    static Object solveB(List<String> values) {
        HashSet<Point> points = new HashSet<>();
        for(String string : values) {
            points.add(new Point(string));
        }

        int minX = MapUtil.getMinX(points);
        int minY = MapUtil.getMinY(points);
        int minZ = MapUtil.getMinZ(points);
        int maxX = MapUtil.getMaxX(points);
        int maxY = MapUtil.getMaxY(points);
        int maxZ= MapUtil.getMaxZ(points);

        HashSet<Point> outside = new HashSet<>();

        Point start1 = new Point(minX-1, minY, minZ);
        Point start2 = new Point(maxX, maxY+1, maxY);
        Point start3 = new Point(minX, minY, minZ-1);

        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start1);
        queue.add(start2);
        queue.add(start3);

        while (!queue.isEmpty()) {
            Point current=queue.poll();
            for(Point np : current.getOrthogonalNeighbours3d()) {
                if (np.getX() < minX-1 || np.getX() > maxX+1) continue;
                if (np.getY() < minY-1 || np.getY() > maxY+1) continue;
                if (np.getZ() < minZ-1 || np.getZ() > maxZ+1) continue;
                if (outside.contains(np)) continue;
                if (points.contains(np)) continue;
                outside.add(np);
                queue.add(np);
            }
        }



       int result=0;
        for(Point p : points) {
            for(Point n : p.getOrthogonalNeighbours3d()) {
                if(outside.contains(n)) {
                    result++;
                }
            }
        }
        return result;
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 4320
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 2456
    }
}
