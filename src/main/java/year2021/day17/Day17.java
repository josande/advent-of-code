package year2021.day17;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Day17 {

    static Object solveA(List<String> values) {
        String input = values.getFirst();
        String xRange = input.substring(input.indexOf("=")+1, input.indexOf(","));
        String yRange = input.substring(input.lastIndexOf("=")+1);


        int xMin = Integer.parseInt(xRange.split("\\.\\.")[0]);
        int xMax = Integer.parseInt(xRange.split("\\.\\.")[1]);
        int yMin = Integer.parseInt(yRange.split("\\.\\.")[0]);
        int yMax = Integer.parseInt(yRange.split("\\.\\.")[1]);

        int highestPosition=0;

        for(int y = yMax; y<100; y++) {
            for(int x=0; x<=xMax; x++) {
                if (isHit(x,y, xMin, xMax, yMin, yMax)) {
                    highestPosition = getMaxHeight(y);
                }
            }
        }

        return highestPosition;
    }

    static boolean isHit(int x, int y, int xMin, int xMax, int yMin, int yMax) {
        int velX=x, velY=y;
        int posX=0, posY=0;
        for(;;) {
            posX+=velX;
            posY+=velY;
            if(xMin <= posX && posX <= xMax &&
               yMin <= posY && posY <= yMax ) {
                return true;
            }
            if(posX>xMax || posY < yMin) {
                return false;
            }
            velX = Math.max(0, velX-1);
            velY-=1;
        }
    }
    static int getMaxHeight(int y) {
        int currY = 0;
        int velY=y;
        while(velY>0) {
            currY+=velY;
            velY--;
        }
        return currY;
    }
    static Object solveB(List<String> values) {

        String input = values.getFirst();
        String xRange = input.substring(input.indexOf("=")+1, input.indexOf(","));
        String yRange = input.substring(input.lastIndexOf("=")+1);


        int xMin = Integer.parseInt(xRange.split("\\.\\.")[0]);
        int xMax = Integer.parseInt(xRange.split("\\.\\.")[1]);
        int yMin = Integer.parseInt(yRange.split("\\.\\.")[0]);
        int yMax = Integer.parseInt(yRange.split("\\.\\.")[1]);

        int possibilities=0;

        for(int y = yMin; y<100; y++) {
            for(int x=0; x<=xMax; x++) {
                if (isHit(x,y, xMin, xMax, yMin, yMax)) {
                    possibilities++;
                }
            }
        }
        return possibilities;
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //4186
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
