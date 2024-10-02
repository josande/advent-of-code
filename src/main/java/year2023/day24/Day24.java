package year2023.day24;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day24 implements AdventOfCode {
    public static void main(String[] args){
        Reporter.report(new Day24());
    }


    private record Particle(long x, long y, long z, long dx, long dy, long dz) { }
    private record Point(double x, double y, double z) { }

    private long minValue = 200000000000000L;
    private long maxValue = 400000000000000L;

    @Override
    public Object solveA(List<String> input) {

        List<Particle> particles = new ArrayList<>();
        for(String s : input) {
            particles.add(asParticle(s));
        }

        int collisions = 0;
        for(int i=0; i<particles.size(); i++) {
            for(int j=i+1; j<particles.size(); j++) {
                Optional<Point> collidesAt = findIntersectionPoint2D(particles.get(i), particles.get(j));
                if(collidesAt.isPresent()
                        && insideTestArea(collidesAt.get())
                        && (getTimeToCollisionSpot(particles.get(i), collidesAt.get()) > 0)
                        && (getTimeToCollisionSpot(particles.get(j), collidesAt.get()) > 0)) {
                    collisions++;
                }
            }
        }
        return collisions;
    }

    private Particle asParticle(String input) {
        String[] parts = input.replaceAll("[@,]", "").split("\\s+");
        long x = Long.parseLong(parts[0]);
        long y = Long.parseLong(parts[1]);
        long z = Long.parseLong(parts[2]);
        long dx = Long.parseLong(parts[3]);
        long dy = Long.parseLong(parts[4]);
        long dz = Long.parseLong(parts[5]);
        return new Particle(x,y,z,dx,dy,dz);
    }
    void setTestInterval() {
        minValue = 7L;
        maxValue = 27L;
    }

    boolean insideTestArea(Point point) {
        return point.x >= minValue
                && point.y >= minValue
                && point.x <= maxValue
                && point.y <= maxValue;
    }

    Optional<Point> findIntersectionPoint2D(Particle one, Particle two) {
        double a1 = one.dy;
        double b1 = -one.dx;
        double c1 = one.dy*(one.x) - one.dx*(one.y);

        double a2 = two.dy;
        double b2 = -two.dx;
        double c2 = two.dy*(two.x) - two.dx*(two.y);

        double determinant = a1*b2 - a2*b1;

        if(determinant == 0)
            return Optional.empty();

        double x = (one.dx * c2 - two.dx * c1)/determinant;
        double y = (one.dy * c2 - two.dy * c1)/determinant;
        return Optional.of(new Point(x, y, 0L));
    }

    private void gaussianElimination(double[][] coefficients, double[] rhs) {
        int nrVariables = coefficients.length;
        for (int i = 0; i < nrVariables; i++) {

            double factor = coefficients[i][i];

            for (int j = 0; j < nrVariables; j++) {
                coefficients[i][j] = coefficients[i][j] / factor;
            }
            rhs[i] = rhs[i] / factor;

            for (int k = 0; k < nrVariables; k++) {
                if (k != i) {
                    factor = coefficients[k][i];
                    for (int j = 0; j < nrVariables; j++) {
                        coefficients[k][j] = coefficients[k][j] - factor * coefficients[i][j];
                    }
                    rhs[k] = rhs[k] - factor * rhs[i];
                }
            }
        }
    }

    double getTimeToCollisionSpot(Particle particle, Point point) {
        return (point.x - particle.x) / particle.dx;
    }

    @Override
    public Object solveB(List<String> input) {
        List<Particle> particles = new ArrayList<>();

        for(String s : input) {
            particles.add(asParticle(s));
        }

        int particlesToUse = 4;
        double[][] coefficients = new double[particlesToUse][4];
        double[] rhs = new double[particlesToUse];

        for (int i = 0; i < particlesToUse; i++) {
            Particle p1 = particles.get(i);
            Particle p2 = particles.get(i + 1);
            coefficients[i][0] = p2.dy - p1.dy;
            coefficients[i][1] = p1.dx - p2.dx;
            coefficients[i][2] = p1.y - p2.y;
            coefficients[i][3] = p2.x - p1.x;
            rhs[i] = -p1.x * p1.dy
                    + p1.y * p1.dx
                    + p2.x * p2.dy
                    - p2.y * p2.dx;
        }

        gaussianElimination(coefficients, rhs);

        long x = Math.round(rhs[0]);
        long y = Math.round(rhs[1]);
        long dx = Math.round(rhs[2]);

        coefficients = new double[2][2];
        rhs = new double[2];

        for (int i = 0; i < 2; i++) {
            Particle p1 = particles.get(i);
            Particle p2 = particles.get(i + 1);
            coefficients[i][0] = p1.dx - p2.dx;
            coefficients[i][1] = p2.x - p1.x;
            rhs[i] = -p1.x * p1.dz
                    + p1.z * p1.dx
                    + p2.x * p2.dz
                    - p2.z * p2.dx
                    - ((p2.dz - p1.dz) * x)
                    - ((p1.z - p2.z) * dx);
        }

        gaussianElimination(coefficients, rhs);

        long z = Math.round(rhs[0]);

        return x+y+z;
    }
}
