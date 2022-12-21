package year2022.day17;

import lombok.Data;
import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day17 {

    static Object solveA(List<String> values) {
        HashSet<Point> map = new HashSet<>();
        String wind=values.get(0);
        int windPos=0;
        int shapeCounter=0;
        for(int rockNumber=0; rockNumber<2022; rockNumber++) {
            HashSet<Point> rock = getRock(shapeCounter, map);
            shapeCounter++;
            boolean collision=false;
            while(!collision) {
                if(wind.charAt(windPos)=='>') {
                    if(canMoveRight(rock, map)) {
                        moveRockRight(rock);
                    }
                } else {
                    if(canMoveLeft(rock, map)) {
                        moveRockLeft(rock);
                    }
                }
                windPos++;
                windPos%=wind.length();
                if(canMoveDown(rock, map)) {
                    moveRockDown(rock);
                } else {
                    addRockToMap(rock, map);
                    collision=true;
                }
            }
            map = cleanUpMap(map);
        }

        return -MapUtil.getMinY(map);
    }
    private static HashSet<Point> cleanUpMap(HashSet<Point> map) {
        int minY=MapUtil.getMinY(map);
        return (HashSet<Point>) map.stream().filter(e->e.getY() <= minY + 50).collect(Collectors.toSet());

    }
    private static boolean canMoveDown(HashSet<Point> rock, HashSet<Point> map) {
        for(Point p : rock) {
            if (map.contains(p.south()) || p.getY()==-1) {
                return false;
            }
        }
        return true;
    }
    private static boolean canMoveRight(HashSet<Point> rock, HashSet<Point> map) {
        for(Point p : rock) {
            if (map.contains(p.east()) || p.getX()==7) {
                return false;
            }
        }
        return true;
    }
    private static boolean canMoveLeft(HashSet<Point> rock, HashSet<Point> map) {
        for(Point p : rock) {
            if (map.contains(p.west()) || p.getX()==1) {
                return false;
            }
        }
        return true;
    }
    private static void moveRockRight(HashSet<Point> rock) {
        HashSet<Point> oldRock= new HashSet<>(rock);
        rock.clear();
        for(Point p : oldRock) {
            rock.add(p.east());
        }
    }
    private static void moveRockLeft(HashSet<Point> rock) {
        HashSet<Point> oldRock= new HashSet<>(rock);
        rock.clear();
        for(Point p : oldRock) {
            rock.add(p.west());
        }
    }
    private static void moveRockDown(HashSet<Point> rock) {
        HashSet<Point> oldRock= new HashSet<>(rock);
        rock.clear();
        for(Point p : oldRock) {
            rock.add(p.south());
        }
    }
   private static void addRockToMap(HashSet<Point> rock, HashSet<Point> map) {
        map.addAll(rock);
    }

    private static HashSet<Point> getRock(int shapeCounter, HashSet<Point> map) {
        int yValue = MapUtil.getMinY(map) - 4;
        int xValue = 3;
        HashSet<Point> rock = new HashSet<>();
        if(shapeCounter % 5 == 0) {
            // ####
            rock.add(new Point(xValue, yValue));
            rock.add(new Point(xValue+1, yValue));
            rock.add(new Point(xValue+2, yValue));
            rock.add(new Point(xValue+3, yValue));

        } else if(shapeCounter % 5 == 1) {
            // .#.
            // ###
            // .#.
           rock.add(new Point(xValue+1, yValue-2));
           rock.add(new Point(xValue, yValue-1));
           rock.add(new Point(xValue+1,yValue-1));
           rock.add(new Point(xValue+2, yValue-1));
           rock.add(new Point(xValue+1, yValue));
        } else if(shapeCounter % 5 == 2) {
            // ..#
            // ..#
            // ###
            rock.add(new Point(xValue+2, yValue-2));
            rock.add(new Point(xValue+2, yValue-1));
            rock.add(new Point(xValue, yValue));
            rock.add(new Point(xValue+1, yValue));
            rock.add(new Point(xValue+2, yValue));

        } else if(shapeCounter % 5 == 3) {
            // #
            // #
            // #
            // #
            rock.add(new Point(xValue, yValue-3));
            rock.add(new Point(xValue, yValue-2));
            rock.add(new Point(xValue, yValue-1));
            rock.add(new Point(xValue, yValue));
        } else if(shapeCounter % 5 == 4) {
            // ##
            // ##
            rock.add(new Point(xValue, yValue-1));
            rock.add(new Point(xValue+1, yValue-1));
            rock.add(new Point(xValue, yValue));
            rock.add(new Point(xValue+1, yValue));
        }
        return rock;
    }
    @Data
    static class State {
        int windPos;
        int shapeCounter;
        int hash;
        int minY;

        public State(int windPos, int shapeCounter, int hash, int minY) {
            this.windPos=windPos;
            this.shapeCounter=shapeCounter;
            this.hash=hash;
            this.minY=minY;
        }
        public int getMinY() {
            return minY;
        }

        @Override
        public int hashCode() {
            return windPos*100000+shapeCounter+hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;

            if (!(obj instanceof State)) {
                return false;
            }

            State o = (State) obj;
            return this.windPos == o.windPos &&
                   this.shapeCounter == o.shapeCounter &&
                   this.hash == o.hash;
        }
    }

    static Object solveB(List<String> values) {
        HashSet<Point> map = new HashSet<>();
        String wind=values.get(0);
        int windPos=0;
        int shapeCounter=0;
        boolean cycleFound=false;
        int cycleLength;
        int cycleHeight;
        long targetCycles = 1000000000000L;
        long extraHeight = 0L;
        HashMap<State, Integer> knownStates=new HashMap<>();
        HashMap<Integer, Integer> rockHeight=new HashMap<>();
        for(long rockNumber=0; rockNumber<targetCycles; rockNumber++) {
            HashSet<Point> rock = getRock(shapeCounter, map);
            shapeCounter++;
            shapeCounter%=5;
            boolean collision=false;
            while(!collision) {
                if(wind.charAt(windPos)=='>') {
                    if(canMoveRight(rock, map)) {
                        moveRockRight(rock);
                    }
                } else {
                    if(canMoveLeft(rock, map)) {
                        moveRockLeft(rock);
                    }
                }
                windPos++;
                windPos%=wind.length();
                if(canMoveDown(rock, map)) {
                    moveRockDown(rock);
                } else {
                    addRockToMap(rock, map);
                    collision=true;
                }
            }
            map = cleanUpMap(map);

            if(!cycleFound) {
                int hash = viaHashCode(map);
                int height = -MapUtil.getMinY(map);

                State state = new State(windPos, shapeCounter, hash, height);
                rockHeight.put((int)rockNumber, height);

                if (knownStates.containsKey(state)) {
                    cycleLength = (int) rockNumber - knownStates.get(state);
                    cycleHeight = height - rockHeight.get(knownStates.get(state));

                    cycleFound = true;

                    long cycles = (targetCycles-rockNumber)/cycleLength;
                    extraHeight = cycles*cycleHeight;
                    rockNumber += cycleLength*cycles;
                } else {
                    knownStates.put(state, (int) rockNumber);
                }
            }
        }

        return -MapUtil.getMinY(map)+extraHeight;
    }
    private static int viaHashCode(HashSet<Point> map) {
        int result=0;
        int maxY=MapUtil.getMaxY(map);
        for(Point p : map) {
            Point np = new Point(p.getX(), p.getY()-maxY);
            result+=np.hashCode();
        }
        return result;
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 3153
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 1553665689155
    }
}
