package year2020.day04;

import utils.FileHelper;

import java.util.List;

public class Day04 {

    static int solveA(List<String> values) {

        return -1;
    }
    static int solveB(List<String> values) {

        return -1;
    }
    public static void main(String[] args){
        String day = "Day04";

       // List<Integer> inputs = new FileHelper().readFileAsIntegers("2020/"+day+".txt");
        List<String> inputs = new FileHelper().readFile("2020/"+day+".txt");

        long t0 = System.currentTimeMillis();
        int ansA=solveA(inputs);
        long t1 = System.currentTimeMillis();
        int ansB=solveB(inputs);
        long timePart1 = System.currentTimeMillis()-t0;
        long timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
