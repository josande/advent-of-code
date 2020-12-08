package year2020.day07;

import org.junit.jupiter.api.Test;
import year2016.day02.Day02;
import year2020.day06.Day06;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day07Test {

    @Test
    void testSolveA() {
        String input =
                """
                light red bags contain 1 bright white bag, 2 muted yellow bags.
                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                bright white bags contain 1 shiny gold bag.
                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                faded blue bags contain no other bags.
                dotted black bags contain no other bags.
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        assertEquals(4, Day07.solveA(values));

    }


    @Test
    void testSolveB() {
        String input =
                """
                light red bags contain 1 bright white bag, 2 muted yellow bags.
                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                bright white bags contain 1 shiny gold bag.
                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                faded blue bags contain no other bags.
                dotted black bags contain no other bags.
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        assertEquals(32, Day07.solveB(values));
    }
    @Test
    void testSolveB2() {
        String input =
                """
                shiny gold bags contain 2 dark red bags.
                dark red bags contain 2 dark orange bags.
                dark orange bags contain 2 dark yellow bags.
                dark yellow bags contain 2 dark green bags.
                dark green bags contain 2 dark blue bags.
                dark blue bags contain 2 dark violet bags.
                dark violet bags contain no other bags.
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        assertEquals(126, Day07.solveB(values));
    }
    @Test
    void testSolveB3() {
        String input =
                """
                shiny gold bags contain 2 dark red bags.
                dark red bags contain 2 dark orange bags.
                dark orange bags contain no other bags.

                """;

        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        assertEquals(6, Day07.solveB(values));
    }
    @Test
    void testSolveB4() {
        String input =
                """
                shiny gold bags contain no other bags.
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        assertEquals(0, Day07.solveB(values));
    }
    @Test
    void testSolveB5() {
        String input =
                """
                shiny gold bags contain 2 dark red bags.
                dark red bags contain no other bags.
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        assertEquals(2, Day07.solveB(values));
    }
}