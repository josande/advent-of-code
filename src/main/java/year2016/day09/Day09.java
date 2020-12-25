package year2016.day09;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day09 {

    static long solveA(List<String> values) {
        long result=0;
        for(var val:values) {
            String tempString = val;
            while(tempString.contains("(")) {
                tempString = simplify(tempString);
            }
            result+=tempString.length();
        }
        return result;
    }

    private static String simplify(String tempString) {
        int startPos=tempString.indexOf("(");
        int endPos=tempString.indexOf(")");
        String instruction= tempString.substring(startPos+1, endPos);
        int length = Integer.parseInt(instruction.split("x")[0]);
        int times  = Integer.parseInt(instruction.split("x")[1]);

        String partToRepeat = tempString.substring(endPos+1, endPos+1+length).replaceAll("\\(","x").replaceAll("\\)", "x");

        StringBuilder finalString= new StringBuilder();
        if(startPos>0) {
            finalString = new StringBuilder(tempString.substring(0, startPos));
        }
        finalString.append(partToRepeat.repeat(Math.max(0, times)));

        finalString.append(tempString.substring(endPos + length + 1));
        return finalString.toString();
    }

    static long solveB(List<String> values) {
        long result=0;
        for(var val:values) {
            result+= getLengthRecurse(val);
        }
        return result;
    }

    private static long getLengthRecurse(String tempString) {
        if(!tempString.contains("(")) {
            return tempString.length();
        }
        long result = tempString.indexOf("(");

        int startPos=tempString.indexOf("(");
        int endPos=tempString.indexOf(")");
        String instruction= tempString.substring(startPos+1, endPos);
        int length = Integer.parseInt(instruction.split("x")[0]);
        int times  = Integer.parseInt(instruction.split("x")[1]);

        String partToRepeat = tempString.substring(endPos+1, endPos+1+length);
        String rest = tempString.substring(endPos+length+1);

        return result + times* getLengthRecurse(partToRepeat) + getLengthRecurse(rest);
    }


    public static void main (String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //120765
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //11658395076
    }

}
