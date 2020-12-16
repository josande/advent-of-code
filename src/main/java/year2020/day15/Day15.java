package year2020.day15;

import utils.FileHelper;
import java.util.*;

public class Day15 {


    static int solveA(List<String> values) {
        List<Integer> numbers = new ArrayList<>();
        for(String v : values.get(0).split(",")) {
            numbers.add(Integer.parseInt(v));
        }
        int lastNumber=-1;
        while(numbers.size()<=2020) {
            lastNumber = numbers.get(numbers.size()-1);
            int firstIndex=numbers.indexOf(lastNumber);
            if(firstIndex != numbers.size()-1) {
               numbers.set(firstIndex, -1);
               numbers.add(numbers.size()-1-firstIndex);
            } else {
                numbers.add(0);
            }
        }
        return lastNumber;
    }

    static long solveB(List<String> values) {
        // value, lastSeen
        HashMap<Integer, Integer> numbers = new HashMap<>();
        int turn=1;
        for(String v : values.get(0).split(",")) {
            numbers.put(Integer.parseInt(v), turn);
            turn++;
        }
        int nextNumber=0;
        int currentNumber=0;
        while(turn<=30000000) {
            currentNumber=nextNumber;
            if( numbers.containsKey(nextNumber)) {
                nextNumber=turn-numbers.get(nextNumber);
            } else {
                nextNumber = 0;
            }
            numbers.put(currentNumber, turn);
            turn++;
        }
        return currentNumber;

    }



    public static void main (String[] args){
        var day = "Day15";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //447
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //11721679
    }
}
