package year2017.day21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Day21Test {

    @Test
    void testSolveA() {
        String input = """
                ../.# => ##./#../...
                .#./..#/### => #..#/..../..../#..#
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(12L, new Day21().solveA(inputs));
    }
}
