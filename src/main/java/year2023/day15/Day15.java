package year2023.day15;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Day15 implements AdventOfCode {
    public static void main(){
         Reporter.report(new Day15());
    }

    @Override
    public Object solveA(List<String> input) {
        String fullInput = String.join("", input);
        int result =0;
        for(String part : fullInput.split(",")) {
            int currentValue=0;
            for (char c : part.toCharArray()) {
                currentValue = asHash(c, currentValue);
            }
            result+=currentValue;
        }
        return result;
    }

    private int asHash(char c, int currentValue) {
        int result = currentValue+c;
        result*=17;
        return result%256;
    }

    @Override
    public Object solveB(List<String> input) {
        String fullInput = String.join("", input);
        HashMap<Integer, LinkedList<Pair<String, Integer>>> boxes = new HashMap<>();
        for(String part : fullInput.split(",")) {
            String label = part.split("[=-]")[0];
            int boxId=0;
            for (char c : label.toCharArray()) {
                boxId = asHash(c, boxId);
            }

            if(part.contains("=")) {
                int value = Integer.parseInt(part.split("=")[1]);
                LinkedList<Pair<String, Integer>> content = boxes.getOrDefault(boxId, new LinkedList<>());
                boolean found = false;
                for(int i=0; i<content.size(); i++) {
                    if(content.get(i).getKey().equals(label)) {
                        content.set(i, new ImmutablePair<>(label, value));
                        found=true;
                        break;
                    }
                }
                if(!found) {
                    content.add(new ImmutablePair<>(label, value));
                }
                boxes.put(boxId, content);
            } else {
                LinkedList<Pair<String, Integer>> content = boxes.getOrDefault(boxId, new LinkedList<>());
                for(int i=0; i<content.size(); i++) {
                    if(content.get(i).getKey().equals(label)) {
                        content.remove(i);
                        break;
                    }
                }
            }
        }
        int result =0;

        for(int boxNumber=0; boxNumber<256; boxNumber++) {
            for (int slot=0; slot<boxes.getOrDefault(boxNumber, new LinkedList<>()).size(); slot++) {
               result+=(boxNumber+1)*(slot+1)*boxes.get(boxNumber).get(slot).getValue();
            }
        }

        return result;
    }
}
