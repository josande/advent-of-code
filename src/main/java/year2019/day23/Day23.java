package year2019.day23;

import utils.FileHelper;
import utils.OpComputer;

import java.lang.invoke.MethodHandles;

public class Day23 {


    static OpComputer[] computers= new OpComputer[50];
    static boolean run=true;
    static long natValueY =-1;
    static long natValueX =-1;
    static long lastNatValue=-1;

    static Long ansA=null,ansB=null;
    public static void main(String[] args) {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");
        OpComputer comp=new OpComputer(inputs.get(0));
        for(int i=0;i<computers.length;i++) {
            computers[i]=new OpComputer(comp);
            computers[i].addInput(i);
        }
        while(run) {
            for(OpComputer c : computers) {
                c.restartIfNeeded();
                while(c.isRunning()) {
                    if (c.runUntilOutput() != null) {
                        long out1 = c.getOutput();
                        long out2 = c.runUntilOutput();
                        long out3 = c.runUntilOutput();
                        c.clearOutput();
                        processOutput(out1, out2, out3);
                    }
                }
            }
            handleNatValue();

        }
        System.out.println("Day23A "+ansA);
        System.out.println("Day23B "+ansB);
    }
    static int natCounter=0;
    static void handleNatValue() {

        for(OpComputer c : computers) {
            if(c.getNumbersOfInputLeft()>0) {
                natCounter=0;
                return;
            }
        }
        natCounter++;
        if(natCounter<2) return; //Want to get here twice in a row to be sure all system are idle
        if(natValueY ==lastNatValue) {
            ansB =  natValueY;
            run = false;
        }
        computers[0].addInput(natValueX);
        computers[0].addInput(natValueY);
        lastNatValue= natValueY;

    }
    static void processOutput(long dest, long x, long y) {
        if(dest>=computers.length) {
            if(ansA==null)
                ansA=y;
            natValueX =x;
            natValueY =y;
        }
        else
        {
            computers[(int)dest].addInput(x);
            computers[(int)dest].addInput(y);
        }
    }

}
