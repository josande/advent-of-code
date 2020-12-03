package year2019.day09;

import utils.FileHelper;
import utils.OpComputer;

public class Day09 {

    public static void main(String[] args) {
        String input = new FileHelper().readFile("year2019/day09/input.txt").get(0);

        OpComputer computer = new OpComputer(input);
        computer.addInput(2);

        computer.run();
        System.out.print("Day09A: "+computer.getOutput());
    }


}
