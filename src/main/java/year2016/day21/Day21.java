package year2016.day21;

import org.apache.commons.lang3.StringUtils;
import utils.AdventOfCode;
import utils.FileHelper;
import utils.Reporter;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day21 implements AdventOfCode {

    @Override
    public Object solveA(List<String> values) {
        return solveA("abcdefgh", values);

    }
    public String solveA(String input, List<String> values) {
        String password=input;
        for(String operation : values) {
            password=scramble(password, operation);
        }

        return password;
    }

    static String scramble(String input, String operation) {
        String[] words = operation.toLowerCase().split(" ");
        if(words[0].equals("swap") && words[1].equals("position")) {
            int val1 = Integer.parseInt(words[2]);
            int val2 = Integer.parseInt(words[5]);
            int pos1 = Math.min(val1, val2);
            int pos2 = Math.max(val1, val2);
            return input.substring(0, pos1) +
                    input.charAt(pos2) +
                    input.substring(pos1+1, pos2) +
                    input.charAt(pos1) +
                    input.substring(pos2+1);

        }
        if(words[0].equals("swap") && words[1].equals("letter")) {
            return input.replaceAll(words[2], "^").replaceAll(words[5], words[2]).replaceAll("\\^", words[5]);
        }

        if(words[0].equals("reverse") ) {
            int val1 = Integer.parseInt(words[2]);
            int val2 = Integer.parseInt(words[4]);
            int pos1 = Math.min(val1, val2);
            int pos2 = Math.max(val1, val2);

            String before = input.substring(0, pos1);
            String reverse = StringUtils.reverse(input.substring(pos1, pos2+1));
            String after = input.length()>pos2?input.substring(pos2+1):"";

            return before+reverse+after;
        }
        if(words[0].equals("rotate") && words[1].equals("left")) {
            int rot = Integer.parseInt(operation.split(" ")[2])%input.length();

            String s1 = input.substring(0, rot);
            String s2 = input.substring(rot);
            return s2+s1;
        }
        if(words[0].equals("rotate") && words[1].equals("right")) {
            int rot = Integer.parseInt(operation.split(" ")[2])%input.length();

            String s1 = input.substring(0, input.length()-rot);
            String s2 = input.substring(input.length()-rot);
            return s2+s1;
        }
        if(words[0].equals("rotate") && words[1].equals("based")) {
            char letter = operation.split(" ")[6].charAt(0);
            int index = input.indexOf(letter);
            int rotations=(1+index+(index>=4?1:0))%input.length();

            String s1 = input.substring(0, input.length()-rotations);
            String s2 = input.substring(input.length()-rotations);
            return s2+s1;
        }
        if(words[0].equals("move")) {
            int val1 = Integer.parseInt(words[2]);
            int val2 = Integer.parseInt(words[5]);
            char c = input.charAt(val1);
            String rest = input.substring(0,val1)+ input.substring(val1+1);
            return rest.substring(0,val2)+c+(rest.length()>val2?rest.substring(val2):"");
        }

        throw new IllegalArgumentException("Unknown operation to scramble: "+operation);
    }
    static String unscramble(String input, String operation) {
        String[] words = operation.toLowerCase().split(" ");
        if(words[0].equals("swap") && words[1].equals("position")) {
            int val1 = Integer.parseInt(words[2]);
            int val2 = Integer.parseInt(words[5]);
            int pos1 = Math.min(val1, val2);
            int pos2 = Math.max(val1, val2);
            return input.substring(0, pos1) +
                    input.charAt(pos2) +
                    input.substring(pos1+1, pos2) +
                    input.charAt(pos1) +
                    input.substring(pos2+1);

        }
        if(words[0].equals("swap") && words[1].equals("letter")) {
            return input.replaceAll(words[2], "^").replaceAll(words[5], words[2]).replaceAll("\\^", words[5]);
        }

        if(words[0].equals("reverse") ) {
            int val1 = Integer.parseInt(words[2]);
            int val2 = Integer.parseInt(words[4]);
            int pos1 = Math.min(val1, val2);
            int pos2 = Math.max(val1, val2);

            String before = input.substring(0, pos1);
            String reverse = StringUtils.reverse(input.substring(pos1, pos2+1));
            String after = input.length()>pos2?input.substring(pos2+1):"";

            return before+reverse+after;
        }
        if(words[0].equals("rotate") && words[1].equals("left")) {
            int rot = Integer.parseInt(operation.split(" ")[2])%input.length();

            String s1 = input.substring(0, input.length()-rot);
            String s2 = input.substring(input.length()-rot);
            return s2+s1;
        }
        if(words[0].equals("rotate") && words[1].equals("right")) {
            int rot = Integer.parseInt(operation.split(" ")[2])%input.length();

            String s1 = input.substring(0, rot);
            String s2 = input.substring(rot);
            return s2+s1;
        }
        if(words[0].equals("rotate") && words[1].equals("based")) {

            String result = input;
            for(int i=0; i<input.length(); i++){
                String s1 = result.substring(0, input.length()-1);
                String s2 = result.substring(input.length()-1);
                result = s2+s1;
                if(input.equals(scramble(result, operation))) {
                    return result;
                }
            }
        }
        if(words[0].equals("move")) {
            int val1 = Integer.parseInt(words[2]);
            int val2 = Integer.parseInt(words[5]);
            char c = input.charAt(val2);
            String rest = input.substring(0,val2)+ input.substring(val2+1);
            return rest.substring(0,val1)+c+(rest.length()>val1?rest.substring(val1):"");
        }

        throw new IllegalArgumentException("Unknown operation to unscramble: "+operation);
    }

    @Override
    public Object solveB(List<String> values) {
        return solveB("fbgdceah", values);
    }
    public Object solveB(String input, List<String> values) {
        for(String password : getAllUniquePermutations(input)) {
            if(input.equals(solveA(password, values)))
                return password;
        }

        return "No matching password found!";
    }

    public static HashSet<String> getAllUniquePermutations(String input) {
        HashSet<String> permutations = new HashSet<>();
        var chars = input.toCharArray();

        Stack<List<Integer>> candidates = new Stack<>();
        for(int i=0; i<chars.length; i++) {
            candidates.add(List.of(i));
        }

        while(!candidates.isEmpty()) {
            List<Integer> candidate = candidates.pop();
            for(int i=0; i<chars.length; i++) {
                if (!candidate.contains(i)) {
                    List<Integer> newCandidate = new ArrayList<>(candidate);
                    newCandidate.add(i);
                    if (newCandidate.size() == input.length()) {
                        StringBuilder perm = new StringBuilder();
                        for (Integer integer : newCandidate) {
                            perm.append(chars[integer]);
                        }
                        permutations.add(perm.toString());
                    } else
                        candidates.add(newCandidate);
                }
            }
        }
        return permutations;
    }

    public static void main(){
        Reporter.report(new Day21());
    }
}
