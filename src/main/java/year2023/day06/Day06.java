package year2023.day06;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.List;

public class Day06 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day06());
    }

    @Override
    public Object solveA(List<String> input) {
        List<Pair<Long, Long>> races = new ArrayList<>();
        long result=1;
        {
            String[] times = input.get(0).split("[^\\d]+");
            String[] distances = input.get(1).split("[^\\d]+");
            for (int i=1; i< times.length; i++) {
                races.add(new ImmutablePair<>(Long.parseLong(times[i]), Long.parseLong(distances[i])));
            }
        }
        for (var race: races) {
            result*=getWaysToWin(race);
        }
        return result;
    }

    long getWaysToWin(Pair<Long, Long> race) {
        for(long time=1; time<race.getLeft(); time++) {
            if (time*(race.getLeft()-time)>race.getRight()) {
                return race.getLeft()+1-2*time;
            }
        }
        return 0;
    }

    @Override
    public Object solveB(List<String> input) {
        Pair<Long, Long> race = new ImmutablePair<>(
                    Long.parseLong(input.get(0).replaceAll("[^\\d.]", "")),
                    Long.parseLong(input.get(1).replaceAll("[^\\d.]", "")));

        return getWaysToWin(race);
    }
}
