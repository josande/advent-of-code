package year2022.day16;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day16 {


    @Data
    static class Valve {
        private String name;
        private int flow;
        private boolean open;

        private HashMap<String, Integer> connectedValves = new HashMap<>();

        public Valve(String inputRow) {
            String[] parts = inputRow.split(" ");
            this.name = parts[1];
            this.flow = Integer.parseInt(parts[4].substring(5, parts[4].length() - 1));
            this.open = false;
        }

        public void setOtherValves(Collection<Valve> otherValves) {
            for(Valve otherValve : otherValves) {
                if(otherValve.getName().equals(this.name)) continue;
                connectedValves.put(otherValve.getName(), Integer.MAX_VALUE);
            }
            calculateCosts();
        }
        public void calculateCosts() {
            Queue<StateA> queue = new ArrayDeque<>();
            queue.add(new StateA(this.getName(), new ArrayList<>()));
            while (connectedValves.containsValue(Integer.MAX_VALUE) && !queue.isEmpty()) {
                StateA state = queue.poll();
                if(connectedValves.containsKey(state.getName())) {
                    connectedValves.put(state.getName(), Math.min(state.visited.size()+1, connectedValves.get(state.getName())));
                }
                ArrayList<String> visitedSoFar=new ArrayList<>(state.getVisited());
                visitedSoFar.add(state.getName());
                for(String s : allConnections.get(state.getName())) {
                    if(visitedSoFar.contains(s)) continue;
                    queue.add(new StateA(s, visitedSoFar));
                }
            }
        }
    }
    @AllArgsConstructor
    @Data
    static class StateA {
        String name;
        ArrayList<String> visited;
    }
    @AllArgsConstructor
    @Data
    static class State {
        int time;
        Valve valve;
        HashMap<Valve, Integer> openedValves;

        public int getScore(int timelimit) {
            int score = 0;
            for (Map.Entry<Valve, Integer> e : openedValves.entrySet()) {
                if (e.getValue() > timelimit) continue;
                int flow = e.getKey().getFlow();
                int timeLeft = timelimit - e.getValue();
                score+=flow*timeLeft;
            }
            return score;
        }
        public void print() {
            int total=0;
            for(int i=0; i<=26; i++) {
                for(Map.Entry<Valve, Integer> s : openedValves.entrySet()) {
                    if(s.getValue().equals(i)) {
                        System.out.println(i+"\tOpen "+s.getKey().getName()+", "+s.getKey().getFlow());
                        total+=(26-i)*s.getKey().getFlow();
                    }
                }
            }
            System.out.println("For a total of "+total);
        }
    }

    static HashMap<String, Valve> allValves = new HashMap<>();
    static HashMap<String, List<String>> allConnections = new HashMap<>();

    static Object solveA(List<String> values) {
        for(String row : values) {
            Valve room = new Valve(row);
            if(room.getFlow()>0) {
                allValves.put(room.getName(), room);
            }
            String nameFrom=row.split(" ")[1];
            ArrayList<String> nameTo=new ArrayList<>();
            if(nameFrom.equals("AA")) {
                allValves.put(room.getName(), room);
            }
            for(int i=9; i<row.split(" ").length; i++) {
                nameTo.add(row.split(" ")[i].replace(",", ""));
            }
            allConnections.put(nameFrom, nameTo);
        }
        Valve start = allValves.get("AA");

        for(Valve v : allValves.values()) {
            v.setOtherValves(allValves.values());
        }

        Stack<State> stack = new Stack<>();
        stack.add(new State(0, start, new HashMap<>()));

        int bestScore=0;
        while(!stack.isEmpty()) {

            State state = stack.pop();
            if(state.getTime()>=30 || state.getOpenedValves().size() == allValves.size())  {
               int score = 0;
                for(Map.Entry<Valve, Integer> e : state.getOpenedValves().entrySet()) {
                    if (e.getValue()>30) continue;
                    int flow = e.getKey().getFlow();
                    int timeLeft=30-e.getValue();
                    score+=flow*timeLeft;
                }

                bestScore=Math.max(score, bestScore);

            } else {
                for(Map.Entry<String, Integer> valve : state.getValve().getConnectedValves().entrySet()) {
                    Valve v = allValves.get(valve.getKey());
                    if(state.getOpenedValves().containsKey(v)) continue;
                    HashMap<Valve, Integer> openedValves =  new HashMap<>(state.getOpenedValves());
                    openedValves.put(v, state.getTime()+valve.getValue());
                    State newState = new State(state.getTime()+valve.getValue(),v, openedValves);
                    stack.add(newState);
                }
            }
        }

        return bestScore;
    }
    static Object solveB(List<String> values) {
        for(String row : values) {
            Valve room = new Valve(row);
            if(room.getFlow()>0) {
                allValves.put(room.getName(), room);
            }
            String nameFrom=row.split(" ")[1];
            ArrayList<String> nameTo=new ArrayList<>();
            if(nameFrom.equals("AA")) {
                allValves.put(room.getName(), room);
            }
            for(int i=9; i<row.split(" ").length; i++) {
                nameTo.add(row.split(" ")[i].replace(",", ""));
            }
            allConnections.put(nameFrom, nameTo);
        }
        Valve start = allValves.get("AA");

        for(Valve v : allValves.values()) {
            v.setOtherValves(allValves.values());
        }
        Stack<State> stack = new Stack<>();
        stack.add(new State(0, start, new HashMap<>()));

        int bestScore=0;
        HashMap<Collection<Valve>, Integer> testedStates = new HashMap<>();

        while(!stack.isEmpty()) {

            State state = stack.pop();
            if(state.getTime()>=26)  {
                boolean hasSeenBetter=false;
                for(Map.Entry<Collection<Valve>, Integer> ee : testedStates.entrySet()) {
                    if (ee.getKey().containsAll(state.getOpenedValves().keySet()) &&
                            ee.getKey().size() == state.getOpenedValves().keySet().size() &&
                            ee.getValue() >= state.getScore(26)) {
                        hasSeenBetter=true;
                        break;
                    }
                }
                if(hasSeenBetter) {
                    continue;
                }
                testedStates.put(state.getOpenedValves().keySet(), state.getScore(26));
                //Elephant time!
                Stack<State> stack2 = new Stack<>();

                HashMap<Valve, Integer> map = new HashMap<>();
                for(Map.Entry<Valve, Integer> e : state.openedValves.entrySet() ) {
                    if (e.getValue() > 26) continue;
                    map.put(e.getKey(), e.getValue());
                }

                stack2.add(new State(0, start, map ));

                while(!stack2.isEmpty()) {
                    State state2 = stack2.pop();
                    if(state2.getTime() >= 26) {
                        int score = 0;
                        for (Map.Entry<Valve, Integer> e : state2.getOpenedValves().entrySet()) {
                            if (e.getValue() > 26) continue;
                            int flow = e.getKey().getFlow();
                            int timeLeft = 26 - e.getValue();
                            score += flow * timeLeft;
                        }
                        bestScore = Math.max(score, bestScore);
                    } else {
                        for(Map.Entry<String, Integer> valve : state2.getValve().getConnectedValves().entrySet()) {
                            Valve v = allValves.get(valve.getKey());
                            if(state2.getOpenedValves().containsKey(v)) continue;
                            if(v.getName().equals(start.getName())) continue;
                            HashMap<Valve, Integer> openedValves = new HashMap<>(state2.getOpenedValves());
                            openedValves.put(v, state2.getTime()+valve.getValue());
                            stack2.add( new State(state2.getTime()+valve.getValue(), v, openedValves));
                        }
                        stack2.add(new State(26, state2.getValve(), state2.getOpenedValves()));
                    }
                }
            } else {
                for(Map.Entry<String, Integer> valve : state.getValve().getConnectedValves().entrySet()) {
                    Valve v = allValves.get(valve.getKey());
                    if(state.getOpenedValves().containsKey(v)) continue;
                    if(v.getName().equals(start.getName())) continue;
                    HashMap<Valve, Integer> openedValves = new HashMap<>(state.getOpenedValves());
                    openedValves.put(v, state.getTime()+valve.getValue());
                    stack.add(new State(state.getTime()+valve.getValue(), v, openedValves));
                }
                stack.add(new State(26, state.getValve(), state.getOpenedValves()));
            }
        }
        return bestScore;
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 1767
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 2528
    }
}
