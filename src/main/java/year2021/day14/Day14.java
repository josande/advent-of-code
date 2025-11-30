package year2021.day14;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14 {

    static Object solveA(List<String> values) {
        String startString = values.get(0);
        HashMap<String, String> transfortmations = new HashMap<>();
        for(var val : values) {
            if(!val.contains("->"))
                continue;
            String from = val.split(" -> ")[0];
            String to = val.split(" -> ")[1]+from.charAt(1);
            transfortmations.put(from, to);
        }

        List<String> startStrings = new ArrayList<>();
        for(int i = 0; i < startString.length()-1; i++) {
            startStrings.add(""+startString.charAt(i)+startString.charAt(i+1));
        }
        Map<Character, Long> totalCharCount=new HashMap<>();

        for(String str : startStrings ) {
            for (int step = 0; step < 10; step++) {
                StringBuilder sb = new StringBuilder();
                sb.append(str.charAt(0));
                for (int i = 0; i < str.length() - 1; i++) {
                    String from = "" + str.charAt(i) + str.charAt(i + 1);
                    sb.append(transfortmations.get(from));
                }
                str = sb.toString();
            }
            final String temp = str;
            Map<Character, Long> charCount = IntStream.range(0, temp.length())
                    .mapToObj(i -> temp.charAt(i))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            charCount.put(str.charAt(0), charCount.get(str.charAt(0))-1);

            for (Map.Entry<Character, Long> e : charCount.entrySet()) {
                totalCharCount.put(e.getKey(), e.getValue() + totalCharCount.getOrDefault(e.getKey(), 0L));
            }
        }
        totalCharCount.put(startString.charAt(0), totalCharCount.get(startString.charAt(0))-1);

        ArrayList<Long> occurences = new ArrayList<>(totalCharCount.values());
        Collections.sort(occurences);
        return occurences.get(occurences.size()-1) - occurences.get(0);
    }
    static Object solveB(List<String> values) {
        String startString = values.get(0);
        HashMap<String, String> transfortmations = new HashMap<>();
        for(var val : values) {
            if(!val.contains("->"))
                continue;
            String from = val.split(" -> ")[0];
            String to = from.charAt(0) + val.split(" -> ")[1]+from.charAt(1);
            transfortmations.put(from, to);
        }

        List<String> startStrings = new ArrayList<>();
        HashMap<String, Long> occurrences = new HashMap<>();
        for(int i = 0; i < startString.length()-1; i++) {
            String from =""+startString.charAt(i)+startString.charAt(i+1);
            startStrings.add(from);
            occurrences.put(from, 1+occurrences.getOrDefault(from, 0L));
        }

        for(int step = 0; step<40; step++) {
            HashMap<String, Long> newOccurences = new HashMap<>();

            for(String str : occurrences.keySet()) {
                String to = transfortmations.get(str);
                String to1 = to.substring(0,2);
                String to2 = to.substring(1,3);
                newOccurences.merge(to1, occurrences.get(str), Long::sum);
                newOccurences.merge(to2, occurrences.get(str), Long::sum);
            }
            occurrences = newOccurences;
        }
        HashMap<Character, Long> frequency = new HashMap<>();
        for(String str : occurrences.keySet()) {
            frequency.put(str.charAt(1), frequency.getOrDefault(str.charAt(1), 0L)+occurrences.get(str));
        }
        frequency.merge(startString.charAt(0),  1L, Long::sum);

        ArrayList<Long> numbers = new ArrayList<>(frequency.values());
        Collections.sort(numbers);

        return numbers.get(numbers.size()-1) - numbers.get(0);
    }


    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
