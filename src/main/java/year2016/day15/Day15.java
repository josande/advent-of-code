package year2016.day15;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class Day15 {

    @Data
    @AllArgsConstructor
    static class Disc {
        int id;
        int size;
        int startPosition;

        boolean isWall(int time) {
            return  ((startPosition+id+time)%size!=0);
        }
    }
    static Object solveA(List<String> values) {
        ArrayList<Disc> discs = new ArrayList<>();
        int discId=0;
        for(String row : values) {
            discId++;
            //Disc #6 has 3 positions; at time=0, it is at position 1.
            String[] words = row.split(" ");
            int id =  discId;
            int size = Integer.parseInt(words[3]);
            int startPos = Integer.parseInt(words[11].replaceAll("\\.", ""));
            discs.add(new Disc(id, size, startPos));
        }
        for (int time=0; ; time++) {
            boolean fitsAll=true;
            for (Disc disc : discs) {
                if (disc.isWall(time)) {
                    fitsAll=false;
                }
            }
            if(fitsAll)
                return time;
        }

    }
    static Object solveB(List<String> values) {
        ArrayList<Disc> discs = new ArrayList<>();
        int discId=0;
        for(String row : values) {
            discId++;
            String[] words = row.split(" ");
            int size = Integer.parseInt(words[3]);
            int startPos = Integer.parseInt(words[11].replaceAll("\\.", ""));
            discs.add(new Disc(discId, size, startPos));
        }
        discId++;
        discs.add(new Disc(discId, 11, 0 ));
        for (int time=0; ; time++) {
            boolean fitsAll=true;
            for (Disc disc : discs) {
                if (disc.isWall(time)) {
                    fitsAll=false;
                }
            }
            if(fitsAll)
                return time;
        }
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 203660
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 2408135
    }
}
