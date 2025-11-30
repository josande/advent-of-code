package year2022.day19;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day19 {

    @Data
    @AllArgsConstructor
    static class State {
        int round;
        int ore;
        int clay;
        int obsidian;
        int geode;

        int oreBot;
        int clayBot;
        int obsidianBot;
        int geodeBot;


        List<State> getOutcomes(Blueprint blueprint) {
            List<State> outcomes = new ArrayList<>();

            int highestOreCost =  blueprint.oreBotOreCost;
            highestOreCost = Math.max(highestOreCost, blueprint.clayBotOreCost);
            highestOreCost = Math.max(highestOreCost, blueprint.obsidianBotOreCost);
            highestOreCost = Math.max(highestOreCost, blueprint.geodeBotOreCost);

            if(ore >= blueprint.geodeBotOreCost && obsidian >= blueprint.geodeBotObsidianCost ) {
                State state = new State(round, ore-blueprint.geodeBotOreCost, clay, obsidian-blueprint.geodeBotObsidianCost, geode, oreBot, clayBot, obsidianBot, geodeBot+1);
                state.endOfTurn();
                state.geode--;
                outcomes.add(state);
            } else {
                {
                    State state = new State(round, ore, clay, obsidian, geode, oreBot, clayBot, obsidianBot, geodeBot);
                    state.endOfTurn();
                    outcomes.add(state);
                }
                if (ore >= blueprint.oreBotOreCost
                        && oreBot < highestOreCost) {
                    State state = new State(round, ore - blueprint.oreBotOreCost, clay, obsidian, geode, oreBot + 1, clayBot, obsidianBot, geodeBot);
                    state.endOfTurn();
                    state.ore--;
                    outcomes.add(state);
                }

                if (ore >= blueprint.clayBotOreCost
                        && clayBot < blueprint.obsidianBotClayCost) {
                    State state = new State(round, ore - blueprint.clayBotOreCost, clay, obsidian, geode, oreBot, clayBot + 1, obsidianBot, geodeBot);
                    state.endOfTurn();
                    state.clay--;
                    outcomes.add(state);
                }

                if (ore >= blueprint.obsidianBotOreCost
                        && clay >= blueprint.obsidianBotClayCost
                        && obsidianBot < blueprint.geodeBotObsidianCost) {
                    State state = new State(round, ore - blueprint.obsidianBotOreCost, clay - blueprint.obsidianBotClayCost, obsidian, geode, oreBot, clayBot, obsidianBot + 1, geodeBot);
                    state.endOfTurn();
                    state.obsidian--;
                    outcomes.add(state);
                }
            }
            return outcomes;
        }

        private void endOfTurn() {
            ore+=oreBot;
            clay+=clayBot;
            obsidian+=obsidianBot;
            geode+=geodeBot;

            round++;
        }
    }

    @Data
    @AllArgsConstructor
    private static class Blueprint {
        int blueprintId;

        int oreBotOreCost;

        int clayBotOreCost;

        int obsidianBotOreCost;
        int obsidianBotClayCost;

        int geodeBotOreCost;
        int geodeBotObsidianCost;

        public Blueprint(String value) {
            String[] parts = value.split(" ");
            blueprintId = Integer.parseInt(parts[1].replace(":", ""));
            oreBotOreCost = Integer.parseInt(parts[6]);
            clayBotOreCost = Integer.parseInt(parts[12]);
            obsidianBotOreCost = Integer.parseInt(parts[18]);
            obsidianBotClayCost = Integer.parseInt(parts[21]);
            geodeBotOreCost = Integer.parseInt(parts[27]);
            geodeBotObsidianCost = Integer.parseInt(parts[30]);
        }
    }

    static Object solveA(List<String> values) {

        int result = 0;
        int numberOfTurns = 24;
        for(String value : values) {
            Blueprint blueprint = new Blueprint(value);
            State startState = new State(0,0,0,0,0,1,0,0,0);
            int bestScore = 0;
            HashMap<Integer, Integer> bestRounds = new HashMap<>();
            final Set<State> visited = new HashSet<>();
            Stack<State> queue = new Stack<>();
            queue.add(startState);
            while(!queue.isEmpty()) {
               State current = queue.pop();


               int bestThisRound = bestRounds.getOrDefault(current.getRound(), 0);

                if(current.getGeode() < bestThisRound) {
                    continue;
                }

                if(!visited.add(current))
                    continue;

                bestRounds.put(current.getRound(), current.getGeode());

                int turnsLeft = numberOfTurns - current.getRound();
                int theoreticalBest =
                        current.geode
                        + current.geodeBot * turnsLeft
                        + turnsLeft*turnsLeft / 2;



                if(theoreticalBest < bestScore)
                    continue;

                if(current.getRound()==24) {
                    bestScore = Math.max(bestScore, current.getGeode());
                } else {
                    queue.addAll(current.getOutcomes(blueprint));
                }
            }
            result+=blueprint.getBlueprintId() * bestScore;
        }
        return result;
    }

    static Object solveB(List<String> values) {
        int result=1;
        int timeLimit=32;
        for(int i=0; i<Math.min(values.size(), 3); i++) {

            Blueprint blueprint = new Blueprint(values.get(i));
            State startState = new State(0,0,0,0,0,1,0,0,0);

            int bestScore=0;
            final Set<State> visited = new HashSet<>();
            Stack<State> queue = new Stack<>();
            queue.add(startState);
            while(!queue.isEmpty()) {
                State current = queue.pop();
                if(visited.contains(current))
                    continue;
                visited.add(current);

                if(bestScore > getMaxGeodeForState(current,timeLimit))
                    continue;

                if(current.getRound()==timeLimit) {
                    bestScore = Math.max(bestScore, current.getGeode());
                } else {
                    queue.addAll(current.getOutcomes(blueprint));
                }
            }
            result*=bestScore;
        }
        return result;
    }

    private static int getMaxGeodeForState(State current, int timeLimit) {
        int maxScore=current.geode;
        int timeLeft=timeLimit-current.getRound();
        maxScore+=current.geodeBot*timeLeft;

        maxScore += (timeLeft*timeLeft)/2;
        return maxScore;
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 1766
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 30780
    }


}
