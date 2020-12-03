package year2019.day01;

import utils.FileHelper;

import java.util.List;

public class Day01 {

    static int getFuelNeededForMass(int mass) {
        return Math.max(mass/3-2, 0);
    }

    static int getFuelNeededForMassIncludingFuelWeight(int moduleMass) {
        int totalFuel = 0, mass=moduleMass;
        while((mass=getFuelNeededForMass(mass)) > 0 ) { totalFuel += mass; }
        return totalFuel;
    }

    public static void main(String[] args){
        List<Integer> inputs = new FileHelper().readFileAsIntegers("year2019/day01/input.txt");
        int massOfFuel = 0, massOfFuelIncFuel=0;
        for (int mass : inputs ) {
            massOfFuel+=getFuelNeededForMass(mass);
            massOfFuelIncFuel+=getFuelNeededForMassIncludingFuelWeight(mass);
        }
        System.out.println("Day01A: "+massOfFuel);
        System.out.println("Day01B: "+massOfFuelIncFuel);
    }
}
