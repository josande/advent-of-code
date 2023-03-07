package year2017.day02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    @Test
    void testSolveA() {
        String input = """
                5 1 9  5
                7 5 3
                2 4 6 8
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(18, new Day02().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                5 9 2 8
                9 4 7 3
                3 8 6 5
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(9, new Day02().solveB(inputs));
    }
}
