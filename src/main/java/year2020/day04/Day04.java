package year2020.day04;

import utils.FileHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day04 {


    static String data = """
            byr (Birth Year)
            iyr (Issue Year)
            eyr (Expiration Year)
            hgt (Height)
            hcl (Hair Color)
            ecl (Eye Color)
            pid (Passport ID)
            cid (Country ID)
            """;
    static List<String> neededKeys = Arrays.asList("byr","iyr","eyr","hgt","hcl","ecl","pid");
    static int solveA(List<String> values) {

        List<HashMap<String, String>> passports=new ArrayList<>();
        passports.add(new HashMap<String, String>());

        int passPortCounter=0, validPassports=0;
        for (String str : values) {
            if(str.isEmpty()) {
                passPortCounter++;
                passports.add(new HashMap<>());
                continue;
            }
            for (String strPart : str.split(" ")) {

                passports.get(passPortCounter).put(strPart.split(":")[0],strPart.split(":")[1]);
            }
        }
        for (var passport : passports) {
            if (hasAllKeyFields(passport))
                validPassports++;
        }
        return validPassports;
    }
    static boolean hasAllKeyFields(HashMap<String, String> passport) {
        return (passport.keySet().containsAll(neededKeys));

    }
    static int solveB(List<String> values) {
        List<HashMap<String, String>> passports=new ArrayList<>();
        passports.add(new HashMap<String, String>());

        int passPortCounter=0, validPassports=0;

        for (String str : values) {
            if(str.isEmpty()) {
                passPortCounter++;
                passports.add(new HashMap<>());
                continue;
            }
            for (String strPart : str.split(" ")) {

                passports.get(passPortCounter).put(strPart.split(":")[0],strPart.split(":")[1]);
            }
        }
        int valid=0;
        for(var passPort: passports)
            if(hasAllKeyFields(passPort) && hasValidFieldData(passPort))
               valid++;

        return valid;
    }
    static boolean hasValidFieldData(HashMap<String, String> passport) {
        int byr = Integer.valueOf(passport.get("byr")) ;
        if (byr<1920 || byr >2002) return false; // byr (Birth Year) - four digits; at least 1920 and at most 2002.

        int iyr = Integer.valueOf(passport.get("iyr")) ;
        if (iyr<2010 || iyr >2020) return false; //iyr (Issue Year) - four digits; at least 2010 and at most 2020.

        int eyr = Integer.valueOf(passport.get("eyr")) ;
        if (eyr<2020 || eyr >2030) return false; //eyr (Expiration Year) - four digits; at least 2020 and at most 2030.


        int hgt = Integer.valueOf(passport.get("hgt").replaceAll("cm","").replaceAll("in",""));
        if (passport.get("hgt").endsWith("cm") ) {
             if (hgt<150 || hgt >193) return false;
        } else if (passport.get("hgt").endsWith("in")) {
            if (hgt<59 || hgt >76) return false;
        } else {
            return false;
        }
        String hcl = passport.get("hcl");
        if(hcl.length() != 7 ||
                !hcl.startsWith("#") ||
                !hcl.substring(1,7).matches("[0-9a-f]{6}")
        ) return false;

        String ecl = passport.get("ecl");
        List<String> harColors = Arrays.asList("amb","blu","brn","gry","grn","hzl","oth");
        if(!harColors.contains(ecl)) return false;

        String pid = passport.get("pid");

        if( !pid.matches("[0-9]{9}")) {
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        String day = "Day04";

        List<String> inputs = new FileHelper().readFile("2020/"+day+".txt");

        long t0 = System.currentTimeMillis();
        int ansA=solveA(inputs);
        long t1 = System.currentTimeMillis();
        int ansB=solveB(inputs);
        long timePart1 = System.currentTimeMillis()-t0;
        long timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //204
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //179
    }
}
