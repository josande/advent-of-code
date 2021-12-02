package year2021.day03;

import org.junit.jupiter.api.Test;
import year2021.day02.Day02;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {

    @Test
    void solveA() {
        String input = """
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(null, Day03.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(null, Day03.solveB(values));
    }
}