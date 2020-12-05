package year2016.day01;

import utils.FileHelper;
import utils.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day01 {

    static int solveA(List<String> values) {
        int x=0, y=0, direction=0;
        for (var value : values) {
            for ( var part : value.split(", ") ) {
                if( part.charAt(0)=='L' ) direction--;
                if( part.charAt(0)=='R' ) direction++;
                direction = (direction+4)%4;
                switch ( direction ) {
                    case 0 : y += Integer.parseInt(part.substring(1)); break;
                    case 1 : x += Integer.parseInt(part.substring(1)); break;
                    case 2 : y -= Integer.parseInt(part.substring(1)); break;
                    case 3 : x -= Integer.parseInt(part.substring(1)); break;
                }
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    static int solveB(List<String> values) {
        int x=0, y=0, direction=0;
        var visited = new HashSet<Point>();
        for (var value : values) {
            for ( var part : value.split(", ") ) {
                if( part.charAt(0)=='L' ) direction--;
                if( part.charAt(0)=='R' ) direction++;
                direction = (direction+4)%4;
                int length = Integer.parseInt(part.substring(1));
                for(int i=0; i<length; i++) {
                    switch ( direction ) {
                        case 0 : y++; break;
                        case 1 : x++; break;
                        case 2 : y--; break;
                        case 3 : x--; break;
                    }
                    Point p = new Point(x,y);
                    if(visited.contains(p)) return Math.abs(x) + Math.abs(y);
                    visited.add(p);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        var day = "Day01";

        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = System.currentTimeMillis()-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //250
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //151
    }
}
