package year2021.day25;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day25 {

    static Object solveA(List<String> values) {

        int maxY= values.size()-1;
        int maxX= values.get(0).length()-1;

        HashMap<Point, Character> map = new HashMap<>();
        for(int y=0; y<=maxY; y++){
            for(int x=0; x<=maxX; x++){
                map.put(new Point(x,y), values.get(y).charAt(x));
            }
        }
        Point.print(map);

        int steps=0;
        HashMap<Point, Character> nextMap = iterate(map,maxY,maxX);

        while (!map.equals(nextMap)) {
            steps++;
            map=nextMap;
            nextMap=iterate(map, maxY, maxX);
         //   System.out.println("\nAfter "+steps+" steps\n");
         //   Point.print(map);
         //   System.out.println("");
        }


        return steps+1;
    }

    private static HashMap<Point, Character> iterate(HashMap<Point, Character> map, int maxY, int maxX) {
        HashMap<Point, Character> nextMap=new HashMap<>();

        for(Point p : map.keySet()) {
            nextMap.put(p, '.');
        }

        for (Map.Entry<Point, Character> e : map.entrySet()) {
            switch (e.getValue()) {
                case '>' -> {
                    Point target = e.getKey().east();
                    target.setX(target.getX()%(maxX+1));
                    if(map.get(target) == '.') nextMap.put(target, '>'); else nextMap.put(e.getKey(), '>');
                }

            }
        }

        for (Map.Entry<Point, Character> e : map.entrySet()) {
            switch (e.getValue()) {
                case 'v' -> {
                    Point target = e.getKey().south();
                    target.setY(target.getY()%(maxY+1));
                    if((nextMap.get(target) == '.') && !(map.get(target) == 'v')) nextMap.put(target, 'v'); else nextMap.put(e.getKey(), 'v');}
            }
        }

        return nextMap;
    }


    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = t1-t0;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //
    }
}
