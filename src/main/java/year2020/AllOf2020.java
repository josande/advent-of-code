package year2020;

import org.apache.commons.lang3.StringUtils;
import year2020.day01.Day01;
import year2020.day02.Day02;
import year2020.day03.Day03;
import year2020.day04.Day04;
import year2020.day05.Day05;
import year2020.day06.Day06;
import year2020.day07.Day07;
import year2020.day08.Day08;
import year2020.day09.Day09;
import year2020.day10.Day10;
import year2020.day11.Day11;
import year2020.day12.Day12;
import year2020.day13.Day13;
import year2020.day14.Day14;
import year2020.day15.Day15;
import year2020.day16.Day16;
import year2020.day17.Day17;
import year2020.day18.Day18;
import year2020.day19.Day19;
import year2020.day20.Day20;
import year2020.day21.Day21;
import year2020.day22.Day22;
import year2020.day23.Day23;
import year2020.day24.Day24;
import year2020.day25.Day25;

import java.lang.invoke.MethodHandles;

@SuppressWarnings("DuplicatedCode")
public class AllOf2020 {
    public static void main(String[] args){
        long t0=System.currentTimeMillis();
        Day01.main(null);
        Day02.main(null);
        Day03.main(null);
        Day04.main(null);
        Day05.main(null);
        Day06.main(null);
        Day07.main(null);
        Day08.main(null);
        Day09.main(null);
        Day10.main(null);
        Day11.main(null);
        Day12.main(null);
        Day13.main(null);
        Day14.main(null);
        Day15.main(null);
        Day16.main(null);
        Day17.main(null);
        Day18.main(null);
        Day19.main(null);
        Day20.main(null);
        Day21.main(null);
        Day22.main(null);
        Day23.main(null);
        Day24.main(null);
        Day25.main(null);
        long t1=System.currentTimeMillis();
        var year = MethodHandles.lookup().lookupClass().getPackageName().substring(4,8);
        String totalTime = StringUtils.leftPad(""+(t1-t0), 5, '0');
        System.out.println(year+":Total:  ("+totalTime+ " ms)");
    }
}
