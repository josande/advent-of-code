package year2017.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    @Test
    void testSolveA() {
        String input = """
                aa bb cc dd ee
                aa bb cc dd aa
                aa bb cc dd aaa
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(2, new Day04().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                abcde fghij
                abcde xyz ecdab
                a ab abc abd abf abj
                iiii oiii ooii oooi oooo
                oiii ioii iioi iiio
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(3, new Day04().solveB(inputs));
    }
}
