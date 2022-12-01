package year2022.day01;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 {

    static Object solveA(List<String> values) {
        int highest=0, current=0;
        for(String val : values) {
            if(val.isEmpty()) {
                current=0;
            } else {
                current += Integer.parseInt(val);
            }
            highest = Math.max(highest, current);

        }

        return highest;
    }
    static Object solveB(List<String> values) {
        List<Integer> cals=new ArrayList<>();
        int current=0;
        for(String val : values) {
            if(val.isEmpty()) {
                cals.add(current);
                current=0;
            } else {
                current += Integer.parseInt(val);
            }
        }
        cals.add(current);
        Collections.sort(cals);
        Collections.reverse(cals);
        return cals.get(0)+cals.get(1)+cals.get(2);
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //72070
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //211805
    }
}
