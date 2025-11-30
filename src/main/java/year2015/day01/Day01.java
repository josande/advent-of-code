package year2015.day01;

import utils.FileHelper;

import java.util.List;

public class Day01 {

    static int solveA(List<String> values) {
        return -1;
    }

    static int solveB(List<String> values) {
        return -1;
    }

    public static void main(){
        var day = "Day01";

        var inputs = new FileHelper().readFile("2015/"+day+".txt");

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
