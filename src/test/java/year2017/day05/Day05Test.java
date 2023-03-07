package year2017.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    @Test
    void testSolveA() {
        String input = """
                0
                3
                0
                1
                -3
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(5, new Day05().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                0
                3
                0
                1
                -3
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(10, new Day05().solveB(inputs));
    }
}
