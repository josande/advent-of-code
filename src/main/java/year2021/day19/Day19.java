package year2021.day19;

import lombok.Data;
import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Day19 {
    @Data
    static class Scanner {
        int id;
        Point position;
        List<Point> points = new ArrayList<>();

        Scanner(String[] input) {

            id = Integer.parseInt(input[0].replaceAll("[^\\d.]", ""));
            for(int i=1; i<input.length;i++) {
                points.add(new Point(input[i]));
            }
        }
        Scanner(String input) {
            id = Integer.parseInt(input.replaceAll("[^\\d.]", ""));
            if(id == 0)
                position= new Point(0,0);
        }
        void addPoint(String string) {
            points.add(new Point(string));
        }
        boolean isFixed() {
            return getPosition() != null;
        }
        Point alignScannerToPoint(Point fixPoint, Point sensorReading) {
            int sensorX = fixPoint.getX() - sensorReading.getX();
            int sensorY = fixPoint.getY() - sensorReading.getY();
            int sensorZ = fixPoint.getZ() - sensorReading.getZ();
            return new Point(sensorX, sensorY, sensorZ);
        }
        int getNumberOfMatches(List<Point> configuration, Point scannerPoint, Scanner otherScanner) {
            int matches=0;
            for(Point other: otherScanner.getPoints()) {
                for (Point point : configuration) {
                    if (other.getX() == point.getX() + scannerPoint.getX()
                     && other.getY() == point.getY() + scannerPoint.getY()
                     && other.getZ() == point.getZ() + scannerPoint.getZ() )
                        matches++;
                }
            }
            return matches;
        }

        List<List<Point>> getAllConfigurations() {
            HashSet<List<Point>> configurations = new HashSet<>();

            for(int rotateX = 0; rotateX<4; rotateX++) {
                flipX();
                for (int rotateY = 0; rotateY < 4; rotateY++) {
                    flipY();
                    for (int rotateZ = 0; rotateZ < 4; rotateZ++) {
                        configurations.add(points);
                        flipZ();

                    }
                }
            }
            return new ArrayList<>(configurations);
        }

        void flipX() {
            List<Point> afterFlip = new ArrayList<>();
            for(Point p : points) {
                afterFlip.add(new Point(p.getX(), p.getZ(), -p.getY()));
            }
            this.points = afterFlip;
        }
        void flipY() {
            List<Point> afterFlip = new ArrayList<>();
            for(Point p : points) {
                afterFlip.add(new Point(-p.getZ(), p.getY(), p.getX()));
            }
            this.points = afterFlip;
        }
        void flipZ() {
            List<Point> afterFlip = new ArrayList<>();
            for(Point p : points) {
                afterFlip.add(new Point(p.getY(), -p.getX(), p.getZ()));
            }
            this.points = afterFlip;
        }
    }


    static Object solveA(List<String> values) {

        Scanner s = null;
        List<Scanner> scanners = new ArrayList<>();
        HashSet<Point> fixedPoints = new HashSet<>();

        for(var val : values) {
            if (val.isEmpty()) continue;
            if(val.startsWith("---")) {
                s = new Scanner(val);
                scanners.add(s);
                continue;
            }
            if(s != null)
                s.addPoint(val);
        }

        while(scanners.stream().anyMatch(scanner->!scanner.isFixed())) {
            List<Scanner> leftToBeLockedIn = scanners.stream().filter(scanner -> !scanner.isFixed()).collect(Collectors.toList());
            List<Scanner> fixedScanners = scanners.stream().filter(scanner -> scanner.isFixed()).collect(Collectors.toList());
            for(Scanner toBeLockedIn : leftToBeLockedIn) {
                lockInScanner(toBeLockedIn, fixedScanners);
                if(toBeLockedIn.isFixed()) {
                    break;
                }
            }
        }
        scanners.stream().forEach(scanner -> fixedPoints.addAll(scanner.getPoints()));

        return fixedPoints.size();
    }

    static void lockInScanner(Scanner toBeLockedIn, List<Scanner> fixedScanners) {
        var configurations = toBeLockedIn.getAllConfigurations();
        for(Scanner fixedScanner : fixedScanners) {
            for (List<Point> conf : configurations) {
                for (Point fixedPoint : fixedScanner.getPoints()) {
                    for (Point p2 : conf) {
                        Point scannerPoint = toBeLockedIn.alignScannerToPoint(fixedPoint, p2);
                        int matches = toBeLockedIn.getNumberOfMatches(conf, scannerPoint, fixedScanner);
                        if (matches >= 12) {
                            toBeLockedIn.setPosition(scannerPoint);
                            List<Point> adjustedPoints = adjustPoints(conf, scannerPoint);
                            toBeLockedIn.setPoints(adjustedPoints);

                            return;
                        }
                    }
                }
            }
        }
    }

    private static List<Point> adjustPoints(List<Point> conf, Point scannerPoint) {
        List<Point> adjustPoints = new ArrayList<>();
        for(Point p : conf) {
            Point adjP = new Point(p.getX()+scannerPoint.getX(),
                    p.getY()+scannerPoint.getY(),
                    p.getZ()+scannerPoint.getZ());
            adjustPoints.add(adjP);
        }
        return adjustPoints;

    }

    static Object solveB(List<String> values) {
        Scanner s = null;
        List<Scanner> scanners = new ArrayList<>();
        HashSet<Point> fixedPoints = new HashSet<>();

        for(var val : values) {
            if (val.isEmpty()) continue;
            if(val.startsWith("---")) {
                s = new Scanner(val);
                scanners.add(s);
                continue;
            }
            if(s != null)
                s.addPoint(val);
        }

        while(scanners.stream().anyMatch(scanner->!scanner.isFixed())) {
            List<Scanner> leftToBeLockedIn = scanners.stream().filter(scanner -> !scanner.isFixed()).collect(Collectors.toList());
            List<Scanner> fixedScanners = scanners.stream().filter(scanner -> scanner.isFixed()).collect(Collectors.toList());
            for(Scanner toBeLockedIn : leftToBeLockedIn) {
                lockInScanner(toBeLockedIn, fixedScanners);
                if(toBeLockedIn.isFixed()) {
                    break;
                }
            }
        }
        int maxDistance=0;
        for (Scanner s1 : scanners) {
            for (Scanner s2 : scanners) {
                maxDistance = Math.max(maxDistance, s1.getPosition().getManhattanDistance(s2.getPosition()));
            }
        }
        return maxDistance;
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");
//        var inputs = new FileHelper().readFileAsIntegers("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //396
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //11828
    }
}
