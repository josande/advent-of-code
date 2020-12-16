package year2020.day10;

import utils.FileHelper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {


    static Integer solveA(List<Integer> values) {
        Collections.sort(values);
        int oneDiff =1, threeDiff=1;
        for(int i = 1; i<values.size(); i++){
            if( values.get(i) - values.get(i-1) == 1)
                oneDiff++;
            if( values.get(i) - values.get(i-1) == 3)
                threeDiff++;
        }

        return oneDiff*threeDiff;
    }


    static Long solveB(List<Integer> values) {
        Collections.sort(values);
        HashMap<Integer, Long> pathMap=new HashMap<>();
        pathMap.put(values.get(values.size()-1)+3, 1L);
        for(int i = values.size()-1; i>=0; i--) {
            pathMap.put(values.get(i), findNumberOfChildren(pathMap, values.get(i)));
        }
        return findNumberOfChildren(pathMap, 0);
    }

    private static long findNumberOfChildren(HashMap<Integer, Long> pathMap, int val) {
        return pathMap.getOrDefault(val+1,0L) +
               pathMap.getOrDefault(val+2,0L) +
               pathMap.getOrDefault(val+3,0L);

    }



    public static void main(String[] args){
        var day = "Day10";

        var inputs = new FileHelper().readFile("2020/"+day+".txt")
                .stream().map(Integer::parseInt).collect(Collectors.toList());

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //1656
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //56693912375296
    }
}
