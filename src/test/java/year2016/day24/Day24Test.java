package year2016.day24;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day24Test {
    @Test
    void testSolveA() {
        String input = """
                ###########
                #0.1.....2#
                #.#######.#
                #4.......3#
                ###########
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(14, Day24.solveA(inputs));
    }

}
