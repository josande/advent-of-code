package year2024.day02;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

public class Day02 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day02());
    }

    @Override
    public Object solveA(List<String> input) {
        int safe=0;
        for(String line : input) {
            String[] vals = line.split(" ");
            int lastValue = 0;

            boolean increasing = true;
            boolean decreasing = true;
            boolean diff = true;
            for (String s : vals) {
                int currentValue = Integer.parseInt(s);
                if (currentValue > lastValue) {
                    lastValue = currentValue;
                } else {
                    increasing = false;
                    break;
                }
            }
            lastValue = Integer.MAX_VALUE;
            for (String s : vals) {
                int currentValue = Integer.parseInt(s);
                if (currentValue < lastValue) {
                    lastValue = currentValue;
                } else {
                    decreasing = false;
                    break;
                }
            }
            lastValue = Integer.parseInt(vals[0]);
            for (int i = 1; i < vals.length; i++) {
                int currentValue = Integer.parseInt(vals[i]);
                if (lastValue == currentValue + 1 ||
                        lastValue == currentValue + 2 ||
                        lastValue == currentValue + 3 ||
                        lastValue == currentValue - 1 ||
                        lastValue == currentValue - 2 ||
                        lastValue == currentValue - 3) {
                    lastValue = currentValue;
                } else {
                    diff = false;
                    break;

                }


            }
            if ((increasing || decreasing) && diff)
                safe++;
        }
        return safe;

    }

    @Override
    public Object solveB(List<String> input) {
        int safe=0;
        for(String line : input) {
            String[] vals = line.split(" ");
            int lastValue;
            boolean foundSafe=false;

            for (int skipRound = 0; skipRound < vals.length; skipRound++) {
                boolean increasing = true;
                boolean decreasing = true;
                boolean diff = true;
                if (foundSafe) continue;
                lastValue=0;
                for (int r=0; r<vals.length; r++) {
                    if(r==skipRound) continue;
                    int currentValue = Integer.parseInt(vals[r]);
                    if (currentValue > lastValue) {
                        lastValue = currentValue;
                    } else {
                        increasing = false;
                        break;

                    }
                }
                lastValue = Integer.MAX_VALUE;
                for (int r=0; r<vals.length; r++) {
                    if(r==skipRound) continue;
                    int c = Integer.parseInt(vals[r]);
                    if (c < lastValue) {
                        lastValue = c;
                    } else {
                        decreasing = false;
                        break;
                    }
                }
                int startAt=1;
                if(skipRound==0) {
                    startAt=2;
                    lastValue = Integer.parseInt(vals[1]);

                } else {
                    lastValue = Integer.parseInt(vals[0]);

                }
                for (int i = startAt; i < vals.length; i++) {
                    if(i==skipRound) continue;
                    int c = Integer.parseInt(vals[i]);
                    if (lastValue == c + 1 ||
                        lastValue == c + 2 ||
                        lastValue == c + 3 ||
                        lastValue == c - 1 ||
                        lastValue == c - 2 ||
                        lastValue == c - 3) {
                        lastValue = c;
                    } else {
                        diff = false;
                        break;
                    }
                }

                if ((increasing || decreasing) && diff) {
                    safe++;
                    foundSafe=true;
                }
            }
        }
        return safe;

    }
}
