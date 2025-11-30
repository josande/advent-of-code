package year2023.day02;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

public class Day02 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day02());
    }

    @Override
    public Object solveA(List<String> input) {
        int maxRed =12;
        int maxGreen =13;
        int maxBlue =14;
        int result =0;
        for(String line : input) {
            boolean valid=true;
            for (String set : line.split(": ")[1].split("; ")) {
                if(valid) {
                    for(String round : set.split(", ")) {
                        String[] outcome = round.split(" ");
                        boolean isOk = switch (outcome[1].trim())  {
                            case "red" -> Integer.parseInt(outcome[0]) <= maxRed;
                            case "green" -> Integer.parseInt(outcome[0]) <= maxGreen;
                            case "blue" -> Integer.parseInt(outcome[0]) <= maxBlue;
                            default -> throw new IllegalArgumentException();
                        };
                        valid &= isOk;
                    }
                }

            }
            if(valid) {
                result+=Integer.parseInt(line.substring(line.indexOf(" ")+1, line.indexOf(":")));
            }

        }

        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        int result =0;
        for(String line : input) {
            int minRed =0;
            int minGreen =0;
            int minBlue =0;
            for (String set : line.split(": ")[1].split("; ")) {
                for(String round : set.split(", ")) {
                    String[] outcome = round.split(" ");
                    switch (outcome[1].trim())  {
                        case "red" -> minRed = Math.max(Integer.parseInt(outcome[0]), minRed);
                        case "green" -> minGreen = Math.max(Integer.parseInt(outcome[0]), minGreen);
                        case "blue" -> minBlue = Math.max(Integer.parseInt(outcome[0]), minBlue);
                        default -> throw new IllegalArgumentException();
                    }
                }
            }
            result+=minRed * minGreen * minBlue;
        }

        return result;
    }
}
