package year2021.day02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

    @Test
    void testSolveA(){
        String input = """
                forward 5
                down 5
                forward 8
                up 3
                down 8
                forward 2
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(150, Day02.solveA(values));
    }
    @Test
    void testSolveB() {
        String input = """
                forward 5
                down 5
                forward 8
                up 3
                down 8
                forward 2
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(900, Day02.solveB(values));
    }
}