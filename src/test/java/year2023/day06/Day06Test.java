package year2023.day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {

    @Test
    void testSolveA() {
        String input = """
                Time:      7  15   30
                Distance:  9  40  200
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(288L, new Day06().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                Time:      7  15   30
                Distance:  9  40  200
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(71503L, new Day06().solveB(inputs));
    }
}
