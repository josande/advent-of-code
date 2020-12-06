package year2020.day06;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day06Test {

    @Test
    void testSolveA() {
        String input =
                """
                abc
                                
                a
                b
                c
                                
                ab
                ac
                                
                a
                a
                a
                a
                                
                b
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        assertEquals(11, Day06.solveA(values));
    }

    @Test
    void testSolveB() {
        String input =
                """
                abc
                                
                a
                b
                c
                                
                ab
                ac
                                
                a
                a
                a
                a
                                
                b
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        assertEquals(6, Day06.solveB(values));
    }
}