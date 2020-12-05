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
}
