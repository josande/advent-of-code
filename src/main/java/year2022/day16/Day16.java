package year2022.day16;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
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
    }


    public static void main(){
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

    static Object solveA(List<String> values) {
        HashMap<String, HashMap<String, Integer>> connectionCost = new HashMap<>();
        HashMap<String, Integer> valves = new HashMap<>();
        for (String row : values) {

            String name = row.split(" ")[1];
            int flow = Integer.parseInt(row.split("=")[1].split(";")[0]);
            String[] tunnelsTo = row.split("valve |valves ")[1].split(", ");

            valves.put(name, flow);

            HashMap<String, Integer> connections = new HashMap<>();
            for(String to : tunnelsTo) {
                connections.put(to, 1);
            }
            connectionCost.put(name, connections);
        }

        for(String name : connectionCost.keySet()) {
            fillPaths(name, connectionCost);
        }

        int maxTime=30;

        Queue<NewState> stack = new LinkedList<>();

        HashMap<String, Integer> openAtStart = new HashMap<>();
        for(var e : valves.entrySet()) {
            if(e.getValue()==0) {
                openAtStart.put(e.getKey(),0);
            }
        }
        stack.add(new NewState("AA", openAtStart, connectionCost.keySet(), connectionCost, 0));

        HashMap<Position, Integer> bestScoresMidRun = new HashMap<>();

        while(!stack.isEmpty()) {
            NewState state = stack.poll();
            int score = getScore(state.valvesOpenedAt, valves, maxTime);
            if(score > bestScoresMidRun.getOrDefault(state.asPosition(), -1)) {
                bestScoresMidRun.put(state.asPosition(), score);

                if (state.time < maxTime) {
                    stack.addAll(state.getNextStates());
                }
            }
        }

        return bestScoresMidRun.values().stream().mapToInt(i -> i).max().getAsInt();
    }

    private record Connection(String name, Integer cost) {}
    private static void fillPaths(String name, HashMap<String, HashMap<String, Integer>> connectionCost) {
        HashMap<String, Integer> seen = new HashMap<>();
        Stack<Connection> stack = new Stack<>();
        for(var e : connectionCost.get(name).entrySet()) {
            stack.add(new Connection(e.getKey(), e.getValue()));
        }
        while(!stack.isEmpty()) {
            Connection conn = stack.pop();
            if(seen.containsKey(conn.name) && seen.get(conn.name) < conn.cost)
                continue;
            seen.put(conn.name, conn.cost);
            for(var e : connectionCost.get(conn.name).entrySet()) {
                stack.add(new Connection(e.getKey(), e.getValue()+conn.cost));
            }
        }
        connectionCost.put(name, seen);
    }

    static Object solveB(List<String> values) {
        HashMap<String, HashMap<String, Integer>> connectionCost = new HashMap<>();
        HashMap<String, Integer> valves = new HashMap<>();
        for (String row : values) {

            String name = row.split(" ")[1];
            int flow = Integer.parseInt(row.split("=")[1].split(";")[0]);
            String[] tunnelsTo = row.split("valve |valves ")[1].split(", ");

            valves.put(name, flow);

            HashMap<String, Integer> connections = new HashMap<>();
            for(String to : tunnelsTo) {
                connections.put(to, 1);
            }
            connectionCost.put(name, connections);
        }

        for(String name : connectionCost.keySet()) {
            fillPaths(name, connectionCost);
        }

        int maxTime=26;
        Queue<NewState> stack = new LinkedList<>();

        HashSet<HashSet<String>> allValveOptions = new HashSet<>();

        HashMap<String, Integer> temp = new HashMap<>();
        for(var valve : valves.entrySet()) {
            if(valve.getValue()>0)
                temp.put(valve.getKey(), valve.getValue());
        }
        valves = temp;
        ArrayList<String> selectableValves = new ArrayList<>(valves.keySet());

        for(int i=0; i<Math.pow(2, valves.keySet().size()); i++) {
            HashSet<String> selection = new HashSet<>();
            String asBinary = StringUtils.leftPad(Integer.toBinaryString(i), valves.keySet().size() , '0');

            for(int pos=0; pos<valves.keySet().size(); pos++) {
                if(asBinary.charAt(pos)=='1') {
                    selection.add(selectableValves.get(pos));
                }
            }
            allValveOptions.add(selection);
        }

        int totalScore=0;
        ArrayList<HashSet<String>> valvesOptions = new ArrayList<>(allValveOptions);

        for(int i=0; i<(valvesOptions.size()+1)/2; i++) {
            var selection = valvesOptions.get(i);
            int humanScore=0;
            int elephantScore=0;
            {
                stack.add(new NewState("AA", new HashMap<>(), selection, connectionCost, 0));

                while (!stack.isEmpty()) {
                    NewState state = stack.poll();
                    int score = getScore(state.valvesOpenedAt, valves, maxTime);
                    elephantScore= Math.max(elephantScore, score);

                    if (state.time < maxTime) {
                        stack.addAll(state.getNextStates());
                    }
                }
            }
            {
                HashSet<String> remaining = new HashSet<>(valves.keySet());
                remaining.removeAll(selection);
                stack.add(new NewState("AA", new HashMap<>(), remaining, connectionCost, 0));

                while (!stack.isEmpty()) {
                    NewState state = stack.poll();
                    int score = getScore(state.valvesOpenedAt, valves, maxTime);
                    humanScore= Math.max(humanScore, score);

                    if (state.time < maxTime) {
                        stack.addAll(state.getNextStates());
                    }
                }
            }
            totalScore = Math.max(totalScore, humanScore+elephantScore);
        }

        return totalScore;
    }


    private static int getScore(HashMap<String, Integer> valvesOpenedAt, HashMap<String, Integer> valves, int maxTime) {
        int score=0;
        for(var e : valvesOpenedAt.entrySet()) {
            score += Math.max(0, maxTime - e.getValue() ) * valves.get(e.getKey());
        }
        return score;
    }
    private record Position(HashSet<String> positions, HashSet<String> openValves){}

    private record NewState(String pos,
                            HashMap<String, Integer> valvesOpenedAt,
                            Set<String> availableValves,
                            HashMap<String, HashMap<String, Integer>> connectionCosts,
                            int time){
        Position asPosition() {
            HashSet<String> placements = new HashSet<>();
            if(pos != null) placements.add(pos);
            return new Position(placements, new HashSet<>(valvesOpenedAt.keySet()));
        }
        List<NewState> getNextStates() {
            ArrayList<NewState> nextStates = new ArrayList<>();
            if(availableValves.contains(pos) && !valvesOpenedAt.containsKey(pos)) {
                var newSet = new HashMap<>(valvesOpenedAt);
                newSet.put(pos, time+1);
                nextStates.add(new NewState(pos, newSet, availableValves,connectionCosts,  time+1));
                return nextStates;
            }
            for(var newPos : availableValves) {
                if(pos.equals(newPos))
                    continue;
                if(valvesOpenedAt.containsKey(newPos))
                    continue;
                nextStates.add(new NewState(newPos, valvesOpenedAt, availableValves, connectionCosts, time + connectionCosts.get(pos).get(newPos)));
            }
            return nextStates;
        }
    }
}