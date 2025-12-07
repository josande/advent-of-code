package year2025.day07;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.*;

public class Day07 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day07());
    }

    @Override
    public Object solveA(List<String> input) {
        Long result = 0L;
        var map= MapUtil.asMap(input);
        Point start = map.entrySet().stream().filter(e -> e.getValue().equals('S')).findFirst().get().getKey();
        HashSet<Point> current = new HashSet<>();
        HashSet<Point> visited = new HashSet<>();
        current.add(start);
        while(!current.isEmpty()) {
            Point p= current.iterator().next();
            current.remove(p);
            if(visited.contains(p)) continue;
            visited.add(p);
            current.addAll(getnext(p,map));
        }
        for(Point p: visited) {
            if(map.getOrDefault(p, 'z').equals('^')) result++;
        }
        return result;
    }
    List<Point> getnext(Point point, HashMap<Point,Character> map) {
        if(!map.containsKey(point)) return List.of();
        if(map.get(point)=='^') return List.of(point.west(),point.east());
        return List.of(point.south());
    }

    @Override
    public Object solveB(List<String> input) {
        var map= MapUtil.asMap(input);
        Point start = map.entrySet().stream().filter(e -> e.getValue().equals('S')).findFirst().get().getKey();
        HashSet<Point> current = new HashSet<>();
        HashSet<Point> visited = new HashSet<>();
        HashSet<Splitter> splitters = new HashSet<>();
        current.add(start);
        while(!current.isEmpty()) {
            Point p= current.iterator().next();
            current.remove(p);
            if(visited.contains(p)) continue;
            visited.add(p);
            current.addAll(getnext(p,map));
        }

        for(Point p: visited) {
            if(map.getOrDefault(p, '.').equals('^')) splitters.add(new Splitter(p));
        }
        Splitter startSplitter = new Splitter(start);
        splitters.add(startSplitter);

        for(Splitter s: splitters) {
            s.setLeft(splitters);
            s.setRight(splitters);

        }

        return startSplitter.getValue();

    }

    private class Splitter {
        Point point;
        Splitter left;
        Splitter right;
        Long value;
        Splitter(Point point) {
            this.point = point;
        }
        Splitter(Long value) {
            this.value = value;
        }
        Point getPoint() {
            return point;
        }
        Splitter getLeft() {
            return left;
        }
        Splitter getRight() {
            return right;
        }
        void setLeft(HashSet<Splitter> splitters) {
            var foo = splitters.stream().filter(p -> p.getPoint().getX()==point.getX()-1 && p.getPoint().getY()>point.getY()).toList();
            if(foo.isEmpty())  {
                left = new Splitter(1L);
                return;
            }
            var first = foo.getFirst();
            for(var p : foo) {
                if(p.getPoint().getY()<first.getPoint().getY()) {
                    first = p;
                }
            }
            left = first;
        }
        void setRight(HashSet<Splitter> splitters) {
            var foo = splitters.stream().filter(p -> p.getPoint().getX()==point.getX()+1 && p.getPoint().getY()>point.getY()).toList();
            if(foo.isEmpty())  {
                right = new Splitter(1L);
                return;
            }
            var first = foo.getFirst();
            for(var p : foo) {
                if(p.getPoint().getY()<first.getPoint().getY()) {
                    first = p;
                }
            }
            right = first;
        }
        Long getValue() {
            if(value==null) {
                value = getLeft().getValue() + getRight().getValue();
            }
            return value;
        }

    }
}
