package year2020.day05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {


    @Test
    void testSolveA() {
        String input =
                """
                FBFBBFFRLR
                BFFFBBFRRR
                FFFBBBFRRR
                BBFFBBFRLL
                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());

        assertEquals(0, Day05.findSeatId("FFFFFFFLLL"));
        assertEquals(1023, Day05.findSeatId("BBBBBBBRRR"));
        assertEquals(1, Day05.findSeatId("FFFFFFFLLR"));

        assertEquals(357, Day05.findSeatId("FBFBBFFRLR"));
        assertEquals(567, Day05.findSeatId("BFFFBBFRRR"));
        assertEquals(119, Day05.findSeatId("FFFBBBFRRR"));
        assertEquals(820, Day05.findSeatId("BBFFBBFRLL"));
        assertEquals(820, Day05.solveA(values));
    }

    @Test
    void testSolveB() {
        String input =
                """
                FBFBBFFRLR
                FBFBBFFRRR
                FFFBBBFRRR
                BBFFBBFRLL
                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());

        assertEquals(357, Day05.findSeatId("FBFBBFFRLR"));
        assertEquals(359, Day05.findSeatId("FBFBBFFRRR"));
        assertEquals(358, Day05.solveB(values));
    }
}