package year2020.day25;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day25Test {
    @Test
    void solveA() {
        String input =
                """
                5764801
                17807724
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(14897079, Day25.solveA(values));
    }


}