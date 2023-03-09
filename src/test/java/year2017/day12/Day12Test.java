package year2017.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    @Test
    void testSolveA() {
        String input = """
                0 <-> 2
                1 <-> 1
                2 <-> 0, 3, 4
                3 <-> 2, 4
                4 <-> 2, 3, 6
                5 <-> 6
                6 <-> 4, 5
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(6, new Day12().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                0 <-> 2
                1 <-> 1
                2 <-> 0, 3, 4
                3 <-> 2, 4
                4 <-> 2, 3, 6
                5 <-> 6
                6 <-> 4, 5
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(2, new Day12().solveB(inputs));
    }
}
