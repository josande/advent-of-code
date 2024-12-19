package year2024.day15;

import utils.*;

import java.util.*;

public class Day15 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day15());
    }

    private record State(HashMap<Point, Character> map, String instruction){
        int getScore() {
            var currentMap = new HashMap<>(map);
            Point position = map.entrySet().stream().filter(e -> e.getValue()=='@').map(Map.Entry::getKey).findFirst().get();
            for (char move : instruction.toCharArray()) {
                Direction direction = Direction.of(move);
                Point newPosition = position.getNext(direction);
                if(currentMap.get(newPosition) == '.') {position= newPosition; continue;}
                if(currentMap.get(newPosition) == '#') {continue;}
                Set<Point> toMove = new HashSet<>();
                toMove.add(position);
                toMove = getBoxesToMove(currentMap, direction, toMove);

                if(!toMove.isEmpty()) {
                    HashMap<Point, Character> newMap = new HashMap<>(currentMap);
                    newMap.put(newPosition, '.');
                    for(Point p : toMove)
                        newMap.put(p, '.');
                    for(Point p : toMove)
                        newMap.put(p.getNext(direction), currentMap.get(p));
                    currentMap = newMap;
                    position=newPosition;
                }

            }
            return currentMap.entrySet().stream().filter(e->e.getValue()=='O' || e.getValue()=='[').mapToInt(e -> e.getKey().getY()*100 + e.getKey().getX()).sum();
        }
    }
    private static Set<Point> getBoxesToMove(HashMap<Point, Character> map, Direction direction, Set<Point> boxesToMove) {
        HashSet<Point> toMoveAfter = new HashSet<>(boxesToMove);
        for(Point box : boxesToMove) {
            Point next = box.getNext(direction);
            if(boxesToMove.contains(next)) continue;
            if(map.get(next) == 'O') toMoveAfter.add(next);
            if(map.get(next) == '[') {toMoveAfter.add(next); toMoveAfter.add(next.east());}
            if(map.get(next) == ']') {toMoveAfter.add(next); toMoveAfter.add(next.west());}
            if(map.get(next) == '#') return new HashSet<>();
        }

        return (toMoveAfter.size() == boxesToMove.size()) ? toMoveAfter : getBoxesToMove(map, direction, toMoveAfter);
    }

    @Override
    public Object solveA(List<String> input) {
        List<String> mapInstructions = new ArrayList<>();
        StringBuilder instructionList = new StringBuilder();
        boolean mapPart= true;
        for(var line: input) {
            if(line.isEmpty()) {
                mapPart=false;
                continue;
            }
            if (mapPart) mapInstructions.add(line);
            else instructionList.append(line);
        }
        State current2 = new State(MapUtil.asMap(mapInstructions), instructionList.toString());
        return current2.getScore();
    }

    @Override
    public Object solveB(List<String> input) {
        List<String> mapInstructions = new ArrayList<>();
        StringBuilder instructionList = new StringBuilder();
        boolean mapPart = true;
        for (var line : input) {
            if (line.isEmpty()) {
                mapPart = false;
                continue;
            }

            if (mapPart) {
                StringBuilder temp = new StringBuilder();
                for (char ch : line.toCharArray()) {
                    temp.append(switch (ch) {
                        case '#' -> "##";
                        case 'O' -> "[]";
                        case '.' -> "..";
                        case '@' -> "@.";
                        default -> throw new IllegalArgumentException("Unknown symbol: " + ch);
                    });
                }
                mapInstructions.add(temp.toString());
            } else {
                instructionList.append(line);
            }
        }
        State current2 = new State(MapUtil.asMap(mapInstructions), instructionList.toString());
        return current2.getScore();
    }
}
