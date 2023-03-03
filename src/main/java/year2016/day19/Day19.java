package year2016.day19;

import lombok.Data;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day19 {

    @Data
    static class Elf {
        int id;
        Elf left;
        Elf right;

        public Elf(int id) {
            this.id=id;
        }

        public void remove() {
            right.setLeft(left);
            left.setRight(right);
        }

    }
    static Object solveA(List<Integer> values) {
        Elf startElf = new Elf(1);
        Elf lastElf = startElf;
        Elf currentElf = null;
        for (int id = 2; id <= values.get(0); id++) {
            currentElf = new Elf(id);
            lastElf.setLeft(currentElf);
            currentElf.setRight(lastElf);
            lastElf = currentElf;
        }
        startElf.setRight(currentElf);
        if(currentElf!=null)
            currentElf.setLeft(startElf);


        currentElf = startElf;
        while(currentElf.getLeft()!=currentElf) {
            currentElf.getLeft().remove();
            currentElf=currentElf.getLeft();
        }
        return currentElf.getId();
    }
    static Object solveB(List<Integer> values) {
        Elf startElf = new Elf(1);
        Elf lastElf = startElf;
        Elf currentElf = null;

        for (int id = 2; id <= values.get(0); id++) {
            currentElf = new Elf(id);
            lastElf.setLeft(currentElf);
            currentElf.setRight(lastElf);
            lastElf = currentElf;
        }
        startElf.setRight(currentElf);
        if(currentElf!=null)
            currentElf.setLeft(startElf);
        currentElf = startElf;

        Elf nextToBeRemoved=startElf;
        for(int i = 0; i< values.get(0)/2; i++) {
            nextToBeRemoved=nextToBeRemoved.getLeft();
        }
        int skipCounter=values.get(0)%2;
        while(currentElf.getLeft()!=currentElf) {
            Elf toRemove = nextToBeRemoved;
            skipCounter++;
            nextToBeRemoved=nextToBeRemoved.getLeft();
            if(skipCounter%2==0) {
                nextToBeRemoved=nextToBeRemoved.getLeft();
            }
            toRemove.remove();
            currentElf=currentElf.getLeft();
        }
        return currentElf.getId();
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFileAsIntegers("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 1816277
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 1410967
    }

}
