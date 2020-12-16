package year2020.day12;

import utils.FileHelper;
import java.util.List;

public class Day12 {


    static int solveA(List<String> values) {
        int x=0, y=0, direction=1;

        for (var v : values) {
            switch (v.charAt(0)) {
                case 'N' -> y-=Integer.parseInt(v.substring(1));
                case 'E' -> x+=Integer.parseInt(v.substring(1));
                case 'S' -> y+=Integer.parseInt(v.substring(1));
                case 'W' -> x-=Integer.parseInt(v.substring(1));
                case 'F' -> {switch (direction) {
                                case 0 -> y-=Integer.parseInt(v.substring(1));
                                case 1 -> x+=Integer.parseInt(v.substring(1));
                                case 2 -> y+=Integer.parseInt(v.substring(1));
                                case 3 -> x-=Integer.parseInt(v.substring(1));
                             }
                }
                case 'L' -> {int degrees = Integer.parseInt(v.substring(1));
                             while (degrees>0) { direction--; degrees-=90;}}
                case 'R' -> {int degrees = Integer.parseInt(v.substring(1));
                             while (degrees>0) { direction++; degrees-=90;}}
            }
             direction=(direction+4)%4;
        }

        return Math.abs(y)+Math.abs(x);
    }



    static int solveB(List<String> values) {

        int shipX=0, shipY=0;
        int wpX=10, wpY=-1;

        for (var v : values) {
            switch (v.charAt(0)) {
                case 'N' -> wpY-=Integer.parseInt(v.substring(1));
                case 'E' -> wpX+=Integer.parseInt(v.substring(1));
                case 'S' -> wpY+=Integer.parseInt(v.substring(1));
                case 'W' -> wpX-=Integer.parseInt(v.substring(1));
                case 'F' -> {
                    shipX+=wpX*Integer.parseInt(v.substring(1));
                    shipY+=wpY*Integer.parseInt(v.substring(1));
                }
                case 'L' -> {int degrees = Integer.parseInt(v.substring(1));
                    while (degrees>0) {
                        int temp = wpY;
                        wpY = -wpX;
                        wpX = temp;
                        degrees-=90;
                    }
                }
                case 'R' -> {
                    int degrees = Integer.parseInt(v.substring(1));
                    while (degrees > 0) {
                        int temp = -wpY;
                        wpY = +wpX;
                        wpX = temp;
                        degrees -= 90;
                    }
                }
            }
        }
        return Math.abs(shipY)+Math.abs(shipX);
    }



    public static void main(String[] args){
        var day = "Day12";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //757
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //51249
    }
}
