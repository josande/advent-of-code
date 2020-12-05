package year2020.day05;

import org.junit.jupiter.api.Test;
import year2020.day01.Day01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {

    @Test
    void solveA() {
        String input =
                """
                FBFBBFFRLR
                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());

        assertEquals(357, Day05.solveA(values));
    }

    @Test
    void solveB() {
    }
}