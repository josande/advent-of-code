package year2020.day01;

import utils.FileHelper;

import java.util.List;

public class Day01 {

    static int solveA(List<Integer> values) {
        for (int i =0; i<values.size() - 1; i++)
            for (int j = i+1; j<values.size(); j++)
                if (values.get(i)+values.get(j) == 2020)
                    return values.get(i) * values.get(j);
        return -1;
    }
    static int solveB(List<Integer> values) {
        for (int i = 0; i<values.size() - 2; i++)
            for (int j = i + 1; j < values.size() - 1; j++)
                if ((values.get(i) + values.get(j)) < 2020)
                    for (int k = j + 1; k < values.size(); k++)
                        if (values.get(i) + values.get(j) + values.get(k) == 2020)
                            return values.get(i) * values.get(j) * values.get(k);
        return -1;
    }
    public static void main(String[] args){
        String day = "Day01";

        List<Integer> inputs = new FileHelper().readFileAsIntegers("2020/"+day+".txt");

        long t0 = System.currentTimeMillis();
        int ansA=solveA(inputs);
        long t1 = System.currentTimeMillis();
        int ansB=solveB(inputs);
        var timePart1 = t1-t0;
        long timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //877971
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //203481432
    }
}
