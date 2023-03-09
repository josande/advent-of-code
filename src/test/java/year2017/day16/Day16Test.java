package year2017.day16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class Day16Test {

    @Test
    void testSolveA() {
        Assertions.assertEquals("baedc", new Day16().solveA(List.of("s1,x3/4,pe/b")));
    }
}
