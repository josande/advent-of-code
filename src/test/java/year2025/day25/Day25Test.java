package year2025.day25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day25Test {

    @Test
    void testSolveA() {
        String input = """
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals("Not yet implemented", new Day25().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """

                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals("Merry X-mas!", new Day25().solveB(inputs));
    }
}
