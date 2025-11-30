package year2019.day15;

import org.junit.jupiter.api.Test;
import utils.Point;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {


    @Test
    void testOxygenSpread() {

        String input =
                """
                 ##   
                #..## 
                #.#..#
                #.o.# 
                 ###  
                """;
        HashMap<Point, Character> map = new HashMap<>();
        int y=0;
        int x=0;
        for( String row :input.split("\n")) {
            for (char c: row.toCharArray()) {
                map.put(new Point(x,y), c);
                x++;
            }
            x=0;
            y++;
        }
        assertEquals(4, Day15.timeToFillWithOxygen(map));
    }
}