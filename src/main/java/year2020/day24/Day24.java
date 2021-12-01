package year2020.day24;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day24 {
    static class HexTile {
        private final int n;
        private final int e;
        private final int _ne;
        private final int _nw;
        private final int _e;

        public HexTile(int ne, int nw, int e) {
            this.n = ne+nw;
            this.e = 2*e + (ne-nw);
            this._ne=ne;
            this._nw=nw;
            this._e=e;

        }


        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;

            if (!(obj instanceof HexTile)) {
                return false;
            }

            HexTile o = (HexTile) obj;
            return this.e == o.e && this.n == o.n;
        }
        @Override
        public int hashCode() {
            return 100*n+e;
        }

        List<HexTile> getNeighbours() {
            List<HexTile> neighbours = new ArrayList<>();
            neighbours.add(new HexTile(_ne+1, _nw, _e));
            neighbours.add(new HexTile(_ne-1, _nw, _e));
            neighbours.add(new HexTile(_ne, _nw+1, _e));
            neighbours.add(new HexTile(_ne, _nw-1, _e));
            neighbours.add(new HexTile(_ne, _nw, _e+1));
            neighbours.add(new HexTile(_ne, _nw, _e-1));
            return neighbours;
        }
    }

    static int solveA(List<String> inputs) {

        return getStartPosition(inputs).size();
    }
    static private HashSet<HexTile> getStartPosition(List<String> inputs) {
        HashSet<HexTile> flippedTiles=new HashSet<>();
        for(var row : inputs) {
            String tmp = row;

            int l0= tmp.length();
            tmp=tmp.replaceAll("ne", "");
            int ne = (l0-tmp.length())/2;

            l0= tmp.length();
            tmp=tmp.replaceAll("nw", "");
            int nw = (l0-tmp.length())/2;

            l0= tmp.length();
            tmp=tmp.replaceAll("se", "");
            int se = (l0-tmp.length())/2;

            l0= tmp.length();
            tmp=tmp.replaceAll("sw", "");
            int sw = (l0-tmp.length())/2;

            l0= tmp.length();
            tmp=tmp.replaceAll("e", "");
            int e = (l0-tmp.length());

            int w = tmp.length();
            HexTile t = new HexTile(ne-sw, nw-se, e-w);
            if(flippedTiles.contains(t)) {
                flippedTiles.remove(t);
            } else {
                flippedTiles.add(t);
            }
        }
        return flippedTiles;
    }


    static long solveB(List<String> inputs) {
        HashSet<HexTile> currentLayout = getStartPosition(inputs);
        for(int turn=0; turn<100; turn++) {
            HashSet<HexTile> newLayout = new HashSet<>();
            HashSet<HexTile> tested = new HashSet<>();
            for(var tile : currentLayout) {
                for(var testTile : tile.getNeighbours()) {
                    if(!tested.add(testTile)) continue;
                    HashSet<HexTile> tmp = new HashSet<>(currentLayout);
                    tmp.retainAll(testTile.getNeighbours());

                    if(currentLayout.contains(testTile)) {
                        if(tmp.size()>0 && tmp.size()<3) { newLayout.add(testTile); }
                    } else {
                        if(tmp.size()==2) {newLayout.add(testTile); }
                    }
                }
            }
            currentLayout = newLayout;
        }
        return currentLayout.size();

    }


    public static void main (String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //317
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //3804
    }
}
