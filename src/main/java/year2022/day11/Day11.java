package year2022.day11;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {
    @Data
    @AllArgsConstructor
    private static class Item {
        long worryLevel;
    }
    @Data
    private static class Monkey {
        private ArrayList<Item> items = new ArrayList<>();

        private String[] operation;
        private String[] test;
        private int ifTrue;
        private int ifFalse;

        private int timesInspected;

        public Monkey(String items, String operation, String test, String ifTrue, String ifFalse) {
            for(String level : items.split(": ")[1].split(", ")) {
                this.items.add(new Item(Long.parseLong(level)));
            }
            this.operation = operation.trim().split(" ");
            this.test=test.trim().split(" ");
            this.ifTrue=Integer.parseInt(ifTrue.trim().split(" ")[5]);
            this.ifFalse=Integer.parseInt(ifFalse.trim().split(" ")[5]);
        }

        void operation(Item item) {
            timesInspected++;
          //  System.out.println(item.getWorryLevel());
            long val1, val2;
            if(operation[3].equals("old")) {
                val1 = item.getWorryLevel();
            } else {
                val1 = Long.parseLong(operation[3]);
            }
            if(operation[5].equals("old")) {
                val2 = item.getWorryLevel();
            } else {
                val2 = Long.parseLong(operation[5]);
            }
            if (operation[4].equals("*")) {
                item.setWorryLevel(val1 * val2);
            } else {
                item.setWorryLevel(val1 + val2);
            }
        }
        void reduceWorry(Item item) {
            item.setWorryLevel(item.getWorryLevel() / 3L);

        }
        boolean test(Item item) {
            long testVal = Long.parseLong(test[3]);
            return item.getWorryLevel() % testVal == 0;
        }

        void throwItem(Item item) {
            if(test(item)) {
                monkeys.get(ifTrue).getItems().add(item);
            } else {
                monkeys.get(ifFalse).getItems().add(item);
            }
            items.remove(item);
        }

    }

    static ArrayList<Monkey> monkeys = new ArrayList<>();

    static Object solveA(List<String> values) {
        monkeys.clear();


        for(int row=0; row<values.size(); row+=7) {
            monkeys.add(new Monkey(values.get(row+1), 
                                   values.get(row+2),
                                   values.get(row+3),
                                   values.get(row+4),
                                   values.get(row+5)));
        }
        for(int round=0; round <20; round++ ){
            for(Monkey monkey : monkeys) {
                while(!monkey.getItems().isEmpty()) {
                    Item item = monkey.getItems().get(0);
                    monkey.operation(item);
                    monkey.reduceWorry(item);
                    monkey.throwItem(item);
                }
            }
        }
        List<Integer> timesCounted = monkeys.stream().map(Monkey::getTimesInspected).sorted().collect(Collectors.toList());

        return timesCounted.get(timesCounted.size()-1)*timesCounted.get(timesCounted.size()-2);
    }
    static Object solveB(List<String> values) {
        monkeys.clear();


        long sanity=1;
        for(int row=0; row< values.size(); row+=7) {
            monkeys.add(new Monkey(values.get(row+1),
                    values.get(row+2),
                    values.get(row+3),
                    values.get(row+4),
                    values.get(row+5)));
        }
        for(Monkey n : monkeys) {
            sanity*=Long.parseLong(n.test[3]);
        }

        for(int round=0; round < 10000; round++ ){
              for(Monkey monkey : monkeys) {
                while(!monkey.getItems().isEmpty()) {
                    Item item = monkey.getItems().get(0);
                    monkey.operation(item);
                    item.setWorryLevel(item.getWorryLevel() % sanity);
                    monkey.throwItem(item);
                }
            }
        }
        List<Integer> timesCounted = monkeys.stream().map(Monkey::getTimesInspected).sorted().collect(Collectors.toList());

        return (long) timesCounted.get(timesCounted.size() - 1) *timesCounted.get(timesCounted.size()-2);
    }
    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //54036
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //13237873355c
    }
}
