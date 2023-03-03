package year2016.day19;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day19Test {

    @Test
    void testSSolveA() {
        ArrayList<Integer> values = new ArrayList<>();
        values.add(5);
        assertEquals(3, Day19.solveA(values));
    }
    @Test
    void testSSolveB() {
        ArrayList<Integer> values = new ArrayList<>();
        values.add(5);
        assertEquals(2, Day19.solveB(values));
    }
}
