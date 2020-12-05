package year2016.day07;

import utils.FileHelper;

import java.util.ArrayList;
import java.util.List;

public class Day07 {

    static int solveA(List<String> values) {
        int valid=0;
        for ( var value : values ){
            if(supportsTLS(value))
                valid++;
        }
        return valid;
    }
    static boolean supportsTLS(String string ) {
        String[] parts = string.split("\\[");
        List<String> outsides=new ArrayList<>();
        List<String> insides =new ArrayList<>();
        for (var part : parts) {
            if (part.contains("]")) {
                insides.add(part.split("]")[0]);
                outsides.add(part.split("]")[1]);
            } else {
                outsides.add(part);
            }
        }
        for(var in : insides) {
            if (containsABBA(in)) return false;
        }
        for(var out : outsides) {
            if (containsABBA(out)) return true;
        }

        return false;
    }
    static boolean containsABBA(String string ) {
        for (int i=0; i<string.length()-3; i++) {
            if( string.charAt(i)   == string.charAt(i+3) &&
                string.charAt(i+1) == string.charAt(i+2) &&
                string.charAt(i)   != string.charAt(i+1) )
                return true;

        }

        return false;
    }

    static int solveB(List<String> values) {
        int valid=0;
        for ( var value : values ){
            if(supportsSSL(value)) {
                valid++;
            }

        }
        return valid;
    }
    static boolean supportsSSL(String string ) {
        String[] parts = string.split("\\[");
        List<String> outsides=new ArrayList<>();
        List<String> insides =new ArrayList<>();
        for (var part : parts) {
            if (part.contains("]")) {
                insides.add(part.split("]")[0]);
                outsides.add(part.split("]")[1]);
            } else {
                outsides.add(part);
            }
        }
        List<String> babs=new ArrayList<>();
        for(var in : insides) {
            babs.addAll(findBabs(in));
        }
        for(var out : outsides) {
            if (containsBAB(out, babs)) return true;
        }

        return false;
    }
    static List<String> findBabs(String string) {
        List<String> babs=new ArrayList<>();
        for (int i=0; i<string.length()-2; i++) {
            if( string.charAt(i) == string.charAt(i+2) &&
                string.charAt(i) != string.charAt(i+1) )
                babs.add(string.substring(i,i+2));
        }
        return babs;
    }
    static boolean containsBAB(String string,  List<String> babs) {
        for (String bab : babs) {
            String inverted = ""+bab.charAt(1)+bab.charAt(0)+bab.charAt(1);
            if (string.contains(inverted)) return true;
        }
        return false;
    }

    public static void main(String[] args){
        var day = "Day07";

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
