package year2017.day24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day24Test {

    @Test
    void testSolveA() {
        String input = """
                0/2
                2/2
                2/3
                3/4
                3/5
                0/1
                10/1
                9/10
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(31, new Day24().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                0/2
                2/2
                2/3
                3/4
                3/5
                0/1
                10/1
                9/10
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(19, new Day24().solveB(inputs));
    }
}
