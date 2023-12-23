package year2023.day23;

import utils.*;

import java.util.*;

public class Day23 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day23());
    }

    record Node(HashMap<Point, Integer> edges){

    }
    record State(Point point, Direction direction, int cost){
        State nextState(HashMap<Point,Character> map) {
            for(Direction d : Direction.values()) {
                if(d == direction.reverse()) continue;
                Point p = point.getNext(d);
                if(map.getOrDefault(p, '#') != '#')
                    return new State(p, d, cost+1);
            }
            throw new IllegalStateException("No exits from :"+point);
        }
    }
    record TravelState(Point point, List<Point> visited, int cost){

    }



    List<State> getStartStates(Point point, HashMap<Point,Character> map, boolean ignoreSlops ) {
        List<State> states = new ArrayList<>();
        if(ignoreSlops) {
            for (Direction d : Direction.values()) {
                Point p = point.getNext(d);
                if (map.getOrDefault(p, '#') != '#')
                    states.add(new State(p, d, 1));
            }
        }
        else {
            switch (map.get(point) ) {
                case '.' -> {
                    for (Direction d : Direction.values()) {
                        Point p = point.getNext(d);
                        if (map.getOrDefault(p, '#') != '#')
                            states.add(new State(p, d, 1));
                    }
                }
                case '>' -> states.add(new State(point.east(), Direction.EAST, 1));
                case '<' -> states.add(new State(point.west(), Direction.WEST, 1));
                case 'v' -> states.add(new State(point.south(), Direction.SOUTH, 1));
                case '^' -> states.add(new State(point.north(), Direction.NORTH, 1));
                default -> throw new IllegalStateException();
            }
        }
        return states;
    }

    @Override
    public Object solveA(List<String> input) {

        var map = MapUtil.asMap(input);


        int maxX = MapUtil.getMaxX(map);
        int maxY = MapUtil.getMaxY(map);
        List<Character> slopes = List.of('^', '>', 'v','<');
        List<Point> pointsOfInterest = new ArrayList<>();
        Point start = null;
        Point end = null;
        for(int x=0; x<=maxX; x++) {
            Point p1 = new Point(x,0);
            Point p2 = new Point(x,maxY);
            if(map.get(p1) == '.')
                start = p1;
            if(map.get(p2) == '.')
                end = p2;
        }
        if(start == null || end == null) throw new IllegalStateException();
        pointsOfInterest.add(start);
        pointsOfInterest.add(end);

        for(var e : map.entrySet()) {
            if(slopes.contains(e.getValue())) pointsOfInterest.add(e.getKey());
            if(e.getValue()=='.') {
                int paths = e.getKey().getOrthogonalNeighbours2d().stream().filter(p -> map.getOrDefault(p, '#') !='#').toList().size();

                if(paths>2)
                    pointsOfInterest.add(e.getKey());
            }
        }
        HashMap<Point, Node> nodes = new HashMap<>();

        for(Point p : pointsOfInterest) {
            HashMap<Point, Integer> edges = new HashMap<>();
            Queue<State> queue = new LinkedList<>(getStartStates(p, map, false));
            while(!queue.isEmpty()) {
                State current = queue.poll();

                if(current.cost>0 && pointsOfInterest.contains(current.point)) {
                    edges.put(current.point, current.cost);
                }
                else {
                    if(current.point.equals(end))
                        continue;
                    queue.add(current.nextState(map));
                }
             }
            nodes.put(p, new Node(edges));
        }

        int maxDistance = 0;
        Stack<TravelState> queue = new Stack<>();
        TravelState startState = new TravelState(start, new ArrayList<>(), 0);
        queue.add(startState);
        while (!queue.isEmpty()) {
            TravelState current = queue.pop();
            if (current.point.equals(end)) {
                maxDistance = Math.max(current.cost, maxDistance);
                continue;
            }
            current.visited.add(current.point);
            for(var edge : nodes.get(current.point).edges.entrySet()) {
                if(!current.visited.contains(edge.getKey())) {
                    queue.add(new TravelState(edge.getKey(), new ArrayList<>(current.visited), current.cost + edge.getValue()));
                }
            }
        }

        return maxDistance;
    }

    @Override
    public Object solveB(List<String> input) {
        var map = MapUtil.asMap(input);


        int maxX = MapUtil.getMaxX(map);
        int maxY = MapUtil.getMaxY(map);
    //    List<Character> slopes = List.of('^', '>', 'v','<');
        List<Point> pointsOfInterest = new ArrayList<>();
        Point start = null;
        Point end = null;
        for(int x=0; x<=maxX; x++) {
            Point p1 = new Point(x,0);
            Point p2 = new Point(x,maxY);
            if(map.get(p1) == '.')
                start = p1;
            if(map.get(p2) == '.')
                end = p2;
        }
        if(start == null || end == null) throw new IllegalStateException();
        pointsOfInterest.add(start);
        pointsOfInterest.add(end);

        for(var e : map.entrySet()) {
            if(e.getValue() != '#') {
                int paths = e.getKey().getOrthogonalNeighbours2d().stream().filter(p -> map.getOrDefault(p, '#') != '#').toList().size();

                if(paths>2) pointsOfInterest.add(e.getKey());
            }
        }
        HashMap<Point, Node> nodes = new HashMap<>();

        for(Point p : pointsOfInterest) {
            HashMap<Point, Integer> edges = new HashMap<>();
            Queue<State> queue = new LinkedList<>(getStartStates(p, map, true));
            while(!queue.isEmpty()) {
                State current = queue.poll();

                if(current.cost>0 && pointsOfInterest.contains(current.point)) {
                    edges.put(current.point, current.cost);
                }
                else {
                    if(current.point.equals(end))
                        continue;
                    queue.add(current.nextState(map));
                }
            }
            nodes.put(p, new Node(edges));
        }

        int maxDistance = 0;
        Stack<TravelState> queue = new Stack<>();
        TravelState startState = new TravelState(start, new ArrayList<>(), 0);
        queue.add(startState);
        while (!queue.isEmpty()) {
            TravelState current = queue.pop();
            if (current.point.equals(end)) {
                maxDistance = Math.max(current.cost, maxDistance);
                continue;
            }
            current.visited.add(current.point);
            for(var edge : nodes.get(current.point).edges.entrySet()) {
                if(!current.visited.contains(edge.getKey())) {
                    queue.add(new TravelState(edge.getKey(), new ArrayList<>(current.visited), current.cost + edge.getValue()));
                }
            }
        }

        return maxDistance;
    }


}
