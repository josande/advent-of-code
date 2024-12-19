
package year2024.day16;

import utils.*;

import java.util.*;

public class Day16 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day16());
    }

    private record Position(Point point, Direction direction) {}
    private record State(Position position, int score, HashSet<Point> visited) implements Comparable<State> {
        List<State> getNextStates(HashMap<Point, Character> map) {
            ArrayList<State> next = new ArrayList<>();
            if(map.get(position.point.getNext(position.direction.left())) != '#' && !visited.contains(position.point.getNext(position.direction.left())))
               next.add(new State(new Position(position.point, position.direction.left()), score+1000, visited));
            if(map.get(position.point.getNext(position.direction.right())) != '#' && !visited.contains(position.point.getNext(position.direction.right())))
                next.add(new State(new Position(position.point, position.direction.right()), score+1000, visited));
            if(map.get(position.point.getNext(position.direction)) != '#') {
                HashSet<Point> v2 = new HashSet<>(visited);
                v2.add(position.point);
                next.add(new State(new Position(position.point.getNext(position.direction), position.direction), score+1, v2));
            }
            return next;
        }

        @Override
        public int compareTo(State o) {
            return score-o.score;
        }
    }
    @Override
    public Object solveA(List<String> input) {
        var map = MapUtil.asMap(input);
        Point start = map.entrySet().stream().filter(e->e.getValue()=='S').findAny().orElseThrow(IllegalArgumentException::new).getKey();
        Direction startDirection=Direction.EAST;
        Point end = map.entrySet().stream().filter(e->e.getValue()=='E').findAny().orElseThrow(IllegalArgumentException::new).getKey();

        HashMap<Position, Integer> topScores = new HashMap<>();

        var filledMap = fillSillyRooms(map);

        Queue<State> stack = new PriorityQueue<>();
        stack.add(new State(new Position(start, startDirection), 0, new HashSet<>()));
        while(!stack.isEmpty()) {
            State test = stack.poll();
            if(topScores.containsKey(test.position)) {
                if (topScores.get(test.position)<=test.score) {
                    continue;
                }
            }
            Position lookLeft = new Position(test.position.point, test.position.direction.left());
            if(topScores.containsKey(lookLeft) && topScores.get(lookLeft)<test.score-1000)
                continue;
            Position lookRight = new Position(test.position.point, test.position.direction.right());
            if(topScores.containsKey(lookRight) && topScores.get(lookRight)<test.score-1000)
                continue;
            Position opposite = new Position(test.position.point, test.position.direction.reverse());
            if(topScores.containsKey(opposite) && topScores.get(opposite)<test.score-2000)
                continue;
            topScores.put(test.position, test.score);
            if(test.position.point.equals(end))
                return test.score;
            stack.addAll(test.getNextStates(filledMap));
        }
        return null;
    }

    HashMap<Point, Character> fillSillyRooms(HashMap<Point, Character> map) {
        boolean changed = false;
        var newMap = new HashMap<>(map);
        for(var e : newMap.entrySet()) {
            if(e.getValue()!='.') continue;
            int openWays =0;
            for(Point n : e.getKey().getOrthogonalNeighbours2d()) {
                if(map.get(n)!='#') openWays++;
            }
            if(openWays==1) {
                changed=true;
                newMap.put(e.getKey(), '#');
            }
        }
        if(changed)
            return fillSillyRooms(newMap);
        return newMap;
    }

    @Override
    public Object solveB(List<String> input) {
        var map = MapUtil.asMap(input);
        Point start = map.entrySet().stream().filter(e->e.getValue()=='S').findAny().orElseThrow(IllegalArgumentException::new).getKey();
        Direction startDirection=Direction.EAST;
        Point end = map.entrySet().stream().filter(e->e.getValue()=='E').findAny().orElseThrow(IllegalArgumentException::new).getKey();

        HashMap<Position, Integer> topScores = new HashMap<>();

        var filledMap = fillSillyRooms(map);

        Queue<State> stack = new PriorityQueue<>();
        stack.add(new State(new Position(start, startDirection), 0, new HashSet<>()));
        HashSet<Point> bestPaths = new HashSet<>();
        int bestScore = Integer.MAX_VALUE;

        while(!stack.isEmpty()) {
            State test = stack.poll();
            if(topScores.containsKey(test.position) && topScores.get(test.position)<test.score) continue;
            Position lookLeft = new Position(test.position.point, test.position.direction.left());
            if(topScores.containsKey(lookLeft) && topScores.get(lookLeft)<test.score-1000)
                continue;
            Position lookRight = new Position(test.position.point, test.position.direction.right());
            if(topScores.containsKey(lookRight) && topScores.get(lookRight)<test.score-1000)
                continue;
            Position opposite = new Position(test.position.point, test.position.direction.reverse());
            if(topScores.containsKey(opposite) && topScores.get(opposite)<test.score-2000)
                continue;
            topScores.put(test.position, test.score);
            if(test.position.point.equals(end)) {
                if(test.score<bestScore) {
                    bestScore = test.score;
                    bestPaths = new HashSet<>(test.visited);
                }
                else if(test.score==bestScore) {
                    bestPaths.addAll(test.visited);
                }
            }
            stack.addAll(test.getNextStates(filledMap));
        }

        bestPaths.add(start);
        bestPaths.add(end);

        return bestPaths.size();
    }
}
