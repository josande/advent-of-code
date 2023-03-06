package year2019.day11;

import utils.FileHelper;
import utils.OpComputer;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.LinkedList;

public class Day11 {

    //read, run, paint, turn, move


    static LinkedList<Point> paintedAreas=new LinkedList<>();
    static HashSet<Point> whiteAreas=new HashSet<>();
    static int x=0,y=0;
    static int direction=0; //0=up, 1=left, 2=down, 3=right;
    static OpComputer computer;


    static boolean isWhite() {
        return whiteAreas.contains(new Point(x,y));
    }
    static void paint() {
        computer.clearOutput();
        computer.runUntilOutput();
        if (!computer.isRunning()) return;
        if (computer.getOutput() == 0) {
            whiteAreas.remove(new Point(x,y));
        } else if (computer.getOutput() == 1) {
            whiteAreas.add(new Point(x,y));

        } else {
            System.out.println("Something went wrong");
        }
        if(!paintedAreas.contains(new Point(x,y)))
            paintedAreas.add(new Point(x,y));

    }
    static void turn() {
        computer.clearOutput();
        computer.runUntilOutput();
        if (!computer.isRunning()) return;

        if (computer.getOutput() == 0) {
            direction+=3;
            direction%=4;
        } else if (computer.getOutput() == 1) {
            direction+=5;
            direction%=4;
        } else {
            System.out.println("Something went wrong");
        }
    }
    static void move() {
        if (direction==0) {
            y--;
        }
        if (direction==1) {
            x++;
        }
        if (direction==2) {
            y++;
        }
        if (direction==3) {
            x--;
        }
    }
    static void readInput() {
        int input = isWhite() ? 1 : 0;

        computer.addInput(input);
    }
    static int solveA(String input) {
        computer  = new OpComputer(input);
        while(computer.isRunning()) {
            readInput();
            paint();
            turn();
            move();
        }
        return paintedAreas.size();
    }
    public static void main(String[] args) {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        System.out.println("Day11A: "+solveA(inputs.get(0)));

        paintedAreas=new LinkedList<>();
        whiteAreas=new HashSet<>();
        x=0;y=0;
        direction=0;
        computer.reset();

        whiteAreas.add(new Point(0,0));
        while(computer.isRunning()) {
            readInput();
            paint();
            turn();
            move();
        }
        System.out.println("Day11B: ");
        Point.print(whiteAreas);
    }
}
