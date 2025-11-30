package year2019.day21;

import utils.FileHelper;
import utils.OpComputer;

import java.lang.invoke.MethodHandles;

public class Day21 {

    public static void main() {
        long t0=System.currentTimeMillis();

        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        OpComputer computer = new OpComputer(inputs.get(0));

        //JUMP if J is True
        computer.addInput("NOT A T");
        computer.addInput("NOT B J");
        computer.addInput("OR T J");
        computer.addInput("NOT C T");
        computer.addInput("OR T J");
        computer.addInput("AND D J");
        computer.addInput("WALK");

        long result =0L;
        while(computer.isRunning()) {
            Long c = computer.runUntilOutput();
            if (c != null) {
                result=c;
            }
        }
        System.out.println("Day11A: "+result);


        computer.reset();

        //JUMP if J is True
        computer.addInput("NOT A T");
        computer.addInput("NOT B J");
        computer.addInput("OR T J");
        computer.addInput("NOT C T");
        computer.addInput("OR T J");
        computer.addInput("AND D J");
        computer.addInput("AND E T");
        computer.addInput("OR H T");
        computer.addInput("AND T J");

        computer.addInput("RUN");
        result =0L;
        while(computer.isRunning()) {
            Long c = computer.runUntilOutput();
            if (c != null) {
                result=c;
            }
        }
        System.out.println("Day11B: "+result);
        System.out.println("Time : "+(System.currentTimeMillis()-t0));
    }

}
