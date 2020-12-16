package year2020.day20;

import org.junit.jupiter.api.Test;
import year2020.day17.Day17;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day20Test {
    @Test
    void solveA() {
        String input =
                """
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(-1, Day20.solveA(values));
    }

    @Test
    void solveB() {
        String input =
                """
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(-1, Day20.solveB(values));
    }
}