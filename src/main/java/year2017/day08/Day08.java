package year2017.day08;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.HashMap;
import java.util.List;

public class Day08 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day08());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<String, Integer> registers = new HashMap<>();

        for(String row : input) {
            String[] words = row.split(" ");
            String register = words[0];
            String operation = words[1];
            int value = Integer.parseInt(words[2]);
            String register2 = words[4];
            String comparator = words[5];
            int value2 = Integer.parseInt(words[6]);

            int valAfter;
            if(operation.equals("inc")) {
                valAfter = registers.getOrDefault(register, 0)+value;
            } else {
                valAfter = registers.getOrDefault(register, 0)-value;
            }
            boolean doUpdate;
            switch (comparator) {
                case "==" ->  doUpdate = (registers.getOrDefault(register2, 0) == value2);
                case "!=" ->  doUpdate = (registers.getOrDefault(register2, 0) != value2);
                case "<" ->  doUpdate = (registers.getOrDefault(register2, 0) < value2);
                case ">" ->  doUpdate = (registers.getOrDefault(register2, 0) > value2);
                case ">=" ->  doUpdate = (registers.getOrDefault(register2, 0) >= value2);
                case "<=" ->  doUpdate = (registers.getOrDefault(register2, 0) <= value2);
                default -> throw new IllegalArgumentException("Unknown comparator "+comparator);
            }
            if (doUpdate) registers.put(register, valAfter);
        }
        return registers.values().stream().reduce(Integer::max).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<String, Integer> registers = new HashMap<>();
        int highestValueEver=0;
        for(String row : input) {
            String[] words = row.split(" ");
            String register = words[0];
            String operation = words[1];
            int value = Integer.parseInt(words[2]);
            String register2 = words[4];
            String comparator = words[5];
            int value2 = Integer.parseInt(words[6]);

            int valAfter;
            if(operation.equals("inc")) {
                valAfter = registers.getOrDefault(register, 0)+value;
            } else {
                valAfter = registers.getOrDefault(register, 0)-value;
            }
            boolean doUpdate;
            switch (comparator) {
                case "==" ->  doUpdate = (registers.getOrDefault(register2, 0) == value2);
                case "!=" ->  doUpdate = (registers.getOrDefault(register2, 0) != value2);
                case "<" ->  doUpdate = (registers.getOrDefault(register2, 0) < value2);
                case ">" ->  doUpdate = (registers.getOrDefault(register2, 0) > value2);
                case ">=" ->  doUpdate = (registers.getOrDefault(register2, 0) >= value2);
                case "<=" ->  doUpdate = (registers.getOrDefault(register2, 0) <= value2);
                default -> throw new IllegalArgumentException("Unknown comparator "+comparator);
            }
            if (doUpdate) {
                registers.put(register, valAfter);
                highestValueEver = Math.max(highestValueEver, registers.values().stream().reduce(Integer::max).orElse(0));
            }
        }
        return highestValueEver;
    }
}
