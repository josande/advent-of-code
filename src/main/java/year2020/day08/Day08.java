package year2020.day08;

import utils.FileHelper;
import utils.OnboardComputer;
import utils.OnboardComputerBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day08 {


    static int solveA(List<String> values) {
        OnboardComputer computer = new OnboardComputerBuilder().setInstructions(values).create();
        HashSet<Integer> doneOperations = new HashSet<>();
        while(!doneOperations.contains(computer.getPosition())) {
            doneOperations.add(computer.getPosition());
            computer.doNextOperation();
        }
        return computer.getAccumulator();
    }

    static int solveB(List<String> values) {
        OnboardComputer computer = new OnboardComputerBuilder().setInstructions(values).create();
        List<OnboardComputer.Operation> operations = computer.getOperations();

        for (int i= 0; i< operations.size(); i++) {
            List<OnboardComputer.Operation> newOperations = new ArrayList<>(operations);
            if (operations.get(i).getInstruction() == OnboardComputer.Instruction.JMP) {
                newOperations.set(i, new OnboardComputer.Operation(OnboardComputer.Instruction.NOP,
                                                                   operations.get(i).getValue()));
            } else if (operations.get(i).getInstruction() == OnboardComputer.Instruction.NOP) {
                newOperations.set(i, new OnboardComputer.Operation(OnboardComputer.Instruction.JMP,
                                                                   operations.get(i).getValue()));
            } else {
                continue;
            }
            OnboardComputer newComputer = new OnboardComputerBuilder().setOperations(newOperations).create();

            Integer acc = runOperations(newComputer);
            if(acc != null) {
                return acc;
            }
        }
        throw new RuntimeException("No changes made the computer stop looping!");
    }
    static Integer runOperations(OnboardComputer computer) {
        HashSet<Integer> doneOperations = new HashSet<>();
        while(true) {
            if(computer.getPosition() == computer.getOperations().size())
                return computer.getAccumulator();
            doneOperations.add(computer.getPosition());
            computer.doNextOperation();
            if (doneOperations.contains(computer.getPosition()) )
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
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //1137
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //1125
    }
}
