package year2022.day25;

import org.apache.commons.lang3.StringUtils;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day25 {

    static String addSnafu(String snafu1, String snafu2) {
        snafu1=StringUtils.leftPad(snafu1, snafu2.length(), '0');
        snafu2=StringUtils.leftPad(snafu2, snafu1.length(), '0');

        int memory = 0;
        char res = 'x';
        StringBuilder result = new StringBuilder();
        for (int p = snafu1.length() - 1; p >= 0; p--) {
            int val1 = 0, val2 = 0;
            switch (snafu1.charAt(p)) {
                case '2' -> val1 =  2;
                case '1' -> val1 =  1;
                case '0' -> val1 =  0;
                case '-' -> val1 = -1;
                case '=' -> val1 = -2;
            }
            switch (snafu2.charAt(p)) {
                case '2' -> val2 =  2;
                case '1' -> val2 =  1;
                case '0' -> val2 =  0;
                case '-' -> val2 = -1;
                case '=' -> val2 = -2;
            }
            switch (val1 + val2 + memory) {
                case  5 -> { memory =  1; res = '0'; }
                case  4 -> { memory =  1; res = '-'; }
                case  3 -> { memory =  1; res = '='; }
                case  2 -> { memory =  0; res = '2'; }
                case  1 -> { memory =  0; res = '1'; }
                case  0 -> { memory =  0; res = '0'; }
                case -1 -> { memory =  0; res = '-'; }
                case -2 -> { memory =  0; res = '='; }
                case -3 -> { memory = -1; res = '2'; }
                case -4 -> { memory = -1; res = '1'; }
                case -5 -> { memory = -1; res = '0'; }
            }
            result.insert(0, res);
        }
        if(memory == 1){
            result.insert(0, '1');
        }
        if(memory == -1){
            result.insert(0, '-');
        }

        return result.toString();
    }

    static Object solveA(List<String> values) {
        String result="";
        for(String val : values) {
            result=addSnafu(val, result);
        }

        return result;
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = t1-t0;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 2=20---01==222=0=0-2
    }
}
