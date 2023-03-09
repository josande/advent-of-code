package year2017.day13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {

    @Test
    void testSolveA() {
        String input = """
                0: 3
                1: 2
                4: 4
                6: 4
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(24, new Day13().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                0: 3
                1: 2
                4: 4
                6: 4
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(10, new Day13().solveB(inputs));
    }
}
