package year2020.day24;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day24Test {
    @Test
    void solveA() {
        //noinspection SpellCheckingInspection
        String input =
                """
                sesenwnenenewseeswwswswwnenewsewsw
                neeenesenwnwwswnenewnwwsewnenwseswesw
                seswneswswsenwwnwse
                nwnwneseeswswnenewneswwnewseswneseene
                swweswneswnenwsewnwneneseenw
                eesenwseswswnenwswnwnwsewwnwsene
                sewnenenenesenwsewnenwwwse
                wenwwweseeeweswwwnwwe
                wsweesenenewnwwnwsenewsenwwsesesenwne
                neeswseenwwswnwswswnw
                nenwswwsewswnenenewsenwsenwnesesenew
                enewnwewneswsewnwswenweswnenwsenwsw
                sweneswneswneneenwnewenewwneswswnese
                swwesenesewenwneswnwwneseswwne
                enesenwswwswneneswsenwnewswseenwsese
                wnwnesenesenenwwnenwsewesewsesesew
                nenewswnwewswnenesenwnesewesw
                eneswnwswnwsenenwnwnwwseeswneewsenese
                neswnwewnwnwseenwseesewsenwsweewe
                wseweeenwnesenwwwswnew
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(10, Day24.solveA(values));
    }
    @Test
    void solveB() {
        //noinspection SpellCheckingInspection
        String input =
                """
                sesenwnenenewseeswwswswwnenewsewsw
                neeenesenwnwwswnenewnwwsewnenwseswesw
                seswneswswsenwwnwse
                nwnwneseeswswnenewneswwnewseswneseene
                swweswneswnenwsewnwneneseenw
                eesenwseswswnenwswnwnwsewwnwsene
                sewnenenenesenwsewnenwwwse
                wenwwweseeeweswwwnwwe
                wsweesenenewnwwnwsenewsenwwsesesenwne
                neeswseenwwswnwswswnw
                nenwswwsewswnenenewsenwsenwnesesenew
                enewnwewneswsewnwswenweswnenwsenwsw
                sweneswneswneneenwnewenewwneswswnese
                swwesenesewenwneswnwwneseswwne
                enesenwswwswneneswsenwnewswseenwsese
                wnwnesenesenenwwnenwsewesewsesesew
                nenewswnwewswnenesenwnesewesw
                eneswnwswnwsenenwnwnwwseeswneewsenese
                neswnwewnwnwseenwseesewsenwsweewe
                wseweeenwnesenwwwswnew
                """;
        var values = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        assertEquals(2208, Day24.solveB(values));
    }
}