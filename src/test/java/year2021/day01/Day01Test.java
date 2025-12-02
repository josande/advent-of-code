package year2021.day01;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {
    @Test
    void solveA() {
        String input = """
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(7, Day01.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(5, Day01.solveB(values));
    }
}