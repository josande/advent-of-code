package utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Data
public class Assembunny {

    private int a,b,c,d, instruction;
    ArrayList<String> commands = new ArrayList<>();

    boolean keepRunning=true;
    boolean cyclic=false;
    int lastOut=1;
    HashSet<List<Integer>> cycles = new HashSet<>();

    public void addCommand(String command) {
        commands.add(command);
    }

    public boolean run() {
        simplify();

        while(instruction < commands.size()) {
            if(!keepRunning)
                return false;
            execute(commands.get(instruction));
        }
        return true;
    }

    public void execute(String command) {
        String[] words = command.split(" ");
        switch (words[0]) {
            case "cpy" -> {
                int val=getValue(words[1]);
                try {
                    Integer.parseInt(words[2]);
                } catch (NumberFormatException ignored) {
                    switch (words[2]) {
                        case "a" -> a = val;
                        case "b" -> b = val;
                        case "c" -> c = val;
                        case "d" -> d = val;
                    }
                }
            }
            case "inc" -> {
                switch (words[1]) {
                    case "a" -> a++;
                    case "b" -> b++;
                    case "c" -> c++;
                    case "d" -> d++;
                }
            }
            case "dec" -> {
                switch (words[1]) {
                    case "a" -> a--;
                    case "b" -> b--;
                    case "c" -> c--;
                    case "d" -> d--;
                }
            }
            case "jnz" -> {
                int val1 =getValue(words[1]), val2=getValue(words[2]);
                if (val1!=0) {
                   instruction+=val2-1;
                }
            }
            case "tgl" -> {
                int val = getValue(words[1]);
                int targetedInstruction = instruction+val;
                if(targetedInstruction >= commands.size()) {
                    instruction++;
                    return;
                }
                String[] instructionBefore = commands.get(instruction+val).split(" ");
                if(instructionBefore.length == 2) {
                    if (instructionBefore[0].equals("inc")) {
                        commands.set(targetedInstruction, commands.get(instruction+val).replaceAll("inc", "dec"));
                    } else {
                        commands.set(instruction+val, "inc"+commands.get(instruction+val).substring(3));
                    }
                } else {
                    if (instructionBefore[0].equals("jnz")) {
                        commands.set(instruction+val, commands.get(instruction+val).replaceAll("jnz", "cpy"));
                    } else {
                        commands.set(instruction+val, "jnz"+commands.get(instruction+val).substring(3));
                    }
                }
            }
            case "mul" -> {
                int fac1 = getValue(words[1]);
                int fac2 = getValue(words[2]);
                switch (words[3]) {
                    case "a" -> a+=fac1*fac2;
                    case "b" -> b+=fac1*fac2;
                    case "c" -> c+=fac1*fac2;
                    case "d" -> d+=fac1*fac2;
                }
            }
            case "out" -> {
                int outValue = getValue(words[1]);
              //  System.out.println(a+","+b+","+c+","+d+"- "+outValue);
                if(outValue+lastOut == 1) {
                    lastOut = outValue;
                    ArrayList<Integer> values = new ArrayList<>(Arrays.asList(a, b, c, d));
                    if(cycles.contains(values)) {
                        cyclic=true;
                        keepRunning=false;
                    } else {
                        cycles.add(values);
                    }
                } else {
                    keepRunning = false;
                }
            }
            default -> throw new IllegalArgumentException("Unknown command: "+command);
        }
        instruction++;
    }
    private void simplify() {
        for (int i=0; i<commands.size()-5; i++) {
            if(commands.get(i).startsWith("cpy") &&
               commands.get(i+1).startsWith("inc") &&
               commands.get(i+2).startsWith("dec") &&
               commands.get(i+3).startsWith("jnz") &&
               commands.get(i+4).startsWith("dec") &&
               commands.get(i+5).startsWith("jnz") ) {
               String fac1 = commands.get(i).split(" ")[1];
               String fac2 = commands.get(i+4).split(" ")[1];
               String targ = commands.get(i+1).split(" ")[1];
               commands.set(i, "mul "+fac1+" "+fac2+" "+targ);
               commands.set(i+1, "jnz 0 0");
               commands.set(i+2, "jnz 0 0");
               commands.set(i+3, "jnz 0 0");
               commands.set(i+4, "jnz 0 0");
               commands.set(i+5, "jnz 0 0");

            }
        }
    }
    private int getValue(String word) {
        switch (word) {
            case "a" -> {return a;}
            case "b" -> {return b;}
            case "c" -> {return c;}
            case "d" -> {return d;}
            default -> {return Integer.parseInt(word);}
        }
    }
}