package year2016.day01;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

    @Test
    void testSolveA() {
        assertEquals(5, Day01.solveA(Arrays.asList("R2, L3")));
        assertEquals(4, Day01.solveA(Arrays.asList("R1, R2, R3, R4")));
        assertEquals(4, Day01.solveA(Arrays.asList("L1, L2, L3, L4")));
        assertEquals(2, Day01.solveA(Arrays.asList("R2, R2, R2")));
        assertEquals(12, Day01.solveA(Arrays.asList("R5, L5, R5, R3")));
        assertEquals(0, Day01.solveA(Arrays.asList("R5, R5, R5, R5, R5, R5, R5, R5")));
    }

    @Test
    void testSolveB() {
        assertEquals(4, Day01.solveB(Arrays.asList("R8, R4, R4, R8")));

    }
}