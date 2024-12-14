package year2024.day13;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.List;

public class Day13 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day13());
    }
    private record Game (int ax, int ay, int bx, int by, int prizeX, int prizeY){
        long getCost(long maxClick, Long offset){
            double goalX = prizeX+offset;
            double goalY = prizeY+offset;
            double aX =  ax;
            double aY =  ay;
            double b = ((goalY/aY)-(goalX/aX))/((by/aY)-(bx/aX));
            long bClick = (long) (b+0.5d);
            long xRest = (long) (goalX-bClick*bx);
            long aClick = xRest/ax;
            if(aClick*ax + bClick*bx == goalX && aClick*ay + bClick*by == goalY) {
                if(maxClick>=0 && (aClick>maxClick || bClick>maxClick))
                    return 0;
                return 3*aClick + bClick;
            }
            return 0;
        }
    }

    @Override
    public Object solveA(List<String> input) {

        long totaltCost = 0L;
        for(int i = 0; i < input.size(); i+=4){
            Game game = getGame(input.get(i), input.get(i+1), input.get(i+2));
            long gameCost = game.getCost(100L, 0L);

            totaltCost+=gameCost;
        }
        return totaltCost; //29388
    }

    @Override
    public Object solveB(List<String> input) {

        long totaltCost = 0;
        for(int i = 0; i < input.size(); i+=4){
            Game game = getGame(input.get(i), input.get(i+1), input.get(i+2));
            long gameCost = game.getCost(-1L, 10000000000000L);
            totaltCost+=gameCost;
        }
        return totaltCost;// 99548032866004
    }

    private static Game getGame(String aRow, String bRow, String prizeRow) {
        int ax = Integer.parseInt(aRow.split(" ")[2].split(",")[0].substring(2));
        int ay = Integer.parseInt(aRow.split(" ")[3].substring(2));

        int bx = Integer.parseInt(bRow.split(" ")[2].split(",")[0].substring(2));
        int by = Integer.parseInt(bRow.split(" ")[3].substring(2));

        int prizeX = Integer.parseInt(prizeRow.split(" ")[1].split(",")[0].substring(2));
        int prizeY = Integer.parseInt(prizeRow.split(" ")[2].substring(2));
        return new Game(ax, ay, bx, by, prizeX, prizeY);
    }
}
