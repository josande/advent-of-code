package year2020.day02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class Day02Test {

    @Test
    void solveSolveA() {
        String input =
                """
                1-3 a: abcde
                1-3 b: cdefg
                2-9 c: ccccccccc
                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());
        assertEquals(2, Day02.solveA(values));

    }
    @Test
    void solveSolveB() {
        String input =
                """
                1-3 a: abcde
                1-3 b: cdefg
                2-9 c: ccccccccc
                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());

        assertEquals(1, Day02.solveB(values));
    }
}