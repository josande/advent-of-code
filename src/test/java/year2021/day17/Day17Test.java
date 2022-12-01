package year2021.day17;

import org.junit.jupiter.api.Test;
import year2021.day11.Day11;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {
    @Test
    void solveA() {
        String input = """
                target area: x=20..30, y=-10..-5
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(45, Day17.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                target area: x=20..30, y=-10..-5
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(112, Day17.solveB(values));
    }
}