package year2019.day12;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import static utils.MathTools.isPrime;

public class Day12 {
    static class Moon extends Point {
        int velX=0, velY=0, velZ=0;
        public Moon(int x, int y, int z) {
            super(x,y,z);
        }
        public Moon(Moon moon) {
            super(moon.getX(), moon.getY(), moon.getZ());
            this.velX=moon.getVelX();
            this.velY=moon.getVelY();
            this.velZ=moon.getVelZ();
        }
        public int getVelX() {
            return velX;
        }

        public void setVelX(int velX) {
            this.velX = velX;
        }

        public int getVelY() {
            return velY;
        }

        public void setVelY(int velY) {
            this.velY = velY;
        }

        public int getVelZ() {
            return velZ;
        }

        public void setVelZ(int velZ) {
            this.velZ = velZ;
        }
        public int getKineticEnergy() {
            return Math.abs(getVelX())+Math.abs(getVelY())+Math.abs(getVelZ());
        }
        public int getPotentialEnergy() {
            return Math.abs(getX())+Math.abs(getY())+Math.abs(getZ());
        }
        int getEnergy() {
            return getPotentialEnergy()*getKineticEnergy();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;

            if (!(obj instanceof Moon)) {
                return false;
            }

            Moon o = (Moon) obj;
            return this.getX() == o.getX() &&
                   this.getY() == o.getY() &&
                   this.getZ() == o.getZ() &&
                   this.getVelX() == o.getVelX() &&
                   this.getVelY() == o.getVelY() &&
                   this.getVelZ() == o.getVelZ();
        }

        @Override
        public String toString() {
            return "x="+getX()+",y="+getY()+",z="+getZ()+", velX="+getVelX()+", velY="+getVelY()+", velZ="+getVelZ();
        }
    }

