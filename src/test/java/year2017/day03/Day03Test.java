package year2017.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    @Test
    void testSolveA() {

        assertEquals(0, new Day03().solveA(List.of("1")));
        assertEquals(3, new Day03().solveA(List.of("12")));
        assertEquals(2, new Day03().solveA(List.of("23")));
        assertEquals(31, new Day03().solveA(List.of("1024")));
    }
    @Test
    void testSolveB() {
        assertEquals(5, new Day03().solveB(List.of("4")));
        assertEquals(747, new Day03().solveB(List.of("362")));
        assertEquals(806, new Day03().solveB(List.of("748")));

    }
}
