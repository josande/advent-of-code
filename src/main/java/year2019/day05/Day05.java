package year2019.day05;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;

public class Day05 {

    static int inputValue = 1;
    static Integer outputValue =null;
    static int runProgram(int noun, int verb, String input) {
        Integer[] values = splitToInt(input);
        setStartValues(noun, verb, values);
        return runProgram(values);
    }

    static int runProgram(String input) {
        runProgram(splitToInt(input));
        return outputValue;

    }
    static int runProgram(Integer[] values) {
        Integer position=0;
        int output=-1;
        while(position != null && position >= 0) {
            position = doOpCode(position, values);
        }

        return output;
    }

    private static void setStartValues(int noun, int verb, Integer[] values) {
        values[1]=noun;
        values[2]=verb;
    }
    static Integer[] splitToInt(String input) {
        String[] inputs = input.split(",");
        Integer[] values = new Integer[inputs.length];
        for (int i=0;i<inputs.length; i++) {
            values[i]= Integer.parseInt(inputs[i]);
        }
        return values;
    }
    static private int getModifier(int variableNumber, int opCode) {
        opCode/=100; // Ignore last 2 digits
        for (int i=1; i<variableNumber;i++) {
            opCode/=10;
        }
        return opCode%10;
    }
    static private int getVariableValue(int variableNumber, int position, Integer[] values) {
        int opCode=values[position];
        int modifier = getModifier(variableNumber, opCode);
        if (modifier==1) { // Immediate mode
            return values[position + variableNumber];
        } else if (modifier==0){ // Positional mode
            return values[values[position + variableNumber]];
        }
        System.out.println("Unknown variable value, position="+position+", variable number="+variableNumber+", opCode="+opCode);
        return -1;
    }
    static Integer doOpCode(int position, Integer[] values) {
        int opCodeOrg=values[position];

        switch (opCodeOrg % 100) {
            case 1 -> {
                int varA = getVariableValue(1, position, values);
                int varB = getVariableValue(2, position, values);
                int varC = values[position + 3];
                values[varC] = varA + varB;
                position += 4;
                return position;
            }
            case 2 -> {
                int varA = getVariableValue(1, position, values);
                int varB = getVariableValue(2, position, values);
                int varC = values[position + 3];

                values[varC] = varA * varB;
                position += 4;
                return position;
            }
            case 3 -> {
                int varA = values[position + 1];
                values[varA] = inputValue;
                return position + 2;
            }
            case 4 -> {
                outputValue = getVariableValue(1, position, values);
                return position + 2;
            }
            case 5 -> {
                int varA = getVariableValue(1, position, values);
                int varB = getVariableValue(2, position, values);
                if (varA != 0) {
                    return varB;
                } else {
                    return position + 3;
                }
            }
            case 6 -> {
                int varA = getVariableValue(1, position, values);
                int varB = getVariableValue(2, position, values);
                if (varA == 0) {
                    return varB;
                } else {
                    return position + 3;
                }
            }
            case 7 -> {
                int varA = getVariableValue(1, position, values);
                int varB = getVariableValue(2, position, values);
                int varC = values[position + 3];

                if (varA < varB) {
                    values[varC] = 1;
                } else {
                    values[varC] = 0;
                }
                return position + 4;
            }
            case 8 -> {
                int varA = getVariableValue(1, position, values);
                int varB = getVariableValue(2, position, values);
                int varC = values[position + 3];

                if (varA == varB) {
                    values[varC] = 1;
                } else {
                    values[varC] = 0;
                }
                return position + 4;
            }
            case 99 -> {
                return -1;
            }
            default -> System.out.println("Unknown code: " + values[position]);
        }
        return position;
    }
    public static void main() {

        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        inputValue=1;
        runProgram(inputs.get(0));
        System.out.println("Day05A "+outputValue);
        inputValue=5;
        runProgram(inputs.get(0));

        System.out.println("Day05B "+outputValue);
    }


}

