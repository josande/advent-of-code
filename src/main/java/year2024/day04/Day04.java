package year2024.day04;

import utils.*;

import java.util.HashMap;
import java.util.List;

public class Day04 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day04());
    }

    @Override
    public Object solveA(List<String> input) {
        HashMap<Point,Character> map =  MapUtil.asMap(input);

        int matches=0;

        char[][] letters = new char[input.size()][input.get(0).length()];
        for(int y=0;y<input.size();y++){
            char[] chars = input.get(y).toCharArray();
            for(int x=0;x<chars.length;x++){
                letters[y][x] = chars[x];
            }
        }
        for(int y=0;y<letters.length;y++){
            for(int x=0;x<letters[y].length;x++){
                matches+=findMatches(y,x, letters);
            }
        }
        return matches;
    }

    private Integer findMatches(int y, int x, char[][] letters) {
        int matches = 0;

        matches+=check(x,y, -1,-1, letters)?1:0;
        matches+=check(x,y,-1,0, letters)?1:0;
        matches+=check(x,y, -1,1, letters)?1:0;

        matches+=check(x,y,0, -1, letters)?1:0;
        matches+=check(x,y, 0,1, letters)?1:0;

        matches+=check(x,y, 1,-1, letters)?1:0;
        matches+=check(x,y, 1,0, letters)?1:0;
        matches+=check(x,y, 1,1, letters)?1:0;

        return matches;
    }

    private boolean check(int x, int y, int dx, int dy, char[][] letters) {
        char[] searchFor = "XMAS".toCharArray();

        for (char c : searchFor) {
            if(x<0 || x>=letters[0].length || y<0 || y>=letters.length || letters[y][x] != c) return false;
            x+=dx;
            y+=dy;
        }
        return true;
    }



    @Override
    public Object solveB(List<String> input) {
        int matches=0;

        char[][] letters = new char[input.size()][input.get(0).length()];
        for(int y=0;y<input.size();y++){
            char[] chars = input.get(y).toCharArray();
            for(int x=0;x<chars.length;x++){
                letters[y][x] = chars[x];
            }
        }
        for(int y=0;y<letters.length;y++){
            for(int x=0;x<letters[y].length;x++){
                matches+=isMasCross(y,x, letters)?1:0;
            }
        }
        return matches;
    }
    private boolean isMasCross(int x, int y, char[][] strings) {
        if(x<1 || x>=strings[0].length-1 || y<1 || y>=strings.length-1 || strings[y][x] != 'A') return false;
        char nw = strings[y-1][x-1];
        char ne = strings[y-1][x+1];
        char sw = strings[y+1][x-1];
        char se = strings[y+1][x+1];

        if(nw == 'M' && ne == 'M' && sw == 'S' && se == 'S') return true;
        if(ne == 'M' && se == 'M' && nw == 'S' && sw == 'S') return true;
        if(sw == 'M' && se == 'M' && nw == 'S' && ne == 'S') return true;
        return (nw == 'M' && sw == 'M' && ne == 'S' && se == 'S');
    }
}
