package year2016.day15;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Day15Test {

    @Test
    void tesSolveA() {
        String input = """
                Disc #1 has 5 positions; at time=0, it is at position 4.
                Disc #2 has 2 positions; at time=0, it is at position 1.
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(5, new Day15().solveA(values));


    }
}
