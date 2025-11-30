package year2021.day09;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
    @Test
    void solveA() {
        String input = """
                2199943210
                3987894921
                9856789892
                8767896789
                9899965678
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(15, Day09.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                2199943210
                3987894921
                9856789892
                8767896789
                9899965678
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(1134, Day09.solveB(values));
    }
}