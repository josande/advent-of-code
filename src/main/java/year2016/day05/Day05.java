package year2016.day05;

import utils.FileHelper;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 {

    static String solveA(List<String> values) {
        int counter=0;
        String password="";
        for ( var value : values ) {
            while (password.length()<8) {
                String md5="";
                try {
                    md5 = asMD5(value+counter);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                if(md5.startsWith("00000")) {
                    password+=md5.charAt(5);
                }
                counter++;
            }
        }

        return password;
    }
    static String asMD5(String data)
            throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(data.getBytes(StandardCharsets.UTF_8));
        return String.format("%032x", new BigInteger(1, digest));
    }

    static String solveB(List<String> values) {
        int counter=0;
        char[] password = "********".toCharArray();
        for ( var value : values ) {
            while (String.valueOf(password).contains("*")) {
                String md5="";
                try {
                    md5 = asMD5(value+counter);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                if(md5.startsWith("00000")) {
                    int pos=99;
                    try {
                        pos= Integer.valueOf(""+md5.charAt(5));
                    } catch (Exception ex) {
                    }
                    if (pos<8 && password[pos] == '*') {
                        password[pos]=md5.charAt(6);
                        System.out.println(String.valueOf(password));
                    }
                }
                counter++;
            }
        }
        return String.valueOf(password);
    }


    public static void main(String[] args){
        var day = "Day05";

        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = System.currentTimeMillis()-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
