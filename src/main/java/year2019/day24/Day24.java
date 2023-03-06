package year2019.day24;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day24 {

    static HashMap<Point, Character> makeMap(List<String> input ) {
        emptyMap=new HashMap<>();
        HashMap<Point, Character> map = new HashMap<>();
        int y=0;
        for (String row : input){
            int x=0;
            for (Character c : row.toCharArray()) {
                Point p = new Point(x, y);
                map.put(p, c);
                x++;
                emptyMap.put(p, '.');
            }
            y++;
        }
        return map;
    }


    static HashMap<Point, Character> evolve(HashMap<Point, Character> before) {
        HashMap<Point, Character> after=new HashMap<>();
        for (Point p : before.keySet()) {
            int bugs = 0;
            if (before.getOrDefault(p.north(), '.') == '#') {
                bugs++;
            }
            if (before.getOrDefault(p.south(), '.') == '#') {
                bugs++;
            }
            if (before.getOrDefault(p.west(), '.') == '#') {
                bugs++;
            }
            if (before.getOrDefault(p.east(), '.') == '#') {
                bugs++;
            }
            char current = before.get(p);

            if(current=='#') {
                if (bugs == 1) {
                    after.put(p, '#');
                }
                else {
                    after.put(p, '.');
                }

            } else {
                if (bugs == 1 || bugs == 2) {
                    after.put(p, '#');
                }
                else {
                    after.put(p, '.');
                }
            }
                // An empty space becomes infested with a bug if exactly one or two bugs are adjacent to it.
                // A bug dies (becoming an empty space) unless there is exactly one bug adjacent to it.
        }
        return after;
    }

    static HashMap<Point, Character> emptyMap;
    static HashMap<Integer, HashMap<Point, Character>> evolveWithLayers(HashMap<Integer, HashMap<Point, Character>> before, int iterations) {
        HashMap<Integer, HashMap<Point, Character>> after=new HashMap<>(before);
        for (int i =0; i<iterations; i++) {
            after = evolveWithLayers(after);
        }
        return after;
    }
    static HashMap<Integer, HashMap<Point, Character>> evolveWithLayers(HashMap<Integer, HashMap<Point, Character>> before) {

        int lowestLevelSoFar=before.keySet().stream().mapToInt(v -> v).min().orElse(0);
        int highestLevelSoFar=before.keySet().stream().mapToInt(v -> v).max().orElse(0);


        HashMap<Integer, HashMap<Point, Character>> afterTotal=new HashMap<>();
        for(int level=lowestLevelSoFar-1; level<=highestLevelSoFar+1; level++) {
            HashMap<Point, Character> outside            = before.getOrDefault(level-1, new HashMap<>(emptyMap));
            HashMap<Point, Character> inside             = before.getOrDefault(level+1, new HashMap<>(emptyMap));
            HashMap<Point, Character> currentLevelBefore = before.getOrDefault(level, new HashMap<>(emptyMap));

            HashMap<Point, Character> currentLevelAfter  = new HashMap<>(before.getOrDefault(level, new HashMap<>(emptyMap)));


            for(Map.Entry<Point, Character> e : currentLevelBefore.entrySet()) {
                char current = e.getValue();
                Point p = e.getKey();
                if(p.getX()==2 && p.getY()==2)
                    continue;

                int bugs=0;
                //FindBugsNorth:
                if(p.getY()==0) {
                    if(outside.get(new Point(2,1))=='#') { // look at level above, middle square of side.
                        bugs++;
                    }
                } else if(p.getY()==3 && p.getX()==2) { //look at level inside, i.e. level+1
                    if(inside.get(new Point(0,4))=='#') {
                        bugs++;
                    }
                    if(inside.get(new Point(1,4))=='#') {
                        bugs++;
                    }
                    if(inside.get(new Point(2,4))=='#') {
                        bugs++;
                    }
                    if(inside.get(new Point(3,4))=='#') {
                        bugs++;
                    }
                    if(inside.get(new Point(4,4))=='#') {
                        bugs++;
                    }
                } else {
                    if(currentLevelBefore.get(p.north())=='#') {
                        bugs++;
                    }
                }

                if(bugs<=2) {
                    //FindBugsSouth:
                    if(p.getY()==4) {
                        if(outside.get(new Point(2,3))=='#') {
                            bugs++;
                        }
                    } else if(p.getY()==1 && p.getX()==2) {
                        if(inside.get(new Point(0,0))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(1,0))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(2,0))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(3,0))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(4,0))=='#') {
                            bugs++;
                        }
                    } else {
                        if(currentLevelBefore.get(p.south())=='#') {
                            bugs++;
                        }
                    }



                }
                if(bugs<=2) {
                    //FindBugsEast:
                    if(p.getX()==4) {
                        if(outside.get(new Point(3,2))=='#') {
                            bugs++;
                        }
                    } else if(p.getY()==2 && p.getX()==1) {
                        if(inside.get(new Point(0,0))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(0,1))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(0,2))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(0,3))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(0,4))=='#') {
                            bugs++;
                        }
                    } else {
                        if(currentLevelBefore.get(p.east())=='#') {
                            bugs++;
                        }
                    }
                }
                if(bugs<=2) {
                    //FindBugsWest:
                    if(p.getX()==0) {
                        if(outside.get(new Point(1,2))=='#') {
                            bugs++;
                        }
                    } else if(p.getY()==2 && p.getX()==3) {
                        if(inside.get(new Point(4,0))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(4,1))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(4,2))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(4,3))=='#') {
                            bugs++;
                        }
                        if(inside.get(new Point(4,4))=='#') {
                            bugs++;
                        }
                    } else {
                        if(currentLevelBefore.get(p.west())=='#') {
                            bugs++;
                        }
                    }
                }
                if (current == '#') {
                    if (bugs == 1) {
                        currentLevelAfter.put(p, '#');
                    } else {
                        currentLevelAfter.put(p, '.');
                    }

                } else {
                    if (bugs == 1 || bugs == 2) {
                        currentLevelAfter.put(p, '#');
                    } else {
                        currentLevelAfter.put(p, '.');
                    }
                }
            }

            afterTotal.put(level, currentLevelAfter);
            // An empty space becomes infested with a bug if exactly one or two bugs are adjacent to it.
            // A bug dies (becoming an empty space) unless there is exactly one bug adjacent to it.
        }
        return afterTotal;
    }
    static int getNumberOfBugs(HashMap<Integer, HashMap<Point, Character>> levelMap) {
        return (int) levelMap.values().stream().map(HashMap::values).flatMap(Collection::stream).filter(c -> c =='#').count();

    }

    static long getBioDiversity(HashMap<Point, Character> map) {
        long bio=0;
        int pow=0;
        for(int y=0;y<5;y++) {
            for(int x=0;x<5;x++) {
                if(map.get(new Point(x,y))=='#') {
                    bio += Math.pow(2, pow);
                }
                pow++;
            }
        }
        return bio;
    }
    public static void main(String[] args) {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");


        HashMap<Point, Character> map = makeMap(inputs);
        Set<HashMap<Point, Character>> knownState=new HashSet<>();
        HashMap<Point, Character> after = evolve(map);
        while(!knownState.contains(after)) {
            knownState.add(after);
            after=evolve(after);
        }
        long bioDiversity = getBioDiversity(after);

        HashMap<Integer, HashMap<Point, Character>> levelMap=new HashMap<>();
        levelMap.put(0, map);

        levelMap=evolveWithLayers(levelMap,200);


        System.out.println("Day24A: "+bioDiversity);
        System.out.println("Day24B: "+getNumberOfBugs(levelMap));
    }
}
