package year2019.day22;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static year2019.day22.Day22.*;
import static org.junit.jupiter.api.Assertions.*;

class Day22Test {

    @Test
    void testDealIntoNewStack() {
        /*
        Top          Bottom
    0 1 2 3 4 5 6 7 8 9   Your deck
                          New stack
         */
        setDeckSize(10);
        setCardToTrack(9);
        assertEquals(9, getCardPosition());
        dealIntoNewOrder();
        assertEquals(0, getCardPosition());
    }

    @Test
    void testCutWithPositiveValue() {
        /*
Top          Bottom
0 1 2 3 4 5 6 7 8 9   Your deck
3 4 5 6 7 8 9 0 1 2   New stack
         */
        setDeckSize(10);
        setCardToTrack(9);
        cut(3);
        assertEquals(6, getCardPosition());

    }


    @Test
    void testCutWithPositiveValueB() {
        /*
Top          Bottom
0 1 2 3 4 5 6 7 8 9   Your deck
3 4 5 6 7 8 9 0 1 2   New stack
         */
        setDeckSize(10);
        setCardToTrack(1);
        cut(3);
        assertEquals(8, getCardPosition());

    }

    @Test
    void testCutWithNegativeValue() {
        /*
Top          Bottom
0 1 2 3 4 5 6 a 8 9   Your deck
6 a 8 9 0 1 2 3 4 5   New stack
         */
        setDeckSize(10);
        setCardToTrack(9);
        cut(-4);
        assertEquals(3, getCardPosition());

    }

    @Test
    void testCutWithNegativeValue2() {
        /*
Top          Bottom
0 1 2 3 4 5 6 a 8 9   Your deck
6 a 8 9 0 1 2 3 4 5   New stack
         */
        setDeckSize(10);
        setCardToTrack(1);
        cut(-4);
        assertEquals(5, getCardPosition());

    }

    @Test
    void testDealWithIncrement() {
        /*
Top          Bottom
0 1 2 3 4 5 6 a 8 9   Your deck
0 a 4 1 8 5 2 9 6 3   New stack
         */
        setDeckSize(10);
        setCardToTrack(9);
        deal(3);
        assertEquals(7, getCardPosition());

    }

    @Test
    void handleInstructions() {
        String input = """
                deal with increment 7
                deal into new stack
                deal into new stack
                """;



        List<String> commands = Arrays.asList(input.split("\n"));
        setDeckSize(10);
        setCardToTrack(3);
        shuffle(commands);


        /* Result: 0 3 6 9 2 5 8 1 4  */
        assertEquals(1, getCardPosition());

    }

    @Test
    void handleInstructions2() {
        String input =
                """
                deal into new stack
                cut -2
                deal with increment 7
                cut 8
                cut -4
                deal with increment 7
                cut 3
                deal with increment 9
                deal with increment 3
                cut -1
                """;
        List<String> commands = Arrays.asList(input.split("\n"));
        setDeckSize(10);
        setCardToTrack(9);
        shuffle(commands);

        /* Result: 9 2 5 8 1 4 a 0 3 6  */
        assertEquals(0, getCardPosition());


    }
}