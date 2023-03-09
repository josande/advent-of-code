package year2017.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test {

    @Test
    void testSolveA() {
        assertEquals(1, new Day09().solveA(List.of("{}")));
        assertEquals(3, new Day09().solveA(List.of("{{}}")));
        assertEquals(6, new Day09().solveA(List.of("{{{}}}")));
        assertEquals(5, new Day09().solveA(List.of("{{},{}}")));
        assertEquals(16, new Day09().solveA(List.of("{{{},{},{{}}}}")));
        assertEquals(1, new Day09().solveA(List.of("{<a>,<a>,<a>,<a>}")));
        assertEquals(9, new Day09().solveA(List.of("{{<ab>},{<ab>},{<ab>},{<ab>}}")));
        assertEquals(9, new Day09().solveA(List.of("{{<!!>},{<!!>},{<!!>},{<!!>}}")));
        assertEquals(3, new Day09().solveA(List.of("{{<a!>},{<a!>},{<a!>},{<ab>}}")));
    }
    @Test
    void testSolveB() {
        assertEquals(0, new Day09().solveB(List.of("<>")));
        assertEquals(17, new Day09().solveB(List.of("<random characters>")));
        assertEquals(3, new Day09().solveB(List.of("<<<<>")));
        assertEquals(2, new Day09().solveB(List.of("<{!>}>")));
        assertEquals(0, new Day09().solveB(List.of("<!!>")));
        assertEquals(0, new Day09().solveB(List.of("<!!!>>")));
        assertEquals(10, new Day09().solveB(List.of("<{o\"i!a,<{i<a>")));
    }
}
