package year2016.day23;

import utils.Assembunny;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day23 {

    static Object solveA(List<String> values) {
        Assembunny assembunny = new Assembunny();
        for(String val : values) assembunny.addCommand(val);
        assembunny.setA(7);
        assembunny.run();
        return assembunny.getA();
    }

    static Object solveB(List<String> values) {

        Assembunny assembunny = new Assembunny();
        for(String val : values) assembunny.addCommand(val);
        assembunny.setA(12);
        assembunny.run();
        return assembunny.getA();
    }


    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 11200
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 479007760
    }
}
