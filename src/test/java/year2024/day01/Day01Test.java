package year2024.day01;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    void testSolveA() {
        String input = """
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(11, new Day01().solveA(inputs));
    }

    @Test
    void testSolveB() {
        String input = """
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(31, new Day01().solveB(inputs));
    }
}
