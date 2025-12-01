package year2025.day01;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    void testSolveA() {
        String input = """
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(3, new Day01().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82
                R100
                R201
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(6, new Day01().solveB(inputs));
    }
}
