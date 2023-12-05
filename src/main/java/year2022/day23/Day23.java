package year2022.day23;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.List;

public class Day23 {
    @Data
    @EqualsAndHashCode
    static class Elf {
        int id;
        Point position;
        Point wantedTarget;
        Point actualTarget;

        public Elf(Point position ) {
            this.position=position;
            this.id=id++;
        }

        public void findTarget(HashSet<Point> elves, int lookOrder) {
            boolean someoneNear = false;
            for (Point nearby : position.getAllNeighbours2d()) {
                if (elves.contains(nearby)) {
                    someoneNear = true;
                    break;
                }
            }
            if (!someoneNear) {
                wantedTarget = position;
            } else {
                switch (lookOrder) {
                    case 0 -> {
                        if (!elves.contains(position.northWest()) && !elves.contains(position.north()) && !elves.contains(position.northEast())) {
                            wantedTarget = position.north();
                        } else if (!elves.contains(position.southWest()) && !elves.contains(position.south()) && !elves.contains(position.southEast())) {
                            wantedTarget = position.south();
                        } else if (!elves.contains(position.northWest()) && !elves.contains(position.west()) && !elves.contains(position.southWest())) {
                            wantedTarget = position.west();
                        } else if (!elves.contains(position.northEast()) && !elves.contains(position.east()) && !elves.contains(position.southEast())) {
                            wantedTarget = position.east();
                        } else {
                            wantedTarget = position;
                        }
                    }
                    case 1 -> {
                        if (!elves.contains(position.southWest()) && !elves.contains(position.south()) && !elves.contains(position.southEast())) {
                            wantedTarget = position.south();
                        } else if (!elves.contains(position.northWest()) && !elves.contains(position.west()) && !elves.contains(position.southWest())) {
                            wantedTarget = position.west();
                        } else if (!elves.contains(position.northEast()) && !elves.contains(position.east()) && !elves.contains(position.southEast())) {
                            wantedTarget = position.east();
                        } else if (!elves.contains(position.northWest()) && !elves.contains(position.north()) && !elves.contains(position.northEast())) {
                            wantedTarget = position.north();
                        } else {
                            wantedTarget = position;
                        }
                    }
                    case 2 -> {
                        if (!elves.contains(position.northWest()) && !elves.contains(position.west()) && !elves.contains(position.southWest())) {
                            wantedTarget = position.west();
                        } else if (!elves.contains(position.northEast()) && !elves.contains(position.east()) && !elves.contains(position.southEast())) {
                            wantedTarget = position.east();
                        } else if (!elves.contains(position.northWest()) && !elves.contains(position.north()) && !elves.contains(position.northEast())) {
                            wantedTarget = position.north();
                        } else if (!elves.contains(position.southWest()) && !elves.contains(position.south()) && !elves.contains(position.southEast())) {
                            wantedTarget = position.south();
                        } else {
                            wantedTarget = position;
                        }
                    }
                    case 3 -> {
                        if (!elves.contains(position.northEast()) && !elves.contains(position.east()) && !elves.contains(position.southEast())) {
                            wantedTarget = position.east();
                        } else if (!elves.contains(position.northWest()) && !elves.contains(position.north()) && !elves.contains(position.northEast())) {
                            wantedTarget = position.north();
                        } else if (!elves.contains(position.southWest()) && !elves.contains(position.south()) && !elves.contains(position.southEast())) {
                            wantedTarget = position.south();
                        } else if (!elves.contains(position.northWest()) && !elves.contains(position.west()) && !elves.contains(position.southWest())) {
                            wantedTarget = position.west();
                        } else {
                            wantedTarget = position;
                        }
                    }
                }
            }
        }
    }


    static Object solveA(List<String> values) {
        HashSet<Elf> elves = new HashSet<>();

        for(int y=0; y<values.size(); y++) {
            for(int x=0; x<values.get(y).length(); x++) {
                if(values.get(y).charAt(x) == '#') {
                    elves.add(new Elf(new Point(x,y)));
                }
            }
        }
        int lookOrder=0; //north, south, west, east.

        for(int round=0; round < 10; round++) {
            HashSet<Point> elfLocations = new HashSet<>();
            for(Elf elf : elves) {
                elfLocations.add(elf.getPosition());
            }

            //look around
            for(Elf elf : elves) {
                elf.findTarget(elfLocations, lookOrder);
            }


            //move about
            for(Elf elf : elves) {
                if(elves.stream().filter(e -> !e.equals(elf)).map(Elf::getWantedTarget).anyMatch(p -> p.equals(elf.wantedTarget))) {
                    elf.actualTarget =elf.getPosition();
                } else {
                    elf.actualTarget =elf.wantedTarget;
                }
            }
            for(Elf elf : elves) {
                elf.position= elf.actualTarget;
                elf.wantedTarget=null;
                elf.actualTarget=null;
            }
            lookOrder=(4+lookOrder+1)%4;

            elfLocations.clear();
            for(Elf elf : elves) {
                elfLocations.add(elf.getPosition());
            }
        }
        HashSet<Point> elfLocations = new HashSet<>();

        for(Elf elf : elves) {
            elfLocations.add(elf.getPosition());
        }
        int minX = MapUtil.getMinX(elfLocations);
        int minY = MapUtil.getMinY(elfLocations);
        int maxX = MapUtil.getMaxX(elfLocations);
        int maxY = MapUtil.getMaxY(elfLocations);

        return (maxX-minX+1) * (maxY-minY+1)-elves.size();
    }
    static Object solveB(List<String> values) {
        HashSet<Elf> elves = new HashSet<>();

        for(int y=0; y<values.size(); y++) {
            for(int x=0; x<values.get(y).length(); x++) {
                if(values.get(y).charAt(x) == '#') {
                    elves.add(new Elf(new Point(x,y)));
                }
            }
        }
        int lookOrder=0; //north, south, west, east.
        int round;
        for(round=1; ; round++) {
            HashSet<Point> elfLocationsStart = new HashSet<>();
            for(Elf elf : elves) {
                elfLocationsStart.add(elf.getPosition());
            }

            //look around
            for(Elf elf : elves) {
                elf.findTarget(elfLocationsStart, lookOrder);
            }


            //move about
            for(Elf elf : elves) {
                if(elves.stream().filter(e -> !e.equals(elf)).map(Elf::getWantedTarget).anyMatch(p -> p.equals(elf.wantedTarget))) {
                    elf.actualTarget =elf.getPosition();
                } else {
                    elf.actualTarget =elf.wantedTarget;
                }
            }
            for(Elf elf : elves) {
                elf.position= elf.actualTarget;
                elf.wantedTarget=null;
                elf.actualTarget=null;
            }
            lookOrder=(4+lookOrder+1)%4;


            boolean allMatch=true;
            for(Elf elf : elves) {
                if(!elfLocationsStart.contains(elf.getPosition())) {
                    allMatch = false;
                    break;
                }
            }
            if(allMatch) {
                return round;
            }
        }
    }
    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 3815
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 893
    }
}
