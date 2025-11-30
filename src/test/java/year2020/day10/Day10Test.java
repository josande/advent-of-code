package year2020.day10;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    void solveA() {
        String input =
                """
                16
                10
                15
                5
                1
                11
                7
                19
                6
                12
                4
                """;
        List<Integer> values = Arrays.stream(input.split("\n"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        assertEquals(35, Day10.solveA(values));
    }

    @Test
    void solveB1() {
        String input =
                """
                16
                10
                15
                5
                1
                11
                7
                19
                6
                12
                4
                """;
        List<Integer> values = Arrays.stream(input.split("\n"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        assertEquals(8, Day10.solveB(values));
    }
    @Test
    void solveB2() {
        String input =
                """
                28
                33
                18
                42
                31
                14
                46
                20
                48
                47
                24
                23
                49
                45
                19
                38
                39
                11
                1
                32
                25
                35
                8
                17
                7
                9
                4
                2
                34
                10
                3
                """;
        List<Integer> values = Arrays.stream(input.split("\n"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        assertEquals(19208, Day10.solveB(values));
    }
}