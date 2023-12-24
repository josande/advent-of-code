package utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueReversed(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
    public static HashMap<Character, Integer> getLetterOccurrencesMap(Collection<String> strings) {
        HashMap<Character, Integer> map = new HashMap<>();
        for ( String string : strings ) {
            for (char c : string.toCharArray()) {
                if (c == ' ') continue;
                map.put(c, 1 + map.getOrDefault(c, 0));
            }
        }
        return map;
    }

    public static HashMap<Character, Integer> getLetterOccurrencesMap(String string) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : string.toCharArray()) {
            if (c == ' ') continue;
            map.put(c, 1+map.getOrDefault(c, 0));
        }
        return map;
    }

    public static HashMap<Point,Character> asMap(List<String> values) {
        HashMap<Point, Character> map = new HashMap<>();
        for(int y=0; y < values.size(); y++) {
            for(int x=0; x<values.get(y).length(); x++) {
                map.put(new Point(x, y), values.get(y).charAt(x));
            }
        }
        return map;
    }
    public static void print(HashMap<Point, Character> points) {
        int minX = getMinX(points);
        int maxX = getMaxX(points);
        int minY = getMinY(points);
        int maxY = getMaxY(points);
        int minZ = getMinZ(points);
        int maxZ = getMaxZ(points);
        for (int z=minZ;z<=maxZ;z++) {
            if(maxZ>0)
                System.out.println("z="+z);
            for (int y = minY - 1; y <= maxY+1; y++) {
                System.out.print(y+"\t");
                for (int x = minX - 1; x <= maxX+1; x++) {
                    System.out.print(points.getOrDefault(new Point(x, y, z), ' '));
                }
                System.out.print("\n");
            }
        }
    }
    public static void print(Collection<Point> points) {
        int minX = getMinX(points);
        int maxX = getMaxX(points);
        int minY = getMinY(points);
        int maxY = getMaxY(points);
        int minZ = getMinZ(points);
        int maxZ = getMaxZ(points);
        for (int z=minZ;z<=maxZ;z++) {
            if(maxZ>0)
                System.out.println("z="+z);
            for (int y = minY - 1; y <= maxY; y++) {
                System.out.print(StringUtils.leftPad(""+y, 5)+"\t");
                for (int x = minX - 1; x <= maxX; x++) {
                    if(points.contains(new Point(x,y,z))) {
                        System.out.print('#');
                    } else {
                        System.out.print(' ');
                    }
                }
                System.out.print("\n");
            }
        }
    }
    public static void print(ArrayList<Pair<Point, Point>> walls) {
        HashMap<Point, Character> map = new HashMap<>();
        for(Pair<Point, Point> p : walls) {
            if(p.getLeft().getX() == p.getRight().getX()) {
                for(int y=Math.min(p.getLeft().getY(), p.getRight().getY())+1; y<Math.max(p.getLeft().getY(), p.getRight().getY()); y++) {
                    Point newPoint = new Point(p.getLeft().getX(),y);
                    if(map.containsKey(newPoint)) {
                        if(map.get(newPoint) == '-')
                            map.put(newPoint, '+' );
                    } else {
                        map.put(newPoint, '|');
                    }
                }
            } else if (p.getLeft().getY() == p.getRight().getY()) {
                for(int x=Math.min(p.getLeft().getX(), p.getRight().getX())+1; x<Math.max(p.getLeft().getX(), p.getRight().getX()); x++) {
                    Point newPoint = new Point(x, p.getLeft().getY());
                    if(map.containsKey(newPoint)) {
                        if(map.get(newPoint) == '|')
                           map.put(newPoint, '+' );
                    } else {
                        map.put(newPoint, '-');
                    }
                }
            }
            map.put(p.getLeft(), '#');
            map.put(p.getRight(), '#');

        }
        print(map);

    }
    public static int getMaxX(HashMap<Point, ?> points) {
        if(points.isEmpty()) return 0;
        int maxX=Integer.MIN_VALUE;
        for ( Point p : points.keySet() ) {
            maxX=Math.max(maxX, p.getX());
        }
        return maxX;
    }
    public static int getMinX(HashMap<Point, ?> points) {
        if(points.isEmpty()) return 0;
        int minX=Integer.MAX_VALUE;
        for ( Point p : points.keySet() ) {
            minX=Math.min(minX, p.getX());
        }
        return minX;
    }
    public static int getMaxY(HashMap<Point, ?> points) {
        if(points.isEmpty()) return 0;
        int maxY=Integer.MIN_VALUE;
        for ( Point p : points.keySet() ) {
            maxY=Math.max(maxY, p.getY());
        }
        return maxY;
    }
    public static int getMinY(HashMap<Point, ?> points) {
        if(points.isEmpty()) return 0;
        int minY=Integer.MAX_VALUE;
        for ( Point p : points.keySet() ) {
            minY=Math.min(minY, p.getY());
        }
        return minY;
    }
    public static int getMaxZ(HashMap<Point, ?> points) {
        if(points.isEmpty()) return 0;
        int maxZ=Integer.MIN_VALUE;
        for ( Point p : points.keySet() ) {
            maxZ=Math.max(maxZ, p.getZ());
        }
        return maxZ;
    }
    public static int getMinZ(HashMap<Point, ?> points) {
        if(points.isEmpty()) return 0;
        int minZ=Integer.MAX_VALUE;
        for ( Point p : points.keySet() ) {
            minZ=Math.min(minZ, p.getZ());
        }
        return minZ;
    }

    public static int getMaxX(Collection<Point> points) {
        if(points.isEmpty()) return 0;
        int maxX=Integer.MIN_VALUE;
        for ( Point p : points ) {
            maxX=Math.max(maxX, p.getX());
        }
        return maxX;
    }
    public static int getMinX(Collection<Point> points) {
        if(points.isEmpty()) return 0;
        int minX=Integer.MAX_VALUE;
        for ( Point p : points ) {
            minX=Math.min(minX, p.getX());
        }
        return minX;
    }
    public static int getMaxY(Collection<Point> points) {
        if(points.isEmpty()) return 0;
        int maxY=Integer.MIN_VALUE;
        for ( Point p : points ) {
            maxY=Math.max(maxY, p.getY());
        }
        return maxY;
    }
    public static int getMinY(Collection<Point> points) {
        if(points.isEmpty()) return 0;
        int minY=Integer.MAX_VALUE;
        for ( Point p : points ) {
            minY=Math.min(minY, p.getY());
        }
        return minY;
    }
    public static int getMaxZ(Collection<Point> points) {
        if(points.isEmpty()) return 0;
        int maxZ=Integer.MIN_VALUE;
        for ( Point p : points) {
            maxZ=Math.max(maxZ, p.getZ());
        }
        return maxZ;
    }
    public static int getMinZ(Collection<Point> points) {
        if(points.isEmpty()) return 0;
        int minZ=Integer.MAX_VALUE;
        for ( Point p : points ) {
            minZ=Math.min(minZ, p.getZ());
        }
        return minZ;
    }

    public static Collection<Point> getPointsAtManhattanDistance(Point p, int distance) {
        HashSet<Point> points = new HashSet<>();

        for(int i=0; i<=distance; i++) {
            points.add(new Point(p.getX()-distance+i, p.getY()+i));
            points.add(new Point(p.getX()-distance+i, p.getY()-i));
            points.add(new Point(p.getX()+distance-i, p.getY()+i));
            points.add(new Point(p.getX()+distance-i, p.getY()-i));
        }
        return points;

    }
    public static Collection<Point> getPointsAtManhattanDistance(Point p, int distance, int minX, int maxX, int minY, int maxY) {
        HashSet<Point> points = new HashSet<>();

        for(int i=0; i<=distance; i++) {
            if(p.getX()-distance+i >=minX && p.getX()-distance+i <=maxX && p.getY()+i >= minY && p.getY()+i <= maxY)
                points.add(new Point(p.getX()-distance+i, p.getY()+i));
            if(p.getX()-distance+i >=minX && p.getX()-distance+i <=maxX && p.getY()-i >= minY && p.getY()-i <= maxY)
                points.add(new Point(p.getX()-distance+i, p.getY()-i));
            if(p.getX()+distance-i >=minX && p.getX()+distance-i <=maxX && p.getY()+i >= minY && p.getY()+i <= maxY)
                points.add(new Point(p.getX()+distance-i, p.getY()+i));
            if(p.getX()+distance-i >=minX && p.getX()-distance+i <=maxX && p.getY()-i >= minY && p.getY()-i <= maxY)
                points.add(new Point(p.getX()+distance-i, p.getY()-i));
        }
        return points;

    }

    public static HashMap<Point, Character> rotateClockWise(HashMap<Point, Character> map) {
        int maxX = getMaxX(map);

        HashMap<Point, Character> rotated = new HashMap<>();
        for(Map.Entry<Point, Character> entry : map.entrySet()) {
            rotated.put(new Point(maxX-entry.getKey().getY(),entry.getKey().getX()), entry.getValue());
        }
        // (00,00) -> (10,00)
        // (00,10) -> (00,00)
        // (10,0)  -> (10,10)
        // (10,10) -> (00,10)
        return rotated;
    }
    public static HashMap<Point, Character> rotateCounterClockWise(HashMap<Point, Character> map) {
        int maxY = getMaxY(map);

        HashMap<Point, Character> rotated = new HashMap<>();
        for(Map.Entry<Point, Character> entry : map.entrySet()) {
            rotated.put(new Point(entry.getKey().getY(), maxY-entry.getKey().getX()), entry.getValue());
        }
        // (00,00) -> (10,00)
        // (00,10) -> (00,00)
        // (10,0)  -> (10,10)
        // (10,10) -> (00,10)
        return rotated;
    }
    public static HashMap<Point, Character> flipHorizontal(HashMap<Point, Character> map) {
        int maxX = getMaxX(map);

        HashMap<Point, Character> flipped = new HashMap<>();
        for(Map.Entry<Point, Character> entry : map.entrySet()) {
            flipped.put(new Point(maxX-entry.getKey().getX(), entry.getKey().getY()), entry.getValue());
        }
        // (00,00) -> (00,00)
        // (00,10) -> (01,00)
        // (10,00) -> (00,10)
        // (10,10) -> (10,10)
        return flipped;
    }
    public static HashMap<Point, Character> flipVertical(HashMap<Point, Character> map) {
        int maxY = getMaxY(map);

        HashMap<Point, Character> flipped = new HashMap<>();
        for(Map.Entry<Point, Character> entry : map.entrySet()) {
            flipped.put(new Point(entry.getKey().getX(), maxY-entry.getKey().getY()), entry.getValue());
        }
        // (00,00) -> (10,10)
        // (00,10) -> (10,00)
        // (10,00) -> (00,10)
        // (10,10) -> (00,00)
        return flipped;
    }

    public static HashMap<Point, Character> getNewMap(int maxX, int maxY, Character defaultChar) {
        HashMap<Point, Character> newMap = new HashMap<>();
        for(int y=0; y<=maxY; y++) {
            for(int x=0; x<=maxX; x++) {
                newMap.put(new Point(x,y), defaultChar);
            }
        }
        return newMap;
    }
}
