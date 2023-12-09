package year2023.day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test {

    @Test
    void testSolveA() {
        String input = """
                0 3 6 9 12 15
                1 3 6 10 15 21
                10 13 16 21 30 45
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(114L, new Day09().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                0 3 6 9 12 15
                1 3 6 10 15 21
                10 13 16 21 30 45
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(2L, new Day09().solveB(inputs));
    }
}
