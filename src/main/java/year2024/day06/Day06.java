package year2024.day06;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.*;

import java.util.*;


public class Day06 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day06());
    }

    @Override
    public Object solveA(List<String> input) {

        HashMap<Integer, ArrayList<Integer>> obstacleX = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> obstacleY = new HashMap<>();

        int guardX=0, guardY=0, direction =0;

        for(int y=0; y<input.size(); y++) {
            ArrayList<Integer> xWalls = new ArrayList<>();
            for(int x=0; x<input.get(y).length(); x++) {
                if(input.get(y).charAt(x)=='#') {
                    xWalls.add(x);
                    var yWall = obstacleY.getOrDefault(x, new ArrayList<>());
                    yWall.add(y);
                    obstacleY.put(x, yWall);
                }
                if(input.get(y).charAt(x) == '^') {guardX=x; guardY=y;}
            }
            obstacleX.put(y, xWalls);
        }
        return allVisited(guardX, guardY, direction, obstacleX, obstacleY).size();
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<Integer, ArrayList<Integer>> obstacleX = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> obstacleY = new HashMap<>();

        int guardX=0, guardY=0, direction =0;

        for(int y=0; y<input.size(); y++) {
            ArrayList<Integer> xWalls = new ArrayList<>();
            for(int x=0; x<input.get(y).length(); x++) {
                if(input.get(y).charAt(x)=='#') {
                    xWalls.add(x);
                    var yWall = obstacleY.getOrDefault(x, new ArrayList<>());
                    yWall.add(y);
                    obstacleY.put(x, yWall);
                }
                if(input.get(y).charAt(x) == '^') {guardX=x; guardY=y;}
            }
            obstacleX.put(y, xWalls);
        }
        int counter=0;
        for(var pair : allVisited(guardX, guardY, direction, obstacleX, obstacleY)) {
            HashMap<Integer, ArrayList<Integer>> _obstacleX = new HashMap<>(obstacleX);
            HashMap<Integer, ArrayList<Integer>> _obstacleY = new HashMap<>(obstacleY);
            var tempY = new ArrayList<>(_obstacleY.getOrDefault(pair.getKey(), new ArrayList<>()));
            var tempX = new ArrayList<>(_obstacleX.getOrDefault(pair.getValue(), new ArrayList<>()));
            tempY.add(pair.getValue());
            tempX.add(pair.getKey());
            Collections.sort(tempY);
            Collections.sort(tempX);
            _obstacleY.put(pair.getKey(), tempY);
            _obstacleX.put(pair.getValue(), tempX);
            if(isLoop(guardX, guardY, direction, _obstacleX, _obstacleY)) counter++;

        }
        return counter;
    }

    private boolean isLoop(int guardX, int guardY, int direction,
                           HashMap<Integer, ArrayList<Integer>> obstacleX,
                           HashMap<Integer, ArrayList<Integer>> obstacleY) {
        HashSet<Pair<Integer, Integer>> visited = new HashSet<>();
        while(!visited.contains(new ImmutablePair<>(guardX, guardY))) {
            int steps = getStepCount(guardX, guardY, direction, obstacleX, obstacleY);
            if (steps < 0) return false;

            if(steps>0)
                visited.add(new ImmutablePair<>(guardX, guardY));
            switch (direction) {
                case 0 -> guardY -= steps;
                case 1 -> guardX += steps;
                case 2 -> guardY += steps;
                case 3 -> guardX -= steps;
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            }
            direction = (direction + 1) % 4;

        }
        return true;
    }
    private HashSet<Pair<Integer, Integer>> allVisited(int guardX, int guardY, int direction,
                           HashMap<Integer, ArrayList<Integer>> obstacleX,
                           HashMap<Integer, ArrayList<Integer>> obstacleY) {
        HashSet<Pair<Integer, Integer>> visited = new HashSet<>();
        HashSet<Pair<Integer, Integer>> allVisited = new HashSet<>();
        while(!visited.contains(new ImmutablePair<>(guardX, guardY))) {
            int steps = getStepCount(guardX, guardY, direction, obstacleX, obstacleY);
            visited.add(new ImmutablePair<>(guardX, guardY));
            int xBefore = guardX, yBefore=guardY;
            if (steps < 0) {
                switch (direction) {
                    case 0 -> guardY=0;
                    case 1 -> guardX=obstacleX.keySet().stream().mapToInt(i->i).max().getAsInt();
                    case 2 -> guardY=obstacleY.keySet().stream().mapToInt(i->i).max().getAsInt();
                    case 3 -> guardX=0;
                }
                for(int y=Math.min(yBefore, guardY); y<=Math.max(yBefore, guardY); y++) {
                    for(int x=Math.min(xBefore, guardX); x<=Math.max(xBefore, guardX); x++) {
                        allVisited.add(new ImmutablePair<>(x, y));
                    }
                }
                return allVisited;
            } else {
                switch (direction) {
                    case 0 -> guardY -= steps;
                    case 1 -> guardX += steps;
                    case 2 -> guardY += steps;
                    case 3 -> guardX -= steps;
                    default -> throw new IllegalStateException("Unexpected value: " + direction);
                }
                direction = (direction + 1) % 4;
                for(int y=Math.min(yBefore, guardY); y<=Math.max(yBefore, guardY); y++) {
                    for(int x=Math.min(xBefore, guardX); x<=Math.max(xBefore, guardX); x++) {
                        allVisited.add(new ImmutablePair<>(x, y));
                    }
                }
            }
        }
        return allVisited;
    }

    private Integer getStepCount( int guardX, int guardY, int direction,
                                  HashMap<Integer, ArrayList<Integer>> obstacleX,
                                  HashMap<Integer, ArrayList<Integer>> obstacleY) {
        int steps=0;
        switch (direction) {
            case 0 -> {
                for(int y : obstacleY.getOrDefault(guardX, new ArrayList<>()))
                    if(y<guardY) steps=guardY-y;
                return steps-1;
            }
            case 1 -> {
                for(int x : obstacleX.getOrDefault(guardY, new ArrayList<>()).reversed())
                    if(x>guardX) steps=x-guardX;
                return steps-1;
            }
            case 2 -> {
                for(int y : obstacleY.getOrDefault(guardX, new ArrayList<>()).reversed())
                    if(y>guardY) steps=y-guardY;
                return steps-1;
            }
            case 3 -> {
                for(int x : obstacleX.getOrDefault(guardY, new ArrayList<>()))
                    if(x<guardX) steps=guardX-x;
                return steps-1;
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }
}
