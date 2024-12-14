package year2024.day14;

import utils.AdventOfCode;
import utils.MapUtil;
import utils.Point;
import utils.Reporter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day14 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day14());
    }

    public void setRoomHeight(int roomHeight) {
        this.roomHeight = roomHeight;
    }

    public void setRoomWidth(int roomWidth) {
        this.roomWidth = roomWidth;
    }

    private record Robot(int x, int y, int vx, int vy){
        private int getQuadrant(int width, int height){
            int finalX =(width + x + vx*100%width)%width;
            int finalY = (height + y + vy*100%height)%height;

            if(finalX<width/2 && finalY<height/2) return 1;
            if(finalX<width/2 && finalY>height/2) return 2;
            if(finalX>width/2 && finalY<height/2) return 3;
            if(finalX>width/2 && finalY>height/2) return 4;
            return 0;
        }
        Point positionAtTime(int width, int height, int time) {
            int finalX =(width + x + vx*time%width)%width;
            int finalY = (height + y + vy*time%height)%height;
            return new Point(finalX, finalY);
        }
    }
    private int roomHeight = 103;
    private int roomWidth = 101;
    @Override
    public Object solveA(List<String> input) {
        ArrayList<Robot> robots = new ArrayList<>();
        for(var line : input) {
            int robotX = Integer.parseInt(line.split(",")[0].substring(2));
            int robotY = Integer.parseInt(line.split(",")[1].split(" ")[0]);
            int velX = Integer.parseInt(line.split("=")[2].split(",")[0]);
            int velY = Integer.parseInt(line.split(",")[2]);
            robots.add(new Robot(robotX, robotY, velX, velY));
        }
        int quad1=0;
        int quad2=0;
        int quad3=0;
        int quad4=0;
        for(Robot robot : robots) {
            switch (robot.getQuadrant(roomWidth, roomHeight)) {
                case 1 -> quad1++;
                case 2 -> quad2++;
                case 3 -> quad3++;
                case 4 -> quad4++;
                default -> {}
            }
        }

        return quad1*quad2*quad3*quad4;
    }

    @Override
    public Object solveB(List<String> input) {

        ArrayList<Robot> robots = new ArrayList<>();
        for(var line : input) {
            int robotX = Integer.parseInt(line.split(",")[0].substring(2));
            int robotY = Integer.parseInt(line.split(",")[1].split(" ")[0]);
            int velX = Integer.parseInt(line.split("=")[2].split(",")[0]);
            int velY = Integer.parseInt(line.split(",")[2]);
            robots.add(new Robot(robotX, robotY, velX, velY));
        }
        for(int time=0; ; time++) {
            int finalTime = time;
            Set<Point> points = robots.stream().map(r -> r.positionAtTime(roomWidth, roomHeight, finalTime)).collect(Collectors.toSet());
            long maxPerRow=0;
            long maxPerCol=0;
            int cutOff = 30;
            for(int y=0; y<roomHeight; y++) {
                int finalY = y;
                maxPerRow=Math.max(maxPerRow, points.stream().filter(p->p.getY()== finalY).count());
            }
            if(maxPerRow<cutOff) continue;
            for(int x=0; x<roomWidth; x++) {
                int finalX = x;
                maxPerCol=Math.max(maxPerCol, points.stream().filter(p->p.getX()== finalX).count());
            }
            if(maxPerCol<cutOff) continue;

            return time;
        }
    }
}
