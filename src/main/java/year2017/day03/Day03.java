package year2017.day03;

import utils.AdventOfCode;
import utils.Point;
import utils.Reporter;

import java.util.HashMap;
import java.util.List;

public class Day03 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day03());
    }

    @Override
    public Object solveA(List<String> input) {
        int data = Integer.parseInt(input.get(0));
        int ring=1;
        int ringSide;

        for(; ;ring++) {
            ringSide = 2*ring-1;
            if(data <= ringSide*ringSide)
                break;
        }

        int leftOfLastLap = ringSide*ringSide-data;
        if(leftOfLastLap>0)
            leftOfLastLap = leftOfLastLap%(ringSide-1);

        if(leftOfLastLap>(ringSide-1)/2) {
            leftOfLastLap+=leftOfLastLap-(ringSide-1)/2;
        }

        return 2*ring-2-leftOfLastLap;


    }

    @Override
    public Object solveB(List<String> input) {
        int data = Integer.parseInt(input.get(0));

        HashMap<Point, Integer> map = new HashMap<>();
        int ring=1;
        Point current = new Point(0,0);
        map.put(current,1);
        for(;;ring++) {
            current=current.east();
            current=current.south();
            for(int step = 0; step<2*ring;step++) {
                current = current.north();
                int value=0;
                for(Point near : current.getAllNeighbours2d() ) {
                    value+=map.getOrDefault(near, 0);
                }
                if(value>data) return value;
                map.put(current, value);
            }
            for(int step = 0; step<2*ring;step++) {
                current = current.west();
                int value=0;
                for(Point near : current.getAllNeighbours2d() ) {
                    value+=map.getOrDefault(near, 0);
                }
                if(value>data) return value;
                map.put(current, value);
            }
            for(int step = 0; step<2*ring;step++) {
                current = current.south();
                int value=0;
                for(Point near : current.getAllNeighbours2d() ) {
                    value+=map.getOrDefault(near, 0);
                }
                if(value>data) return value;
                map.put(current, value);
            }
            for(int step = 0; step<2*ring;step++) {
                current = current.east();
                int value=0;
                for(Point near : current.getAllNeighbours2d() ) {
                    value+=map.getOrDefault(near, 0);
                }
                if(value>data) return value;
                map.put(current, value);
            }
        }
    }
}
