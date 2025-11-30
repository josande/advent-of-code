package year2017.day18;

import lombok.Data;
import utils.AdventOfCode;
import utils.Reporter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Day18 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day18());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<Character, BigInteger> registers = new HashMap<>();
        ArrayList<BigInteger> soundsPlayed = new ArrayList<>();
        int position=0;
        while (position>=0 && position<input.size()) {
            String row = input.get(position);
            switch (row.split(" ")[0]) {
                case "snd" ->
                        soundsPlayed.add(getValue(row.split(" ")[1], registers));
                case "set" -> registers.put(row.charAt(4), getValue(row.split(" ")[2], registers));
                case "add" -> registers.put(row.charAt(4), getValue(row.split(" ")[1], registers).add(getValue(row.split(" ")[2], registers)));
                case "mul" -> registers.put(row.charAt(4), getValue(row.split(" ")[1], registers).multiply(getValue(row.split(" ")[2], registers)));
                case "mod" -> registers.put(row.charAt(4), getValue(row.split(" ")[1], registers).mod(getValue(row.split(" ")[2], registers)));
                case "rcv" -> {if(!getValue(row.split(" ")[1], registers).equals(BigInteger.ZERO)) {return soundsPlayed.get(soundsPlayed.size()-1).intValue();}}
                case "jgz" -> {if(getValue(row.split(" ")[1], registers).compareTo(BigInteger.ZERO) > 0) { position+=getValue(row.split(" ")[2], registers).intValue()-1;}}
           }
           position++;

        }
        throw new IllegalStateException("No solution found");
    }
    private BigInteger getValue(String s, HashMap<Character, BigInteger> registers) {
        try {
            return new BigInteger(s);
        } catch (NumberFormatException ex) {
            return registers.getOrDefault(s.charAt(0), BigInteger.ZERO);
        }
    }

    @Override
    public Object solveB(List<String> input) {
        Program p0 = new Program();
        Program p1 = new Program();

        p0.inputs = p1.outputs;
        p1.inputs = p0.outputs;

        p0.setRegister('p', BigInteger.ZERO);
        p1.setRegister('p', BigInteger.ONE);

        p0.setInstructions(input);
        p1.setInstructions(input);

        do {
            p0.runUntilPause();
            p1.runUntilPause();
        } while(p0.hasOutput() || p1.hasOutput());

        return p1.getTimesSent();
    }
    @Data
    private class Program {
        HashMap<Character, BigInteger> registers = new HashMap<>();
        int position;
        List<String> instructions;

        LinkedList<BigInteger> inputs;
        LinkedList<BigInteger> outputs = new LinkedList<>();

        int timesSent=0;

        void setRegister(char c, BigInteger value) {
            registers.put(c, value);
        }
        boolean hasOutput() {
            return !outputs.isEmpty();
        }
        void runUntilPause() {
            while (position>=0 && position<instructions.size()) {
                String row = instructions.get(position);
                switch (row.split(" ")[0]) {
                    case "snd" -> {outputs.add(getValue(row.split(" ")[1], registers)); timesSent++;}
                    case "set" -> registers.put(row.charAt(4), getValue(row.split(" ")[2], registers));
                    case "add" -> registers.put(row.charAt(4), getValue(row.split(" ")[1], registers).add(getValue(row.split(" ")[2], registers)));
                    case "mul" -> registers.put(row.charAt(4), getValue(row.split(" ")[1], registers).multiply(getValue(row.split(" ")[2], registers)));
                    case "mod" -> registers.put(row.charAt(4), getValue(row.split(" ")[1], registers).mod(getValue(row.split(" ")[2], registers)));
                    case "rcv" -> {
                        if(inputs.isEmpty()) return;
                        else registers.put(row.charAt(4), inputs.pop());
                    }
                    case "jgz" -> {if(getValue(row.split(" ")[1], registers).compareTo(BigInteger.ZERO) > 0) { position+=getValue(row.split(" ")[2], registers).intValue()-1;}}
                }
                position++;
            }
        }
    }
}
