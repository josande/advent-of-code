package year2024.day08;


import utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day08 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day08());
    }
    @Override
    public Object solveA(List<String> input) {

        HashMap<Point, Character> map = MapUtil.asMap(input);
        HashMap<Character, ArrayList<Point>> antennas = new HashMap<>();
        for(var e : map.entrySet()) {
            if(e.getValue() != '.') {
                ArrayList<Point> locations = antennas.getOrDefault(e.getValue(), new ArrayList<>());
                locations.add(e.getKey());
                antennas.put(e.getValue(), locations);
            }
        }
        HashSet<Point> antiNodes = new HashSet<>();
        int maxX = MapUtil.getMaxX(map);
        int maxY = MapUtil.getMaxY(map);
        for(var e : antennas.entrySet()) {
            for(int i=0; i<e.getValue().size()-1; i++) {
                Point a = e.getValue().get(i);
                for(int j=i+1; j<e.getValue().size(); j++) {
                    Point b = e.getValue().get(j);
                    int dx = b.getX()-a.getX();
                    int dy = b.getY()-a.getY();
                    Point min1 = new Point(a.getX()-dx, a.getY()-dy);
                    Point min2 = new Point(b.getX()+dx, b.getY()+dy);

                    if(min1.getX()>=0 && min1.getX()<=maxX && min1.getY()>=0 && min1.getY()<=maxY) {
                        antiNodes.add(min1);
                    }
                    if(min2.getX()>=0 && min2.getX()<=maxX && min2.getY()>=0 && min2.getY()<=maxY) {
                        antiNodes.add(min2);
                    }
                }
            }

        }
        return antiNodes.size();
    }



    @Override
    public Object solveB(List<String> input) {

        HashMap<Point, Character> map = MapUtil.asMap(input);
        HashMap<Character, ArrayList<Point>> antennas = new HashMap<>();
        for (var e : map.entrySet()) {
            if (e.getValue() != '.') {
                ArrayList<Point> locations = antennas.getOrDefault(e.getValue(), new ArrayList<>());
                locations.add(e.getKey());
                antennas.put(e.getValue(), locations);
            }
        }
        HashSet<Point> antiNodes = new HashSet<>();
        int maxX = MapUtil.getMaxX(map);
        int maxY = MapUtil.getMaxY(map);
        for (var e : antennas.entrySet()) {
            for (int i = 0; i < e.getValue().size() - 1; i++) {
                Point a = e.getValue().get(i);
                for (int j = i + 1; j < e.getValue().size(); j++) {
                    Point b = e.getValue().get(j);
                    int dx = b.getX() - a.getX();
                    int dy = b.getY() - a.getY();
                    int gcd = MathTools.getGcd(dx, dy);
                    dx = dx/gcd;
                    dy = dy/gcd;

                    int x = a.getX();
                    int y = a.getY();
                    while(x>=0 && y>=0 && x<=maxX && y<=maxY) {
                        antiNodes.add(new Point(x,y));
                        x-=dx;
                        y-=dy;
                    }
                    x = a.getX();
                    y = a.getY();
                    while(x>=0 && y>=0 && x<=maxX && y<=maxY) {
                        antiNodes.add(new Point(x,y));
                        x+=dx;
                        y+=dy;
                    }
                }
            }
        }
        return antiNodes.size();
    }


}
