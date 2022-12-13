package year2022.day12;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void testSolveA(){
        String input = """
                Sabqponm
                abcryxxl
                accszExk
                acctuvwj
                abdefghi
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(31, Day12.solveA(values));
    }
    @Test
    void testSolveB() {
        String input = """
                Sabqponm
                abcryxxl
                accszExk
                acctuvwj
                abdefghi
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(29, Day12.solveB(values));
    }
}