package year2016.day01;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

    @Test
    void testSolveA() {
        assertEquals(5, new Day01().solveA(List.of("R2, L3")));
        assertEquals(4, new Day01().solveA(List.of("R1, R2, R3, R4")));
        assertEquals(4, new Day01().solveA(List.of("L1, L2, L3, L4")));
        assertEquals(2, new Day01().solveA(List.of("R2, R2, R2")));
        assertEquals(12,new Day01().solveA(List.of("R5, L5, R5, R3")));
        assertEquals(0, new Day01().solveA(List.of("R5, R5, R5, R5, R5, R5, R5, R5")));
    }

    @Test
    void testSolveB() {
        assertEquals(4, new Day01().solveB(List.of("R8, R4, R4, R8")));

    }
}