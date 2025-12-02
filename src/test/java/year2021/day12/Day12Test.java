package year2021.day12;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {
    @Test
    void solveA() {
        String input = """
                start-A
                start-b
                A-c
                A-b
                b-d
                A-end
                b-end
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(10, Day12.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                start-A
                start-b
                A-c
                A-b
                b-d
                A-end
                b-end
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(36, Day12.solveB(values));
    }
}