package year2016.day03;

import utils.FileHelper;
import utils.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 {

    static int solveA(List<String> values) {
        int valid=0;
        for( var row : values ) {
            var lengths =  Arrays.stream(row.split(" ")).filter(s->!s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList());
            if (lengths.get(0)+lengths.get(1)>lengths.get(2) &&
                lengths.get(1)+lengths.get(2)>lengths.get(0) &&
                lengths.get(2)+lengths.get(0)>lengths.get(1))
                valid++;

        }

        return valid;
    }

    static int solveB(List<String> values) {
        int valid=0;
        List<Integer> lengths = new ArrayList<>();

        for( var row : values ) {
            Arrays.stream(row.split(" ")).filter(s->!s.isEmpty()).map(Integer::valueOf).forEach(i -> lengths.add(i));
        }

        System.out.println(values.size()+ " "+lengths.size());
        int l1,l2,l3;
        for(int i =0; i<lengths.size(); i=i+9) {
            l1=lengths.get(i+0);
            l2=lengths.get(i+3);
            l3=lengths.get(i+6);
            if (l1+l2>l3 && l2+l3>l1 && l3+l1>l2)  valid++;

            l1=lengths.get(i+1);
            l2=lengths.get(i+4);
            l3=lengths.get(i+7);
            if (l1+l2>l3 && l2+l3>l1 && l3+l1>l2)  valid++;

            l1=lengths.get(i+2);
            l2=lengths.get(i+5);
            l3=lengths.get(i+8);
            if (l1+l2>l3 && l2+l3>l1 && l3+l1>l2)  valid++;
        }

        return valid;
    }


    public static void main(String[] args){
        var day = "Day03";

        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = System.currentTimeMillis()-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //982
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //1826
    }
}
