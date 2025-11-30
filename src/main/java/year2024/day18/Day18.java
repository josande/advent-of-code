package year2024.day18;

import lombok.Data;
import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.*;

@Data
public class Day18 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day18());
    }

    private int roomSize=70;
    private int steps=1024;
    @Override
    public Object solveA(List<String> input) {

        HashMap<Point, Character> map = new HashMap<>();

        for(int y=0;y<=roomSize;y++){
            for(int x=0;x<=roomSize;x++){
                map.put(new Point(x,y), '.');
            }
        }
        for(int i=0; i<steps; i++){
            map.put(new Point(input.get(i)), '#');
        }

        Point start = new Point(0,0);
        Point end = new Point(roomSize, roomSize);

        return  MapUtil.getShortestPath2D(map, start, end).size()-1;
    }



    @Override
    public Object solveB(List<String> input) {

        HashMap<Point, Character> map = new HashMap<>();
        Point start = new Point(0, 0);
        Point end = new Point(roomSize, roomSize);

        for (int y = 0; y <= roomSize; y++) {
            for (int x = 0; x <= roomSize; x++) {
                map.put(new Point(x, y), '.');
            }
        }
        for(int i=0; i<steps; i++){
            map.put(new Point(input.get(i)), '#');
        }
        var path = MapUtil.getShortestPath2D(map, start, end);
        for (int step = steps;  ; step++) {
            Point nextpoint = new Point(input.get(step));
            map.put(nextpoint, '#');
            if (path.contains(nextpoint)) {
                try {
                    path = MapUtil.getShortestPath2D(map, start, end);
                } catch (IllegalStateException e) {
                    return input.get(step);
                }
            }
        }
    }
}
