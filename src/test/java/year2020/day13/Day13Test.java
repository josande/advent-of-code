package year2020.day13;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

    @Test
    void solveA() {
        String input =
                """
                939
                7,13,x,x,59,x,31,19
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(295, Day13.solveA(values));
    }

    @Test
    void solveB() {
        String input =
                """
                939
                7,13,x,x,59,x,31,19
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(1068781, Day13.solveB(values));
    }

    @Test
    void solveB2() {
        String input =
                """
                754018
                6,1,4
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(6, Day13.solveB(values));
    }



}