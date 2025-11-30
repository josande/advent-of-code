package year2021.day09;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

public class Day09 {

    static Object solveA(List<String> values) {
        int xMax=values.get(0).length(), yMax=values.size();
        int[][] points = new int[xMax][yMax];
        for (int x = 0; x<xMax; x++) {
            for (int y=0;y<yMax;y++) {
                points[x][y]=Integer.parseInt(""+values.get(y).charAt(x));
            }
        }

        int risk=0;
        for (int x = 0; x<xMax; x++) {
            for (int y = 0; y < yMax; y++) {
                if((x==0 || points[x][y]<points[x-1][y])
               && (x==xMax-1 || points[x][y]<points[x+1][y])
               && (y==0 || points[x][y]<points[x][y-1])
               && (y==yMax-1 || points[x][y]<points[x][y+1])) {
                    risk+=points[x][y]+1;
                }
            }
        }


                return risk;
    }
    static Object solveB(List<String> values) {
        int xMax=values.get(0).length(), yMax=values.size();
       HashMap<Point, Integer> points= new HashMap<>();
        for (int x = 0; x<xMax; x++) {
            for (int y=0;y<yMax;y++) {
                points.put(new Point(x,y), Integer.parseInt(""+values.get(y).charAt(x)));
            }
        }
       List<Integer> basinSizes = new ArrayList<>();
       for(Point p : points.keySet()) {
           HashSet<Point> basinPoints = new HashSet<>();
           if(points.get(p) != 9) {
                basinPoints.addAll(findBasin(p, points));
           }
           basinSizes.add(basinPoints.size());
           for(Point bp : basinPoints) {
                points.put(bp, 9);
           }
        }

        Collections.sort(basinSizes);
        Collections.reverse(basinSizes);

        return basinSizes.get(0)*basinSizes.get(1)*basinSizes.get(2);
    }
    static  HashSet<Point> findBasin(Point p, HashMap<Point, Integer> points) {
        HashSet<Point> basinsPoints=new HashSet<>();

        HashSet<Point> pointsToCheck= new HashSet<>();
        pointsToCheck.add(p);
        while(!pointsToCheck.isEmpty()) {
            basinsPoints.addAll(pointsToCheck);
            HashSet<Point> newPointsToCheck=new HashSet<>();
            for (Point point : pointsToCheck) {
                if (!basinsPoints.contains(point.north()) && points.containsKey(point.north()) && points.get(point.north()) < 9) {
                    newPointsToCheck.add(point.north());
                }
                if (!basinsPoints.contains(point.south()) && points.containsKey(point.south()) && points.get(point.south()) < 9) {
                    newPointsToCheck.add(point.south());
                }
                if (!basinsPoints.contains(point.west()) && points.containsKey(point.west()) && points.get(point.west()) < 9) {
                    newPointsToCheck.add(point.west());
                }
                if (!basinsPoints.contains(point.east()) && points.containsKey(point.east()) && points.get(point.east()) < 9) {
                    newPointsToCheck.add(point.east());
                }
            }
            pointsToCheck = newPointsToCheck;
        }

        return new HashSet<>(basinsPoints);
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //439
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //900900
    }
}
