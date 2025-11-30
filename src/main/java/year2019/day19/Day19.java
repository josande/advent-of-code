package year2019.day19;

import utils.FileHelper;
import utils.OpComputer;

import java.lang.invoke.MethodHandles;

public class Day19 {
    public static void main() {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        OpComputer computer = new OpComputer(inputs.get(0));
        int counter=0;
        int firstHitX=0;
        int ansB=0;
        for (int y=0;y<50;y++) {
            for (int x=firstHitX;x<50;x++) {
                firstHitX=0;
                if (getValueAt(x,y,computer)==1) {
                    firstHitX = x;
                    counter++;
                }
            }
        }
        System.out.println("Day19A: "+counter); //173

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
    }
    static private int getValueAt(int x, int y, OpComputer computer) {
        computer.reset();
        computer.addInput(x);
        computer.addInput(y);
        return computer.runUntilOutput().intValue();
    }
}
