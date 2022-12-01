package year2021.day18;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;

public class Day18 {

    static Object solveA(List<String> values) {
        String result=values.get(0);
        for(int i=0; i<values.size()-1; i++) {
            result = comnbine(result, values.get(i+1));
        }

        return getMagnitude(result);
    }

    static Object solveB(List<String> values) {
        long biggestSoFar=0;

        for(int i=0; i<values.size()-1; i++) {
            for(int j=0; j<values.size()-1; j++) {
                if(i==j) continue;
                String s = comnbine(values.get(i), values.get(j));
                biggestSoFar = Math.max(biggestSoFar, getMagnitude(s));
            }
        }

        return biggestSoFar;
    }

    private static Long getMagnitude(String s) {
        if(s.contains("]")) {
            int firstEnd = s.indexOf("]");
            String before = s.substring(0, firstEnd);
            String after ="";
            if(s.length()>firstEnd) {
                after = s.substring(firstEnd + 1);
            }
            String activePart = before.substring(before.lastIndexOf("[")+1);
            before = before.substring(0, before.lastIndexOf("["));

            int d1=Integer.parseInt(activePart.split(",")[0])*3;
            int d2=Integer.parseInt(activePart.split(",")[1])*2;

            return getMagnitude(before+(d1+d2)+after);
        }
        return Long.valueOf(s);
    }



    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }

    public static String explode(String s) {
        int level =0;
        int startIndex, endIndex;
        for( int i=1; i<s.length()-1; i++ ) {
            if(s.charAt(i) == '[') level++;
            if(s.charAt(i) == ']') level--;
            if(level<4)
                continue;

            if(s.charAt(i) == ',' && isDigit(s.charAt(i-1)) && isDigit(s.charAt(i+1))) {
                String d1="", d2="";
                int offset1=1;
                while(isDigit(s.charAt(i-offset1))) {
                    d1=s.charAt(i-offset1)+d1;
                    offset1++;
                }
                startIndex=i-offset1;

                int offset2=1;
                while(isDigit(s.charAt(i+offset2))) {
                    d2 = d2 + s.charAt(i+offset2);
                    offset2++;
                }
                endIndex = i+offset2;

                String before = s.substring(0, startIndex);
                String after = s.substring(endIndex+1);

                String[] valuesBefore = before.split("[\\D+]");
                String[] valuesAfter = after.split("[\\D+]");
                int valueBefore = Arrays.stream(valuesBefore).filter(v->v.length()>0).map(v -> Integer.parseInt(v)).reduce((first, second) -> second).orElse(-1);
                int valueAfter = Arrays.stream(valuesAfter).filter(v->v.length()>0).map(v -> Integer.parseInt(v)).findFirst().orElse(-1);


                if(valueBefore>=0) {
                    int index = before.lastIndexOf(""+valueBefore);
                    String beforeValue = before.substring(0,index);
                    String afterValue = before.substring(1+index+(valueBefore/10));
                    before = beforeValue + (valueBefore+Integer.parseInt(d1)) + afterValue;
                }
                char c1 = s.charAt(i-offset1-1);
                char c2 = s.charAt(i+offset2+1);
                if(c1 != ',' ) {
                    before+="0";
                }
                if(valueAfter >= 0) {
                    after = after.replaceFirst(""+valueAfter, ""+(valueAfter+Integer.parseInt(d2)));
                }
                if(c2 != ',') {
                    after = "0"+after;
                }

                if(!before.endsWith(",") && !after.startsWith(",")) {
                    before+=",";
                }
                return before+after;
            }
        }
        return s;

    }
    static boolean isDigit(char c) {
        return 48 <= c && c<=48+9;
    }
    public static String split(String s) {
        String[] values  =s.split("[\\D+]");
        for(String v : values) {
            if (v.length()>=2) {
                int i = Integer.parseInt(v);
                int d1=i/2;
                int d2=i/2+i%2;
                return s.replaceFirst(v, "["+d1+","+d2+"]");
            }
        }
        return s;
    }
    public static String comnbine(String s1, String s2) {
        return reduce("[" + s1 + "," + s2 + "]");
    }

    public static String reduce(String s) {
        String before = s;
        String afterExplode = explode(before);
        if(!before.equals(afterExplode)) {
            return reduce(afterExplode);
        }
        String afterSplit = split(afterExplode);
        if(!afterExplode.equals(afterSplit)) {
            return reduce(afterSplit);
        }
        return afterSplit;

    }
}
