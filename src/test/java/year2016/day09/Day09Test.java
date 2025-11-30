package year2016.day09;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {

    @Test
    void solveA() {

        assertEquals(6L, new Day09().solveA(Collections.singletonList("ADVENT")));
        assertEquals(7L, new Day09().solveA(Collections.singletonList("A(1x5)BC")));
        assertEquals(9L, new Day09().solveA(Collections.singletonList("(3x3)XYZ")));
        assertEquals(11L,new Day09().solveA(Collections.singletonList("A(2x2)BCD(2x2)EFG")));
        assertEquals(6L, new Day09().solveA(Collections.singletonList("(6x1)(1x3)A")));
        assertEquals(18L,new Day09().solveA(Collections.singletonList("X(8x2)(3x3)ABCY")));
    }

    @Test
    void solveB() {
        assertEquals(20L,    new Day09().solveB(Collections.singletonList("X(8x2)(3x3)ABCY")));
        assertEquals(241920L,new Day09().solveB(Collections.singletonList("(27x12)(20x12)(13x14)(7x10)(1x12)A")));
        assertEquals(445L,   new Day09().solveB(Collections.singletonList("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN")));

    }
}