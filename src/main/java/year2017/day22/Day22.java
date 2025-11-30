package year2017.day22;

import utils.*;

import java.util.HashSet;
import java.util.List;

public class Day22 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day22());
    }

    @Override
    public Object solveA(List<String> input) {
        int size= input.size();
        Point start = new Point(size/2, size/2);
        Direction direction = Direction.NORTH;

        HashSet<Point> infected = new HashSet<>();
        for(int y=0; y<size; y++) {
            for(int x=0; x<size; x++) {
                if(input.get(y).charAt(x)=='#') infected.add(new Point(x,y));
            }
        }
        int bursts=10000;
        Point current = start;
        int infectionCounter=0;
        for(int iteration =0; iteration<bursts; iteration++) {
            if(infected.contains(current)) {
                direction = direction.right();
                infected.remove(current);
            }
            else {
                direction = direction.left();
                infected.add(current);
                infectionCounter++;
            }
            current = current.getNext(direction);
        }

        return infectionCounter;
    }

    @Override
    public Object solveB(List<String> input) {
        int size= input.size();
        Point start = new Point(size/2, size/2);
        Direction direction = Direction.NORTH;

        HashSet<Point> infected = new HashSet<>();
        HashSet<Point> weakened = new HashSet<>();
        HashSet<Point> flagged = new HashSet<>();

        for(int y=0; y<size; y++) {
            for(int x=0; x<size; x++) {
                if(input.get(y).charAt(x)=='#') infected.add(new Point(x,y));
            }
        }
        int bursts=10000000;
        Point current = start;
        int infectionCounter=0;
        for(int iteration =0; iteration<bursts; iteration++) {

            if(infected.contains(current)) {
                infected.remove(current);
                flagged.add(current);
                direction = direction.right();
            } else if (weakened.contains(current)) {
                weakened.remove(current);
                infected.add(current);
                infectionCounter++;
            } else if (flagged.contains(current)) {
                flagged.remove(current);
                direction = direction.left().left();
            } else {
                weakened.add(current);
                direction = direction.left();
            }
            current = current.getNext(direction);
        }

        return infectionCounter;
    }
}
