package utils;

import java.util.List;

public class OnboardComputer {

    private int position;
    private int accumulator;
    private final List<Operation> operations;

    public OnboardComputer(int position, int accumulator, List<Operation> operations) {
        this.position = position;
        this.accumulator = accumulator;
        this.operations = operations;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(int accumulator) {
        this.accumulator = accumulator;
    }

    public List<Operation> getOperations() {
        return operations;
    }



    public enum Instruction {
        NOP("nop"),
        ACC("acc"),
        JMP("jmp");

        private final String name;
        Instruction(String name) {
            this.name=name;
        }
        public String getName() {
            return name;
        }
    }
    static public class Operation {
        final private Instruction instruction;

        public Instruction getInstruction() {
            return instruction;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        private int value;
        public Operation(Instruction instruction, int value ){
            this.instruction=instruction;
            this.value=value;
        }

    }


    public void doNextOperation() {
        Operation nextOp = operations.get(position);
        switch (nextOp.getInstruction()) {
            case  NOP ->   position++;
            case  ACC -> { position++; accumulator+=nextOp.getValue(); }
            case  JMP ->   position+=nextOp.getValue();
        }
    }
}
