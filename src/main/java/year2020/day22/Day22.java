package year2020.day22;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day22 {


    static Object solveA(List<String> values) {
        LinkedList<Integer> deckA = new LinkedList<>();
        LinkedList<Integer> deckB = new LinkedList<>();
        boolean firstPart=true;
        for(int i =1; i<values.size(); i++) {
            if (values.get(i).isEmpty()) continue;
            if(values.get(i).contains("Player 2:")) { firstPart=false; continue;}
            if(firstPart)
                deckA.addFirst(Integer.parseInt(values.get(i)));
            else
                deckB.addFirst(Integer.parseInt(values.get(i)));
        }
        while (0 < deckA.size() && 0 < deckB.size()) {
            if(deckA.getLast() > deckB.getLast()) {
                deckA.addFirst(deckA.getLast());
                deckA.addFirst(deckB.getLast());
            }
            if(deckA.getLast() < deckB.getLast()) {
                deckB.addFirst(deckB.getLast());
                deckB.addFirst(deckA.getLast());
            }
            deckA.removeLast();
            deckB.removeLast();
        }
        long result = 0;
        for(int i = deckA.size()-1; i>=0; i--) {
            result+= (long) deckA.get(i) *(i+1);
        }
        for(int i = deckB.size()-1; i>=0; i--) {
            result+= (long) deckB.get(i) *(i+1);
        }
        return result;
    }


    static int solveB(List<String> values) {

        LinkedList<Integer> deckA = new LinkedList<>();
        LinkedList<Integer> deckB = new LinkedList<>();


        boolean firstPart=true;
        for(int i =1; i<values.size(); i++) {
            if (values.get(i).isEmpty()) continue;
            if(values.get(i).contains("Player 2:")) { firstPart=false; continue;}
            if(firstPart)
                deckA.addFirst(Integer.parseInt(values.get(i)));
            else
                deckB.addFirst(Integer.parseInt(values.get(i)));
        }

        playRecursiveCombat(deckA, deckB);

        int result = 0;
        for(int i = deckA.size()-1; i>=0; i--) {
            result+=deckA.get(i)*(i+1);
        }
        for(int i = deckB.size()-1; i>=0; i--) {
            result+=deckB.get(i)*(i+1);
        }
        return result;

    }
    private static boolean playRecursiveCombat(LinkedList<Integer> deckA, LinkedList<Integer> deckB) {
        HashSet<List<Integer>> playedGames = new HashSet<>();
        while ( 0 < deckA.size() &&
                0 < deckB.size() ) {
            if(!playedGames.add(new ArrayList<>(deckA))){
                return true;
            }
            boolean wonByA;
            if (deckA.size() <= deckA.getLast() ||
                deckB.size() <= deckB.getLast()) {
                wonByA = deckA.getLast() > deckB.getLast();
            } else {
                wonByA = playRecursiveCombat(new LinkedList<>(deckA.subList(deckA.size() - deckA.getLast()-1 , deckA.size()-1)),
                                             new LinkedList<>(deckB.subList(deckB.size() - deckB.getLast()-1 , deckB.size()-1)));
            }
            if (wonByA) {
                deckA.addFirst(deckA.getLast());
                deckA.addFirst(deckB.getLast());
            } else {
                deckB.addFirst(deckB.getLast());
                deckB.addFirst(deckA.getLast());
            }
            deckA.removeLast();
            deckB.removeLast();
        }
        return deckB.isEmpty();
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //31455
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //32528
    }
}
