package year2019.day25;

import utils.FileHelper;
import utils.OpComputer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day25 {

    public static class Room {
        String name;
        List<String> wayHere;
        List<String> doors;
        List<String> items;

        public Room(String name) {
            this.name=name;
        }
        public Room(String name, List<String> wayHere, List<String> doors, List<String> items) {
            this.name = name;
            this.wayHere = new ArrayList<>(wayHere);
            this.doors = new ArrayList<>(doors);
            if(name.contains("Security Checkpoint"))
                this.doors=new ArrayList<>();
            this.items = new ArrayList<>(items);

            for(String string : new HashSet<>(items)) {
                if (!string.equals("giant electromagnet")
                        && !string.equals("escape pod")
                        && !string.equals("photons")
                        && !string.equals("molten lava")
                        && !string.equals("infinite loop")

                        ) {
                //    allItems.add(string);
                    runCommands();
                }
            }
        }

        public String getName() {
            return name;
        }

        public void setWayHere(List<String> wayHere) {
            this.wayHere = wayHere;
            for(String string :items) {
                System.out.print("Item "+string+" at: "+name+" ");
                for(String s : wayHere) System.out.print(s);
                System.out.print("\n");
                computer.addInput("take " + string);
            }
        }

        public List<String> getWayHere() {
            return wayHere;
        }
        public List<String> getWayBack() {
            List<String> wayBack=new ArrayList<>();
            for(int i=wayHere.size()-1; i>=0; i--) {
                if(wayHere.get(i).equals("north"))
                    wayBack.add("south");
                if(wayHere.get(i).equals("south"))
                    wayBack.add("north");
                if(wayHere.get(i).equals("west"))
                    wayBack.add("east");
                if(wayHere.get(i).equals("east"))
                    wayBack.add("west");
            }
            return wayBack;
        }

        public List<String> getDoors() {
            return doors;
        }

        public List<String> getItems() {
            return items;
        }

        @Override
        public boolean equals(Object obj) {
            Room o = (Room) obj;
            return this.getName().equals(o.getName());
        }

        @Override
        public int hashCode() {
            return getName().hashCode();
        }

    }

    static OpComputer computer;
    static List<Room> allRooms = new ArrayList<>();
    static List<String> allItems = new ArrayList<>();



    static void goToRoom(Room room) {
        List<String> routeSoFar = new ArrayList<>(room.getWayHere());

        computer.restartIfNeeded();

        for(String door : routeSoFar) {
            computer.addInput(door);
            runCommands();
        }
    }
    static String goToRoom(String direction, Room previousRoom) {
        List<String> routeSoFar = new ArrayList<>(previousRoom.getWayHere());
        routeSoFar.add(direction);

        computer.restartIfNeeded();
        String roomData="";

        for(String door : routeSoFar) {
            computer.addInput(door);
            roomData=runCommands();
        }
        Room newRoom = searchRoom(roomData);


        if(!allRooms.contains(newRoom)) {
            newRoom.setWayHere(routeSoFar);

            if (newRoom.getName().isEmpty()) {
                System.out.print("no name?");
            } else {
                allRooms.add(newRoom);
            }
        }
        computer.restartIfNeeded();

        for(String door:newRoom.getWayBack()) {
            computer.addInput(door);
        }
        return roomData;
    }
    static String runCommand(String command) {
        computer.addInput(command);
        return runCommands();
    }
    static String runCommands() {
        String output="\n\n\n\n";
        computer.restartIfNeeded();
        while (computer.isRunning()) {
            Long c = computer.runUntilOutput();
            if (c != null) {
                output+=(char) c.intValue();
            }
        }
        if(output.contains("You can't go that way.")) {
            System.out.print("what happened here?");
        }
        return output;
    }
    static String runAndPrint() {
        String output = runCommands();
        System.out.print(output);
        return output;
    }

    static Room searchRoom(String roomData) {
        String output=roomData;
//        System.out.println(output);
        if(output.contains("pressure"))
//            System.out.println(output);
        if(output.contains("sensor"))
//            System.out.println(output);
        if(output.contains("plate"))
//            System.out.println(output);
        if(output.contains("You can't go that way."))
            System.out.println(output);


if(!output.contains("\n\n\n")) {
    System.out.println(output);
}
        output=output.substring(output.lastIndexOf("\n\n\n"));
        String[] things = output.split("\n");
        String name="";
        List<String> doors=new ArrayList<>();
        List<String> items=new ArrayList<>();
        for (String string : things ) {
            if (string.contains("==")) {
                name = string;
            }
            if (!string.startsWith("-")) continue;
            string = string.substring(2);
            if (string.equals("north") || string.equals("west") || string.equals("east") || string.equals("south")) {
                doors.add(string);
            } else {
                items.add(string);

            }
        }
        return new Room(name, new ArrayList<>(), doors, items);


    }

    static void mapAllRooms() {
        HashSet<Room> queue=new HashSet<>();
        HashSet<Room> visited=new HashSet<>();
        Room start = searchRoom(runCommands());
        allRooms.add(start);
        queue.add(start);
        while(!queue.isEmpty()) {

            for (Room r : queue) {
                visited.add(r);
                for (String door : r.getDoors()) {
                    goToRoom(door, r);
                }
            }
            queue.addAll(allRooms);
            queue.removeAll(visited);

        }
    }
    public static void main(String[] args) {

        allItems.add("whirled peas");
        allItems.add("space law space brochure");
        allItems.add("mutex");
        allItems.add("loom");
        allItems.add("hologram");
        allItems.add("cake");
        allItems.add("easter egg");
        allItems.add("manifold");

        long t0 = System.currentTimeMillis();
        String input = new FileHelper().readFile("year2019/day25/input.txt").get(0);
        computer = new OpComputer(input);

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
        System.out.println("Time: "+(System.currentTimeMillis()-t0)+" ms");
    }
}
