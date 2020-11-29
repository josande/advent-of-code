package utils;

import java.util.ArrayList;
import java.util.List;

public class OpComputer {
    private List<Long> input = new ArrayList<>();
    private int inputCounter=0;
    private Long output;
    private int position=0;
    private Long relativeBase = 0L;
    private Long[] values;
    boolean ended=false;
    private Long[] orgValues;

    public OpComputer(String input) {
        values=splitToInt(input);
        orgValues=splitToInt(input);
    }
    public OpComputer(OpComputer computer) {
        this.input=new ArrayList<>(computer.input);
        this.inputCounter=computer.inputCounter;
        this.output=computer.output;
        this.position=computer.position;
        this.relativeBase=computer.relativeBase;
        this.values=computer.values.clone();
        this.ended=computer.ended;
        this.orgValues=computer.orgValues.clone();

    }
    OpComputer(Long[] input) {
        values= input.clone();
        orgValues= input.clone();
    }

    public void addInput(long input) {
        this.input.add(input);
    }
    public void addInput(String input) {
        for (char c : input.toCharArray()) {
            addInput((int) c);
        }
        addInput(10);
    }
    public List<Long> getInputs() {
        return input;
    }
    public long getOutput() {
        return output;
    }
    public void clearOutput() {
        output=null;
    }

    public boolean isRunning() {
        return !ended;
    }

    public void setMemory(long value, int position) {
        values[position]=value;
    }
    public long getMemory(int position) {
        return values[position];
    }

    public void restartIfNeeded() {
        ended=false;
    }

    public void reset() {
        output=null;
        input = new ArrayList<>();
        inputCounter=0;
        ended=false;
        position=0;
        values=orgValues.clone();
        relativeBase=0L;


    }

    public long getRelativeBase() {
        return relativeBase;
    }

    public void setRelativeBase(long relativeBase) {
        this.relativeBase = relativeBase;
    }

    public void run() {
        while (!ended ) {
            executeOperation();
        }
    }
    public Long runUntilOutput() {
        output=null;
        while (!ended && output==null) {
            executeOperation();
        }
        return output;
    }
    private void executeOperation() {
        int opCode = values[position].intValue();
        switch (opCode % 100) {
            case 1 -> {
                long varA = getVariableValue(1);
                long varB = getVariableValue(2);
                int pos = values[position + 3].intValue();
                int modifier = getModifier(3, opCode).intValue();
                if (modifier == 2) {
                    pos += relativeBase;
                }
                values[pos] = varA + varB;
                position += 4;
            }
            case 2 -> {
                long varA = getVariableValue(1);
                long varB = getVariableValue(2);
                int pos = values[position + 3].intValue();
                int modifier = getModifier(3, opCode).intValue();
                if (modifier == 2) {
                    pos += relativeBase;
                }
                values[pos] = varA * varB;
                position += 4;
            }
            case 3 -> {

                int pos = values[position + 1].intValue();
                int modifier = getModifier(1, opCode).intValue();
                if (modifier == 2) {
                    pos += relativeBase;
                }
                if (input.size() > inputCounter) {
                    values[pos] = input.get(inputCounter);

                    inputCounter++;
                    position += 2;
                } else {
                    values[pos] = -1L;
                    position += 2;
                    ended = true;
                }
            }
            case 4 -> {
                output = getVariableValue(1);
                position += 2;
            }
            case 5 -> {
                Long varA = getVariableValue(1);
                Long varB = getVariableValue(2);
                if (varA != 0) {
                    position = varB.intValue();
                } else {
                    position += 3;
                }
            }
            case 6 -> {
                Long varA = getVariableValue(1);
                Long varB = getVariableValue(2);

                if (varA == 0L) {
                    position = varB.intValue();
                } else {
                    position += 3;
                }
            }
            case 7 -> {
                long varA = getVariableValue(1);
                long varB = getVariableValue(2);
                int pos = values[position + 3].intValue();
                int modifier = getModifier(3, opCode).intValue();
                if (modifier == 2) {
                    pos += relativeBase;
                }
                if (varA < varB) {
                    values[pos] = 1L;
                } else {
                    values[pos] = 0L;
                }
                position += 4;
            }
            case 8 -> {
                long varA = getVariableValue(1);
                long varB = getVariableValue(2);
                //int pos =getVariableValue(3).intValue();
                int pos = values[position + 3].intValue();
                int modifier = getModifier(3, opCode).intValue();
                if (modifier == 2) {
                    pos += relativeBase;
                }
                if (varA == varB) {
                    values[pos] = 1L;
                } else {
                    values[pos] = 0L;
                }
                position += 4;
            }
            case 9 -> {
                //Long varA = getVariableValue(1);
                Long varA = getVariableValue(1);
                relativeBase += varA;
                position += 2;
            }
            case 99 -> ended = true;

            default -> System.out.println("Unknown code: " + values[position]);
        }

    }

    public int getNumbersOfInputLeft() {
        return input.size()-inputCounter;
    }

    private Long getVariableValue(int variableNumber) {
        long opCode=values[position];
        long modifier = getModifier(variableNumber, opCode);
        if (modifier==1) { // Immediate mode
            return values[(position + variableNumber)];
        } else if (modifier==0){ // Positional mode
            return values[values[position + variableNumber].intValue()];
        } else if (modifier==2 ) { // Offset mode
            int offset = values[position + variableNumber].intValue();
            return values[ relativeBase.intValue() +offset];
        }
        System.out.println("Unknown variable value, position="+position+", variable number="+variableNumber+", opCode="+opCode);
        return -1L;
    }
    private Long getModifier(long variableNumber, long opCode) {
        int n=1;
        opCode/=100; // Ignore last 2 digits
        for (int i=1; i<variableNumber;i++) {
            opCode/=10;
        }
        return opCode%10;
    }
    private Long[] splitToInt(String input) {
        String[] inputs = input.split(",");
        Long[] values = new Long[150000];
        for (int i =0; i<150000;i++) {
            values[i]=0L;
        }

        for (int i=0;i<inputs.length; i++) {
            try {
                values[i] = Long.valueOf(inputs[i]);
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return values;
    }
}
