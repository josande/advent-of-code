package year2017.day11;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

public class Day11 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day11());
    }

    @Override
    public Object solveA(List<String> input) {
        int x=0, y=0;
        for(String step : input.get(0).split(",")) {
            switch (step) {
                case "n" -> y-=2;
                case "s" -> y+=2;
                case "nw" -> {x--; y--;}
                case "sw" -> {x--; y++;}
                case "ne" -> {x++; y--;}
                case "se" -> {x++; y++;}
                default -> throw new IllegalArgumentException("Unknown direction: "+step);
            }
        }
        x=Math.abs(x);
        y=Math.abs(y);
        int resUlt = x;
        if(y>x) resUlt+=(y-x)/2;

        return resUlt;
    }

    @Override
    public Object solveB(List<String> input) {
        int x=0, y=0;
        int maxDistance=0;
        for(String step : input.get(0).split(",")) {
            switch (step) {
                case "n" -> y-=2;
                case "s" -> y+=2;
                case "nw" -> {x--; y--;}
                case "sw" -> {x--; y++;}
                case "ne" -> {x++; y--;}
                case "se" -> {x++; y++;}
                default -> throw new IllegalArgumentException("Unknown direction: "+step);
            }
            int tempX=Math.abs(x);
            int tempY=Math.abs(y);
            int resUlt = tempX;
            if(y>x) resUlt+=(tempY-tempX)/2;
            maxDistance=Math.max(maxDistance, resUlt);
        }
        return maxDistance;
    }
}
