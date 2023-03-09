package year2017.day06;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {

    @Test
    void testSolveA() {
        assertEquals(5, new Day06().solveA(List.of("0   2   7   0")));
    }
    @Test
    void testSolveB() {
        assertEquals(4, new Day06().solveB(List.of("0   2   7   0")));

    }
}
