package year2016.day07;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day07Test {

    @Test
    void solveA() {
        String input =
                """
                abba[mnop]qrst
                abcd[bddb]xyyx
                aaaa[qwer]tyui
                ioxxoj[asdfgh]zxcvbn
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertTrue(Day07.supportsTLS("abba[mnop]qrst"));
        assertFalse(Day07.supportsTLS("abcd[bddb]xyyx"));
        assertFalse(Day07.supportsTLS("aaaa[qwer]tyui"));
        assertTrue(Day07.supportsTLS("ioxxoj[asdfgh]zxcvbn"));

        assertEquals(2, Day07.solveA(values));

    }

    @Test
    void solveB() {
        String input =
                """
                aba[bab]xyz
                xyx[xyx]xyx
                aaa[kek]eke
                zazbz[bzb]cdb
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        assertEquals(3, Day07.solveB(values));


    }
}