package year2024.day03;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

import static java.lang.Integer.parseInt;

public class Day03 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day03());
    }

    @Override
    public Object solveA(List<String> input) {

        int result=0;
        for(String row : input) {
            String[] parts = row.split("mul\\(");

            for (String s : parts) {

                if (!s.contains(")") || !s.contains(",")) continue;
                String part = s.substring(0, s.indexOf(")"));

                if (!part.contains(",")) continue;
                if (part.contains("w")) continue;
                if (part.contains("%")) continue;
                if (part.contains("[")) continue;
                if (part.contains("?")) continue;
                if (part.contains(" ")) continue;
                if (part.contains("$")) continue;
                if (part.contains("f")) continue;
                if (part.contains("<")) continue;
                if (part.contains("*")) continue;
                if (part.contains("'")) continue;
                if (part.contains("#")) continue;
                result += parseInt(part.split(",")[0]) * parseInt(part.split(",")[1]);
            }
        }
       return result; //169021493
    }

    @Override
    public Object solveB(List<String> input) {

        int result=0;
        boolean isActive=true;
        boolean startAfter=false;
        boolean stopAfter=false;

        for(String row : input) {

            String[] parts = row.split("mul\\(");

            for (String s : parts) {
                int start = s.lastIndexOf("do()");
                int stop = s.lastIndexOf("don't()");

                if (startAfter)
                    isActive = true;
                if (stopAfter)
                    isActive = false;

                startAfter = start > 0;
                stopAfter = stop > 0;

                if (!s.contains(")") || !s.contains(",")) continue;
                String part = s.substring(0, s.indexOf(")"));


                if (part.contains("w")) continue;
                if (part.contains("%")) continue;
                if (part.contains("[")) continue;
                if (part.contains("?")) continue;
                if (part.contains(" ")) continue;
                if (part.contains("$")) continue;
                if (part.contains("f")) continue;
                if (part.contains("<")) continue;
                if (part.contains("*")) continue;
                if (part.contains("'")) continue;
                if (part.contains("#")) continue;
                if (isActive)
                    result += parseInt(part.split(",")[0]) * parseInt(part.split(",")[1]);
            }
        }
        return result; //111762583
    }
}
