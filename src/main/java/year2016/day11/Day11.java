package year2016.day11;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import utils.AdventOfCode;
import utils.FileHelper;
import utils.Reporter;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day11 implements AdventOfCode {

    @Data
    @NoArgsConstructor
    static class State {
        HashMap<String, Integer> generators = new HashMap<>();
        HashMap<String, Integer> microchips = new HashMap<>();
        private int elevatorFloor = 0;
        private int steps = 0;

        public State(State state) {
            this.generators.putAll(state.getGenerators());
            this.microchips.putAll(state.getMicrochips());
            this.elevatorFloor = state.getElevatorFloor();
            this.steps = state.getSteps() + 1;
        }

        public boolean isLegal() {
            if (elevatorFloor < 0 || elevatorFloor > 3) return false;

            for (Map.Entry<String, Integer> microchip : microchips.entrySet()) {
                if (Objects.equals(generators.get(microchip.getKey()), microchip.getValue())) continue;
                if (generators.containsValue(microchip.getValue())) return false;
            }
            return true;
        }

        public boolean isEndState() {
            for (Integer floor : getMicrochips().values()) {
                if (floor != 3) return false;
            }
            for (Integer floor : getGenerators().values()) {
                if (floor != 3) return false;
            }
            return true;
        }


        public List<State> getChildStates() {
            ArrayList<State> childStates = new ArrayList<>();
            childStates.addAll(getOneMicrochip());
            childStates.addAll(getTwoMicrochips());
            childStates.addAll(getOneGenerator());
            childStates.addAll(getTwoGenerators());
            childStates.addAll(getOneOfEach());

            return childStates;
        }

        private ArrayList<State> getOneMicrochip() {
            ArrayList<State> childStates = new ArrayList<>();
            for (Map.Entry<String, Integer> microchip : microchips.entrySet()) {
                if (microchip.getValue() == elevatorFloor) {
                    State newStateUp = new State(this);
                    newStateUp.setElevatorFloor(elevatorFloor + 1);
                    newStateUp.getMicrochips().put(microchip.getKey(), elevatorFloor + 1);
                    if (newStateUp.isLegal()) {
                        childStates.add(newStateUp);
                    }
                    State newStateDown = new State(this);
                    newStateDown.setElevatorFloor(elevatorFloor - 1);
                    newStateDown.getMicrochips().put(microchip.getKey(), elevatorFloor - 1);
                    if (newStateDown.isLegal()) {
                        childStates.add(newStateDown);
                    }
                }
            }


            return childStates;
        }

        private ArrayList<State> getTwoMicrochips() {

            ArrayList<State> childStates = new ArrayList<>();
            for (Map.Entry<String, Integer> microchip : microchips.entrySet()) {
                if (microchip.getValue() == elevatorFloor) {
                    for (Map.Entry<String, Integer> microchip2 : microchips.entrySet()) {
                        if (microchip.getKey().equals(microchip2.getKey())) continue;
                        if (microchip2.getValue() == elevatorFloor) {
                            State newStateUp = new State(this);
                            newStateUp.setElevatorFloor(elevatorFloor + 1);
                            newStateUp.getMicrochips().put(microchip.getKey(), elevatorFloor + 1);
                            newStateUp.getMicrochips().put(microchip2.getKey(), elevatorFloor + 1);
                            if (newStateUp.isLegal()) {
                                childStates.add(newStateUp);
                            }

                            State newStateDown = new State(this);
                            newStateDown.setElevatorFloor(elevatorFloor - 1);
                            newStateDown.getMicrochips().put(microchip.getKey(), elevatorFloor - 1);
                            newStateDown.getMicrochips().put(microchip2.getKey(), elevatorFloor - 1);
                            if (newStateDown.isLegal()) {
                                childStates.add(newStateDown);
                            }
                        }
                    }
                }
            }
            return childStates;
        }

        private ArrayList<State> getOneGenerator() {
            ArrayList<State> childStates = new ArrayList<>();
            for (Map.Entry<String, Integer> generator : generators.entrySet()) {
                if (generator.getValue() == elevatorFloor) {
                    State newStateUp = new State(this);
                    newStateUp.setElevatorFloor(elevatorFloor + 1);
                    newStateUp.getGenerators().put(generator.getKey(), elevatorFloor + 1);
                    if (newStateUp.isLegal())
                        childStates.add(newStateUp);
                    State newStateDown = new State(this);
                    newStateDown.setElevatorFloor(elevatorFloor - 1);
                    newStateDown.getGenerators().put(generator.getKey(), elevatorFloor - 1);
                    if (newStateDown.isLegal())
                        childStates.add(newStateDown);
                }
            }


            return childStates;
        }

        private ArrayList<State> getTwoGenerators() {

            ArrayList<State> childStates = new ArrayList<>();
            for (Map.Entry<String, Integer> generator : generators.entrySet()) {
                if (generator.getValue() == elevatorFloor) {
                    for (Map.Entry<String, Integer> generator2 : generators.entrySet()) {
                        if (generator.getKey().equals(generator2.getKey())) continue;
                        if (generator2.getValue() == elevatorFloor) {
                            State newStateUp = new State(this);
                            newStateUp.setElevatorFloor(elevatorFloor + 1);
                            newStateUp.getGenerators().put(generator.getKey(), elevatorFloor + 1);
                            newStateUp.getGenerators().put(generator2.getKey(), elevatorFloor + 1);
                            if (newStateUp.isLegal()) {
                                childStates.add(newStateUp);
                            }

                            State newStateDown = new State(this);
                            newStateDown.setElevatorFloor(elevatorFloor - 1);
                            newStateDown.getGenerators().put(generator.getKey(), elevatorFloor - 1);
                            newStateDown.getGenerators().put(generator2.getKey(), elevatorFloor - 1);
                            if (newStateDown.isLegal()) {
                                childStates.add(newStateDown);
                            }
                        }
                    }
                }
            }
            return childStates;
        }
        private ArrayList<State> getOneOfEach() {
            ArrayList<State> childStates = new ArrayList<>();
            for (Map.Entry<String, Integer> microchip : microchips.entrySet()) {
                if (microchip.getValue() == elevatorFloor) {
                    for (Map.Entry<String, Integer> generator : generators.entrySet()) {
                        if (generator.getValue() == elevatorFloor) {
                            State newStateUp = new State(this);
                            newStateUp.setElevatorFloor(elevatorFloor + 1);
                            newStateUp.getMicrochips().put(microchip.getKey(), elevatorFloor + 1);
                            newStateUp.getGenerators().put(generator.getKey(), elevatorFloor + 1);
                            if (newStateUp.isLegal()) {
                                childStates.add(newStateUp);
                            }

                            State newStateDown = new State(this);
                            newStateDown.setElevatorFloor(elevatorFloor - 1);
                            newStateDown.getMicrochips().put(microchip.getKey(), elevatorFloor - 1);
                            newStateDown.getGenerators().put(generator.getKey(), elevatorFloor - 1);
                            if (newStateDown.isLegal()) {
                                childStates.add(newStateDown);
                            }
                        }
                    }
                }
            }
            return childStates;
        }
        private ArrayList<ImmutablePair<Integer, Integer>> asPairs() {
            ArrayList<ImmutablePair<Integer, Integer>> pairs = new ArrayList<>();
            for(String name : generators.keySet()) {
                pairs.add(new ImmutablePair<>(generators.get(name), microchips.get(name)));
            }
            pairs.add(new ImmutablePair<>(elevatorFloor, -1));

            Collections.sort(pairs);
            return pairs;
        }
    }

    @Override
    public Object solveA(List<String> values) {
        State startState = generateStartState(values);

        HashMap<List<ImmutablePair<Integer, Integer>>, Integer> checkedStates = new HashMap<>();

        Queue<State> queue = new LinkedList<>();
        queue.add(startState);

        while (!queue.isEmpty()) {
            State state = queue.poll();

            if(checkedStates.containsKey(state.asPairs()) &&
                    checkedStates.get(state.asPairs()) <= state.getSteps()) {
                continue;
            }
            checkedStates.put(state.asPairs(), state.getSteps());

            if(state.isEndState()) {
                return state.getSteps();
            }
            queue.addAll(state.getChildStates());
        }

        return -1;
    }

    private static State generateStartState(List<String> values) {
        State state = new State();
        int floor = 0;
        for(var val:values) {
            String[] words = val.replaceAll("[,.]", "").split(" ");
            for(int i=1; i<words.length; i++) {
                if(words[i].equalsIgnoreCase("generator")) {
                    state.getGenerators().put(words[i-1], floor);
                }
                if(words[i].equalsIgnoreCase("microchip")) {
                    state.getMicrochips().put(words[i - 1].substring(0, words[i - 1].indexOf("-")), floor);
                }
            }

            floor++;
        }
        return state;
    }


    @Override
    public Object solveB(List<String> values) {
        State startState = generateStartState(values);

        startState.getMicrochips().put("elerium", 0);
        startState.getGenerators().put("elerium", 0);
        startState.getMicrochips().put("dilithium", 0);
        startState.getGenerators().put("dilithium", 0);

        HashMap<List<ImmutablePair<Integer, Integer>>, Integer> checkedStates = new HashMap<>();

        Queue<State> queue = new LinkedList<>();
        queue.add(startState);

        while (!queue.isEmpty()) {
            State state = queue.poll();

            if(checkedStates.containsKey(state.asPairs()) &&
               checkedStates.get(state.asPairs()) <= state.getSteps()) {
                continue;
            }
            checkedStates.put(state.asPairs(), state.getSteps());

            if(state.isEndState()) {
                return state.getSteps();
            }
            queue.addAll(state.getChildStates());
        }

        return -1;
    }


    public static void main(){
        Reporter.report(new Day11());
    }

}
