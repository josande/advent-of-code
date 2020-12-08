package utils;

import year2020.day08.Day08;

import java.util.ArrayList;
import java.util.List;

public class OnboardComputer {

    private int position;
    private int accumulator;
    private List<Operation> operations;

    public OnboardComputer(List<String> inputs) {
        position=0;
        accumulator=0;
        operations = asOperations(inputs);
    }

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
    class Operation {
        final Instruction instruction;
        final int value;
        Operation(Instruction instruction, int value ){
            this.instruction=instruction;
            this.value=value;
        }
    }
    List<Operation> asOperations(List<String> strings) {
        List<Operation> operations=new ArrayList<>();
        for (String s : strings) {
            operations.add(new Operation(Instruction.fromString(s.split(" ")[0]),
                    Integer.parseInt(s.split(" ")[1])));
        }
        return operations;
    }
}
