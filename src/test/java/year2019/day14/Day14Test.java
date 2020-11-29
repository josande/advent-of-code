package year2019.day14;

import org.junit.jupiter.api.Test;
import utils.FileHelper;

import java.util.Arrays;
import java.util.List;

import static year2019.day14.Day14.amountOfOreForFuel;
import static year2019.day14.Day14.amountOfOreForOneFuel;
import static year2019.day14.Day14.sortInput;
import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

    @Test
    void testOreExample1() {
        String input =
                """
                9 ORE => 2 A
                8 ORE => 3 B
                7 ORE => 5 C
                3 A, 4 B => 1 AB
                5 B, 7 C => 1 BC
                4 C, 1 A => 1 CA
                2 AB, 3 BC, 4 CA => 1 FUEL
                """;
        sortInput(Arrays.asList(input.split("\n")));
        assertEquals(165, amountOfOreForOneFuel());
    }

    @Test
    void testMakeManyOreAtOnce() {
        String input =
                """
                9 ORE => 2 A
                8 ORE => 3 B
                7 ORE => 5 C
                3 A, 4 B => 1 AB
                5 B, 7 C => 1 BC
                4 C, 1 A => 1 CA
                2 AB, 3 BC, 4 CA => 1 FUEL
                """;
        sortInput(Arrays.asList(input.split("\n")));

        long ore=0;
        for (int i=0;i<100;i++) {
            ore+=amountOfOreForOneFuel();
        }
        sortInput(Arrays.asList(input.split("\n")));

        assertEquals(ore, amountOfOreForFuel(100));
    }

}