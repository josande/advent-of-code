package year2017.day23;

import utils.AdventOfCode;
import utils.MathTools;
import utils.Reporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day23 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day23());
    }

    @Override
    public Object solveA(List<String> input) {

        ArrayList<String> instructions = new ArrayList<>(input);
        int position=0;
        HashMap<String, Integer> registers = new HashMap<>();

        int timesMul=0;
        while(position< instructions.size()) {
            String[] ins  = instructions.get(position).split(" ");
            if(ins[0].equals("mul")) timesMul++;
            switch (ins[0]) {
                case "set" -> registers.put(ins[1], asValue(ins[2], registers));
                case "sub" -> registers.put(ins[1], asValue(ins[1], registers)-asValue(ins[2], registers));
                case "mul" -> registers.put(ins[1], asValue(ins[1], registers)*asValue(ins[2], registers));
                case "jnz" -> position = asValue(ins[1], registers) == 0 ? position : position+asValue(ins[2],registers)-1;
                default -> throw new IllegalArgumentException(instructions.get(position));
            }
            position++;
        }
        return timesMul;
    }
    private int asValue(String s, HashMap<String, Integer> registers) {
        try {
            return Integer.parseInt(s);

        } catch (NumberFormatException e) {
            return registers.getOrDefault(s,0);
        }
    }

    @Override
    public Object solveB(List<String> input) {
        ArrayList<String> instructions = new ArrayList<>(input);
        int position=0;
        HashMap<String, Integer> registers = new HashMap<>();
        registers.put("a", 1);

        while(position< 9) {

            String[] ins  = instructions.get(position).split(" ");
            switch (ins[0]) {
                case "set" -> registers.put(ins[1], asValue(ins[2], registers));
                case "sub" -> registers.put(ins[1], asValue(ins[1], registers)-asValue(ins[2], registers));
                case "mul" -> registers.put(ins[1], asValue(ins[1], registers)*asValue(ins[2], registers));
                case "jnz" -> position = asValue(ins[1], registers) == 0 ? position : position+asValue(ins[2],registers)-1;
                default -> throw new IllegalArgumentException(instructions.get(position));
            }
            position++;
        }
        int result=0;
        for(int val = registers.get("b"); val<=registers.get("c"); val+=17 ) {
            result+=MathTools.isPrime(val)?0:1;
        }
        return result;
    }
}
