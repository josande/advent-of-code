package year2020.day04;

import org.junit.jupiter.api.Test;
import year2020.day03.Day03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {

    @Test
    void testSolveA(){
        String input =
                """

                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());
        assertEquals(-1, Day04.solveA(values));
    }


    @Test
    void testSolveB(){
        String input = """
                
                """;
        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());
        assertEquals(-1, Day04.solveB(values));
    }
}