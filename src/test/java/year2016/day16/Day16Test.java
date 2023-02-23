package year2016.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test {


    @Test
    void tesSolveA() {
        assertEquals("100", Day16.curve("1"));
        assertEquals("001", Day16.curve("0"));
        assertEquals("11111000000", Day16.curve("11111"));
        assertEquals("1111000010100101011110000", Day16.curve("111100001010"));
    }
    @Test
    void testFillDisk() {
        assertEquals("10000011110010000111", Day16.fillDisk("10000", 20));
    }
    @Test
    void testMakeCChecksum() {
        assertEquals("100", Day16.makeChecksum("110010110100"));
    }
}
