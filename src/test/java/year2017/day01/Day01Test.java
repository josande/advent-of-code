package year2017.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    void testSolveA() {
        assertEquals(3, new Day01().solveA(List.of("1122")));
        assertEquals(4, new Day01().solveA(List.of("1111")));
        assertEquals(0, new Day01().solveA(List.of("1234")));
        assertEquals(9, new Day01().solveA(List.of("91212129")));
    }
    @Test
    void testSolveB() {
        assertEquals(6, new Day01().solveB(List.of("1212")));
        assertEquals(0, new Day01().solveB(List.of("1221")));
        assertEquals(4, new Day01().solveB(List.of("123425")));
        assertEquals(12, new Day01().solveB(List.of("123123")));
        assertEquals(4, new Day01().solveB(List.of("12131415")));
    }
}
