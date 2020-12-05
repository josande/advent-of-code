package year2016.day04;

import utils.FileHelper;

import java.util.*;

import static utils.MapUtil.getLetterOccurrencesMap;

public class Day04 {

    static int solveA(List<String> values) {
        int result=0;

        for(var value : values) {
            if (isValidRoom(value))
                result+=Integer.valueOf(value.replaceAll("\\D", ""));
        }

        return result;
    }

    static boolean isValidRoom(String roomCode) {
        String roomName = roomCode.substring(0,roomCode.lastIndexOf("-")).replaceAll("-","");
        String checkSum = roomCode.substring(roomCode.indexOf("[")+1, roomCode.lastIndexOf("]"));
        HashMap<Character, Integer> map = getLetterOccurrencesMap(roomName);
        var list = new ArrayList<Occurance>();
        for (var e : map.entrySet()) {
            list.add(new Occurance(e.getKey(), e.getValue()));
        }
        Collections.sort(list);
        for(int i = 0; i<5; i++) {
            if (checkSum.charAt(i) != list.get(i).c) return false;
        }
        return true;
    }
    private static class Occurance implements Comparable<Occurance> {
        char c;
        int i;
        Occurance(char c, int i) {
            this.c=c;
            this.i=i;
        }

        @Override
        public int compareTo(Occurance o) {
            if(this.i == o.i) return c-o.c;
            return o.i-i;

        }
    }


    static int solveB(List<String> values) {
        int result=0;

        for(var value : values) {
            if(isValidRoom(value) && isNorthPoleObjectStorage(value)) {
                return Integer.valueOf(value.replaceAll("\\D", ""));
            }
        }

        return result;
    }
    static boolean isNorthPoleObjectStorage(String roomCode) {
        String roomName = roomCode.substring(0,roomCode.lastIndexOf("-")).replaceAll("-"," ");
        int sectorId=Integer.valueOf(roomCode.replaceAll("\\D", ""));
        var asChars = roomName.toCharArray();
         for(int steps = 0; steps<sectorId; steps++) {
             for (int i = 0; i < asChars.length; i++) {
                 if(asChars[i] == 'z') {
                     asChars[i]='a';
                 } else if (asChars[i] == ' ') {

                 } else {
                     char c = asChars[i];
                     c++;
                     asChars[i] = c;
                 }
             }
         }
        roomName= String.valueOf(asChars);

        return "northpole object storage".equals(roomName);
    }


    public static void main(String[] args){
        var day = "Day04";

        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = System.currentTimeMillis()-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //173787
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //548
    }
}
