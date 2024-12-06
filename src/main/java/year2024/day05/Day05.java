package year2024.day05;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day05 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day05());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<String, ArrayList<String>> rules = new HashMap<>();
        int score=0;
        for(var line : input) {
            if(line.contains("|")) {
                String[] parts = line.split("\\|");
                ArrayList<String> updated = rules.getOrDefault(parts[0], new ArrayList<>());
                updated.add(parts[1]);
                rules.put(parts[0], updated);
            }
            if(line.contains(",")) {
                String[] pages = line.split(",");
                if (verify(pages, rules)) {
                   score += Integer.parseInt(pages[pages.length/2]);
                }
            }
        }

        return score;
    }
    private boolean verify(String[] pages, HashMap<String, ArrayList<String>> rules) {
        ArrayList<String> pagesBefore = new ArrayList<>();
        for(String page : pages) {
            ArrayList<String> notAfter = new ArrayList<>(rules.getOrDefault(page, new ArrayList<>()));
            notAfter.retainAll(pagesBefore);
            if(!notAfter.isEmpty())
                return false;
            pagesBefore.add(page);
        }
        return true;
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<String, ArrayList<String>> rules = new HashMap<>();
        int score=0;
        for(var line : input) {
            if(line.contains("|")) {
                String[] parts = line.split("\\|");
                ArrayList<String> updated = rules.getOrDefault(parts[0], new ArrayList<>());
                updated.add(parts[1]);
                rules.put(parts[0], updated);
            }
            if(line.contains(",")) {
                String[] pages = line.split(",");
                if (!verify(pages, rules)) {
                    score += rearrangedScore(pages, rules);
                }
            }
        }
        return score;
    }

    private int rearrangedScore(String[] pages, HashMap<String, ArrayList<String>> rules) {
        LinkedList<String> pagesRemaining = new LinkedList<>(Arrays.asList(pages));
        ArrayList<String> ordered = new ArrayList<>();
        while(!pagesRemaining.isEmpty()) {
            String next = findBestCandidate(pagesRemaining, rules);
            pagesRemaining.remove(next);
            ordered.add(next);
        }
        return Integer.parseInt(ordered.get(ordered.size()/2));
    }
    private String findBestCandidate(LinkedList<String> pagesRemaining, HashMap<String, ArrayList<String>> rules) {
        for(var candidate : pagesRemaining) {
            boolean isValid=true;
            for(var other : pagesRemaining) {
                if (!isValid || candidate.equals(other)) continue;
                if(rules.getOrDefault(other, new ArrayList<>()).contains(candidate))
                    isValid=false;
            }
            if(isValid)
                return candidate;
        }
        throw new IllegalStateException("No candidate found");
    }
}
