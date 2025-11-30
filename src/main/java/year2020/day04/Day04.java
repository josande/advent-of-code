package year2020.day04;

import utils.FileHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day04 {

    static final List<String> neededKeys = Arrays.asList("byr","iyr","eyr","hgt","hcl","ecl","pid");
    static final List<String> harColors = Arrays.asList("amb","blu","brn","gry","grn","hzl","oth");

    static int solveA(List<String> values) {
        var valid=0;
        var passPort = new HashMap<String, String>();

        for (var str : values) {
            if( str.isEmpty() ) {
                if(hasAllKeyFields(passPort) )
                    valid++;
                passPort = new HashMap<>();
                continue;
            }
            for (var strPart : str.split(" ")) {
                passPort.put(strPart.split(":")[0],strPart.split(":")[1]);
            }
        }
        if( hasAllKeyFields(passPort) )
            valid++;
        return valid;
    }
    static boolean hasAllKeyFields(HashMap<String, String> passport) {
        return (passport.keySet().containsAll(neededKeys));

    }
    static int solveB(List<String> values) {
        int valid=0;

        var passPort = new HashMap<String, String>();
        for (var str : values) {
            if( str.isEmpty() ) {
                if(hasAllKeyFields(passPort) && hasValidFieldData(passPort))
                    valid++;
                passPort = new HashMap<>();
                continue;
            }
            for (var strPart : str.split(" ")) {
                passPort.put(strPart.split(":")[0],strPart.split(":")[1]);
            }
        }
        if(hasAllKeyFields(passPort) && hasValidFieldData(passPort))
            valid++;
        return valid;
    }
    static boolean hasValidFieldData(HashMap<String, String> passport) {
        int byr = Integer.parseInt(passport.get("byr")) ;
        if (byr < 1920 || byr > 2002) return false;

        int iyr = Integer.parseInt(passport.get("iyr")) ;
        if (iyr < 2010 || iyr > 2020) return false;

        int eyr = Integer.parseInt(passport.get("eyr")) ;
        if (eyr < 2020 || eyr > 2030) return false;

        String _hgt = passport.get("hgt");
        if(!_hgt.endsWith("cm") || _hgt.endsWith("in")) return false;
        int hgt = Integer.parseInt(_hgt.replaceAll("cm","").replaceAll("in",""));
        if (_hgt.endsWith("cm") ) {
            if (hgt < 150 || hgt > 193) return false;
        } else if (hgt < 59 || hgt > 76) return false;

        if(!passport.get("hcl").matches("#[0-9a-f]{6}")) return false;

        if(!harColors.contains(passport.get("ecl"))) return false;

        return passport.get("pid").matches("[0-9]{9}");
    }

    public static void main(){
        var day = "Day04";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //204
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //179
    }
}
