package year2016.day04;

import utils.AdventOfCode;
import utils.FileHelper;
import utils.Reporter;

import java.util.*;

import static utils.MapUtil.getLetterOccurrencesMap;

public class Day04 implements AdventOfCode {

    @Override
    public Object solveA(List<String> values) {
        int result=0;

        for(var value : values) {
            if (isValidRoom(value))
                result+=Integer.parseInt(value.replaceAll("\\D", ""));
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

    @Override
    public Object solveB(List<String> values) {
        int result=0;

        for(var value : values) {
            if(isValidRoom(value) && isNorthPoleObjectStorage(value)) {
                return Integer.parseInt(value.replaceAll("\\D", ""));
            }
        }

        return result;
    }
    static boolean isNorthPoleObjectStorage(String roomCode) {
        return "northpole object storage".equals(getRoomName(roomCode));
    }
    static String getRoomName(String roomCode) {
        String roomName = roomCode.substring(0,roomCode.lastIndexOf("-")).replaceAll("-"," ");
        int sectorId=Integer.parseInt(roomCode.replaceAll("\\D", ""));
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
        return String.valueOf(asChars);
    }

    public static void main(){
        Reporter.report(new Day04());
    }
}
