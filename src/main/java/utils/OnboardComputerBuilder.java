package utils;

import java.util.ArrayList;
import java.util.List;

public class OnboardComputerBuilder {
    private int position;
    private int accumulator;
    private List<OnboardComputer.Operation> operations;

    public OnboardComputerBuilder setPosition(int position) {
        this.position = position;
        return this;
    }

    public OnboardComputerBuilder setAccumulator(int accumulator) {
        this.accumulator = accumulator;
        return this;
    }

    public OnboardComputerBuilder setOperations(List<OnboardComputer.Operation> operations) {
        this.operations = operations;
        return this;
    }

    public OnboardComputerBuilder setInstructions(List<String> instructions) {
        List<OnboardComputer.Operation> asOperations=new ArrayList<>();
        for (String s : instructions) {
            asOperations.add(new OnboardComputer.Operation(operationFromString(s.split(" ")[0]),
                    Integer.parseInt(s.split(" ")[1])));
        }
        this.operations = asOperations;
        return this;
    }
    private static OnboardComputer.Instruction operationFromString(String name) {
        for(OnboardComputer.Instruction instruction : OnboardComputer.Instruction.values()) {
            if(instruction.getName().equals(name))
                return instruction;
        }
        throw new RuntimeException("No such operation! '"+name+"'");
    }

    public OnboardComputer create() {
        return new OnboardComputer(position, accumulator, operations);
    }
}