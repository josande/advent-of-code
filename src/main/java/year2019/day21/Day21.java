package year2019.day21;

import utils.FileHelper;
import utils.OpComputer;
import utils.Point;

import java.util.HashMap;

public class Day21 {

    public static void main(String[] args) {
        long t0=System.currentTimeMillis();

        String input = new FileHelper().readFile("year2019/day21/input.txt").get(0);

        HashMap<Point, Character> map = new HashMap<>();
        OpComputer computer = new OpComputer(input);
        int x = 0, y = 0;


        //JUMP if J is True
        computer.addInput("NOT A T");
        computer.addInput("NOT B J");
        computer.addInput("OR T J");
        computer.addInput("NOT C T");
        computer.addInput("OR T J");
        computer.addInput("AND D J");
        computer.addInput("WALK");


        x=0;
        y=0;
        long result =0L;
        while(computer.isRunning()) {
            Long c = computer.runUntilOutput();
            if (c != null) {
                result=c;
           /*     if (c != null) {
                    if (c.intValue() == 10) {
                        x = 0;
                        y++;
                    } else {
                        char ch = (char) c.intValue();
                        map.put(new Point(x, y), ch);
                        x++;
                    }
                }*/
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
        x=0;
        y=0;
        result =0L;
        while(computer.isRunning()) {
            Long c = computer.runUntilOutput();
            if (c != null) {
                result=c;
/*                if (c != null) {
                    if (c.intValue() == 10) {
                        x = 0;
                        y++;
                    } else {
                        char ch = (char) c.intValue();
                        map.put(new Point(x, y), ch);
                        x++;
                    }
                }*/
            }
        }
        System.out.println("Day11B: "+result);
        System.out.println("Time : "+(System.currentTimeMillis()-t0));
    }

}
