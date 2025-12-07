package year2025.day06;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {

    @Test
    void testSolveA() {
        String input = """
                123 328  51 64\s
                 45 64  387 23\s
                  6 98  215 314
                *   +   *   + \s
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(4277556L, new Day06().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                123 328  51 64\s
                 45 64  387 23\s
                  6 98  215 314
                *   +   *   + \s
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(3263827L, new Day06().solveB(inputs));
    }
}