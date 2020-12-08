package year2020.day08;

import org.junit.jupiter.api.Test;
import year2020.day05.Day05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day08Test {

    @Test
    void testSolveA() {
        String input =
                """
                nop +0
                acc +1
                jmp +4
                acc +3
                jmp -3
                acc -99
                acc +1
                jmp -4
                acc +6
                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());


        assertEquals(5, Day08.solveA(values));
    }

    @Test
    void testSolveB() {
        String input =
                """
                nop +0
                acc +1
                jmp +4
                acc +3
                jmp -3
                acc -99
                acc +1
                jmp -4
                acc +6
                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());


        assertEquals(8, Day08.solveB(values));
    }
}