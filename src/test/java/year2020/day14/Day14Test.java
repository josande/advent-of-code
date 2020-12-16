package year2020.day14;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

    @Test
    void solveA() {
        String input =
                """
                mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
                mem[8] = 11
                mem[7] = 101
                mem[8] = 0
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(165, Day14.solveA(values));
    }


    @Test
    void solveB() {
        String input =
                """
                        mask = 000000000000000000000000000000X1001X
                        mem[42] = 100
                        mask = 00000000000000000000000000000000X0XX
                        mem[26] = 1
                        """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(208, Day14.solveB(values));
    }


}