package year2020.day09;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {

    @Test
    void solveA() {
        String input =
                """
                35
                20
                15
                25
                47
                40
                62
                55
                65
                95
                102
                117
                150
                182
                127
                219
                299
                277
                309
                576
                """;
        List<Long> values = Arrays.stream(input.split("\n")).map(Long::valueOf).collect(Collectors.toList());


        assertEquals(127, Day09.solveA(5, values));
    }

    @Test
    void solveB() {
        String input =
                """
                35
                20
                15
                25
                47
                40
                62
                55
                65
                95
                102
                117
                150
                182
                127
                219
                299
                277
                309
                576
                """;
        List<Long> values = Arrays.stream(input.split("\n")).map(Long::valueOf).collect(Collectors.toList());


        assertEquals(62, Day09.solveB(5, values));


    }
}