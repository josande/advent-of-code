package year2020.day15;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

    @Test
    void solveA() {
        assertEquals(436, Day15.solveA(Arrays.asList("0,3,6")));
        assertEquals(1, Day15.solveA(Arrays.asList("1,3,2")));
        assertEquals(10, Day15.solveA(Arrays.asList("2,1,3")));
        assertEquals(27, Day15.solveA(Arrays.asList("1,2,3")));
        assertEquals(78, Day15.solveA(Arrays.asList("2,3,1")));
        assertEquals(438, Day15.solveA(Arrays.asList("3,2,1")));
        assertEquals(1836, Day15.solveA(Arrays.asList("3,1,2")));
    }

    @Test
    void solveB() {
        assertEquals(175594, Day15.solveB(Arrays.asList("0,3,6")));
        assertEquals(2578, Day15.solveB(Arrays.asList("1,3,2")));
        assertEquals(3544142, Day15.solveB(Arrays.asList("2,1,3")));
        assertEquals(261214, Day15.solveB(Arrays.asList("1,2,3")));
        assertEquals(6895259, Day15.solveB(Arrays.asList("2,3,1")));
        assertEquals(18, Day15.solveB(Arrays.asList("3,2,1")));
        assertEquals(362, Day15.solveB(Arrays.asList("3,1,2")));

    }
}