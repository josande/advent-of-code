package year2016.day21;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day21Test {

    @Test
    void testSwapPosition() {
        assertEquals("ebcda", Day21.scramble("abcde", "swap position 4 with position 0"));
        assertEquals("aecdbf", Day21.scramble("abcdef", "swap position 4 with position 1"));
    }
    @Test
    void testUnscrambleSwapPosition() {
        assertEquals("ebcda", Day21.scramble("abcde", "swap position 4 with position 0"));
        assertEquals("abcde", Day21.scramble("ebcda", "swap position 4 with position 0"));
        assertEquals("aecdbf", Day21.scramble("abcdef", "swap position 4 with position 1"));
        assertEquals("abcdef", Day21.scramble("aecdbf", "swap position 4 with position 1"));
    }
    @Test
    void testSwapLetter() {
        assertEquals("adcbe", Day21.scramble("abcde", "swap letter d with letter b"));
        assertEquals("adcadcadc", Day21.scramble("abcabcabc", "swap letter d with letter b"));
    }
    @Test
    void testUnscrambleSwapLetter() {
        assertEquals("adcbe", Day21.scramble("abcde", "swap letter d with letter b"));
        assertEquals("abcde", Day21.unscramble("adcbe", "swap letter d with letter b"));
        assertEquals("adcadcadc", Day21.scramble("abcabcabc", "swap letter d with letter b"));
        assertEquals("abcabcabc", Day21.unscramble("adcadcadc", "swap letter d with letter b"));
    }
    @Test
    void testReversePositions() {
        assertEquals("abcde", Day21.scramble("edcba", "reverse positions 0 through 4"));
        assertEquals("abedcfg", Day21.scramble("abcdefg", "reverse positions 2 through 4"));
    }
    @Test
    void testUnscrambleReversePositions() {
        assertEquals("abcde", Day21.scramble("edcba", "reverse positions 0 through 4"));
        assertEquals("edcba", Day21.unscramble("abcde", "reverse positions 0 through 4"));
        assertEquals("abedcfg", Day21.scramble("abcdefg", "reverse positions 2 through 4"));
        assertEquals("abcdefg", Day21.unscramble("abedcfg", "reverse positions 2 through 4"));
    }

    @Test
    void testRotateLeft() {
        assertEquals("bcdea", Day21.scramble("abcde", "rotate left 1 step"));
        assertEquals("abcde", Day21.scramble("abcde", "rotate left 10 step"));
        assertEquals("cdeab", Day21.scramble("abcde", "rotate left 2 step"));
    }
    @Test
    void testUnscrambleRotateLeft() {
        assertEquals("bcdea", Day21.scramble("abcde", "rotate left 1 step"));
        assertEquals("abcde", Day21.unscramble("bcdea", "rotate left 1 step"));
        assertEquals("abcde", Day21.scramble("abcde", "rotate left 10 step"));
        assertEquals("abcde", Day21.unscramble("abcde", "rotate left 10 step"));
        assertEquals("cdeab", Day21.scramble("abcde", "rotate left 2 step"));
        assertEquals("abcde", Day21.unscramble("cdeab", "rotate left 2 step"));
    }
    @Test
    void testRotateRight() {
        assertEquals("eabcd", Day21.scramble("abcde", "rotate right 1 step"));
        assertEquals("abcde", Day21.scramble("abcde", "rotate right 10 step"));
        assertEquals("deabc", Day21.scramble("abcde", "rotate right 2 step"));
    }
    @Test
    void testUnscrambleRotateRight() {
        assertEquals("eabcd", Day21.scramble("abcde", "rotate right 1 step"));
        assertEquals("abcde", Day21.unscramble("eabcd", "rotate right 1 step"));
        assertEquals("abcde", Day21.scramble("abcde", "rotate right 10 step"));
        assertEquals("abcde", Day21.unscramble("abcde", "rotate right 10 step"));
        assertEquals("deabc", Day21.scramble("abcde", "rotate right 2 step"));
        assertEquals("abcde", Day21.unscramble("deabc", "rotate right 2 step"));
    }
    @Test
    void testRotateBasedOnPosition() {
        assertEquals("ecabd", Day21.scramble("abdec", "rotate based on position of letter b"));
        assertEquals("decab", Day21.scramble("ecabd", "rotate based on position of letter d"));
    }
    @Test
    void testUnscrambleRotateBasedOnPosition() {
        assertEquals("ecabd", Day21.scramble("abdec", "rotate based on position of letter b"));
        assertEquals("abdec", Day21.unscramble("ecabd", "rotate based on position of letter b"));

        assertEquals("decab", Day21.scramble("abdec", "rotate based on position of letter d"));
        assertEquals("abdec", Day21.unscramble("decab", "rotate based on position of letter d"));

        assertEquals("decab", Day21.scramble("ecabd", "rotate based on position of letter d"));
        assertEquals("ecabd", Day21.unscramble("decab", "rotate based on position of letter d"));
    }
    @Test
    void testMovePosition() {
        assertEquals("bdeac", Day21.scramble("bcdea", "move position 1 to position 4"));
        assertEquals("bcdea", Day21.scramble("bdeac", "move position 4 to position 1"));
    }
    @Test
    void testUnscrambleMovePosition() {
        assertEquals("bdeac", Day21.scramble("bcdea", "move position 1 to position 4"));
        assertEquals("bcdea", Day21.unscramble("bdeac", "move position 1 to position 4"));
        assertEquals("bcdea", Day21.scramble("bdeac", "move position 4 to position 1"));
        assertEquals("bdeac", Day21.unscramble("bcdea", "move position 4 to position 1"));
    }
    @Test
    void testSolveA() {
        String input = """
                swap position 4 with position 0
                swap letter d with letter b
                reverse positions 0 through 4
                rotate left 1 step
                move position 1 to position 4
                move position 3 to position 0
                rotate based on position of letter b
                rotate based on position of letter d
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals("decab", Day21.solveA("abcde", inputs));
    }
    @Test
    void testSolveB() {
        String input = """
                swap position 4 with position 0
                swap letter d with letter b
                reverse positions 0 through 4
                rotate left 1 step
                move position 1 to position 4
                move position 3 to position 0
                rotate based on position of letter b
                rotate based on position of letter d
                """;
        var inputs = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals("abcde", Day21.solveB("decab", inputs));
    }

}
