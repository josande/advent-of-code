package year2020.day07;

import utils.FileHelper;

import java.util.ArrayList;
import java.util.List;

public class Day07 {

    static int solveA(List<String> values) {

        return -1;
    }

    static int solveB(List<String> values) {
        return -1;
    }

    public static void main(String[] args){
        var day = "Day07";

        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = System.currentTimeMillis()-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
