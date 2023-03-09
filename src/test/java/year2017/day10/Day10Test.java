package year2017.day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day10Test {

    @Test
    void testSolveA() {
        Assertions.assertEquals(12, new Day10().solveA(List.of("3,4,1,5")));
    }
    @Test
    void testSolveB() {
        Assertions.assertEquals("a2582a3a0e66e6e86e3812dcb672a272", new Day10().solveB(List.of("")));
        Assertions.assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", new Day10().solveB(List.of("1,2,3")));
        Assertions.assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", new Day10().solveB(List.of("1,2,4")));
        Assertions.assertEquals("33efeb34ea91902bb2f59c9920caa6cd", new Day10().solveB(List.of("AoC 2017")));
    }
}
