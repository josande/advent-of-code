package year2023.day11;

import utils.AdventOfCode;
import utils.Point;
import utils.Reporter;
import java.util.ArrayList;
import java.util.List;

public class Day11 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day11());
    }

    @Override
    public Object solveA(List<String> input) {
        return solveWithOffset(input, 2);
    }

    @Override
    public Object solveB(List<String> input) {
        return solveWithOffset(input, 1000000);
    }

    public long solveWithOffset(List<String> input, final int offsetSize) {
        int size = input.size();
        ArrayList<Point> allStars = new ArrayList<>();

        for(int y=0; y<size; y++) {
            for(int x=0; x<size; x++) {
                if(input.get(y).charAt(x) == '#') {
                    allStars.add(new Point(x, y));
                }
            }
        }

        ArrayList<Integer> emptyCols=new ArrayList<>();
        ArrayList<Integer> emptyRows=new ArrayList<>();
        for(int x=0; x<size; x++) {
            boolean emptyCol=true;
            for (String s : input) {
                if (s.charAt(x) == ('#')) {
                    emptyCol = false;
                    break;
                }
            }
            if(emptyCol) {
                emptyCols.add(x);
            }
        }
        for(int y=0; y<size; y++) {
            if(!input.get(y).contains("#")) {
                emptyRows.add(y);
            }
        }

        for(int x=size-1; x>=0; x--) {
            int xx=x;
            long passedCols =  emptyCols.stream().filter(px -> px<xx).count();
            allStars.stream().filter(e->e.getX()==xx).forEach(e-> e.setX(Math.toIntExact((e.getX()+(offsetSize-1)*passedCols))));
        }
        for(int y=size-1; y>=0; y--) {
            int yy=y;
            long passedRows = emptyRows.stream().filter(py -> py<yy).count();
            allStars.stream().filter(e->e.getY()==yy).forEach(e-> e.setY(Math.toIntExact((e.getY()+(offsetSize-1)*passedRows))));
        }

        long result =0L;
        for(int i =0; i< allStars.size(); i++) {
            for(int j=i+1; j<allStars.size(); j++) {
                result+= allStars.get(i).getManhattanDistance(allStars.get(j));
            }
        }
        return result;

    }
}
