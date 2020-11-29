package year2019.day18;

import org.junit.jupiter.api.Test;
import utils.Point;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static year2019.day18.Day18.*;
import static org.junit.jupiter.api.Assertions.*;

class Day18Test {
    @Test
    void testA1() {
        String inputStr=
                """
                ###########
                #b.A.@.a.B#
                ###########
                """;
        List<String> input = Arrays.asList(inputStr.split("\n"));

        HashMap<Point, Character> map = makeMap(input);
        map= simplifyMap(map);

        Point[] entrances = getEntrances(map);
        for (Point e : entrances) {

            State startState = new Day18.State(e, map);
            assertEquals(8, findShortestDistance(startState));
        }
    }


    @Test
    void testA2() {
        String inputStr=
                """
                ########################
                #f.D.E.e.C.b.A.@.a.B.c.#
                ######################.#
                #d.....................#
                ########################
                """;
        List<String> input = Arrays.asList(inputStr.split("\n"));

        HashMap<Point, Character> map = makeMap(input);
        map= simplifyMap(map);
        Point[] entrances = getEntrances(map);

        for (Point e : entrances) {
            State startState = new Day18.State(e, map);
            assertEquals(86, findShortestDistance(startState));
        }
    }

    @Test
    void makeMapPartB() {
        String inputStr =
                """
                #######
                #a.#Cd#
                ##...##
                ##.@.##
                ##...##
                #cB#Ab#
                #######
                """;

        List<String> input = Arrays.asList(inputStr.split("\n"));
        HashMap<Point, Character> map = makeMap(input);
        updateEntrances(map);
        String expectedStr=
                """
                #######
                #a.#Cd#
                ##@#@##
                #######
                ##@#@##
                #cB#Ab#
                #######
                """;

        List<String> expected = Arrays.asList(expectedStr.split("\n"));
        HashMap<Point, Character> exp = makeMap(expected);

        assertEquals(exp, map);

        Point[] entrances = getEntrances(map);
        removeAllDoors(map);
        int steps = 0;
        for (Point p : entrances) {
            steps+=getAllKeysInCave(map, p);
        }
        assertEquals(8,steps);

    }

    @Test
    void makeMapPartB2() {
        String inputStr =
               """                 
               #######
               #a.#Cd#
               ##...##
               ##.@.##
               ##...##
               #cB#Ab#
               #######
               """;
        List<String> input = Arrays.asList(inputStr.split("\n"));
        HashMap<Point, Character> map = makeMap(input);
        updateEntrances(map);
        removeAllDoors(map);
    }

}