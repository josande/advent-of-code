package year2023.day13;

import utils.AdventOfCode;
import utils.Reporter;
import java.util.ArrayList;
import java.util.List;

public class Day13 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day13());
    }

    @Override
    public Object solveA(List<String> input) {
        ArrayList<ArrayList<String>> maps = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        for(String line : input) {
            if (line.isEmpty()) {
                maps.add(temp);
                temp = new ArrayList<>();
            } else {
                temp.add(line);
            }
        }
        maps.add(temp);

        int result=0;
        for(ArrayList<String> map : maps) {
            int x = checkMirror(map);
            int y = checkMirror(rotate(map));
            result+= 100 * x + y;
        }

        return result;
    }

    private int checkMirror (ArrayList<String> map) {
        for (int i = 1; i < map.size(); i++ ) {
            if(map.get(i).equals(map.get(i-1))) {

                int left = Math.min(i, map.size()-i);
                boolean mirror=true;
                for (int j = 1; j<left; j++) {
                    if(!map.get(i-j-1).equals(map.get(i+j))) {
                        mirror=false;
                        break;
                    }
                }
                if(mirror) return i;
            }
        }
        return 0;
    }


    private ArrayList<String> rotate(ArrayList<String> map) {
        ArrayList<String> rotatedList=new ArrayList<>();
        for(int i=0; i<map.get(0).length(); i++) {
            StringBuilder tmp= new StringBuilder();
            for(String s : map) {
                tmp.append(s.charAt(i));
            }
            rotatedList.add(tmp.toString());
        }
        return rotatedList;
    }
    @Override
    public Object solveB(List<String> input) {
        ArrayList<ArrayList<String>> maps = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        for(String line : input) {
            if (line.isEmpty()) {
                maps.add(temp);
                temp = new ArrayList<>();
            } else {
                temp.add(line);
            }
        }
        maps.add(temp);

        int result=0;
        for(ArrayList<String> map : maps) {
            int x = checkWithSmudge(map);
            int y = checkWithSmudge(rotate(map));
            result+= 100 * x + y;
        }

        return result;
    }
    int checkWithSmudge(ArrayList<String> map) {
        for (int i=1; i<map.size(); i++) {
            int left = Math.min(i, map.size()-i);

            int errors=0;
            for (int j = 0; j<left; j++) {
                errors+=numberOfDifferences(map.get(i-j-1), map.get(i+j));
            }
            if(errors ==1) return i;
        }
        return 0;
    }
    private int numberOfDifferences(String a, String b) {
        if(a.length() != b.length()) throw new IllegalArgumentException("Wrong length!");
        int differnece =0;
        for(int i=0; i<a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) differnece++;
        }
        return differnece;
    }
}
