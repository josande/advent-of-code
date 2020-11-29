package year2019.day16;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static year2019.day16.Day16.*;
import static org.junit.jupiter.api.Assertions.*;

class Day16Test {

    @Test
    void test16A() {
        int[] input=splitInput("12345678",1);
      //  setUpPatterns(input.length);
        int[] ans = {4,8,2,2,6,1,5,8};
        int[] result=calculatePhase(input);

        assertArrayEquals(ans, result);
    }

    @Test
    void test16A2() {
        int[] input=splitInput("12345678",1);
      //  setUpPatterns(input.length);
        int[] ans = splitInput("34040438",1);
        int[] result=runPhases(input,2 );
        assertArrayEquals(ans, result);
    }
    @Test
    void test16A2b() {
        int[] input=splitInput("12345678",1);
        //  setUpPatterns(input.length);
        int[] ans = splitInput("01029498",1);
        int[] result=runPhases(input,4 );

        assertArrayEquals(ans, result);
    }
    @Test
    void test16A3() {
        int[] input=splitInput("69317163492948606335995924319873",1);
       // setUpPatterns(input.length);
        int[] result=runPhases(input,100 );
        int ans =52432133;
        int res=getFirstEight(result);
        assertEquals(ans, res);
    }

    @Test
    void test16B1() {
        int[] input=splitInput("03036732577212944063491565474664",10000);

        String ans ="84462026";
        int[] result=runPhases(input,100, true );

        String res = Arrays.toString(result).replaceAll("\\[|]|,|\\s", "");
        int offset= Integer.parseInt("03036732577212944063491565474664".substring(0,7));

        assertEquals(ans, res.substring(offset,offset+8));
    }
    @Test
    void test16B2() {
        int[] input=splitInput("02935109699940807407585447034323",10000);

        String ans ="78725270";
        int[] result=runPhases(input,100, true );

        String res = Arrays.toString(result).replaceAll("\\[|]|,|\\s", "");
        int offset= Integer.parseInt("02935109699940807407585447034323".substring(0,7));

        assertEquals(ans, res.substring(offset,offset+8));
    }

}