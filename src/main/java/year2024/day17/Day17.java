package year2024.day17;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day17 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day17());
    }


    private class Intcode {
        private long a,b,c;
        private int position=0;
        int[] instructions;
        ArrayList<Long> outputs = new ArrayList<>();
        public Intcode(long a, long b, long c, String string) {
            this.a = a;
            this.b = b;
            this.c = c;
            String[] parts = string.split(",");
            instructions=new int[parts.length];
            for(int i=0;i<parts.length;i++){
                instructions[i]=Integer.parseInt(parts[i]);
            }
        }
        private String perform() {
            while(position>=0 && position<instructions.length) {
                int value = instructions[position+1];
                switch (instructions[position]) {
                    case 0 -> a = a/pow(getCombo(value),2);
                    case 1 -> b=b^value;
                    case 2 -> b=getCombo(value)%8;
                    case 3 -> {if(a!=0) position=value-2;}
                    case 4 -> b=b^c;
                    case 5 -> outputs.add(getCombo(value)%8);
                    case 6 -> b=a/pow(getCombo(value),2);
                    case 7 -> c=a/pow(getCombo(value),2);
                    default -> throw new IllegalArgumentException("Invalid instruction at position " + position + ", value: "+instructions[position]);
                }
                position+=2;
            }
            return outputs.stream().map(i->""+i).collect(Collectors.joining(","));
        }
        private long getCombo(long val) {
            if(val ==0L) return 0L;
            if(val ==1L) return 1L;
            if(val ==2L) return 2L;
            if(val ==3L) return 3L;
            if(val ==4L) return a;
            if(val ==5L) return b;
            if(val ==6L) return c;
            throw new IllegalArgumentException("Invalid value at position " + position + ", value: "+val);
        }


    }
    private long pow(long val, int base) {
        long result =1;
        for(int i=0; i<val; i++) {
            result*=base;
        }
        return result;
    }
    @Override
    public Object solveA(List<String> input) {


        int a = Integer.parseInt(input.get(0).split(": ")[1]);
        int b = Integer.parseInt(input.get(1).split(": ")[1]);
        int c = Integer.parseInt(input.get(2).split(": ")[1]);
        String instructions = input.get(4).split(": ")[1];
        Intcode intcode = new Intcode(a, b, c, instructions);
        return intcode.perform();
    }

    @Override
    public Object solveB(List<String> input) {
        int b = Integer.parseInt(input.get(1).split(": ")[1]);
        int c = Integer.parseInt(input.get(2).split(": ")[1]);
        String instructions = input.get(4).split(": ")[1];

        long toHigh=Long.MAX_VALUE, toLow=0L;
        while(toHigh>toLow+1) {
            long test = (toHigh+toLow)/2;
            Intcode intcode = new Intcode(test,0,0, instructions);
            String result = intcode.perform();
            if(result.length()<instructions.length()) {toLow = test;}
            else {toHigh=test;}
        }
        int matchCounter=2;
        String lookfor=instructions.substring(instructions.length()-matchCounter);
        for(long a=toHigh;; ) {
            Intcode intcode = new Intcode(a, b, c, instructions);
            String result = intcode.perform();
            if(!result.endsWith(lookfor)) {
                long increase = pow(((instructions.length())-matchCounter)/2-1,8);
                a+=increase;
            }
            else {
                if(matchCounter<=instructions.length()+1) {
                    lookfor=instructions.substring(instructions.length()-matchCounter+1);
                }
                matchCounter+=2;
            }
            if(result.equals(instructions))
                return a;
        }
    }

}