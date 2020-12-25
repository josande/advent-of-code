package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Point {
    private int x, y, z, w;
    public Point(int x, int y) {
        this.x=x;
        this.y=y;
        this.z=0;
        this.w=0;
    }
    public Point(int x, int y, int z) {
        this.x=x;
        this.y=y;
        this.z=z;
        this.w=0;
    }
    public Point(int x, int y, int z, int w) {
        this.x=x;
        this.y=y;
        this.z=z;
        this.w=w;
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
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
                Math.abs(z-point.getZ()) +
                Math.abs(w-point.getW());
    }
    @Override
    public String toString() {
        return "("+x+","+y+","+z+","+w+")";
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
                this.getZ() == o.getZ() &&
                this.getW() == o.getW();
    }
    @Override
    public int hashCode() {
        return x*37+y*11-z*133+w*5+12;
    }


    public static void print(Collection<Point> points) {
        int maxX= Integer.MIN_VALUE, maxY= Integer.MIN_VALUE,
            minX= Integer.MAX_VALUE, minY= Integer.MAX_VALUE;
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

    public static void printMap(HashMap<Point, Character> points) {
        int maxX= Integer.MIN_VALUE, maxY= Integer.MIN_VALUE, maxZ= Integer.MIN_VALUE,
            minX= Integer.MAX_VALUE, minY= Integer.MAX_VALUE, minZ= Integer.MAX_VALUE;
        for ( Point p : points.keySet() ) {
            maxX=Math.max(maxX, p.getX());
            maxY=Math.max(maxY, p.getY());
            maxZ=Math.max(maxZ, p.getZ());
            minX=Math.min(minX, p.getX());
            minY=Math.min(minX, p.getY());
            minZ=Math.min(minZ, p.getZ());
        }

        for (int z=minZ;z<=maxZ;z++) {
            System.out.println("z="+z);
            for (int y = minY - 1; y <= maxY; y++) {
                for (int x = minX - 1; x <= maxX; x++) {
                    System.out.print(points.getOrDefault(new Point(x, y, z), ' '));
                }
                System.out.print("\n");
            }
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

    public HashSet<Point> getAllNeighbours2d() {
        HashSet<Point> points = new HashSet<>();
        for(int _x = x-1; _x <= x+1; _x++) {
            for(int _y = y-1; _x <= y+1; _y++) {
                points.add(new Point(_x, _y));
            }
        }
        points.remove(this);
        return points;
    }
    public HashSet<Point> getAllNeighbours3d() {
        HashSet<Point> points = new HashSet<>();
        for(int _x = x-1; _x <= x+1; _x++) {
            for(int _y = y-1; _y <= y+1; _y++) {
                for (int _z = z - 1; _z <= z+1; _z++) {
                    points.add(new Point(_x, _y, _z));
                }
            }
        }
        points.remove(this);
        return points;
    }
    public HashSet<Point> getAllNeighbours4d() {
        HashSet<Point> points = new HashSet<>();
        for(int _x = x-1; _x <= x+1; _x++) {
            for(int _y = y-1; _y <= y+1; _y++) {
                for (int _z = z - 1; _z <= z+1; _z++) {
                    for (int _w = w - 1; _w <= w+1; _w++) {
                        points.add(new Point(_x, _y, _z, _w));
                    }
                }
            }
        }
        points.remove(this);
        return points;
    }
}
