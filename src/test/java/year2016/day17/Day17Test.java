package year2016.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17Test {

    @Test
    void testSolveA() {
        assertEquals("DDRRRD", Day17.getShortestPath("ihgpwlah"));
        assertEquals("DDUDRLRRUDRD", Day17.getShortestPath("kglvqrro"));
        assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", Day17.getShortestPath("ulqzkmiv"));
    }
    @Test
    void testSolveB() {
        assertEquals(370, Day17.getLongestPath("ihgpwlah"));
        assertEquals(492, Day17.getLongestPath("kglvqrro"));
        assertEquals(830, Day17.getLongestPath("ulqzkmiv"));
    }
}
