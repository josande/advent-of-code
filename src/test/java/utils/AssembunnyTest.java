package utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssembunnyTest {

    @Test
    void testCpyInt() {
        Assembunny assembunny = new Assembunny();
        assembunny.execute("cpy 41 a");
        assertEquals(41, assembunny.getA());
    }
    @Test
    void testCpyReg() {
        Assembunny assembunny = new Assembunny();
        assembunny.setA(2);
        assembunny.setB(7);
        assembunny.execute("cpy b a");
        assertEquals(7, assembunny.getA());
    }
    @Test
    void testInc() {
        Assembunny assembunny = new Assembunny();
        assembunny.setA(2);
        assembunny.execute("inc a");
        assertEquals(3, assembunny.getA());
        assembunny.execute("inc a");
        assembunny.execute("inc b");
        assertEquals(4, assembunny.getA());
    }
    @Test
    void testDec() {
        Assembunny assembunny = new Assembunny();
        assembunny.setA(2);
        assembunny.execute("dec a");
        assertEquals(1, assembunny.getA());
        assembunny.execute("dec b");
        assertEquals(1, assembunny.getA());
    }
    @Test
    void testJnz() {
        Assembunny assembunny = new Assembunny();
        assembunny.setA(0);
        assembunny.setInstruction(10);
        assertEquals(10, assembunny.getInstruction());
        assembunny.execute("jnz a 2");
        assertEquals(11, assembunny.getInstruction());
        assembunny.execute("jnz 1 -1");
        assertEquals(10, assembunny.getInstruction());
        assembunny.execute("jnz 1 1");
        assertEquals(11, assembunny.getInstruction());
    }

    @Test
    void testMultipleInstructions() {
        String instructions = """
                cpy 41 a
                inc a
                inc a
                dec a
                jnz a 2
                dec a
                """;
        var values = Arrays.stream(instructions.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assembunny assembunny = new Assembunny();
        for(String command : values) {
            assembunny.addCommand(command);
        }
        assembunny.run();
        assertEquals(42, assembunny.getA());
    }
    @Test
    void testTgl() {
        String instructions = """
                cpy 2 a
                tgl a
                tgl a
                tgl a
                cpy 1 a
                dec a
                dec a
                """;
        var values = Arrays.stream(instructions.split("\n"))
                .filter(s->!s.isEmpty())
                .map(String::valueOf)
                .collect(Collectors.toList());
        Assembunny assembunny = new Assembunny();
        for(String command : values) {
            assembunny.addCommand(command);
        }
        assembunny.run();
        assertEquals(3, assembunny.getA());
    }
}
