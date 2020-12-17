package year2020.day17;

import org.junit.jupiter.api.Test;
import year2020.day12.Day12;
import year2020.day18.Day18;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {

    @Test
    void solveA() {
        String input =
                """
                .#.
                ..#
                ###
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(112, Day17.solveA(values));
    }

    @Test
    void solveB() {
        String input =
                """
                .#.
                ..#
                ###
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(848, Day17.solveB(values));
    }
}