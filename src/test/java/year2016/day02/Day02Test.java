package year2016.day02;

import org.junit.jupiter.api.Test;
import year2020.day01.Day01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day02Test {

    @Test
    void testSolveA() {
        String input =
                """
                ULL
                RRDDD
                LURDL
                UUUUD
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals("1985", Day02.solveA(values));
    }

    @Test
    void testSolveB() {
        String input =
                """
                ULL
                RRDDD
                LURDL
                UUUUD
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals("5DB3", Day02.solveB(values));


    }
}