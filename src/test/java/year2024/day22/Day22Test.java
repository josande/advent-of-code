package year2024.day22;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day22Test {
    @Test
    void testSolveA() {
        String input = """
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals("Not yet implemented", new Day22().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals("Not yet implemented", new Day22().solveB(inputs));
    }
}