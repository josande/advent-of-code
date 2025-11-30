package year2021.day06;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {
    @Test
    void solveA() {
        String input = """
                3,4,3,1,2
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(5934L, Day06.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                3,4,3,1,2
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(26984457539L, Day06.solveB(values));
    }
}