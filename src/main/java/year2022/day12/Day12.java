package year2022.day12;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day12 {

    @Data
    @AllArgsConstructor
    static class State {
        ArrayList<Point> points;

        Point getPoint() {
            return points.get(points.size()-1);
        }
    }
    static Object solveA(List<String> values) {
        HashMap<Point, Character> map = MapUtil.asMap(values);

        Point start=null;
        Point end=null;
        for(Map.Entry<Point, Character> e : map.entrySet() ) {
            if(e.getValue() == 'S') {
                start = e.getKey();
            }
            if(e.getValue() == 'E') {
                end = e.getKey();
            }
        }

        map.put(start, 'a');
        map.put(end, 'z');


        Queue<State> queue=new LinkedList<>();
        ArrayList<Point> startList = new ArrayList<>();
        startList.add(start);
        queue.add(new State(startList));
        HashMap<Point, Integer> bestSoFarAtPoint=new HashMap<>();

        while(!queue.isEmpty()) {
            State current = queue.poll();
            if(bestSoFarAtPoint.containsKey(current.getPoint()) && bestSoFarAtPoint.get(current.getPoint())<current.getPoints().size())
                continue;
            for (Point p : current.getPoint().getOrthogonalNeighbours2d()) {
                if(map.containsKey(p) && map.get(p) <= 1 + (map.get(current.getPoint()))) {
                    ArrayList<Point> points = new ArrayList<>(current.getPoints());
                    points.add(p);
                    if(!bestSoFarAtPoint.containsKey(p) || bestSoFarAtPoint.get(p)>points.size()) {
                        queue.add(new State(points));
                        bestSoFarAtPoint.put(p, points.size());                                    queue.add(new State(points));
                    }
                }
            }

        }
        return bestSoFarAtPoint.get(end)-1;
    }

    static Object solveB(List<String> values) {
        HashMap<Point, Character> map = MapUtil.asMap(values);

        Point end=null;
        List<Point> starts = new ArrayList<>();

        for(Map.Entry<Point, Character> e : map.entrySet() ) {
            if(e.getValue() == 'S' || e.getValue() == 'a') {
                starts.add(e.getKey());
            }
            if(e.getValue() == 'E') {
                end = e.getKey();
            }
        }

        map.put(end, 'z');


        Queue<State> queue=new LinkedList<>();
        HashMap<Point, Integer> bestSoFarAtPoint=new HashMap<>();

        for(Point start : starts) {
            map.put(start, 'a');
            ArrayList<Point> startList = new ArrayList<>();
            startList.add(start);
            queue.add(new State(startList));

            bestSoFarAtPoint.put(start, startList.size());

        }



        while(!queue.isEmpty()) {
            State current = queue.poll();
            if(bestSoFarAtPoint.containsKey(current.getPoint()) && bestSoFarAtPoint.get(current.getPoint())<current.getPoints().size())
                continue;
            for (Point p : current.getPoint().getOrthogonalNeighbours2d()) {
            if(map.containsKey(p) && map.get(p) <= 1 + (map.get(current.getPoint()))) {
                    ArrayList<Point> points = new ArrayList<>(current.getPoints());
                    if(!points.contains(p)) {
                        points.add(p);
                        if(!bestSoFarAtPoint.containsKey(p) || bestSoFarAtPoint.get(p)>points.size()) {
                            queue.add(new State(points));
                            bestSoFarAtPoint.put(p, points.size());
                            queue.add(new State(points));
                        }
                    }
                }
            }
        }
        return bestSoFarAtPoint.get(end)-1;
    }
    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //339
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //332
    }
}
