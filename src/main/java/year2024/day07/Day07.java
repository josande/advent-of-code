package year2024.day07;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day07());
    }

    @Override
    public Object solveA(List<String> input) {
        long result = 0;
        for (var line : input) {
            long target = Long.parseLong(line.split(": ")[0]);
            List<Long> values = Arrays.stream(line.split(": ")[1].split(" "))
                    .map(Long::parseLong).toList();
            List<Long> current = new ArrayList<>();
            for(var value: values)
                current = getResults(current, value, false, target);
            if(current.contains(target))
                result += target;
        }


        return result;
    }

    private List<Long> getResults(List<Long> current, Long value, boolean partB, Long target) {
        ArrayList<Long> results = new ArrayList<>();
        if(current.isEmpty()) {
            results.add(value);
            return results;
        }
        long result;
        for(var c : current) {
            result = c+value;
            if(result<=target)
                results.add(result);

            result = c*value;
            if(result<=target)
                results.add(result);

            if(partB) {
                result = Long.parseLong("" + c + value);
                if(result<=(target))
                    results.add(result);
            }
        }
        return results;
    }

    @Override
    public Object solveB(List<String> input) {
        long result = 0;
        for (var line : input) {
            long target = Long.parseLong(line.split(": ")[0]);
            List<Long> values = Arrays.stream(line.split(": ")[1].split(" "))
                    .map(Long::parseLong).toList();

            List<Long> current = new ArrayList<>();
            for(var value: values)
                current = getResults(current, value, true, target);
            if(current.contains(target))
                result += target;
        }
        return result;
    }
}
