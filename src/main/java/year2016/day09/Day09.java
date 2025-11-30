package year2016.day09;

import utils.AdventOfCode;
import utils.FileHelper;
import utils.Reporter;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day09 implements AdventOfCode {

    @Override
    public Object solveA(List<String> values) {
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

    @Override
    public Object solveB(List<String> values) {
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

    public static void main(){
        Reporter.report(new Day09());
    }

}
