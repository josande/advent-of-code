package year2017.day14;

import org.apache.commons.lang3.StringUtils;
import utils.AdventOfCode;
import utils.Point;
import utils.Reporter;
import year2017.day10.Day10;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Day14 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day14());
    }

    @Override
    public Object solveA(List<String> input) {

        Day10 day10 = new Day10();
        int sum=0;
        for(int y=0; y<128; y++) {
            String hex = (String) day10.solveB(List.of(input.get(0)+"-"+y));
            for(char c : hex.toCharArray()) {
                String bin = new BigInteger(c+"", 16).toString(2);
                for(char c2 : bin.toCharArray()) {
                    if (c2=='1') sum++;
                }
            }
        }
        return sum;
    }

    @Override
    public Object solveB(List<String> input) {
        HashSet<Point> walls = new HashSet<>();
        Day10 day10 = new Day10();

        for(int y=0; y<128; y++) {
            String hex = (String) day10.solveB(List.of(input.get(0)+"-"+y));
            StringBuilder row= new StringBuilder();
            for(char c : hex.toCharArray()) {
                String bin = StringUtils.leftPad(new BigInteger(c+"", 16).toString(2), 4, '0');
                row.append(bin);
            }
            for(int x=0; x<128; x++) {
                if(row.charAt(x)=='1') walls.add(new Point(x,y));
            }
        }
        int regions=0;
        while(!walls.isEmpty()) {
            LinkedList<Point> queue = new LinkedList<>();
            queue.add(walls.iterator().next());
            while (!queue.isEmpty()) {
                Point current = queue.poll();
                if (walls.contains(current)) {
                    queue.addAll(current.getOrthogonalNeighbours2d());
                }
                walls.remove(current);
            }
            regions++;
        }

        return regions;
    }
}
