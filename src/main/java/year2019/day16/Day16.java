package year2019.day16;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class Day16 {


    static int[] splitInput(String input, int repeat) {
        int length=input.length();
        int[] numbers=new int[length*repeat];
        int pos=0;
        for (int r=0;r<repeat;r++) {
            for (char c : input.toCharArray()) {
                numbers[pos] = Integer.parseInt(String.valueOf(c));
                pos++;
            }
        }
        return numbers;
    }
    static int[] runPhases(int[] input, int phases) {
        return runPhases(input, phases,false);
    }
    static int[] runPhases(int[] input, int phases, boolean onlySecondHalf) {
        int[] result=input.clone();
        for(int p=0;p<phases;p++) {
            result=calculatePhase(result, onlySecondHalf);
        }
        return result;
    }
    static int[] calculatePhase(int[] input) {
        return calculatePhase(input, false);
    }
    static int[] calculatePhase(int[] input, boolean onlySecondHalf) {
        int[] result = new int[input.length];
        if (onlySecondHalf) {
            result[result.length - 1] = input[result.length - 1];
            for (int i = result.length - 2; i > 0; i--) {
                result[i] = (input[i] + result[i + 1]) % 10;
            }
            return result;
        }

        int patternOffset=1;
        int repeat = 1;
        for (int times =0; times<input.length;times++) {
            int sum=0;
            for (int i=0; i<input.length;i++) {
                sum += input[i]*getPatternValue(i, patternOffset, repeat);
            }
            result[times]=Math.abs(sum%10);
            repeat++;
        }
        return result;
    }

    static int getFirstEight(int[] values) {
        String res = Arrays.toString(values).replaceAll("\\[|]|,|\\s", "");
        return  Integer.parseInt(res.substring(0,8));
    }

    static int getPatternValue(int position, int offset, int repeat) {
        position=(position+offset)%(repeat*4);
        if(position/repeat==3) {
            return -1;
        }
        if(position/repeat==1) {
            return 1;
        }
        return 0;
    }
    public static void main(String[] args) {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");
        int[] start=splitInput(inputs.get(0),1);

        int[] result =runPhases(start,100);
        int ansA=getFirstEight(result);

        int offset=Integer.parseInt(inputs.get(0).substring(0,7));
        start=splitInput(inputs.get(0), 10000);
        result = runPhases(start, 100, true);

        String res = Arrays.toString(result).replaceAll("\\[|]|,|\\s", "");
        String ansB = res.substring(offset,offset+8);

        System.out.println("Day16A: "+ansA);
        System.out.println("Day16B: "+ansB);

    }
}