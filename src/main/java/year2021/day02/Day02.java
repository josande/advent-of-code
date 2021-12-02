package year2021.day02;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day02 {

    static int solveA(List<String> values) {
        int horizontal=0, depth=0;
        for(var val : values) {
            String command = val.split(" ")[0];
            int length = Integer.valueOf(val.split(" ")[1]);
            switch (command) {
                case "forward" : {horizontal+=length; break;}
                case "down" : {depth+=length; break;}
                case "up" : {depth-=length; break;}
                default:
                    System.out.println("Somehting wrong with "+command);
            }

        }

        return horizontal*depth;
    }
    static int solveB(List<String> values) {
        int aim = 0, horizontal=0,depth = 0;
        for (var val : values) {
            String command = val.split(" ")[0];
            int length = Integer.valueOf(val.split(" ")[1]);
            switch (command) {
                case "forward": {
                    depth += aim * length;
                    horizontal+=length;
                    break;
                }
                case "down": {
                    aim += length;
                    break;
                }
                case "up": {
                    aim -= length;
                    break;
                }
                default:
                    System.out.println("Somehting wrong with " + command);
            }
        }

        return horizontal * depth;
    }

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //2215080
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //1864715580
    }
}
