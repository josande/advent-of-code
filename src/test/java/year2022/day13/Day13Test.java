package year2022.day13;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    @Test
    void testSolveA(){
        String input = """                      
                [1,1,3,1,1]
                [1,1,5,1,1]
                
                [[1],[2,3,4]]
                [[1],4]
                
                [9]
                [[8,7,6]]
                
                [[4,4],4,4]
                [[4,4],4,4,4]
                
                [7,7,7,7]
                [7,7,7]
                
                []
                [3]
                
                [[[]]]
                [[]]
                
                [1,[2,[3,[4,[5,6,7]]]],8,9]
                [1,[2,[3,[4,[5,6,0]]]],8,9]
                """;
        var values = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(13, Day13.solveA(values));
    }

    @Test
    void testSolveB() {
        String input = """                      
                [1,1,3,1,1]
                [1,1,5,1,1]
                
                [[1],[2,3,4]]
                [[1],4]
                
                [9]
                [[8,7,6]]
                
                [[4,4],4,4]
                [[4,4],4,4,4]
                
                [7,7,7,7]
                [7,7,7]
                
                []
                [3]
                
                [[[]]]
                [[]]
                
                [1,[2,[3,[4,[5,6,7]]]],8,9]
                [1,[2,[3,[4,[5,6,0]]]],8,9]
                """;
        var values = Arrays.stream(input.split("\n"))
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(140, Day13.solveB(values));
    }
}