package year2025.day05;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    @Test
    void testSolveA() {
        String input = """
                3-5
                10-14
                16-20
                12-18
                
                1
                5
                8
                11
                17
                32
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(3, new Day05().solveA(inputs));
    }

    @Test
    void testSolveB() {
        String input = """
                3-5
                10-14
                12-18
                16-20
                
                1
                5
                8
                11
                17
                32
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(14L, new Day05().solveB(inputs));
    }
}
