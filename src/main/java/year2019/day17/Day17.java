package year2019.day17;

import utils.FileHelper;
import utils.OpComputer;
import utils.Point;

import java.util.HashMap;
import java.util.Map;

public class Day17 {

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        String input = new FileHelper().readFile("year2019/day17/input.txt").get(0);

        HashMap<Point, Character> map = new HashMap<>();
        OpComputer computer = new OpComputer(input);
        int x = 0, y = 0;
        while (computer.isRunning()) {
            Long c = computer.runUntilOutput();
            if (c != null) {
                if (c != null) {
                    if (c.intValue() == 10) {
                        x = 0;
                        y++;
                    } else {
                        char ch = (char) c.intValue();
                        map.put(new Point(x, y), ch);
                        x++;
                    }
                }

            }
        }
        int distance = 0;
        for (Map.Entry<Point, Character> e : map.entrySet()) {
            if (e.getValue() == '#'
                    && map.containsKey(e.getKey().north()) && '#' == map.get(e.getKey().north())
                    && map.containsKey(e.getKey().east()) && '#' == map.get(e.getKey().east())
                    && map.containsKey(e.getKey().west()) && '#' == map.get(e.getKey().west())
                    && map.containsKey(e.getKey().south()) && '#' == map.get(e.getKey().south())

                    ) {
                distance += Math.abs(e.getKey().getX() * e.getKey().getY());

            }
        }
        System.out.println("Day17A: " + distance);


        //The ASCII program will use input instructions to receive them, but they need to be provided as ASCII code; end each line of logic with a single newline, ASCII code 10.
        //First, you will be prompted for the main movement routine.
        //  The main routine may only call the movement functions: A, B, or C.
        //  Supply the movement functions to use as ASCII text, separating them with commas (,, ASCII code 44)
        //  and ending the list with a newline (ASCII code 10).

        // Then, you will be prompted for each movement function.
        //  Movement functions may use L to turn left, R to turn right, or a number to move forward that many units.
        //  I.e provide the string 10,L,8,R,6 and then a newline.

        // Finally, you will be asked whether you want to see a continuous video feed; provide either y or n and a newline
        // The ASCII definitions of the main routine and the movement functions may each contain at most 20 characters, not counting the newline.

        char nl = (char)10;

        //Case A

        String mainRoutine = "A,B,A,B,C,B,C,A,C,C" + nl; //A,B,C,C,C...
        String functionA = "R,12,L,10,L,10" + nl; //R,7,L,7
        String functionB = "L,6,L,12,R,12,L,4" + nl;
        String functionC = "L,12,R,12,L,6" + nl;

        String video = "n" + nl;


        computer.reset();
        computer.setMemory(2, 0);
        for (char c : mainRoutine.toCharArray()) {
            computer.addInput((int) c);
        }
        for (char c : functionA.toCharArray()) {
            computer.addInput((int) c);
        }
        for (char c : functionB.toCharArray()){
            computer.addInput((int) c);
        }
        for(char c :functionC.toCharArray()) {
            computer.addInput((int) c);
        }
        for (char c : video.toCharArray()) {
            computer.addInput((int) c);
        }


        HashMap<Point, Character> map2 = new HashMap<>();
        x=0;
        y=0;
        long result =0L;
        while(computer.isRunning()) {
            Long c = computer.runUntilOutput();
            if (c != null) {
                result=c;
                if (c != null) {
                    if (c.intValue() == 10) {
                        x = 0;
                        y++;
                    } else {
                        char ch = (char) c.intValue();
                        map2.put(new Point(x, y), ch);
                        x++;
                    }
                }
            }
        }
        System.out.println("Day17B: "+result);
    }

}
