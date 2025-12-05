package year2025.day02;

import lombok.val;
import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

public class Day02 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day02());
    }

    @Override
    public Object solveA(List<String> input) {
        String[] parts= input.getFirst().split(",");
        long result =0L;
        for(String part : parts) {
            val values = part.split("-");
            long start = Long.parseLong(values[0]);
            long end =   Long.parseLong(values[1]);
            for(long i=start; i<=end;i++) {
               String value = String.valueOf(i);
               if(value.length() % 2 == 1) continue;
               if(value.substring(0, value.length()/2).equals(value.substring(value.length()/2))) {
                   result+=i;
               }
            }
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        String[] parts= input.getFirst().split(",");
        long result =0L;
        for(String part : parts) {
            val values = part.split("-");
            long start = Long.parseLong(values[0]);
            long end =   Long.parseLong(values[1]);
            for(long i=start; i<=end; i++) {
                String id = String.valueOf(i);
                for(int len = 1; len<=id.length()/2; len++) {
                    if(id.length()%len!=0) continue;
                    boolean valid=false;
                    String subString = id.substring(0, len);
                    for(int time=1; time<id.length()/len; time++) {
                        var partValue = id.substring(time*len, (time+1)*len);
                        if(!partValue.equals(subString)) {
                            valid=true;
                            break;
                        }
                    }
                    if(!valid) {
                        result+=i;
                        break;
                    }
                }
            }
        }
        return result;
    }
}