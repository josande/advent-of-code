package year2020.day25;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day25 {


    static int solveA(List<String> values) {

        return -1;
    }



    public static void main (String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
    }
}
