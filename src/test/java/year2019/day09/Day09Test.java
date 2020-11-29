package year2019.day09;

import org.junit.jupiter.api.Test;
import utils.OpComputer;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {

    @Test
    public void testRelativeBase () {
        OpComputer computer = new OpComputer("109,19,99");
computer.setRelativeBase(2000);
        computer.run();
        assertEquals(2019, computer.getRelativeBase());
    }

    @Test
    public void testBigNumber () {
        OpComputer computer = new OpComputer("104,1125899906842624,99");
        computer.run();
        System.out.println(computer.getMemory(0));
        System.out.println(computer.getMemory(1));
        System.out.println(computer.getMemory(2));
    }

}