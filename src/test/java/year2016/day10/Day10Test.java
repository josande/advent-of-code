package year2016.day10;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    @Test
    void testSolveB() {
        String input = """
                value 5 goes to bot 2
                bot 2 gives low to bot 1 and high to bot 0
                value 3 goes to bot 1
                bot 1 gives low to output 1 and high to bot 0
                bot 0 gives low to output 2 and high to output 0
                value 2 goes to bot 2
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(5*2*3, new Day10().solveB(values));

    }
}