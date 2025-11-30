package year2024.day24;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;
import java.util.stream.Collectors;

public class Day24 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day24());
    }

    @Data
    private static class Node {
        String input1;
        String input2;
        String output;
        String operation;

        Node(Node other) {
            this.input1 = other.input1;
            this.input2 = other.input2;
            this.output = other.output;
            this.operation = other.operation;
        }
        Node(String input1, String input2, String output, String operation) {
            this.input1 = input1;
            this.input2 = input2;
            this.output = output;
            this.operation = operation;
        }
        HashSet<Node> getParents(HashMap<String, Node> nodes) {
            HashSet<Node> parents = new HashSet<>();
            if(input1 != null && input1.length()>1) {
                parents.add(nodes.get(input1));
                parents.addAll(nodes.get(input1).getParents(nodes));
            }
            if(input2 != null && input2.length()>1) {
                parents.add(nodes.get(input2));
                parents.addAll(nodes.get(input2).getParents(nodes));
            }
            return parents;
        }

        Integer getValue(HashMap<String, Node> nodes) {
            if(input2 == null)
                return Integer.valueOf(input1);

            if(!nodes.containsKey(input1)||!nodes.containsKey(input2)) {
                System.out.println("Missing node "+input1+" or "+input2);
            }
            int value1 = nodes.get(input1).getValue(nodes);
            int value2 = nodes.get(input2).getValue(nodes);
            return switch (operation) {
                case "AND" -> value1==1 && value2==1 ? 1 : 0;
                case "OR" ->  value1==1 || value2==1 ? 1 : 0;
                case "XOR" -> value1==1  ^ value2==1 ? 1 : 0;
                default -> throw new IllegalArgumentException();
            };
        }
    }


    @Override
    public Object solveA(List<String> input) {
        HashMap<String, Integer> values = new HashMap<>();
        ArrayList<String> reRun = new ArrayList<>();
        for (var line : input) {
            if(line.contains(":")) {
                String[] split = line.split(": ");
                values.put(split[0], Integer.parseInt(split[1]));
            }
            if(line.contains("->")) {
                if(!doOp(line.split(" "), values)) {
                    reRun.add(line);
                }
            }
        }
        while(!reRun.isEmpty()) {
            ArrayList<String> doReRun = new ArrayList<>(reRun);
            reRun.clear();
            for(String line : doReRun) {
                if(!doOp(line.split(" "), values)) {
                    reRun.add(line);
                }
            }
        }
        long result=0L;

        List<String> zVals = new ArrayList<>();
        for(String key : values.keySet())
            if(key.startsWith("z")) zVals.add(key);

        Collections.sort(zVals);
        Collections.reverse(zVals);

        for(String z : zVals) {
            result=result*2 + values.get(z);
        }
        return result;
    }
    private boolean doOp(String[] line, HashMap<String, Integer> values) {
        if(values.containsKey(line[0])&&values.containsKey(line[2])) {
            switch (line[1]) {
                case "AND" -> values.put(line[4], (values.get(line[0]) == 1 && values.get(line[2]) == 1) ? 1 : 0);
                case "OR"  -> values.put(line[4], (values.get(line[0]) == 1 || values.get(line[2]) == 1) ? 1 : 0);
                case "XOR" -> values.put(line[4], (values.get(line[0]) == 1 ^  values.get(line[2]) == 1) ? 1 : 0);
                default -> throw new IllegalArgumentException();
            }
            return true;
        }
        return false;
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<String, Node> nodes = new HashMap<>();
        HashMap<Node, HashSet<Node>> parents = new HashMap<>();

        int zNodes =0;
        for (var line : input) {
            if (line.contains("->")) {
                String[] split = line.split(" ");
                Node node = new Node(split[0], split[2], split[4], split[1]);
                nodes.put(split[4], node);
                if(node.output.startsWith("z")) zNodes++;
            }
        }
        for(int i=0; i<zNodes-1; i++) {
            Node xNode = new Node("0", null, null, null);
            Node yNode = new Node("0", null, null, null);
            nodes.put("x"+StringUtils.leftPad(""+i, 2, '0'), xNode);
            nodes.put("y"+StringUtils.leftPad(""+i, 2, '0'), yNode);
        }

        Stack<State> stack = new Stack<>();
        stack.add(new State(0, new ArrayList<>(), nodes));

        while(!stack.isEmpty()) {
            State current = stack.pop();
            if(current.passedStep==zNodes-1) {
                System.out.println("Done "+current.swaps);
            } else {
                stack.addAll(current.getNextStates(zNodes-1));
            }
        }

        /*
        for(int i=0; i<50; i++) {
            Node xNode = new Node("0", null, null, null);
            Node yNode = new Node("0", null, null, null);
            nodes.put("x"+StringUtils.leftPad(""+i, 2, '0'), xNode);
            nodes.put("y"+StringUtils.leftPad(""+i, 2, '0'), yNode);
        }
        //swappie
       // String swap1a="fkb";
       // String swap1b="z16";
       // String swap2a="nnr";
       // String swap2b="rqf";
       // String swap3a="rdn";
       // String swap3b="z31";
       // String swap4a="rrn";
       // String swap4b="z37";
       // swap(swap1a, swap1b, nodes);
       // swap(swap2a, swap2b, nodes);
       // swap(swap3a, swap3b, nodes);
       // swap(swap4a, swap4b, nodes);


        for(Node node : nodes.values()) {
            node.getParents(nodes, parents);
        }

        int bit=0;
        for(long x=1; bit<45; x*=2) {
            bit++;
            for(long y=1; y<8000; y*=2) {
                setXy(nodes, x, y);
                long value = 0L;
                for (int pos = 46; pos >= 0; pos--) {
                    String name = "z" + StringUtils.leftPad("" + pos, 2, '0');
                    if (nodes.containsKey(name))
                        value = value * 2 + nodes.get(name).getValue(nodes);
                }
                if (value != x + y) {
                    HashSet<Node> usedBefore = new HashSet<>();
                    for(int pos = 0; pos < bit; pos++) {
                        String name = "z" + StringUtils.leftPad("" + pos, 2, '0');
                        usedBefore.addAll(parents.get(nodes.get(name)));
                    }

                    String name = "z" + StringUtils.leftPad("" + bit, 2, '0');
                    HashSet<Node> newUsed = new HashSet<>(parents.get(nodes.get(name)));
                    newUsed.removeAll(usedBefore);

                    System.out.println("Suspects:");
                    for(Node node : newUsed) {
                        System.out.println(node.output + " " + node);
                    }
                    System.out.println("");
                    break;
                }
            }
        }
        return ""; */
        //var swapped = List.of(swap1a, swap1b, swap2a, swap2b, swap3a, swap3b, swap4a, swap4b);
        //return swapped.stream().sorted().collect(Collectors.joining(","));
        return "";
    }
