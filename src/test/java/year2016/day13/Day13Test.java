package year2016.day13;

import org.junit.jupiter.api.Test;
import utils.Point;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

    @Test
    void tesIsWall() {
        assertFalse(Day13.isWall(new Point(0,0), 10));
        assertTrue(Day13.isWall(new Point(1,0), 10));
        assertFalse(Day13.isWall(new Point(0,1), 10));
        assertTrue(Day13.isWall(new Point(9,6), 10));


    }
    @Test
    void testGetSteps() {
        int offset = 10;
        Point target = new Point(7,4);
        assertEquals(11, Day13.getSteps(offset, target));
    }
}