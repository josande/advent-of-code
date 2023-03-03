package year2019.day06;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;

public class Day06 {

    static int calculateCheckSum(HashMap<String, String> orbitMap) {
        int checkSum=0;
        for (String c : orbitMap.keySet()) {
            checkSum+=orbitCount(c, orbitMap);
        }
        return checkSum;
    }
    private static int orbitCount(String str, HashMap<String, String> orbitMap) {
        int count = 0;
        while(orbitMap.containsKey(str)) {
            count++;
            str= orbitMap.get(str);
        }
        return count;
    }

    static int orbitCountBetween(String o1, String o2, HashMap<String,String> orbitMap) {
        HashMap<String, Integer> orbitLevel = new HashMap<>();
        int jumps=0;
        String parent = orbitMap.get(o1);
        while (orbitMap.containsKey(orbitMap.get(parent))) {
            orbitLevel.put(parent, jumps);
            jumps++;
            parent=orbitMap.get(parent);
        }
        parent = orbitMap.get(o2);
        jumps=0;
        while (orbitMap.containsKey(orbitMap.get(parent))) {
            jumps++;
            parent=orbitMap.get(parent);
            if (orbitLevel.containsKey(parent)) {
                return orbitLevel.get(parent)+jumps;
            }
        }
        return 0;
    }

    static HashMap<String,String> makeMap(List<String> orbits) {
        HashMap<String, String> orbitMap = new HashMap<>();
        for (String orbit : orbits) {
            String parentName = orbit.split("\\)")[0];
            String childName = orbit.split("\\)")[1];
            orbitMap.put(childName, parentName);
        }
        return orbitMap;
    }
    public static void main(String[] args) {

        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        HashMap<String,String> orbitMap = makeMap(inputs);

        System.out.println("Day06A " + calculateCheckSum(orbitMap)); // 251208
        System.out.println("Day06B " + orbitCountBetween("SAN", "YOU", orbitMap)); // 397

    }
}
