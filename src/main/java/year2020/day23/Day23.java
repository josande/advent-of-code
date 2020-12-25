package year2020.day23;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day23 {

    static class Cup {
        Cup next;
        final int id;

        public Cup(int id) {
            this.id = id;
        }

        public Cup getNext() {
            return next;
        }

        public void setNext(Cup next) {
            this.next = next;
        }

        public int getId() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;

            if (!(obj instanceof Cup)) {
                return false;
            }

            Cup o = (Cup) obj;
            return this.getId() == o.getId();
        }
        @Override
        public int hashCode() {
            return id;
        }

    }

    static String solveA(List<String> values) {
        ArrayList<Cup> cups = new ArrayList<>();
        for(var row : values) {
            for( var id : row.toCharArray()) {
                int cupId = Integer.parseInt(String.valueOf(id));
                cups.add(new Cup(cupId));
            }
        }
       cups.get(cups.size()-1).setNext(cups.get(0));

        for (int i =0; i<cups.size(); i++) {
            if(i>0) {
                cups.get(i - 1).setNext(cups.get(i));
            }
        }

        int currentId= cups.get(0).getId();

        for(int turn =0; turn<100; turn++) {
            int currId = currentId;
            @SuppressWarnings("OptionalGetWithoutIsPresent") Cup currentCup = cups.stream().filter(c -> (c.getId() == currId)).findAny().get();

            //remove three
            Cup posPlus1 = currentCup.getNext();
            Cup posPlus2 = posPlus1.getNext();
            Cup posPlus3 = posPlus2.getNext();

            currentCup.setNext(posPlus3.getNext());

            int destination = currentCup.getId();
            while (currentCup.getId() == destination ||
                     posPlus1.getId() == destination ||
                     posPlus2.getId() == destination ||
                     posPlus3.getId() == destination) {
                if (destination == 1) destination = cups.size();
                else destination--;
            }
            int destinationId = destination;

            @SuppressWarnings("OptionalGetWithoutIsPresent") Cup destinationCup = cups.stream().filter(c -> (c.getId() == destinationId)).findAny().get();
            Cup destPlusOne = destinationCup.getNext();

            destinationCup.setNext(posPlus1);

            posPlus3.setNext(destPlusOne);

            currentId = currentCup.getNext().getId();
        }
        StringBuilder result = new StringBuilder();

        @SuppressWarnings("OptionalGetWithoutIsPresent") Cup cu = cups.stream().filter(c -> (c.getId() == 1)).findAny().get().getNext();
        for (int i = 0; i < cups.size()-1; i++) {
            result.append(cu.getId());
            cu = cu.getNext();
        }
        return result.toString();
    }


    static long solveB(List<String> values) {
        ArrayList<Cup> cups = new ArrayList<>();
        for(var row : values) {
            for( var id : row.toCharArray()) {
                int cupId = Integer.parseInt(String.valueOf(id));
                cups.add(new Cup(cupId));
            }
        }
        for(int i = cups.size(); i < 1000000; i++) {
            cups.add(new Cup(i+1));
        }

        cups.get(1000000-1).setNext(cups.get(0));
        Map<Integer, Cup> allCups=cups.stream().collect(Collectors.toMap(Cup::getId, Function.identity()));
        for (int i =0; i<cups.size(); i++) {
            if(i>0) {
                cups.get(i - 1).setNext(cups.get(i));
            }
        }

        int currentId= cups.get(0).getId();
        for(int turn=0; turn<10000000; turn++) {
            Cup currentCup = allCups.get(currentId);

            //remove three
            Cup posPlus1 = currentCup.getNext();
            Cup posPlus2 = posPlus1.getNext();
            Cup posPlus3 = posPlus2.getNext();

            currentCup.setNext(posPlus3.getNext());

            int destination = currentCup.getId();
            while (currentCup.getId() == destination ||
                    posPlus1.getId() == destination ||
                    posPlus2.getId() == destination ||
                    posPlus3.getId() == destination) {
                if (destination == 1) destination = cups.size();
                else destination--;
            }
            Cup destinationCup = allCups.get(destination);
            Cup destPlusOne = destinationCup.getNext();

            destinationCup.setNext(posPlus1);
            posPlus3.setNext(destPlusOne);

            currentId = currentCup.getNext().getId();
        }

        Cup cu = allCups.get(1).getNext();
        return (long) cu.getId() * cu.getNext().getId();
    }


    public static void main (String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //98645732
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //689500518476
    }
}
