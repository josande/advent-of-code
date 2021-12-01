package year2020.day11;

import utils.FileHelper;

import java.util.Arrays;
import java.util.List;

public class Day11 {


    static int solveA(List<String> values) {
        if ( values.isEmpty() ) return -1;
        var map = new char[values.size()][values.get(0).length()];

        for (int i=0; i<values.size() ; i++) {
            map[i]=values.get(i).toCharArray();
        }
        var newMap = makeNewMap(map);
        while(isDifferentMap(map, newMap)) {
            map = newMap;
            newMap = makeNewMap(map);
        }
        int occupiedSeats=0;
        for( var v : newMap) {
            for (char c : v ) {
                if (c == '#') occupiedSeats++;
            }
        }

        return occupiedSeats;
    }
    static char[][] makeNewMap(char[][] map) {
        int maxY = map.length;
        int maxX = map[0].length;
        var newMap = new char[maxY][maxX];

        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                if(map[y][x]=='L' && getPeopleNear(y,x,map)==0)
                    newMap[y][x]='#';
                else if(map[y][x]=='#' && getPeopleNear(y,x,map)>=4)
                    newMap[y][x]='L';
                else
                    newMap[y][x]=map[y][x];
            }
        }

        return newMap;
    }
    static char[][] makeNewMapB(char[][] map) {
        int maxY = map.length;
        int maxX = map[0].length;
        var newMap = new char[maxY][maxX];

        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                if(map[y][x]=='L' && getPeopleInSight(y,x,map)==0)
                    newMap[y][x]='#';
                else if(map[y][x]=='#' && getPeopleInSight(y,x,map)>=5)
                    newMap[y][x]='L';
                else
                    newMap[y][x]=map[y][x];
            }
        }
        return newMap;
    }
    public static boolean isDifferentMap(char[][] map1, char[][] map2) {
        if (map1 == null || map2 == null) return true;
        if (map1.length!=map2.length) return true;
        for (int i =0; i<map1.length; i++) {
            if(!Arrays.equals(map1[i], map2[i])) return true;
        }
        return false;

    }
    static int getPeopleNear(int y, int x, char[][] seatMap) {
        int pplNear=0, maxY=seatMap.length-1, maxX=seatMap[0].length-1;
        if(y>0 && x>0 &&    seatMap[y-1][x-1] =='#') pplNear++;
        if(y>0 &&           seatMap[y-1][x]   =='#') pplNear++;
        if(y>0 && x<maxX && seatMap[y-1][x+1] =='#') pplNear++;

        if(x>0 &&           seatMap[y]  [x-1] =='#') pplNear++;

        if(x<maxX &&        seatMap[y]  [x+1] =='#') pplNear++;

        if(y<maxY && x>0 && seatMap[y+1][x-1] =='#') pplNear++;
        if(y<maxY &&        seatMap[y+1][x]   =='#') pplNear++;
        if(y<maxY && x<maxX && seatMap[y+1][x+1] =='#') pplNear++;
        return pplNear;
    }
    public static int getPeopleInSight(int y, int x, char[][] seatMap) {
        int peopleInSight=0, maxY=seatMap.length-1, maxX=seatMap[0].length-1;

        if(canSeePersonInDirection(y,x,-1,-1, maxY, maxX,seatMap)) peopleInSight++;
        if(canSeePersonInDirection(y,x,-1,0,maxY, maxX,seatMap)) peopleInSight++;
        if(canSeePersonInDirection(y,x,-1,1,maxY, maxX,seatMap)) peopleInSight++;

        if(canSeePersonInDirection(y,x,0,-1,maxY, maxX,seatMap)) peopleInSight++;

        if(canSeePersonInDirection(y,x,0,1,maxY, maxX,seatMap)) peopleInSight++;

        if(canSeePersonInDirection(y,x,1,-1,maxY, maxX,seatMap)) peopleInSight++;
        if(canSeePersonInDirection(y,x,1,0,maxY, maxX,seatMap)) peopleInSight++;
        if(canSeePersonInDirection(y,x,1,1,maxY, maxX,seatMap)) peopleInSight++;
        return peopleInSight;
    }
    static boolean canSeePersonInDirection(int y, int x, int dy, int dx, int maxY, int maxX, char[][] seatMap) {
        while(y+dy<=maxY && y+dy>=0 &&
              x+dx<=maxX && x+dx>=0 ) {
            x+=dx;
            y+=dy;
            if(seatMap[y][x]=='#') return true;
            if(seatMap[y][x]=='L') return false;
        }
        return false;
    }

    static int solveB(List<String> values) {
        if ( values.isEmpty() ) return -1;
        var map = new char[values.size()][values.get(0).length()];

        for (int i=0; i<values.size() ; i++) {
            map[i]=values.get(i).toCharArray();
        }
        var newMap = makeNewMapB(map);
        while(isDifferentMap(map, newMap)) {
            map = newMap;
            newMap = makeNewMapB(map);
        }
        int occupiedSeats=0;
        for( var v : newMap) {
            for (char c : v ) {
                if (c == '#') occupiedSeats++;
            }
        }

        return occupiedSeats;
    }



    public static void main(String[] args){
        var day = "Day11";

        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //2281
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //2085
    }
}