/*
    ArrayList<String> findSuspectCable(HashMap<String, Node> nodes, HashMap<Node, HashSet<Node>> parents) {
        for(int i=0; i<50; i++) {
            Node xNode = new Node("0", null, null, null);
            Node yNode = new Node("0", null, null, null);
            nodes.put("x"+StringUtils.leftPad(""+i, 2, '0'), xNode);
            nodes.put("y"+StringUtils.leftPad(""+i, 2, '0'), yNode);
        }
        //swappie
        // String swap1a="fkb";
        // String swap1b="z16";

        // String swap2a="nnr";
        // String swap2b="rqf";

        // String swap3a="rdn";
        // String swap3b="z31";

        // String swap4a="rrn";
        // String swap4b="z37";
        // swap(swap1a, swap1b, nodes);
        // swap(swap2a, swap2b, nodes);
        // swap(swap3a, swap3b, nodes);
        // swap(swap4a, swap4b, nodes);

        ArrayList<String> suspectCables = new ArrayList<>();
        for(Node node : nodes.values()) {
            node.getParents(nodes, parents);
        }


        int bit=0;

        ArrayList<Pair<String, String>> swaps = new ArrayList<>();
        for(int maxBit=0; maxBit<45; ) {
            if (testUpToBit(maxBit, nodes, parents, swaps).isEmpty())
                maxBit++;

        }

        for(long x=1; bit<45; x*=2) {
            bit++;
            for(long y=1; y<8000; y*=2) {
                setXy(nodes, x, y);
                long value = 0L;
                for (int pos = 46; pos >= 0; pos--) {
                    String name = "z" + StringUtils.leftPad("" + pos, 2, '0');
                    if (nodes.containsKey(name))
                        value = value * 2 + nodes.get(name).getValue(nodes);
                }
                if (value != x + y) {
                    HashSet<Node> usedBefore = new HashSet<>();
                    for(int pos = 0; pos < bit; pos++) {
                        String name = "z" + StringUtils.leftPad("" + pos, 2, '0');
                        usedBefore.addAll(parents.get(nodes.get(name)));
                    }

                    String name = "z" + StringUtils.leftPad("" + bit, 2, '0');
                    HashSet<Node> newUsed = new HashSet<>(parents.get(nodes.get(name)));
                    newUsed.removeAll(usedBefore);

                    System.out.println("Suspects:");
                    suspectCables.add(name);
                    System.out.println(name + " ");

                    for(Node node : newUsed) {
                        suspectCables.add(node.output);
                        System.out.println(node.output + " " + node);
                    }
                    System.out.println("");
                    return suspectCables;
                }
            }
        }
        return suspectCables;
    }

 */
 /*   private ArrayList<String> testUpToBit(int maxBit, int currentBit, HashMap<String, Node> nodes, HashMap<Node, HashSet<Node>> parents, ArrayList<Pair<String, String>> swaps) {
 */
    /*
        if (currentBit==maxBit)  {
            ArrayList<String> allSwaps = new ArrayList<>();
            for(Pair<String, String> p : swaps) {
                allSwaps.add(p.getKey());
                allSwaps.add(p.getValue());
            }
            return allSwaps;
        }

        HashMap<String, Node> swappedNodes = swap(swaps, nodes);

        ArrayList<String> suspectCables = new ArrayList<>();
        int bit=currentBit;
        for(long x=bit; bit<maxBit; x*=2) {
            bit++;
            for(long y=1; y<8000; y*=2) {
                setXy(nodes, x, y);
                long value = getValue(nodes);
                if(value == x + y) {

                }
                if (value != x + y) {
                    HashSet<Node> usedBefore = new HashSet<>();
                    for(int pos = 0; pos < bit; pos++) {
                        String name = "z" + StringUtils.leftPad("" + pos, 2, '0');
                        usedBefore.addAll(parents.get(nodes.get(name)));
                    }

                    String name = "z" + StringUtils.leftPad("" + bit, 2, '0');
                    HashSet<Node> newUsed = new HashSet<>(parents.get(nodes.get(name)));
                    newUsed.removeAll(usedBefore);

                    suspectCables.add(name);
                    for(Node node : newUsed) {
                        suspectCables.add(node.output);
                    }
                    return suspectCables;
                }
            }
        }
        return suspectCables;

    }
*/
    static private HashMap<String, Node> setXy(HashMap<String, Node> nodes, long x, long y, int maxBit) {
        HashMap<String, Node> updatedNodes = new HashMap<>(nodes);

        String xStr = StringUtils.leftPad(Long.toBinaryString(x), maxBit, '0');
        String yStr = StringUtils.leftPad(Long.toBinaryString(y), maxBit, '0');
        for(int i=0; i<maxBit-1; i++) {
            updatedNodes.get("x"+StringUtils.leftPad(""+i, 2, '0')).setInput1(""+(xStr.charAt(maxBit-i-1)=='1'?1:0));
            updatedNodes.get("y"+StringUtils.leftPad(""+i, 2, '0')).setInput1(""+(yStr.charAt(maxBit-i-1)=='1'?1:0));
        }
        return updatedNodes;
    }
    private HashMap<String, Node> swap(String n1, String n2, HashMap<String, Node> nodes) {
        HashMap<String, Node> swappedNodes = new HashMap<>(nodes);
        if(!nodes.containsKey(n1)) throw new IllegalArgumentException("Node "+n1+" not present!");
        if(!nodes.containsKey(n2)) throw new IllegalArgumentException("Node "+n2+" not present!");

        var node1 = nodes.get(n1);
        var node2 = nodes.get(n2);
        node1.setOutput(n2);
        node2.setOutput(n1);
        swappedNodes.put(n1, node2);
        swappedNodes.put(n2, node1);
        return swappedNodes;
    }



    private record State (int passedStep, ArrayList<Pair<String, String>> swaps, HashMap<String, Node> nodes) {
        private List<State> getNextStates(int maxBit) {
            int bit = 0;
            ArrayList<State> nextStates = new ArrayList<>();
            var updatedNodes = swap(swaps, nodes);
            for (long x = 1; bit < maxBit; x *= 2) {
                int yBit = 0;
                for (long y = 1; yBit < bit; y *= 2) {
                    updatedNodes = setXy(nodes, x, y, maxBit);
                    long value = getValue(updatedNodes);
                    if (value == x + y) {
                        yBit++;
                    } else {
                        if (bit > passedStep) {
                            for (Pair<String, String> swap : findPotentialSwaps(bit, updatedNodes)){
                                ArrayList<Pair<String, String>> swaps = new ArrayList<>(this.swaps);
                                swaps.add(swap);
                                nextStates.add(new State(bit, swaps, nodes));
                            }
                        }
                        return nextStates;
                    }
                } 
                bit++;
            }
            nextStates.add(new State(bit, swaps, nodes));
            return nextStates;
        }

        private HashMap<String, Node> swap(ArrayList<Pair<String, String>> swaps, HashMap<String, Node> nodes) {
            HashMap<String, Node> swappedNodes = new HashMap<>(nodes);
            for(Pair<String, String> p : swaps) {
                if(!nodes.containsKey(p.getLeft())) throw new IllegalArgumentException("Node "+p.getLeft()+" not present!");
                if(!nodes.containsKey(p.getRight())) throw new IllegalArgumentException("Node "+p.getRight()+" not present!");

                var node1 = new Node(nodes.get(p.getLeft()));
                node1.setOutput(p.getRight());

                var node2 = new Node(nodes.get(p.getRight()));
                node2.setOutput(p.getLeft());

                swappedNodes.put(p.getLeft(), node2);
                swappedNodes.put(p.getRight(), node1);
            }

            return swappedNodes;
        }

        private long getValue(HashMap<String, Node> updatedNodes) {
            long value = 0L;
            for (int pos = 46; pos >= 0; pos--) {
                String name = "z" + StringUtils.leftPad("" + pos, 2, '0');
                if (updatedNodes.containsKey(name))
                    value = value * 2 + updatedNodes.get(name).getValue(updatedNodes);
            }
            return value;
        }

        private ArrayList<Pair<String, String>> findPotentialSwaps(int lastPassed, HashMap<String, Node> nodes) {
            int bit=lastPassed+1;
            HashSet<Node> usedBefore = new HashSet<>();
            ArrayList<String> suspectCables = new ArrayList<>();
            for (int pos = 0; pos < bit; pos++) {
                String name = "z" + StringUtils.leftPad("" + pos, 2, '0');
                usedBefore.addAll(nodes.get(name).getParents(nodes));
            }

            String name = "z" + StringUtils.leftPad("" + bit, 2, '0');
            HashSet<Node> newUsed = new HashSet<>(nodes.get(name).getParents(nodes));
            newUsed.removeAll(usedBefore);

            suspectCables.add("z" + StringUtils.leftPad("" + (bit), 2, '0'));
            suspectCables.add("z" + StringUtils.leftPad("" + (bit-1), 2, '0'));
            for (Node node : newUsed) {
                if (!suspectCables.contains(node.output))
                    suspectCables.add(node.output);
            }
            ArrayList<Pair<String, String>> swaps = new ArrayList<>();
            for (int i = 0; i < suspectCables.size(); i++) {
                for (int j = i + 1; j < suspectCables.size(); j++) {
                    swaps.add(new ImmutablePair<>(suspectCables.get(i), suspectCables.get(j)));
                }
            }
            return swaps;
        }
    }
}
