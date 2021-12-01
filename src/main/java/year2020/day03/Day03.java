package year2020.day03;

import utils.FileHelper;

import java.util.List;

public class Day03 {

    static long solveA(List<String> values) {
        return getNumberOfThrees(values,1,3);
    }
    static long solveB(List<String> values) {
        int slope1 = getNumberOfThrees(values, 1, 1);
        int slope2 = getNumberOfThrees(values, 1, 3);
        int slope3 = getNumberOfThrees(values, 1, 5);
        int slope4 = getNumberOfThrees(values, 1, 7);
        int slope5 = getNumberOfThrees(values, 2, 1);
        return (long) slope1 * slope2 * slope3 * slope4 * slope5;
    }

    private static int getNumberOfThrees (List<String> values, int yVal, int xVal) {
        int trees=0, x=0, length=values.get(0).length();
        for (int row=0; row<values.size(); row+=yVal) {
            if (values.get(row).charAt(x) == '#') { trees++; }
            x = (x+xVal) % length;
        }

        return trees;
    }

    public static void main(String[] args){
        String day = "Day03";

        List<String> inputs = new FileHelper().readFile("2020/" + day + ".txt");

        long t0 = System.currentTimeMillis();
        long ansA = solveA(inputs);
        long t1 = System.currentTimeMillis();
        long ansB = solveB(inputs);
        var timePart1 = t1-t0;
        long timePart2 = System.currentTimeMillis()-t1;

        System.out.println( day + "A: ("+timePart1+" ms)\t"+ansA); //176
        System.out.println( day + "B: ("+timePart2+" ms)\t"+ansB); //5872458240
    }
}
