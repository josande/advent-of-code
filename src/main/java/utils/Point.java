package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Point {
    private int x, y, z;
    public Point(int x, int y) {
        this.x=x;
        this.y=y;
        this.z=0;
    }
    public Point(int x, int y, int z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public Point north() {
        return new Point(x,y-1);
    }
    public Point east() {
        return new Point(x+1,y);
    }
    public Point south() {
        return new Point(x,y+1);
    }
    public Point west() {
        return new Point(x-1,y);
    }
    public int getManhattanDistance(Point point) {
        return Math.abs(x-point.getX()) +
                Math.abs(y-point.getY()) +
                Math.abs(z-point.getZ());
    }
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Point)) {
            return false;
        }

        Point o = (Point) obj;
        return this.getX() == o.getX() &&
                this.getY() == o.getY() &&
                this.getZ() == o.getZ();
    }
    @Override
    public int hashCode() {
        return x*37+y*11-z*133+12;
    }


    public static void print(Collection<Point> points) {
        int maxX= Integer.MIN_VALUE,
                maxY= Integer.MIN_VALUE,
                minX= Integer.MAX_VALUE,
                minY= Integer.MAX_VALUE;
        for ( Point p : points ) {
            maxX=Math.max(maxX, p.getX());
            maxY=Math.max(maxY, p.getY());
            minX=Math.min(minX, p.getX());
            minY=Math.min(minX, p.getY());
        }
        for (int y=minY-1;y<=maxY;y++) {
            for (int x=minX-1;x<=maxX;x++) {
                if (points.contains(new Point(x,y))) {
                    System.out.print("#");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
    public static void print(HashMap<Point, Character> points) {
        int maxX= Integer.MIN_VALUE,
                maxY= Integer.MIN_VALUE,
                minX= Integer.MAX_VALUE,
                minY= Integer.MAX_VALUE;
        for ( Point p : points.keySet() ) {
            maxX=Math.max(maxX, p.getX());
            maxY=Math.max(maxY, p.getY());
            minX=Math.min(minX, p.getX());
            minY=Math.min(minY, p.getY());
        }

        for (int y=minY-1;y<=maxY;y++) {
            for (int x=minX-1;x<=maxX;x++) {
                System.out.print(points.getOrDefault(new Point(x,y), ' ' ));
            }
            System.out.print("\n");
        }
    }

}
