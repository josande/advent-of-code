package year2021.day11;

import lombok.Getter;
import lombok.Setter;
import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {

    @Getter
    @Setter
    static class Octopus {
        int power;
        int timesFlashed=0;
        boolean hasFlashed=false;
        Octopus(char power) {
            this.power=Integer.parseInt(""+power);
        }
        public void addPower(int extraPower) {
            power+=extraPower;
        }
        public boolean willFlash() {
            return !hasFlashed&&power>9;
        }

        public void resetAfterFlash() {
            if(hasFlashed) {
                power=0;
                timesFlashed++;
                hasFlashed=false;
            }
        }
    }
    static Object solveA(List<String> values) {
        
        HashMap<Point, Octopus> octopi = getOctopi(values);

        for(int day=0; day<100; day++) {
            octopi.values().forEach(o->o.addPower(1));
            handleFlashes(octopi);
            octopi.values().forEach(Octopus::resetAfterFlash);
        }
        int totalFlashes=0;
        for(Octopus o :octopi.values()) {
            totalFlashes+=o.getTimesFlashed();
        }
        return totalFlashes;
    }

    private static HashMap<Point, Octopus> getOctopi(List<String> values) {
        HashMap<Point, Octopus> octopi = new HashMap<>();
        for(int y=0; y<values.size(); y++) {
            for(int x=0; x<values.get(y).length(); x++) {
               octopi.put(new Point(x,y), new Octopus(values.get(y).charAt(x)));
            }
        }
        return octopi;
    }

    private static void handleFlashes(HashMap<Point, Octopus> octopi) {
        boolean doCheckFlashed = true;
        while (doCheckFlashed) {
            doCheckFlashed = false;
            for (Map.Entry<Point, Octopus> entry : octopi.entrySet()) {
                if (entry.getValue().willFlash()) {
                    entry.getValue().setHasFlashed(true);
                    octopi.keySet().stream().filter(p -> p.isNeighbour(entry.getKey())).map(octopi::get).forEach(o -> o.addPower(1));
                    doCheckFlashed = true;
                }
            }
        }
    }

    static Object solveB(List<String> values) {

        HashMap<Point, Octopus> octopi = getOctopi(values);

        for(int day=1; ; day++) {
            octopi.values().forEach(o->o.addPower(1));
            handleFlashes(octopi);
            if(octopi.values().stream().allMatch(o -> o.isHasFlashed())) {
                return day;
            }
            octopi.values().forEach(Octopus::resetAfterFlash);
        }
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //1729
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
