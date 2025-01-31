package year2024.day22;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day22 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day22());
    }

    @Override
    public Object solveA(List<String> input) {

        long result =0L;
        for(String line : input){
            long secret = Long.parseLong(line);
            for(int i=0; i<2000; i++){
                long newSecret=secret;
                newSecret = mix(64*newSecret, newSecret);
                newSecret = prune(newSecret);
                newSecret = mix(newSecret/32, newSecret);
                newSecret = prune(newSecret);
                newSecret = mix(2048*newSecret, newSecret);
                newSecret = prune(newSecret);
                secret=newSecret;
            }
            result+=secret;
        }
        return result;
    }

    private long  mix(long value, long secret){
        return value^secret;
    }
    private long prune(long value) {
        return value%16777216;
    }

    @Override
    public Object solveB(List<String> input) {

        HashSet<List<Integer>> allSeries = new HashSet<>();
        HashMap<List<Integer>, Integer> allBuyers = new HashMap<>();

        for(String line : input){
            List<Integer> lastValues =new ArrayList<>();
            long secret = Long.parseLong(line);

            lastValues.add(Math.toIntExact(secret%10));

            for(int i=0; i<2000; i++){
                long newSecret=secret;
                newSecret = mix(64*newSecret, newSecret);
                newSecret = prune(newSecret);
                newSecret = mix(newSecret/32, newSecret);
                newSecret = prune(newSecret);
                newSecret = mix(2048*newSecret, newSecret);
                newSecret = prune(newSecret);
                secret=newSecret;
                lastValues.add(Math.toIntExact(secret%10));
            }
            List<Integer> differences = new ArrayList<>();
            for(int i=1; i<lastValues.size(); i++){
                differences.add(lastValues.get(i)-lastValues.get(i-1));
            }
            HashMap<List<Integer>, Integer> currentBuyerMap = new HashMap<>();
            for(int a =4; a<differences.size(); a++) {
                List<Integer> deltas = differences.subList(a-4, a);
                int value = lastValues.get(a);
                if(currentBuyerMap.containsKey(deltas)) {
                    int valBeforeThisRun = currentBuyerMap.get(deltas);
                    if(valBeforeThisRun<valBeforeThisRun) {
                        allBuyers.put(deltas, value - valBeforeThisRun+ value);
                        currentBuyerMap.put(deltas, value);
                    }
                } else {
                    currentBuyerMap.put(deltas, value);
                    int valBefore = allBuyers.getOrDefault(deltas, 0);
                    allBuyers.put(deltas, value+valBefore);
                    currentBuyerMap.put(deltas, value);
                    allSeries.add(deltas);
                }
            }
        }
        return allBuyers.values().stream().mapToInt(i->i).max().getAsInt();

    }
}
