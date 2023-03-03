package year2016.day18;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class Day18 {
    static Object solveA(List<String> values) {
        int numberOfRows=40;
        ArrayList<String> rows = generateMap(values.get(0), numberOfRows);
        return calculateSafeSpaces(rows);
    }
    static ArrayList<String> generateMap(String row0, int numberOfRows) {
        ArrayList<String> rows = new ArrayList<>();
        rows.add(row0);
        for(int row=1; row<numberOfRows; row++) {
            String rowAbove = rows.get(row-1);
            StringBuilder sb = new StringBuilder();
            for (int p=0; p<rowAbove.length();p++) {
                boolean leftIsTrap, middleIssTrap, rightIsTrap;
                leftIsTrap = (p>0 && rowAbove.charAt(p-1)=='^');
                middleIssTrap = rowAbove.charAt(p)=='^';
                rightIsTrap = (p<rowAbove.length()-1 && rowAbove.charAt(p+1)=='^');


                /*
                Its left and center tiles are traps, but its right tile is not.
                Its center and right tiles are traps, but its left tile is not.
                Only its left tile is a trap.
                Only its right tile is a trap.
                 */
                boolean isTrap=false;
                if(leftIsTrap && middleIssTrap && !rightIsTrap) {
                    isTrap=true;
                }
                if(!leftIsTrap && middleIssTrap && rightIsTrap) {
                    isTrap=true;
                }
                if(leftIsTrap && !middleIssTrap && !rightIsTrap) {
                    isTrap=true;
                }
                if(!leftIsTrap && !middleIssTrap && rightIsTrap) {
                    isTrap=true;
                }
                if(isTrap) sb.append("^");
                else sb.append(".");
            }
            rows.add(sb.toString());

        }
        return rows;
    }
    static int calculateSafeSpaces(ArrayList<String> rows) {
        int safeSpaces=0;
        for(String row : rows) {
            for (char c : row.toCharArray())
                if(c=='.') safeSpaces++;
        }
        return safeSpaces;
    }
    static Object solveB(List<String> values) {
        int numberOfRows=400000;
        ArrayList<String> rows = generateMap(values.get(0), numberOfRows);
        return calculateSafeSpaces(rows);

    }

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 2013
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 20006289
    }
}
