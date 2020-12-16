package year2020.day23;

import org.junit.jupiter.api.Test;
import year2020.day17.Day17;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day23Test {
    @Test
    void solveA() {
        String input =
                """
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(-1, Day23.solveA(values));
    }

    @Test
    void solveB() {
        String input =
                """
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(-1, Day23.solveB(values));
    }
}