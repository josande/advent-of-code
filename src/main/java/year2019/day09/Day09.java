package year2019.day09;

import utils.FileHelper;
import utils.OpComputer;

import java.lang.invoke.MethodHandles;

public class Day09 {

    public static void main() {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");


        OpComputer computer = new OpComputer(inputs.get(0));
        computer.addInput(1);
        computer.run();
        System.out.println("Day09A: "+computer.getOutput());

        computer.reset();
        computer.addInput(2);

        computer.run();
        System.out.println("Day09B: "+computer.getOutput());
    }


}
