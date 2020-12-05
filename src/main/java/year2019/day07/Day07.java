package year2019.day07;

import utils.FileHelper;
import utils.OpComputer;

public class Day07 {

    static long getMaxThruster(int rangeStart, int rangeEnd, OpComputer ampA, OpComputer ampB, OpComputer ampC, OpComputer ampD, OpComputer ampE) {
        long maxThrust=0;
        for (int i=rangeStart; i<=rangeEnd; i++) {
            for (int j=rangeStart; j<=rangeEnd; j++) {
                if (i!=j)
                    for (int k=rangeStart; k<=rangeEnd; k++) {
                        if (k!=i && k!=j)
                            for (int l=rangeStart; l<=rangeEnd; l++) {
                                if (l!=i && l!=j && l!=k)
                                    for (int m=rangeStart; m<=rangeEnd; m++) {
                                        if (m!=i && m!=j && m!=k  && m!=l) {

                                            ampA.addInput(i);
                                            ampB.addInput(j);
                                            ampC.addInput(k);
                                            ampD.addInput(l);
                                            ampE.addInput(m);


                                            ampA.addInput(0);
                                            ampA.run();

                                            ampB.addInput(ampA.getOutput());
                                            ampB.run();

                                            ampC.addInput(ampB.getOutput());
                                            ampC.run();

                                            ampD.addInput(ampC.getOutput());
                                            ampD.run();

                                            ampE.addInput(ampD.getOutput());
                                            ampE.run();

                                            long thrust=ampE.getOutput();
                                            maxThrust=Math.max(thrust,maxThrust);

                                            ampA.reset();
                                            ampB.reset();
                                            ampC.reset();
                                            ampD.reset();
                                            ampE.reset();
                                        }
                                    }
                            }
                    }
            }
        }
        return maxThrust;
    }
    static int getMaxThrusterWithLoop(int rangeStart, int rangeEnd, OpComputer ampA, OpComputer ampB, OpComputer ampC, OpComputer ampD, OpComputer ampE) {
        int maxThrust=0;
        for (int i=rangeStart; i<=rangeEnd; i++) {
            for (int j=rangeStart; j<=rangeEnd; j++) {
                if (i!=j)
                    for (int k=rangeStart; k<=rangeEnd; k++) {
                        if (k!=i && k!=j)
                            for (int l=rangeStart; l<=rangeEnd; l++) {
                                if (l!=i && l!=j && l!=k)
                                    for (int m=rangeStart; m<=rangeEnd; m++) {
                                        if (m!=i && m!=j && m!=k  && m!=l) {

                                            ampA.addInput(i);
                                            ampB.addInput(j);
                                            ampC.addInput(k);
                                            ampD.addInput(l);
                                            ampE.addInput(m);
                                            int thrust = getThrustOutput(ampA,ampB,ampC,ampD,ampE);
                                            maxThrust=Math.max(maxThrust, thrust);
                                            ampA.reset();
                                            ampB.reset();
                                            ampC.reset();
                                            ampD.reset();
                                            ampE.reset();
                                        }
                                    }
                            }
                    }
            }
        }
        return maxThrust;
    }
    static int getThrustOutput(OpComputer ampA, OpComputer ampB, OpComputer ampC, OpComputer ampD, OpComputer ampE) {
        Long maxThrust=0L, thrust=0L;
        while (true) {
            ampA.addInput(thrust);
            ampA.runUntilOutput();
            if (!ampA.isRunning())
                return maxThrust.intValue();
            ampB.addInput(ampA.getOutput());
            ampA.clearOutput();
            ampA.restartIfNeeded();

            ampB.restartIfNeeded();
            ampB.runUntilOutput();
            if (!ampB.isRunning())
                return maxThrust.intValue();
            ampC.addInput(ampB.getOutput());
            ampB.clearOutput();

            ampC.restartIfNeeded();
            ampC.runUntilOutput();
            if (!ampC.isRunning())
                return maxThrust.intValue();
            ampD.addInput(ampC.getOutput());
            ampC.clearOutput();

            ampD.restartIfNeeded();
            ampD.runUntilOutput();
            if (!ampD.isRunning())
                return maxThrust.intValue();
            ampE.addInput(ampD.getOutput());
            ampD.clearOutput();

            ampE.restartIfNeeded();
            ampE.runUntilOutput();
            if (!ampE.isRunning())
                return maxThrust.intValue();
            thrust=ampE.getOutput();
            ampE.clearOutput();

            maxThrust = Math.max(thrust, maxThrust);
        }
    }
    public static void main(String[] args) {
        String input = new FileHelper().readFile("year2019/day07/input.txt").get(0);
        OpComputer ampA=new OpComputer(input);
        OpComputer ampB=new OpComputer(input);
        OpComputer ampC=new OpComputer(input);
        OpComputer ampD=new OpComputer(input);
        OpComputer ampE=new OpComputer(input);

        System.out.println("Day07A "+ getMaxThruster(0,4,ampA,ampB,ampC,ampD,ampE));
        System.out.println("Day07B "+ getMaxThrusterWithLoop(5,9,ampA,ampB,ampC,ampD,ampE));

    }

}
