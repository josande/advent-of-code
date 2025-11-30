package year2023.day25;


import org.w3c.dom.Node;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day25 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day25());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<String, ArrayList<String>> edges = new HashMap<>();
        for(String in : input) {
            String nodeName = in.split(": ")[0];
            edges.putIfAbsent(nodeName, new ArrayList<>());

            List<String> targets = Arrays.stream(in.split(": ")[1].split(" ")).toList();
            for(String target : targets) {
                edges.get(nodeName).add(target);
                edges.putIfAbsent(target, new ArrayList<>());
                edges.get(target).add(nodeName);
            }
        }

        while(true) {
            HashMap<String, ArrayList<String>> tempMap = new HashMap<>();
            for(String node : edges.keySet()) {
                tempMap.put(node, new ArrayList<>(edges.get(node)));
            }
            while (tempMap.size() > 2) {
                Random random = new Random();
                String start = new ArrayList<>(tempMap.keySet()).get(random.nextInt(tempMap.keySet().size()));
                String end = new ArrayList<>(tempMap.get(start)).get(random.nextInt(tempMap.get(start).size()));
                mergeNodes(start, end, tempMap);
                removeLinksToSelf(tempMap);
            }
            if(tempMap.values().stream().findAny().get().size() == 3) {
                ArrayList<String> nodes = new ArrayList<>(tempMap.keySet());
                return nodes.get(0).split(",").length * nodes.get(1).split(",").length;
            }
        }
    }

    void removeLinksToSelf(HashMap<String, ArrayList<String>> edges) {
        for(String name : edges.keySet()) {
            while(edges.get(name).contains(name))
                edges.get(name).remove(name);
        }
    }

    void mergeNodes(String nodeA, String nodeB, HashMap<String, ArrayList<String>> edges) {
        String newName = nodeA + "," + nodeB;
        for(ArrayList<String> name : edges.values()) {
            while(name.contains(nodeA)) {
                name.remove(nodeA);
                name.add(newName);
            }
            while(name.contains(nodeB)) {
                name.remove(nodeB);
                name.add(newName);
            }
        }

        ArrayList<String> newEdges = edges.get(nodeA);
        newEdges.addAll(edges.get(nodeB));
        edges.put(newName, new ArrayList<>(newEdges));
        edges.remove(nodeA);
        edges.remove(nodeB);
    }


    @Override
    public Object solveB(List<String> input) {
        return "Merry X-mas!";
    }
}
