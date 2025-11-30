package year2023.day04;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day04 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day04());
    }

    @Override
    public Object solveA(List<String> input) {
        int result=0;
        for(String line : input) {
            line = line.replaceAll(" {2}", " ");
            String[] parts=line.split(": ")[1].split(" \\| ");
            List<String> winningNumbers = List.of(parts[0].split(" "));
            ArrayList<String> numbers = new ArrayList<>(List.of(parts[1].split(" ")));

            numbers.retainAll(winningNumbers);
            if(!numbers.isEmpty()) {
                int res = 1;
                for (int i = 1; i < numbers.size(); i++) {
                    res *= 2;
                }
                result += res;
            }
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<Integer, Integer> winnings = new HashMap<>();
        HashMap<Integer, Integer> cards = new HashMap<>();
        int cardId = 0;
        for (String line : input) {
            cardId++;
            cards.put(cardId, 1);
            line = line.replaceAll(" {2}", " ");
            String[] parts = line.split(": ")[1].split(" \\| ");
            List<String> winningNumbers = List.of(parts[0].split(" "));
            ArrayList<String> numbers = new ArrayList<>(List.of(parts[1].split(" ")));

            numbers.retainAll(winningNumbers);
            winnings.put(cardId, numbers.size());
        }

        for (Integer card : cards.keySet()) {
            for (int j = 1; j <= winnings.get(card); j++) {
                cards.put(card + j, cards.getOrDefault(card + j, 0) + cards.get(card));
            }
        }
        return cards.values().stream().reduce(0, Integer::sum);
    }
}
