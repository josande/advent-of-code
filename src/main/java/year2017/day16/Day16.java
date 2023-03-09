
package year2017.day16;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day16());
    }

    @Override
    public Object solveA(List<String> input) {
        String[] instructions = input.get(0).split(",");
        int programs = instructions.length<5 ? 5 : 16;

        char c = 'a';
        StringBuilder order = new StringBuilder();
        for(int i=0; i<programs; i++) { order.append(c++); }

        for(String instruction : instructions ) {
            switch (instruction.charAt(0)) {
                case 's' -> {
                    int length = Integer.parseInt(instruction.substring(1));
                    order = new StringBuilder(order.substring(order.length() - length) + order.substring(0, order.length() - length));
                }
                case 'x' -> {
                    int pos1 = Integer.parseInt(""+instruction.substring(1).split("/")[0]);
                    int pos2 = Integer.parseInt(""+instruction.split("/")[1]);
                    char c1 = order.charAt(pos1);
                    char c2 = order.charAt(pos2);
                    order = new StringBuilder(order.toString().replace(c2, 'X').replace(c1, c2).replace('X', c1));
                }
                case 'p' -> {
                    char c1 = instruction.charAt(1);
                    char c2 = instruction.charAt(3);
                    order = new StringBuilder(order.toString().replace(c2, 'X').replace(c1, c2).replace('X', c1));
                }
                default -> throw new IllegalArgumentException("Unknown instruction: "+instruction);
            }
        }

        return order.toString();
    }

    @Override
    public Object solveB(List<String> input) {
        String[] instructions = input.get(0).split(",");
        int programs = instructions.length<5 ? 5 : 16;

        char c = 'a';
        StringBuilder order = new StringBuilder();
        for(int i=0; i<programs; i++) { order.append(c++); }

        HashMap<String, Integer> positions = new HashMap<>();
        int iterations=1000000000;
        for(int round=0; round<iterations; round++) {
            for (String instruction : instructions) {
                switch (instruction.charAt(0)) {
                    case 's' -> {
                        int length = Integer.parseInt(instruction.substring(1));
                        order = new StringBuilder(order.substring(order.length() - length) + order.substring(0, order.length() - length));
                    }
                    case 'x' -> {
                        int pos1 = Integer.parseInt("" + instruction.substring(1).split("/")[0]);
                        int pos2 = Integer.parseInt("" + instruction.split("/")[1]);
                        char c1 = order.charAt(pos1);
                        char c2 = order.charAt(pos2);
                        order = new StringBuilder(order.toString().replace(c2, 'X').replace(c1, c2).replace('X', c1));
                    }
                    case 'p' -> {
                        char c1 = instruction.charAt(1);
                        char c2 = instruction.charAt(3);
                        order = new StringBuilder(order.toString().replace(c2, 'X').replace(c1, c2).replace('X', c1));
                    }
                    default -> throw new IllegalArgumentException("Unknown instruction: " + instruction);
                }
            }
            if(positions.containsKey(order.toString())) {
                int cycleLength = round - positions.get(order.toString());
                for (Map.Entry<String, Integer> e : positions.entrySet()) {
                    if (e.getValue() == iterations % cycleLength - 1) return e.getKey();
                }
            }
            positions.put(order.toString(), round);
        }
        return order.toString();
    }
}
