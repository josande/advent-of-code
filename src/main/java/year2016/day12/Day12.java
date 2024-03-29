package year2016.day12;

import utils.Assembunny;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day12 {

    static long solveA(List<String> values) {
        Assembunny assembunny = new Assembunny();
        for(var val:values) {
            assembunny.addCommand(val);
        }
        assembunny.run();
        return assembunny.getA();
    }

    static long solveB(List<String> values) {
        Assembunny assembunny = new Assembunny();
        for(var val:values) {
            assembunny.addCommand(val);
        }
        assembunny.setC(1);
        assembunny.run();
        return assembunny.getA();
    }



    public static void main (String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 318077
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 9227731
    }

}
