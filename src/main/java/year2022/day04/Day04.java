package year2022.day04;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day04 {

    static Object solveA(List<String> values) {
        int counter=0;
        for(String val : values) {
            String partA = val.split(",")[0];
            String partB = val.split(",")[1];

            int start1 = Integer.parseInt(partA.split("-")[0]);
            int start2 = Integer.parseInt(partB.split("-")[0]);
            int end1 = Integer.parseInt(partA.split("-")[1]);
            int end2 = Integer.parseInt(partB.split("-")[1]);
            if((start1 <= start2 && end1 >= end2)
            || (start1 >= start2 && end1 <= end2)) {
                counter++;
            }
        }

        return counter;
    }
    static Object solveB(List<String> values) {
        int counter=0;
        for(String val : values) {
            String partA = val.split(",")[0];
            String partB = val.split(",")[1];

            int start1=Integer.parseInt(partA.split("-")[0]);
            int start2=Integer.parseInt(partB.split("-")[0]);
            int end1=Integer.parseInt(partA.split("-")[1]);
            int end2=Integer.parseInt(partB.split("-")[1]);

            if((start1 >= start2 && start1 <= end2) || ( end1 <= end2 && end1 >= start2) ||
               (start2 >= start1 && start2 <= end1) || ( end2 <= end1 && end2 >= start1 ) ) {
                counter++;
            }
        }

        return counter;
    }
    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //576
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //905
    }
}
