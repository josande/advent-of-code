package year2020.day24;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day24 {


    static int solveA(List<String> values) {

        return -1;
    }


    static long solveB(List<String> values) {

        return -1;

    }


    public static void main (String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
