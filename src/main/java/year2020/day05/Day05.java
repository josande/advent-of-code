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
        int minRow=0;
        int maxRow=127;
        int curRow=(minRow+maxRow)/2;;
        int minCol=0;
        int maxCol=7;
        int curCol=(minCol+maxCol)/2;
        for(char c : seatData.toCharArray()) {
             if (c=='F') {
                maxRow = curRow;
                curRow=(minRow+maxRow)/2;
             } else if (c=='B'){
                minRow = curRow+1;
                curRow=(minRow+maxRow)/2;
             } else if (c=='L') {
                maxCol = curCol;
                curCol=(minCol+maxCol)/2;
             } else if (c=='R'){
                minCol = curCol+1;
                curCol=(minCol+maxCol)/2;
             }
        }
        return 8*curRow+curCol;
    }


    public static void main(String[] args){
        String day = "Day05";

        List<String> inputs = new FileHelper().readFile("2020/"+day+".txt");

        long t0 = System.currentTimeMillis();
        int ansA=solveA(inputs);
        long t1 = System.currentTimeMillis();
        int ansB=solveB(inputs);
        long timePart1 = System.currentTimeMillis()-t0;
        long timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //989
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //548
    }
}
