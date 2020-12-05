package year2016.day03;

import org.junit.jupiter.api.Test;
import year2016.day02.Day02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {

    @Test
    void testSolveA() {
        String input =
                """

                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals("1985", Day03.solveA(values));
    }

    @Test
    void testSolveB() {
        String input =
                """
                101 301 501
                102 302 502
                103 303 503
                201 401 601
                202 402 602
                203 403 603
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(6, Day03.solveB(values));
    }



}