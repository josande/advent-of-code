package year2017.day25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day25Test {

    @Test
    void testSolveA() {
        String input = """
                Begin in state A.
                Perform a diagnostic checksum after 6 steps.
                                
                In state A:
                  If the current value is 0:
                    - Write the value 1.
                    - Move one slot to the right.
                    - Continue with state B.
                  If the current value is 1:
                    - Write the value 0.
                    - Move one slot to the left.
                    - Continue with state B.
                                
                In state B:
                  If the current value is 0:
                    - Write the value 1.
                    - Move one slot to the left.
                    - Continue with state A.
                  If the current value is 1:
                    - Write the value 1.
                    - Move one slot to the right.
                    - Continue with state A.
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(3, new Day25().solveA(inputs));
    }
    @Test
    void testSolveB() {
        assertEquals("Merry X-mas!", new Day25().solveB(List.of()));
    }
}
