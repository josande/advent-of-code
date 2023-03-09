package year2017.day14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {

    @Test
    void testSolveA() {
        Assertions.assertEquals(8108, new Day14().solveA(List.of("flqrgnkx")));
    }
    @Test
    void testSolveB() {
        assertEquals(1242, new Day14().solveB(List.of("flqrgnkx")));
    }
}
