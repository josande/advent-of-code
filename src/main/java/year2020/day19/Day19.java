package year2020.day19;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;


public class Day19 {


    static int solveA(List<String> values) {
        int okRowCount=0;
        LinkedList<String> rules = new LinkedList<>();
        ArrayList<String> inStrings = new ArrayList<>();
        for ( var inRow : values) {
            if(inRow.contains(":")) {
                rules.add(inRow);

            } else if (!inRow.isEmpty()) {
                inStrings.add(inRow);
            }
        }

        String regExRule = getRuleSetAsRegEx(rules);

        for(String row : inStrings)
            if(row.matches(regExRule))
               okRowCount++;


        return okRowCount;
    }

    private static String getRuleSetAsRegEx(LinkedList<String> rules) {
        HashMap<Integer, String> orderedRules = new HashMap<>();
        for(var rule : rules ) {
            int id = Integer.parseInt(rule.split(":")[0]);
            String content = " " + rule.split(":")[1]+" ";
            orderedRules.put(id, content);
        }
        List<Integer> handledRules=new ArrayList<>();
        while(orderedRules.get(0).matches(".*\\d.*")) {
            for (var rule : orderedRules.entrySet()) {
                if (!rule.getValue().matches(".*\\d.*")) {
                    String replaceWith = rule.getValue().replaceAll("\"", "");
                    if (replaceWith.contains("|")) {
                        replaceWith = " ( " + replaceWith + " ) ";
                    } else {
                        replaceWith = " " + replaceWith + " ";

                    }
                    handledRules.add(rule.getKey());

                    for (var r : orderedRules.entrySet()) {
                        if (r.getKey().equals(rule.getKey())) continue;
                        String tmp = r.getValue();
                        while(tmp.contains(" "+rule.getKey()+" ")) {
                            tmp = tmp.replace(" " + rule.getKey()+" ", replaceWith);
                        }
                        r.setValue(tmp);
                    }
                }
            }
            for (int id : handledRules)
                orderedRules.remove(id);
            handledRules = new ArrayList<>();
        }
        return orderedRules.get(0).replaceAll(" ","");
    }

    private static String getRuleSetAsRegExB(LinkedList<String> rules) {
        HashMap<Integer, String> orderedRules = new HashMap<>();
        for(var rule : rules ) {
            int id = Integer.parseInt(rule.split(":")[0]);
            String content = " " + rule.split(":")[1]+" ";
            orderedRules.put(id, content);
        }

        orderedRules.put(8, " ( 42 )+ ");
        orderedRules.put(11, " ( 42 31 ) | 42 ( 42 31 )+ 31  | 42 31 " +
                                                            "| 42 42 31 31 " +
                                                            "| 42 42 42 31 31 31 " +
                                                            "| 42 42 42 42 31 31 31 31 " +
                                                            "| 42 42 42 42 42 31 31 31 31 31 " +
                                                            "| 42 42 42 42 42 42 31 31 31 31 31 31 " +
                                                            "| 42 42 42 42 42 42 42 31 31 31 31 31 31 31 " +
                                                            "| 42 42 42 42 42 42 42 42 31 31 31 31 31 31 31 31 ");
        List<Integer> handledRules=new ArrayList<>();
        while(orderedRules.get(0).matches(".*\\d.*")) {
            for (var rule : orderedRules.entrySet()) {
                if (!rule.getValue().matches(".*\\d.*")) {
                    String replaceWith = rule.getValue().replaceAll("\"", "");
                    if (replaceWith.contains("|")) {
                        replaceWith = " ( " + replaceWith + " ) ";
                    } else {
                        replaceWith = " " + replaceWith + " ";

                    }
                    handledRules.add(rule.getKey());

                    for (var r : orderedRules.entrySet()) {
                        if (r.getKey().equals(rule.getKey())) continue;
                        String tmp = r.getValue();
                        while(tmp.contains(" "+rule.getKey()+" ")) {
                            tmp = tmp.replace(" " + rule.getKey()+" ", replaceWith);
                        }
                        r.setValue(tmp);
                    }
                }
            }
            for (int id : handledRules)
                orderedRules.remove(id);
            handledRules = new ArrayList<>();
        }
        return orderedRules.get(0).replaceAll(" ","");
    }


    static long solveB(List<String> values) {
        int okRowCount=0;
        LinkedList<String> rules = new LinkedList<>();
        ArrayList<String> inStrings = new ArrayList<>();
        for ( var inRow : values) {
            if(inRow.contains(":")) {
                rules.add(inRow);

            } else if (!inRow.isEmpty()) {
                inStrings.add(inRow);
            }
        }

        String regExRule = getRuleSetAsRegExB(rules);

        for(String row : inStrings)
            if(row.matches(regExRule))
                okRowCount++;


        return okRowCount;

    }


    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //111
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //343
    }
}
