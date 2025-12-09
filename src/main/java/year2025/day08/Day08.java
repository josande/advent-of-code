package year2025.day08;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.Point;
import utils.Reporter;

import java.util.*;

public class Day08 implements AdventOfCode {
    public static void main() {
        Reporter.report(new Day08());
    }

    public Object solveA(List<String> input, int connections) {
        List<Point> points = new java.util.ArrayList<>();
        HashMap<Long, Pair<Point, Point>> distances = new HashMap<>();
        for(var line : input) {
            Point point = new Point(line);
            for(var point2 : points) {
                var pairing = new ImmutablePair<>(point, point2);
                distances.put(pairing.getLeft().getLineDistanceSquared(pairing.getRight()), pairing);
            }
            points.add(point);
        }

        HashMap<UUID, HashSet<Point>> circuits = new HashMap<>();
        var list = distances.entrySet().stream()
                .sorted(Comparator.comparingLong(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .limit(connections)
                .toList();

        for(var pair : list) {
            UUID clusterA = null, clusterB = null;
            for(var cluster : circuits.entrySet()) {
                if(cluster.getValue().contains(pair.getLeft()))  clusterA = cluster.getKey();
                if(cluster.getValue().contains(pair.getRight())) clusterB = cluster.getKey();
            }
            if(clusterA != null) {

                if(clusterB != null) {
                    if(clusterA.equals(clusterB)) continue;
                    var values = circuits.get(clusterA);
                    values.addAll(circuits.get(clusterB));
                    circuits.put(clusterA, values);
                    circuits.remove(clusterB);
                } else { circuits.get(clusterA).add(pair.getRight()); }
            } else {
                if(clusterB != null) { circuits.get(clusterB).add(pair.getLeft()); }
                else {
                    HashSet<Point> newCluster = new HashSet<>();
                    newCluster.add(pair.getLeft());
                    newCluster.add(pair.getRight());
                    circuits.put(UUID.randomUUID(), newCluster);
                }
            }
        }

        return circuits.values().stream().map(HashSet::size)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(s->s)
                .reduce(1, (a, b)->a*b);
    }
    @Override
    public Object solveA(List<String> input) {
        return solveA(input, 1000);
    }

    @Override
    public Object solveB(List<String> input) {
        List<Point> points = new java.util.ArrayList<>();
        SortedMap<Long, Pair<Point, Point>> distances = new TreeMap<>();
        for(var line : input) {
            Point point = new Point(line);
            for(var point2 : points) {
                var pairing = new ImmutablePair<>(point, point2);
                distances.put(pairing.getLeft().getLineDistanceSquared(pairing.getRight()), pairing);
            }
            points.add(point);
        }

        HashMap<UUID, HashSet<Point>> circuits = new HashMap<>();
        for(var pair : distances.values()) {
            UUID clusterA = null, clusterB = null;
            for(var cluster : circuits.entrySet()) {
                if(cluster.getValue().contains(pair.getLeft()))  clusterA = cluster.getKey();
                if(cluster.getValue().contains(pair.getRight())) clusterB = cluster.getKey();
                if(clusterA != null && clusterB != null) break;
            }
            if(clusterA != null) {
                if(clusterA.equals(clusterB)) continue;

                if(clusterB != null) {
                    var values = circuits.get(clusterA);
                    values.addAll(circuits.get(clusterB));
                    circuits.put(clusterA, values);
                    circuits.remove(clusterB);
                } else {
                    var values = circuits.get(clusterA);
                    values.add(pair.getRight());
                    circuits.put(clusterA, values);
                }
            } else {
                if(clusterB != null) {
                    var values = circuits.get(clusterB);
                    values.add(pair.getLeft());
                    circuits.put(clusterB, values);
                } else {
                    HashSet<Point> newCluster = new HashSet<>();
                    newCluster.add(pair.getLeft());
                    newCluster.add(pair.getRight());
                    circuits.put(UUID.randomUUID(), newCluster);
                }
            }
            if(circuits.size()==1 && circuits.values().iterator().next().size()==input.size()) {
                return (long) pair.getRight().getX() * pair.getLeft().getX();
            }
        }
        return null;
    }
}
