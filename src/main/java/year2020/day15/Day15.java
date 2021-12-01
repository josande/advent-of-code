package year2020.day15;

import utils.FileHelper;
import java.util.*;

public class Day15 {


    static int solveA(List<String> values) {
        return solveFor(values, 2020);
    }

    static long solveB(List<String> values) {
        return solveFor(values, 30000000);
    }

    private static int solveFor(List<String> values, int turns) {
        int[] numbers = new int[turns];
        int lastAdded=-1;
        int turn = 0;
        int currentValue = 0;
        int nextValue;
        for(String v : values.get(0).split(",")) {
            turn++;
            currentValue = Integer.parseInt(v);
            numbers[currentValue] = turn;
        }
        numbers[currentValue] = 0;

        while(turn <= turns) {
            if(numbers[currentValue] > 0) {
                nextValue = turn - numbers[currentValue];
            } else {
                nextValue = 0;
            }
            numbers[currentValue] = turn;
            lastAdded = currentValue;
            currentValue = nextValue;
            turn++;
        }
        return lastAdded;
    }



    public static void main (String[] args){
        var day = "Day15";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //447
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //11721679
    }
}
