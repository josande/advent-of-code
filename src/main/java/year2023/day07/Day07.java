package year2023.day07;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day07 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day07());
    }

    record PokerHand(String cards, int value, boolean useJokers) implements Comparable<PokerHand> {
            private int getHand() {

                HashMap<Character, Integer> map = new HashMap<>();
                for (char c : cards.toCharArray()) {
                    map.put(c, 1 + map.getOrDefault(c, 0));
                }
                if (useJokers && map.containsKey('J')) {
                    int jokers = map.get('J');
                    map.remove('J');

                    var quads = map.entrySet().stream().filter(e -> e.getValue() == 4).toList();
                    var trips = map.entrySet().stream().filter(e -> e.getValue() == 3).toList();
                    var pairs = map.entrySet().stream().filter(e -> e.getValue() == 2).toList();
                    var singles = map.entrySet().stream().filter(e -> e.getValue() == 1).toList();

                    if (!quads.isEmpty()) return 5 + jokers;
                    else if (!trips.isEmpty()) return 4 + jokers;
                    else if (!pairs.isEmpty()) return Math.min(6, pairs.size() + 2 * jokers);
                    else if (!singles.isEmpty()) return Math.min(6, jokers * 2 - 1);

                    return 6;
                }


                if (map.containsValue(5)) return 6;
                if (map.containsValue(4)) return 5;
                if (map.containsValue(3)) {
                    if (map.containsValue(2)) return 4;
                    return 3;
                }
                if (map.values().stream().filter(v -> v == 2).count() == 2) return 2;
                if (map.containsValue(2)) return 1;
                return 0;
            }

            @Override
            public int compareTo(PokerHand o) {
                if (this.getHand() != o.getHand())
                    return (this.getHand() - o.getHand());
                String s1 = this.cards().replaceAll("A", "E").replaceAll("K", "D").replaceAll("Q", "C").replaceAll("J", "B").replaceAll("T", "A");
                String s2 = o.cards().replaceAll("A", "E").replaceAll("K", "D").replaceAll("Q", "C").replaceAll("J", "B").replaceAll("T", "A");
                if (useJokers) {
                    s1 = s1.replaceAll("B", "1");
                    s2 = s2.replaceAll("B", "1");
                }

                return s1.compareTo(s2);
            }
        }
    @Override
    public Object solveA(List<String> input) {
        ArrayList<PokerHand> hands = new ArrayList<>();
        for (String s : input) {
            String[] parts = s.split(" ");
             hands.add(new PokerHand(parts[0], Integer.parseInt(parts[1]), false));
        }

        Collections.sort(hands);


        long result = 0L;
        int counter=1;
        for(PokerHand ph : hands) {
            result+= (long) ph.value() *counter;
            counter++;
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        ArrayList<PokerHand> hands = new ArrayList<>();
        for (String s : input) {
            String[] parts = s.split(" ");
            hands.add(new PokerHand(parts[0], Integer.parseInt(parts[1]), true));
        }

        Collections.sort(hands);


        long result = 0L;
        int counter=1;
        for(PokerHand ph : hands) {
            result+= (long) ph.value() *counter;
            counter++;
        }
        return result;
    }
}
