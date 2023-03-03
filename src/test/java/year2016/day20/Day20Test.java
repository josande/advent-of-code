package year2016.day20;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day20Test {

    @Test
    void testSSolveA() {
        String input = """
                5-8
                0-2
                4-7
                """;

        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(3L, Day20.getUnblockedAddresses(values, 9).get(0));
    }

}
