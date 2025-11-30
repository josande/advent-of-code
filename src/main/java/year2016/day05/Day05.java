package year2016.day05;

import utils.AdventOfCode;
import utils.FileHelper;
import utils.Reporter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Day05 implements AdventOfCode {

    @Override
    public Object solveA(List<String> values) {
        int counter=0;
        StringBuilder password = new StringBuilder();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for ( var value : values ) {
            while (password.length()<8) {
                String md5 = asMD5(value+counter, messageDigest);

                if(md5.startsWith("00000")) {
                    password.append(md5.charAt(5));
                }
                counter++;
            }
        }

        return password.toString();
    }

    static String asMD5(String data, MessageDigest messageDigest) {
        byte[] digest = messageDigest.digest(data.getBytes(StandardCharsets.UTF_8));
        return String.format("%032x", new BigInteger(1, digest));
    }

    @Override
    public Object solveB(List<String> values) {
        int counter=0;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        char[] password = "********".toCharArray();
        for ( var value : values ) {
            while (String.valueOf(password).contains("*")) {
                String md5="";
                md5 = asMD5(value+counter, messageDigest);

                if(md5.startsWith("00000")) {
                    int pos=99;
                    try {
                        pos= Integer.parseInt(""+md5.charAt(5));
                    } catch (Exception ignored) {
                    }
                    if (pos<8 && password[pos] == '*') {
                        password[pos]=md5.charAt(6);
                    }
                }
                counter++;
            }
        }
        return String.valueOf(password);
    }

    public static void main(){
        Reporter.report(new Day05());
    }
}
