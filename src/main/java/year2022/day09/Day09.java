package year2022.day09;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day09 {

    static Object solveA(List<String> values) {
        Point head = new Point(0,0);
        Point tail = new Point(0,0);

        HashSet<Point> visited = new HashSet<>();
        for(String s : values) {
            for(int i =0; i < Integer.parseInt(s.split(" ")[1]); i++) {
                switch (s.charAt(0)) {
                    case 'U' -> head = head.north();
                    case 'R' -> head = head.east();
                    case 'D' -> head = head.south();
                    case 'L' -> head = head.west();
                }
                if(!head.getAllNeighbours2d().contains(tail) && !tail.equals(head)) {
                    if(head.getX() == tail.getX()) {
                        if(head.getY() > tail.getY()) {
                            tail = tail.south();
                        } else {
                            tail = tail.north();
                        }
                    }
                    else if(head.getY() == tail.getY()) {
                        if(head.getX() > tail.getX()) {
                            tail = tail.east();
                        } else {
                            tail = tail.west();
                        }
                    } else {
                        if(head.getY() > tail.getY()) {
                            tail = tail.south();
                        } else {
                            tail = tail.north();
                        }
                        if(head.getX() > tail.getX()) {
                            tail = tail.east();
                        } else {
                            tail = tail.west();
                        }
                    }
                }
                visited.add(tail);
            }
        }

        return visited.size();
    }
    static Object solveB(List<String> values) {
        ArrayList<Point> rope = new ArrayList<>();
        for(int i =0; i<10; i++) {
            rope.add(new Point(0,0));
        }

        HashSet<Point> visited = new HashSet<>();
        for(String s : values) {
            for(int i =0; i < Integer.parseInt(s.split(" ")[1]); i++) {
                switch (s.charAt(0)) {
                    case 'U' -> rope.set(0, rope.get(0).north());
                    case 'R' -> rope.set(0, rope.get(0).east());
                    case 'D' -> rope.set(0, rope.get(0).south());
                    case 'L' -> rope.set(0, rope.get(0).west());
                }
                for(int part = 1; part<rope.size(); part++) {
                    if(!rope.get(part-1).getAllNeighbours2d().contains(rope.get(part)) && !rope.get(part).equals(rope.get(part-1))) {
                        if(rope.get(part-1).getX() == rope.get(part).getX()) {
                            if(rope.get(part-1).getY() > rope.get(part).getY()) {
                                rope.set(part, rope.get(part).south());
                            } else {
                                rope.set(part, rope.get(part).north());
                            }
                        }
                        else if(rope.get(part-1).getY() == rope.get(part).getY()) {
                            if(rope.get(part-1).getX() > rope.get(part).getX()) {
                                rope.set(part, rope.get(part).east());
                            } else {
                                rope.set(part, rope.get(part).west());
                            }
                        } else {
                            if(rope.get(part-1).getY() > rope.get(part).getY()) {
                                rope.set(part, rope.get(part).south());
                            } else {
                                rope.set(part, rope.get(part).north());
                            }
                            if(rope.get(part-1).getX() > rope.get(part).getX()) {
                                rope.set(part, rope.get(part).east());
                            } else {
                                rope.set(part, rope.get(part).west());
                            }
                        }
                    }
                }
                visited.add(rope.get(9));
            }
        }
        return visited.size();
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //6269
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
