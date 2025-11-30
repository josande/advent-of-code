package year2020.day17;

import utils.FileHelper;
import utils.Point;
import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day17 {



    static long solveA(List<String> values) {
        HashSet<Point> activePoints = new HashSet<>();
        for(int y=0; y< values.size() ; y++) {
            for(int x=0; x< values.get(0).length() ; x++) {
                if(values.get(y).charAt(x)=='#')
                   activePoints.add(new Point(x, y,0));
            }
        }
        for(int round=0; round<6; round++) {
            HashSet<Point> scanned  = new HashSet<>();
            HashSet<Point> newActivePoints  = new HashSet<>();
            for (Point point : activePoints) {
                for(Point np : point.getAllNeighbours3d()) {
                    if(scanned.contains(np)) continue;
                    scanned.add(np);
                    long activeNeighbours = getActiveNeighbours3d(np, activePoints);
                    if( activePoints.contains(np) ) {
                        if (activeNeighbours == 2 || activeNeighbours == 3)
                            newActivePoints.add(np);
                    } else {
                        if ( activeNeighbours == 3 )
                            newActivePoints.add(np);
                    }
                }
            }
            activePoints = newActivePoints;
        }
        return activePoints.size();
    }
    static long getActiveNeighbours3d(Point point, HashSet<Point> activePoints) {
        return point.getAllNeighbours3d().stream().filter(activePoints::contains).count();
    }
    static long getActiveNeighbours4d(Point point, HashSet<Point> activePoints) {
        return point.getAllNeighbours4d().stream().filter(activePoints::contains).count();
    }

    static long solveB(List<String> values) {
        HashSet<Point> activePoints = new HashSet<>();
        for(int y=0; y< values.size() ; y++) {
            for(int x=0; x< values.get(0).length() ; x++) {
                if(values.get(y).charAt(x)=='#')
                    activePoints.add(new Point(x, y,0, 0));
            }
        }
        for(int round=0; round<6; round++) {
            HashSet<Point> scanned  = new HashSet<>();
            HashSet<Point> newActivePoints  = new HashSet<>();
            for (Point point : activePoints) {
                for(Point np : point.getAllNeighbours4d()) {
                    if(scanned.contains(np)) continue;
                    scanned.add(np);
                    long activeNeighbours = getActiveNeighbours4d(np, activePoints);
                    if( activePoints.contains(np) ) {
                        if (activeNeighbours == 2 || activeNeighbours == 3) newActivePoints.add(np);
                    } else {
                        if ( activeNeighbours == 3 ) newActivePoints.add(np);
                    }
                }
            }
            activePoints = newActivePoints;
        }
        return activePoints.size();
    }


    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //223
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //1884
    }
}
