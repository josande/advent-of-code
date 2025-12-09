package year2025.day09;

import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.*;

public class Day09 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day09());
    }

    @Override
    public Object solveA(List<String> input) {
        List<Point> points = new ArrayList<>();
        for(var line : input) {
            points.add(new Point(line));
        }
        long largest =0;
        for(int i=0; i<points.size(); i++) {
            for (int j = i+1; j < points.size(); j++) {
                long tmp = (1+(long) Math.abs(points.get(i).getX() - points.get(j).getX())) *
                           (1+(long) Math.abs(points.get(i).getY() - points.get(j).getY()));
                largest = Math.max(largest, tmp);
            }
        }
        return largest;
    }

    @Override
    public Object solveB(List<String> input) {
        List<Point> points = new ArrayList<>();
        List<Point> leftHandSide = new ArrayList<>();
        List<Point> rightHandSide = new ArrayList<>();
        SortedMap<Long, Pair<Point, Point>> areas = new TreeMap<>(Collections.reverseOrder());
        for(String s : input) {
            Point p0 = new Point(s);

            for(var p1 : points) {
                long size = (1 + (long) Math.abs(p0.getX() - p1.getX())) * (1 + (long) Math.abs(p0.getY() - p1.getY()));
                areas.put(size, Pair.of(p0, p1));
            }
            points.add(p0);

        }
        HashSet<Point> shape = new HashSet<>(points);


        for(int i=0; i<input.size(); i++) {

            Point p0 = points.get(i);
            Point p1 = points.get(i == input.size() - 1 ? 0 : i+1);

            points.add(p0);
            var line = MapUtil.getPointsBetween(p0, p1);

            shape.addAll(line);
            line.add(p0);
            line.add(p1);

            if(p0.isNorthOf(p1)) {
                leftHandSide.addAll (line.stream().map(p->p.west()).toList());
                rightHandSide.addAll(line.stream().map(p->p.east()).toList());
            }
            if(p0.isEastOf(p1)) {
                leftHandSide.addAll (line.stream().map(p->p.north()).toList());
                rightHandSide.addAll(line.stream().map(p->p.south()).toList());
            }
            if(p0.isSouthOf(p1)) {
                leftHandSide.addAll (line.stream().map(p->p.east()).toList());
                rightHandSide.addAll(line.stream().map(p->p.west()).toList());
            }
            if(p0.isWestOf(p1)) {
                leftHandSide.addAll (line.stream().map(p->p.south()).toList());
                rightHandSide.addAll(line.stream().map(p->p.north()).toList());
            }
        }

        leftHandSide.removeAll(shape);
        rightHandSide.removeAll(shape);

        Point outsidePoint;
        {
            int minY = Integer.MAX_VALUE;
            int minX = Integer.MAX_VALUE;
            for(Point p : points) {
                minY = Math.min(minY, p.getY());
            }
            for(Point p : points) {
                if(p.getY() == minY) {
                    minX = Math.min(minX, p.getX());
                }
            }
            outsidePoint = new Point(minX-1, minY);
        }

        HashSet<Point> outside;

        if(leftHandSide.contains(outsidePoint)) {
            outside = new HashSet<>(leftHandSide);
        } else {
            outside = new HashSet<>(rightHandSide);
        }

        for(var es : areas.entrySet()) {
            Point p0 = es.getValue().getLeft();
            Point p1 = es.getValue().getRight();
            int maxX = Math.max(p0.getX(), p1.getX());
            int maxY = Math.max(p0.getY(), p1.getY());
            int minX = Math.min(p0.getX(), p1.getX());
            int minY = Math.min(p0.getY(), p1.getY());
            if(!outside.stream().anyMatch(p->p.getX()>=minX && p.getX()<=maxX && p.getY()>=minY && p.getY()<=maxY)) {
                return es.getKey();
            }
        }
        return null;
    }
}
