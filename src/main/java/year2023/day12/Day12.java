
package year2023.day12;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;
import java.util.stream.Collectors;

public class Day12 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day12());
    }

    static HashMap<String, HashMap<String, Long>> cache = new HashMap<>();

    record State2(int segment, int patternPosition, long combinations) implements Comparable<State2> {

        @Override
        public int compareTo(State2 o) {
            return o.segment - this.segment;
        }
    }

    @Override
    public Object solveA(List<String> input) {
        long result=0L;

        for (String line : input) {
            String string = line.split(" ")[0];
            String patternString = line.split(" ")[1];
            string = string.replaceAll("\\.+", ".");
            if(string.startsWith(".")) string = string.replaceFirst(".", "");

            List<Integer> pattern = Arrays.stream(patternString.split(",")).map(Integer::parseInt).toList();

            HashMap<String, HashMap<Pair<Integer, Integer>, Long>> innerCache = new HashMap<>();
            for(String s : string.split("\\.+")) {
                cacheAllStates(s, pattern, innerCache);
            }

            LinkedList<State2> queue = new LinkedList<>();
            queue.add(new State2(0, 0, 1L));

            long partResult =0L;

            while(!queue.isEmpty()) {
                State2 current = queue.poll();
                if(current.combinations()==0L)
                    continue;

                if(current.segment == string.split("\\.").length) {
                    if(current.patternPosition() == pattern.size()) {
                        partResult += current.combinations;
                    }

                } else {
                    var temp = getNextStates(current, string, innerCache);

                    for (var t: temp) {
                        boolean found = false;
                        for(int i=0; i< queue.size(); i++) {
                            if (t.segment == queue.get(i).segment && t.patternPosition == queue.get(i).patternPosition) {
                                queue.set(i, new State2(t.segment, t.patternPosition, t.combinations + queue.get(i).combinations));
                                found=true;
                                break;
                            }
                        }
                        if(!found)
                            queue.add(t);
                    }
                }
            }
            result+=partResult;
        }
        return result;
    }
    HashSet<State2> getNextStates(State2 state, String string, HashMap<String, HashMap<Pair<Integer, Integer>, Long>> innerCache) {

        HashMap<Pair<Integer, Integer>, Long> foo = innerCache.get(string.split("\\.")[state.segment]);
        HashSet<State2> nextStates = new HashSet<>();

        for(Map.Entry<Pair<Integer, Integer>, Long> v : foo.entrySet()) {
            if(v.getKey().getKey() == state.patternPosition && v.getValue()>0) {
                nextStates.add(new State2(state.segment+1, v.getKey().getValue(), state.combinations * v.getValue()));
            }
        }

        return nextStates;
    }
    private String asString(List<Integer> pattern){
        return pattern.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    long getCombinations(String remaining, List<Integer> pattern) {
        if(cache.getOrDefault(remaining, new HashMap<>()).containsKey(asString(pattern)))
            return cache.get(remaining).get(asString(pattern));

        var combinations = 0L;
        if(pattern.size() == 0) {
            HashMap<String, Long> previous = cache.getOrDefault(remaining, new HashMap<>());
            combinations = remaining.contains("#") ? 0L : 1L;
            previous.put(asString(pattern), combinations);
            cache.put(remaining, previous);
            return combinations;
        }

        if(pattern.size() == 1) {
            int length = pattern.get(0);
            for(int start=0; start<remaining.length()-length+1; start++) {
                char[] temp=remaining.toCharArray();
                for(int l = start; l < start+length; l++)
                    if(temp[l] == '?')
                        temp[l]='#';

                int numberOfHash =0;
                for(char c : temp) numberOfHash+= c=='#' ? 1 : 0;

                if (numberOfHash==length)
                    combinations++;
            }
            HashMap<String, Long> previous = cache.getOrDefault(remaining, new HashMap<>());
            previous.put(asString(pattern), combinations);
            cache.put(remaining, previous);
            return combinations;
        }

        LinkedList<Integer> patternLeft = new LinkedList<>(pattern);
        patternLeft.remove(0);
        for(int start=0; start<remaining.length()-pattern.get(0); start++) {
            if (remaining.length() == start +pattern.get(0)+1) break;
            if(start>0 && remaining.charAt(start-1) == '#') break;

            if (remaining.charAt(start+pattern.get(0))!='#') {
                combinations+=getCombinations(remaining.substring(start +pattern.get(0)+1), patternLeft);
            }
        }
        HashMap<String, Long> previous = cache.getOrDefault(remaining, new HashMap<>());
        previous.put(asString(pattern), combinations);
        cache.put(remaining, previous);
        return combinations;
    }

    private void cacheAllStates(String part, List<Integer> pattern, HashMap<String, HashMap<Pair<Integer, Integer>, Long>> cache) {
        if(cache.containsKey(part)) return;

        HashMap<Pair<Integer, Integer>, Long> inner = new HashMap<>();

        for( int start=0; start <= pattern.size(); start++ ) {
            for(int end=start; end <= pattern.size(); end++) {
                long combinations = getCombinations(part, pattern.subList(start, end));
                if(combinations>0)
                    inner.put(new ImmutablePair<>(start, end), combinations);
            }
        }
        cache.put(part, inner);
    }

    @Override
    public Object solveB(List<String> input) {
        long result=0L;

        for (String line : input) {
            String string = line.split(" ")[0];
            string+="?"+string+"?"+string+"?"+string+"?"+string;
            String patternString = line.split(" ")[1];
            patternString+=","+patternString+","+patternString+","+patternString+","+patternString;

            string = string.replaceAll("\\.+", ".");
            if(string.startsWith(".")) string = string.replaceFirst(".", "");

            List<Integer> pattern = Arrays.stream(patternString.split(",")).map(Integer::parseInt).toList();

            HashMap<String, HashMap<Pair<Integer, Integer>, Long>> innerCache = new HashMap<>();
            for(String s : string.split("\\.+")) {
                cacheAllStates(s, pattern, innerCache);
            }

            LinkedList<State2> queue = new LinkedList<>();
            queue.add(new State2(0, 0, 1L));

            long partResult =0L;

            while(!queue.isEmpty()) {
                State2 current = queue.poll();
                if(current.combinations()==0L)
                    continue;

                if(current.segment == string.split("\\.").length) {
                    if(current.patternPosition() == pattern.size()) {
                        partResult += current.combinations;
                    }

                } else {
                    var temp = getNextStates(current, string, innerCache);

                    for (var t: temp) {
                        boolean found = false;
                        for(int i=0; i< queue.size(); i++) {
                            if (t.segment == queue.get(i).segment && t.patternPosition == queue.get(i).patternPosition) {
                                queue.set(i, new State2(t.segment, t.patternPosition, t.combinations + queue.get(i).combinations));
                                found=true;
                                break;
                            }
                        }
                        if(!found)
                            queue.add(t);
                    }
                }
            }
            result+=partResult;
        }
        return result;
    }
}
