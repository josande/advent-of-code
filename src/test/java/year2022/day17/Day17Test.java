package year2022.day17;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

    @Test
    void testSolveA(){
        String input = """
                >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(3068, Day17.solveA(values));
    }
    @Test
    void testSolveB() {
        String input = """
                >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(1514285714288L, Day17.solveB(values));
    }
}