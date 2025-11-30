package year2021.day07;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day07 {

    static Object solveA(List<String> values) {
        List<Integer> heights = Arrays.stream(values.get(0).split(",")).map(Integer::valueOf).collect(Collectors.toList());

        int smallest=Integer.MAX_VALUE;
        for(int val : heights) {
            smallest = Math.min(smallest, heights.stream().map(i -> Math.abs(i-val)).mapToInt(Integer::intValue).sum());
        }

        return smallest;
    }
    static Object solveB(List<String> values) {
        List<Integer> heights = Arrays.stream(values.get(0).split(",")).map(Integer::valueOf).collect(Collectors.toList());

        int smallest=Integer.MAX_VALUE;
        int largest = 0;

        for (int i  : heights) {
            largest = Math.max(i, largest);
        }

        for(int val = 0; val<largest; val++) {
            int sum=0;

            for(var h2 : heights) {
                int diff=Math.abs(h2- val);
                sum+= ((diff+1)*(diff))/2;
            }
            smallest = Math.min(smallest, sum);
        }

        return smallest;
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //344735
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //96798233
    }
}
