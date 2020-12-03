package year2019.day16;

import utils.FileHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        int result[] = new int[input.length];
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

    static HashMap<Integer, List<Integer>> patterns = new HashMap<>();
    static void setUpPatterns(int length) {
        int[] pattern = {0,1,0,-1};

        for (int repeat=1;repeat<=length;repeat++) {
            List<Integer> resultList= new ArrayList<>();
            while(resultList.size() <= (length+1)) {
                for (int p : pattern) {
                    for (int i=0;i<repeat;i++) {
                        resultList.add(p);
                    }
                }
            }
            patterns.put(repeat, resultList);
        }
    }
    static int getFirstEight(int[] values) {
        String res = Arrays.toString(values).replaceAll("\\[|\\]|,|\\s", "");;
        return  Integer.valueOf(res.substring(0,8));
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
        long t0=System.currentTimeMillis();
        String input = new FileHelper().readFile("year2019/day16/input.txt").get(0);
        int[] start=splitInput(input,1);

        int[] result =runPhases(start,100);
        int ansA=getFirstEight(result);

        int offset=Integer.valueOf(input.substring(0,7));
        start=splitInput(input, 10000);
        result = runPhases(start, 100, true);

        String res = Arrays.toString(result).replaceAll("\\[|\\]|,|\\s", "");;
        String ansB = res.substring(offset,offset+8);

        System.out.println("Day16A: "+ansA);
        System.out.println("Day16B: "+ansB);
        System.out.println("time: "+(System.currentTimeMillis()-t0)+" ms");

    }
}