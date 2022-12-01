package year2021.day23;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileHelper;
import utils.Point;
import year2019.day20.Day20;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day23 {

    static Object solveA(List<String> values) {
        return solvePuzzle(values);
    }
    static int solvePuzzle(List<String> values) {
        HashMap<Point, Character> map = new HashMap<>();
        for(int y=0;y<values.size();y++){
            for(int x=0;x<values.get(y).length();x++){
                map.put(new Point(x,y), values.get(y).charAt(x));
            }
        }
        //  Point.print(map);

        State startState = new State(map, 0);

        HashMap<HashMap<Point, Character>, Integer> testedStates= new HashMap<>();
        Queue<State> toDo = new ArrayDeque<>();
        toDo.add(startState);
        int lowestEnergyUsageFound= Integer.MAX_VALUE;
       // State lowestSolution=null;
        while(!toDo.isEmpty()) {

            State current = toDo.poll();

            if(testedStates.containsKey(current.getMap())) {
                if(testedStates.get(current.getMap())<current.getEnergy()) {
                    continue;
                }
            }
            testedStates.put(current.getMap(), current.getEnergy());
            if(current.getEnergy() >= lowestEnergyUsageFound) {
                continue;
            }
            if(current.isWon()) {
                lowestEnergyUsageFound = current.getEnergy();
           //     lowestSolution = current;
            }
            var statesAfter = current.getAllStates();
            toDo.addAll(statesAfter);
        }


      //  ArrayList<State> previousStates = new ArrayList<>();
      //  State p = lowestSolution.getPreviousState();
       // while (p!=null) {
       //     previousStates.add(p);
       //     p = p.getPreviousState();
       // }
      //  for(int i = previousStates.size()-1; i>=0; i--) {
      //      Point.print(previousStates.get(i).getMap());
      //      System.out.println("-->"+previousStates.get(i).getEnergy());
//
      //  }

        return lowestEnergyUsageFound;
    }
    @Data
    static class State {
        HashMap<Point, Character> map;
        int energy;
      //  State previousState;

   //     @Override
   //     public boolean equals(Object other) {
   //         if (!(other instanceof State)) return false;
   //         State o = (State) other;
   //         return ( this.getMap().equals(o.getMap())
   //                 && this.getEnergy() == o.getEnergy() );
   //     }
   //     @Override
   //     public int hashCode() {
   //
   //         return map.hashCode()+energy*3+7;
   //     }

        State(HashMap<Point, Character> map, int energy) {
            this.map=map;
            this.energy=energy;
        }
      //  State(HashMap<Point, Character> map, int energy, State previousStates) {
      //      this.map=map;
      //      this.energy=energy;
      //      this.previousState=previousStates;
//
      //  }

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


                List<Point> freePoints = getAllFreePoints(entry.getKey(), map);
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
               // System.out.println("Found a good move..?");
               // Point.print(map);
               // System.out.println("--->");
               // Point.print(goodMoveState.getMap());
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
            if(token =='D' && end.getX()==9) return true;
            return false;
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

        private List<Point> getAllFreePoints(Point start, HashMap<Point, Character> map) {
         //   Point.print(map);
            HashMap<Point, Character> newMap = new HashMap<>(map);
            char free = '.';
            newMap.remove(start);
            List<Point> points =new ArrayList<>();
            points.add(start);
            if(free == newMap.getOrDefault(start.north(),' ')) {
                points.addAll(getAllFreePoints(start.north(), newMap));
            }
            if(free == newMap.getOrDefault(start.west(),' ')) {
                points.addAll(getAllFreePoints(start.west(), newMap));
            }
            if(free == newMap.getOrDefault(start.east(),' ')) {
                points.addAll(getAllFreePoints(start.east(), newMap));
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
                        if (map.entrySet().stream().filter(e -> e.getValue() == 'D').allMatch(e -> e.getKey().getX() == 9 && e.getKey().getY() >= 2)) {
                            return true;
                        }
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

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

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
