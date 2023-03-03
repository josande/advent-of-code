package year2019.day10;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class Day10 {

    static  List<Point> getAsteroidMap (List<String> input) {
        List<Point> asteroids = new ArrayList<>();
        for (int y=0; y<input.size(); y++) {
            for (int x=0; x<input.get(y).length(); x++) {
                if (input.get(y).charAt(x)=='#') {
                    asteroids.add(new Point(x,y));
                }
            }
        }
        return asteroids;
    }
    static List<Point> getAsteroidsInSight(Point p, List<Point> asteroids) {
        List<Point> blockedPoints = new ArrayList<>();
        int xMax=50;
        int yMax=50;
        for (Point a : asteroids) {
            if (a.equals(p)) continue;
            int x=a.getX();
            int y=a.getY();
            int deltaX = a.getX()-p.getX();
            int deltaY = a.getY()-p.getY();
            if (deltaX==0) {
                deltaY=deltaY/Math.abs(deltaY);
            }
            if (deltaY==0) {
                deltaX=deltaX/Math.abs(deltaX);
            }
            if (Math.abs(deltaX)==Math.abs(deltaY)) {
                deltaX=deltaX/Math.abs(deltaX);
                deltaY=deltaY/Math.abs(deltaY);
            }
            int gcd = getGcd(deltaX, deltaY) ;
            deltaX/=gcd;
            deltaY/=gcd;
                //hitta gcd
            while (x>=0 && x<xMax && y>=0 && y< yMax) {
                y+=deltaY;
                x+=deltaX;

                blockedPoints.add(new Point(x,y));
            }
        }
        List<Point> visiblePoints = new ArrayList<>(asteroids);
        visiblePoints.removeAll(blockedPoints);
        visiblePoints.remove(p);
        return visiblePoints;

    }
    static  int getGcd(int n1, int n2) {
        if (n1==0 || n2==0) return 1;
        n1 = ( n1 > 0) ? n1 : -n1;
        n2 = ( n2 > 0) ? n2 : -n2;
        while(n1 != n2)
        {
            if(n1 > n2)
                n1 -= n2;
            else
                n2 -= n1;
        }
        return n1;
    }
    static Point getNextAsteroidToBlast(Point p, List<Point> remainingAsteroids) {
        //for top-right side:
        //find anyone Directly above
        //if more then one, take closest

        remainingAsteroids.remove(p);
        Point nextTarget = null;
        int shortestLengthToAsteroid = 50;
        for (Point ra : remainingAsteroids) {
            if (p.getX() == ra.getX() && p.getY()>ra.getY()) {
                int lengthToAsteroidAbove = p.getY()-ra.getY();
                if (lengthToAsteroidAbove<shortestLengthToAsteroid) {
                    shortestLengthToAsteroid=lengthToAsteroidAbove;
                    nextTarget=ra;
                }
            }
        }
        if (nextTarget!=null) {
            return nextTarget;
        }
        double lowestKValue=50.0;
        double highestKvalue=0.0;

        for (Point ra : remainingAsteroids) {
            if (p.getX() < ra.getX() && p.getY() > ra.getY()) { // top right quadrant
                int deltaX = ra.getX() - p.getX();
                int deltaY = ra.getY() - p.getY();
                double kValue = ((double) -deltaY) / deltaX;
                if (kValue > highestKvalue) {
                    shortestLengthToAsteroid = ra.getManhattanDistance(p);
                    highestKvalue = kValue;
                    nextTarget = ra;
                } else if (kValue == highestKvalue && ra.getManhattanDistance(p) < shortestLengthToAsteroid) {
                    shortestLengthToAsteroid = ra.getManhattanDistance(p);
                    highestKvalue = kValue;
                    nextTarget = ra;
                }
            }
        }
        if (nextTarget!=null) {
            return nextTarget;
        }
        for (Point ra : remainingAsteroids) {
            if (p.getY() == ra.getY() && p.getX() < ra.getX()) { // to the right
                int distance = ra.getManhattanDistance(p);
                if (distance<shortestLengthToAsteroid) {
                    nextTarget=ra;
                    shortestLengthToAsteroid=distance;
                }
            }
        }
        if (nextTarget!=null) {
            return nextTarget;
        }
        for (Point ra : remainingAsteroids) {
            if (p.getX() < ra.getX() && p.getY() < ra.getY()) { // bottom right quadrant
                int deltaX = ra.getX() - p.getX();
                int deltaY = ra.getY() - p.getY();
                double kValue = ((double) deltaY) / deltaX;
                if (kValue < lowestKValue) {
                    shortestLengthToAsteroid = ra.getManhattanDistance(p);
                    lowestKValue = kValue;
                    nextTarget = ra;
                } else if (kValue == lowestKValue && ra.getManhattanDistance(p) < shortestLengthToAsteroid) {
                    shortestLengthToAsteroid = ra.getManhattanDistance(p);
                    lowestKValue = kValue;
                    nextTarget = ra;
                }
            }
        }
        if (nextTarget!=null) {
            return nextTarget;
        }

        for (Point ra : remainingAsteroids) {
            if (p.getX() == ra.getX() && p.getY() < ra.getY()) { // below
                int distance = ra.getManhattanDistance(p);
                if (distance<shortestLengthToAsteroid) {
                    nextTarget=ra;
                    shortestLengthToAsteroid=distance;
                }
            }
        }
        if (nextTarget!=null) {
            return nextTarget;
        }

        for (Point ra : remainingAsteroids) {
            if (p.getX() > ra.getX() && p.getY() < ra.getY()) { // bottom left
                int deltaX = ra.getX() - p.getX();
                int deltaY = ra.getY() - p.getY();
                double kValue = ((double) -deltaY) / deltaX;
                if (kValue > highestKvalue) {
                    shortestLengthToAsteroid = ra.getManhattanDistance(p);
                    highestKvalue = kValue;
                    nextTarget = ra;
                } else if (kValue == highestKvalue && ra.getManhattanDistance(p) < shortestLengthToAsteroid) {
                    shortestLengthToAsteroid = ra.getManhattanDistance(p);
                    highestKvalue = kValue;
                    nextTarget = ra;
                }
            }
        }
        if (nextTarget!=null) {
            return nextTarget;
        }

        for (Point ra : remainingAsteroids) {
            if (p.getY() == ra.getY() && p.getX() > ra.getX()) { // to the left
                int distance = ra.getManhattanDistance(p);
                if (distance<shortestLengthToAsteroid) {
                    nextTarget=ra;
                    shortestLengthToAsteroid=distance;
                }
            }
        }
        if (nextTarget!=null) {
            return nextTarget;
        }
        for (Point ra : remainingAsteroids) {
            if (p.getX() > ra.getX() && p.getY() > ra.getY()) { // top left
                int deltaX = ra.getX() - p.getX();
                int deltaY = ra.getY() - p.getY();
                double kValue = ((double) deltaY) / deltaX;
                if (kValue < lowestKValue) {
                    shortestLengthToAsteroid = ra.getManhattanDistance(p);
                    lowestKValue = kValue;
                    nextTarget = ra;
                } else if (kValue == lowestKValue && ra.getManhattanDistance(p) < shortestLengthToAsteroid) {
                    shortestLengthToAsteroid = ra.getManhattanDistance(p);
                    lowestKValue = kValue;
                    nextTarget = ra;
                }
            }
        }
        return nextTarget;
    }
    public static void main(String[] args) {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

       // List<String> input = Arrays.asList(in.split("\n"));
        List<Point> asteroids = getAsteroidMap(inputs);

        int maxInSight=0;
        Point bestSpot=null;
        for (Point p : asteroids) {
            int inSight=getAsteroidsInSight(p, asteroids).size();
            if (inSight>maxInSight) {
                bestSpot=p;
                maxInSight=inSight;
            }
        }

        System.out.println("Day10A "+maxInSight);

        List<Point> asteroidsInSight= getAsteroidsInSight(bestSpot, asteroids);
        Point nextTarget=null;
        for (int i=1; i<=200;i++) {
             nextTarget = getNextAsteroidToBlast(bestSpot, asteroidsInSight);
            asteroidsInSight.remove(nextTarget);
        }
        System.out.println("Day10B "+ (nextTarget.getX()*100+nextTarget.getY()));
    }
}
