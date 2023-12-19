package year2023.day19;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day19 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day19());
    }

    private record Xmas(int x, int m, int a, int s) {}

    static class Gate {
        final char letter;
        final boolean greaterThan;
        final int value;
        final String result;
        final HashMap<Character, HashSet<Integer>> passing = new HashMap<>();
        final HashMap<Character, HashSet<Integer>> filteredOut = new HashMap<>();

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
    }
    private static class Workflow {
        private final String code;
        private final ArrayList<Gate> gates = new ArrayList<>();
        Workflow(String code) {
            this.code = code;
            setGates(code);
        }
        List<State> getNext(State state) {
            List<State> states = new ArrayList<>();
            State remaining = state;
            for ( Gate gate : gates) {
                states.add(gate.pass(remaining));
                remaining = gate.fail(remaining);
            }
            return states;
        }

        private void setGates(String code) {
            String[] parts = code.split(",");
            for (String part : parts) {
                if (!part.contains(":")) {
                    Gate gate = new Gate('x', true, -1, part);
                    gates.add(gate);
                    continue;
                }

                char letter = part.charAt(0);
                char operator = part.charAt(1);
                int value = Integer.parseInt(part.substring(2).split(":")[0]);
                String target = part.split(":")[1];
                Gate gate = new Gate(letter, operator == '>', value, target);
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
                case 'x' -> switch (operator) {
                    case '<' -> (xmas.x < value);
                    case '>' -> (xmas.x > value);
                    default -> throw new IllegalArgumentException();
                };
                case 'm' -> switch (operator) {
                    case '<' -> (xmas.m < value);
                    case '>' -> (xmas.m > value);
                    default -> throw new IllegalArgumentException();
                };
                case 'a' -> switch (operator) {
                    case '<' -> (xmas.a < value);
                    case '>' -> (xmas.a > value);
                    default -> throw new IllegalArgumentException();
                };
                case 's' -> switch (operator) {
                    case '<' -> (xmas.s < value);
                    case '>' -> (xmas.s > value);
                    default -> throw new IllegalArgumentException();
                };
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
                workflows.put(name, new Workflow(code));
            } else {
                String[] xmas = line.split("\\{")[1].split("}")[0].split(",");
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
            return (long) remaining.get('x').size() *
                   (long) remaining.get('m').size() *
                   (long) remaining.get('a').size() *
                   (long) remaining.get('s').size();
        }
        List<State> getNext(HashMap<String, Workflow> workflows) {
            Workflow wf = workflows.get(position);
            return wf.getNext(this);
        }
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<String, Workflow> workflows = new HashMap<>();

        for (String line : input) {
            if (line.isEmpty()) {
                break;
            }
            String name = line.split("\\{")[0];
            String code = line.split("\\{")[1].split("}")[0];
            workflows.put(name, new Workflow(code));
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
