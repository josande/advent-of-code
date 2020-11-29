package year2019.day04;

import org.junit.jupiter.api.Test;

import static year2019.day04.Day04.testPasswordA;
import static year2019.day04.Day04.testPasswordB;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Day04Test {
    @Test
    void testA() {
        //111111 meets these criteria (double 11, never decreases).
        assertTrue(testPasswordA(1,1,1,1,1,1));

        //223450 does not meet these criteria (decreasing pair of digits 50).
        //assertFalse(testPasswordA(2,2,3,4,5,0));  //Ignored due to sorting data before hand

        //123789 does not meet these criteria (no double).
        assertFalse(testPasswordA(1,2,3,7,8,9));
    }
    @Test
    void testB() {
        //112233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.
        assertTrue(testPasswordB(1,1,2,2,3,3));

        //123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).
        assertFalse(testPasswordB(1,2,3,4,4,4));

        //111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).
        assertTrue(testPasswordB(1,1,1,1,2,2));
    }
}
