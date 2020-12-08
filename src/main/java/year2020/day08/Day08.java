package year2020.day08;

import utils.FileHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day08 {
    static int position=0;
    enum Instruction {
        NOP("nop"),
        ACC("acc"),
        JMP("jmp");

        private final String name;
        Instruction(String name) {
            this.name=name;
        }
        static Instruction fromString(String name) {
            for(Instruction instruction : values()) {
                if(instruction.name.equals(name))
                    return instruction;
            }
            throw new RuntimeException("No such operation! '"+name+"'");
        }
    }
    static class Operation {
        final Instruction instruction;
        final int value;
        Operation(Instruction instruction, int value ){
            this.instruction=instruction;
            this.value=value;
        }
    }
    static List<Operation> asOperations(List<String> strings) {
        List<Operation> operations=new ArrayList<>();
        for (String s : strings) {
            operations.add(new Operation(Instruction.fromString(s.split(" ")[0]),
                                         Integer.parseInt(s.split(" ")[1])));
        }
        return operations;
    }
    static int doOperation(Operation op, int acc) {
        switch (op.instruction) {
            case  NOP ->   position++;
            case  ACC -> { position++; acc+=op.value; }
            case  JMP ->   position+=op.value;
        }
        return acc;
    }

    static int solveA(List<String> values) {
        position=0;
        List<Operation> operations = asOperations(values);
        HashSet<Integer> doneOperations = new HashSet<>();
        int accumulator=0;
        while(!doneOperations.contains(position)) {
            doneOperations.add(position);
            accumulator=doOperation(operations.get(position), accumulator);
        }

        return accumulator;
    }

    static int solveB(List<String> values) {
        List<Operation> operations = asOperations(values);
        for (int i= 0; i< operations.size(); i++) {
            List<Operation> newOperations = new ArrayList<>(operations);
            if (operations.get(i).instruction == Instruction.JMP) {
                newOperations.set(i, new Operation(Instruction.NOP, operations.get(i).value));
            } else if (operations.get(i).instruction == Instruction.NOP) {
                newOperations.set(i, new Operation(Instruction.JMP, operations.get(i).value));
            } else {
                continue;
            }
            Integer acc = runOperations(newOperations);
            if(acc != null) {
                return acc;
            }
        }
        return -99;
    }
    static Integer runOperations(List<Operation> operations) {
        position=0;
        int accumulator=0;
        HashSet<Integer> doneOperations = new HashSet<>();
        while(true) {
            if(position == operations.size())
                return accumulator;
            doneOperations.add(position);
            accumulator = doOperation(operations.get(position), accumulator);
            if (doneOperations.contains(position) )
                return null;
        }
    }

    public static void main(String[] args){
        var day = "Day08";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = System.currentTimeMillis()-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //1137
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //1125
    }
}
