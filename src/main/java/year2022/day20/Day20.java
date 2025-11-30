package year2022.day20;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day20 {

    @Data
    @AllArgsConstructor
    static class Numb {
        long value;
        int index;

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof Numb)) return false;
            Numb o = (Numb) obj;
            return this.value== o.value &&
                    this.index== o.index;
        }
        @Override
        public int hashCode() {
            return (int)value*37+index;
        }

    }
    static Object solveA(List<String> values) {
        LinkedList<Numb> numbers = new LinkedList<>();
        LinkedList<Numb> numbers2 = new LinkedList<>();

        int index=0;
        for(String s : values) {
            int number = Integer.parseInt(s);
            Numb numb = new Numb(number, index++);
            numbers.add(numb);
            numbers2.add(numb);
        }

        for(Numb currentNumb : numbers2){

            int currentIndex=numbers.indexOf(currentNumb);
            numbers.remove(currentIndex);

            int newIndex = (int) (currentIndex + currentNumb.getValue()% numbers.size() + numbers.size())% numbers.size() ;
            while(newIndex<0) {
                newIndex+=numbers.size();
            }

            numbers.add(newIndex, currentNumb);
        }

        List<Long> vals = numbers.stream().map(Numb::getValue).collect(Collectors.toList());
        int indexOfZero= vals.indexOf(0L);
        long result = vals.get((1000+indexOfZero)%numbers.size());
        result += vals.get((2000+indexOfZero)%numbers.size());
        result += vals.get((3000+indexOfZero)%numbers.size());

        return result;
    }
    static Object solveB(List<String> values) {
        LinkedList<Numb> numbers = new LinkedList<>();
        LinkedList<Numb> numbers2 = new LinkedList<>();

        int index=0;
        long key=811589153L;
        for(String s : values) {
            int number = Integer.parseInt(s);
            Numb numb = new Numb(number*key, index++);
            numbers.add(numb);
            numbers2.add(numb);
        }
        for(int iteration=0; iteration<10; iteration++) {
            for (Numb currentNumb : numbers2) {

                int currentIndex = numbers.indexOf(currentNumb);
                numbers.remove(currentIndex);

                int newIndex = (int) (currentIndex + currentNumb.getValue()% numbers.size() + numbers.size())% numbers.size() ;
                while (newIndex < 0) {
                    newIndex += numbers.size();
                }

                numbers.add(newIndex, currentNumb);
            }
            List<Long> vals = numbers.stream().map(Numb::getValue).collect(Collectors.toList());
        }

        List<Long> vals = numbers.stream().map(Numb::getValue).collect(Collectors.toList());
        int indexOfZero= vals.indexOf(0L);
        long result = vals.get((1000+indexOfZero)%numbers.size());
        result += vals.get((2000+indexOfZero)%numbers.size());
        result += vals.get((3000+indexOfZero)%numbers.size());

        return result;
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 4066
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 6704537992933
    }
}
