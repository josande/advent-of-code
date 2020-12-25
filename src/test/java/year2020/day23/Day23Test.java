package year2020.day23;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day23Test {
    @Test
    void solveA() {
        String input =
                """
                389125467
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals("67384529", Day23.solveA(values));
    }

    @Test
    void solveB() {
        String input =
                """
                389125467
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(149245887792L, Day23.solveB(values));
    }
}