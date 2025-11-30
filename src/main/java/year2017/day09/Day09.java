package year2017.day09;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

public class Day09 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day09());
    }

    @Override
    public Object solveA(List<String> input) {
        String row = input.get(0);
        int score = 0;
        int level = 0;
        boolean garbage = false;
        for(int i=0; i<row.length(); i++) {
            char c = row.charAt(i);
            if(c == '!') {i++;continue;}
            if(c=='>') garbage = false;
            if(c=='<') garbage = true;

            if(!garbage) {
                if (c == '{') {
                    level++;
                    score += level;
                } else if (c == '}') level--;
            }
        }

        return score;
    }

    @Override
    public Object solveB(List<String> input) {
        String row = input.get(0);
        int score = 0;
        boolean garbage = false;
        for(int i=0; i<row.length(); i++) {
            char c = row.charAt(i);
            if(c == '!') {i++;continue;}
            if(c=='>') garbage = false;
            if(garbage) score ++;
            if(c=='<') garbage = true;
        }

        return score;
    }
}
