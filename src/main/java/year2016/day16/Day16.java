package year2016.day16;

import org.apache.commons.lang3.StringUtils;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day16 {

    static String curve(String input) {
        String b = input;
        b = StringUtils.reverse(b);
        StringBuilder sb = new StringBuilder(input);
        sb.append("0");
        for(char c : b.toCharArray()) {
            if(c=='0')
                sb.append("1");
            else
                sb.append("0");
        }
        return sb.toString();
    }

    static String fillDisk(String input, int diskSize) {
        String temp = curve(input);
        while(temp.length()<diskSize) {
            temp = curve(temp);
        }
        return temp.substring(0,diskSize);
    }

    static Object solveA(List<String> values) {
        int diskLength=272;
        String diskContent=fillDisk(values.get(0), diskLength);
        return makeChecksum(diskContent);
    }
    static Object solveB(List<String> values) {
        int diskLength=35651584;
        String diskContent=fillDisk(values.get(0), diskLength);
        return makeChecksum(diskContent);
    }

    public static String makeChecksum(String input) {
        String checkSum=input;
        while(checkSum.length()%2==0) {
            StringBuilder sb=new StringBuilder();
            for(int i=0; i<checkSum.length()-1; i+=2) {

                if(checkSum.charAt(i) == checkSum.charAt(i+1)) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            checkSum= sb.toString();

        }
        return checkSum;
    }

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2016/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 10100011010101011
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 01010001101011001
    }
}
