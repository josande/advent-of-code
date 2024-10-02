package year2023.day05;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day05 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day05());
    }

    @Override
    public Object solveA(List<String> input) {
        ArrayList<Long> seeds = new ArrayList<>();
        ArrayList<String> seedToSoil = new ArrayList<>();
        ArrayList<String> soilToFertilizer = new ArrayList<>();
        ArrayList<String> fertilizerToWater = new ArrayList<>();
        ArrayList<String> waterToLight = new ArrayList<>();
        ArrayList<String> lightToTemperature  = new ArrayList<>();
        ArrayList<String> temperatureToHumidity  = new ArrayList<>();
        ArrayList<String> tempMap = new ArrayList<>();
        for(String line : input) {
            if(line.startsWith("seeds:")) {
                String[] tmp = line.split(" ");
                for(int i=1; i<tmp.length; i++) {
                    seeds.add(Long.parseLong(tmp[i]));
                }
            }
            else //noinspection StatementWithEmptyBody
                if( line.startsWith("seed-to")) {
                //do nothing
            }
            else if( line.startsWith("soil")) {
                seedToSoil.addAll(tempMap);
                tempMap.clear();
            }
            else if( line.startsWith("fertilizer")) {
                soilToFertilizer.addAll(tempMap);
                tempMap.clear();
            }
            else if( line.startsWith("water")) {
                fertilizerToWater.addAll(tempMap);
                tempMap.clear();
            }
            else if( line.startsWith("light")) {
                waterToLight.addAll(tempMap);
                tempMap.clear();
            }
            else if( line.startsWith("temperature")) {
                lightToTemperature.addAll(tempMap);
                tempMap.clear();
            }
            else if( line.startsWith("humidity")) {
                temperatureToHumidity.addAll(tempMap);
                tempMap.clear();
            } else if (!line.isEmpty()) {
                tempMap.add(line);
            }
        }
        ArrayList<String> humidityToLocation = new ArrayList<>(tempMap);

        long minValue = Long.MAX_VALUE;
        for(long seed : seeds) {

            long soil = mapTo(seedToSoil, seed);
            long fert = mapTo(soilToFertilizer, soil);
            long wate = mapTo(fertilizerToWater, fert);
            long ligh = mapTo(waterToLight, wate);
            long temp = mapTo(lightToTemperature, ligh);
            long humi = mapTo(temperatureToHumidity, temp);
            long loca = mapTo(humidityToLocation, humi);
            minValue= Math.min(minValue, loca);
        }

        return minValue;
    }

    private long mapTo(ArrayList<String> map, long fromValue) {
        for(String line : map) {
            long to = Long.parseLong(line.split(" ")[0]);
            long from = Long.parseLong(line.split(" ")[1]);
            long range = Long.parseLong(line.split(" ")[2]);

            if(fromValue>=from && fromValue<(from+range)) {
                return fromValue+(to-from);
            }

        }
        return fromValue;
    }

    long stepToNextLimit=1;
    @Override
    public Object solveB(List<String> input) {
        ArrayList<Pair<Long, Long>> seeds = new ArrayList<>();
        ArrayList<String> seedToSoil = new ArrayList<>();
        ArrayList<String> soilToFertilizer = new ArrayList<>();
        ArrayList<String> fertilizerToWater = new ArrayList<>();
        ArrayList<String> waterToLight = new ArrayList<>();
        ArrayList<String> lightToTemperature  = new ArrayList<>();
        ArrayList<String> temperatureToHumidity  = new ArrayList<>();
        ArrayList<String> tempMap = new ArrayList<>();
        for(String line : input) {
            if(line.startsWith("seeds:")) {
                String[] tmp = line.split(" ");
                for(int i=1; i<tmp.length; i=i+2) {
                    seeds.add(new ImmutablePair<>(Long.parseLong(tmp[i]), Long.parseLong(tmp[i+1])));
                }
            }
            else //noinspection StatementWithEmptyBody
                if( line.startsWith("seed-to")) {
                //do nothing
            }
            else if( line.startsWith("soil")) {
                seedToSoil.addAll(tempMap);
                tempMap.clear();
            }
            else if( line.startsWith("fertilizer")) {
                soilToFertilizer.addAll(tempMap);
                tempMap.clear();
            }
            else if( line.startsWith("water")) {
                fertilizerToWater.addAll(tempMap);
                tempMap.clear();
            }
            else if( line.startsWith("light")) {
                waterToLight.addAll(tempMap);
                tempMap.clear();
            }
            else if( line.startsWith("temperature")) {
                lightToTemperature.addAll(tempMap);
                tempMap.clear();
            }
            else if( line.startsWith("humidity")) {
                temperatureToHumidity.addAll(tempMap);
                tempMap.clear();
            } else if (!line.isEmpty()) {
                tempMap.add(line);
            }
        }
        ArrayList<String> humidityToLocation = new ArrayList<>(tempMap);

        for (long location =0L; ; location+=stepToNextLimit) {
            stepToNextLimit = Long.MAX_VALUE;

            long humi = mapFrom(humidityToLocation, location);
            long temp = mapFrom(temperatureToHumidity, humi);
            long ligh = mapFrom(lightToTemperature, temp);
            long wate = mapFrom(waterToLight, ligh);
            long fert = mapFrom(fertilizerToWater, wate);
            long soil = mapFrom(soilToFertilizer, fert);

            long seed = mapFrom(seedToSoil, soil);
            if (inSeeds(seeds, seed)) {
                return location;
            }
        }
    }
    boolean inSeeds(ArrayList<Pair<Long, Long>> seeds, Long value) {
        for (var seed : seeds) {
            if (value >= seed.getLeft() && value < seed.getLeft()+seed.getRight())
                return true;
        }
        return false;
    }

    private long mapFrom(ArrayList<String> map, long fromValue) {
        for(String line : map) {
            long to = Long.parseLong(line.split(" ")[0]);
            long from = Long.parseLong(line.split(" ")[1]);
            long range = Long.parseLong(line.split(" ")[2]);

            if(fromValue < to)
                stepToNextLimit = Math.min(stepToNextLimit, to-fromValue);

            if(fromValue>=to && fromValue<(to+range)) {
                stepToNextLimit = Math.min(stepToNextLimit, to+range - fromValue);
                return fromValue+(from-to);
            }
        }
        return fromValue;
    }
}
