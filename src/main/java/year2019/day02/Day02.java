package year2019.day02;

import utils.FileHelper;

import java.util.List;

public class Day02 {

    static void doOpCode(int a, int b, int c, int d, Integer[] values) {
        switch(a) {
            case 1: values[d]=values[b]+values[c]; return;
            case 2: values[d]=values[b]*values[c]; return;
        }
    }
    static int runProgram(int noun, int verb, String input) {
        Integer[] values = splitToInt(input);
        setStartValues(noun, verb, values);
        return runProgram(values);
    }

    static int runProgram(Integer[] values) {
        int position=0;
        while(position >= 0) {
            position = doOpCode(position, values);
        }
        return values[0];
    }

    private static void setStartValues(int noun, int verb, Integer[] values) {
        values[1]=noun;
        values[2]=verb;
    }
    static Integer doOpCode(int position, Integer[] values) {

        switch(values[position]) {
            case 1: values[values[position+3]] = values[values[position+1]] + values[values[position+2]]; return position+4;
            case 2: values[values[position+3]] = values[values[position+1]] * values[values[position+2]]; return position+4;
            case 99: return -1;
            default : System.out.println("Unknown code: "+values[position]);
        }
        return null;
    }
    static Integer[] splitToInt(String input) {
        String[] inputs = input.split(",");
        Integer[] values = new Integer[inputs.length];
        for (int i=0;i<inputs.length; i++) {
            values[i]= Integer.parseInt(inputs[i]);
        }
        return values;
    }
    static int solveB(String input) {
        for (int verb=0; verb<=99; verb++) {
            for(int noun=0; noun<=99; noun++) {
                runProgram(noun, verb, input);
                if (runProgram(noun, verb, input) == 19690720) {
                    return 100*noun+verb;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args){
        String input = new FileHelper().readFile("year2019/day02/input.txt").get(0);
        System.out.println("Day02A "+runProgram(12,2, input));
        System.out.println("Day02B "+solveB(input));
    }
}
