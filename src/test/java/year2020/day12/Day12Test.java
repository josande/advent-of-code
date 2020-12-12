package year2020.day12;

import org.junit.jupiter.api.Test;
import year2020.day10.Day10;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    @Test
    void solveA() {
        String input =
                """
                F10
                N3
                F7
                R90
                F11
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(25, Day12.solveA(values));
    }

    @Test
    void solveB() {
        String input =
                """
                F10
                N3
                F7
                R90
                F11
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(286, Day12.solveB(values));
    }
}