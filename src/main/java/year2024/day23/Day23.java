package year2024.day23;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;
import java.util.stream.Collectors;

public class Day23 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day23());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<String, HashSet<String>> connections = new HashMap<>();
        for (String line : input) {
            String[] nodes = line.split("-");

            var setA = connections.getOrDefault(nodes[0], new HashSet<>());
            setA.add(nodes[1]);
            connections.put(nodes[0], setA);

            var setB = connections.getOrDefault(nodes[1], new HashSet<>());
            setB.add(nodes[0]);
            connections.put(nodes[1], setB);
        }
        HashSet<HashSet<String>> tripples = new HashSet<>();
        for(var fromNode : connections.entrySet()){
            if(fromNode.getKey().startsWith("t")) {
                HashSet<String> candidates = new HashSet<>(fromNode.getValue());
                for(var toNode: fromNode.getValue()) {
                    var candidates2 = new HashSet<>(connections.get(toNode));
                    candidates2.retainAll(candidates);
                    for(String sharedConnection : candidates2) {
                        HashSet<String> tripple = new HashSet<>();
                        tripple.add(fromNode.getKey());
                        tripple.add(toNode);
                        tripple.add(sharedConnection);
                        tripples.add(tripple);
                    }
                }
            }
        }
        return tripples.size();
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<String, HashSet<String>> connections = new HashMap<>();
        for (String line : input) {
            String[] nodes = line.split("-");

            var setA = connections.getOrDefault(nodes[0], new HashSet<>());
            setA.add(nodes[1]);
            connections.put(nodes[0], setA);

            var setB = connections.getOrDefault(nodes[1], new HashSet<>());
            setB.add(nodes[0]);
            connections.put(nodes[1], setB);
        }

        return findLargestGroup(connections);
    }
    String findLargestGroup(HashMap<String, HashSet<String>> connections) {
        List<String> bestSoFar = null;
        HashSet<HashSet<String>> seen = new HashSet<>();
        int maxLength = 0;

        for(String start : connections.keySet()) {
            ArrayList<String> group = new ArrayList<>();
            group.add(start);

            Stack<ArrayList<String>> stack = new Stack<>();
            stack.add(group);
            while (!stack.isEmpty()) {
                ArrayList<String> current = stack.pop();
                HashSet<String> ignoreOrder = new HashSet<>(current);
                if (seen.contains(ignoreOrder)) continue;
                seen.add(ignoreOrder);
                for (String next : connections.get(current.getLast())) {
                    if (current.contains(next)) continue;
                    if (connections.get(next).containsAll(current)) {
                        var toAdd = new ArrayList<>(current);
                        toAdd.add(next);
                        if (toAdd.size() > maxLength) {
                            maxLength = toAdd.size();
                            bestSoFar = toAdd;
                        }
                        stack.add(toAdd);
                    }
                }
            }
        }
        Collections.sort(bestSoFar);
        return bestSoFar.stream().collect(Collectors.joining(","));
    }
}
