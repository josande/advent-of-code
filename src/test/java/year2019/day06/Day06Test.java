package year2019.day06;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static year2019.day06.Day06.*;
import static org.junit.jupiter.api.Assertions.*;

class Day06Test {

    @Test
    void testA1() {
        String input =
                """
                COM)B
                B)C
                C)D
                D)E
                E)F
                B)G
                G)H
                D)I
                E)J
                J)K
                K)L
                """;

        List<String> orbits = Arrays.asList(input.split("\n"));
        HashMap<String, String> orbitMap = makeMap(orbits);

        assertEquals(42, calculateCheckSum(orbitMap));
    }


    @Test
    void testB() {

        String input=
                """
                COM)B
                B)C
                C)D
                D)E
                E)F
                B)G
                G)H
                D)I
                E)J
                J)K
                K)L
                K)YOU
                I)SAN
                """;
        List<String> orbits = Arrays.asList(input.split("\n"));
        HashMap<String, String> orbitMap = makeMap(orbits);

        assertEquals(4, orbitCountBetween("SAN", "YOU", orbitMap));
    }
}