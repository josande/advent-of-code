package year2022.day02;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day02 {

    static Object solveA(List<String> values) {
        int score=0;
        for(String s : values) {
            char them = s.charAt(0);
            char you = s.charAt(2);

            score += getScoreFromChoice(you);
            score += getScoreFromMatch(them, you);
        }

        return score;
    }

    private static int getScoreFromChoice(char you) {
        if (you == 'X') { return 1; }
        if (you == 'Y') { return 2; }
        return 3;
    }
    static int getScoreFromMatch(char them, char you) {
        if (you == 'X') {
            if (them=='A') { return 3; }
            if (them=='B') {  return 0; }
            return 6;
        }
        if (you == 'Y') {
            if (them=='B') { return 3; }
            if (them=='C') { return 0; }
            return 6;
        }
        if (them == 'C') { return 3; }
        if (them == 'A') { return 0; }
        return 6;
    }

    static Object solveB(List<String> values) {
        int score=0;

        for(String s : values) {

            char them = s.charAt(0);
            char result = s.charAt(2);

            char you=whatDidYouDo(them, result);

            score += getScoreFromChoice(you);
            score += getScoreFromMatch(them, you);
        }

        return score;
    }



    private static char whatDidYouDo(char them, char result) {
        if(result == 'X') { //lose
            if(them == 'A') { return 'Z'; }
            if (them=='B') { return 'X'; }
            return 'Y';
        }
        if(result == 'Y') { //draw
            if(them == 'A') { return 'X'; }
            if (them=='B') { return 'Y'; }
            return 'Z';
        }
        //win
        if(them == 'A') { return 'Y'; }
        if (them=='B') { return 'Z'; }
        return 'X';
   }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //11873
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //12014
    }
}
