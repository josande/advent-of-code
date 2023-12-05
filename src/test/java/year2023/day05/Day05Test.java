package year2023.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    @Test
    void testSolveA() {
        String input = """
                seeds: 79 14 55 13
                                
                seed-to-soil map:
                50 98 2
                52 50 48
                                
                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15
                                
                fertilizer-to-water map:
                49 53 8
                0 11 42
                42 0 7
                57 7 4
                                
                water-to-light map:
                88 18 7
                18 25 70
                                
                light-to-temperature map:
                45 77 23
                81 45 19
                68 64 13
                                
                temperature-to-humidity map:
                0 69 1
                1 0 69
                                
                humidity-to-location map:
                60 56 37
                56 93 4
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assertions.assertEquals(35L, new Day05().solveA(inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                seeds: 79 14 55 13
                                
                seed-to-soil map:
                50 98 2
                52 50 48
                                
                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15
                                
                fertilizer-to-water map:
                49 53 8
                0 11 42
                42 0 7
                57 7 4
                                
                water-to-light map:
                88 18 7
                18 25 70
                                
                light-to-temperature map:
                45 77 23
                81 45 19
                68 64 13
                                
                temperature-to-humidity map:
                0 69 1
                1 0 69
                                
                humidity-to-location map:
                60 56 37
                56 93 4
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(46L, new Day05().solveB(inputs));
    }
}
