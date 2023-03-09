package year2017.day15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {

    @Test
    void testSolveA() {
        String input = """
                Generator A starts with 65
                Generator B starts with 8921
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(588, new Day15().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                Generator A starts with 65
                Generator B starts with 8921
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(309, new Day15().solveB(inputs));
    }
}
