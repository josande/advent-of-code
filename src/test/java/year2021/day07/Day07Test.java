package year2021.day07;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Test {
    @Test
    void solveA() {
        String input = """
                16,1,2,0,4,2,7,1,2,14
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(37, Day07.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                16,1,2,0,4,2,7,1,2,14
                 """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(168, Day07.solveB(values));
    }
}