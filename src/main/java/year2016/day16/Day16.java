package year2016.day16;

import org.apache.commons.lang3.StringUtils;
import utils.AdventOfCode;
import utils.FileHelper;
import utils.Reporter;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day16 implements AdventOfCode {

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

    @Override
    public Object solveA(List<String> values) {
        int diskLength=272;
        String diskContent=fillDisk(values.get(0), diskLength);
        return makeChecksum(diskContent);
    }
    @Override
    public Object solveB(List<String> values) {
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

    public static void main(){
        Reporter.report(new Day16());
    }
}
