package year2020.day09;

import utils.FileHelper;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day09 {


    static Long solveA(List<Long> values) {
        return solveA(25, values);
    }
    static Long solveA(int preambleSize, List<Long> values) {
        LinkedList<Long> lastValues = new LinkedList<>(values.subList(0,preambleSize));

        for(int i = preambleSize; i<values.size(); i++) {
            if(!containsSum(lastValues, values.get(i)))
                return values.get(i);
            lastValues.removeFirst();
            lastValues.add(values.get(i));
        }
        return -1L;
    }

    private static boolean containsSum(List<Long> list, Long value) {
        for (int i=0; i<list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if ((list.get(i)+list.get(j))==value)
                    return true;
            }
        }
        return false;
    }

    static Long solveB(List<Long> values) {
        return solveB(25, values);
    }
    static Long solveB(int preambleSize, List<Long> values) {
        Long firstError= solveA(preambleSize, values);
        for(int listIndex = 0; listIndex<values.size()-1; listIndex++) {
            int seqIndex = listIndex;
            Long sum = values.get(seqIndex);
            Long smallestSoFar = values.get(seqIndex);
            Long biggestSoFar = values.get(seqIndex);
            while (sum <= firstError) {
                sum += values.get(++seqIndex);
                biggestSoFar = Math.max(values.get(seqIndex), biggestSoFar);
                smallestSoFar = Math.min(values.get(seqIndex), smallestSoFar);
                if (sum.equals(firstError) ) {
                    return smallestSoFar + biggestSoFar;
                }
            }
        }
        return -1L;
    }


    public static void main(String[] args){
        var day = "Day09";

        var inputs = new FileHelper().readFile("2020/"+day+".txt").stream().map(Long::valueOf).collect(Collectors.toList());

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //26134589
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //3535124
    }
}
