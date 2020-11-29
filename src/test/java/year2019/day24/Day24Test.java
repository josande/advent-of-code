package year2019.day24;

import org.junit.jupiter.api.Test;
import utils.Point;

import java.util.*;

import static year2019.day24.Day24.*;
import static org.junit.jupiter.api.Assertions.*;

class Day24Test {


    @Test
    void testA1() {
        String inputStr =
               """
               ....#
               #..#.
               #..##
               ..#..
               #....
               """
                ;
        List<String> input = Arrays.asList(inputStr.split("\n"));
        HashMap<Point, Character> map = makeMap(input);
        Set<HashMap<Point, Character>> knownState=new HashSet<>();
        HashMap<Point, Character> after = evolve(map);
        while(!knownState.contains(after)) {
            knownState.add(after);
            after=evolve(after);
        }

        long bio = getBioDiversity(after);
        assertEquals(2129920, bio);
    }

    @Test
    void testB1() {
        String inputStr =
                """
                ....#
                #..#.
                #.?##
                ..#..
                #....        
                """;
        List<String> input = Arrays.asList(inputStr.split("\n"));
        HashMap<Point, Character> map = makeMap(input);
        Point.print(map);HashMap<Integer, HashMap<Point, Character>> levelMap=new HashMap<>();
        levelMap.put(0, map);


        levelMap=evolveWithLayers(levelMap, 10);
        assertEquals(99, getNumberOfBugs(levelMap));

    }
}