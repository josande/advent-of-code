package year2019.day19;

import utils.FileHelper;
import utils.OpComputer;

public class Day19 {
    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        String input = new FileHelper().readFile("year2019/day19/input.txt").get(0);
        OpComputer computer = new OpComputer(input);
        int counter=0;
        int firstHitX=0;
        int ansB=0;
        for (int y=0;y<50;y++) {
            for (int x=firstHitX;x<50;x++) {
                firstHitX=0;
                int out = getValueAt(x,y,computer);
                if (out==1) {
                    if (firstHitX == 0) {
                        firstHitX = x;
                    }
                    counter++;
                } else if (firstHitX > 0) {
                    x = 50;
                }
            }
        }
        System.out.println("Day19A: "+counter); //173
        long t1 = System.currentTimeMillis();

        System.out.println("TimeA "+(System.currentTimeMillis()-t0));

        for (int y=50; ansB==0; y++) {
            for (int x=firstHitX;x<10000;x++) {
                firstHitX=-1;
                int out = getValueAt(x,y,computer);
                if (out == 1) {
                    firstHitX = x;
                    int topRight=getValueAt(99+x,y-99,computer);
                    if (topRight==1) {
                        ansB=x*10000 + y-99;
                    }
                    counter += out;
                    x=10000;
                }
            }
        }
        System.out.println("Day19B: "+ansB); //6671097
        System.out.println("TimeB "+(System.currentTimeMillis()-t1));
        System.out.println("Time: "+(System.currentTimeMillis()-t0)+" ms");
    }
    static private int getValueAt(int x, int y, OpComputer computer) {
        computer.reset();
        computer.addInput(x);
        computer.addInput(y);
        return computer.runUntilOutput().intValue();
    }
}
