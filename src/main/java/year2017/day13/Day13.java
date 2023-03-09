package year2017.day13;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day13());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<Integer, Integer> filters = new HashMap<>();
        for(String row:input) {
            int position = Integer.parseInt(row.split(": ")[0]);
            int size = Integer.parseInt(row.split(": ")[1]);
            filters.put(position, size);
        }
        int cost=0;
        for(Map.Entry<Integer, Integer> s : filters.entrySet()) {
            if((s.getKey())%((s.getValue()-1)*2)==0)
                cost+=s.getKey()*s.getValue();
        }
        return cost;
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<Integer, Integer> filters = new HashMap<>();
        for(String row:input) {
            int position = Integer.parseInt(row.split(": ")[0]);
            int size = Integer.parseInt(row.split(": ")[1]);
            filters.put(position, size);
        }

        for(int delay=0; ; delay+=2) {
            boolean success =true;
            for (Map.Entry<Integer, Integer> s : filters.entrySet()) {
                if ((s.getKey() + delay) % ((s.getValue() - 1) * 2) == 0) {
                    success = false;
                    break;
                }
            }
            if(success)
                return delay;
        }
    }
}
