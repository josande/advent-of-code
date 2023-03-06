package year2016.day24;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day24 {

    @Data
    @AllArgsConstructor
    public static class Location {

        Integer id;
        Point location;
        HashMap<Integer, Integer> distances;
    }
    static Object solveA(List<String> values) {
        HashMap<Point,Character> map = MapUtil.asMap(values);

        HashMap<Pair<Integer, Integer>, Integer> distances =getDistances(0, map);
        int nodes = distances.size();

        for(int i=1; i<nodes; i++) {
            distances.putAll(getDistances(i, map));
        }
        int bestSoFar = Integer.MAX_VALUE;

        Queue<Pair<ArrayList<Integer>, Integer>> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        queue.add(new ImmutablePair<>(list, 0));
        while(!queue.isEmpty()) {
            Pair<ArrayList<Integer>, Integer> state = queue.poll();
            if(state.getValue()>=bestSoFar) continue;
            if(state.getKey().size() == nodes) {
                bestSoFar = state.getValue();
            }
            int current = state.getKey().get(state.getKey().size()-1);
            for (int next = 0; next<nodes; next++) {
                if(state.getKey().contains(next)) continue;
                ArrayList<Integer> newList = new ArrayList<>(state.getKey());
                newList.add(next);
                queue.add(new ImmutablePair<>(newList, state.getValue()+distances.get(new ImmutablePair<>(current, next))));
            }
        }
        return bestSoFar;
    }
    static Point getPositionOf(Integer value, HashMap<Point,Character> map) {
        return map.entrySet().stream().filter(e -> (""+e.getValue()).equals(""+value)).findAny().map(Map.Entry::getKey).orElseThrow(IllegalArgumentException::new);
    }

    static HashMap<Pair<Integer, Integer>, Integer> getDistances(int idFrom, HashMap<Point,Character> map) {
        Point start = getPositionOf(idFrom, map);
        HashMap<Pair<Integer, Integer>, Integer> distances = new HashMap<>();
        Queue<Pair<Point,Integer>> queue = new LinkedList<>();
        queue.add(new ImmutablePair<>(start, 0));
        HashMap<Point, Integer> visited = new HashMap<>();

        while(!queue.isEmpty()) {
            Pair<Point, Integer> state = queue.poll();
            Point point = state.getKey();
            int steps = state.getValue();

            if(visited.containsKey(point) && visited.get(point) <= steps) continue;

            visited.put(point, steps);

            if(map.get(point) != '.') {
                int idTo = Integer.parseInt(""+map.get(point));
                distances.put(new ImmutablePair<>(idFrom, idTo), steps);
            }

            for(Point p : point.getOrthogonalNeighbours2d()) {
                if(map.containsKey(p) && !(map.get(p) == '#')) {
                    queue.add(new ImmutablePair<>(p, steps+1));
                }
            }
        }
        return distances;
    }

    static Object solveB(List<String> values) {
        HashMap<Point,Character> map = MapUtil.asMap(values);

        HashMap<Pair<Integer, Integer>, Integer> distances =getDistances(0, map);
        int nodes = distances.size();

        for(int i=1; i<nodes; i++) {
            distances.putAll(getDistances(i, map));
        }
        int bestSoFar = Integer.MAX_VALUE;

        Queue<Pair<ArrayList<Integer>, Integer>> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        queue.add(new ImmutablePair<>(list, 0));
        while(!queue.isEmpty()) {
            Pair<ArrayList<Integer>, Integer> state = queue.poll();
            if(state.getValue()>=bestSoFar) continue;
            int current = state.getKey().get(state.getKey().size()-1);
            if(state.getKey().size() == nodes) {
                bestSoFar = Math.min(bestSoFar, state.getValue()+distances.get(new ImmutablePair<>(current, 0)));
            }
            for (int next = 0; next<nodes; next++) {
                if(state.getKey().contains(next)) continue;
                ArrayList<Integer> newList = new ArrayList<>(state.getKey());
                newList.add(next);
                queue.add(new ImmutablePair<>(newList, state.getValue()+distances.get(new ImmutablePair<>(current, next))));
            }
        }
        return bestSoFar;
    }


    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 490
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 744
    }
}
