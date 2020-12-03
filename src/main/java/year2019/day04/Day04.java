package year2019.day04;

import utils.FileHelper;
import utils.Point;

import java.util.HashMap;
import java.util.List;

public class Day04 {

    static boolean testPasswordA(int number1, int number2, int number3, int number4, int number5, int number6) {
        return hasPair(number1,number2,number3,number4,number5,number6);
    }
    static boolean testPasswordB(int number1, int number2, int number3, int number4, int number5, int number6) {
        return hasPairWithoutTriple(number1,number2,number3,number4,number5,number6);
    }
    static private boolean hasPair(int number1, int number2, int number3, int number4, int number5, int number6) {
        return (number1 == number2) ||
                (number2 == number3) ||
                (number3 == number4) ||
                (number4 == number5) ||
                (number5 == number6);
    }
    static private boolean hasPairWithoutTriple(int number1, int number2, int number3, int number4, int number5, int number6) {
        return                        (number1 == number2 && number2 != number3) ||
                (number1 != number2 && number2 == number3 && number3 != number4) ||
                (number2 != number3 && number3 == number4 && number4 != number5) ||
                (number3 != number4 && number4 == number5 && number5 != number6) ||
                (number4 != number5 && number5 == number6);
    }
    static private boolean isIncreasing(int number1, int number2, int number3, int number4, int number5, int number6) {
        return (number1<=number2 &&
                number2<=number3 &&
                number3<=number4 &&
                number4<=number5 &&
                number5<=number6);
    }
    public static void main(String[] args){
        String input = new FileHelper().readFile("year2019/day04/input.txt").get(0);
        int start=Integer.valueOf(input.split("-")[0]);
        int end  =Integer.valueOf(input.split("-")[1]);
        int passwordsA=0, passwordsB=0;

        for (int number=start; number<=end; number++) {
            int number1=(number%1000000 -number%100000)/100000;
            int number2=(number%100000  -number%10000) /10000;
            int number3=(number%10000   -number%1000)  /1000;
            int number4=(number%1000    -number%100)   /100;
            int number5=(number%100     -number%10)    /10;
            int number6=(number%10      -number%1)     /1;
            if (!isIncreasing(number1,number2,number3,number4,number5,number6)) {continue;}
            if (testPasswordA(number1,number2,number3,number4,number5,number6))
                passwordsA++;
            if (testPasswordB(number1,number2,number3,number4,number5,number6))
                passwordsB++;
        }
        System.out.println("Day04A "+passwordsA);
        System.out.println("Day04B "+passwordsB);
    }
}