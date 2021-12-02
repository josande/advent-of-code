package year2019.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static year2019.day02.Day02.runProgram;
import static year2019.day02.Day02.splitToInt;

class Day02Test {


    @Test
    void testPartA_Example1() {
        //1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2).

        String input="1,0,0,0,99";
        assertEquals(2, runProgram(splitToInt(input)));
    }

    @Test
    void testPartA_Example2() {
        //2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6).
        String input="2,3,0,3,99";
        assertEquals(2, runProgram(splitToInt(input)));
    }

    @Test
    void testPartA_Example3() {
        String input="2,4,4,5,99,0";
        assertEquals(2, runProgram(splitToInt(input)));
    }
    @Test
    void testPartA_Example4() {
        String input="1,1,1,4,99,5,6,0,99";
        assertEquals(30, runProgram(splitToInt(input)));
    }




}