package year2020.day11;

import org.junit.jupiter.api.Test;
import year2020.day08.Day08;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    @Test
    void testSolveA() {
        String input =
                """
                L.LL.LL.LL
                LLLLLLL.LL
                L.L.L..L..
                LLLL.LL.LL
                L.LL.LL.LL
                L.LLLLL.LL
                ..L.L.....
                LLLLLLLLLL
                L.LLLLLL.L
                L.LLLLL.LL
                 """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());

        assertEquals(37, Day11.solveA(values));

    }

    @Test
    void testSolveB() {

        String input =
                """
                L.LL.LL.LL
                LLLLLLL.LL
                L.L.L..L..
                LLLL.LL.LL
                L.LL.LL.LL
                L.LLLLL.LL
                ..L.L.....
                LLLLLLLLLL
                L.LLLLLL.L
                L.LLLLL.LL
                 """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());

        assertEquals(26, Day11.solveB(values));


    }
}