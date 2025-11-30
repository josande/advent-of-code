package year2021.day23;

import lombok.Data;
import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day23 {

    static Object solveA(List<String> values) {
        return solvePuzzle(values);
    }
    static int solvePuzzle(List<String> values) {
        HashMap<Point, Character> map = MapUtil.asMap(values);
        //  Point.print(map);

        State startState = new State(map, 0);

        HashMap<HashMap<Point, Character>, Integer> testedStates= new HashMap<>();
        Queue<State> toDo = new ArrayDeque<>();
        toDo.add(startState);
        int lowestEnergyUsageFound = Integer.MAX_VALUE;
       // State lowestSolution=null;
        while(!toDo.isEmpty()) {

            State current = toDo.poll();

            if(testedStates.containsKey(current.getMap())) {
                if(testedStates.get(current.getMap())<=current.getEnergy()) {
                    continue;
                }
            }
            testedStates.put(current.getMap(), current.getEnergy());
            if(current.getEnergy() >= lowestEnergyUsageFound) {
                continue;
            }
            if(current.isWon()) {
                lowestEnergyUsageFound = current.getEnergy();
                continue;
            }
            toDo.addAll(current.getAllStates());
        }

        return lowestEnergyUsageFound;
    }
    @Data
    static class State {
        HashMap<Point, Character> map;
        int energy;


        State(HashMap<Point, Character> map, int energy) {
            this.map=map;
            this.energy=energy;
        }

        List<State> getAllStates() {
            ArrayList<State> possibleStatesAfter = new ArrayList<>();
            boolean foundGoodMove = false;
            State goodMoveState = null;
            int goodMoveMaxCost=-1;
            for(Map.Entry<Point, Character> entry : map.entrySet()) {
                if(entry.getValue()=='#' || entry.getValue()=='.' || entry.getValue()==' ')
                    continue;

                if(isRightRoom(entry.getValue(), entry.getKey()) && onlySameTokensInRoom(entry.getValue(), entry.getKey(), map)) {
                    continue;
                }


                HashSet<Point> freePoints = getAllFreePoints(entry.getKey(), map);
                freePoints.remove(entry.getKey());
                for (Point point : freePoints) {
                    if(isLegalMove(entry.getKey(), point, map)) {
                        int cost = getDistance(entry.getKey(), point) * getEnergyFactor(entry.getValue());

                        HashMap<Point, Character> mapAfter = new HashMap<>(map);
                        mapAfter.put(entry.getKey(), '.');
                        mapAfter.put(point, entry.getValue());
                        State stateAfter = new State(mapAfter, getEnergy()+cost);
                        if(isRightRoom(entry.getValue(), point)) {
                            foundGoodMove = true;
                            if(cost > goodMoveMaxCost) {
                                goodMoveMaxCost=cost;
                                goodMoveState = stateAfter;
                            }
                        }
                        possibleStatesAfter.add(stateAfter);
                    }
                }
            }
            if(foundGoodMove) {
                possibleStatesAfter.clear();
                possibleStatesAfter.add(goodMoveState);
            }
            return possibleStatesAfter;
        }

        private int getDistance(Point start, Point end) {
            if(start.getX() == end.getX())
                return start.getManhattanDistance(end);

            if(start.getY()==1 || end.getY()==1) {
                return start.getManhattanDistance(end);
            }
            return start.getY()-1 + end.getY()-1 + Math.abs(start.getX()-end.getX());

        }

        private boolean isLegalMove(Point start, Point end, HashMap<Point, Character> map) {
            char token = map.get(start);
            if(start.getY()==1 && end.getY()==1) return false;
            if(start.getX() == end.getX()) return false;
            if(end.getY()>1) {
                if(isRightRoom(token, end)) {
                    return onlySameTokensInRoom(token, end, map);
                } else {
                    return false;
                }
            } else {
                return (end.getX() != 3 && end.getX() != 5 && end.getX() != 7 && end.getX() != 9);
            }
        }

        private boolean isRightRoom(char token, Point end) {
            if(token =='A' && end.getX()==3) return true;
            if(token =='B' && end.getX()==5) return true;
            if(token =='C' && end.getX()==7) return true;
            return(token =='D' && end.getX()==9);
        }

        private boolean onlySameTokensInRoom(Character token, Point end, HashMap<Point, Character> map) {
            int x = end.getX();
            return map.entrySet().stream().filter(e-> e.getKey().getX()==x).allMatch(e -> (e.getValue() == '.' || e.getValue()=='#' || e.getValue()==token));
        }

        private int getEnergyFactor(Character value) {
            if(value=='A') return 1;
            if(value=='B') return 10;
            if(value=='C') return 100;
            if(value=='D') return 1000;

            throw new IllegalArgumentException("Bad value to move:'"+value+"'.");
        }

        private HashSet<Point> getAllFreePoints(Point start, HashMap<Point, Character> map) {
         //   Point.print(map);
            HashMap<Point, Character> newMap = new HashMap<>(map);
            char free = '.';
            newMap.remove(start);
            HashSet<Point> points = new HashSet<>();
            points.add(start);
            if(free == newMap.getOrDefault(start.north(),' ')) {
                points.addAll(getAllFreePoints(start.north(), newMap));
            }
            for(Point point : points) {
                newMap.remove(point);
            }
            if(free == newMap.getOrDefault(start.west(),' ')) {
                points.addAll(getAllFreePoints(start.west(), newMap));
            }
            for(Point point : points) {
                newMap.remove(point);
            }
            if(free == newMap.getOrDefault(start.east(),' ')) {
                points.addAll(getAllFreePoints(start.east(), newMap));
            }
            for(Point point : points) {
                newMap.remove(point);
            }
            if(free == newMap.getOrDefault(start.south(),' ')) {
                points.addAll(getAllFreePoints(start.south(), newMap));
            }
            return points;
        }

        public boolean isWon() {
            if (map.entrySet().stream().filter(e -> e.getValue() == 'A').allMatch(e -> e.getKey().getX() == 3 && e.getKey().getY() >= 2)) {
                if (map.entrySet().stream().filter(e -> e.getValue() == 'B').allMatch(e -> e.getKey().getX() == 5 && e.getKey().getY() >= 2)) {
                    if (map.entrySet().stream().filter(e -> e.getValue() == 'C').allMatch(e -> e.getKey().getX() == 7 && e.getKey().getY() >= 2)) {
                        return map.entrySet().stream().filter(e -> e.getValue() == 'D').allMatch(e -> e.getKey().getX() == 9 && e.getKey().getY() >= 2);
                    }
                }
            }
            return false;
        }
    }
    static Object solveB(List<String> values) {
        ArrayList<String> newMap = new ArrayList<>();
        newMap.add(values.get(0));
        newMap.add(values.get(1));
        newMap.add(values.get(2));
        newMap.add("  #D#C#B#A#  ");
        newMap.add("  #D#B#A#C#  ");
        newMap.add(values.get(3));
        newMap.add(values.get(4));

        return solvePuzzle(newMap);
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //18282
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //50132
    }
}
