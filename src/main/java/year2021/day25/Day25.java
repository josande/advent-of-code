package year2021.day25;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day25 {

    static Object solveA(List<String> values) {

        for(var val : values) {

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
        var timePart1 = t1-t0;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
    }
}
