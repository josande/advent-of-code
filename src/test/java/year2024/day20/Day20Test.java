package year2024.day20;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day20Test {
    @Test
    void testSolveA() {
        String input = """
                ###############
                #...#...#.....#
                #.#.#.#.#.###.#
                #S#...#.#.#...#
                #######.#.#.###
                #######.#.#...#
                #######.#.###.#
                ###..E#...#...#
                ###.#######.###
                #...###...#...#
                #.#####.#.###.#
                #.#...#.#.#...#
                #.#.#.#.#.#.###
                #...#...#...###
                ###############
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        Day20 day20 = new Day20();

        day20.setTimeLimit(64);
        assertEquals(1, day20.solveA(inputs));

        day20.setTimeLimit(40);
        assertEquals(2, day20.solveA(inputs));

        day20.setTimeLimit(2);
        assertEquals(44, day20.solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                ###############
                #...#...#.....#
                #.#.#.#.#.###.#
                #S#...#.#.#...#
                #######.#.#.###
                #######.#.#...#
                #######.#.###.#
                ###..E#...#...#
                ###.#######.###
                #...###...#...#
                #.#####.#.###.#
                #.#...#.#.#...#
                #.#.#.#.#.#.###
                #...#...#...###
                ###############
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Day20 day20 = new Day20();

        day20.setTimeLimit(76);
        assertEquals(3, day20.solveB(inputs));

        day20.setTimeLimit(74);
        assertEquals(7, day20.solveB(inputs));

        day20.setTimeLimit(50);
        assertEquals(285, day20.solveB(inputs));
    }
}