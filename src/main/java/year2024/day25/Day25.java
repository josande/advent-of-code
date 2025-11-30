package year2024.day25;


import utils.AdventOfCode;
import utils.Reporter;

import java.util.HashSet;
import java.util.List;

public class Day25 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day25());
    }

    @Override
    public Object solveA(List<String> input) {
        HashSet<int[]> keys = new HashSet<>();
        HashSet<int[]> locks = new HashSet<>();
        for(int i = 0; i < input.size(); i+=8){
           if(input.get(i).equals("#####")) {
                int[] lock = new int[5];
                for(int tumbler=0; tumbler<5; tumbler++){
                    for(int height=1; height<6; height++){
                        if(input.get(i+height).charAt(tumbler) == '#') {lock[tumbler]++;}
                    }
                }
                locks.add(lock);
            } else {
                int[] key = new int[5];
                for(int tumbler=0; tumbler<5; tumbler++){
                    for(int height=5; height>0; height--){
                        if(input.get(i+height).charAt(tumbler) == '#') {key[tumbler]++;}
                    }
                }
                keys.add(key);
            }
        }
        int result=0;
        for(var key:keys) {
            for(var lock:locks) {
                result+=testKey(key, lock);
            }
        }
        return result;
    }

    private int testKey(int[] key, int[] lock) {
        for(int tumbler=0; tumbler<5; tumbler++){
            if(key[tumbler] + lock[tumbler] >= 6) { return 0;}
        }
        return 1;
    }

    @Override
    public Object solveB(List<String> input) {
        return "Merry X-mas!";
    }
}
