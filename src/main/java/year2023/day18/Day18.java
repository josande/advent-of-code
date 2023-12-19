package year2023.day18;

import utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class Day18 implements AdventOfCode {
    record Wall(Point p1, Point p2) implements Comparable<Wall> {
        @Override
        public int compareTo(Wall o) {
            if(p1().getX()-o.p1.getX() != 0)
                return p1().getX()-o.p1.getX();
            return p1().getY()-o.p1.getY();
        }
    }

    public static void main(String[] args){
        Reporter.report(new Day18());
    }

    @Override
    public Object solveA(List<String> input) {
        HashSet<Point> boarder = new HashSet<>();

        Point start = new Point(0,0);
        boarder.add(start);
        Point current = start;

        ArrayList<Wall> walls = new ArrayList<>();

        for (String line : input) {
            String len = line.split(" ")[1];
            String dir = line.split(" ")[0];

            int length = Integer.parseInt(len,16);

            Point endPoint  = switch (dir) {
                case "R" -> new Point(current.getX()+length, current.getY());
                case "D" -> new Point(current.getX(), current.getY()+length);
                case "L" -> new Point(current.getX()-length, current.getY());
                case "U" -> new Point(current.getX(), current.getY()-length);
                default -> throw new IllegalArgumentException(dir);
            };

            if(current.getX()+current.getY()<(endPoint.getX() + endPoint.getY())) {
                walls.add(new Wall(current, endPoint));
            } else {
                walls.add(new Wall(endPoint, current));
            }
            current=endPoint;
        }

        long wallLength = lengthOfWalls(walls);
        long filling = areaBetweenWalls(walls);

        System.out.println(wallLength);
        System.out.println(filling);
return wallLength+filling;
        // for (String line : input) {
       //     String[] parts = line.split(" ");
       //     Direction direction = switch (parts[0]) {
       //         case "R" -> Direction.EAST;
       //         case "D" -> Direction.SOUTH;
       //         case "L" -> Direction.WEST;
       //         case "U" -> Direction.NORTH;
       //         default -> throw new IllegalArgumentException(parts[1]);
       //     };
       //     int length = Integer.parseInt(parts[1]);
//
       //     for(int i=0; i<length; i++) {
       //         current = current.getNext(direction);
       //         boarder.add(current);
       //     }
       // }

       // return floodFill(boarder)+boarder.size();
    }
    private Integer floodFill(HashSet<Point> border) {

        int maxX = MapUtil.getMaxX(border);
        int maxY = MapUtil.getMaxY(border);
        int minX = MapUtil.getMinX(border);
        int minY = MapUtil.getMinY(border);

        int fillCounter=0;
        HashSet<Point> p2 = new HashSet<>();
        for ( int y=minY+1; y<maxY; y++) {
            boolean fill = false;
            HashSet<Point> section = new HashSet<>();
            for ( int x=minX; x<maxX; x++) {
                Point p = new Point(x,y);
                if (border.contains(p)) {
                    section.add(p);
                } else if(!section.isEmpty()) {
                    boolean north=false, south=false;
                    for (Point s : section) {
                        if(border.contains(s.north())) north=true;
                        if(border.contains(s.south())) south=true;
                    }
                    section.clear();;
                    if(north && south) {
                        fill = !fill;
                    }
                }
                if (fill && !border.contains(p)){
                    p2.add(p);
                    fillCounter++;
                }
            }
        }
        return fillCounter;
    }
 //8936270059 to low
    @Override
    public Object solveB(List<String> input) {
        HashSet<Point> boarder = new HashSet<>();

        Point start = new Point(0,0);
        boarder.add(start);
        Point current = start;

        ArrayList<Wall> walls = new ArrayList<>();

        for (String line : input) {
            String len = line.split(" ")[2].substring(2,7);
            String dir = line.split(" ")[2].substring(7,8);

            int length = Integer.parseInt(len,16);

            Point endPoint  = switch (dir) {
                case "0" -> new Point(current.getX()+length, current.getY());
                case "1" -> new Point(current.getX(), current.getY()+length);
                case "2" -> new Point(current.getX()-length, current.getY());
                case "3" -> new Point(current.getX(), current.getY()-length);
                default -> throw new IllegalArgumentException(dir);
            };

            if(current.getX()+current.getY()<(endPoint.getX() + endPoint.getY())) {
                walls.add(new Wall(current, endPoint));
            } else {
                walls.add(new Wall(endPoint, current));
            }
            current=endPoint;
        }
        for(Wall w : walls) System.out.println(w);
        long wallLength = lengthOfWalls(walls);
        long filling = areaBetweenWalls(walls);

        return filling + wallLength;
    }
    private long lengthOfWalls(ArrayList<Wall> walls) {
        Long length =0L;
        for (Wall w : walls) {
            length+=w.p1.getManhattanDistance(w.p2);
        }
        return length;
    }
    private long areaBetweenWalls(ArrayList<Wall> walls) {
        var verticalWalls = walls.stream().filter(w -> w.p1.getX() == w.p2.getX()).collect(Collectors.toList());
        Collections.sort(verticalWalls);

        List<Integer> interestingPoints = verticalWalls.stream().map(w ->w.p1.getY()).collect(Collectors.toList());
        interestingPoints.addAll(verticalWalls.stream().map(w ->w.p2.getY()).collect(Collectors.toList()));
        interestingPoints = interestingPoints.stream().distinct().sorted().collect(Collectors.toList());
        Long result = 0L;

        //check between interesting points
        for (int y=0; y<interestingPoints.size()-1; y++) {
            List<Integer> collidingPoints = getAllWalls(interestingPoints.get(y)+1, verticalWalls);
            int distance = 0;
            for(int i=1; i<collidingPoints.size(); i=i+2) {
                distance += (collidingPoints.get(i)-collidingPoints.get(i-1)-1);
            }
            int height = (interestingPoints.get(y+1)-interestingPoints.get(y)-1);
            result+=distance*height;
        }
        //check actual I.P.
        for (int y=0; y<interestingPoints.size(); y++) {
            System.out.print("unclear point: "+interestingPoints.get(y));
            List<Integer> collidingPoints = getAll Walls(interestingPoints.get(y)+1, verticalWalls);
            System.out.println(" collisons:"+collidingPoints.size());






        }
        return result;
    }
    private List<Integer> getAllWalls(int y, List<Wall> walls) {
       // System.out.println("\ny="+y);
        List<Integer> collisions = new ArrayList<>();
        for ( Wall w : walls) {
         //   System.out.println(w.p1.getY()+" - "+w.p2.getY()+" "+(w.p1.getY()<=y && w.p2.getY()>=y));
            if(w.p1.getY()<y && w.p2.getY()>y)
                collisions.add(w.p1.getX());
        }
        return collisions;
    }
}
