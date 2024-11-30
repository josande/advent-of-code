package year2017.day23;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day23Test {

    @Test
    void testSolveA() {
        String input = """
                set b 81
                set c b
                jnz a 2
                jnz 1 5
                mul b 100
                sub b -100000
                set c b
                sub c -17000
                set f 1
                set d 2
                set e 2
                set g d
                mul g e
                sub g b
                jnz g 2
                set f 0
                sub e -1
                set g e
                sub g b
                jnz g -8
                sub d -1
                set g d
                sub g b
                jnz g -13
                jnz f 2
                sub h -1
                set g b
                sub g c
                jnz g 2
                jnz 1 3
                sub b -17
                jnz 1 -23
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(6241, new Day23().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                set b 81
                set c b
                jnz a 2
                jnz 1 5
                mul b 100
                sub b -100000
                set c b
                sub c -17000
                set f 1
                set d 2
                set e 2
                set g d
                mul g e
                sub g b
                jnz g 2
                set f 0
                sub e -1
                set g e
                sub g b
                jnz g -8
                sub d -1
                set g d
                sub g b
                jnz g -13
                jnz f 2
                sub h -1
                set g b
                sub g c
                jnz g 2
                jnz 1 3
                sub b -17
                jnz 1 -23
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(909, new Day23().solveB(inputs));
    }
}
