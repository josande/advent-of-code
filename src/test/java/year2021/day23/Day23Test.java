package year2021.day23;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23Test {
    @Test
    void solveA() {

        String input = """
                #############
                #...........#
                ###B#C#B#D###
                  #A#D#C#A#
                  #########
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(12521, Day23.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                #############
                #...........#
                ###B#C#B#D###
                  #A#D#C#A#
                  #########
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(44169, Day23.solveB(values));
    }
}