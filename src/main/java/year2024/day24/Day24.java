package year2024.day24;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;
import java.util.stream.Collectors;

public class Day24 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day24());
    }

    @Data
    private class Node {
        String input1;
        String input2;
        String output;
        final String originalOutput;
        String operation;

        Node(String input1, String input2, String output, String operation) {
            this.input1 = input1;
            this.input2 = input2;
            this.output = output;
            originalOutput = output;
            this.operation = operation;

            if(output!= null && output.startsWith("z") && !operation.equals("XOR"))
                System.out.println("bad node! "+originalOutput);
        }
        boolean canDoOp(HashMap<String, Integer> values) {
            return values.containsKey(input1) && values.containsKey(input2);
        }
        Integer doOp(HashMap<String, Integer> values) {
            return switch (operation) {
                case "AND" -> values.get(input1) == 1 && values.get(input2) == 1  ? 1 : 0;
                case "OR" ->  values.get(input1) == 1 || values.get(input2) == 1 ? 1 : 0;
                case "XOR" -> values.get(input1) == 1 ^ values.get(input2) == 1 ? 1 : 0;
                default -> throw new IllegalArgumentException();
            };
        }
        boolean canRenameTo(String newName, HashMap<String, Node> nodes, HashMap<Node, HashSet<Node>> allParents) {

            if(newName.equals(input1) || newName.equals(input2)){ return false;}
            if(getParents(nodes, allParents).contains(newName)) return false;
            return true;
        }
        void rename(String newName) {
            output=newName;
        }
        HashSet<Node> getParents(HashMap<String, Node> nodes, HashMap<Node, HashSet<Node>> allParents) {
            if(allParents.containsKey(this))
                return allParents.get(this);
            HashSet<Node> parents = new HashSet<>();
            if(input1 != null && input1.length()>1) {
                parents.add(nodes.get(input1));
                parents.addAll(nodes.get(input1).getParents(nodes, allParents));
            }
            if(input2 != null && input2.length()>1) {
                parents.add(nodes.get(input2));
                parents.addAll(nodes.get(input2).getParents(nodes, allParents));
            }
            allParents.put(this, parents);
            return parents;
        }

        Integer getValue(HashMap<String, Node> nodes) {
            if(input2 == null) {
                return Integer.valueOf(input1);
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
                case "OR" -> values.put(line[4], (values.get(line[0]) == 1 || values.get(line[2]) == 1) ? 1 : 0);
                case "XOR" -> values.put(line[4], (values.get(line[0]) == 1 ^ values.get(line[2]) == 1) ? 1 : 0);
                default -> throw new IllegalArgumentException();
            }
            return true;
        }
        return false;
    }

    private record State(HashMap<String, List<String>> parents, HashSet<String> swapped, int lastFailedAt) {}
    //private record Node(String input1, String input2, String operation) {
    //    boolean perform(HashMap<>) {
    //        switch (operation) {
    //            case "AND" -> values.put(line[4], (values.get(line[0]) == 1 && values.get(line[2]) == 1) ? 1 : 0);
    //            case "OR" -> values.put(line[4], (values.get(line[0]) == 1 || values.get(line[2]) == 1) ? 1 : 0);
    //            case "XOR" -> values.put(line[4], (values.get(line[0]) == 1 ^ values.get(line[2]) == 1) ? 1 : 0);
    //    }
    //}



    //Error at
    //  -> z16
    //  -> z31
    //  -> z37


    @Override
    public Object solveB(List<String> input) {
        HashMap<String, Node> nodes = new HashMap<>();
        HashMap<Node, HashSet<Node>> parents = new HashMap<>();

        for (var line : input) {
            if (line.contains("->")) {
                String[] split = line.split(" ");
                Node node = new Node(split[0], split[2], split[4], split[1]);
                nodes.put(split[4], node);
            }
        }



        for(int i=0; i<50; i++) {
            Node xNode = new Node("0", null, null, null);
            Node yNode = new Node("0", null, null, null);
            nodes.put("x"+StringUtils.leftPad(""+i, 2, '0'), xNode);
            nodes.put("y"+StringUtils.leftPad(""+i, 2, '0'), yNode);
        }


        //swappie
        String swap1a="fkb";
        String swap1b="z16";
        String swap2a="nnr";
        String swap2b="rqf";
        String swap3a="rdn";
        String swap3b="z31";
        String swap4a="rrn";
        String swap4b="z37";
        swap(swap1a, swap1b, nodes);
        swap(swap2a, swap2b, nodes);
        swap(swap3a, swap3b, nodes);
        swap(swap4a, swap4b, nodes);


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
                    HashSet<Node> newUsed = new HashSet<>();

                    String name = "z" + StringUtils.leftPad("" + bit, 2, '0');
                    newUsed.addAll(parents.get(nodes.get(name)));
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


        var swapped = List.of(swap1a, swap1b, swap2a, swap2b, swap3a, swap3b, swap4a, swap4b);
        return swapped.stream().sorted().collect(Collectors.joining(","));
    }

    private void setXy(HashMap<String, Node> nodes, long x, long y) {
        String xStr = StringUtils.leftPad(Long.toBinaryString(x), 50, '0');
        String yStr = StringUtils.leftPad(Long.toBinaryString(y), 50, '0');
        for(int i=0; i<50; i++) {
            nodes.get("x"+StringUtils.leftPad(""+i, 2, '0')).setInput1(""+(xStr.charAt(49-i)=='1'?1:0));
            nodes.get("y"+StringUtils.leftPad(""+i, 2, '0')).setInput1(""+(yStr.charAt(49-i)=='1'?1:0));
        }

    }
    private boolean swap(String n1, String n2, HashMap<String, Node> nodes) {
        if(!nodes.containsKey(n1)) return false;
        if(!nodes.containsKey(n2)) return false;


        var node1 = nodes.get(n1);
        var node2 = nodes.get(n2);
        node1.setOutput(n2);
        node2.setOutput(n1);
        nodes.put(n1, node2);
        nodes.put(n2, node1);
        return true;
    }

    private List<String> getPossibleNodes(int bitPos, HashMap<String, List<String>> parents, HashSet<String> used) {
        var possibleNodes = new ArrayList<>(findParentOfZNodes(bitPos, parents));
        possibleNodes.removeAll(used);
        return possibleNodes;
    }

    private void rerunInput(HashMap<String, List<String>> parents, HashMap<String, Integer> values, List<String> input) {
        parents.clear();
        values.clear();
        ArrayList<String> reRun = new ArrayList<>();
        for (var line : input) {
            if(line.contains(":")) {
                String[] split = line.split(": ");
                values.put(split[0], Integer.parseInt(split[1]));
            }
            if(line.contains("->")) {
                parents.put(line.split(" ")[4], Arrays.asList(line.split(" ")[0], line.split(" ")[2]));
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
        String xySum=Long.toBinaryString(getSumForPrefix("x", values) + getSumForPrefix("y", values));
        String zSum=Long.toBinaryString(getSumForPrefix("z", values));
        xySum = StringUtils.leftPad(xySum, 50, '0');
        zSum = StringUtils.leftPad(zSum, 50, '0');
        System.out.println(xySum);
        System.out.println(zSum);
        for(int i=zSum.length()-1; i>=0; i--) {
            if(xySum.charAt(i)!=zSum.charAt(i)) {
                System.out.println("First diff at: "+(zSum.length()-i));
                break;
            }
        }

    }

    private boolean runGates(HashMap<String, List<String>> input, long x, long y) {
        HashMap<String, Integer> values = new HashMap<>();
        String xStr = StringUtils.leftPad(Long.toBinaryString(x), 50, '0');
        String yStr = StringUtils.leftPad(Long.toBinaryString(y), 50, '0');
        for(int i=0; i<50; i++) {
            values.put("x"+StringUtils.leftPad(""+i, 2, '0'), xStr.charAt(49-i)=='1'?1:0);
            values.put("y"+StringUtils.leftPad(""+i, 2, '0'), yStr.charAt(49-i)=='1'?1:0);
        }


        ArrayList<String> reRun = new ArrayList<>();
//        for (var line : input) {
//            if(line.contains("->")) {
//                if(!doOp(line.split(" "), values)) {
//                    reRun.add(line);
//                }
//            }
//        }
        while(!reRun.isEmpty()) {
            ArrayList<String> doReRun = new ArrayList<>(reRun);
            reRun.clear();
            for(String line : doReRun) {
                if(!doOp(line.split(" "), values)) {
                    reRun.add(line);
                }
            }
        }
        String xySum=Long.toBinaryString(getSumForPrefix("x", values) + getSumForPrefix("y", values));
        String zSum=Long.toBinaryString(getSumForPrefix("z", values));
        xySum = StringUtils.leftPad(xySum, 50, '0');
        zSum = StringUtils.leftPad(zSum, 50, '0');
        for(int i=zSum.length()-1; i>=0; i--) {
            if(xySum.charAt(i)!=zSum.charAt(i)) {
                System.out.println("First diff at: "+(zSum.length()-i));
                break;
            }
        }
        return xySum.equals(zSum);

    }

    List<String> getSwapped(String node1, String node2, List<String> input) {
        String delim = " -> ";
        int index1=-1, index2=-1;
        System.out.println("Swapping "+node1+" to "+node2);
        for(int i =0; i<input.size(); i++) {
            if (input.get(i).endsWith(delim+node1)) { index1=i; }
            if (input.get(i).endsWith(delim+node2)) { index2=i; }
        }
        if(index1==-1 || index2==-1) {
            System.out.println("Cant swap, static value");
            return input;
        }

        String[] parts1 = input.get(index1).split(delim);
        String[] parts2 = input.get(index2).split(delim);


        if(parts2[0].contains(node1) || parts1[0].contains(node2)) {
            System.out.println("Cant swap, self dependency");
            return input;
        }
        ArrayList<String> swapped = new ArrayList<>(input);
        swapped.set(index1, parts1[0] + delim + node2);
        swapped.set(index2, parts2[0] + delim + node1);
        return swapped;
    }

    private HashSet<String> findParentOfZNodes(int nodeNumber, HashMap<String, List<String>> parents) {
        HashSet<String> allParents=new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.addAll(parents.getOrDefault("z"+StringUtils.leftPad(""+nodeNumber, 2, '0'), new ArrayList<>()));
        while(!queue.isEmpty()) {
            String parent = queue.poll();
            allParents.add(parent);
            if(parents.containsKey(parent))
                queue.addAll(parents.get(parent));
        }
        return allParents;
    }

    private HashSet<String> findParentOfZNodes(int nodeNumber, HashMap<String, List<String>> parents, HashMap<Integer, HashSet<String>> usedIn) {
        HashSet<String> allParents=new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.addAll(parents.getOrDefault("z"+StringUtils.leftPad(""+nodeNumber, 2, '0'), new ArrayList<>()));
        while(!queue.isEmpty()) {
            String parent = queue.poll();
            allParents.add(parent);
            if(parents.containsKey(parent))
                queue.addAll(parents.get(parent));
        }
        usedIn.put(nodeNumber, allParents);
        return allParents;
    }
    private Long getSumForPrefix(String prefix, HashMap<String, Integer> values) {
        List<String> vals = new ArrayList<>();
        for(String key : values.keySet())
            if(key.startsWith(prefix)) vals.add(key);

        Collections.sort(vals);
        Collections.reverse(vals);
        Long result=0L;
        for(String z : vals) {
            result=result*2 + values.get(z);
        }
        return result;

    }
}
