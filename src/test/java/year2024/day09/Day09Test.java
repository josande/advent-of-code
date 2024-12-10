package year2024.day09;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test {
    @Test
    void testSolveA() {
        String input = """
                2333133121414131402
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(1928L, new Day09().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                2333133121414131402
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(2858L, new Day09().solveB(inputs));
    }
}