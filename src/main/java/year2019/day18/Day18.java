package year2019.day18;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day18 {

    static List<Object> allKeys=new ArrayList<>();

    static class State {
        HashMap<Point, Character> map;
        HashSet<Point> visitedPoints;
        Point position;
        int steps;
        List<Integer> keys=new ArrayList<>();

        State(Point start, HashMap<Point, Character> map) {
            position=start;
            keys=new ArrayList<>();
            visitedPoints=new HashSet<>();
            this.map=map;
            steps=0;

        }
        State(State state, Point point) {
            this.map=new HashMap<>(state.getMap());
            this.visitedPoints=new HashSet<>(state.getVisitedPoints());
            position=point;
            steps=state.getSteps()+1;
            keys.addAll(state.getKeys());
            checkPosition();
        }
        void checkPosition() {
            int c = (int) map.get(position);
            if (c >=97 && c <=122 ) {
                keys.add(c);
                map.put(position,'.');
                visitedPoints=new HashSet<>();
            }
            map.put(position, ' ');
            visitedPoints.add(position);
        }


        List<State> getPossibleStates() {
            List<State> possibleStates = new ArrayList<>();

            Point north = position.north();
            Point south = position.south();
            Point west = position.west();
            Point east = position.east();

            if (!visitedPoints.contains(north) && map.get(north) != '#') {
                if (isUnLockedDoor(north))
                    possibleStates.add(new State(this, north));
            }
            if (!visitedPoints.contains(south) && map.get(south) != '#') {
                if (isUnLockedDoor(south))
                    possibleStates.add(new State(this, south));
            }
            if (!visitedPoints.contains(west) && map.get(west) != '#') {
                if (isUnLockedDoor(west))
                    possibleStates.add(new State(this, west));
            }
            if (!visitedPoints.contains(east) && map.get(east) != '#') {
                if (isUnLockedDoor(east))
                    possibleStates.add(new State(this, east));
            }

            return possibleStates;
        }
        boolean isUnLockedDoor(Point p) {
            int c = (int) map.get(p);
            if (c>=65 && c<=90) {
                return(keys.contains(c+32));
            }

            return true;
        }

        public HashMap<Point, Character> getMap() {
            return map;
        }

        public void setMap(HashMap<Point, Character> map) {
            this.map = map;
        }

        public HashSet<Point> getVisitedPoints() {
            return visitedPoints;
        }

        public Point getPosition() {
            return position;
        }

        public List<Integer> getKeys() {
            Collections.sort(keys);

            return keys;
        }

        public int getSteps() {
            return steps;
        }

        public void setSteps(int steps) {
            this.steps = steps;
        }

        boolean gotAllKeys() {
            return keys.size()==allKeys.size();
        }
    }

    static HashMap<Point, Character> makeMap(List<String> input) {
        allKeys.clear();
        HashMap<Point, Character> map = new HashMap<>();
        int x=0,y=0;
        for (String row : input){
            for (Character c : row.toCharArray()) {
                map.put(new Point(x,y), c);
                x++;
                if(((int) c) >=97 && (int) c <123 ) {
                    allKeys.add(c);
                }
            }
            x=0;
            y++;
        }
        return map;
    }
    static Point getEntrance(HashMap<Point, Character> map) {
        for (Map.Entry<Point, Character> e : map.entrySet()) {
            if (e.getValue() == '@') return e.getKey();
        }
        throw new IllegalArgumentException("Entrance not found!");
    }
    static Point[] getEntrances(HashMap<Point, Character> map) {
        List<Point> entrances = new ArrayList<>();
        for (Map.Entry<Point, Character> e : map.entrySet()) {
            if (e.getValue() == '@') entrances.add(e.getKey());
        }
        return entrances.toArray(new Point[0]);
    }

    static int getAllKeysInCave(HashMap<Point, Character> map, Point point) {
        State state = new State (point, map);
        return findMaxKeysInMinMoves(state);
    }


    static private int findMaxKeysInMinMoves(State start) {
        Stack<State> stack = new Stack<>();
        HashMap<Point, HashMap<List<Integer>, Integer>> visitedStates=new HashMap<>();

        stack.push(start);

        int maxKeysSoFar=0;
        int fewestSteps=0;
        while (!stack.isEmpty()) {
            State current = stack.pop();
            if (haveNotBeenBetter(current, visitedStates)) {

                if (current.getKeys().size() == maxKeysSoFar) {
                    fewestSteps = Math.min(current.getSteps(), fewestSteps);
                }
                if (current.getKeys().size() > maxKeysSoFar) {
                    fewestSteps = current.getSteps();
                    maxKeysSoFar = current.getKeys().size();
                }
                for (State dest : current.getPossibleStates()) {
                    stack.push(dest);
                }
            }

        }
        return fewestSteps;
    }
    static boolean haveNotBeenBetter(State state, HashMap<Point, HashMap<List<Integer>, Integer>> visitedStates) {
        if (!visitedStates.containsKey(state.getPosition())) {
            HashMap<List<Integer>, Integer> record = new HashMap<>();
            record.put(state.getKeys(), state.getSteps());
            visitedStates.put(state.getPosition(), record);
            return true;
        }
        HashMap<List<Integer>, Integer> previousVisitsHere = visitedStates.get(state.getPosition());
        for (Map.Entry<List<Integer>, Integer> e : previousVisitsHere.entrySet()) {
            if (new HashSet<>(e.getKey()).containsAll(state.getKeys()) && e.getValue() <= state.getSteps()) {
                return false;
            }
            if (new HashSet<>(e.getKey()).containsAll(state.getKeys()) && e.getKey().size() == state.getKeys().size())  {
                e.setValue(state.getSteps());
                return true;
            }
        }

        previousVisitsHere.put(state.getKeys(), state.getSteps());
        return true;
    }

    static HashMap<Point, Character> simplifyMap(HashMap<Point, Character>  map) {
        HashMap<Point, Character> currentMap = new HashMap<>(map);
        boolean keepOn=true;
        while (keepOn) {
            keepOn=false;
            HashMap<Point, Character> newMap = new HashMap<>(currentMap);
            for (Map.Entry<Point, Character> e : currentMap.entrySet()) {
                if (e.getValue() == '.' || e.getValue() == '#' || ((int) e.getValue()>=65 && (int) e.getValue()<=90)) { //third part as we can safely ignore locked doors if all sides are blocked already. We are just looking for keys.
                    int blockedSides = 0;
                    if ( currentMap.getOrDefault(e.getKey().north(), '#') == '#') blockedSides++;
                    if ( currentMap.getOrDefault(e.getKey().south(), '#') == '#') blockedSides++;
                    if ( currentMap.getOrDefault(e.getKey().west(),  '#') == '#') blockedSides++;
                    if ( currentMap.getOrDefault(e.getKey().east(),  '#') == '#') blockedSides++;

                    if (blockedSides == 3 && e.getValue()!='#') {
                        newMap.put(e.getKey(), '#');
                        keepOn=true;
                    }
                    if (blockedSides == 4) {
                        newMap.remove(e.getKey());
                        keepOn=true;
                    }
                }
            }
            currentMap=newMap;
        }
        return currentMap;
    }

    static void updateEntrances(HashMap<Point, Character>  map) {
        Point originalEntrance=getEntrance(map);

        map.put(originalEntrance, '#');
        map.put(originalEntrance.north(),'#');
        map.put(originalEntrance.south(),'#');
        map.put(originalEntrance.west(),'#');
        map.put(originalEntrance.east(),'#');

        map.put(originalEntrance.northEast(),'@');
        map.put(originalEntrance.northWest(),'@');
        map.put(originalEntrance.southEast(),'@');
        map.put(originalEntrance.southWest(),'@');
    }


    static void removeAllDoors(HashMap<Point, Character>  map) {
        for (Map.Entry<Point, Character> e : map.entrySet()) {
            int c = (int) e.getValue();
            if (65 <= c && c <= 90) {
                e.setValue('.');
            }
        }
    }




    static int findShortestDistance(State startState) {
        HashSet<State> queue = new HashSet<>();
        queue.add(startState);
        HashMap<Point, HashMap<List<Integer>, Integer>> visitedStates=new HashMap<>();


        int shortestPath=Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            HashSet<State> newToAdd  = new HashSet<>();
            HashSet<State> toBeRemoved = new HashSet<>();
            boolean foundAll=false;

            for (State current : queue ) {
                toBeRemoved.add(current);
                if (current.getSteps()<shortestPath && haveNotBeenBetter(current, visitedStates)) {
                    HashMap<List<Integer>, Integer> previousVisitsHere = visitedStates.getOrDefault(current.getPosition(), new HashMap<>());
                    previousVisitsHere.put(current.getKeys(), current.getSteps());
                    visitedStates.put(current.getPosition(), previousVisitsHere);

                    if (current.gotAllKeys()) {
                        foundAll = true;
                        shortestPath = Math.min(shortestPath, current.getSteps());
                    }
                    newToAdd.addAll(current.getPossibleStates());
                }
            }
            queue.addAll(newToAdd);
            if(foundAll) {
                for (State s : queue) {
                    if (s.getSteps()>=shortestPath) {
                        toBeRemoved.add(s);
                    }
                }
            }
            queue.removeAll(toBeRemoved);
        }
        return shortestPath;
    }
    public static void main(String[] args) {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        HashMap<Point, Character> startMap = makeMap(inputs);
        HashMap<Point, Character> mapA= simplifyMap(startMap);
        Point entranceA = getEntrance(mapA);
        State startStateA = new State(entranceA, mapA);

        int stepsA=findShortestDistance(startStateA);

        HashMap<Point, Character> mapB = makeMap(inputs);
        updateEntrances(mapB);
        simplifyMap(mapB);


        removeAllDoors(mapB);

        Point[] entrances = getEntrances(mapB);

        int stepsB = 0;
        for (Point p : entrances) {
            stepsB+=getAllKeysInCave(mapB, p);
        }

        System.out.println("Day18A: "+stepsA);  //6162
        System.out.println("Day18B: "+stepsB);  //1556
    }
}
