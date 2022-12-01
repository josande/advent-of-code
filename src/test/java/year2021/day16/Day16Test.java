package year2021.day16;

import org.junit.jupiter.api.Test;
import year2021.day11.Day11;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Test {
    @Test
    void solveA() {
        String input = """
                C0015000016115A2E0802F182340
                """;
//                C0015000016115A2E0802F182340
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(23L, Day16.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                C200B40A82
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(3L, Day16.solveB(values));
    }
}