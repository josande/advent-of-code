package year2025.day06;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.List;

public class Day06 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day06());
    }

    @Override
    public Object solveA(List<String> input) {
        List<String> copy = new ArrayList<>(input);
        List<String[]> values = new java.util.ArrayList<>();
        String[] operators = copy.getLast().split("\\s++");
        copy.removeLast();
        for (String line : copy) {
            if(line.isBlank()) continue;
            values.add(line.trim().split("\\s+"));
        }
        long result =0L;
        for(int i = 0; i<operators.length; i++) {
            long temp=operators[i].equals("+")?0L:1L;
            switch(operators[i]) {
                case "+" -> {for(var value : values) temp += Long.parseLong(value[i]);}
                case "*" -> {for(var value : values) temp *= Long.parseLong(value[i]);}
            }
            result+=temp;
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        List<String> copy = new ArrayList<>(input);
        List<Character> operators = new ArrayList<>();
        String lastRow = copy.getLast();
        copy.removeLast();
        for(int i=0; i<lastRow.length(); i++) {
            if(lastRow.charAt(i)==' ') continue;
            operators.add(lastRow.charAt(i));
        }
        List<String> rotated = new ArrayList<>();
        for(int i =0; i<copy.getFirst().length(); i++) {
            StringBuilder rotRow = new StringBuilder();
            for(var row:copy) rotRow.append(row.charAt(i));
            rotated.add(rotRow.toString().trim());
        }
        long result = 0L;

        for(Character operator : operators) {
            long tempValue = operator == '+' ? 0L : 1L;
            if(rotated.isEmpty()) break;
            if(rotated.getFirst().isBlank()) rotated.removeFirst();
            String firstValue = rotated.getFirst();
            while(!firstValue.isEmpty()) {
                switch(operator) {
                    case '+' -> tempValue += Long.parseLong(firstValue);
                    case '*' -> tempValue *= Long.parseLong(firstValue);
                }
                rotated.removeFirst();
                if(rotated.isEmpty()) break;

                firstValue = rotated.getFirst();
            }
            result += tempValue;

            if(!rotated.isEmpty()) rotated.removeFirst();
        }
        return result;
    }
}
