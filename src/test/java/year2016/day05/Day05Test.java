package year2016.day05;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {

    @Test
    void testSolveA() {
        String input="abc";
        assertEquals("18f47a30", Day05.solveA(Arrays.asList(input)));
    }

    @Test
    void testAsMD5() throws NoSuchAlgorithmException {
        assertEquals("5eb63bbbe01eeed093cb22bb8f5acdc3", Day05.asMD5("hello world"));
    }

    @Test
    void testSolveB() {
        String input="abc";
        assertEquals("05ace8e3", Day05.solveB(Arrays.asList(input)));
    }
}