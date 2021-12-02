package year2021.day24;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day24 {

    static Object solveA(List<String> values) {

        for(var val : values) {

        }

        return null;
    }
    static Object solveB(List<String> values) {

        for (var val : values) {

        }

        return null;
    }

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
