package year2016.day02;

import utils.FileHelper;
import utils.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {

    static String solveA(List<String> values) {
        String result = "";
        int x=1, y=1;

        for( var value : values ) {
            for (char c : value.toCharArray()) {
                if ( c== 'U') {y=Math.max(0, --y);}
                if ( c== 'D') {y=Math.min(2, ++y);}
                if ( c== 'L') {x=Math.max(0, --x);}
                if ( c== 'R') {x=Math.min(2, ++x);}

            }
            result+= (y*3+x+1);
        }
        return result;
    }

    static String solveB(List<String> values) {
        String result = "";
        int x=0, y=2;
        Point current = new Point(x,y);
        for( var value : values ) {
            for (char c : value.toCharArray()) {
                x= current.getX();
                y= current.getY();
                if ( c== 'U') {y=current.getY()-1;}
                if ( c== 'D') {y=current.getY()+1;}
                if ( c== 'L') {x=current.getX()-1;}
                if ( c== 'R') {x=current.getX()+1;}
                if(x<0 || x>4 || y<0 || y>4) continue;
                Point newPoint = new Point(x,y);
                if(getValue(newPoint)!= ' ') {
                    current=newPoint;

                }
            }

            result+= getValue(current);
        }

        return result;
    }
    private static char getValue(Point p) {
        String keypad=
                "  1  \n" +
                " 234 \n" +
                "56789\n" +
                " ABC \n" +
                "  D  \n";
        List<String> values = Arrays.stream(keypad.split("\n")).collect(Collectors.toList());
        return values.get(p.getY()).charAt(p.getX());
    }

    public static void main(String[] args){
        var day = "Day02";

        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = System.currentTimeMillis()-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //36629
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //99C3D
    }
}
