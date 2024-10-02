package year2021.day18;

import org.junit.jupiter.api.Test;
import year2021.day11.Day11;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    @Test
    void testExplode() {
        assertEquals("[[[[0,9],2],3],4]", Day18.explode("[[[[[9,8],1],2],3],4]"));
        assertEquals("[7,[6,[5,[7,0]]]]", Day18.explode("[7,[6,[5,[4,[3,2]]]]]"));
        assertEquals("[[6,[5,[7,0]]],3]", Day18.explode("[[6,[5,[4,[3,2]]]],1]"));
        assertEquals("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", Day18.explode("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]"));
        assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", Day18.explode("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"));
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", Day18.explode("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"));
    }
    @Test
    void testSplit() {
        assertEquals("[5,5]", Day18.split("10"));
        assertEquals("[5,[5,5]]", Day18.split("[5,10]"));
        assertEquals("[5,6]", Day18.split("11"));
        assertEquals("[[5,6],11]", Day18.split("[11,11]"));
        assertEquals("[6,6]", Day18.split("12"));
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", Day18.split("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"));


    }
    @Test
    void testCombine() {
        assertEquals("[[1,2],[[3,4],5]]", Day18.comnbine("[1,2]", "[[3,4],5]"));
    }

    @Test
    void testReduce()  {
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", Day18.reduce("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]"));
    }

    @Test
    void testSolveA1() {

        String input = """
                [[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]
                [7,[[[3,7],[4,3]],[[6,3],[8,8]]]]
                [[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]
                [[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]
                [7,[5,[[3,8],[1,4]]]]
                [[2,[2,2]],[8,[8,1]]]
                [2,9]
                [1,[[[9,3],9],[[9,0],[0,7]]]]
                [[[5,[7,4]],7],1]
                [[[[4,2],2],6],[8,7]]
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(3488L, Day18.solveA(values));


    }


    @Test
    void solveA() {
        String input = """
                [[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]
                [7,[[[3,7],[4,3]],[[6,3],[8,8]]]]
                [[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]
                [[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]
                [7,[5,[[3,8],[1,4]]]]
                [[2,[2,2]],[8,[8,1]]]
                [2,9]
                [1,[[[9,3],9],[[9,0],[0,7]]]]
                [[[5,[7,4]],7],1]
                [[[[4,2],2],6],[8,7]]
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(3488L, Day18.solveA(values));
    }

    @Test
    void solveB() {
        String input = """
                [[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
                [[[5,[2,8]],4],[5,[[9,9],0]]]
                [6,[[[6,2],[5,6]],[[7,6],[4,7]]]]
                [[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]
                [[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]
                [[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]
                [[[[5,4],[7,7]],8],[[8,3],8]]
                [[9,3],[[9,9],[6,[4,9]]]]
                [[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]
                [[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]
                """;
        var values = Arrays.stream(input.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(3993L, Day18.solveB(values));
    }
}