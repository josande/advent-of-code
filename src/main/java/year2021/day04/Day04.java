package year2021.day04;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day04 {

    static Object solveA(List<String> values) {
        List<Integer> numbers = Stream.of(values.get(0).split(",")).map(Integer::valueOf).collect(Collectors.toList());
        List<List<List<Integer>>> allBoards= new ArrayList<>();
        values=values.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        for(int i=1; i < values.size(); ) {
            List<List<Integer>> board = new ArrayList<>();
            board.add(Stream.of(values.get(i).split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            board.add(Stream.of(values.get(i+1).split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            board.add(Stream.of(values.get(i+2).split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            board.add(Stream.of(values.get(i+3).split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            board.add(Stream.of(values.get(i+4).split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            i+=5;
            allBoards.add(board);
        }

        for(int i=0; i<numbers.size();i++) {

            for (List<List<Integer>> board : allBoards) {
                int sum = isWin(board, numbers.subList(0, i));
                if (sum>=0) {
                    return sum * numbers.get(i-1);
                }
            }
        }

        return null;
    }
    static int isWin(List<List<Integer>> board, List<Integer> numbers) {

        for (int num : numbers) {
            for (int row =0; row<5; row++) {
                for (int pos =0; pos<5; pos++) {
                    if(board.get(row).get(pos) == num) {
                        board.get(row).set(pos, -1);
                    }
                }
            }
        }
        for (int i =0; i<5; i++) {
            if((board.get(i).get(0) + board.get(i).get(1) + board.get(i).get(2) + board.get(i).get(3) + board.get(i).get(4) )==-5) {
                return sum(board);
            }
            if((board.get(0).get(i) + board.get(1).get(i) + board.get(2).get(i) + board.get(3).get(i) + board.get(4).get(i) )==-5) {
                return sum(board);
            }
        }
        return -1;
    }
    static int sum(List<List<Integer>> board) {
        int sum=0;
        for (int row = 0; row < 5; row++) {
            for (int pos =0; pos < 5; pos++) {
                if(board.get(row).get(pos) > 0)
                    sum+=board.get(row).get(pos);
            }
        }
        return sum;
    }
    static Object solveB(List<String> values) {
        List<Integer> numbers = Stream.of(values.get(0).split(",")).map(Integer::valueOf).collect(Collectors.toList());
        List<List<List<Integer>>> allBoards= new ArrayList<>();
        values=values.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        for(int i=1; i < values.size(); ) {
            List<List<Integer>> board = new ArrayList<>();
            board.add(Stream.of(values.get(i).split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            board.add(Stream.of(values.get(i+1).split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            board.add(Stream.of(values.get(i+2).split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            board.add(Stream.of(values.get(i+3).split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            board.add(Stream.of(values.get(i+4).split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            i+=5;
            allBoards.add(board);
        }
        List<List<List<Integer>>> boardsToRemove= new ArrayList<>();

        for(int i=0; i<numbers.size();i++) {
            for (List<List<Integer>> board : allBoards) {
                int sum = isWin(board, numbers.subList(0, i));
                if (sum>=0) {
                    boardsToRemove.add(board);
                    if(allBoards.size()==1) {
                        return sum * numbers.get(i-1);
                    }

                }
            }
            allBoards.removeAll(boardsToRemove);
        }


        return null;
    }

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //16674
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //7075
    }
}
