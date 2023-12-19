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
        Point current = new Point(0,0);

        ArrayList<Wall> walls = new ArrayList<>();

        long wallLength = 0L;
        for (String line : input) {
            String len = line.split(" ")[1];
            String dir = line.split(" ")[0];

            int length = Integer.parseInt(len);
            wallLength += length;

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
        long filling = areaBetweenWalls(walls);
        return wallLength+filling;
    }

    @Override
    public Object solveB(List<String> input) {

        Point current = new Point(0,0);

        ArrayList<Wall> walls = new ArrayList<>();

        long wallLength = 0L;
        for (String line : input) {
            String len = line.split(" ")[2].substring(2,7);
            String dir = line.split(" ")[2].substring(7,8);

            int length = Integer.parseInt(len,16);
            wallLength += length;
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

        long filling = areaBetweenWalls(walls);

        return filling + wallLength;
    }

    private long areaBetweenWalls(ArrayList<Wall> walls) {
        var verticalWalls = walls.stream().filter(w -> w.p1.getX() == w.p2.getX()).collect(Collectors.toList());
        var horizontalWalls = walls.stream().filter(w -> w.p1.getY() == w.p2.getY()).collect(Collectors.toList());
        Collections.sort(verticalWalls);

        long result = 0L;
        List<Integer> interestingPoints = horizontalWalls.stream().map(w -> w.p1.getY()).distinct().sorted().toList();

        //check between interesting points
        for (int y=0; y<interestingPoints.size()-1; y++) {
            if(interestingPoints.get(y+1)==interestingPoints.get(y)+1) continue;
            List<Integer> collidingPoints = getAllWalls(interestingPoints.get(y)+1, verticalWalls);
            int distance = 0;
            for(int i=1; i<=collidingPoints.size(); i=i+2) {
                distance += (collidingPoints.get(i)-collidingPoints.get(i-1)-1);
            }
            int height = (interestingPoints.get(y+1)-interestingPoints.get(y)-1);
            result+=(long) distance*height;
        }
        //check actual I.P.
        for (int y : interestingPoints) {
            long counter=0L;
            int lastX=-1;
            boolean count =false;
            List<Integer> collidingPoints = getAllWalls(y, verticalWalls);
            boolean isFalseWall = false;
            for(int x : collidingPoints) {
                if(x==lastX) continue;
                if(count && !isFalseWall) {
                    counter+=(long) (x-lastX)-1L;
                }

                Integer tmpX = isRealWall(new Point(x,y), horizontalWalls, verticalWalls);
                if( tmpX != null) {
                    count=!count;
                    lastX=tmpX;
                } else {
                    isFalseWall = !isFalseWall;
                    lastX=x;
                }
            }
            result+=counter;

        }

        return result;
    }
    Integer isRealWall(Point point, List<Wall> horizontalWalls, List<Wall> verticalWalls) {
        Collections.sort(verticalWalls);
        Collections.sort(horizontalWalls);
        Optional<Wall> oWall = horizontalWalls.stream().filter(hw->hw.p1.equals(point) || hw.p2.equals(point)).findAny();
        if(oWall.isEmpty()) return point.getX();

        Wall v1 = verticalWalls.stream().filter(w -> w.p1.equals(oWall.get().p1) || w.p2.equals(oWall.get().p1)).findAny().orElseThrow();
        Wall v2 = verticalWalls.stream().filter(w -> w.p1.equals(oWall.get().p2) || w.p2.equals(oWall.get().p2)).findAny().orElseThrow();
        if ((Math.max(v1.p1.getY(), v1.p2.getY()) > point.getY()) != (Math.max(v2.p1.getY(), v2.p2.getY()) > point.getY())) {
            return oWall.get().p2.getX();
        }
        return null;
    }

    private List<Integer> getAllWalls(int y, List<Wall> walls) {
        List<Integer> collisions = new ArrayList<>();
        for ( Wall w : walls) {
            if(w.p1.getY()<=y && w.p2.getY()>=y)
                collisions.add(w.p1.getX());
        }
        return collisions;
    }
}
