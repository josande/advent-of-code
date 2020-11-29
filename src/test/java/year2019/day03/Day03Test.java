package year2019.day03;

import org.junit.jupiter.api.Test;
import utils.Point;

import java.util.HashMap;

import static year2019.day03.Day03.getClosestDistance;
import static year2019.day03.Day03.getShortestWalk;
import static year2019.day03.Day03.stringToPoints;
import static org.junit.jupiter.api.Assertions.*;

class Day03Test {
    @Test
        void testA_1() {
        String input = "R8,U5,L5,D3\n" +
                "U7,R6,D4,L4";
        HashMap<Point, Integer> wireA = stringToPoints(input.split("\n")[0]);
        HashMap<Point, Integer> wireB = stringToPoints(input.split("\n")[1]);
        assertEquals(6, getClosestDistance(wireA, wireB));
    }
    @Test
        void testA_2() {
        String input = "R75,D30,R83,U83,L12,D49,R71,U7,L72\n" +
                "U62,R66,U55,R34,D71,R55,D58,R83";
        HashMap<Point, Integer> wireA = stringToPoints(input.split("\n")[0]);
        HashMap<Point, Integer> wireB = stringToPoints(input.split("\n")[1]);
        assertEquals(159, getClosestDistance(wireA, wireB));
    }
    @Test
        void testA_3() {
        String input = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\n" +
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
        HashMap<Point, Integer> wireA = stringToPoints(input.split("\n")[0]);
        HashMap<Point, Integer> wireB = stringToPoints(input.split("\n")[1]);
        assertEquals(135, getClosestDistance(wireA, wireB));
    }


    @Test
    void testB_1() {
        String input = "R8,U5,L5,D3\n" +
                "U7,R6,D4,L4";
        HashMap<Point, Integer> wireA = stringToPoints(input.split("\n")[0]);
        HashMap<Point, Integer> wireB = stringToPoints(input.split("\n")[1]);
        assertEquals(30, getShortestWalk(wireA, wireB));
    }
    @Test
    void testB_2() {
        String input = "R75,D30,R83,U83,L12,D49,R71,U7,L72\n" +
                "U62,R66,U55,R34,D71,R55,D58,R83";
        HashMap<Point, Integer> wireA = stringToPoints(input.split("\n")[0]);
        HashMap<Point, Integer> wireB = stringToPoints(input.split("\n")[1]);
        assertEquals(610, getShortestWalk(wireA, wireB));
    }
    @Test
    void testB_3() {
        String input = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\n" +
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
        HashMap<Point, Integer> wireA = stringToPoints(input.split("\n")[0]);
        HashMap<Point, Integer> wireB = stringToPoints(input.split("\n")[1]);
        assertEquals(410, getShortestWalk(wireA, wireB));
    }

}