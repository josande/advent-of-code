package year2016.day05;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {

    @Test
    void testSolveA() {
        String input="abc";
        assertEquals("18f47a30", new Day05().solveA(List.of(input)));
    }

    @Test
    void testAsMD5() throws NoSuchAlgorithmException {
        assertEquals("5eb63bbbe01eeed093cb22bb8f5acdc3", Day05.asMD5("hello world", MessageDigest.getInstance("MD5")));
    }

    @Test
    void testSolveB() {
        String input="abc";
        assertEquals("05ace8e3", new Day05().solveB(List.of(input)));
    }
}