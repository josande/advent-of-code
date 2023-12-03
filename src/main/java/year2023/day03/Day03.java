package year2023.day03;

import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Day03 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day03());
    }

    @Override
    public Object solveA(List<String> input) {
        var map = MapUtil.asMap(input);
        int result=0;
        for (int y=0; y<input.size(); y++) {
            int counter=0;
            boolean partNumber=false;
            for(int x=0; x<input.get(0).length(); x++) {
                if(isDigit(input.get(y).charAt(x))) {
                    Point p = new Point(x, y);
                    if ((map.containsKey(p.north()) && isSymbol(map.get(p.north()))) ||
                        (map.containsKey(p.east())  && isSymbol(map.get(p.east())))  ||
                        (map.containsKey(p.south()) && isSymbol(map.get(p.south()))) ||
                        (map.containsKey(p.west())  && isSymbol(map.get(p.west())))  ||
                        (map.containsKey(p.north().west()) && isSymbol(map.get(p.north().west()))) ||
                        (map.containsKey(p.north().east()) && isSymbol(map.get(p.north().east()))) ||
                        (map.containsKey(p.south().west()) && isSymbol(map.get(p.south().west()))) ||
                        (map.containsKey(p.south().east()) && isSymbol(map.get(p.south().east())))) {
                        partNumber=true;
                    }
                    counter*=10;
                    counter+=(input.get(y).charAt(x)-'0');

                } else {
                    if (partNumber && counter>0) result+=counter;
                    counter=0;
                    partNumber=false;
                }
            }
            if(partNumber)
                result+=counter;
        }
        return result;
    }


    private boolean isDigit(char c) {
        return (c-'0') >= 0 && (c-'0')<=9;
    }
    private boolean isSymbol(char c) {
        return c != '.' && !isDigit(c);
    }

    @Override
    public Object solveB(List<String> input) {
        var map = MapUtil.asMap(input);
        long result=0L;
        for (int y=0; y< input.size(); y++) {
            for (int x = 0; x < input.get(0).length(); x++) {
                if (input.get(y).charAt(x) == '*') {
                    Point p = new Point(x, y);
                    boolean n=false,e=false,s=false,w=false,nw=false,ne=false,sw=false,se=false;
                    if (map.containsKey(p.north()) && isDigit(map.get(p.north()))) n = true;
                    if (map.containsKey(p.east())  && isDigit(map.get(p.east())))  e = true;
                    if (map.containsKey(p.south()) && isDigit(map.get(p.south()))) s = true;
                    if (map.containsKey(p.west())  && isDigit(map.get(p.west())))  w = true;
                    if (!n) {
                        if (map.containsKey(p.north().west()) && isDigit(map.get(p.north().west()))) nw = true;
                        if (map.containsKey(p.north().east()) && isDigit(map.get(p.north().east()))) ne = true;
                    }
                    if (!s) {
                        if (map.containsKey(p.south().west()) && isDigit(map.get(p.south().west()))) sw = true;
                        if (map.containsKey(p.south().east()) && isDigit(map.get(p.south().east()))) se = true;
                    }
                    if (Stream.of(n, s, w, e, ne, nw, se, sw).filter(b -> b).count() == 2) {
                        int product = 1;
                        if (n)  product *= expandToNumber(p.north(), map);
                        if (e)  product *= expandToNumber(p.east(),  map);
                        if (w)  product *= expandToNumber(p.west(),  map);
                        if (s)  product *= expandToNumber(p.south(), map);
                        if (nw) product *= expandToNumber(p.north().west(), map);
                        if (ne) product *= expandToNumber(p.north().east(), map);
                        if (sw) product *= expandToNumber(p.south().west(), map);
                        if (se) product *= expandToNumber(p.south().east(), map);
                        result += product;
                    }
                }
            }
        }
        return result;
    }
    int expandToNumber(Point p, HashMap<Point, Character> map) {
        StringBuilder result = new StringBuilder("" + map.get(p));
        Point w = p.west();
        while(map.containsKey(w) && isDigit(map.get(w))) {
            result.insert(0, map.get(w));
            w=w.west();
        }
        Point e = p.east();
        while(map.containsKey(e) && isDigit(map.get(e))) {
            result.append(map.get(e));
            e=e.east();
        }
        return Integer.parseInt(result.toString());
    }
}
