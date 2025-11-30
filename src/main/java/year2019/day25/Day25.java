package year2019.day25;

import utils.FileHelper;
import utils.OpComputer;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class Day25 {

    static OpComputer computer;
    static List<String> allItems = new ArrayList<>();

    static String runCommand(String command) {
        computer.addInput(command);
        return runCommands();
    }
    static String runCommands() {
        StringBuilder output= new StringBuilder("\n\n\n\n");
        computer.restartIfNeeded();
        while (computer.isRunning()) {
            Long c = computer.runUntilOutput();
            if (c != null) {
                output.append((char) c.intValue());
            }
        }
        if(output.toString().contains("You can't go that way.")) {
            throw new IllegalArgumentException("Bad pathing! "+output);
        }
        return output.toString();
    }

    public static void main() {

        allItems.add("whirled peas");
        allItems.add("space law space brochure");
        allItems.add("mutex");
        allItems.add("loom");
        allItems.add("hologram");
        allItems.add("cake");
        allItems.add("easter egg");
        allItems.add("manifold");

        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        computer = new OpComputer(inputs.get(0));

        runCommand("north");
        runCommand("take mutex");
        runCommand("east");
        runCommand("east");
        runCommand("east");
        runCommand("take whirled peas");
        runCommand("west");
        runCommand("west");
        runCommand("west");

        runCommand("south");
        runCommand("west");
        runCommand("take space law space brochure");
        runCommand("north");
        runCommand("take loom");
        runCommand("south");
        runCommand("south");
        runCommand("take hologram");

        runCommand("west");
        runCommand("take manifold");
        runCommand("east");

        runCommand("north");
        runCommand("east");

        runCommand("south");
        runCommand("take cake");

        runCommand("west");


        runCommand("south");
        runCommand("take easter egg");
        runCommand("south");


        for(int i1=0;i1<2;i1++) {
            for (int i2 = 0; i2 < 2; i2++) {
                for (int i3 = 0; i3 < 2; i3++) {
                    for (int i4 = 0; i4 < 2; i4++) {
                        for (int i5 = 0; i5 < 2; i5++) {
                            for (int i6 = 0; i6 < 2; i6++) {
                                for (int i7 = 0; i7 < 2; i7++) {
                                    for (int i8 = 0; i8 < 2; i8++) {
                                        if(i1==0) { runCommand("drop "+allItems.get(0)); } else { runCommand("take " + allItems.get(0));}
                                        if(i2==0) { runCommand("drop "+allItems.get(1)); } else { runCommand("take " + allItems.get(1));}
                                        if(i3==0) { runCommand("drop "+allItems.get(2)); } else { runCommand("take " + allItems.get(2));}
                                        if(i4==0) { runCommand("drop "+allItems.get(3)); } else { runCommand("take " + allItems.get(3));}
                                        if(i5==0) { runCommand("drop "+allItems.get(4)); } else { runCommand("take " + allItems.get(4));}
                                        if(i6==0) { runCommand("drop "+allItems.get(5)); } else { runCommand("take " + allItems.get(5));}
                                        if(i7==0) { runCommand("drop "+allItems.get(6)); } else { runCommand("take " + allItems.get(6));}
                                        if(i8==0) { runCommand("drop "+allItems.get(7)); } else { runCommand("take " + allItems.get(7));}
                                        String out=runCommand("south");
                                        if(!out.contains("Alert!") ) {
                                            out=out.replaceAll("[^\\d]", "");
                                            if (!out.isEmpty()) {
                                                System.out.println("Day25A: " + out);
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
