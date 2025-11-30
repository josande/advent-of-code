package year2021.day06;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day06 {

    static Object solveA(List<String> values) {
        long[] fishes = new long[9];
        String[] startState = values.get(0).split(",");
        fishes[0] = Stream.of(startState).filter(s -> s.equals("0")).count();
        fishes[1] = Stream.of(startState).filter(s -> s.equals("1")).count();
        fishes[2] = Stream.of(startState).filter(s -> s.equals("2")).count();
        fishes[3] = Stream.of(startState).filter(s -> s.equals("3")).count();
        fishes[4] = Stream.of(startState).filter(s -> s.equals("4")).count();
        fishes[5] = Stream.of(startState).filter(s -> s.equals("5")).count();
        fishes[6] = Stream.of(startState).filter(s -> s.equals("6")).count();
        fishes[7] = 0;
        fishes[8] = 0;

        for(int days = 0; days < 80; days++) {
            long temp = fishes[0];
            fishes[0] = fishes[1];
            fishes[1] = fishes[2];
            fishes[2] = fishes[3];
            fishes[3] = fishes[4];
            fishes[4] = fishes[5];
            fishes[5] = fishes[6];
            fishes[6] = fishes[7] + temp;
            fishes[7] = fishes[8];
            fishes[8] = temp;
        }

        long sum=0;
        for (long fish : fishes) {
            sum += fish;
        }
        return sum;
    }

    static Object solveB(List<String> values) {
        long[] fishes = new long[9];
        String[] startState = values.get(0).split(",");
        fishes[0] = Stream.of(startState).filter(s -> s.equals("0")).count();
        fishes[1] = Stream.of(startState).filter(s -> s.equals("1")).count();
        fishes[2] = Stream.of(startState).filter(s -> s.equals("2")).count();
        fishes[3] = Stream.of(startState).filter(s -> s.equals("3")).count();
        fishes[4] = Stream.of(startState).filter(s -> s.equals("4")).count();
        fishes[5] = Stream.of(startState).filter(s -> s.equals("5")).count();
        fishes[6] = Stream.of(startState).filter(s -> s.equals("6")).count();
        fishes[7] = 0;
        fishes[8] = 0;

        for(int days = 0; days < 256; days++) {
            long temp = fishes[0];
            fishes[0] = fishes[1];
            fishes[1] = fishes[2];
            fishes[2] = fishes[3];
            fishes[3] = fishes[4];
            fishes[4] = fishes[5];
            fishes[5] = fishes[6];
            fishes[6] = fishes[7] + temp;
            fishes[7] = fishes[8];
            fishes[8] = temp;
        }

        long sum=0;
        for (long fish : fishes) {
            sum += fish;
        }
        return sum;
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //353274
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //1609314870967
    }
}
