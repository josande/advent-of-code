package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpComputerTest {
    //Day 2 Part A tests
    @Test
    void test_day2_a_0() {
        String input ="1,9,10,3,2,3,11,0,99,30,40,50";
        OpComputer computer = new OpComputer(input);
        computer.run();
        assertEquals(3500, computer.getMemory(0));
    }
    @Test
    void test_day2_a_1() {
        // 1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2).
        String input ="1,0,0,0,99";
        OpComputer computer = new OpComputer(input);
        computer.run();
        assertEquals(2, computer.getMemory(0));
        assertEquals(0, computer.getMemory(1));
        assertEquals(0, computer.getMemory(2));
        assertEquals(0, computer.getMemory(3));
        assertEquals(99, computer.getMemory(4));
    }
    @Test
    void test_day2_a_2() {
        // 2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6).
        String input ="2,3,0,3,99";
        OpComputer computer = new OpComputer(input);
        computer.run();
        assertEquals(2, computer.getMemory(0));
        assertEquals(3, computer.getMemory(1));
        assertEquals(0, computer.getMemory(2));
        assertEquals(6, computer.getMemory(3));
        assertEquals(99, computer.getMemory(4));
    }
    @Test
    void test_day2_a_3() {
        //2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801)
        String input ="2,4,4,5,99,0";
        OpComputer computer = new OpComputer(input);
        computer.run();
        assertEquals(2, computer.getMemory(0));
        assertEquals(4, computer.getMemory(1));
        assertEquals(4, computer.getMemory(2));
        assertEquals(5, computer.getMemory(3));
        assertEquals(99, computer.getMemory(4));
        assertEquals(9801, computer.getMemory(5));
    }
    @Test
    void test_day2_a_4() {
        //1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99.
        String input ="1,1,1,4,99,5,6,0,99";
        OpComputer computer = new OpComputer(input);
        computer.run();
        assertEquals(30, computer.getMemory(0));
        assertEquals(1, computer.getMemory(1));
        assertEquals(1, computer.getMemory(2));
        assertEquals(4, computer.getMemory(3));
        assertEquals(2, computer.getMemory(4));
        assertEquals(5, computer.getMemory(5));
        assertEquals(6, computer.getMemory(6));
        assertEquals(0, computer.getMemory(7));
        assertEquals(99, computer.getMemory(8));
    }
    @Test
    void testPuzzleDay2A(){
        String input = new FileHelper().readFile("year2019/day02/input.txt").get(0);
        OpComputer computer = new OpComputer(input);
        computer.setMemory(12, 1);
        computer.setMemory(2, 2);
        computer.run();
        assertEquals(2890696, computer.getMemory(0));
    }
    //Day 2 Part B tests
    @Test
    void testPuzzleDay2B(){
        String input = new FileHelper().readFile("year2019/day02/input.txt").get(0);
        OpComputer computer = new OpComputer(input);
        computer.setMemory(82, 1);
        computer.setMemory(26, 2);
        computer.run();
        assertEquals(19690720, computer.getMemory(0));
    }


    //Day5 Part A tests
    @Test
    void test_day5_a_0() {
        // Multiplication using immediate and positional values
        //99 is written to address 4
        String input="1002,4,3,4,33";
        OpComputer computer = new OpComputer(input);
        computer.run();
        assertEquals(99, computer.getMemory(4));
    }
    @Test
    void testPuzzleDay5A(){
        String input = new FileHelper().readFile("year2019/day05/input.txt").get(0);
        OpComputer computer = new OpComputer(input);
        computer.addInput(1);
        computer.run();
        assertEquals(14155342, computer.getOutput());
    }

    //Day5 Part B tests
    @Test
    void test_day5_b_0() {
        // Uses an input instruction to ask for a single number.
        // The program will then output 999 if the input value is below 8,
        // output 1000 if the input value is equal to 8,
        // or output 1001 if the input value is greater than 8.
        String input="3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
        OpComputer computer = new OpComputer(input);
        computer.addInput(7);
        computer.run();
        assertEquals(999, computer.getOutput());

        computer.reset();
        computer.addInput(8);
        computer.run();
        assertEquals(1000, computer.getOutput());

        computer.reset();
        computer.addInput(9);
        computer.run();
        assertEquals(1001, computer.getOutput());
    }
    @Test
    void test_day5_b_1() {
        // Here are some jump tests that take an input, then output 0 if the input was zero or 1 if the input was non-zero:
        //Jump using immediate mode
        String input="3,3,1105,-1,9,1101,0,0,12,4,12,99,1";
        OpComputer computer = new OpComputer(input);
        computer.addInput(17);
        computer.run();
        assertEquals(1, computer.getOutput());

        computer.reset();
        computer.addInput(0);
        computer.run();
        assertEquals(0, computer.getOutput());
    }

    @Test
    void test_day5_b_2() {
        // Here are some jump tests that take an input, then output 0 if the input was zero or 1 if the input was non-zero:
        //Jump using position mode
        String input="3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9";
        OpComputer computer = new OpComputer(input);
        computer.addInput(17);
        computer.run();
        assertEquals(1, computer.getOutput());
    }
    @Test
    void test_day5_b_3() {
        // Here are some jump tests that take an input, then output 0 if the input was zero or 1 if the input was non-zero:
        //Jump using position mode
        String input="3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9";
        OpComputer computer = new OpComputer(input);
        computer.addInput(0);
        computer.run();
        assertEquals(0, computer.getOutput());
    }

    @Test
    void test_day5_b_4() {
        // 3,9,8,9,10,9,4,9,99,-1,8 - Using position mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
        String input="3,9,8,9,10,9,4,9,99,-1,8";
        OpComputer computer = new OpComputer(input);
        computer.addInput(7);
        computer.run();
        assertEquals(0, computer.getOutput());

        computer.reset();
        computer.addInput(8);
        computer.run();
        assertEquals(1, computer.getOutput());

        computer.reset();
        computer.addInput(9);
        computer.run();
        assertEquals(0, computer.getOutput());
    }
    @Test
    void test_day5_b_5() {
        //3,9,7,9,10,9,4,9,99,-1,8 - Using position mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
        String input="3,9,7,9,10,9,4,9,99,-1,8";
        OpComputer computer = new OpComputer(input);
        computer.addInput(7);
        computer.run();
        assertEquals(1, computer.getOutput());

        computer.reset();
        computer.addInput(8);
        computer.run();
        assertEquals(0, computer.getOutput());

        computer.reset();
        computer.addInput(9);
        computer.run();
        assertEquals(0, computer.getOutput());
    }
    @Test
    void test_day5_b_6() {
        // 3,3,1108,-1,8,3,4,3,99 - Using immediate mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
        String input="3,3,1108,-1,8,3,4,3,99";
        OpComputer computer = new OpComputer(input);
        computer.addInput(7);
        computer.run();
        assertEquals(0, computer.getOutput());

        computer.reset();
        computer.addInput(8);
        computer.run();
        assertEquals(1, computer.getOutput());

        computer.reset();
        computer.addInput(9);
        computer.run();
        assertEquals(0, computer.getOutput());
    }
    @Test
    void test_day5_b_7() {
        // 3,3,1107,-1,8,3,4,3,99 - Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
        String input="3,3,1107,-1,8,3,4,3,99";
        OpComputer computer = new OpComputer(input);
        computer.addInput(7);
        computer.run();
        assertEquals(1, computer.getOutput());

        computer.reset();
        computer.addInput(8);
        computer.run();
        assertEquals(0, computer.getOutput());

        computer.reset();
        computer.addInput(9);
        computer.run();
        assertEquals(0, computer.getOutput());
    }

    @Test
    void testPuzzleDay5B(){
        String input = new FileHelper().readFile("year2019/day05/input.txt").get(0);
        OpComputer computer = new OpComputer(input);
        computer.addInput(5);
        computer.run();
        assertEquals(8684145, computer.getOutput());
    }
    //Day9
    @Test
    void test_day9_1() {
        String input ="109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
        OpComputer computer = new OpComputer(input);
        computer.run();

        assertEquals(109, computer.getMemory(0));
        assertEquals(1, computer.getMemory(1));
        assertEquals(204, computer.getMemory(2));
        assertEquals(-1, computer.getMemory(3));
        assertEquals(1001, computer.getMemory(4));
        assertEquals(100, computer.getMemory(5));
        assertEquals(1, computer.getMemory(6));
        assertEquals(100, computer.getMemory(7));
        assertEquals(1008, computer.getMemory(8));
        assertEquals(100, computer.getMemory(9));
        assertEquals(16, computer.getMemory(10));
        assertEquals(101, computer.getMemory(11));
        assertEquals(1006, computer.getMemory(12));
        assertEquals(101, computer.getMemory(13));
        assertEquals(0, computer.getMemory(14));
        assertEquals(99, computer.getMemory(15));

    }
    @Test
    void test_day9_2(){
        String input ="1102,34915192,34915192,7,4,7,99,0";
        OpComputer computer = new OpComputer(input);
        computer.run();
        assertEquals(16, String.valueOf(computer.getOutput()).length());
    }

    @Test
    void test_day9_3(){
        String input ="109,19,204,-34,99";
        OpComputer computer = new OpComputer(input);
        computer.setRelativeBase(2000);
        computer.setMemory(177,1985);
        computer.run();
        assertEquals(177, computer.getOutput());
    }

}