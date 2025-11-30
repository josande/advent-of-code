package year2022.day24;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day24 {

    @Data
    @AllArgsConstructor
    static class State {
        Point position;
        int round;
    }
    static Object solveA(List<String> values) {

        HashSet<Point> rights = new HashSet<>();
        HashSet<Point> lefts = new HashSet<>();
        HashSet<Point> ups = new HashSet<>();
        HashSet<Point> downs = new HashSet<>();

        for(int y=0; y < values.size()-2; y++) {
            for (int x = 0; x < values.get(y).length()-2; x++) {
                if(values.get(y+1).charAt(x+1) == '>') rights.add(new Point(x,y));
                if(values.get(y+1).charAt(x+1) == '<') lefts.add(new Point(x,y));
                if(values.get(y+1).charAt(x+1) == '^') ups.add(new Point(x,y));
                if(values.get(y+1).charAt(x+1) == 'v') downs.add(new Point(x,y));
            }
        }

        State start = new State(new Point(0,-1), 0);
        int maxX=values.get(0).length()-3;
        int maxY=values.size()-3;

        Point goal = new Point(maxX, maxY+1);


        return solve(start, goal, maxX, maxY, lefts, rights, ups, downs);

    }
    static Object solveB(List<String> values) {
        HashSet<Point> rights = new HashSet<>();
        HashSet<Point> lefts = new HashSet<>();
        HashSet<Point> ups = new HashSet<>();
        HashSet<Point> downs = new HashSet<>();

        for(int y=0; y < values.size()-2; y++) {
            for (int x = 0; x < values.get(y).length()-2; x++) {
                if(values.get(y+1).charAt(x+1) == '>') rights.add(new Point(x,y));
                if(values.get(y+1).charAt(x+1) == '<') lefts.add(new Point(x,y));
                if(values.get(y+1).charAt(x+1) == '^') ups.add(new Point(x,y));
                if(values.get(y+1).charAt(x+1) == 'v') downs.add(new Point(x,y));
            }
        }

        State start1 = new State(new Point(0,-1), 0);
        int maxX=values.get(0).length()-3;
        int maxY=values.size()-3;

        Point goal1 = new Point(maxX, maxY+1);

        int trip1=solve(start1, goal1, maxX, maxY, lefts, rights, ups, downs);

        State start2 = new State(goal1, trip1);
        Point goal2 = start1.getPosition();

        int trip2 = solve(start2, goal2, maxX, maxY, lefts, rights, ups, downs);

        State start3 = new State(start1.getPosition(), trip2);
        Point goal3 = goal1;
        return solve(start3, goal3, maxX, maxY, lefts, rights, ups, downs);
    }

    static int solve(State start, Point goal, int maxX, int maxY, HashSet<Point> lefts, HashSet<Point> rights, HashSet<Point> ups, HashSet<Point> downs) {

        Queue<State> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            State state = queue.poll();

            for(Point p : state.getPosition().getOrthogonalNeighbours2d()) {
                if(p.equals(goal))
                    return state.getRound()+1;
                if(p.getY()<0) continue;
                if(p.getX()<0) continue;
                if(p.getX()>maxX) continue;
                if(p.getY()>maxY) continue;
                State newState = new State(p, state.getRound()+1);
                if(lefts.contains( new Point((1000*(maxX+1) + p.getX() + newState.getRound())%(maxX+1), p.getY()))) { continue; }
                if(rights.contains(new Point((1000*(maxX+1) + p.getX() - newState.getRound())%(maxX+1), p.getY()))) { continue; }
                if(downs.contains( new Point(p.getX(), (1000*(maxY + 1)+ p.getY() - newState.getRound())%(maxY+1)))) { continue; }
                if(ups.contains(   new Point(p.getX(), (1000*(maxY + 1)+ p.getY() + newState.getRound())%(maxY+1)))) { continue; }
                if(!queue.contains(newState)) {
                    queue.add(newState);
                }
            }
            //stand still
            Point p = state.getPosition();
            State newState = new State(p, state.getRound()+1);
            boolean canStay = true;
            if(lefts.contains( new Point((1000*(maxX+1) + p.getX() + newState.getRound())%(maxX+1), p.getY()))) { canStay=false; }
            if(rights.contains(new Point((1000*(maxX+1) + p.getX() - newState.getRound())%(maxX+1), p.getY()))) { canStay=false; }
            if(downs.contains( new Point(p.getX(), (1000*(maxY+1) + p.getY() - newState.getRound())%(maxY+1)))) { canStay=false; }
            if(ups.contains(   new Point(p.getX(), (1000*(maxY+1) + p.getY() + newState.getRound())%(maxY+1)))) { canStay=false; }

            if(canStay && !queue.contains(newState)) {
                queue.add(newState);
            }
        }

        return -1;
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 247
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 728
    }
}
