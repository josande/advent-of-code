package year2017.day20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day20Test {

    @Test
    void testSolveA() {
        String input = """
                p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>
                p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(0, new Day20().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>
                p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>
                p=<-9,0,0>, v=<1,0,0>, a=<2,0,0>
                p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>
                p=<6,6,6>, v=<0,0,0>, a=<-1,-1,-1>
                p=<-6,-6,-6>, v=<0,0,0>, a=<1,1,1>
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(1, new Day20().solveB(inputs));
    }
}
