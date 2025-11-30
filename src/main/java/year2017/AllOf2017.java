
package year2017;

import org.apache.commons.lang3.StringUtils;
import year2017.day01.Day01;
import year2017.day02.Day02;
import year2017.day03.Day03;
import year2017.day04.Day04;
import year2017.day05.Day05;
import year2017.day06.Day06;
import year2017.day07.Day07;
import year2017.day08.Day08;
import year2017.day09.Day09;
import year2017.day10.Day10;
import year2017.day11.Day11;
import year2017.day12.Day12;
import year2017.day13.Day13;
import year2017.day14.Day14;
import year2017.day15.Day15;
import year2017.day16.Day16;
import year2017.day17.Day17;
import year2017.day18.Day18;
import year2017.day19.Day19;
import year2017.day20.Day20;
import year2017.day21.Day21;
import year2017.day22.Day22;
import year2017.day23.Day23;
import year2017.day24.Day24;
import year2017.day25.Day25;

import java.lang.invoke.MethodHandles;

@SuppressWarnings("DuplicatedCode")
public class AllOf2017 {
    static void main(){
        long t0=System.currentTimeMillis();
        Day01.main();
        Day02.main();
        Day03.main();
        Day04.main();
        Day05.main();
        Day06.main();
        Day07.main();
        Day08.main();
        Day09.main();
        Day10.main();
        Day11.main();
        Day12.main();
        Day13.main();
        Day14.main();
        Day15.main();
        Day16.main();
        Day17.main();
        Day18.main();
        Day19.main();
        Day20.main();
        Day21.main();
        Day22.main();
        Day23.main();
        Day24.main();
        Day25.main();
        long t1=System.currentTimeMillis();
        var year = MethodHandles.lookup().lookupClass().getPackageName().substring(4,8);
        String totalTime = StringUtils.leftPad(""+(t1-t0), 5, '0');
        System.out.println(year+":Total:  ("+totalTime+ " ms)");

    }
}
