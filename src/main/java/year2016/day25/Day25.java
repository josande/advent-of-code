package year2016.day25;

import utils.Assembunny;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day25 {

    static Object solveA(List<String> values) {

        for(int a=0; ;a++) {
            Assembunny assembunny = new Assembunny();
            for (String val : values) assembunny.addCommand(val);
            assembunny.setA(a);
            assembunny.run();
            if(assembunny.isCyclic()) return a;
        }
    }



    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = t1-t0;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 192
    }
}
