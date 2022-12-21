package year2022.day21;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 {

    static Object solveA(List<String> values) {
        HashMap<String, String> monkeys = new HashMap<>();
        for(String value : values) {
            monkeys.put(value.split(": ")[0], value.substring(value.indexOf(": ")+2));
        }

        long result = getNumber(monkeys.get("root"), monkeys);

        return result;
    }

    private static long getNumber(String string, HashMap<String, String> monkeys) {

        try {
            long value = Long.parseLong(string);
            return value;
        } catch (NumberFormatException e) {
            String p1 = string.split(" ")[0];
            String p2 = string.split(" ")[2];
            String op = string.split(" ")[1];

            long val1 = getNumber(monkeys.get(p1), monkeys);
            long val2 = getNumber(monkeys.get(p2), monkeys);

            long result;
            switch (op) {
                case "+" -> result = val1 + val2;
                case "-" -> result = val1 - val2;
                case "*" -> result = val1 * val2;
                case "/" -> result = val1 / val2;
                default -> {throw new IllegalArgumentException(val1+op+val2);}

            }
            return result;
        }
    }
    static Object solveB(List<String> values) {
        HashMap<String, String> monkeys = new HashMap<>();
        for (String value : values) {
            monkeys.put(value.split(": ")[0], value.substring(value.indexOf(": ") + 2));
        }
        String equation = monkeys.get("root");
        equation = equation
                .replace("+", "=")
                .replace("-", "=")
                .replace("*", "=")
                .replace("/", "=");

        monkeys.remove("humn");

        String before = "";

        while (!before.equals(equation)){
            before = equation;
            for (Map.Entry<String, String> monkey : monkeys.entrySet()) {
                equation = equation.replace(monkey.getKey(), "(" +monkey.getValue()+")");
            }
        }

        before = "";
        String left = equation.split("=")[0];
        String right = equation.split("=")[1];

        while (!before.equals(left)){
            before = left;
            left = simplifyEquation(left);
        }
        before = "";
        while (!before.equals(right)){
            before = right;
            right = simplifyEquation(right);
        }

        left = left.replaceAll("\\[", "(").replaceAll("]", ")").replaceAll(" ", "");
        right = right.replaceAll("\\[", "(").replaceAll("]", ")").replaceAll(" ", "");

        long value;
        if(left.contains("humn")) {
            value = Long.parseLong(right);
            equation = left;
        } else {
            value = Long.parseLong(left);
            equation = right;
        }

        Long result = solve(equation, value);

        return result;

    }

    private static long solve(String equation, long value) {
        String before = "";
        while(!before.equals(equation)) {
            while (equation.startsWith("(") && equation.endsWith(")")) {
                equation = equation.substring(1, equation.length() - 1);
            }
            if (equation.startsWith("(")) {
                int lastIndex = 0;
                lastIndex = Math.max(lastIndex, equation.lastIndexOf("+"));
                lastIndex = Math.max(lastIndex, equation.lastIndexOf("-"));
                lastIndex = Math.max(lastIndex, equation.lastIndexOf("*"));
                lastIndex = Math.max(lastIndex, equation.lastIndexOf("/"));

                Long val = Long.parseLong(equation.substring(lastIndex + 1));
                switch (equation.charAt(lastIndex)) {
                    case '+' -> value -= val;
                    case '-' -> value += val;
                    case '*' -> value /= val;
                    case '/' -> value *= val;
                    default -> throw new IllegalArgumentException(value + " " + val);
                }
                equation = equation.substring(0, lastIndex);
            } else if (equation.endsWith(")")) {
                int firstIndex = equation.length();
                if(equation.contains("+"))
                    firstIndex = Math.min(firstIndex, equation.indexOf("+"));
                if(equation.contains("-"))
                    firstIndex = Math.min(firstIndex, equation.indexOf("-"));
                if(equation.contains("*"))
                    firstIndex = Math.min(firstIndex, equation.indexOf("*"));
                if(equation.contains("/"))
                    firstIndex = Math.min(firstIndex, equation.indexOf("/"));

                Long val = Long.parseLong(equation.substring(0, firstIndex));
                switch (equation.charAt(firstIndex)) {
                    case '+' -> value -= val;
                    case '-' -> value = -(value-val);
                    case '*' -> value /= val;
                    case '/' -> value *= val;
                    default -> throw new IllegalArgumentException(value + " " + val);
                }
                equation = equation.substring(firstIndex+1);
            } else {
                int index = 0;
                index = Math.max(index, equation.indexOf("+"));
                index = Math.max(index, equation.indexOf("-"));
                index = Math.max(index, equation.indexOf("*"));
                index = Math.max(index, equation.indexOf("/"));


                Long val;
                try {
                    val = Long.parseLong(equation.substring(0, index));
                } catch (NumberFormatException e) {
                    val = Long.parseLong(equation.substring(index + 1));
                }
                switch (equation.charAt(index)) {
                    case '+' -> value -= val;
                    case '-' -> value += val;
                    case '*' -> value /= val;
                    case '/' -> value *= val;
                    default -> throw new IllegalArgumentException(value + " " + val);

                }
                return value;
            }
        }
        return Long.parseLong(equation);

    }

    private static String simplifyEquation(String equation) {
        if(!equation.contains("(")) return equation;

        String part = equation.substring(0,equation.indexOf(")"));
        part = part.substring(part.lastIndexOf("(")+1);

        long value;

        try {
            value = Long.parseLong(part);
        } catch (NumberFormatException e) {
            String p1 = part.split(" ")[0];
            String p2 = part.split(" ")[2];
            String op = part.split(" ")[1];

            if (p1.contains("humn") || p2.contains("humn")) {
                equation = equation.replace("("+part+")", "["+p1+op+p2+"]");
                return equation;
            }

            long val1 = Long.parseLong(p1);
            long val2 = Long.parseLong(p2);

            switch (op) {
                case "+" -> value = val1 + val2;
                case "-" -> value = val1 - val2;
                case "*" -> value = val1 * val2;
                case "/" -> value = val1 / val2;
                default -> {
                    throw new IllegalArgumentException(val1 + op + val2);
                }
            }
        }

        equation = equation.replace("("+part+")", ""+value);

        return equation;

    }

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 142707821472432
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 3587647562851

    }
}
