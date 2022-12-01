package year2019.day15;

import utils.FileHelper;
import utils.OpComputer;
import utils.Point;

import java.util.*;

public class Day15 {

    static int stepsToOxygenTank=Integer.MAX_VALUE;

    //north (1), south (2), west (3), and east (4)

    static class State {
        OpComputer computer;
        HashMap<Point, Character> map;
        Point position;
        int steps=0;

        public State(OpComputer computer, HashMap<Point, Character> map, Point position, int steps) {
            this.computer = new OpComputer(computer);
            this.map = map;
            this.position = position;
            this.steps = steps;
        }

        void move(int direction) {
            computer.addInput(direction);
            computer.runUntilOutput();
        }
        int getSteps() {
            return steps;
        }
        Point getPosition() {
            return position;
        }
        List<State> getPossibleStates_old() {
            //checkWalls();
            List<State> possibleStates=new ArrayList<>();

            Point north=position.north();
            Point south=position.south();
            Point west =position.west();
            Point east =position.east();

            if('#' != map.get(north)) {
                possibleStates.add(new State(computer, map, north, steps+1));
            }
            if ('#' != map.get(south) ) {
                possibleStates.add(new State(computer, map,  south,steps+1));
            }
            if ('#' != map.get(west) ) {
                possibleStates.add(new State(computer, map, west, steps+1));
            }
            if ('#' != map.get(east) ) {
                possibleStates.add(new State(computer, map, east,steps+1));
            }
            return possibleStates;
        }

         List<State> getPossibleStates() {
             List<State> possibleStates=new ArrayList<>();
             Point north=position.north();
             Point south=position.south();
             Point west =position.west();
             Point east =position.east();


             //Check north
            if (!map.containsKey(north))
            {
                OpComputer c = new OpComputer(computer);
                c.addInput(1);
                int output = c.runUntilOutput().intValue();
                addToMap(position.north(), output);
                if (output > 0 ) {
                    possibleStates.add(new State(c, map, north, steps+1));
                }
            }
            //Check south
            if (!map.containsKey(position.south()))
            {
                OpComputer c = new OpComputer(computer);
                c.addInput(2);
                int output = c.runUntilOutput().intValue();
                addToMap(position.south(), output);
                if (output > 0) {
                    possibleStates.add(new State(c, map, south, steps+1));
                }
            }
            //Check west
            if (!map.containsKey(position.west()))
            {
                OpComputer c = new OpComputer(computer);
                c.addInput(3);
                int output = c.runUntilOutput().intValue();
                addToMap(west, output);
                if (output > 0) {
                    possibleStates.add(new State(c, map, west, steps+1));
                }
            }

            //Check east
            if (!map.containsKey(position.east())) {
                OpComputer c = new OpComputer(computer);
                c.addInput(4);
                int output = c.runUntilOutput().intValue();
                addToMap(east, output);
                if (output > 0) {
                    possibleStates.add(new State(c, map, east, steps+1));
                }
            }
            return possibleStates;
        }
        void addToMap(Point p, int output) {
            switch(output) {
                case 0 : {map.put(p, '#'); break;}
                case 1 : {map.put(p, '.'); break;}
                case 2 : {map.put(p, 'o'); stepsToOxygenTank=Math.min(getSteps()+1, stepsToOxygenTank); break;}
            }
        }

    }


    static void createMap(State startState) {
        HashSet<State> queue = new HashSet<>();
        HashSet<Point> visited = new HashSet<>();
        queue.add(startState);

        while (!queue.isEmpty()){
            HashSet<State> newToAdd  = new HashSet<>();
            HashSet<State> toBeRemoved = new HashSet<>();
            for (State current : queue ) {
                toBeRemoved.add(current);
                Point pos = current.getPosition();
                if(visited.contains(pos)) {
                    continue;
                }
                visited.add(pos);

                for (State state : current.getPossibleStates()) {
                    if (!visited.contains(state.getPosition())) {
                        newToAdd.add(state);
                    }
                }
            }
            queue.addAll(newToAdd);
            queue.removeAll(toBeRemoved);
        }
    }
    static int timeToFillWithOxygen(HashMap<Point,Character> map) {
        Point oxygenTank=null;
        for (Map.Entry<Point, Character> p : map.entrySet()) {
            if (p.getValue()=='o') {
                oxygenTank=p.getKey();
                break;
            }
        }
        HashSet<Point> queue=new HashSet<>();
        queue.add(oxygenTank);
        int time=-1;
        while(!queue.isEmpty()) {

            time++;
            HashSet<Point> newToAdd = new HashSet<>();
            for (Point p : queue) {
                map.put(p, 'O');
            }
            for (Point p : queue) {
                newToAdd.addAll(emptyNeighbors(p,map));
            }
            queue= new HashSet<>(newToAdd);

        }


        return time;
    }
    static HashSet<Point> emptyNeighbors(Point p, HashMap<Point, Character> map) {
        HashSet<Point> emptyNeighbors = new HashSet<>();
        if (map.get(p.north())=='.') {
            emptyNeighbors.add(p.north());
        }
        if (map.get(p.east())=='.') {
            emptyNeighbors.add(p.east());
        }
        if (map.get(p.south())=='.') {
            emptyNeighbors.add(p.south());
        }
        if (map.get(p.west())=='.') {
            emptyNeighbors.add(p.west());
        }
        return emptyNeighbors;

    }

    public static void main(String[] args) {
        Long t0 = System.currentTimeMillis();
        String input = new FileHelper().readFile("year2019/day15/input.txt").get(0);

        OpComputer computer = new OpComputer(input);
        HashMap<Point, Character> map = new HashMap<>();
        Point position=new Point(0,0);

        map.put(position,'S');
        State startState= new State(computer, map, position,  0);
        createMap(startState);

        System.out.println("Day15A: "+stepsToOxygenTank);
        System.out.println("Day15B: "+timeToFillWithOxygen(map));
        System.out.println("Time: "+(System.currentTimeMillis()-t0)+" ms");
}
}