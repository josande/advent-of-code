package year2016.day06;

import utils.AdventOfCode;
import utils.FileHelper;
import utils.MapUtil;
import utils.Reporter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static utils.MD5Tool.asMD5;

public class Day06 implements AdventOfCode {

    @Override
    public Object solveA(List<String> values) {
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
    @Override
    public Object solveB(List<String> values) {
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

    public static void main(){
        Reporter.report(new Day06());
    }
}
