package year2021.day24;

import org.junit.jupiter.api.Test;
import year2021.day11.Day11;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Test {
    @Test
    void solveA() {
        String input = """
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(null, Day24.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(null, Day24.solveB(values));
    }
}