package year2017.day06;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day06 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day06());
    }

    @Override
    public Object solveA(List<String> input) {
        ArrayList<Integer> banks = new ArrayList<>();
        for(String value : input.get(0).split("[ \t]+")) {
            banks.add(Integer.parseInt(value));
        }
        HashSet<ArrayList<Integer>> seenStates = new HashSet<>();

        seenStates.add(banks);

        while(true) {
            int biggest = Collections.max(banks);
            int position = banks.indexOf(biggest);
            banks.set(position,0);
            for(int i=0; i<biggest;i++) {
                position=(position+1)%banks.size();
                banks.set(position, banks.get(position)+1);
            }
            if(!seenStates.add(banks)) return seenStates.size();
        }
    }

    @Override
    public Object solveB(List<String> input) {
        ArrayList<Integer> banks = new ArrayList<>();
        for(String value : input.get(0).split("[ \t]+")) {
            banks.add(Integer.parseInt(value));
        }
        HashMap<ArrayList<Integer>, Integer> seenStates = new HashMap<>();

        int steps=0;
        seenStates.put(banks,steps);

        for(;;steps++) {
            int biggest = Collections.max(banks);
            int position = banks.indexOf(biggest);
            banks.set(position,0);
            for(int i=0; i<biggest;i++) {
                position=(position+1)%banks.size();
                banks.set(position, banks.get(position)+1);
            }
            if(seenStates.containsKey(banks))
                return steps - seenStates.get(banks);
            seenStates.put(banks, steps);
        }
    }
}
