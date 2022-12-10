package year2022.day10;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class Day10 {

    static Object solveA(List<String> values) {

        int current=1;
        ArrayList<Integer> strengths = new ArrayList<>();
        for (String value : values) {
            strengths.add(current);
            if (!value.equals("noop")) {
                strengths.add(current);
                int change = Integer.parseInt(value.split(" ")[1]);
                current += change;
            }
        }
        int result=20*strengths.get(20-1);
        for(int i=60; i<=strengths.size(); i+=40) {
            result+=i*strengths.get(i-1);
        }
        return result;
    }
    static Object solveB(List<String> values) {
        int current=1;
        ArrayList<Integer> strengths = new ArrayList<>();
        for (String value : values) {
            strengths.add(current);
            if (!value.equals("noop")) {
                strengths.add(current);
                int change = Integer.parseInt(value.split(" ")[1]);
                current += change;
            }
        }
        for(int cycle=0; cycle<strengths.size(); cycle++) {
            if(cycle%40==0)
                System.out.print("\n");
            if(strengths.get(cycle)==cycle%40
            || strengths.get(cycle)==cycle%40 - 1
            || strengths.get(cycle)==cycle%40 + 1) {
                System.out.print("#");
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("\n");

        return "FJUBULRZ";
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //13060
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //FJUBULRZ
    }
}
