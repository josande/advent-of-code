
package year2017.day12;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Day12 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day12());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<Integer, List<Integer>> pipes = new HashMap<>();
        for(String row : input) {
            row=row.replaceAll(" ","");
            int id=Integer.parseInt(row.split("<->")[0]);
            ArrayList<Integer> targets=new ArrayList<>();
            for(String to : row.split("<->")[1].split(",") ) {
                targets.add(Integer.parseInt(to));
            }
            pipes.put(id, targets);
        }

        LinkedList<Integer> queue = new LinkedList<>();
        ArrayList<Integer> seen = new ArrayList<>();
        queue.add(0);
        while(!queue.isEmpty()) {
            Integer current = queue.poll();
            if(!seen.contains(current)) {

            seen.add(current);
            queue.addAll(pipes.get(current));
            }
        }
        return seen.size();
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<Integer, List<Integer>> pipes = new HashMap<>();
        for(String row : input) {
            row=row.replaceAll(" ","");
            int id=Integer.parseInt(row.split("<->")[0]);
            ArrayList<Integer> targets=new ArrayList<>();
            for(String to : row.split("<->")[1].split(",") ) {
                targets.add(Integer.parseInt(to));
            }
            pipes.put(id, targets);
        }

        int groups=0;
        while(!pipes.isEmpty()) {
            LinkedList<Integer> queue = new LinkedList<>();
            ArrayList<Integer> seen = new ArrayList<>();
            queue.add(pipes.entrySet().iterator().next().getKey());
            while (!queue.isEmpty()) {
                Integer current = queue.poll();
                if (!seen.contains(current)) {
                    seen.add(current);
                    queue.addAll(pipes.get(current));
                    pipes.remove(current);
                }
            }
            groups++;
        }
        return groups;
    }
}
