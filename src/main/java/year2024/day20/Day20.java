package year2024.day20;

import lombok.Data;
import utils.AdventOfCode;
import utils.Point;
import utils.Reporter;

import java.util.*;

@Data
public class Day20 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day20());
    }
    private int timeLimit = 100;

    @Override
    public Object solveA(List<String> input) {

        HashSet<Point> path =new HashSet<>();

        Point start=null;
        Point end=null;

        for(int y=0; y<input.size(); y++) {
            char[] row = input.get(y).toCharArray();
            for(int x=0; x<input.get(y).length(); x++) {
                char c = row[x];
                if(c=='.') path.add(new Point(x,y));
                if(c=='S') start=new Point(x,y);
                if(c=='E') end=new Point(x,y);
            }
        }

        path.add(start);
        path.add(end);
        HashMap<Point, Integer> stepsToEnd = new HashMap<>();
        Stack<Point> stack = new Stack<>();
        stack.add(end);

        int steps = 0;
        while(!stack.isEmpty()) {
            Point p = stack.pop();
            stepsToEnd.put(p, steps);
            for(Point n: p.getOrthogonalNeighbours2d()) {
                if(path.contains(n) && !stepsToEnd.containsKey(n)) stack.add(n);
            }
            steps++;
        }

        int cheatCoutner=0;
        int cheatTime =2;
        for(var e : stepsToEnd.entrySet()){
            Point from = e.getKey();
            for(Point to : getPointsAtDistance(from, cheatTime)) {
                if(path.contains(to) && stepsToEnd.get(to)<=(e.getValue()-timeLimit-cheatTime)) {
                   cheatCoutner++;
                }
            }
        }

        return cheatCoutner;
    }

    HashSet<Point> getPointsAtDistance(Point point, int distance) {
        HashSet<Point> points = new HashSet<>();
        for(int y= -distance; y<=distance; y++) {
            points.add(new Point(point.getX()+(distance-Math.abs(y)), point.getY()+y));
            points.add(new Point(point.getX()-(distance-Math.abs(y)), point.getY()+y));
        }
        return points;
    }
    @Override
    public Object solveB(List<String> input) {
        HashSet<Point> path = new HashSet<>();

        Point start = null;
        Point end = null;

        for (int y = 0; y < input.size(); y++) {
            char[] row = input.get(y).toCharArray();
            for (int x = 0; x < input.get(y).length(); x++) {
                char c = row[x];
                if (c == '.') path.add(new Point(x, y));
                if (c == 'S') start = new Point(x, y);
                if (c == 'E') end = new Point(x, y);
            }
        }

        path.add(start);
        path.add(end);
        HashMap<Point, Integer> stepsToEnd = new HashMap<>();
        Stack<Point> stack = new Stack<>();
        stack.add(end);

        int steps = 0;
        while (!stack.isEmpty()) {
            Point p = stack.pop();
            stepsToEnd.put(p, steps);
            for (Point n : p.getOrthogonalNeighbours2d()) {
                if (path.contains(n) && !stepsToEnd.containsKey(n)) stack.add(n);
            }
            steps++;
        }

        int cheatCoutner=0;
        for (var e : stepsToEnd.entrySet()) {
            Point from = e.getKey();
            for (int cheatTime = 2; cheatTime <= 20; cheatTime++) {
                for (Point to : getPointsAtDistance(from, cheatTime)) {
                    if(!path.contains(to)) continue;
                    if (stepsToEnd.get(to) <= (e.getValue() - timeLimit - cheatTime)) {
                        cheatCoutner++;
                    }
                }
            }
        }
        return cheatCoutner;
    }
}
