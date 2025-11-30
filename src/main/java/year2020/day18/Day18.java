package year2020.day18;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

public class Day18 {



    static long solveA(List<String> values) {
        long result=0;
        for (var value : values) {
            String stringToHandle=value.replaceAll(" ", "");
            while (stringToHandle.contains("(")) {
                stringToHandle = simplify(stringToHandle);
            }
            result+=calculate(stringToHandle);
        }
        return result;
    }

    private static long calculate(String stringToCalculate) {
        List<Long> numbers = Arrays.stream(stringToCalculate.split("\\D")).map(Long::parseLong).collect(Collectors.toList());
        var operands = stringToCalculate.split("\\d+");
        long result =numbers.get(0);
        for(int i=1;i<numbers.size();i++){
            if(operands[i].equals("+"))
                result+=numbers.get(i);
            if(operands[i].equals("*"))
                result*=numbers.get(i);

            numbers.set(i, result);
        }
        return result;
    }
    private static long calculateAdvanced(String stringToCalculate) {
        List<Long> numbers = Arrays.stream(stringToCalculate.split("\\D")).map(Long::parseLong).collect(Collectors.toList());
        var operands = stringToCalculate.split("\\d+");

        for(int i=1;i<numbers.size();i++){
            if(operands[i].equals("+")) {
                numbers.set(i, numbers.get(i - 1) + numbers.get(i));
                numbers.set(i - 1, 1L);
                operands[i] = "*";
            }
        }

        long result =numbers.get(0);

        for(int i=1;i<numbers.size();i++){
            if(operands[i].equals("*")) {
                result*=numbers.get(i);
            }
        }


        return result;
    }

    private static String simplify(String stringToHandle) {
        int firstEnd = stringToHandle.indexOf(")");
        int lastStart = stringToHandle.substring(0, firstEnd).lastIndexOf("(");
        return stringToHandle.substring(0, lastStart)+calculate(stringToHandle.substring(lastStart+1, firstEnd))+stringToHandle.substring(firstEnd+1);
    }
    private static String simplifyAdvanced(String stringToHandle) {
        int firstEnd = stringToHandle.indexOf(")");
        int lastStart = stringToHandle.substring(0, firstEnd).lastIndexOf("(");
        return stringToHandle.substring(0, lastStart)+calculateAdvanced(stringToHandle.substring(lastStart+1, firstEnd))+stringToHandle.substring(firstEnd+1);
    }


    static long solveB(List<String> values) {
        long result=0;
        for (var value : values) {
            String stringToHandle=value.replaceAll(" ", "");
            while (stringToHandle.contains("(")) {
                stringToHandle = simplifyAdvanced(stringToHandle);
            }
            result+=calculateAdvanced(stringToHandle);
        }
        return result;
    }


    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //23507031841020
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //218621700997826
    }
}
