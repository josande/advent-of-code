package year2023.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {

    @Test
    void testSolveA() {
        String input = """
                ...#......
                .......#..
                #.........
                ..........
                ......#...
                .#........
                .........#
                ..........
                .......#..
                #...#.....
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(374L, new Day11().solveA(inputs));
    }
    @Test
    void testSolveB() {

        String input = """
                ...#......
                .......#..
                #.........
                ..........
                ......#...
                .#........
                .........#
                ..........
                .......#..
                #...#.....
                """;

        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(374L,  new Day11().solveWithOffset(inputs, 2));
        assertEquals(1030L, new Day11().solveWithOffset(inputs, 10));
        assertEquals(8410L, new Day11().solveWithOffset(inputs, 100));
    }
}
