package year2025.day03;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    @Test
    void testSolveA() {
        String input = """
                987654321111111
                811111111111119
                234234234234278
                818181911112111
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(357, new Day03().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                987654321111111
                811111111111119
                234234234234278
                818181911112111
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(3121910778619L, new Day03().solveB(inputs));
    }
}
