package year2017.day07;

import lombok.Data;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;
import java.util.stream.Collectors;

public class Day07 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day07());
    }

    @Data
    public static class Program {
        private String name;
        private int weight;
        private List<Program> programs = new ArrayList<>();

        void addProgram(Program program) {
            programs.add(program);
        }
        int getTotalWeight() {
            int totalWeight=weight;
            for(Program program : programs) {
                totalWeight+= program.getTotalWeight();
            }
            return totalWeight;
        }
        boolean isUnbalanced() {
            if(programs.isEmpty())
                return false;
            int w1=programs.get(0).getTotalWeight();
            for(int i=1; i<programs.size(); i++) {
                int w = programs.get(i).getTotalWeight();
                if(w != w1) {
                    return true;
                }
            }
            return false;
        }
    }
    @Override
    public Object solveA(List<String> input) {
        HashMap<String, Program> programs = new HashMap<>();
        HashSet<String> subPrograms= new HashSet<>();
        for(String row : input) {
            Program program = new Program();
            program.setName(row.substring(0,row.indexOf(" ")));
            program.setWeight(Integer.parseInt(row.substring(row.indexOf("(")+1, row.indexOf(")"))));
            programs.put(program.getName(), program);
        }
        for(String row : input) {
            if(row.contains("->")) {
                Program program = programs.get(row.substring(0,row.indexOf(" ")));
                String part2 = row.split("-> ")[1];
                for(String name : part2.split(", ")) {
                    program.addProgram(programs.get(name));
                    subPrograms.add(name);
                }
            }
        }

        Set<String> allPrograms = programs.keySet();
        allPrograms.removeAll(subPrograms);

        return allPrograms.stream().findAny().orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<String, Program> programs = new HashMap<>();
        for(String row : input) {
            Program program = new Program();
            program.setName(row.substring(0,row.indexOf(" ")));
            program.setWeight(Integer.parseInt(row.substring(row.indexOf("(")+1, row.indexOf(")"))));
            programs.put(program.getName(), program);
        }
        for(String row : input) {
            if(row.contains("->")) {
                Program program = programs.get(row.substring(0,row.indexOf(" ")));
                String part2 = row.split("-> ")[1];
                for(String name : part2.split(", ")) {
                    program.addProgram(programs.get(name));
                }
            }
        }

        List<Program> unbalanced = programs.values().stream().filter(Program::isUnbalanced).collect(Collectors.toList());

        Program problemProgram=null;
        for(Program p : unbalanced) {
            boolean allBalanced=true;
            for(Program cc : p.getPrograms()) {
                if(cc.isUnbalanced())
                    allBalanced=false;
            }
            if(allBalanced)
                problemProgram = p;
        }

        if(problemProgram == null) throw new IllegalStateException("No problem programs found.");

        HashMap<Program, Integer> weightMap = new HashMap<>();
        for(Program c : problemProgram.getPrograms()) {
            weightMap.put(c, c.getTotalWeight());
        }
        List<Integer> weights = new ArrayList<>(weightMap.values());
        Collections.sort(weights);
        int correctWeight = weights.get(1);
        for(Map.Entry<Program, Integer> s : weightMap.entrySet()) {
            if(s.getValue() != correctWeight) {
                int diff = correctWeight - s.getValue();
                return s.getKey().getWeight()+diff;
            }
        }
        throw new IllegalStateException("Could not find solution.");

    }
}
