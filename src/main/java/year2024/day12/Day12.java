
package year2024.day12;

import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day12 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day12());
    }

    private record Plot(HashSet<Point> inside, Character character){
        void fill(HashMap<Point, Character> fullMap) {
            HashSet<Point> toIterate = new HashSet<>(inside);
            while (!toIterate.isEmpty()) {
                HashSet<Point> added = new HashSet<>();
                while (!toIterate.isEmpty()) {
                    Point p1=toIterate.iterator().next();
                    for (Point p : p1.getOrthogonalNeighbours2d()) {
                        if (fullMap.get(p) == character && !inside.contains(p))
                            added.add(p);
                        toIterate.remove(p1);
                    }
                }
                inside.addAll(added);
                toIterate=added;
            }
        }
        private int getBorderSize() {
            int borderSize=0;
            for(Point p : inside) {
                for (Point n: p.getOrthogonalNeighbours2d())
                    if(!inside.contains(n))
                        borderSize++;
            }
            return borderSize;
        }

        public int getNumberOrBorders() {
            int numberOfBorders=0;
            for(Point p : inside) {
                if(!inside.contains(p.north()) && (!inside.contains(p.west())  || inside.contains(p.west().north())))
                    numberOfBorders++;

                if(!inside.contains(p.east())  && (!inside.contains(p.north()) || inside.contains(p.north().east())))
                    numberOfBorders++;

                if(!inside.contains(p.south()) && (!inside.contains(p.west())  || inside.contains(p.west().south())))
                    numberOfBorders++;

                if(!inside.contains(p.west())  && (!inside.contains(p.south()) || inside.contains(p.south().west())))
                    numberOfBorders++;
            }
            return numberOfBorders;
        }
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<Point, Character> fullMap = MapUtil.asMap(input);
        HashMap<Point, Character> tempMap = new HashMap<>(fullMap);
        HashSet<Plot> plots = new HashSet<>();
        while (!tempMap.isEmpty()) {
            var entry = tempMap.entrySet().iterator().next();
            Plot plot = new Plot(new HashSet<>(), entry.getValue());
            plot.inside.add(entry.getKey());
            plot.fill(fullMap);
            for(Point p : plot.inside){
                tempMap.remove(p);
            }
            plots.add(plot);
        }

        long result =0L;
        for(Plot p : plots) {
            result+=((long) p.inside.size() * p.getBorderSize());
        }

        return result;  //1546338
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<Point, Character> fullMap = MapUtil.asMap(input);
        HashMap<Point, Character> tempMap = new HashMap<>(fullMap);
        HashSet<Plot> plots = new HashSet<>();
        while (!tempMap.isEmpty()) {
            var entry = tempMap.entrySet().iterator().next();
            Plot plot = new Plot(new HashSet<>(), entry.getValue());
            plot.inside.add(entry.getKey());
            plot.fill(fullMap);
            for(Point p : plot.inside){
                tempMap.remove(p);
            }
            plots.add(plot);
        }

        long result =0L;
        for(Plot p : plots) {
            result+=((long) p.inside.size() * p.getNumberOrBorders());
        }

        return result;
    }
}
