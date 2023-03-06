package year2016.day22;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day22 {

    @Data
    @AllArgsConstructor
    static class Node {
        int size, used;

        public int getAvail() {
            return size-used;
        }
    }

    private static HashMap<Point, Node> asNodes(List<String> values) {
        HashMap<Point, Node> nodes = new HashMap<>();
        for(String val : values) {
            if(!val.startsWith("/dev")) continue;

            String str=val;
            while(str.contains("  ")) str=str.replaceAll("  ", " ");

            String[] words = str.split(" ");
            int size  = Integer.parseInt(words[1].replaceAll("T",""));
            int used  = Integer.parseInt(words[2].replaceAll("T",""));
            int x = Integer.parseInt(words[0].split("-")[1].replaceAll("x",""));
            int y = Integer.parseInt(words[0].split("-")[2].replaceAll("y",""));
            nodes.put(new Point(x,y), new Node(size,used));
        }
        return nodes;
    }
    static Object solveA(List<String> values) {
        HashMap<Point, Node> nodes = asNodes(values);

        int matches = 0;

        for (Map.Entry<Point, Node> nodeA : nodes.entrySet()) {
            if(nodeA.getValue().getUsed() == 0) continue;
            for (Map.Entry<Point, Node> nodeB : nodes.entrySet()) {
                if(nodeA.getKey().equals(nodeB.getKey())) continue;
                if(nodeA.getValue().getUsed()<=nodeB.getValue().getAvail()) {
                    matches++;
                }
            }
        }

        return matches;
    }


    static Object solveB(List<String> values) {
        HashMap<Point, Node> nodes = asNodes(values);
        int maxX = MapUtil.getMaxX(nodes);

        Point initialTarget = new Point(maxX,0);

        HashSet<Point> map = new HashSet<>();

        Point start=null;
        for (Map.Entry<Point, Node> node : nodes.entrySet()) {
            if(node.getValue().getUsed()==0) {
                start=node.getKey();
                map.add(start);
            }
        }
        if(start == null) {
            throw new IllegalStateException("Found no place to start!");
        }
        Queue<Point> mapQueue = new LinkedList<>();
        mapQueue.add(start);

        while (!mapQueue.isEmpty()) {
            Point point = mapQueue.poll();

            for (Point p : point.getOrthogonalNeighbours2d()) {
                if(map.contains(p)) continue;
                if(!nodes.containsKey(p)) continue;
                if(p.getY() > start.getY()) continue;
                if (nodes.get(p).getUsed() < nodes.get(point).getSize()) {
                    map.add(p);
                    mapQueue.add(p);
                }
            }
        }


        HashMap<Pair<Point, Point>, Integer> tested = new HashMap<>();
        Queue<Pair<Pair<Point, Point>, Integer>> queue = new LinkedList<>();
        queue.add(new ImmutablePair<>(new ImmutablePair<>(start, initialTarget), 0));

        int bestSoFar=Integer.MAX_VALUE;
        final Point GOAL = new Point(0,0);

        while(!queue.isEmpty()) {
            Pair<Pair<Point, Point>, Integer> state = queue.poll();
            if(tested.containsKey(state.getKey()) && tested.get(state.getKey())>=state.getValue()){
                continue;
            }
            if(state.getValue()>=bestSoFar) {
                continue;
            }
            tested.put(state.getKey(), state.getValue());
            if(state.getKey().getValue().equals(GOAL)) {
                return state.getValue();
            }
            Point empty = state.getKey().getKey();
            Point target = state.getKey().getValue();

            for(Point p : empty.getOrthogonalNeighbours2d()) {
                if (map.contains(p)) {
                    Pair<Point, Point> pair;
                    if(p.equals(target)) {
                        pair = new ImmutablePair<>(p, empty);
                    } else {
                        pair = new ImmutablePair<>(p, target);
                    }
                    queue.add(new ImmutablePair<>(pair, state.getValue()+1));
                }
            }
        }
        throw new IllegalStateException("Could not reach end state!");
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 888
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //  >165
    }
}
