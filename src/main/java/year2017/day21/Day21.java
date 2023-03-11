package year2017.day21;

import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.HashMap;
import java.util.List;

public class Day21 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day21());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<String, String> instructions = new HashMap<>();
        for(String row : input) {
            String from = row.split(" => ")[0];
            String to = row.split(" => ")[1];

            HashMap<Point, Character> fromMap = asMap(from);
            HashMap<Point, Character> toMap   = asMap(to);

            instructions.put(asRow(fromMap), asRow(toMap));

            for(int i=0; i<4; i++) {
                fromMap = MapUtil.rotateClockWise(fromMap);
                instructions.put(asRow(fromMap), asRow(toMap));
            }
            fromMap = MapUtil.flipHorizontal(fromMap);
            instructions.put(asRow(fromMap), asRow(toMap));

            for(int i=0; i<4; i++) {
                fromMap = MapUtil.rotateClockWise(fromMap);
                instructions.put(asRow(fromMap), asRow(toMap));
            }
        }

        HashMap<Point, Character> map = asMap(".#./..#/###");

        int numberOfIterations = input.size()==2? 2 : 5;
        for(int iterations=0; iterations<numberOfIterations; iterations++) {
            int maxX= MapUtil.getMaxX(map);
            HashMap<Point, Character> mapAfter = new HashMap<>();
            if (maxX % 2 == 1) {
                //split into 2*2
                for (int y = 0; y <= maxX - 1; y += 2) {
                    for (int x = 0; x <= maxX - 1; x += 2) {
                        String row = "" + map.get(new Point(x, y)) + map.get(new Point(x + 1, y)) + "/" +
                                map.get(new Point(x, y + 1)) + map.get(new Point(x + 1, y + 1));
                        mapAfter.putAll(asMap(instructions.get(row), x/2*3, y/2*3));
                    }
                }
            } else {
                //split into 3*3
                for (int y = 0; y <= maxX - 2; y += 3) {
                    for (int x = 0; x <= maxX - 2; x += 3) {
                        String row = "" + map.get(new Point(x, y)) + map.get(new Point(x + 1, y)) + map.get(new Point(x + 2, y)) + "/" +
                                map.get(new Point(x, y + 1)) + map.get(new Point(x + 1, y + 1)) + map.get(new Point(x + 2, y + 1)) + "/" +
                                map.get(new Point(x, y + 2)) + map.get(new Point(x + 1, y + 2)) + map.get(new Point(x + 2, y + 2));
                        mapAfter.putAll(asMap(instructions.get(row), x/3*4, y/3*4));
                    }
                }
            }
            map = mapAfter;
        }

        return map.values().stream().filter(c->c=='#').count();
    }
    private String asRow (HashMap<Point, Character> map) {
        StringBuilder row= new StringBuilder();
        for(int y=0; y<=MapUtil.getMaxY(map); y++) {
            for(int x=0; x<=MapUtil.getMaxY(map); x++) {
                row.append(map.get(new Point(x, y)));
            }
            row.append("/");
        }
        return row.substring(0, row.length()-1);
    }
    private HashMap<Point, Character> asMap (String row) {
        return asMap(row, 0, 0);
    }
    private HashMap<Point, Character> asMap (String row, int xOffset, int yOffset) {
        HashMap<Point, Character> map = new HashMap<>();

        int y=yOffset, x=xOffset;

        for(char c : row.toCharArray())  {
            if(c == '/') {y++; x=xOffset;}
            else { map.put(new Point(x,y), c); x++;}
        }

        return map;
    }


    @Override
    public Object solveB(List<String> input) {
        HashMap<String, String> instructions = new HashMap<>();
        for(String row : input) {
            String from = row.split(" => ")[0];
            String to = row.split(" => ")[1];

            HashMap<Point, Character> fromMap = asMap(from);
            HashMap<Point, Character> toMap   = asMap(to);

            instructions.put(asRow(fromMap), asRow(toMap));

            for(int i=0; i<4; i++) {
                fromMap = MapUtil.rotateClockWise(fromMap);
                instructions.put(asRow(fromMap), asRow(toMap));
            }
            fromMap = MapUtil.flipHorizontal(fromMap);
            instructions.put(asRow(fromMap), asRow(toMap));

            for(int i=0; i<4; i++) {
                fromMap = MapUtil.rotateClockWise(fromMap);
                instructions.put(asRow(fromMap), asRow(toMap));
            }
        }

        HashMap<Point, Character> map = asMap(".#./..#/###");

        int numberOfIterations = 18;
        for(int iteration=0; iteration<numberOfIterations; iteration++) {
            int maxX= MapUtil.getMaxX(map);
            HashMap<Point, Character> mapAfter = new HashMap<>();
            if (maxX % 2 == 1) {
                //split into 2*2
                for (int y = 0; y <= maxX - 1; y += 2) {
                    for (int x = 0; x <= maxX - 1; x += 2) {
                        String row = "" + map.get(new Point(x, y)) + map.get(new Point(x + 1, y)) + "/" +
                                map.get(new Point(x, y + 1)) + map.get(new Point(x + 1, y + 1));
                        mapAfter.putAll(asMap(instructions.get(row), x/2*3, y/2*3));
                    }
                }
            } else {
                //split into 3*3
                for (int y = 0; y <= maxX - 2; y += 3) {
                    for (int x = 0; x <= maxX - 2; x += 3) {
                        String row = "" + map.get(new Point(x, y)) + map.get(new Point(x + 1, y)) + map.get(new Point(x + 2, y)) + "/" +
                                map.get(new Point(x, y + 1)) + map.get(new Point(x + 1, y + 1)) + map.get(new Point(x + 2, y + 1)) + "/" +
                                map.get(new Point(x, y + 2)) + map.get(new Point(x + 1, y + 2)) + map.get(new Point(x + 2, y + 2));
                        mapAfter.putAll(asMap(instructions.get(row), x/3*4, y/3*4));
                    }
                }
            }
            map = mapAfter;
        }

        return map.values().stream().filter(c->c=='#').count();
    }
}
