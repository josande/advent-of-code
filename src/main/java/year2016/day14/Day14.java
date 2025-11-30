package year2016.day14;

import utils.AdventOfCode;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import utils.Reporter;

public class Day14 implements AdventOfCode {

    @Override
    public Object solveA(List<String> values) {

        String salt = values.get(0);

        ArrayList<Integer> keys = new ArrayList<>();
        for (int i=0; ; i++) {
            String hash = DigestUtils.md5Hex(salt+i);
            boolean trippleFound = false;
            StringBuilder toMatch= new StringBuilder();
            for(int c=0; c<hash.length()-2; c++) {
                if (!trippleFound) {
                    if (hash.charAt(c) == hash.charAt(c + 1) && hash.charAt(c) == hash.charAt(c + 2)) {
                        trippleFound = true;
                        toMatch.append(hash.charAt(c));
                        toMatch.append(hash.charAt(c));
                        toMatch.append(hash.charAt(c));
                        toMatch.append(hash.charAt(c));
                        toMatch.append(hash.charAt(c));
                    }
                }
            }
            if(trippleFound) {
                for(int j=1;j<=1000;j++) {
                    if(DigestUtils.md5Hex(salt+(i+j)).contains(toMatch.toString())) {
                        keys.add(i);
                        break;
                    }
                }
            }
            if(keys.size()==64) {
                return i;
            }
        }
    }

    @Override
    public Object solveB(List<String> values) {
        String salt = values.get(0);

        ArrayList<Integer> keys = new ArrayList<>();
        HashMap<Integer, String> hashes = new HashMap<>();
        for (int i=0; ; i++) {
            String foo;
            if(hashes.containsKey(i)) {
                foo = hashes.get(i);
            } else {
                foo = stretch(salt+i);
                hashes.put(i, foo);
            }

            boolean trippleFound = false;
            StringBuilder toMatch= new StringBuilder();
            for(int c=0; c<foo.length()-2; c++) {
                if (!trippleFound) {
                    if (foo.charAt(c) == foo.charAt(c + 1) && foo.charAt(c) == foo.charAt(c + 2)) {
                        trippleFound = true;
                        toMatch.append(foo.charAt(c));
                        toMatch.append(foo.charAt(c));
                        toMatch.append(foo.charAt(c));
                        toMatch.append(foo.charAt(c));
                        toMatch.append(foo.charAt(c));
                    }
                }
            }
            if(trippleFound) {
                for(int j=1;j<=1000;j++) {
                    String temp;
                    if (hashes.containsKey(i+j)) {
                        temp = hashes.get(i+j);
                    } else {
                        temp = stretch(salt+(i+j));
                        hashes.put(i+j, temp);
                    }
                    if(temp.contains(toMatch.toString())) {
                        keys.add(i);
                        break;
                    }
                }
            }
            if(keys.size()==64) {
                return i;
            }
        }
    }

    private static String stretch(String salt) {
        String temp = salt;
        for(int i=0; i<=2016; i++) {
            temp = DigestUtils.md5Hex(temp);
        }
        return temp;
    }

    public static void main(){
        Reporter.report(new Day14());
    }
}
