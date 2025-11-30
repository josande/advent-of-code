package year2016.day25;

import utils.AdventOfCode;
import utils.Assembunny;
import utils.Reporter;

import java.util.List;

public class Day25 implements AdventOfCode {

    @Override
    public Object solveA(List<String> values) {

        for(int a=0; ;a++) {
            Assembunny assembunny = new Assembunny();
            for (String val : values) assembunny.addCommand(val);
            assembunny.setA(a);
            assembunny.run();
            if(assembunny.isCyclic()) return a;
        }
    }

    @Override
    public Object solveB(List<String> input) {
        return "Merry X-mas!";
    }


    public static void main(){
        Reporter.report(new Day25());
    }
}
