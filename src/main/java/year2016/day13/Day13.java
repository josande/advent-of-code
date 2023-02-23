package year2016.day13;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day13 {

    @AllArgsConstructor
    @Data
    static class State {
        Point point;
        int steps;

        public Collection<? extends State> getNext(int offset) {
            ArrayList<State> states = new ArrayList<>();
            for (Point p : point.getOrthogonalNeighbours2d()) {
                if(p.getX()<0 || p.getY() < 0) {
                    continue;
                }
                if (!isWall(p, offset)) {
                    states.add(new State(p, steps+1));
                }
            }
            return states;
        }
    }



    static boolean isWall(Point point, int offset) {
        int x = point.getX();
        int y = point.getY();
        int val = x*x + 3*x + 2*x*y + y + y*y;
        val += offset;
        String binary = Integer.toBinaryString(val);
        return binary.replaceAll("0", "").length()%2!=0;
    }
    static long solveA(List<Integer> values) {
        int offset = values.get(0);
        Point target = new Point(31,39);

        return getSteps(offset, target);


    }
    static Integer getSteps(int offset, Point target)  {
        HashMap<Point, Integer> visited = new HashMap<>();
        Point start = new Point(1,1);

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(start, 0));
        int maxStepsToTarget = Integer.MAX_VALUE;

        while(!queue.isEmpty()) {
            State current = queue.poll();
            if (visited.containsKey(current.getPoint()) && visited.get(current.getPoint())<=current.getSteps()) {
                continue;
            }
            if (current.getSteps() >= maxStepsToTarget) {
                continue;
            }
            visited.put(current.getPoint(), current.getSteps());
            if(current.getPoint().equals(target)) {
                maxStepsToTarget = Math.min(current.getSteps(), maxStepsToTarget);
            } else {
                queue.addAll(current.getNext(offset));
            }
        }

        return maxStepsToTarget;
    }

    static long solveB(List<Integer> values) {
        int offset = values.get(0);
        int maxSteps = 50;


        HashSet<Point> visited = new HashSet<>();
        Point start = new Point(1,1);

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(start, 0));

        while(!queue.isEmpty()) {
            State current = queue.poll();
            if (visited.contains(current.getPoint())) {
                continue;
            }
            if (current.getSteps() > maxSteps) {
                continue;
            }
            visited.add(current.getPoint());
            queue.addAll(current.getNext(offset));
        }
        return visited.size();
    }


    public static void main (String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFileAsIntegers("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 90
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 135
    }

}
