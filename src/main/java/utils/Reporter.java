package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class Reporter {
    public static void report(AdventOfCode aoc){
        var day = aoc.getClass().getSimpleName();
        var year = aoc.getClass().getPackageName().substring(4,8);

        var inputs = new FileHelper().readFile(year+"/"+day+".txt");

        var t0 = System.nanoTime()/1000;
        var ansA = aoc.solveA(inputs);

        var t1 = System.nanoTime()/1000;
        var ansB = aoc.solveB(inputs);
        var t2 = System.nanoTime()/1000;

        var timePart1 = StringUtils.leftPad(""+(t1-t0), 8, ' ');
        var timePart2 = StringUtils.leftPad(""+(t2-t1), 8, ' ');

        long cutOffRed    = 100000L;  // 100 ms
        long cutOffYellow = 10000L;   //  10 ms

        String colorDefault = "\u001B[0m";
        String colorRed     = "\u001B[31m";
        String colorYellow  = "\u001B[33m";
        String colorA = (t1-t0)>cutOffRed ? colorRed :(t1-t0)>cutOffYellow ? colorYellow : colorDefault ;
        String colorB = (t2-t1)>cutOffRed ? colorRed :(t2-t1)>cutOffYellow ? colorYellow : colorDefault ;

        System.out.println(year+":"+day + "B: "+colorA+timePart1+colorDefault+" μs, "+ansA);
        System.out.println(year+":"+day + "B: "+colorB+timePart2+colorDefault+" μs, "+ansB);
    }
}
