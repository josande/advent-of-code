package year2019.day13;

import utils.FileHelper;
import utils.OpComputer;

import java.lang.invoke.MethodHandles;

public class Day13 {

    public static void main(String[] args) {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        OpComputer computer = new OpComputer(inputs.get(0));

        int blocks = 0, score = 0;

        computer.setMemory(2L,0);
        int padX=-1, ballX=-1, balldx;

        while(computer.isRunning()) {
            computer.runUntilOutput();
            int x=-1, y=-1, val=-1;
            if (computer.isRunning()) {
                x = (int) computer.getOutput();
                computer.clearOutput();
            }
            computer.runUntilOutput();
            if (computer.isRunning()) {
                y = (int) computer.getOutput();
                computer.clearOutput();
            }
            computer.runUntilOutput();
            if ( computer.isRunning() ) {
                val = (int) computer.getOutput();
                computer.clearOutput();
            }
            if ( val == 2 ) {
                blocks++;
            }
            if ( x == -1 && y == 0 ) {
                score = val;
            } else {
                if ( val == 3 ) {
                    padX = x;
                }
                if ( val == 4 ) {
                    balldx = x - ballX;
                    ballX = x;

                    computer.addInput(calculateMovement(ballX, y, padX, balldx));
                }
            }
        }

        System.out.println("Day13A: " + blocks);
        System.out.println("Day13B: " + score);

    }
    private static int calculateMovement(int ballX, int ballY, int padX, int balldx) {
        /*
        If the joystick is in the neutral position, provide 0.
        If the joystick is tilted to the left, provide -1.
        If the joystick is tilted to the right, provide 1.
         */
        if ( ballY == 19 ) {
            balldx=0;
        }
        return Integer.compare(ballX + balldx, padX);

    }

}

