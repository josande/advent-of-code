package utils;

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
        HashMap<Point,Character> map = new HashMap<>();
        for(int y=0; y < values.size(); y++) {
            for(int x=0; x<values.get(y).length(); x++) {
                map.put(new Point(x, y), values.get(y).charAt(x));
            }
        }
        return map;
    }
    public static void print(HashMap<Point, Character> points) {
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
            if(maxZ>0)
                System.out.println("z="+z);
            for (int y = minY - 1; y <= maxY; y++) {
                for (int x = minX - 1; x <= maxX; x++) {
                    System.out.print(points.getOrDefault(new Point(x, y, z), ' '));
                }
                System.out.print("\n");
            }
        }
    }
}
