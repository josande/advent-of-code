package year2021.day20;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;

public class Day20 {

    static Object solveA(List<String> values) {

        String enhancementAlgorithm = values.get(0);

        HashMap<Point, Character> image = new HashMap<>();

        for(int y = 2; y < values.size(); y++) {
            for(int x = 0; x<values.get(y).length(); x++) {
                image.put(new Point(x,y-1), values.get(y).charAt(x));
            }
        }

        int minX = -10;
        int maxX = values.get(2).length()+10;
        int minY = -10;
        int maxY = values.size()-2+10;
        int repeatTimes = 2;

        for(int times=0; times<repeatTimes; times++) {
            HashMap<Point, Character> newImage = new HashMap<>();

            for(int y=minY-20; y<=maxY+20; y++) {
                for(int x=minX-20; x<=maxX+20; x++) {
                    Point p = new Point(x, y);
                    int value = asBinaryNumber(p, image);
                    Character newValue = enhancementAlgorithm.charAt(value);
                    if(newValue != null) {
                        newImage.put(p, newValue);
                    }
                }
            }
            minX-=1;
            minY-=1;
            maxY+=1;
            maxY+=1;
            image = newImage;

        }

        HashMap<Point, Character> newImage = new HashMap<>();

        for(int y=-repeatTimes; y<=maxY+repeatTimes; y++) {
            for(int x=-repeatTimes; x<=maxX+repeatTimes; x++) {
                Point p = new Point(x,y);
                newImage.put(p, image.get(p));
            }
        }
        image = newImage;

        return image.values().stream().filter(c->c!=null && c=='#').count();
    }

    static int asBinaryNumber(Point point, HashMap<Point, Character> image) {

        String mapString = "";
        int x = point.getX();
        int y = point.getY();
        mapString += image.getOrDefault(new Point(x-1,y-1), '.');
        mapString += image.getOrDefault(new Point(x,y-1), '.');
        mapString += image.getOrDefault(new Point(x+1,y-1), '.');

        mapString += image.getOrDefault(new Point(x-1,y), '.');
        mapString += image.getOrDefault(new Point(x,y), '.');
        mapString += image.getOrDefault(new Point(x+1,y), '.');

        mapString += image.getOrDefault(new Point(x-1,y+1), '.');
        mapString += image.getOrDefault(new Point(x,y+1), '.');
        mapString += image.getOrDefault(new Point(x+1,y+1), '.');

        mapString = mapString.replaceAll("\\.", "0").replaceAll("#", "1");
        int value = Integer.parseInt(mapString,2);

        return value;

    }

    static Object solveB(List<String> values) {

        String enhancementAlgorithm = values.get(0);

        HashMap<Point, Character> image = new HashMap<>();

        for(int y = 2; y < values.size(); y++) {
            for(int x = 0; x<values.get(y).length(); x++) {
                image.put(new Point(x,y-1), values.get(y).charAt(x));
            }
        }

        int repeatTimes = 50;

        int minX = -10;
        int maxX = values.get(2).length()+10;
        int minY = -10;
        int maxY = values.size()-2+10;

        for(int times=0; times<repeatTimes; times++) {
            HashMap<Point, Character> newImage = new HashMap<>();

            for(int y=minY-(2*repeatTimes); y<=maxY+2*repeatTimes; y++) {
                for(int x=minX-(2*repeatTimes); x<=maxX+2*repeatTimes; x++) {
                    Point p = new Point(x, y);
                    Integer value = asBinaryNumber(p, image);
                    Character newValue = enhancementAlgorithm.charAt(value);
                    newImage.put(p, newValue);
                }
            }
            image = newImage;
        }

        HashMap<Point, Character> newImage = new HashMap<>();
        for(int y=-repeatTimes; y<=maxY+repeatTimes; y++) {
            for(int x=-repeatTimes; x<=maxX+repeatTimes; x++) {
                Point p = new Point(x,y);
                newImage.put(p, image.getOrDefault(p, '.'));
            }
        }
        image = newImage;

        return image.values().stream().filter(c->c!=null && c=='#').count();
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1 - t0;
        var timePart2 = System.currentTimeMillis() - t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 5663
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 19638
    }
}
