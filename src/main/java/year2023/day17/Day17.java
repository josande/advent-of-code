package year2023.day17;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.*;

import java.util.*;

public class Day17 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day17());
    }

    @Override
    public Object solveA(List<String> input) {
        var map = MapUtil.asMap(input);

        Point start = new Point(0,0);
        Point end = new Point(MapUtil.getMaxX(map),MapUtil.getMaxY(map));
        PriorityQueue<State> queue = new PriorityQueue<>();

        int bestSoFar= Integer.MAX_VALUE;

        queue.add(new State(start, Direction.EAST, 0));
        queue.add(new State(start, Direction.SOUTH, 0));
        HashMap<Pair<Point, Direction>, Integer> cache = new HashMap<>();
        while(!queue.isEmpty()) {
            State current = queue.poll();

            if(hasSeenBetter(cache, bestSoFar, current))
                continue;
            if(current.position.getManhattanDistance(end) == 0) {
                bestSoFar = current.cost;
                continue;
            }
            cache.put(current.asPair(), current.cost);
            int finalBestSoFar = bestSoFar;
            queue.addAll(getPossibleStates(current, map, 1, 3).stream().filter(s -> !hasSeenBetter(cache, finalBestSoFar, s)).toList());

        }
        return bestSoFar;
    }
    boolean hasSeenBetter(HashMap<Pair<Point, Direction>, Integer> cache, Integer bestSoFar, State state) {
        return bestSoFar<= state.cost || cache.getOrDefault(state.asPair(), bestSoFar)<=state.cost;
    }
    List<State> getPossibleStates(State state, HashMap<Point, Character> map, int minMove, int maxMove) {
        Direction dir1 = state.direction.left();
        Direction dir2 = state.direction.right();
        Point point= state.position;
        int cost1=state.cost, cost2=state.cost;
        Point pos1=point, pos2=point;
        List<State> states = new ArrayList<>();
        for(int i=1; i<=maxMove; i++) {
            pos1=pos1.getNext(dir1);
            if(map.containsKey(pos1)) {
                cost1+=Integer.parseInt(""+map.get(pos1));
                if(i >= minMove)
                    states.add(new State(pos1, dir1, cost1));
            }
            pos2=pos2.getNext(dir2);
            if(map.containsKey(pos2)) {
                cost2+=Integer.parseInt(""+map.get(pos2));
                if(i >= minMove)
                    states.add(new State(pos2, dir2, cost2));
            }
        }
        return states;
    }

    @Override
    public Object solveB(List<String> input) {
        var map = MapUtil.asMap(input);

        Point start = new Point(0,0);
        Point end = new Point(MapUtil.getMaxX(map),MapUtil.getMaxY(map));
        PriorityQueue<State> queue = new PriorityQueue<>();

        int bestSoFar= Integer.MAX_VALUE;

        queue.add(new State(start, Direction.EAST, 0));
        queue.add(new State(start, Direction.SOUTH, 0));
        HashMap<Pair<Point, Direction>, Integer> cache = new HashMap<>();
        while(!queue.isEmpty()) {
            State current = queue.poll();

            if(hasSeenBetter(cache, bestSoFar, current))
                continue;
            if(current.position.equals(end)) {
                bestSoFar = current.cost;
                continue;
            }

            cache.put(current.asPair(), current.cost);
            int finalBestSoFar = bestSoFar;
            queue.addAll(getPossibleStates(current, map, 4, 10).stream().filter(s -> !hasSeenBetter(cache, finalBestSoFar, s)).toList());

        }
        return bestSoFar;
    }

    record State(Point position, Direction direction, int cost) implements Comparable<State>{
        private Pair<Point, Direction> asPair() { return new ImmutablePair<>(position, direction);}

        @Override
        public int compareTo(State other) {
            int posCost = (other.position.getX()+other.position.getY()) - (this.position.getX()+this.position.getY());

            if(posCost != 0) return posCost;
            return this.cost-other.cost;
        }
    }

}
