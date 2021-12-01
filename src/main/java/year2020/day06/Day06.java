package year2020.day06;

import utils.FileHelper;
import utils.MapUtil;

import java.util.HashSet;
import java.util.List;

public class Day06 {

    static int solveA(List<String> values) {
        int total=0;
        StringBuilder groupResponse= new StringBuilder();
        for(String val : values) {
            if(val.isEmpty()) {
                total+=MapUtil.getLetterOccurrencesMap(groupResponse.toString()).size();
                groupResponse = new StringBuilder();
            } else {
                groupResponse.append(val);
            }
        }
        total+=MapUtil.getLetterOccurrencesMap(groupResponse.toString()).size();

        return total;
    }

    static int solveB(List<String> values) {
        int total=0;

        var responses = new HashSet<Character>();
        boolean newGroup=true;
        for(String val : values) {
            if(val.isEmpty()) {
                total+=responses.size();
                responses.clear();
                newGroup=true;
            } else {
                if (responses.isEmpty() && newGroup) {
                    responses.addAll(MapUtil.getLetterOccurrencesMap(val).keySet());
                    newGroup=false;
                }
                else
                    responses.retainAll(MapUtil.getLetterOccurrencesMap(val).keySet());
            }
        }
        total+=responses.size();

        return total;
    }

    public static void main(String[] args){
        var day = "Day06";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //6930
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //3585
    }
}
