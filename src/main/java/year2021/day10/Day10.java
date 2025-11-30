package year2021.day10;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 {

    static Object solveA(List<String> values) {
        long score = 0L;
        for (String val : values) {
            String remaining = filterString(val);
            score+=getScore(remaining);
        }

        return score;
    }
    static String filterString(String str) {
        int start = str.length();
        str = str.replaceAll("\\[]", "")
                .replaceAll("<>", "")
                .replaceAll("\\{}", "")
                .replaceAll("\\(\\)", "");
        int end=str.length();

        if(start>end)
            return filterString(str);
        else return str;

    }

    static Object solveB(List<String> values) {
        List<Long> scores = new ArrayList<>();
        for (String val : values) {
            String remaining = filterString(val);
            long score = getScore(remaining);
            if (score>0) continue;

            score=0L;
            while (remaining.length()>0) {
                if (remaining.endsWith("(")) {
                    remaining += ")";
                    score = score*5 +1;
                } else if (remaining.endsWith("[")) {
                    remaining += "]";
                    score = score*5 + 2;
                } else if (remaining.endsWith("{")) {
                    remaining += "}";
                    score = score*5 + 3;
                } else if (remaining.endsWith("<")) {
                    remaining += ">";
                    score = score*5 + 4;
                }
                remaining = filterString(remaining);
            }
            scores.add(score);
        }
        Collections.sort(scores);
        return scores.get(scores.size()/2);

    }
    static int getScore(String str) {

        for (char c : str.toCharArray()) {
            if (c == ')') {
                return 3;
            }
            if (c == ']') {
                return 57;
            }
            if (c == '}') {
                return 1197;
            }
            if (c == '>') {
                return 25137;
            }
        }
        return 0;
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //392139
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 4001832844
    }
}
