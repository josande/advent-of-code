package year2017.day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {

    @Test
    void testSolveA() {
        String input = """
                0   2   7   0
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(5, new Day06().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                0   2   7   0
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(4, new Day06().solveB(inputs));
    }
}
