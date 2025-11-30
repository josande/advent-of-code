package year2017.day10;

import org.apache.commons.lang3.StringUtils;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day10());
    }

    @Override
    public Object solveA(List<String> input) {
        String[] inputs = input.get(0).split(",");
        int elements = inputs.length == 4 ?  5 : 256;
        Integer[] values = new Integer[elements];
        for(int i=0; i<elements; i++) {
            values[i]=i;
        }
        int position=0, skipSize=0;
        for(String step : inputs) {
            int length = Integer.parseInt(step);
            if(length<=elements) {
                ArrayList<Integer> toReverse = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    toReverse.add(values[(position + i) % elements]);
                }
                Collections.reverse(toReverse);
                for (int i = 0; i < length; i++) {
                    values[(position + i) % elements] = toReverse.get(i);
                }

                position+=length+skipSize;
                position=position%elements;
                skipSize++;
            }

        }
        return values[0]*values[1];
    }

    @Override
    public Object solveB(List<String> input) {

        ArrayList<Integer> inputs = new ArrayList<>();

        for(char c : input.get(0).toCharArray()) {
            inputs.add((int) c);
        }
        inputs.addAll(List.of(17, 31, 73, 47, 23));

        int elements = 256;
        Integer[] values = new Integer[elements];
        for(int i=0; i<elements; i++) {
            values[i]=i;
        }
        int position=0, skipSize=0;
        for(int round=0; round<64; round++) {
            for (Integer length : inputs) {
                if (length <= elements) {
                    ArrayList<Integer> toReverse = new ArrayList<>();
                    for (int i = 0; i < length; i++) {
                        toReverse.add(values[(position + i) % elements]);
                    }
                    Collections.reverse(toReverse);
                    for (int i = 0; i < length; i++) {
                        values[(position + i) % elements] = toReverse.get(i);
                    }

                    position += length + skipSize;
                    position = position % elements;
                    skipSize++;
                }
            }
        }
        StringBuilder result= new StringBuilder();
        for(int i=0; i<16; i++) {
            int temp=values[i*16];
            for(int j=1; j<16; j++) {
                temp^=values[i*16+j];
            }
            result.append(StringUtils.leftPad(Integer.toHexString(temp), 2, '0'));
        }
        return result.toString();
    }
}
