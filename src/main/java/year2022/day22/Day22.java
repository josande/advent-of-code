package year2022.day22;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileHelper;
import utils.MapUtil;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day22 {

    static Object solveA(List<String> values) {
        List<String> _values = new ArrayList<>(values);
        String lastLine = values.get(_values.size() - 1);
        _values.remove(_values.size() - 1);

        HashMap<Point, Character> map = new HashMap<>();


        for (int y = 0; y < _values.size(); y++) {
            for (int x = 0; x < _values.get(y).length(); x++) {
                char c = values.get(y).charAt(x);
                if (c == '.' || c == '#')
                    map.put(new Point(x, y), c);
            }
        }

        Point currentPosition = new Point(values.get(0).indexOf("."), 0);

        List<String> instructions = splitInstruction(lastLine);

        int direction = 0;
        int maxX = MapUtil.getMaxX(map);
        int maxY = MapUtil.getMaxY(map);


        for (String instruction : instructions) {
            //  System.out.print("From "+currentPosition+" ("+direction+") -> "+instruction);
            if (instruction.equals("R")) {
                direction = (4 + direction + 1) % 4;
            } else if (instruction.equals("L")) {
                direction = (4 + direction - 1) % 4;
            } else {
                int length = Integer.parseInt(instruction);
                for (int step = 0; step < length; step++) {
                    switch (direction) {
                        case 0 -> {
                            if (map.containsKey(currentPosition.east())) {
                                if (map.get(currentPosition.east()).equals('#')) {
                                    step = length;
                                } else {
                                    currentPosition = currentPosition.east();
                                }
                            } else {
                                for (int x = 0; x <= maxX; x++) {
                                    if (map.containsKey(new Point(x, currentPosition.getY()))) {
                                        if (map.get(new Point(x, currentPosition.getY())).equals('#')) {
                                            step = length;
                                        } else {
                                            currentPosition = new Point(x, currentPosition.getY());
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        case 1 -> {
                            if (map.containsKey(currentPosition.south())) {
                                if (map.get(currentPosition.south()).equals('#')) {
                                    step = length;
                                } else {
                                    currentPosition = currentPosition.south();
                                }
                            } else {
                                for (int y = 0; y <= maxY; y++) {
                                    if (map.containsKey(new Point(currentPosition.getX(), y))) {
                                        if (map.get(new Point(currentPosition.getX(), y)).equals('#')) {
                                            step = length;
                                        } else {
                                            currentPosition = new Point(currentPosition.getX(), y);
                                        }
                                        break;
                                    }
                                }
                            }

                        }
                        case 2 -> {
                            if (map.containsKey(currentPosition.west())) {
                                if (map.get(currentPosition.west()).equals('#')) {
                                    step = length;
                                } else {
                                    currentPosition = currentPosition.west();
                                }
                            } else {
                                for (int x = maxX; x >= 0; x--) {
                                    if (map.containsKey(new Point(x, currentPosition.getY()))) {
                                        if (map.get(new Point(x, currentPosition.getY())).equals('#')) {
                                            step = length;
                                        } else {
                                            currentPosition = new Point(x, currentPosition.getY());
                                        }
                                        break;
                                    }
                                }
                            }

                        }
                        case 3 -> {
                            if (map.containsKey(currentPosition.north())) {
                                if (map.get(currentPosition.north()).equals('#')) {
                                    step = length;
                                } else {
                                    currentPosition = currentPosition.north();
                                }
                            } else {
                                for (int y = maxY; y >= 0; y--) {
                                    if (map.containsKey(new Point(currentPosition.getX(), y))) {
                                        if (map.get(new Point(currentPosition.getX(), y)).equals('#')) {
                                            step = length;
                                        } else {
                                            currentPosition = new Point(currentPosition.getX(), y);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return (currentPosition.getY() + 1) * 1000 + (currentPosition.getX() + 1) * 4 + direction;
    }

    public static List<String> splitInstruction(String instruction) {
        ArrayList<String> instructions = new ArrayList<>();
        int lastIndex = 0;
        for (int i = 0; i < instruction.length(); i++) {
            if (instruction.charAt(i) == 'L' || instruction.charAt(i) == 'R') {
                instructions.add(instruction.substring(lastIndex, i));
                instructions.add("" + instruction.charAt(i));
                lastIndex = i + 1;
            }
        }
        instructions.add(instruction.substring(lastIndex));
        return instructions;
    }

    @Data
    @AllArgsConstructor
    static class Position {
        HashMap<Point, Character> currentMap;
        Point position;
        int direction;
        int side;
        int rotations;
    }

    static Object solveB(List<String> values, int cubeSize) {
        String lastLine = values.get(values.size() - 1);
        values.remove(values.size() - 1);


        HashMap<Point, Character> map = new HashMap<>();


        for (int y = 0; y < values.size(); y++) {
            for (int x = 0; x < values.get(y).length(); x++) {
                char c = values.get(y).charAt(x);
                if (c == '.' || c == '#')
                    map.put(new Point(x, y), c);
            }
        }

        HashMap<Point, Character> side0 = new HashMap<>();
        HashMap<Point, Character> side1 = new HashMap<>();
        HashMap<Point, Character> side2 = new HashMap<>();
        HashMap<Point, Character> side3 = new HashMap<>();
        HashMap<Point, Character> side4 = new HashMap<>();
        HashMap<Point, Character> side5 = new HashMap<>();


        List<String> instructions = splitInstruction(lastLine);

        Point startPoint = new Point(values.get(0).indexOf(".") % cubeSize, 0);


        for (int y = 0; y < cubeSize; y++) {
            for (int x = cubeSize; x < 2 * cubeSize; x++) {
                Point p = new Point(x % cubeSize, y % cubeSize);
                side0.put(p, map.get(new Point(x, y)));
            }
        }

        for (int y = 0; y < cubeSize; y++) {
            for (int x = 2 * cubeSize; x < 3 * cubeSize; x++) {
                Point p = new Point(x % cubeSize, y % cubeSize);
                side1.put(p, map.get(new Point(x, y)));
            }
        }

        for (int y = cubeSize; y < 2 * cubeSize; y++) {
            for (int x = cubeSize; x < 2 * cubeSize; x++) {
                Point p = new Point(x % cubeSize, y % cubeSize);
                side2.put(p, map.get(new Point(x, y)));
            }
        }

        for (int y = 2 * cubeSize; y < 3 * cubeSize; y++) {
            for (int x = 0; x < cubeSize; x++) {
                Point p = new Point(x % cubeSize, y % cubeSize);
                side3.put(p, map.get(new Point(x, y)));
            }
        }

        for (int y = 2 * cubeSize; y < 3 * cubeSize; y++) {
            for (int x = cubeSize; x < 2 * cubeSize; x++) {
                Point p = new Point(x % cubeSize, y % cubeSize);
                side4.put(p, map.get(new Point(x, y)));
            }
        }

        for (int y = 3 * cubeSize; y < 4 * cubeSize; y++) {
            for (int x = 0; x < cubeSize; x++) {
                Point p = new Point(x % cubeSize, y % cubeSize);
                side5.put(p, map.get(new Point(x, y)));
            }
        }


        return solveB(side0, side1, side2, side3, side4, side5, instructions, startPoint, cubeSize);
    }

    static int solveB(HashMap<Point, Character> side0,
                      HashMap<Point, Character> side1,
                      HashMap<Point, Character> side2,
                      HashMap<Point, Character> side3,
                      HashMap<Point, Character> side4,
                      HashMap<Point, Character> side5,
                      List<String> instructions,
                      Point startPoint,
                      int cubeSize) {
        Position currentLocation = new Position(new HashMap<>(side0), startPoint, 0, 0, 0);

        List<HashMap<Point, Character>> allSides = Arrays.asList(side0, side1, side2, side3, side4, side5);


        for (String instruction : instructions) {


            if (instruction.equals("R")) {
                currentLocation.setDirection((4 + currentLocation.getDirection() + 1) % 4);
            } else if (instruction.equals("L")) {
                currentLocation.setDirection((4 + currentLocation.getDirection() - 1) % 4);
            } else {
                int length = Integer.parseInt(instruction);
                for (int step = 0; step < length; step++) {
                    switch (currentLocation.getDirection()) {
                        case 0 -> {
                            if (currentLocation.getCurrentMap().containsKey(currentLocation.getPosition().east())) {
                                if (currentLocation.getCurrentMap().get(currentLocation.getPosition().east()).equals('.')) {
                                    currentLocation.setPosition(currentLocation.getPosition().east());
                                }
                            } else {
                                currentLocation = calculateNewPosition(currentLocation, allSides, currentLocation.getDirection(), cubeSize);
                            }
                        }
                        case 1 -> {
                            if (currentLocation.getCurrentMap().containsKey(currentLocation.getPosition().south())) {
                                if (currentLocation.getCurrentMap().get(currentLocation.getPosition().south()).equals('.')) {
                                    currentLocation.setPosition(currentLocation.getPosition().south());
                                }
                            } else {
                                currentLocation = calculateNewPosition(currentLocation, allSides, currentLocation.getDirection(), cubeSize);
                            }
                        }
                        case 2 -> {
                            if (currentLocation.getCurrentMap().containsKey(currentLocation.getPosition().west())) {
                                if (currentLocation.getCurrentMap().get(currentLocation.getPosition().west()).equals('.')) {
                                    currentLocation.setPosition(currentLocation.getPosition().west());
                                }
                            } else {
                                currentLocation = calculateNewPosition(currentLocation, allSides, currentLocation.getDirection(), cubeSize);
                            }
                        }
                        case 3 -> {
                            if (currentLocation.getCurrentMap().containsKey(currentLocation.getPosition().north())) {
                                if (currentLocation.getCurrentMap().get(currentLocation.getPosition().north()).equals('.')) {
                                    currentLocation.setPosition(currentLocation.getPosition().north());
                                }
                            } else {
                                currentLocation = calculateNewPosition(currentLocation, allSides, currentLocation.getDirection(), cubeSize);
                            }
                        }
                    }
                }
            }
        }



        int x = currentLocation.getPosition().getX()+1;
        int y = currentLocation.getPosition().getY()+1;
        int result=0;
        switch (currentLocation.getSide()) {
            case 0 -> result = (y + cubeSize * 0) * 1000 + (x + cubeSize * 1) * 4 + currentLocation.getDirection();
            case 1 -> result = (y + cubeSize * 0) * 1000 + (x + cubeSize * 2) * 4 + currentLocation.getDirection();
            case 2 -> result = (y + cubeSize * 1) * 1000 + (x + cubeSize * 1) * 4 + currentLocation.getDirection();
            case 3 -> result = (y + cubeSize * 2) * 1000 + (x + cubeSize * 0) * 4 + currentLocation.getDirection();
            case 4 -> result = (y + cubeSize * 2) * 1000 + (x + cubeSize * 1) * 4 + currentLocation.getDirection();
            case 5 -> result = (y + cubeSize * 3) * 1000 + (x + cubeSize * 0) * 4 + currentLocation.getDirection();
        }

        return result;
    }

    private static Position calculateNewPosition(Position currentLocation, List<HashMap<Point, Character>> allSides, int direction, int cubeSize) {
        if (currentLocation.getSide() == 0) {
            switch (direction) {
                case 0 -> { //RIGHT
                    Point newPoint = new Point(0, currentLocation.getPosition().getY());
                    int newSide = 1;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 0, newSide, 0);
                    else
                        return currentLocation;
                }
                case 1 -> { //DOWN
                    Point newPoint = new Point(currentLocation.getPosition().getX(), 0);
                    int newSide = 2;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 1, newSide, 0);
                    else
                        return currentLocation;
                }
                case 2 -> { //LEFT
                    Point newPoint = new Point(0, cubeSize - 1 - currentLocation.getPosition().getY());
                    int newSide = 3;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 0, newSide, 0);
                    else
                        return currentLocation;
                }
                case 3 -> { //UP
                    Point newPoint = new Point(0, currentLocation.getPosition().getX());
                    int newSide = 5;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 0, newSide, 0);
                    else
                        return currentLocation;
                }
            }
        }
        if (currentLocation.getSide() == 1) {
            switch (direction) {
                case 0 -> { //RIGHT
                    Point newPoint = new Point(cubeSize - 1, cubeSize - 1 - currentLocation.getPosition().getY());
                    int newSide = 4;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 2, newSide, 0);
                    else
                        return currentLocation;
                }
                case 1 -> { //DOWN
                    Point newPoint = new Point(cubeSize - 1, currentLocation.getPosition().getX());
                    int newSide = 2;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 2, newSide, 0);
                    else
                        return currentLocation;
                }
                case 2 -> { //LEFT
                    Point newPoint = new Point(cubeSize - 1, currentLocation.getPosition().getY());
                    int newSide = 0;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 2, newSide, 0);
                    else
                        return currentLocation;
                }
                case 3 -> { //UP
                    Point newPoint = new Point(currentLocation.getPosition().getX(), cubeSize - 1);
                    int newSide = 5;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 3, newSide, 0);
                    else
                        return currentLocation;
                }
            }
        }
        if (currentLocation.getSide() == 2) {
            switch (direction) {
                case 0 -> { //RIGHT
                    Point newPoint = new Point(currentLocation.getPosition().getY(), cubeSize - 1);
                    int newSide = 1;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 3, newSide, 0);
                    else
                        return currentLocation;
                }
                case 1 -> { //DOWN
                    Point newPoint = new Point(currentLocation.getPosition().getX(), 0);
                    int newSide = 4;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 1, newSide, 0);
                    else
                        return currentLocation;
                }
                case 2 -> { //LEFT
                    Point newPoint = new Point(currentLocation.getPosition().getY(), 0);
                    int newSide = 3;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 1, newSide, 0);
                    else
                        return currentLocation;
                }
                case 3 -> { //UP
                    Point newPoint = new Point(currentLocation.getPosition().getX(), cubeSize - 1);
                    int newSide = 0;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 3, newSide, 0);
                    else
                        return currentLocation;
                }
            }
        }
        if (currentLocation.getSide() == 3) {
            switch (direction) {
                case 0 -> { //RIGHT
                    Point newPoint = new Point(0, currentLocation.getPosition().getY());
                    int newSide = 4;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 0, newSide, 0);
                    else
                        return currentLocation;
                }
                case 1 -> { //DOWN
                    Point newPoint = new Point(currentLocation.getPosition().getX(), 0);
                    int newSide = 5;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 1, newSide, 0);
                    else
                        return currentLocation;
                }
                case 2 -> { //LEFT
                    Point newPoint = new Point(0, cubeSize - 1 - currentLocation.getPosition().getY());
                    int newSide = 0;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 0, newSide, 0);
                    else
                        return currentLocation;
                }
                case 3 -> { //UP
                    Point newPoint = new Point(0, currentLocation.getPosition().getX());
                    int newSide = 2;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 0, newSide, 0);
                    else
                        return currentLocation;
                }
            }
        }
        if (currentLocation.getSide() == 4) {
            switch (direction) {
                case 0 -> { //RIGHT
                    Point newPoint = new Point(cubeSize - 1, cubeSize - 1 - currentLocation.getPosition().getY());
                    int newSide = 1;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 2, newSide, 0);
                    else
                        return currentLocation;
                }
                case 1 -> { //DOWN
                    Point newPoint = new Point(cubeSize - 1, currentLocation.getPosition().getX());
                    int newSide = 5;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 2, newSide, 0);
                    else
                        return currentLocation;
                }
                case 2 -> { //LEFT
                    Point newPoint = new Point(cubeSize - 1, currentLocation.getPosition().getY());
                    int newSide = 3;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 2, newSide, 0);
                    else
                        return currentLocation;
                }
                case 3 -> { //UP
                    Point newPoint = new Point(currentLocation.getPosition().getX(), cubeSize - 1);
                    int newSide = 2;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 3, newSide, 0);
                    else
                        return currentLocation;
                }
            }

        }
        if (currentLocation.getSide() == 5) {
            switch (direction) {
                case 0 -> { //RIGHT
                    Point newPoint = new Point(currentLocation.getPosition().getY(), cubeSize - 1);
                    int newSide = 4;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 3, newSide, 0);
                    else
                        return currentLocation;
                }
                case 1 -> { //DOWN
                    Point newPoint = new Point(currentLocation.getPosition().getX(), 0);
                    int newSide = 1;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 1, newSide, 0);
                    else
                        return currentLocation;
                }
                case 2 -> { //LEFT
                    Point newPoint = new Point(currentLocation.getPosition().getY(), 0);
                    int newSide = 0;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 1, newSide, 0);
                    else
                        return currentLocation;
                }
                case 3 -> { //UP
                    Point newPoint = new Point(currentLocation.getPosition().getX(), cubeSize - 1);
                    int newSide = 3;

                    if (allSides.get(newSide).get(newPoint).equals('.'))
                        return new Position(allSides.get(newSide), newPoint, 3, newSide, 0);
                    else
                        return currentLocation;
                }
            }
        }
        throw new IllegalStateException();
    }

    private static void print(Position currentLocation) {
        switch (currentLocation.getDirection()) {
            case 0 -> currentLocation.getCurrentMap().put(currentLocation.getPosition(), '>');
            case 1 -> currentLocation.getCurrentMap().put(currentLocation.getPosition(), 'v');
            case 2 -> currentLocation.getCurrentMap().put(currentLocation.getPosition(), '<');
            case 3 -> currentLocation.getCurrentMap().put(currentLocation.getPosition(), '^');
        }
        System.out.println(currentLocation.getPosition()+", side: "+currentLocation.getSide());
        MapUtil.print(currentLocation.getCurrentMap());
        currentLocation.getCurrentMap().put(currentLocation.getPosition(), '.');
    }

    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2022/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs, 50);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); // 131052
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); // 4578
    }
}