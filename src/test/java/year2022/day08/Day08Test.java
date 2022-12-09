package year2022.day08;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {

    @Test
    void testSolveA(){
        String input = """
                30373
                25512
                65332
                33549
                35390
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(21, Day08.solveA(values));
    }
    @Test
    void testSolveB() {
        String input = """
                30373
                25512
                65332
                33549
                35390
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(8L, Day08.solveB(values));
    }
}