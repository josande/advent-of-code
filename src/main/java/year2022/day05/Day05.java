package year2022.day05;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day05 {

    static Object solveA(int boxes, List<String> values) {
        HashMap<Integer, Stack<Character>> crates = new HashMap<>();
        boolean partOne=true;

        for(int i=0; i<boxes;i++) {
            crates.put(i+1, new Stack<>());
        }

        for(String s : values) {
            String temp=s;
            if (temp.replaceAll(" ", "").isEmpty()) {
                partOne = false;


                for(Stack<Character> stack : crates.values()) {
                    reverseStack(stack);
                }
                continue;

            }

            if(partOne) {
                temp = s;

                int box=1;
                for(int i=0; i<boxes;i++) {
                    if(temp.length()<i*4+1) continue;
                    char c = temp.charAt(i*4+1);
                    if (c != ' ') {
                        crates.get(box).add(c);
                    }
                    box++;
                }
            } else {
                int toMove = Integer.parseInt(s.split(" ")[1]);
                int fromBox = Integer.parseInt(s.split(" ")[3]);
                int toBox = Integer.parseInt(s.split(" ")[5]);

                System.out.println(toMove+" "+fromBox+" "+toBox);

                for(int i=0; i<toMove;i++) {
                    char c = crates.get(fromBox).pop();
                    crates.get(toBox).add(c);
                }

            }

        }
        String result = "";
        for(int i=1; ;i++) {
            if(crates.containsKey(i)) {
                result +=crates.get(i).peek();
            } else {
                return result;
            }
        }
    }
    static void reverseStack(Stack<Character> stack)  {
        Queue<Character> queue = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        while (!queue.isEmpty()) {
            stack.add(queue.poll());
        }
    }
    static Object solveB(int boxes, List<String> values) {
        HashMap<Integer, Stack<Character>> crates = new HashMap<>();
        boolean partOne=true;

        for(int i=0; i<boxes;i++) {
            crates.put(i+1, new Stack<>());
        }

        for(String s : values) {
            String temp=s;
            if (temp.replaceAll(" ", "").isEmpty()) {
                partOne = false;


                for(Stack<Character> stack : crates.values()) {
                    reverseStack(stack);
                }
                continue;

            }

            if(partOne) {
                temp = s;

                int box=1;
                for(int i=0; i<boxes;i++) {
                    if(temp.length()<i*4+1) continue;
                    char c = temp.charAt(i*4+1);
                    if (c != ' ') {
                        crates.get(box).add(c);
                    }
                    box++;
                }
            } else {
                int toMove = Integer.parseInt(s.split(" ")[1]);
                int fromBox = Integer.parseInt(s.split(" ")[3]);
                int toBox = Integer.parseInt(s.split(" ")[5]);

                Stack<Character> stack = new Stack<>();
                for(int i=0; i<toMove;i++) {
                    stack.add(crates.get(fromBox).pop());
                }

             //   rev  erseStack(stack);
                while(!stack.isEmpty()) {
                    crates.get(toBox).add(stack.pop());
                }

            }

        }
        String result = "";
        for(int i=1; ;i++) {
            if(crates.containsKey(i)) {
                result +=crates.get(i).peek();
            } else {
                return result;
            }
        }
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(9, inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(9, inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
