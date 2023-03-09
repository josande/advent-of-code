
package year2017.day15;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

public class Day15 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day15());
    }

    @Override
    public Object solveA(List<String> input) {
        long genA=Long.parseLong(input.get(0).split(" ")[4]);
        long genB=Long.parseLong(input.get(1).split(" ")[4]);
        long facA=16807L;
        long facB=48271;
        long divider = 2147483647L;
        int iterations = 40000000;

        int matches=0;
        for(int i=0; i<iterations;i++) {
            genA = genA*facA % divider;
            genB = genB*facB % divider;
            if(((genA & 65535) ^ (genB & 65535))==0)
                matches++;
        }
        return matches;
    }

    @Override
    public Object solveB(List<String> input) {
        long genA=Long.parseLong(input.get(0).split(" ")[4]);
        long genB=Long.parseLong(input.get(1).split(" ")[4]);
        long facA=16807L;
        long facB=48271;
        long divider = 2147483647L;
        int iterations = 5000000;

        int matches=0;
        Long[] valA = new Long[iterations];
        Long[] valB = new Long[iterations];

        for(int i=0; i<iterations; ) {
            genA = genA * facA % divider;
            if(genA%4==0) {
                valA[i]=genA;
                i++;
            }
        }
        for(int i=0; i<iterations; ) {
            genB = genB * facB % divider;
            if(genB%8==0) {
                valB[i]=genB;
                i++;
            }
        }

        for(int i=0; i<iterations;i++) {
            if(((valA[i] & 65535) ^ (valB[i] & 65535))==0) matches++;
        }
        return matches;
    }
}
