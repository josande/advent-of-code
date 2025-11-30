package year2020.day02;

import utils.FileHelper;

import java.util.List;

public class Day02 {

    static int solveA(List<String> inputs) {
        int validPasswords=0;

        for (String rule : inputs) {
            String times = rule.split(" ")[0];
            int lowerBound = Integer.parseInt(times.split("-")[0]);
            int upperBound = Integer.parseInt(times.split("-")[1]);
            char letter = rule.split(" ")[1].charAt(0);
            char[] password = rule.split(" ")[2].toCharArray();
            int match = 0;
            for (char c : password) {
                if (c == letter) {
                    match++;
                    if (match>upperBound)
                        break;
                }

            }
            if(lowerBound<=match && match <=upperBound) {
                validPasswords++;
            }
        }
        return validPasswords;
    }
    static int solveB(List<String> inputs) {
        int validPasswords=0;
        for (String rule : inputs) {
            String times = rule.split(" ")[0];
            int lowerBound = Integer.parseInt(times.split("-")[0]);
            int upperBound = Integer.parseInt(times.split("-")[1]);
            char letter = rule.split(" ")[1].toCharArray()[0];

            String pw = rule.split(" ")[2];
            if ((pw.charAt(lowerBound-1) == letter) != (pw.charAt(upperBound-1) == letter))
                validPasswords++;

        }
        return validPasswords;
    }
    public static void main(){
        String day = "Day02";

        List<String> inputs = new FileHelper().readFile("2020/"+day+".txt");

        long t0 = System.currentTimeMillis();
        int ansA = solveA(inputs);
        long t1 = System.currentTimeMillis();
        int ansB = solveB(inputs);
        var timePart1 = t1-t0;
        long timePart2 = System.currentTimeMillis() - t1;
        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //469
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //267
    }
}
