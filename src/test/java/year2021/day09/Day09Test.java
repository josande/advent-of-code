package year2021.day09;

import org.junit.jupiter.api.Test;
import year2021.day04.Day04;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
    @Test
    void solveA() {
        String input = """
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(null, Day09.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(null, Day09.solveB(values));
    }
}