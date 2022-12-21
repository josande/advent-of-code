package year2022.day20;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Test {

    @Test
    void testSolveA(){
        String input = """
                1
                2
                -3
                3
                -2
                0
                4
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(3L, Day20.solveA(values));
    }
    @Test
    void testSolveB() {
        String input = """
                1
                2
                -3
                3
                -2
                0
                4
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(1623178306L, Day20.solveB(values));
    }
}