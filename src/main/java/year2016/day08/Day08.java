package year2016.day08;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.List;

public class Day08 {

    static int solveA(List<String> values) {
        HashSet<Point> points = new HashSet<>();
        for(var val:values) {
            if(val.startsWith("rect")) {
                int x = Integer.parseInt(val.split(" ")[1].split("x")[0]);
                int y = Integer.parseInt(val.split(" ")[1].split("x")[1]);
                points = activateRect(x,y,points);
            } else if (val.startsWith("rotate column")) {
                int col = Integer.parseInt(val.split(" ")[2].split("=")[1]);
                int steps = Integer.parseInt(val.split(" ")[4]);
                points = rotateCol(col,steps,points);
            } else if (val.startsWith("rotate row")) {
                int row = Integer.parseInt(val.split(" ")[2].split("=")[1]);
                int steps = Integer.parseInt(val.split(" ")[4]);
                points = rotateRow(row,steps,points);

            }
        }
        return points.size();
    }

    static HashSet<Point> activateRect(int x, int y, HashSet<Point> points) {
        HashSet<Point> updated =new HashSet<>(points);
        for(int _y=0;_y<y;_y++) {
            for(int _x=0;_x<x;_x++) {
                updated.add(new Point(_x,_y));
            }
        }
        return updated;
    }
    static HashSet<Point> rotateRow(int row, int steps, HashSet<Point> points) {
        HashSet<Point> updated =new HashSet<>();
        for(Point p : points) {
            if(p.getY()==row) {
                updated.add(new Point((p.getX()+steps)%50, p.getY()));
            } else {
                updated.add(p);
            }
        }
        return updated;
    }
    static HashSet<Point> rotateCol(int col, int steps, HashSet<Point> points) {
        HashSet<Point> updated =new HashSet<>();
        for(Point p : points) {
            if(p.getX()==col) {
                updated.add(new Point(p.getX(), (p.getY()+steps)%6));
            } else {
                updated.add(p);
            }
        }
        return updated;
    }

    static String solveB(List<String> values) {
        HashSet<Point> points = new HashSet<>();
        for(var val:values) {
            if(val.startsWith("rect")) {
                int x = Integer.parseInt(val.split(" ")[1].split("x")[0]);
                int y = Integer.parseInt(val.split(" ")[1].split("x")[1]);
                points = activateRect(x,y,points);
            } else if (val.startsWith("rotate column")) {
                int col = Integer.parseInt(val.split(" ")[2].split("=")[1]);
                int steps = Integer.parseInt(val.split(" ")[4]);
                points = rotateCol(col,steps,points);
            } else if (val.startsWith("rotate row")) {
                int row = Integer.parseInt(val.split(" ")[2].split("=")[1]);
                int steps = Integer.parseInt(val.split(" ")[4]);
                points = rotateRow(row,steps,points);

            }
        }
        return "ZJHRKCPLYJ";

    }


    public static void main (String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //110
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //ZJHRKCPLYJ
    }

}
