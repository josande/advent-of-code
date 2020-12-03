package year2020.day01;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
class Day01Test {

    @Test
    void testSolveA(){
        String input = """
                1721
                979
                366
                299
                675
                1456
                """;
        List<Integer> values = Arrays.stream(input.split("\n"))
                                     .filter(s->!s.isEmpty())
                                     .map(Integer::valueOf)
                                     .collect(Collectors.toList());
        assertEquals(514579, Day01.solveA(values));
    }
    @Test
    void testSolveB(){
        String input = """
                1721
                979
                366
                299
                675
                1456
                """;

        List<Integer> values = Arrays.stream(input.split("\n"))
                                     .filter(s->!s.isEmpty())
                                     .map(Integer::valueOf)
                                     .collect(Collectors.toList());

        assertEquals(241861950, Day01.solveB(values));
    }
}
