package year2022.day02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

    @Test
    void testSolveA(){
        String input = """
                A Y
                B X
                C Z
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(15, Day02.solveA(values));
    }
    @Test
    void testSolveB() {
        String input = """
                A Y
                B X
                C Z
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(12, Day02.solveB(values));
    }
}