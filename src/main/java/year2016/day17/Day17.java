package year2016.day17;

import org.apache.commons.codec.digest.DigestUtils;
import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day17 {

    static Object solveA(List<String> values) {

        return getShortestPath(values.get(0));

    }
    static String getShortestPath(String input) {
        Queue<String> queue = new LinkedList<>();
        queue.add(input);

        while(!queue.isEmpty()) {
            String current = queue.poll();
            if (isTarget(current)) {
                return current.substring(input.length());
            }
            if (isOutOfRange(current)) {
                continue;
            }
            queue.addAll(getPaths(current));
        }
        return null;
    }
    static boolean isTarget(String path) {
        Point p = getPosition(path);

        return p.getX()==3 && p.getY()==3;
    }
    static boolean isOutOfRange(String path) {
        Point p = getPosition(path);
        return p.getX()<0 || p.getY()<0 || p.getX()>3 ||p.getY()>3;
    }
    private static Point getPosition(String path) {
        int x=0, y=0;
        for (char c : path.toCharArray()) {
            if (c=='D') {
                y++;
            }
            if (c=='U') {
                y--;
            }
            if (c=='R') {
                x++;
            }
            if (c=='L') {
                x--;
            }
        }
        return new Point(x,y);
    }
    static ArrayList<String> getPaths(String input) {
        String open = "bcdef";
        String hash = DigestUtils.md5Hex(input).substring(0,4);
        ArrayList<String> paths = new ArrayList<>();

        if(open.contains(""+hash.charAt(0))) {
            paths.add(input+"U");
        }
        if(open.contains(""+hash.charAt(1))) {
            paths.add(input+"D");
        }
        if(open.contains(""+hash.charAt(2))) {
            paths.add(input+"L");
        }
        if(open.contains(""+hash.charAt(3))) {
            paths.add(input+"R");
        }

        return paths;
    }
    static Object solveB(List<String> values) {
        return getLongestPath(values.get(0));
    }

    static int getLongestPath(String input) {
        Queue<String> queue = new LinkedList<>();
        queue.add(input);
        int longestPath = 0;
        while(!queue.isEmpty()) {
            String current = queue.poll();
            if (isTarget(current)) {
                longestPath=Math.max(longestPath, current.length()-input.length());
                continue;
            }
            if (isOutOfRange(current)) {
                continue;
            }
            queue.addAll(getPaths(current));
        }
        return longestPath;
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // DDRUDLRRRD
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 398
    }
}
