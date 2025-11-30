package year2016.day19;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day19Test {

    @Test
    void testSolveA() {
        ArrayList<String> values = new ArrayList<>();
        values.add("" + 5);
        assertEquals(3, new Day19().solveA(values));
    }
    @Test
    void testSolveB() {
        ArrayList<String> values = new ArrayList<>();
        values.add("" + 5);
        assertEquals(2, new Day19().solveB(values));
    }
}
