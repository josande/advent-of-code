package year2024.day17;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17Test {

    @Test
    void testSolveA() {
        String input = """
                Register A: 729
                Register B: 0
                Register C: 0
                
                Program: 0,1,5,4,3,0
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertEquals("4,6,3,5,6,3,5,2,1,0", new Day17().solveA(inputs));
    }

    @Test
    void testSolveB() {
        String input = """
                Register A: 2024
                Register B: 0
                Register C: 0
                
                Program: 0,3,5,4,3,0
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(117440L, new Day17().solveB(inputs));
    }
}