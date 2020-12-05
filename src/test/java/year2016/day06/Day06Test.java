package year2016.day06;

import org.junit.jupiter.api.Test;
import year2020.day03.Day03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day06Test {

    @Test
    void testSolveA() {
        String input =
                """
                eedadn
                drvtee
                eandsr
                raavrd
                atevrs
                tsrnev
                sdttsa
                rasrtv
                nssdts
                ntnada
                svetve
                tesnvt
                vntsnd
                vrdear
                dvrsen
                enarar
                """;

        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());

        assertEquals("easter", Day06.solveA(values));
    }

    @Test
    void testSolveB() {
        String input =
                """
                eedadn
                drvtee
                eandsr
                raavrd
                atevrs
                tsrnev
                sdttsa
                rasrtv
                nssdts
                ntnada
                svetve
                tesnvt
                vntsnd
                vrdear
                dvrsen
                enarar
                """;

        List<String> values = Arrays.stream(input.split("\n")).filter(s->!s.isEmpty()).collect(Collectors.toList());

        assertEquals("advent", Day06.solveB(values));
    }
}