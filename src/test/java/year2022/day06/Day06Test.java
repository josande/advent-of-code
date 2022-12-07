package year2022.day06;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

    @Test
    void testSolveA(){
        String input = """
                mjqjpqmgbljsphdztnvjfqwrcgsmlb
                """;
        var values = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(7, Day06.solveA(values));
    }
    @Test
    void testSolveB() {
        String input = """
                mjqjpqmgbljsphdztnvjfqwrcgsmlb
                """;
        var values = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(19, Day06.solveB(values));
    }
}