package year2020.day05;

import utils.FileHelper;

import java.util.*;

public class Day05 {

    static int solveA(List<String> values) {
        int highestSeat=0;
        for (String value: values) {
            highestSeat= Math.max(highestSeat, findSeatId(value));
        }
        return highestSeat;
    }

    static int solveB(List<String> values) {
        Set<Integer> seats = new HashSet<>();
        for (String value: values) {
            seats.add(findSeatId(value));
        }
        for(int i = 0; i<1024; i++) {
            if(!seats.contains(i) && seats.contains(i-1) && seats.contains(i+1)) return i;
        }
        return -1;
    }
    static int findSeatId(String seatData) {
        int seat=0;
        int mul=1;
        for(int i=9; i>=0;i--) {
            if(seatData.charAt(i)=='B' || seatData.charAt(i)=='R')
                seat+=mul;
            mul*=2;
        }
        return seat;
    }


    public static void main(String[] args){
        var day = "Day05";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = System.currentTimeMillis()-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //989
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //548
    }
}
