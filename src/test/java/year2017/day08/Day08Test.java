package year2017.day08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {

    @Test
    void testSolveA() {
        String input = """
                b inc 5 if a > 1
                a inc 1 if b < 5
                c dec -10 if a >= 1
                c inc -20 if c == 10
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(1, new Day08().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                b inc 5 if a > 1
                a inc 1 if b < 5
                c dec -10 if a >= 1
                c inc -20 if c == 10
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(10, new Day08().solveB(inputs));
    }
}
