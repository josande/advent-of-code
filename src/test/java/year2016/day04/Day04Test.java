package year2016.day04;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {

    @Test
    void testSolveA() {
        String input =
                """
                aaaaa-bbb-z-y-x-123[abxyz]
                a-b-c-d-e-f-g-h-987[abcde]
                not-a-real-room-404[oarel]
                totally-real-room-200[decoy]
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(1514, Day04.solveA(values));
    }

    @Test
    void testSolveB() {

        String input =
                """
                qzmt-zixmtkozy-ivhz-343[abxyz]
                """;
        List<String> values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals("very encrypted name", Day04.getRoomName("qzmt-zixmtkozy-ivhz-343"));
    }
}