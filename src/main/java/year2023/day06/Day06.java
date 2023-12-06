package year2023.day06;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.List;

public class Day06 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day06());
    }

    @Override
    public Object solveA(List<String> input) {
        List<Pair<Integer, Integer>> races = new ArrayList<>();
        int result=1;
        {
            String[] times = input.get(0).split("[^\\d]+");
            String[] distances = input.get(1).split("[^\\d]+");
            for (int i=1; i< times.length; i++) {
                races.add(new ImmutablePair<>(Integer.parseInt(times[i]), Integer.parseInt(distances[i])));
            }
        }
        for (var race: races) {
            int waysToWin=0;
            for(int time=0; time<race.getLeft(); time++) {
                if (time*(race.getLeft()-time)>race.getRight()) waysToWin++;
            }
            result*=waysToWin;
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        List<Pair<Long, Long>> races = new ArrayList<>();
        long result=1;
        {
            String line1 = input.get(0).replaceAll("[^\\d.]", "");
            String line2 = input.get(1).replaceAll("[^\\d.]", "");

            races.add(new ImmutablePair<>(Long.parseLong(line1), Long.parseLong(line2)));

        }
        for (var race: races) {
            int waysToWin=0;
            for(long time=1; time<race.getLeft(); time++) {
                if (time*(race.getLeft()-time)>race.getRight()) {
                    return race.getLeft()+race.getLeft()%2-2*time;
                }
            }
            result*=waysToWin;
        }
        return result;
    }
}
