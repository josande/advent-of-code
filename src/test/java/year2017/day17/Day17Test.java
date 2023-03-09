package year2017.day17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class Day17Test {

    @Test
    void testSolveA() {
        Assertions.assertEquals(638, new Day17().solveA(List.of("3")));
    }
    @Test
    void testSolveB() {
        Assertions.assertEquals(1222153, new Day17().solveB(List.of("3")));
    }
}
