package year2016.day12;

import utils.AdventOfCode;
import utils.Assembunny;
import utils.FileHelper;
import utils.Reporter;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day12 implements AdventOfCode {

    @Override
    public Object solveA(List<String> values) {
        Assembunny assembunny = new Assembunny();
        for(var val:values) {
            assembunny.addCommand(val);
        }
        assembunny.run();
        return assembunny.getA();
    }

    @Override
    public Object solveB(List<String> values) {
        Assembunny assembunny = new Assembunny();
        for(var val:values) {
            assembunny.addCommand(val);
        }
        assembunny.setC(1);
        assembunny.run();
        return assembunny.getA();
    }

    public static void main(){
        Reporter.report(new Day12());
    }

}
