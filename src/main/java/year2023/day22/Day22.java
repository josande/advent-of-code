package year2023.day22;

import lombok.Data;
import utils.AdventOfCode;
import utils.Point;
import utils.Reporter;
import java.util.*;

public class Day22 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day22());
    }

    static HashMap<Brick, HashSet<Brick>> fallingIfMissing = new HashMap<>();
    static HashMap<Brick, HashSet<Brick>> bricksAbove = new HashMap<>();
    static HashMap<Brick, HashSet<Brick>> bricksBelow = new HashMap<>();

    @Data
    static class Brick implements Comparable<Brick>{
        private HashSet<Point> parts;
        private Boolean standsOnGround = null;

        Brick(HashSet<Point> parts) {
            this.parts = parts;
        }
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Brick)) return false;
            return this.parts.containsAll(((Brick) other).parts);
        }
        @Override
        public int hashCode() {
            return parts.hashCode();
        }

        void moveDown(HashSet<Point> fixed) {
            int maxZ=0;
            for(Point p : parts) {
                maxZ = Math.max(getMaxZ(p.getX(), p.getY(), fixed), maxZ);
            }
            int jumpLength = parts.stream().mapToInt(Point::getZ).min().orElseThrow()-maxZ-1;
            HashSet<Point> newPos = new HashSet<>();
            for(Point p : parts) {
                newPos.add(new Point(p.getX(), p.getY(), p.getZ()-jumpLength));
            }
            parts.clear();
            parts.addAll(newPos);
        }

        private int getMaxZ(int x, int y, HashSet<Point> fixed) {
            return fixed.stream().filter(p->p.getX()==x && p.getY()==y).mapToInt(Point::getZ).max().orElse(0);
        }
        void standsOnGround() {
            if (standsOnGround!= null) return ;
            standsOnGround = false;
            for(var part : parts) {
                if (part.getZ() == 1) {
                    standsOnGround = true;
                    break;
                }
            }
        }
        void bricksUnder(ArrayList<Brick> allBricks) {
            if(bricksBelow.containsKey(this)) return;

            LinkedList<Brick> otherBricks = new LinkedList<>(allBricks);
            otherBricks.remove(this);
            HashSet<Brick> bricks= new HashSet<>();
            for(Brick other : otherBricks) {
                for (Point p : parts) {
                    if (other.getParts().contains(p.down())) {
                        bricks.add(other);
                        break;
                    }
                }
            }
            bricksBelow.put(this, bricks);
        }
        void bricksOver(ArrayList<Brick> allBricks) {
            if(bricksAbove.containsKey(this)) return;

            LinkedList<Brick> otherBricks = new LinkedList<>(allBricks);
            otherBricks.remove(this);
            HashSet<Brick> bricks = new HashSet<>();
            for(Brick other : otherBricks) {
                for (Point p : parts) {
                    if (other.getParts().contains(p.up())) {
                        bricks.add(other);
                        break;
                    }
                }
            }
            bricksAbove.put(this, bricks);
        }
        @Override
        public int compareTo(Brick o) {
            return this.parts.stream().mapToInt(Point::getZ).min().orElseThrow()
                    - o.parts.stream().mapToInt(Point::getZ).min().orElseThrow();
        }
    }
    @Override
    public Object solveA(List<String> input) {
        ArrayList<Brick> allBricks = new ArrayList<>();
        for(String line : input) {
            String[] parts = line.split("~");
            String[] startValues = parts[0].split(",");
            String[] endValues = parts[1].split(",");
            HashSet<Point> points = new HashSet<>();
            for(int x=Integer.parseInt(startValues[0]); x<=Integer.parseInt(endValues[0]); x++) {
              for(int y=Integer.parseInt(startValues[1]); y<=Integer.parseInt(endValues[1]); y++) {
                for (int z = Integer.parseInt(startValues[2]); z <= Integer.parseInt(endValues[2]); z++) {
                    points.add(new Point(x,y,z));
                }
              }
            }
            allBricks.add(new Brick(points));
        }
        Collections.sort(allBricks);
        HashSet<Point> fixedBricks = new HashSet<>();

        for(Brick brick : allBricks) {
            brick.moveDown(fixedBricks);
            fixedBricks.addAll(brick.parts);
        }
        for(Brick b : allBricks) {
            b.bricksUnder(allBricks);
            b.bricksOver(allBricks);
            b.standsOnGround();
        }


        int removeCounter = 0;
        for(Brick tryToRemove : allBricks) {
            if(safeToRemove(tryToRemove))
                removeCounter++;
        }
        return removeCounter;
    }
    HashSet<Brick> fallingPieces(HashSet<Brick> removedBricks) {


        HashSet<Brick> removedAfter = new HashSet<>(removedBricks);



        boolean checkAgain=true;
        while(checkAgain) {
            checkAgain=false;
            HashSet<Brick> mightFall = new HashSet<>(
                    removedAfter.stream()
                            .map(b -> bricksAbove.get(b))
                            .flatMap(Collection::stream).toList());
            mightFall.removeAll(removedAfter);
            for(Brick brick : mightFall) {
                if(removedAfter.containsAll(bricksBelow.get(brick))) {
                    removedAfter.add(brick);
                    checkAgain=true;
                }
            }
        }
        return removedAfter;


    }
    boolean safeToRemove(Brick brick) {
        if(fallingIfMissing.containsKey(brick))
            return fallingIfMissing.get(brick).size() == 0;
        HashSet<Brick> removed = new HashSet<>();
        removed.add(brick);
        HashSet<Brick> falling = fallingPieces(removed);
        falling.remove(brick);
        fallingIfMissing.put(brick, falling);
        return falling.size() == 0;
    }

    @Override
    public Object solveB(List<String> input) {
        ArrayList<Brick> allBricks = new ArrayList<>();
        for(String line : input) {
            String[] parts = line.split("~");
            String[] startValues = parts[0].split(",");
            String[] endValues = parts[1].split(",");
            HashSet<Point> points = new HashSet<>();
            for(int x=Integer.parseInt(startValues[0]); x<=Integer.parseInt(endValues[0]); x++) {
                for(int y=Integer.parseInt(startValues[1]); y<=Integer.parseInt(endValues[1]); y++) {
                    for (int z = Integer.parseInt(startValues[2]); z <= Integer.parseInt(endValues[2]); z++) {
                        points.add(new Point(x,y,z));
                    }
                }
            }
            allBricks.add(new Brick(points));
        }
        Collections.sort(allBricks);
        HashSet<Point> fixedBricks = new HashSet<>();

        for(Brick brick : allBricks) {
            brick.moveDown(fixedBricks);
            fixedBricks.addAll(brick.parts);
        }
        for(Brick b : allBricks) {
            b.bricksUnder(allBricks);
            b.bricksOver(allBricks);
            b.standsOnGround();
        }

        int fallCounter = 0;
        for(Brick tryToRemove : allBricks) {
            HashSet<Brick> removed = new HashSet<>();
            removed.add(tryToRemove);
            int falling = fallingPieces(removed).size()-1;
            fallCounter+=falling;
        }
        return fallCounter;
    }

}
