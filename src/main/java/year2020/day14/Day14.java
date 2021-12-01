package year2020.day14;

import org.apache.commons.lang3.StringUtils;
import utils.FileHelper;

import java.util.*;

public class Day14 {


    static long solveA(List<String> values) {
        String mask=null;
        HashMap<Integer, Long> memory= new HashMap<>();
        for(String row : values ) {
            if(row.startsWith("mask")) {
                mask=row.split("mask = ")[1];
            } else {
                int position = Integer.parseInt(row.substring(row.indexOf('[')+1, row.indexOf(']')));
                Long value = Long.parseLong(row.split(" = ")[1]);
                memory.put(position, filterValueA(value, mask));
            }
        }
        return memory.values().stream().mapToLong(Long::longValue).sum();
    }

    static long filterValueA(Long value, String mask) {
        char[] result = StringUtils.leftPad(Long.toBinaryString(value), 36, "0").toCharArray();
        for (int i =0; i<36;i++) {
            if(mask.charAt(i)=='0')
                result[i] = '0';
            if(mask.charAt(i)=='1')
                result[i] = '1';
        }
        return Long.valueOf(String.valueOf(result), 2);
    }

    static long solveB(List<String> values) {
        String mask=null;
        HashMap<Long, Long> memory= new HashMap<>();
        for(String row : values ) {
            if(row.startsWith("mask")) {
                mask=row.split("mask = ")[1];
            } else {
                Long position = Long.parseLong(row.substring(row.indexOf('[')+1, row.indexOf(']')));
                Long value = Long.parseLong(row.split(" = ")[1]);

                var positions = filterAddresses(position, mask);
                for(var v : positions) {
                    memory.put(v, value);
                }
            }
        }
        return memory.values().stream().mapToLong(Long::longValue).sum();
    }

    static HashSet<Long> filterAddresses(Long address, String mask) {
        char[] result = StringUtils.leftPad(Long.toBinaryString(address), 36, "0").toCharArray();
        for (int i =0; i<36; i++) {
            if(mask.charAt(i)=='1')
                result[i] = '1';
            else if(mask.charAt(i)=='X')
                result[i] = 'X';
        }
        return new HashSet<>(getPossibleAddresses(new HashSet<>(), result));
    }
    static HashSet<Long> getPossibleAddresses(HashSet<Long> possibleValues, char[] input) {
        boolean containX = false;
        for (int i=0; i<36; i++) {
            if(input[i] == 'X') {
                containX = true;
                char[] v1 = Arrays.copyOf(input, 36);
                char[] v2 = Arrays.copyOf(input, 36);
                v1[i] = '0';
                v2[i] = '1';
                possibleValues.addAll(getPossibleAddresses(possibleValues, v1));
                possibleValues.addAll(getPossibleAddresses(possibleValues, v2));
                break;
            }
        }
        if(!containX) {
            possibleValues.add(Long.valueOf(String.valueOf(input), 2));
        }

        return possibleValues;
    }

    public static void main(String[] args){
        var day = "Day14";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //15018100062885
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //5724245857696
    }
}
