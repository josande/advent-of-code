package year2017.day20;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.AdventOfCode;
import utils.Point;
import utils.Reporter;

import java.util.*;

public class Day20 implements AdventOfCode {
    public static void main(){
        Reporter.report(new Day20());
    }

    @Override
    public Object solveA(List<String> input) {
        int lowestA=Integer.MAX_VALUE;
        int slowestId=-1;

        int id=0;
        for(String row : input) {
            String acc = row.substring(row.lastIndexOf('<')+1, row.lastIndexOf('>'));
            int totalAc=0;
            for(String  s: acc.split(",")) {
                totalAc+=Math.abs(Integer.parseInt(s));
            }

            if(totalAc<lowestA) {
                slowestId=id;
                lowestA=totalAc;
            }
            id++;

        }
        return slowestId;
    }

    @Data
    @AllArgsConstructor
    private static class Particle {
        Point pos, vel, acc;

        void move() {
            vel = vel.add(acc);
            pos = pos.add(vel);
        }
    }
    @Override
    public Object solveB(List<String> input) {

        ArrayList<Particle> particles = new ArrayList<>();
        for(String row : input) {
            String pos = row.split("=")[1].substring(1, row.split("=")[1].indexOf('>'));
            String vel = row.split("=")[2].substring(1, row.split("=")[2].indexOf('>'));
            String acc = row.split("=")[3].substring(1, row.split("=")[3].indexOf('>'));
            particles.add(
                    new Particle(
                            new Point(
                                    Integer.parseInt(pos.split(",")[0]),
                                    Integer.parseInt(pos.split(",")[1]),
                                    Integer.parseInt(pos.split(",")[2])),
                            new Point(
                                    Integer.parseInt(vel.split(",")[0]),
                                    Integer.parseInt(vel.split(",")[1]),
                                    Integer.parseInt(vel.split(",")[2])),
                            new Point(
                                    Integer.parseInt(acc.split(",")[0]),
                                    Integer.parseInt(acc.split(",")[1]),
                                    Integer.parseInt(acc.split(",")[2]))
                    )
            );
        }
        for(int time=1; time<1000; time++) {
            HashSet<Particle> toRemove= new HashSet<>();
            for (Particle p : particles) p.move();

            for(int i=0; i<particles.size()-1; i++) {
                for(int j=i+1; j<particles.size(); j++) {
                   if(particles.get(i).getPos().equals(particles.get(j).getPos()))
                        toRemove.addAll(List.of(particles.get(i), particles.get(j)));
                }
            }
            particles.removeAll(toRemove);
        }

        return particles.size();
    }
}
