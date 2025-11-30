package year2017.day21;

import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day21 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day21());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<String, List<String>> instructions = new HashMap<>();
        for(String row : input) {
            String from = row.split(" => ")[0];
            String to = row.split(" => ")[1];

            HashMap<Point, Character> fromMap = asMap(from);
            HashMap<Point, Character> toMap   = asMap(to);

            instructions.put(asRow(fromMap), asRows(toMap));

            for(int i=0; i<4; i++) {
                fromMap = MapUtil.rotateClockWise(fromMap);
                instructions.put(asRow(fromMap), asRows(toMap));
            }
            fromMap = MapUtil.flipHorizontal(fromMap);
            instructions.put(asRow(fromMap), asRows(toMap));

            for(int i=0; i<4; i++) {
                fromMap = MapUtil.rotateClockWise(fromMap);
                instructions.put(asRow(fromMap), asRows(toMap));
            }
        }

        ArrayList<String> map = new ArrayList<>(List.of(".#.", "..#", "###"));

        int numberOfIterations = input.size()==2? 2 : 5;
        for(int iterations=0; iterations<numberOfIterations; iterations++) {
            int size = map.size();
            ArrayList<String> mapAfter = new ArrayList<>();
            if (size % 2 == 0) {
                //split into 2*2
                for (int y = 0; y <= size - 1; y += 2) {
                    mapAfter.addAll(expand(map.get(y), map.get(y+1), instructions));
                }
            } else {
                //split into 3*3
                for (int y = 0; y <= size - 2; y += 3) {
                    mapAfter.addAll(expand(map.get(y), map.get(y+1), map.get(y+2), instructions));
                }
            }
            map = mapAfter;
        }

        return map.stream().flatMap(a -> a.chars().mapToObj(c -> (char) c)).filter(c->c=='#').count();
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
    private List<String> asRows (HashMap<Point, Character> map) {
        List<String> rows = new ArrayList<>();
        for(int y=0; y<=MapUtil.getMaxY(map); y++) {
            StringBuilder row= new StringBuilder();
            for(int x=0; x<=MapUtil.getMaxY(map); x++) {
                row.append(map.get(new Point(x, y)));
            }
            rows.add(row.toString());
        }
        return rows;
    }

    private HashMap<Point, Character> asMap (String row) {
        HashMap<Point, Character> map = new HashMap<>();

        int y=0, x=0;

        for(char c : row.toCharArray())  {
            if(c == '/') {y++; x=0;}
            else { map.put(new Point(x,y), c); x++;}
        }

        return map;
    }
    private List<String> asMap (String rowA, String rowB, HashMap<String, List<String>> instructions) {

        return instructions.get(rowA+"/"+rowB);
    }
    private List<String> asMap (String rowA, String rowB, String rowC, HashMap<String, List<String>> instructions) {

        return instructions.get(rowA+"/"+rowB+"/"+rowC);
    }


    @Override
    public Object solveB(List<String> input) {
        HashMap<String, List<String>> instructions = new HashMap<>();
        for(String row : input) {
            String from = row.split(" => ")[0];
            String to = row.split(" => ")[1];

            HashMap<Point, Character> fromMap = asMap(from);
            HashMap<Point, Character> toMap   = asMap(to);

            instructions.put(asRow(fromMap), asRows(toMap));

            for(int i=0; i<4; i++) {
                fromMap = MapUtil.rotateClockWise(fromMap);
                instructions.put(asRow(fromMap), asRows(toMap));
            }
            fromMap = MapUtil.flipHorizontal(fromMap);
            instructions.put(asRow(fromMap), asRows(toMap));

            for(int i=0; i<4; i++) {
                fromMap = MapUtil.rotateClockWise(fromMap);
                instructions.put(asRow(fromMap), asRows(toMap));
            }
        }

        ArrayList<String> map = new ArrayList<>(List.of(".#.", "..#", "###"));

        int numberOfIterations = 18;
        for(int iteration=0; iteration<numberOfIterations; iteration++) {
            int size = map.size();
            ArrayList<String> mapAfter = new ArrayList<>();
            if (size % 2 == 0) {
                //split into 2*2
                for (int y = 0; y <= size - 1; y += 2) {
                    mapAfter.addAll(expand(map.get(y), map.get(y+1), instructions));
                }
            } else {
                //split into 3*3
                for (int y = 0; y <= size - 2; y += 3) {
                    mapAfter.addAll(expand(map.get(y), map.get(y+1), map.get(y+2), instructions));
                }
            }
            map = mapAfter;
        }
        return map.stream().flatMap(a -> a.chars().mapToObj(c -> (char) c)).filter(c->c=='#').count();
    }
    List<String> expand(String a, String b, HashMap<String, List<String>> instructions) {
        StringBuilder sbA = new StringBuilder();
        StringBuilder sbB = new StringBuilder();
        StringBuilder sbC = new StringBuilder();
        for(int i=0; i<a.length(); i+=2)  {
            List<String> results = asMap(a.substring(i,i+2), b.substring(i,i+2), instructions);
            sbA.append(results.get(0));
            sbB.append(results.get(1));
            sbC.append(results.get(2));
        }
        return List.of(sbA.toString(), sbB.toString(), sbC.toString());
    }
    List<String> expand(String a, String b, String c, HashMap<String, List<String>> instructions) {
        StringBuilder sbA = new StringBuilder();
        StringBuilder sbB = new StringBuilder();
        StringBuilder sbC = new StringBuilder();
        StringBuilder sbD = new StringBuilder();
        for(int i=0; i<a.length(); i+=3)  {
            List<String> results = asMap(a.substring(i,i+3), b.substring(i,i+3), c.substring(i,i+3), instructions);
            sbA.append(results.get(0));
            sbB.append(results.get(1));
            sbC.append(results.get(2));
            sbD.append(results.get(3));
        }
        return List.of(sbA.toString(), sbB.toString(), sbC.toString(), sbD.toString());
    }
}
