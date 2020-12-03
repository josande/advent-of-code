package year2019.day13;

import utils.FileHelper;
import utils.OpComputer;
import utils.Point;

import java.util.HashMap;

public class Day13 {

    private static char convertToChar(int val) {
        if (val==0) return ' '; //Empty
        if (val==1) return '#'; //Wall
        if (val==2) return '¤'; //Block
        if (val==3) return '_'; //Horizontal Padd le
        if (val==4) return 'o'; //Ball

        return '?';
    }
    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        String input = new FileHelper().readFile("year2019/day13/input.txt").get(0);

        OpComputer computer = new OpComputer(input);

        int blocks = 0, score = 0;

        HashMap<Point, Character> screen = new HashMap<>();
        computer.setMemory(2L,0);
        int padX=-1, padY=-1, ballX=-1, ballY=-1, balldx=0;

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
                Point p = new Point(x, y);
                screen.put(p, convertToChar(val));
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
        System.out.println("Time: "+(System.currentTimeMillis()-t0)+" ms");

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
        if ((ballX+balldx) > padX) return  1;
        if ((ballX+balldx) < padX) return -1;
        return 0;

    }

}

