package year2020.day07;

import utils.FileHelper;

import java.util.ArrayList;
import java.util.List;

public class Day07 {


    static int solveA(List<String> values) {

        List<String> lookingFor = new ArrayList<>();
        lookingFor.add("shiny gold");
        //light red bags contain 1 bright white bag, 2 muted yellow bags.
        //light red
        //s contain 1 bright white
        // , 2 muted yellow
        // .

        boolean foundNew = true;
        while (foundNew) {
            foundNew = false;

            for (String rule : values) {

                String[] parts = rule.split(" bags contain");
                String currentBag = parts[0];
                String content = parts[1];
                if(lookingFor.contains(currentBag.trim()))
                    continue;

                for(int i = 0; i<lookingFor.size(); i++) {
                    String goodBag = lookingFor.get(i);
                    if (content.contains(goodBag) && !lookingFor.contains(currentBag.trim())) {
                        lookingFor.add(currentBag.trim());
                        foundNew = true;
                    }
                }


            }
        }
        return lookingFor.size()-1;
    }

    static int solveB(List<String> values) {
        return getBagContent("shiny gold", values) -1;


    }
    static int getBagContent(String bag, List<String> rules) {

        for (var rule : rules) {
            if(rule.startsWith(bag)) {
                if(rule.contains("no other"))
                    return 1;


                if(rule.contains(", ")) {
                    String[] parts = rule.split(", ");

                    String newBag1 = parts[0].split("bags contain ")[1].trim();
                    int count = Integer.parseInt(newBag1.split(" ")[0]);
                    newBag1=newBag1.substring(2).split(" bag")[0];
                    int part1 = count* getBagContent(newBag1, rules);

                    String newBag2 = parts[1].trim();
                    int count2 = Integer.parseInt(newBag2.split(" ")[0]);
                    newBag2=newBag2.substring(2).split(" bag")[0];
                    int part2 = count2* getBagContent(newBag2, rules);

                    int part3=0;

                    if(parts.length>2) {

                        String newBag3 = parts[2].trim();
                        int count3 = Integer.parseInt(newBag3.split(" ")[0]);
                        newBag3 = newBag3.substring(2).split(" bag")[0];
                        part3 = count3* getBagContent(newBag3, rules);

                    }


                    return 1 + part1+part2+part3;
                } else {
                    String newBag = rule.split("bags contain ")[1].trim();
                    int count = Integer.parseInt(newBag.split(" ")[0]);
                    newBag=newBag.substring(2).split(" bag")[0];

                    return 1 + count* getBagContent(newBag, rules);
                }
            }
        }
        throw new RuntimeException("Bag "+bag+" not found!");
    }

    public static void main(String[] args){
        var day = "Day07";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = System.currentTimeMillis()-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //252
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //23381 to low
    }
}
