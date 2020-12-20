package year2020.day20;

import org.apache.commons.lang3.StringUtils;
import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day20 {


    static long solveA(List<String> values) {
        HashMap<Integer, List<String>> tiles = new HashMap<>();
        for(int i=0; i<values.size(); i++) {
            String row1;
            StringBuilder row2= new StringBuilder();
            StringBuilder row3= new StringBuilder();
            String row4="";
            if(values.get(i).startsWith("Tile ")) {
                int currentId=Integer.parseInt(values.get(i).split("Tile ")[1].split(":")[0]);
                row1=values.get(i+1);
                for( int j=0 ; j<row1.length(); j++) {
                    row2.append(values.get(i + j + 1).charAt(0));
                    row3.append(values.get(i + j + 1).charAt(row1.length() - 1));
                    row4=values.get(i+j+1);
                }
                String row1b = StringUtils.reverse(row1);
                String row2b = StringUtils.reverse(row2.toString());
                String row3b = StringUtils.reverse(row3.toString());
                String row4b = StringUtils.reverse(row4);
                tiles.put(currentId, Arrays.asList(row1, row2.toString(), row3.toString(), row4, row1b, row2b, row3b, row4b));

                i+=row1.length();

            }

        }
        long result=1;
        for(var tile : tiles.entrySet()) {
            int counts=0;
            for(var oTile : tiles.entrySet()) {
                if (tile.getKey().equals(oTile.getKey())) continue;
                List<String> remains = new ArrayList<>(tile.getValue());
                remains.retainAll(oTile.getValue());
                if (!remains.isEmpty())
                    counts++;
            }
            if (counts==2)
                result*=tile.getKey();
        }


        return result;
    }


    public static class Tile {
        private final int id;
        private Point position = null;
        private List<String> content;

        Tile(int id, List<String> content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;

            if (!(obj instanceof Tile)) {
                return false;
            }

            Tile o = (Tile) obj;
            return this.getId() == o.getId();
        }
        @Override
        public int hashCode() {
            return id;
        }


        public int getId() {
            return id;
        }

        public List<String> getContent() {
            return content;
        }

        public boolean isFixed() {
            return position != null;
        }

        public Point getPosition() {
            return position;
        }

        public void setPosition(Point position) {
            this.position = position;
        }

        public String getNorthSide() {
            return content.get(0);
        }

        public String getWestSide() {
            StringBuilder west = new StringBuilder();
            for (String s : content) {
                west.append(s.charAt(0));
            }
            return west.toString();
        }

        public String getEastSide() {
            StringBuilder east = new StringBuilder();
            for(int i=0; i<content.size(); i++) {
                east.append(content.get(i).charAt(content.size() - 1));
            }
            return east.toString();
        }

        public String getSouthSide() {
            return content.get(content.size()-1);
        }

        void rotateRight() {
            // ABC    GDA
            // DEF -> HEB
            // GHI    IFC

            ArrayList<String> tempList=new ArrayList<>();
            for(int i = 0; i<content.size(); i++) {
                StringBuilder tmp = new StringBuilder();
                for(int j = content.size()-1; j>=0; j--) {
                    tmp.append(content.get(j).charAt(i));
                }
                tempList.add(tmp.toString());
            }
            content=tempList;
        }
        void rotateLeft() {
            // ABC    CFI
            // DEF -> BEH
            // GHI    ADG

            ArrayList<String> tempList=new ArrayList<>();
            for(int i = content.size()-1; i>=0; i--) {
                StringBuilder tmp = new StringBuilder();
                for (String s : content) {
                    tmp.append(s.charAt(i));
                }
                tempList.add(tmp.toString());
            }
            content=tempList;

        }
        void flipHorizontal() {
            // ABC    CBA
            // DEF -> FED
            // GHI    IHG

            for(int i = 0; i<content.size(); i++) {
                content.set(i, StringUtils.reverse(content.get(i)));
            }
        }
        void flipVertical() {
            // ABC    GHI
            // DEF -> DEF
            // GHI    ABC

            ArrayList<String> tempList=new ArrayList<>();
            for(int i = content.size()-1; i>=0; i--) {
                tempList.add(content.get(i));
            }
            content=tempList;
        }

        void removeBorder() {
            ArrayList<String> tempList=new ArrayList<>();
            for(int i = 1; i<content.size()-1; i++) {
                tempList.add(content.get(i).substring(1, content.size()-1));
            }
            content=tempList;
        }
    }

    static private List<Tile> makeTileList(List<String> input) {
        List<Tile> tiles = new ArrayList<>();

        for(int i=0; i<input.size(); i++) {
            if (input.get(i).startsWith("Tile ")) {
                int currentId = Integer.parseInt(input.get(i).split("Tile ")[1].split(":")[0]);
                int size = input.get(i + 1).length();
                tiles.add(new Tile(currentId, input.subList(i + 1, i + 1 + size)));
            }
        }
        return tiles;
    }

    static private Map<Point, Tile> fitToMap(List<Tile> tiles) {
        List<Tile> leftToPlace = new ArrayList<>(tiles);
        List<Tile> placed = new ArrayList<>();

        Point p = new Point(0,0);
        tiles.get(0).setPosition(p);
        tiles.get(0).flipVertical();
        placed.add(tiles.get(0));

        leftToPlace.remove(tiles.get(0));

        while(!leftToPlace.isEmpty()) {
            for (Tile current : leftToPlace) {
                for (int turn = 0; turn < 3; turn++) {
                    if (current.isFixed())
                        continue;
                    else
                        current.rotateLeft();
                    for (var fixedTile : placed) {
                        if (current.getSouthSide().equals(fixedTile.getNorthSide())) {
                            current.setPosition(fixedTile.getPosition().north());
                        }
                        if (current.getWestSide().equals(fixedTile.getEastSide())) {
                            current.setPosition(fixedTile.getPosition().east());
                        }
                        if (current.getEastSide().equals(fixedTile.getWestSide())) {
                            current.setPosition(fixedTile.getPosition().west());
                        }
                        if (current.getNorthSide().equals(fixedTile.getSouthSide())) {
                            current.setPosition(fixedTile.getPosition().south());
                        }
                    }
                }
                if (current.isFixed())
                    continue;
                else
                    current.flipVertical();
                for (int turn = 0; turn < 3; turn++) {
                    if (current.isFixed())
                        continue;
                    else
                        current.rotateLeft();
                    for (var fixedTile : placed) {
                        if (current.getSouthSide().equals(fixedTile.getNorthSide())) {
                            current.setPosition(fixedTile.getPosition().north());
                        }
                        if (current.getWestSide().equals(fixedTile.getEastSide())) {
                            current.setPosition(fixedTile.getPosition().east());
                        }
                        if (current.getEastSide().equals(fixedTile.getWestSide())) {
                            current.setPosition(fixedTile.getPosition().west());
                        }
                        if (current.getNorthSide().equals(fixedTile.getSouthSide())) {
                            current.setPosition(fixedTile.getPosition().south());
                        }
                    }
                }
            }
            leftToPlace = tiles.stream().filter(t -> !t.isFixed()).collect(Collectors.toList());
            placed = tiles.stream().filter(Tile::isFixed).collect(Collectors.toList());
        }

        return placed.stream().collect(Collectors.toMap(Tile::getPosition, Function.identity()));
    }
    static private Tile makeBigTile(Map<Point, Tile> tileMap) {
        tileMap.values().forEach(Tile::removeBorder);
        int minY = tileMap.values().stream().mapToInt(t -> t.getPosition().getY()).min().getAsInt();
        int minX = tileMap.values().stream().mapToInt(t -> t.getPosition().getX()).min().getAsInt();

        int maxY = tileMap.values().stream().mapToInt(t -> t.getPosition().getY()).max().getAsInt();
        int maxX = tileMap.values().stream().mapToInt(t -> t.getPosition().getX()).max().getAsInt();
        int rowsPerTile = tileMap.get(new Point(0,0)).getNorthSide().length();
        List<String> content = new ArrayList<>();
        for(int y=minY; y<=maxY; y++) {
            List<String> toAdd = new ArrayList<>();
            for(int i=0;i<rowsPerTile; i++) {
                toAdd.add("");
            }
            for(int x=minX; x<=maxX; x++) {
                Tile currentTile = tileMap.get(new Point(x,y));
                for (int i = 0; i < rowsPerTile; i++) {
                    toAdd.set(i, toAdd.get(i)+currentTile.getContent().get(i));
                }
            }
            content.addAll(toAdd);
        }
        return new Tile(0, content);
    }

    static private long determineWaterRoughness(Tile photo) {

        long seaMonsters=findMonsters(photo);
        if(seaMonsters==0) {
            photo.flipHorizontal();
            seaMonsters=findMonsters(photo);
            photo.flipHorizontal();
        }
        if(seaMonsters==0) {
            photo.flipVertical();
            seaMonsters=findMonsters(photo);
            photo.flipVertical();
        }
        if(seaMonsters==0) {
            photo.rotateLeft();
            return determineWaterRoughness(photo);
        }
        int numberOfHash=0;
        for(int i =0; i<photo.getContent().size(); i++) {
            for(char c : photo.getContent().get(i).toCharArray())
                if(c=='#') numberOfHash++;
        }

        return numberOfHash-seaMonsters*15;

    }
    static long findMonsters(Tile photo) {
        //01234567890123456789
        //                  #
        //#    ##    ##    ###
        // #  #  #  #  #  #
        long seaMonsters=0;
        for(int row =0; row<photo.getContent().size()-2; row++) {
            for(int index =0; index<photo.getContent().get(row).length()-19; index++) {
                String row0=photo.getContent().get(row);
                String row1=photo.getContent().get(row+1);
                String row2=photo.getContent().get(row+2);
                if(row0.charAt(index+18)=='#') {
                    if(row1.charAt(index+0)=='#' &&
                            row1.charAt(index+5)=='#' &&
                            row1.charAt(index+6)=='#' &&
                            row1.charAt(index+11)=='#' &&
                            row1.charAt(index+12)=='#' &&
                            row1.charAt(index+17)=='#' &&
                            row1.charAt(index+18)=='#' &&
                            row1.charAt(index+19)=='#') {
                        if(row2.charAt(index+1)=='#' &&
                                row2.charAt(index+4)=='#' &&
                                row2.charAt(index+7)=='#' &&
                                row2.charAt(index+10)=='#' &&
                                row2.charAt(index+13)=='#' &&
                                row2.charAt(index+16)=='#' ) {
                            seaMonsters++;
                        }
                    }
                }
            }
        }
        return seaMonsters;
    }

    static long solveB(List<String> values) {
        List<Tile> tiles = makeTileList(values);
        Map<Point, Tile> map = fitToMap(tiles);
        Tile photo = makeBigTile(map);
        return determineWaterRoughness(photo);
    }


    public static void main (String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //17032646100079
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //2006
    }
}
