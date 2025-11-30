package year2021.day15;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day15 {

    static Object solveA(List<String> values) {
        int maxX=values.get(0).length();
        int maxY=values.size();
        HashMap<Point, Integer> riskMap = new HashMap<>();
        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                int risk = Integer.parseInt(""+values.get(y).charAt(x));
                riskMap.put(new Point(x,y), risk);

            }
        }

        Point start = new Point(0,0);
        Point goal = new Point(maxX-1, maxY-1);
        HashMap<Point, Integer> bestSoFar = new HashMap<>();
        bestSoFar.put(start, 0);

        Queue<Point> leftToTest = new ArrayDeque<>();
        leftToTest.add(start);
        int lowestRiskSoFar = Integer.MAX_VALUE;

        while(!leftToTest.isEmpty()) {
            Point current = leftToTest.poll();
            int riskSoFar = bestSoFar.get(current);
            if(riskSoFar>=lowestRiskSoFar)
                continue;

            for(Point next : current.getOrthogonalNeighbours2d()) {
                if(next.getX()<0 || next.getX() >= maxX
                || next.getY()<0 || next.getY() >= maxY)
                    continue;
                int risk = riskSoFar + riskMap.get(next);
                if(risk >= lowestRiskSoFar )
                    continue;
                if(bestSoFar.containsKey(next) && bestSoFar.get(next)<risk) {
                    continue;
                }
                bestSoFar.put(next, risk);
                if(next.equals(goal)) {
                    lowestRiskSoFar = risk;
                }
                if(!leftToTest.contains(next))
                    leftToTest.add(next);
            }
        }


        return bestSoFar.get(goal);
    }
    static Object solveB(List<String> values) {

        int maxX=values.get(0).length();
        int maxY=values.size();
        HashMap<Point, Integer> riskMap = new HashMap<>();
        for(int y=0; y<maxY*5; y++) {
            for(int x=0; x<maxX*5; x++) {
                int risk = Integer.parseInt(""+values.get(y%maxY).charAt(x%maxX))+Math.floorDiv(x, maxX)+Math.floorDiv(y, maxY);
                if (risk>9) risk-=9;
                riskMap.put(new Point(x,y), risk);
            }
        }

        Point start = new Point(0,0);
        Point goal = new Point(maxX*5-1, maxY*5-1);
        HashMap<Point, Integer> bestSoFar = new HashMap<>();
        bestSoFar.put(start, 0);

        Queue<Point> leftToTest = new ArrayDeque<>();
        leftToTest.add(start);

        int lowestRiskSoFar = Integer.MAX_VALUE;

        while(!leftToTest.isEmpty()) {
            Point current = leftToTest.poll();
            int riskSoFar = bestSoFar.get(current);
            if (riskSoFar >= lowestRiskSoFar)
                continue;

            for (Point next : current.getOrthogonalNeighbours2d()) {
                if (next.getX() < 0 || next.getX() >= maxX*5 ||
                    next.getY() < 0 || next.getY() >= maxY*5) {
                    continue;
                }

                int risk = riskSoFar + riskMap.get(next);
                if (risk >= lowestRiskSoFar )
                    continue;
                if (bestSoFar.containsKey(next) && bestSoFar.get(next) < risk)
                    continue;

                bestSoFar.put(next, risk);
                if (next.equals(goal)) {
                    lowestRiskSoFar = risk;
                }
                if (!leftToTest.contains(next)) {
                    leftToTest.add(next);
                }
            }
        }


        return bestSoFar.get(goal);
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
        // var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //423
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //2778
    }
}
