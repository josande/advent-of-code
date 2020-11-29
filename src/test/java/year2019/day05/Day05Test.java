package year2019.day05;

import org.junit.jupiter.api.Test;

import static year2019.day05.Day05.runProgram;
import static year2019.day05.Day05.splitToInt;
import static org.junit.jupiter.api.Assertions.*;

class Day05Test {

    @Test
    void testA() {
        String input="1101,100,-1,4,0";
        runProgram(splitToInt(input));
    }
    @Test
    void testA2() {
        String input="3,0,4,0,99";
        Day05.inputValue =8;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 8);
    }
    @Test
    void testB2() {
        String input="3,9,8,9,10,9,4,9,99,-1,8";
        Day05.inputValue =8;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 1);
        Day05.inputValue =7;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 0);
    }
    @Test
    void testB21() {
        String input="3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9";
        Day05.inputValue =1;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 1);
        Day05.inputValue =0;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 0);
    }

    @Test
    void testB22() {
        String input="3,3,1105,-1,9,1101,0,0,12,4,12,99,1";
        Day05.inputValue =2;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 1);
        Day05.inputValue =0;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 0);
    }

    @Test
    void testB23() {
        String input="3,9,7,9,10,9,4,9,99,-1,8";
        Day05.inputValue =2;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 1);
        Day05.inputValue =8;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 0);
    }





    @Test
    void testB3() {
        String input="3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
        Day05.inputValue =8;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 1000);

        Day05.inputValue =7;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 999);

        Day05.inputValue =9;
        runProgram(splitToInt(input));
        assertEquals((int) Day05.outputValue, 1001);
    }






}