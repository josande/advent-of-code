package year2016.day06;

import utils.FileHelper;
import utils.MapUtil;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static utils.MD5Tool.asMD5;

public class Day06 {

    static String solveA(List<String> values) {
        String message="";
        if(values.isEmpty()) return "";

        for(int i=0; i<values.get(0).length(); i++) {
            String str="";
            for(String msg : values) {
                str+=msg.charAt(i);
            }
            var key = MapUtil.getLetterOccurrencesMap(str).entrySet().stream()
                    .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get()
                    .getKey();
            message+=key;
        }


        return message;
    }

    static String solveB(List<String> values) {
        String message="";
        if(values.isEmpty()) return "";

        for(int i=0; i<values.get(0).length(); i++) {
            String str="";
            for(String msg : values) {
                str+=msg.charAt(i);
            }
            var key = MapUtil.getLetterOccurrencesMap(str).entrySet().stream()
                    .min((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get()
                    .getKey();
            message+=key;
        }


        return message;
    }


    public static void main(String[] args){
        var day = "Day06";

        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = System.currentTimeMillis()-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //qtbjqiuq
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
