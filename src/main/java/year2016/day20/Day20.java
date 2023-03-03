package year2016.day20;

import org.apache.commons.lang3.tuple.MutablePair;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day20 {

    static Object solveA(List<String> values) {

        return getUnblockedAddresses(values, 4294967295L).get(0);
    }
    static ArrayList<Long> getUnblockedAddresses(List<String> values, long maxValue) {
        List<MutablePair<Long, Long>> ranges = new ArrayList<>();
        ArrayList<MutablePair<Long, Long>> asLongs = new ArrayList<>();
        for (String value : values) {
            long start = Long.parseLong(value.split("-")[0]);
            long end = Long.parseLong(value.split("-")[1]);
            asLongs.add(new MutablePair<>(start, end));

        }
        Collections.sort(asLongs);

        for(MutablePair<Long, Long> range : asLongs) {
            boolean matched=false;
            long start = range.getLeft();
            long end = range.getRight();
            for (MutablePair<Long, Long> p : ranges) {
                if (p.getLeft() <= start && p.getRight() >= start) { // start in known range
                    p.setRight(Math.max(p.getRight(), end));
                    matched=true;
                } else if (p.getLeft() <= end && p.getRight() >= end) { // end in known range
                    p.setLeft(Math.min(p.getLeft(), start));
                    matched=true;
                }
                if (p.getRight() == start-1) { //Directly after
                    p.setRight(end);
                    matched=true;
                }

            }
            if (!matched) {
                ranges.add(new MutablePair<>(start, end));
            }
        }
        ranges.add(new MutablePair<>(maxValue+1, maxValue+1));
        ranges.add(new MutablePair<>(-1L, -1L));

        Collections.sort(ranges);
        ArrayList<Long> unblocked = new ArrayList<>();
        for (int i=1; i<ranges.size(); i++) {
            for (long l = ranges.get(i-1).getRight() +1; l< ranges.get(i).getLeft(); l++ ) {
                unblocked.add(l);
            }
        }

        return unblocked;
    }
    static Object solveB(List<String> values) {
        return getUnblockedAddresses(values, 4294967295L).size();
    }


        public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 22887907
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 109
    }
}
