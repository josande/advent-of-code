package year2020.day16;

import utils.FileHelper;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {


    static int solveA(List<String> values) {
        List<Integer> lowerBounds = new ArrayList<>();
        List<Integer> upperBounds = new ArrayList<>();
        for(var v : values) {
            for(var part : v.split(" ")) {
                if(part.contains("-"))  {
                    lowerBounds.add(Integer.parseInt(part.split("-")[0]));
                    upperBounds.add(Integer.parseInt(part.split("-")[1]));
                }
            }
        }
        int result=0;
        int part3=values.indexOf("nearby tickets:")+1;
        for(int i = part3; i<values.size(); i++) {
            for(var value : values.get(i).split(",")) {

                if (notInAnyRange(Integer.parseInt(value), lowerBounds, upperBounds)) {
                    result+=Integer.parseInt(value);
                }
            }
        }

        return result;
    }

    private static boolean notInAnyRange(Integer value, List<Integer> lowerBounds, List<Integer> upperBounds) {
        for(int i=0; i<lowerBounds.size(); i++) {
            if (lowerBounds.get(i) <= value && value <= upperBounds.get(i))
                return false;
        }
        return true;
    }

    static long solveB(List<String> values) {
        List<String> names=new ArrayList<>();
        List<Integer> lowerBounds=new ArrayList<>();
        List<Integer> upperBounds=new ArrayList<>();

        int part2=values.indexOf("your ticket:");

        for(int i = 0; i<part2; i++) {
            if(values.get(i).contains(":"))
                names.add(values.get(i).split(":")[0]);

            for(var part : values.get(i).split(" ")) {
                if(part.contains("-"))  {
                    lowerBounds.add(Integer.parseInt(part.split("-")[0]));
                    upperBounds.add(Integer.parseInt(part.split("-")[1]));
                }
            }
        }


        List<Integer> yourTicket = Arrays.stream(values.get(part2+1).split(",")).map(Integer::parseInt).collect(Collectors.toList());


        int part3=values.indexOf("nearby tickets:");
        ArrayList<List<Integer>> tickets = new ArrayList<>();
        for(int i = part3+1; i<values.size(); i++) {
            if(allInRange(values.get(i), lowerBounds, upperBounds)) {
                tickets.add(Arrays.stream(values.get(i).split(",")).map(Integer::parseInt).collect(Collectors.toList()));
            }
        }
        HashMap<String, Integer> namePos = new HashMap<>();
        while(namePos.size()<names.size()) {
            for (int pos = 0; pos < tickets.get(0).size(); pos++) {
                var matches = getMatches(pos, tickets, lowerBounds, upperBounds);
                if (matches.size() == 1) {
                    lowerBounds.set(2*matches.get(0), -1);
                    lowerBounds.set(2*matches.get(0) + 1, -1);
                    upperBounds.set(2*matches.get(0), -1);
                    upperBounds.set(2*matches.get(0) + 1, -1);
                    namePos.put(names.get(matches.get(0)), pos);
                }
            }
        }
        long result = 1;
        for(var np : namePos.entrySet()) {
            if(np.getKey().startsWith("departure")) {
                result *= yourTicket.get(np.getValue());
            }
        }
        return result;

    }

    static boolean allInRange(String row, List<Integer> lowerBounds, List<Integer> upperBounds) {
        for(var value : row.split(",")) {
            if (notInAnyRange(Integer.parseInt(value), lowerBounds, upperBounds)) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> getMatches(Integer position, ArrayList<List<Integer>> tickets, List<Integer> lowerBounds, List<Integer> upperBounds) {
        List<Integer> matches = new ArrayList<>();
        for(int i=0;i<lowerBounds.size()/2; i++) {
            boolean match = true;
            for (var ticket : tickets) {
                if (!((lowerBounds.get(2*i) <= ticket.get(position) && ticket.get(position)<= upperBounds.get(2*i)) ||
                    (lowerBounds.get(2*i+1) <= ticket.get(position) && ticket.get(position)<= upperBounds.get(2*i+1))) ) {
                    match = false;
                    break;
                }
            }
            if(match)
                matches.add(i);
        }
        return matches;
    }



    public static void main (String[] args){
        var day = "Day16";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //25059
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //3253972369789
    }
}
