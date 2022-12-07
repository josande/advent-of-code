package year2022.day05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

    @Test
    void testSolveA(){
        String input = """
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                                
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """;
        var values = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals("CMZ", Day05.solveA(3, values));
    }
    @Test
    void testSolveB() {
        String input = """
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                                
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """;
        var values = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals("MCD", Day05.solveB(3, values));
    }
}