package year2021.day03;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 {

    static Object solveA(List<String> values) {
        int length = values.get(0).length();
        int[] bits = new int[length];
        for(var val : values) {
            for(int i=0; i<length; i++) {
                if( val.charAt(i) == '1') {
                    bits[i]+=1;
                }
            }
        }
        for(int i=0; i<length; i++) {
            if (bits[i]*2 > (values.size())) {
                bits[i]=1;
            } else {
                bits[i]=0;
            }
        }

        int gamma=0;
        int max=0;
        for(int i=0; i<length; i++) {
            gamma=gamma*2+bits[i];
            max=2*max+1;
        }
        int sigma = max-gamma;
        return sigma*gamma;
    }

    static Object solveB(List<String> values) {
        //Oxygen, most common bit
        int pos = 0;
        List<String> filteredValues = filterList(values, 0, true);
        while(filteredValues.size()>1) {
            pos++;
            filteredValues = filterList(filteredValues, pos, true);
        }
        int oxygen=0;
        for(char c : filteredValues.get(0).toCharArray()) {
            oxygen*=2;
            if (c=='1') oxygen+=1;
        }

        //co2, least common bit
        pos = 0;
        filteredValues = filterList(values, 0, false);
        while(filteredValues.size()>1) {
            pos++;
            filteredValues = filterList(filteredValues, pos, false);
        }
        int co2=0;
        for(char c : filteredValues.get(0).toCharArray()) {
            co2*=2;
            if (c=='1') co2+=1;
        }

        return oxygen*co2;

    }
    static List<String> filterList(List<String> values, int pos, boolean mostCommon) {

        char mostCommonAtC;
        int times = 0;
        for (String val : values) {
            if (val.charAt(pos) == '1') {
                times++;
            }
        } if (times*2 < values.size() ) {
            mostCommonAtC = '0';
        } else  {
            mostCommonAtC = '1';
        }

        return values.stream().filter(v-> ((v.charAt(pos) == mostCommonAtC) == mostCommon) ).collect(Collectors.toList());
    }




    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //4001724
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //587895
    }
}
