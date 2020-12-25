package year2020.day22;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day22Test {
    @Test
    void solveA() {
        String input =
                """
                Player 1:
                9
                2
                6
                3
                1
                                
                Player 2:
                5
                8
                4
                7
                10
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(306, Day22.solveA(values));
    }

    @Test
    void solveB() {
        String input =
                """
                Player 1:
                9
                2
                6
                3
                1
                                
                Player 2:
                5
                8
                4
                7
                10
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(291L, Day22.solveB(values));
    }
}