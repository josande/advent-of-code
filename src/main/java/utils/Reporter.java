package utils;

import org.apache.commons.lang3.StringUtils;

public class Reporter {
    public static void report(AdventOfCode aoc){
        var day = aoc.getClass().getSimpleName();
        var year = aoc.getClass().getPackageName().substring(4,8);

        var inputs = new FileHelper().readFile(year+"/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = aoc.solveA(inputs);

        var t1 = System.currentTimeMillis();
        var ansB = aoc.solveB(inputs);
        var t2 = System.currentTimeMillis();
        var timePart1 = StringUtils.leftPad(""+(t1-t0), 5, '0');
        var timePart2 = StringUtils.leftPad(""+(t2-t1), 5, '0');

        System.out.println(year+":"+day + "A: ("+timePart1+" ms)\t"+ansA);
        System.out.println(year+":"+day + "B: ("+timePart2+" ms)\t"+ansB);
    }
}
