package year2021.day12;

import lombok.Data;
import lombok.Getter;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.net.CacheRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {

    @Data
    static class Cave {
        private String name;
        private boolean big;
        private HashSet<Cave> connections = new HashSet<>();

        public Cave(String name) {
            this.name=name;
            big = name.equals(name.toUpperCase());
        }

        public void addConnection(Cave cave) {
            connections.add(cave);
        }
        public boolean isStart() {
            return name.equals("start");
        }
        public boolean isEnd() {
            return name.equals("end");
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Cave))
                return false;
            Cave o = (Cave) other;
            return getName().equals(o.getName());
        }

        @Override
        public int hashCode() {
            return getName().hashCode();
        }

    }
    static Object solveA(List<String> values) {
        HashSet<Cave> caves = getCaves(values);
        return getListOfCaves(caves, false).size();
    }

    static Object solveB(List<String> values) {
        HashSet<Cave> caves = getCaves(values);
        return getListOfCaves(caves, true).size();
    }

    private static HashSet<List<Cave>> getListOfCaves(HashSet<Cave> caves, boolean allowRepeatSmallCave) {
        Cave startCave = caves.stream().filter(Cave::isStart).findFirst().get();

        ArrayList<List<Cave>> queue = new ArrayList<>();

        List<Cave> start = Collections.singletonList(startCave);

        queue.add(start);
        HashSet<List<Cave>> allPaths = new HashSet<>();

        while(!queue.isEmpty()) {
            List<Cave> current = queue.get(queue.size()-1);
            queue.remove(current);

            for (Cave c : current.get(current.size()-1).getConnections()) {
                if (c.isBig() || (allowRepeatSmallCave || !current.contains(c))) {

                    if(c.isStart())
                        continue;
                    List<Cave> newList = new ArrayList<>(current);
                    newList.add(c);
                    if(c.isEnd()) {
                        allPaths.add(newList);
                    } else {
                        if(!allowRepeatSmallCave || countDuplicates(newList)<=1)
                            queue.add(newList);
                    }
                }
            }
        }
        return allPaths;
    }

    private static long countDuplicates(List<Cave> newList) {
        Map<String, Long> filtered = newList.stream().filter(c -> !c.isBig()).collect(Collectors.groupingBy(Cave::getName, Collectors.counting()));
        if (filtered.values().stream().anyMatch(v -> v>2)) {
            return 3;
        }
        return filtered.values().stream().filter(v -> v>1L).count();
    }


    private static HashSet<Cave> getCaves(List<String> values) {
        HashSet<Cave> caves = new HashSet<>();
        for(var val : values) {
            Cave c0 = new Cave(val.split("-")[0]);
            Cave c1 = new Cave(val.split("-")[1]);
            caves.add(c0);
            caves.add(c1);
            caves.stream().filter(c -> c.equals(c0)).forEach(c->c.addConnection(caves.stream().filter(c2 -> c2.equals(c1)).findFirst().get()));
            caves.stream().filter(c -> c.equals(c1)).forEach(c->c.addConnection(caves.stream().filter(c2 -> c2.equals(c0)).findFirst().get()));
        }
        return caves;
    }

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //4495
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //131254
    }
}
