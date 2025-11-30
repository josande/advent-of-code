package year2023.day01;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

public class Day01 implements AdventOfCode {
    enum Number {
        ONE("one",1),
        ONE_DIGIT("1",1),
        TWO("two",2),
        TWO_DIGIT("2",2),
        THREE("three",3),
        THREE_DIGIT("3",3),
        FOUR("four",4),
        FOUR_DIGIT("4",4),
        FIVE("five",5),
        FIVE_DIGIT("5",5),
        SIX("six",6),
        SIX_DIGIT("6",6),
        SEVEN("seven",7),
        SEVEN_DIGIT("7",7),
        EIGHT("eight",8),
        EIGHT_DIGIT("8",8),
        NINE("nine",9),
        NINE_DIGIT("9",9),
        TEN("ten",10),
        ELEVEM("eleven",11),
        TWELVE("twelve",12),
        THIRTEEN("thirteen",13),
        FOURTEEN("fourteen",14),
        FIFTEEN("fifteen",15),
        SIXTEEN("sixteen",16),
        SEVENTEEN("seventeen",17),
        EIGHTEEN("eighteen",18),
        NINETEEN("nineteen",19),
        TWENTY("twenty",20),
        THIRTY("thirty",30),
        FOURTY("fourty",40),
        FIFTY("fifty",50),
        SIXTY("sixty",60),
        SEVENTY("seventy",70),
        EIGHTY("eighty",80),
        NINETY("ninety",90);

        private final String name;
        private final int value;


        Number(String name, int value) {
            this.name=name;
            this.value=value;
        }

        public int getFirstDigit() {
            if (value>9) return value/10;
            return value;
        }
        public int getLastDigit() {
            return value%10;
        }


        public String getName() {
            return name;
        }


    }
    public static void main(){
        Reporter.report(new Day01());
    }

    @Override
    public Object solveA(List<String> input) {

        int sum=0;
        for (String line : input) {
            line= line.replaceAll("[^\\d.]", "");
            sum += Integer.parseInt(line.substring(0,1))*10
                    +Integer.parseInt(line.substring(line.length()-1));
        }

        return sum;
    }


    @Override
    public Object solveB(List<String> input) {
        int sum=0;

        for (String line : input) {
            int lowestIndex=Integer.MAX_VALUE;
            int lowestValue=0;
            int highestIndex=Integer.MIN_VALUE;
            int highestValue=0;
            for (Number number : Number.values()) {
                if(line.contains(number.getName())) {
                    if (line.indexOf(number.getName()) < lowestIndex) {
                        lowestIndex = line.indexOf(number.getName());
                        lowestValue = number.getFirstDigit();
                    }
                    if (line.lastIndexOf(number.getName()) > highestIndex) {
                        highestIndex = line.lastIndexOf(number.getName());
                        highestValue = number.getLastDigit();
                    }
                }
            }
            sum+=lowestValue*10+highestValue;
        }

        return sum;
    }
}
