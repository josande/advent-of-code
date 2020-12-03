package year2020.day03;

import org.junit.jupiter.api.Test;
import utils.Point;
import year2020.day01.Day01;
import year2020.day02.Day02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {


    @Test
    void testSolveA(){
        String input =
                """
                ..##.......
                #...#...#..
                .#....#..#.
                ..#.#...#.#
                .#...##..#.
                ..#.##.....
                .#.#.#....#
                .#........#
                #.##...#...
                #...##....#
                .#..#...#.#
                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());

        assertEquals(7, Day03.solveA(values));
    }


    @Test
    void testSolveB(){
        String input = """
                ..##.......
                #...#...#..
                .#....#..#.
                ..#.#...#.#
                .#...##..#.
                ..#.##.....
                .#.#.#....#
                .#........#
                #.##...#...
                #...##....#
                .#..#...#.#
                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());
        assertEquals(336, Day03.solveB(values));
    }
}