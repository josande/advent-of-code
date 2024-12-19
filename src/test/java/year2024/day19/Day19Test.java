package year2024.day19;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day19Test {
    @Test
    void testSolveA() {
        String input = """
                r, wr, b, g, bwu, rb, gb, br
                
                brwrr
                bggr
                gbbr
                rrbgbr
                ubwu
                bwurrg
                brgr
                bbrgwb
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(6L, new Day19().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                r, wr, b, g, bwu, rb, gb, br
                
                brwrr
                bggr
                gbbr
                rrbgbr
                ubwu
                bwurrg
                brgr
                bbrgwb
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(16L, new Day19().solveB(inputs));
    }
}