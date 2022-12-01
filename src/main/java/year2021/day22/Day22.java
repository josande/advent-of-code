package year2021.day22;

import lombok.Data;
import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day22 {

    static Object solveA(List<String> values) {
        return getEndVolume(values, true);
    }
    static Object solveB(List<String> values) {
        return getEndVolume(values, false);
    }
    static long getEndVolume(List<String> values, boolean onlyInit) {
        ArrayList<Cube> cubes = new ArrayList<>();
        for(var val : values) {

            String parts[] = val
                    .replaceAll("on ","")
                    .replaceAll("off ", "")
                    .replaceAll("x=", "")
                    .replaceAll("y=", "")
                    .replaceAll("z=", "")
                    .split(",");
            int xMin =Integer.parseInt(parts[0].split("\\.\\.")[0]);
            int xMax =Integer.parseInt(parts[0].split("\\.\\.")[1]);
            int yMin =Integer.parseInt(parts[1].split("\\.\\.")[0]);
            int yMax =Integer.parseInt(parts[1].split("\\.\\.")[1]);
            int zMin =Integer.parseInt(parts[2].split("\\.\\.")[0]);
            int zMax =Integer.parseInt(parts[2].split("\\.\\.")[1]);

            if(onlyInit) {
                int limit =50;
                if (xMin < -limit || xMax > limit)
                    continue;
                if (yMin < -limit || yMax > limit)
                    continue;
                if (zMin < -limit || zMax > limit)
                    continue;
            }

            Point a = new Point(xMin, yMin, zMin);
            Point b = new Point(xMax, yMax, zMax);

            Cube c = new Cube(a,b);

            if(val.startsWith("on")) {
                ArrayList<Cube> newSubCubes=new ArrayList<>();
                for(Cube current: cubes) {
                    Cube overLap= findOverlappingCube(current, c);
                    if(overLap.getVolume()==0L) {
                        newSubCubes.add(current);
                    } else {
                        newSubCubes.addAll(explodeAndRemove(current, c));
                    }
                }
                newSubCubes.add(c);
                cubes = newSubCubes;


            } else {
                ArrayList<Cube> newSubCubes=new ArrayList<>();
                for(Cube current : cubes) {
                    newSubCubes.addAll(explodeAndRemove(current, c));
                }
                cubes = newSubCubes;
            }
        }
        Long totalVol = 0L;
        for(Cube c : cubes) {
            totalVol+=c.getVolume();
        }

        return totalVol;
    }
    @Data
    static class Cube {
        Point a, b;

        Cube(Point a, Point b) {
            this.a = a;
            this.b = b;
        }
        Cube(Cube other) {
            this.a=new Point(other.getA());
            this.b=new Point(other.getB());
        }
        long getVolume() {
            return 1L*(1+Math.abs(a.getX()-b.getX()))
                  *(1+Math.abs(a.getY()-b.getY()))
                  *(1+Math.abs(a.getZ()-b.getZ()));
        }
        int getMaxX() {
            return Math.max(a.getX(), b.getX());
        }
        int getMinX() {
            return Math.min(a.getX(), b.getX());
        }
        int getMaxY() {
            return Math.max(a.getY(), b.getY());
        }
        int getMinY() {
            return Math.min(a.getY(), b.getY());
        }
        int getMaxZ() {
            return Math.max(a.getZ(), b.getZ());
        }
        int getMinZ() {
            return Math.min(a.getZ(), b.getZ());
        }
        @Override
        public String toString() {
            return "(x="+a.getX()+" - "+b.getX()+
                   ",y="+a.getY()+" - "+b.getY()+
                   ",z="+a.getZ()+" - "+b.getZ()+"), "+getVolume();

        }
    }

    static boolean overlap (Cube cubeA, Cube cubeB) {
        boolean x1 = cubeA.getMaxX() < cubeB.getMinX();
        boolean x2 = cubeA.getMinX() > cubeB.getMaxX();
        boolean y1 = cubeA.getMaxY() < cubeB.getMinY();
        boolean y2 = cubeA.getMinY() > cubeB.getMaxY();
        boolean z1 = cubeA.getMaxZ() < cubeB.getMinZ();
        boolean z2 = cubeA.getMinZ() > cubeB.getMaxZ();

        return !x1 && !x2 && !y1 && !y2 && !z1 && !z2;
    }
    static Cube findOverlappingCube(Cube cubeA, Cube cubeB) {

        Point a = new Point (Math.max(cubeA.getMinX(), cubeB.getMinX()),
                             Math.max(cubeA.getMinY(), cubeB.getMinY()),
                             Math.max(cubeA.getMinZ(), cubeB.getMinZ()));

        Point b = new Point (Math.min(cubeA.getMaxX(), cubeB.getMaxX()),
                             Math.min(cubeA.getMaxY(), cubeB.getMaxY()),
                             Math.min(cubeA.getMaxZ(), cubeB.getMaxZ()));
        return new Cube(a,b);
    }
    static List<Cube> explodeAndRemove(Cube existing, Cube toRemove) {
        List<Cube> subCubes = new ArrayList<>();

        Cube current= new Cube(existing);

        if(!overlap(existing, toRemove)) {
            subCubes.add(existing);
            return subCubes;
        }

        if(current.getMinX() < toRemove.getMinX() && current.getMaxX() >= toRemove.getMinX()) {
            Cube newPart = new Cube(current);
            newPart.getB().setX(toRemove.getMinX()-1);
            subCubes.add(newPart);
            current.getA().setX(toRemove.getMinX());
        }
        if(current.getMaxX() > toRemove.getMaxX() && current.getMinX() <= toRemove.getMaxX()) {
            Cube newPart = new Cube(current);
            newPart.getA().setX(toRemove.getMaxX()+1);
            subCubes.add(newPart);
            current.getB().setX(toRemove.getMaxX());
        }

        if(current.getMinY() < toRemove.getMinY() && current.getMaxY() >= toRemove.getMinY()) {
            Cube newPart = new Cube(current);
            newPart.getB().setY(toRemove.getMinY()-1);
            subCubes.add(newPart);
            current.getA().setY(toRemove.getMinY());
        }

        if(current.getMaxY() > toRemove.getMaxY() && current.getMinY() <= toRemove.getMaxY()) {
            Cube newPart = new Cube(current);
            newPart.getA().setY(toRemove.getMaxY()+1);
            subCubes.add(newPart);
            current.getB().setY(toRemove.getMaxY());
        }

        if(current.getMinZ() < toRemove.getMinZ() && current.getMaxZ() >= toRemove.getMinZ()) {
            Cube newPart = new Cube(current);
            newPart.getB().setZ(toRemove.getMinZ()-1);
            subCubes.add(newPart);
            current.getA().setZ(toRemove.getMinZ());
        }

        if(current.getMaxZ() > toRemove.getMaxZ() && current.getMinZ() <= toRemove.getMaxZ()) {
            Cube newPart = new Cube(current);
            newPart.getA().setZ(toRemove.getMaxZ()+1);
            subCubes.add(newPart);
            current.getB().setZ(toRemove.getMaxZ());
        }


        return subCubes;
    }

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //652209
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //1217808640648260
    }
}
