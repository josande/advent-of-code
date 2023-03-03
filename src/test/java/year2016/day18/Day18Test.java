package year2016.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day18Test {

    @Test
    void generateMapTest() {
/*          Result
                .^^.^.^^^^
                ^^^...^..^
                ^.^^.^.^^.
                ..^^...^^^
                .^^^^.^^.^
                ^^..^.^^..
                ^^^^..^^^.
                ^..^^^^.^^
                .^^^..^.^^
                ^^.^^^..^^
  */
        assertEquals(".^^.^.^^^^", Day18.generateMap(".^^.^.^^^^", 10).get(0));
        assertEquals("^^^...^..^", Day18.generateMap(".^^.^.^^^^", 10).get(1));
        assertEquals("^.^^.^.^^.", Day18.generateMap(".^^.^.^^^^", 10).get(2));
        assertEquals("^^.^^^..^^", Day18.generateMap(".^^.^.^^^^", 10).get(9));
    }
    @Test
    void generateCountSafeSpacess() {
        assertEquals(38, Day18.calculateSafeSpaces(Day18.generateMap(".^^.^.^^^^", 10)));
    }
}
