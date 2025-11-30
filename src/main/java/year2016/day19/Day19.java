package year2016.day19;

import lombok.Data;
import utils.AdventOfCode;
import utils.FileHelper;
import utils.Reporter;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day19 implements AdventOfCode {

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

    @Override
    public Object solveA(List<String> values) {
        Elf startElf = new Elf(1);
        Elf lastElf = startElf;
        Elf currentElf = null;
        for (int id = 2; id <= Integer.parseInt(values.getFirst()); id++) {
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

    @Override
    public Object solveB(List<String> values) {
        Elf startElf = new Elf(1);
        Elf lastElf = startElf;
        Elf currentElf = null;

        for (int id = 2; id <= Integer.parseInt(values.getFirst()); id++) {
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
        for(int i = 0; i< Integer.parseInt(values.getFirst())/2; i++) {
            nextToBeRemoved=nextToBeRemoved.getLeft();
        }
        int skipCounter=Integer.parseInt(values.getFirst())%2;
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
    public static void main(){
        Reporter.report(new Day19());
    }

}
