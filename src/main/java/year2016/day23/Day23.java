package year2016.day23;

import utils.AdventOfCode;
import utils.Assembunny;
import utils.FileHelper;
import utils.Reporter;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day23 implements AdventOfCode {

    @Override
    public Object solveA(List<String> values) {
        Assembunny assembunny = new Assembunny();
        for(String val : values) assembunny.addCommand(val);
        assembunny.setA(7);
        assembunny.run();
        return assembunny.getA();
    }

    @Override
    public Object solveB(List<String> values) {

        Assembunny assembunny = new Assembunny();
        for(String val : values) assembunny.addCommand(val);
        assembunny.setA(12);
        assembunny.run();
        return assembunny.getA();
    }


    public static void main(){
        Reporter.report(new Day23());
    }
}
