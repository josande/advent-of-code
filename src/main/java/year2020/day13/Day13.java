package year2020.day13;

import utils.FileHelper;

import java.util.*;

public class Day13 {


    static int solveA(List<String> values) {
        int startTime = Integer.parseInt(values.get(0));
        int shortestWait=startTime, busId=-1;
        for (String bus : values.get(1).split(",")) {
            if(bus.equals("x")) continue;
            int currentBusId=Integer.parseInt(bus);
            if (currentBusId-startTime%currentBusId < shortestWait) {
                shortestWait=currentBusId-startTime%currentBusId;
                busId=currentBusId;
            }
        }
        return shortestWait*busId;

    }

    static long solveB(List<String> values) {
        HashMap<Integer, Integer> timeTable= new HashMap<>();
        int pos=0;
        for (String bus : values.get(1).split(",")) {
            if(!bus.equals("x")) {
                timeTable.put(pos, Integer.parseInt(bus));
            }
            pos++;
        }

        long cycle=1;
        long firstMatch=0;
        for(Map.Entry<Integer, Integer> entry : timeTable.entrySet()) {
            long firstHit = getNext(firstMatch, cycle, entry.getValue(), entry.getKey());
            long nextHit = getNext(firstHit+cycle, cycle, entry.getValue(), entry.getKey());
            cycle=nextHit-firstHit;
            firstMatch=firstHit;
        }
        return firstMatch;
    }
    private static long getNext(long start, long cycle, int number, int offset ) {
        for (long t=start;;t+=cycle) {
            if(0L == (t+offset) % number) return t;
        }
    }

    public static void main(String[] args){
        var day = "Day13";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //3246
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //1010182346291467
    }
}
