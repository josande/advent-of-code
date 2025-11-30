package year2020.day25;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day25 {


    static long solveA(List<String> values) {
            long publicCardKey=Long.parseLong(values.get(0));
            long publicDoorKey=Long.parseLong(values.get(1));

            long cardLoopSize = 0;
            long subjectNumber = 7;
            long remainder = 1;
            while(remainder != publicCardKey) {
                remainder=(remainder*subjectNumber) % 20201227;
                cardLoopSize++;
            }
            subjectNumber=publicDoorKey;
            long publicKey = 1;
            for(int i=0; i<cardLoopSize; i++) {
                publicKey=(publicKey*subjectNumber) % 20201227;
            }

        return publicKey;
    }



    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var timePart1 = System.currentTimeMillis()-t0;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //3015200
    }
}
