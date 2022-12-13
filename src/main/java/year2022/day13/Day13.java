package year2022.day13;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day13 {


    static Object solveA(List<String> values) {
        int matches=0;
        int index=0;
        for(int i=0; i<values.size(); i+=3) {
            String s1=values.get(i);
            String s2=values.get(i+1);
            index++;
            if(compare(s1,s2) < 0) {
                matches+=index;
            }
        }

        return matches;
    }
    static int compare(String s1, String s2) {
        if(s1.equals(s2)) {
            return 0;
        }
        if(s1.isEmpty()) {
            return 1;
        }
        if(s2.isEmpty()) {
            return -1;
        }
        if(s1.startsWith("[") && s2.startsWith("[")) {
            List<String> parts1 = splitToParts(s1);
            List<String> parts2 = splitToParts(s2);
            int minLength=Math.min(parts1.size(), parts2.size());

            for(int j=0; j<minLength; j++) {
                int result = compare(parts1.get(j), parts2.get(j));
                if(result!=0) {
                    return result;
                }
            }
            return parts1.size()-parts2.size();
        } else if(!s1.startsWith("[") && !s2.startsWith("[")) {
            return Integer.parseInt(s1)-Integer.parseInt(s2);
        }
        else if(s1.startsWith("[")) {
            s2 = "["+s2+"]";
        } else {
            s1 = "["+s1+"]";
        }
        return compare(s1, s2);

    }

    static ArrayList<String> splitToParts(String input) {
        ArrayList<String> parts=new ArrayList<>();

        input = input.substring(1, input.length()-1);
        while(input.length()>0) {
            if (input.charAt(0) == '[') {
                int level=0;
                for(int i=1; i<input.length(); i++) {
                    if(input.charAt(i)=='[') {
                        level ++;
                    } else if(input.charAt(i) == ']') {
                        if(level==0) {
                            parts.add(input.substring(0,i+1));
                            input=input.substring(i+1);
                            break;
                        }
                        level --;
                    }
                }
            } else if(input.contains(",")) {
                    parts.add(input.substring(0, input.indexOf(',')));
                    input=input.substring(input.indexOf(',')+1);
            } else {
                parts.add(input);
                input="";
            }

        }

        return parts;
    }
    static class Part {
        String input;

    }


    static Object solveB(List<String> values) {
        List<String> packages = new ArrayList<>();
        for(String s : values) {
            if(s.isEmpty()) {
                continue;
            }
            packages.add(s);
        }
        String divider1="[[2]]";
        String divider2="[[6]]";
        packages.add(divider1);
        packages.add(divider2);

        packages.sort(Day13::compare);

        int result=1;
        for(int i = 0; i<packages.size(); i++) {
            if(packages.get(i).equals(divider1) ||
               packages.get(i).equals(divider2))  {
                result *=(i+1);
            }
        }
        return result;
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //5760
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //266770
    }
}