    static void updateVelocity(Moon a, Moon b) {
        if (a.getX() > b.getX()) {
            a.setVelX(a.getVelX() - 1);
            b.setVelX(b.getVelX() + 1);
        } else if (a.getX() < b.getX()) {
            a.setVelX(a.getVelX() + 1);
            b.setVelX(b.getVelX() - 1);
        }

        if (a.getY() > b.getY()) {
            a.setVelY(a.getVelY() - 1);
            b.setVelY(b.getVelY() + 1);
        } else if (a.getY() < b.getY()) {
            a.setVelY(a.getVelY() + 1);
            b.setVelY(b.getVelY() - 1);
        }

        if (a.getZ() > b.getZ()) {
            a.setVelZ(a.getVelZ() - 1);
            b.setVelZ(b.getVelZ() + 1);
        } else if (a.getZ() < b.getZ()) {
            a.setVelZ(a.getVelZ() + 1);
            b.setVelZ(b.getVelZ() - 1);
        }
    }
    static void updatePosition(Moon moon) {
        moon.setX(moon.getX()+moon.getVelX());
        moon.setY(moon.getY()+moon.getVelY());
        moon.setZ(moon.getZ()+moon.getVelZ());
    }
    static List<Moon> parseInput(List<String> input) {
        List<Moon> moons = new ArrayList<>();
        for (String s : input) {
            s=s.replaceAll("<", "");
            s=s.replaceAll(">", "");
            s=s.replaceAll("x=", "");
            s=s.replaceAll("y=", "");
            s=s.replaceAll("z=", "");
            String[] values=s.split(", ");
            Moon moon = new Moon(Integer.parseInt(values[0]),
                                 Integer.parseInt(values[1]),
                                 Integer.parseInt(values[2]));
            moons.add(moon);
        }
        return moons;
    }
    static int getTotalEnergy(List<String> input) {
        List<Moon> moons = parseInput(input);

        for (int i=0; i<1000; i++) {
            updateVelocity(moons.get(0), moons.get(1));
            updateVelocity(moons.get(0), moons.get(2));
            updateVelocity(moons.get(0), moons.get(3));
            updateVelocity(moons.get(1), moons.get(2));
            updateVelocity(moons.get(1), moons.get(3));
            updateVelocity(moons.get(2), moons.get(3));

            for ( Moon moon : moons ) {
                updatePosition(moon);
            }
        }
        int totalEnergy = 0;
        for (Moon m : moons) {
            totalEnergy+=m.getEnergy();
        }
        return totalEnergy;
    }
    static long getCycleLength(List<String> input ) {
        List<Moon> moons = parseInput(input);
        Moon moon1org = new Moon(moons.get(0));
        Moon moon2org = new Moon(moons.get(1));
        Moon moon3org = new Moon(moons.get(2));
        Moon moon4org = new Moon(moons.get(3));

        long steps = 0L;

        long xCycleLength = -1L;
        long yCycleLength = -1L;
        long zCycleLength = -1L;

        do {
            updateVelocity(moons.get(0), moons.get(1));
            updateVelocity(moons.get(0), moons.get(2));
            updateVelocity(moons.get(0), moons.get(3));
            updateVelocity(moons.get(1), moons.get(2));
            updateVelocity(moons.get(1), moons.get(3));
            updateVelocity(moons.get(2), moons.get(3));
            for (Moon m : moons) {
                updatePosition(m);
            }
            steps++;

            if (xCycleLength < 0 &&
                    moons.get(0).getX() == moon1org.getX() && moons.get(0).getVelX() == 0 &&
                    moons.get(1).getX() == moon2org.getX() && moons.get(1).getVelX() == 0 &&
                    moons.get(2).getX() == moon3org.getX() && moons.get(2).getVelX() == 0 &&
                    moons.get(3).getX() == moon4org.getX() && moons.get(3).getVelX() == 0) {
                xCycleLength = steps;
            }
            if (yCycleLength < 0 &&
                    moons.get(0).getY() == moon1org.getY() && moons.get(0).getVelY() == 0 &&
                    moons.get(1).getY() == moon2org.getY() && moons.get(1).getVelY() == 0 &&
                    moons.get(2).getY() == moon3org.getY() && moons.get(2).getVelY() == 0 &&
                    moons.get(3).getY() == moon4org.getY() && moons.get(3).getVelY() == 0) {
                yCycleLength = steps;
            }
            if (zCycleLength < 0 &&
                    moons.get(0).getZ() == moon1org.getZ() && moons.get(0).getVelZ() == 0 &&
                    moons.get(1).getZ() == moon2org.getZ() && moons.get(1).getVelZ() == 0 &&
                    moons.get(2).getZ() == moon3org.getZ() && moons.get(2).getVelZ() == 0 &&
                    moons.get(3).getZ() == moon4org.getZ() && moons.get(3).getVelZ() == 0) {
                zCycleLength = steps;
            }
        } while (xCycleLength <= 0 ||
                yCycleLength <= 0 ||
                zCycleLength <= 0);

        long longestCycle = Math.max(xCycleLength, Math.max(yCycleLength, zCycleLength));
        List<Long> primes = new ArrayList<>();
        for (long number = 2; number <= longestCycle; number++) {
            if(isPrime(number)) {
                primes.add(number);
            }
        }

        long product = 1L;
        for (Long prime : primes) {
            int xTimes = 0, yTimes = 0, zTimes = 0;
            while (xCycleLength % prime == 0) {
                xTimes++;
                xCycleLength /= prime;
            }
            while (yCycleLength % prime == 0) {
                yTimes++;
                yCycleLength /= prime;
            }
            while (zCycleLength % prime == 0) {
                zTimes++;
                zCycleLength /= prime;
            }
            int maxTimes = Math.max(xTimes, Math.max(yTimes, zTimes));
            product *= (long) Math.pow(prime, maxTimes);
        }
        return product;
    }
    public static void main(String[] args) {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        System.out.println("Day12A: " + getTotalEnergy(inputs)); // 12070
        System.out.println("Day12B: " + getCycleLength(inputs)); // 500903629351944
    }
}