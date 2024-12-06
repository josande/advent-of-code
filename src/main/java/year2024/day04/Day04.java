package year2024.day04;

import utils.*;

import java.util.HashMap;
import java.util.List;

public class Day04 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day04());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<Point,Character> map =  MapUtil.asMap(input);

        int matches=0;

        for(int row=0;row<=MapUtil.getMaxY(map);row++){
            for(int col=0;col<=MapUtil.getMaxX(map);col++){
                Point p = new Point(row,col);
                matches+= findMatches(p, map);
            }
        }

        return matches;
    }

    private Integer findMatches(Point p, HashMap<Point, Character> map) {
        int matches = 0;

        matches+=check(p, map, "n")?1:0;
        matches+=check(p, map, "w")?1:0;
        matches+=check(p, map, "s")?1:0;
        matches+=check(p, map, "e")?1:0;
        matches+=check(p, map, "nw")?1:0;
        matches+=check(p, map, "ne")?1:0;
        matches+=check(p, map, "sw")?1:0;
        matches+=check(p, map, "se")?1:0;

        return matches;
    }

    private boolean check(Point p, HashMap<Point, Character> map, String direction) {
        String searchFor = "XMAS";
        Point searchAt  = new Point(p);
        for (char c : searchFor.toCharArray()) {
            if(!map.containsKey(searchAt))
                return false;
            if(map.get(searchAt) != c)
                return false;
            searchAt = switch (direction) {
                case "n" -> searchAt.north();
                case "w" -> searchAt.west();
                case "s" -> searchAt.south();
                case "e" -> searchAt.east();
                case "nw" -> searchAt.northWest();
                case "sw" -> searchAt.southWest();
                case "ne" -> searchAt.northEast();
                case "se" -> searchAt.southEast();
                default -> throw new IllegalStateException("Unexpected value: " + c);
            };
        }
        return true;
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<Point,Character> map =  MapUtil.asMap(input);
        //HashMap<Point,Character> map =  MapUtil.asMapFiltered(input, List.of('M','A','S'));
        int matches=0;
        for(int row=0;row<=MapUtil.getMaxY(map);row++){
            for(int col=0;col<=MapUtil.getMaxX(map);col++){
                Point p = new Point(row,col);
                matches+= findMatchesCross(p, map);
            }
        }

        return matches;
    }
    private Integer findMatchesCross(Point p, HashMap<Point, Character> map) {
        int matches = 0;

        if(!map.containsKey(p)) return matches;
        if(map.get(p) != 'A') return matches;

        matches+=checkCross(p, map, "n")?1:0;
        matches+=checkCross(p, map, "w")?1:0;
        matches+=checkCross(p, map, "s")?1:0;
        matches+=checkCross(p, map, "e")?1:0;

        return matches;
    }

    private boolean checkCross(Point p, HashMap<Point, Character> map, String direction) {

        Point m1, m2, s1,s2;
        switch (direction) {
            case "n" -> {
                m1 = p.northEast();
                m2 = p.northWest();
                s1 = p.southEast();
                s2 = p.southWest();
            }
            case "s" -> {
                m1 = p.southEast();
                m2 = p.southWest();
                s1 = p.northEast();
                s2 = p.northWest();
            }
            case "w" -> {
                m1 = p.northWest();
                m2 = p.southWest();
                s1 = p.northEast();
                s2 = p.southEast();
            }
            case "e" -> {
                m1 = p.northEast();
                m2 = p.southEast();
                s1 = p.northWest();
                s2 = p.southWest();
            }
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            }
            if(!map.containsKey(m1)) return false;
            if(!map.containsKey(m2)) return false;
            if(!map.containsKey(s1)) return false;
            if(!map.containsKey(s2)) return false;
            return map.get(m1) == 'M' && map.get(m2) == 'M' &&
                    map.get(s1) == 'S' && map.get(s2) == 'S';

         }
}
