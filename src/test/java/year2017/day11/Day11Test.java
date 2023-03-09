package year2017.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class Day11Test {

    @Test
    void testSolveA() {
        Assertions.assertEquals(3, new Day11().solveA(List.of("ne,ne,ne")));
        Assertions.assertEquals(0, new Day11().solveA(List.of("ne,ne,sw,sw")));
        Assertions.assertEquals(2, new Day11().solveA(List.of("ne,ne,s,s")));
        Assertions.assertEquals(3, new Day11().solveA(List.of("se,sw,se,sw,sw")));
    }
    @Test
    void testSolveB() {
        Assertions.assertEquals(3, new Day11().solveB(List.of("ne,ne,ne")));
        Assertions.assertEquals(2, new Day11().solveB(List.of("ne,ne,sw,sw")));
        Assertions.assertEquals(2, new Day11().solveB(List.of("ne,ne,s,s")));
        Assertions.assertEquals(3, new Day11().solveB(List.of("se,sw,se,sw,sw")));
    }
}
