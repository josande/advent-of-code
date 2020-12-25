package year2020.day21;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day21Test {
    @Test
    void solveA() {
        //noinspection SpellCheckingInspection
        String input =
                """
                mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                trh fvjkl sbzzf mxmxvkd (contains dairy)
                sqjhc fvjkl (contains soy)
                sqjhc mxmxvkd sbzzf (contains fish)
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(5, Day21.solveA(values));
    }

    @Test
    void solveB() {
        String input =
                """
                mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                trh fvjkl sbzzf mxmxvkd (contains dairy)
                sqjhc fvjkl (contains soy)
                sqjhc mxmxvkd sbzzf (contains fish)
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals("mxmxvkd,sqjhc,fvjkl", Day21.solveB(values));
    }
}