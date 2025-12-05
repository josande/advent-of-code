package year2025.day04;

import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.ArrayList;
import java.util.List;

public class Day04 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day04());
    }

    @Override
    public Object solveA(List<String> input) {
        var map = MapUtil.asMapFiltered(input,'@');
        var result =0;
        for(var point : map.keySet()) {
            if(point.getAllNeighbours2d().stream().filter(map::containsKey).count()<4) result++;
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        var map = MapUtil.asMap(input);

        var result=0;
        boolean keepRunning = true;
        while(keepRunning) {
            keepRunning = false;
            for(var point : map.keySet()) {
                if(map.get(point) != '@') continue;
                if(point.getAllNeighbours2d().stream().filter(s -> map.containsKey(s) && map.get(s) == '@').count() < 4) {
                    keepRunning=true;
                    map.put(point, '.');
                    result++;
                }
            }
        }
        return result;
    }
}
