package year2017.day24;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day24 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day24());
    }

    @Data
    private static class Pipe {
        private int a,b;

        public Pipe(int a, int b) {
            this.a=a;
            this.b=b;
        }
    }
    @Data
    @AllArgsConstructor
    private static class State {
        private HashSet<Pipe> usedPipes;
        private HashSet<Pipe> allPipes;

        int currentOut;

        int getScore() {
            int score=0;
            for(Pipe p : usedPipes) {
                score+=p.getA()+p.getB();
            }
            return score;
        }

        State (State before, Pipe pipe) {
            this.usedPipes=new HashSet<>(before.getUsedPipes());
            this.usedPipes.add(pipe);
            this.allPipes=before.getAllPipes();
            this.currentOut=before.getCurrentOut() == pipe.getA() ? pipe.getB() : pipe.getA();
        }

        List<State> getPossibleStates() {
            ArrayList<Pipe> remainingPipes = new ArrayList<>(allPipes);
            remainingPipes.removeAll(usedPipes);
            List<State> possibleStates = new ArrayList<>();

            for(Pipe p : remainingPipes) {
                if(p.getA() == currentOut || p.getB() == currentOut) {
                    possibleStates.add(new State(this, p));
                }
            }
            return possibleStates;
        }
    }
    @Override
    public Object solveA(List<String> input) {
        HashSet<Pipe> pipes = new HashSet<>();
        for(String row : input) {
            int a = Integer.parseInt(row.split("/")[0]);
            int b = Integer.parseInt(row.split("/")[1]);
            pipes.add(new Pipe(a,b));
        }

        State start = new State(new HashSet<>(), pipes, 0);

        Queue<State> queue = new ArrayDeque<>();

        queue.add(start);

        int maxScore=0;
        while (!queue.isEmpty()) {
            State current = queue.remove();
            List<State> children = current.getPossibleStates();

            if(children.isEmpty()) {
                maxScore=Math.max(current.getScore(), maxScore);
            } else {
                queue.addAll(children);
            }
        }

        return maxScore;
    }

    @Override
    public Object solveB(List<String> input) {
        HashSet<Pipe> pipes = new HashSet<>();
        for(String row : input) {
            int a = Integer.parseInt(row.split("/")[0]);
            int b = Integer.parseInt(row.split("/")[1]);
            pipes.add(new Pipe(a,b));
        }

        State start = new State(new HashSet<>(), pipes, 0);
        Queue<State> queue = new ArrayDeque<>();
        queue.add(start);
        int maxLength =0;
        int maxScore =0;
        while (!queue.isEmpty()) {
            State current = queue.poll();
            List<State> children = current.getPossibleStates();

            if(children.isEmpty()) {
                if(current.getUsedPipes().size()>=maxLength) {
                    if(current.getUsedPipes().size()>maxLength)
                        maxScore=0;
                    maxLength=current.getUsedPipes().size();
                    maxScore = Math.max(current.getScore(), maxScore);
                }
            } else {
                queue.addAll(children);
            }
        }
        return maxScore;
    }
}
