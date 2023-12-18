package year2023.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    @Test
    void testSolveA() {
        String input = """
                ???.### 1,1,3
                .??..??...?##. 1,1,3
                ?#?#?#?#?#?#?#? 1,3,1,6
                ????.#...#... 4,1,1
                ????.######..#####. 1,6,5
                ?###???????? 3,2,1
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(21L, new Day12().solveA(inputs));
    }
    @ParameterizedTest
    @CsvSource(value = {
            "???.### 1,1,3 | 1",
            ".??..??...?##. 1,1,3 | 16384",
            "?#?#?#?#?#?#?#? 1,3,1,6 | 1",
            "????.#...#... 4,1,1 | 16",
            "????.######..#####. 1,6,5 | 2500",
            "?###???????? 3,2,1 | 506250",
            ".?##??.#?..? 2,2 | 1"
    }, delimiterString = "|")
    void testSolveB(String input, Long expected) {
        assertEquals(expected, new Day12().solveB(List.of(input)));
    }
}
