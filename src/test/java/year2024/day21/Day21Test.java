package year2024.day21;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day21Test {
    @Test
    void testSolveA1() {
        assertEquals(68 * 29,  new Day21().solveA(List.of("029A")));
        assertEquals(60 * 980, new Day21().solveA(List.of("980A")));
        assertEquals(68 * 179, new Day21().solveA(List.of("179A")));
        assertEquals(64 * 456, new Day21().solveA(List.of("456A")));
        assertEquals(64 * 379, new Day21().solveA(List.of("379A")));
    }
    @Test
    void testSolveA() {
        String input = """
                029A
                980A
                179A
                456A
                379A
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(126384, new Day21().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals("Not yet implemented", new Day21().solveB(inputs));
    }
}