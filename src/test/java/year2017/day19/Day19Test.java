package year2017.day19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day19Test {

    @Test
    void testSolveA() {
        String input = """
                     |         \s
                     |  +--+   \s
                     A  |  C   \s
                 F---|----E|--+\s
                     |  |  |  D\s
                     +B-+  +--+\s
                                
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals("ABCDEF", new Day19().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                     |         \s
                     |  +--+   \s
                     A  |  C   \s
                 F---|----E|--+\s
                     |  |  |  D\s
                     +B-+  +--+\s
                                
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(38, new Day19().solveB(inputs));
    }
}
