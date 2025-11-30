package year2021.day21;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {
    @Test
    void solveA() {
        String input = """
                Player 1 starting position: 4
                Player 2 starting position: 8
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(739785L, Day21.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                Player 1 starting position: 4
                Player 2 starting position: 8
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(444356092776315L, Day21.solveB(values));
    }
}