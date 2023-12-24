package year2023.day21;

import utils.*;

import java.util.*;

public class Day21 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day21());
    }

    @Override
    public Object solveA(List<String> input) {
        return solveA(input, 64);
    }
    public long solveA(List<String> input, int steps) {
        var tempMap = MapUtil.asMap(input);

        Point point = tempMap.entrySet().stream().filter(e -> e.getValue()=='S').findFirst().map(Map.Entry::getKey).orElseThrow();

        HashMap<Point, Integer> visited = new HashMap<>();
        List<Point> walls = tempMap.entrySet().stream().filter(e -> e.getValue()=='#').map(Map.Entry::getKey).toList();
        for (Point p : walls) {
            visited.put(p, 0);
        }

        Queue<State> queue = new LinkedList<>();
        State start = new State(point, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if(current.steps == steps) continue;
            for (Point next : current.point.getOrthogonalNeighbours2d()) {
                if(!visited.containsKey(next))  {
                    visited.put(next, current.steps+1);
                    queue.add(new State(next, current.steps+1));
                }
            }
        }
        return visited.keySet().stream().filter(p -> !walls.contains(p)).filter(p -> p.getManhattanDistance(point) %2 == 0).count();


    }
    record State(Point point, Integer steps){}

    @Override
    public Object solveB(List<String> input) {
        return solveB(input, 26501365);
    }
    public long solveB(List<String> input, int stepLimit) {

        int mazeSize = input.get(0).length();
        var tempMap = MapUtil.asMap(input);

        Point startPoint = new Point(input.get(0).length()/2, input.get(0).length()/2);

        int startX = startPoint.getX();

        long[] firstValues = new long[3];
        for(int i=0;i<firstValues.length;i++) {
            int iterationSteps = mazeSize/2 + mazeSize * 2 * i;

            HashSet<Point> visited = new HashSet<>();
            Queue<State> queue = new LinkedList<>();
            State start = new State(startPoint, 0);
            queue.add(start);

            while (!queue.isEmpty()) {
                State current = queue.poll();
                if(current.steps == iterationSteps)
                    continue;
                for (Point p1 : current.point.getOrthogonalNeighbours2d()) {
                    if(tempMap.get(getRelativePoint(p1, mazeSize)) == '#') continue;
                    if(!visited.contains(p1))  {
                        visited.add(p1);
                        queue.add(new State(p1, current.steps+1));
                    }
                }
            }
            firstValues[i] = visited.stream().filter(p -> p.getManhattanDistance(startPoint)%2 == stepLimit%2).count();
        }

        // y = 2a*x*x/2 + 2b*x/2 + c
        long c = firstValues[0];
        long a = (firstValues[2]-c - 2*(firstValues[1]-c))/2;
        long b = (firstValues[1] - a - c);

        long iterations = (stepLimit-startX)/(mazeSize * 2L);

        return a*iterations*iterations + b * iterations + c;
    }
    Point getRelativePoint(Point p, int mazeSize) {
        return getRelativePoint(p.getX(), p.getY(), mazeSize);
    }
    Point getRelativePoint(long x, long y, int mazeSize) {
        int modX=(int) (x % mazeSize + mazeSize) % mazeSize;
        int modY=(int) (y % mazeSize + mazeSize) % mazeSize;
        return new Point(modX, modY);
    }
}
