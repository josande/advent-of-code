package year2023.day13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {

    @Test
    void testSolveA() {
        String input = """
                #.##..##.
                ..#.##.#.
                ##......#
                ##......#
                ..#.##.#.
                ..##..##.
                #.#.##.#.
                
                #...##..#
                #....#..#
                ..##..###
                #####.##.
                #####.##.
                ..##..###
                #....#..#
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(405, new Day13().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                #.##..##.
                ..#.##.#.
                ##......#
                ##......#
                ..#.##.#.
                ..##..##.
                #.#.##.#.
                
                #...##..#
                #....#..#
                ..##..###
                #####.##.
                #####.##.
                ..##..###
                #....#..#
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(400, new Day13().solveB(inputs));
    }
}
