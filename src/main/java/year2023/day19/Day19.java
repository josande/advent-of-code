package year2023.day19;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day19 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day19());
    }

    private record Xmas(int x, int m, int a, int s) {}

    class Gate {
        char letter;
        boolean greaterThan;
        int value;
        String result;
        HashMap<Character, HashSet<Integer>> passing = new HashMap<>();
        HashMap<Character, HashSet<Integer>> filteredOut = new HashMap<>();

        public Gate(char letter, boolean greaterThan, int value, String result) {
            this.letter = letter;
            this.greaterThan = greaterThan;
            this.value = value;
            this.result = result;

            HashSet<Integer> x = new HashSet<>();
            HashSet<Integer> m = new HashSet<>();
            HashSet<Integer> a = new HashSet<>();
            HashSet<Integer> s = new HashSet<>();
            HashSet<Integer> filter = new HashSet<>();
            for(int i=1; i<=4000; i++) {
                x.add(i);
                m.add(i);
                a.add(i);
                s.add(i);
                if(greaterThan) {
                    if (i <= value)
                        filter.add(i);
                } else {
                    if (i >= value)
                        filter.add(i);
                }
            }
            passing.put('x', x);
            passing.put('m', m);
            passing.put('a', a);
            passing.put('s', s);

            passing.get(letter).removeAll(filter);
            filteredOut.put(letter, filter);

        }


        String resolve(int x, int m, int a, int s) {
            return switch (letter) {
                case 'x' -> x > value == greaterThan ? result : null;
                case 'm' -> m > value == greaterThan ? result : null;
                case 'a' -> a > value == greaterThan ? result : null;
                case 's' -> s > value == greaterThan ? result : null;
                default -> throw new IllegalStateException("Unexpected value: " + letter);
            };
        }

        State pass(State state) {
            HashMap<Character, HashSet<Integer>> remaining = new HashMap<>();
            for(char c : List.of('x','m','a','s')) {
                HashSet<Integer> set = new HashSet<>(state.remaining.get(c));
                if(filteredOut.containsKey(c))
                    set.removeAll(filteredOut.get(c));
                remaining.put(c, set);
            }
            return new State(remaining, result);
        }
        State fail(State state) {
            HashMap<Character, HashSet<Integer>> remaining = new HashMap<>();
            for(char c : List.of('x','m','a','s')) {
                HashSet<Integer> set = new HashSet<>(state.remaining.get(c));
                if(filteredOut.containsKey(c))
                    set.retainAll(filteredOut.get(c));
                remaining.put(c, set);
            }
            return new State(remaining, null);
        }
    };
    private class Workflow {
        private String name;
        private String code;
        private ArrayList<Gate> gates = new ArrayList<>();
        Workflow(String name, String code) {
            this.name = name;
            this.code = code;
            setGates(code);
        }
        List<State> getNext(State state) {
            List<State> states = new ArrayList<>();
            State remaining = state;
            for ( Gate gate : gates) {
                State s1 = gate.pass(remaining);
                states.add(s1);
                remaining = gate.fail(remaining);
            }
            return states;
        }

        private void setGates(String code) {
            String[] parts = code.split(",");
            for(int i=0; i<parts.length; i++) {
                if(!parts[i].contains(":")) {
                    Gate gate = new Gate('x', true, -1, parts[i]);
                    gates.add(gate);
                    continue;
                }

                char letter = parts[i].charAt(0);
                char operator = parts[i].charAt(1);
                int value = Integer.parseInt(parts[i].substring(2).split(":")[0]);
                String target = parts[i].split(":")[1];
                Gate gate = new Gate(letter, operator=='>', value, target);
                gates.add(gate);
            }
        }
        public String resolveXmas(Xmas xmas) {
            String codeLeft = code;
            while(!codeLeft.isEmpty()) {
                if(!codeLeft.contains(",")) return codeLeft;

                String[] parts = codeLeft.split(":");
                String ifTrue = parts[1].split(",")[0];
                String ifFalse = codeLeft.substring(codeLeft.indexOf(",")+1);
                char variable = parts[0].charAt(0);
                char operator = parts[0].charAt(1);
                int value = Integer.parseInt(parts[0].substring(2));
                if (resolve(variable, operator, value, xmas)) {
                    return ifTrue;
                }
                codeLeft=ifFalse;
            }
            return "";
        }
        boolean resolve(char variable, char operator, int value, Xmas xmas) {
            return switch (variable) {
                case 'x' ->
                    {yield switch (operator) {
                        case '<' -> (xmas.x < value);
                        case '>' -> (xmas.x > value);
                        default -> throw new IllegalArgumentException();
                    };}
                case 'm' ->
                    {yield switch (operator) {
                        case '<' -> (xmas.m < value);
                        case '>' -> (xmas.m > value);
                        default -> throw new IllegalArgumentException();
                    };}
                case 'a' ->
                    {yield switch (operator) {
                        case '<' -> (xmas.a < value);
                        case '>' -> (xmas.a > value);
                        default -> throw new IllegalArgumentException();
                    };}
                case 's' ->
                    {yield switch (operator) {
                        case '<' -> (xmas.s < value);
                        case '>' -> (xmas.s > value);
                        default -> throw new IllegalArgumentException();
                    };}
                default -> throw new IllegalArgumentException();
            };
        }
    }


    @Override
    public Object solveA(List<String> input) {
        boolean firstPart = true;
        HashMap<String, Workflow> workflows = new HashMap<>();
        ArrayList<Xmas> xmasList = new ArrayList<>();
        for (String line : input) {
            if (line.isEmpty()) {
                firstPart = false;
                continue;
            }
            if (firstPart) {
                String name = line.split("\\{")[0];
                String code = line.split("\\{")[1].split("}")[0];
                workflows.put(name, new Workflow(name, code));
            } else {
                String xmas[] = line.split("\\{")[1].split("}")[0].split(",");
                xmasList.add(new Xmas(
                        Integer.parseInt(xmas[0].split("=")[1]),
                        Integer.parseInt(xmas[1].split("=")[1]),
                        Integer.parseInt(xmas[2].split("=")[1]),
                        Integer.parseInt(xmas[3].split("=")[1])
                ));
            }
        }
        long result = 0L;
        for(Xmas xmas : xmasList) {
            String wf = "in";
            while (!wf.equals("R") && !wf.equals("A")) {
                wf = workflows.get(wf).resolveXmas(xmas);
            }
            if(wf.equals("A")) {
                result+=xmas.x+ xmas.m+ xmas.a+ xmas.s;
            }

        }
        return result;
    }
    record State(HashMap<Character, HashSet<Integer>> remaining, String position){
        public long getResult() {
            return Long.valueOf(remaining.get('x').size()) *
                   Long.valueOf(remaining.get('m').size()) *
                   Long.valueOf(remaining.get('a').size()) *
                   Long.valueOf(remaining.get('s').size());
        }
        List<State> getNext(HashMap<String, Workflow> workflows) {
            Workflow wf = workflows.get(position);
            return wf.getNext(this);
        }
    };

    @Override
    public Object solveB(List<String> input) {
        boolean firstPart = true;
        HashMap<String, Workflow> workflows = new HashMap<>();

        for (String line : input) {
            if (line.isEmpty()) {
                break;
            }
            if (firstPart) {
                String name = line.split("\\{")[0];
                String code = line.split("\\{")[1].split("}")[0];
                workflows.put(name, new Workflow(name, code));
            }
        }

        HashSet<Integer> x = new HashSet<>();
        HashSet<Integer> m = new HashSet<>();
        HashSet<Integer> a = new HashSet<>();
        HashSet<Integer> s = new HashSet<>();
        for(int i=1; i<=4000; i++) {
            x.add(i);
            m.add(i);
            a.add(i);
            s.add(i);
        }
        HashMap<Character, HashSet<Integer>> remaining = new HashMap<>();
        remaining.put('x', x);
        remaining.put('m', m);
        remaining.put('a', a);
        remaining.put('s', s);

        Stack<State> stack = new Stack<>();
        stack.add(new State(remaining, "in"));
        long result = 0L;
        while(!stack.isEmpty()) {
            State current = stack.pop();
            if(current.position.equals("R")) {
                continue;
            }
            if(current.position.equals("A")) {
                result += current.getResult();
                continue;
            }
            stack.addAll(current.getNext(workflows));
        }
        return result;
    }
}
