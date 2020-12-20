package year2020.day18;

import org.junit.jupiter.api.Test;
import year2020.day17.Day17;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {

    @Test
    void solveA() {
        String input =
                """
                1 + 2 * 3 + 4 * 5 + 6
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(71, Day18.solveA(values));
    }

    @Test
    void solveB() {
        String input =
                """
                1 + 2 * 3 + 4 * 5 + 6
                5 + (8 * 3 + 9 + 3 * 4 * 3)
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(231+1445, Day18.solveB(values));
    }

}