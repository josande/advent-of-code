package year2024.day12;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {
    @Test
    void testSolveA1() {
        String input = """
                AAAA
                BBCD
                BBCC
                EEEC
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(140L, new Day12().solveA(inputs));
    }
    @Test
    void testSolveA2() {
        String input = """
                OOOOO
                OXOXO
                OOOOO
                OXOXO
                OOOOO
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(772L, new Day12().solveA(inputs));
    }
    @Test
    void testSolveA3() {
        String input = """
                RRRRIICCFF
                RRRRIICCCF
                VVRRRCCFFF
                VVRCCCJFFF
                VVVVCJJCFE
                VVIVCCJJEE
                VVIIICJJEE
                MIIIIIJJEE
                MIIISIJEEE
                MMMISSJEEE
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(1930L, new Day12().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                AAAA
                BBCD
                BBCC
                EEEC
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(80L, new Day12().solveB(inputs));
    }
    @Test
    void testSolveB2() {
        String input = """
                EEEEE
                EXXXX
                EEEEE
                EXXXX
                EEEEE
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(236L, new Day12().solveB(inputs));
    }
}