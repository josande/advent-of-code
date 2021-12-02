package year2021.day01;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 {

    static Object solveA(List<String> values) {
        int times = 0;
        int lastVal = Integer.MAX_VALUE;
        List<Integer> intValues = values.stream().map(Integer::valueOf).collect(Collectors.toList());

        for(int val : intValues) {
            if (Integer.valueOf(val) > lastVal) times++;
            lastVal = Integer.valueOf(val);
        }

        return times;
    }
    static Object solveB(List<String> values) {
        int times = 0;
        int lastSum=Integer.MAX_VALUE;
        List<Integer> intValues = values.stream().map(Integer::valueOf).collect(Collectors.toList());
        for(int i=0; i<intValues.size()-2; i++) {
            int curr = intValues.get(i) + intValues.get(i+1) + intValues.get(i+2);
            if( curr > lastSum ) times++;
            lastSum = curr;
        }

        return times;
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");

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
