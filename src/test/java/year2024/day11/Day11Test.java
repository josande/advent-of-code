package year2024.day11;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {
    @Test
    void testSolveA() {
        String input = """
                125 17
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(55312L, new Day11().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                125 17
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(65601038650482L, new Day11().solveB(inputs));
    }
}