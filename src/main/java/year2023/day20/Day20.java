package year2023.day20;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day20 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day20());
    }

    static class Module {
        String name;
        boolean isFlipFlop;
        boolean flipFlopOn=false;
        boolean isConjunction;
        HashMap<String, SignalType> conjunctionMemory=new HashMap<>();
        List<String> targets;

        Module(String name, boolean isFlipFlop, boolean isConjunction, List<String> targets) {
            this.name=name;
            this.isFlipFlop=isFlipFlop;
            this.isConjunction=isConjunction;
            this.targets=targets;
        }

        List<Signal> getOutputs(String sender, SignalType input) {
            List<Signal> outputs = new ArrayList<>();
            SignalType signalType = null;
            if(isFlipFlop) {
                if(input == SignalType.LOW) {
                    signalType = flipFlopOn ? SignalType.LOW: SignalType.HIGH;
                    flipFlopOn=!flipFlopOn;
                }
            } else if(isConjunction) {
                conjunctionMemory.put(sender, input);
                boolean allHigh = conjunctionMemory.values().stream().allMatch(val -> val==SignalType.HIGH);
                signalType = allHigh ? SignalType.LOW: SignalType.HIGH;
           } else {
                signalType = SignalType.LOW;
            }

            if(signalType != null) {
                for (String target : targets) {
                    outputs.add(new Signal(name, target, signalType));
                }
            }
            return outputs;
        }
    }
    enum SignalType {
        HIGH, LOW
    }
    record Signal(String sender, String target, SignalType signal) {}

    @Override
    public Object solveA(List<String> input) {
        HashMap<String, Module> modules = new HashMap<>();
        for(var line : input) {
            var parts = line.split(" -> ");
            var name = parts[0];
            var targets = parts[1].split(", ");
            boolean isFlipFlop=false;
            boolean isConjunction=false;
            if(name.startsWith("%")) {
                name=name.substring(1);
                isFlipFlop=true;
            }
            if(name.startsWith("&")) {
                name=name.substring(1);
                isConjunction=true;
            }
            modules.put(name, new Module(name, isFlipFlop, isConjunction, List.of(targets)));
        }
        List<Module> conjunctions = modules.values().stream().filter(m -> m.isConjunction).toList();
        for(Module m : modules.values()) {
            for(String t : m.targets) {
                if(conjunctions.contains(modules.get(t))) {
                    modules.get(t).conjunctionMemory.put(m.name, SignalType.LOW);
                }
            }
        }


        Queue<Signal> queue = new LinkedList<>();
        long lowSignals =0L;
        long highSignals =0L;
        for(int i=0; i<1000; i++) {
            queue.add(new Signal("button", "broadcaster", SignalType.LOW));

            while (!queue.isEmpty()) {
                Signal current = queue.poll();
                if (current.signal == SignalType.LOW) lowSignals++;
                else highSignals++;
                if (modules.containsKey(current.target))
                    queue.addAll(modules.get(current.target).getOutputs(current.sender, current.signal));
            }

        }
        return  lowSignals * highSignals;

    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<String, Module> modules = new HashMap<>();
        for(var line : input) {
            var parts = line.split(" -> ");
            var name = parts[0];
            var targets = parts[1].split(", ");
            boolean isFlipFlop=false;
            boolean isConjunction=false;
            if(name.startsWith("%")) {
                name=name.substring(1);
                isFlipFlop=true;
            }
            if(name.startsWith("&")) {
                name=name.substring(1);
                isConjunction=true;
            }
            modules.put(name, new Module(name, isFlipFlop, isConjunction, List.of(targets)));
        }
        List<Module> conjunctions = modules.values().stream().filter(m -> m.isConjunction).toList();
        for(Module m : modules.values()) {
            for(String t : m.targets) {
                if(conjunctions.contains(modules.get(t))) {
                    modules.get(t).conjunctionMemory.put(m.name, SignalType.LOW);
                }
            }
        }

        Module targetsRx = modules.values().stream().filter(m -> m.targets.contains("rx")).findFirst().orElseThrow();

        Set<String> targetsTargetOfRx = targetsRx.conjunctionMemory.keySet();

        Queue<Signal> queue = new LinkedList<>();

        HashMap<String, Integer> highSignals= new HashMap<>();
        for(int i=1; ; i++) {
            queue.add(new Signal("button", "broadcaster", SignalType.LOW));

            while (!queue.isEmpty()) {
                Signal current = queue.poll();

                if(current.target.equals("rx")) {
                    for(var v : targetsRx.conjunctionMemory.entrySet().stream().filter(s->s.getValue()==SignalType.HIGH).toList()) {
                        if(!highSignals.containsKey(v.getKey())) {
                            highSignals.put(v.getKey(), i);
                            if(highSignals.size()==targetsTargetOfRx.size()) {
                                long prod = 1L;
                                for(int val : highSignals.values()) {
                                    prod*=val;
                                }
                                return prod;
                            }
                        }
                    }
                }

                if (modules.containsKey(current.target))
                    queue.addAll(modules.get(current.target).getOutputs(current.sender, current.signal));
            }

        }
       // return null;
    }
}
