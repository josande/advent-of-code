package year2021.day08;

import utils.ArrayHelper;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day08 {

    static Object solveA(List<String> values) {
        int times=0;
        for(var val : values) {
            String output =  val.split(" \\| ")[1];
            String[] digits = output.split(" ");
            for (String digit : digits) {
                if (digit.length()==2 || digit.length()==3 || digit.length()==4 || digit.length()==7 )
                    times++;
            }
        }
        return times;
    }
    static Object solveB(List<String> values) {
        int sum=0;
        for (var val : values) {
            Map<Integer, String> mapping = new HashMap<>();
            String input = val.split(" \\| ")[0];
            String output = val.split(" \\| ")[1];
            String[] mappings = input.split(" ");

            String[] digits = output.split(" ");
            for(int i = 0; i<digits.length; i++) {
                char[] digitArray = digits[i].toCharArray();
                Arrays.sort(digitArray);
                digits[i] = String.valueOf(digitArray);
            }
            for(int i = 0; i<mappings.length; i++) {
                char[] digitArray = mappings[i].toCharArray();
                Arrays.sort(digitArray);
                mappings[i] = String.valueOf(digitArray);
            }

            for (String digit : mappings) {
                if (digit.length() == 2) {
                    mapping.put(1, digit);
                }
                if (digit.length() == 4) {
                    mapping.put(4, digit);
                }
                if (digit.length() == 3) {
                    mapping.put(7, digit);
                }
                if (digit.length() == 7) {
                    mapping.put(8, digit);
                }
            }
            for (String digit : mappings) {
                if (digit.length() == 5) {
                    if (countMatches(digit, mapping.get(7))==3 ) {
                        mapping.put(3, digit);
                    } else if(countMatches(digit, mapping.get(4))==3) {
                        mapping.put(5, digit);
                    } else {
                        mapping.put(2, digit);
                    }
                }
            }
            for (String digit : mappings) {
                if (digit.length() == 6) {
                    if (countMatches(digit, mapping.get(1))==2) {
                        if (countMatches(digit, mapping.get(4)) == 4) {
                            mapping.put(9, digit);
                        } else {
                            mapping.put(0, digit);
                        }
                    } else {
                        mapping.put(6, digit);
                    }
                 }
            }
            int outputNumber=0;
            for (String digit : digits) {
                for (Map.Entry<Integer, String> entry : mapping.entrySet()) {
                    if(digit.equals(entry.getValue())) {
                        outputNumber= outputNumber*10+entry.getKey();
                    }
                }
            }
            sum+=outputNumber;
        }

        return sum;
    }
    private static int countMatches(String strA, String strB) {
        int matches=0;
        for(char c : strA.toCharArray()) {
            if(strB.indexOf(c)>=0) matches++;
        }
        return matches;
    }


    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //294
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //973292
    }
}
