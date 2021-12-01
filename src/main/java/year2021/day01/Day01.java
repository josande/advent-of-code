package year2021.day01;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day01 {

    static int solveA(List<Integer> values) {
        int times = 0;
        int lastVal = Integer.MAX_VALUE;
        for( int val: values ) {
            if (val > lastVal) times++;
            lastVal = val;
        }

        return times;
    }
    static int solveB(List<Integer> values) {
        int times = 0;
        int lastSum=Integer.MAX_VALUE;
        for(int i=0; i<values.size()-2; i++) {
            int curr = values.get(i) + values.get(i+1) + values.get(i+2);
            if( curr > lastSum ) times++;
            lastSum = curr;
        }

        return times;
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
//        var inputs = new FileHelper().readFile("2021/"+day+".txt");
        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //1696
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //1737
    }
}
