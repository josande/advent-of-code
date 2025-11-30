package year2019.day01;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;

public class Day01 {

    static int getFuelNeededForMass(int mass) {
        return Math.max(mass/3-2, 0);
    }

    static int getFuelNeededForMassIncludingFuelWeight(int moduleMass) {
        int totalFuel = 0, mass=moduleMass;
        while((mass=getFuelNeededForMass(mass)) > 0 ) { totalFuel += mass; }
        return totalFuel;
    }

    public static void main(){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFileAsIntegers("2019/"+day+".txt");

        int massOfFuel = 0, massOfFuelIncFuel=0;
        for (int mass : inputs ) {
            massOfFuel+=getFuelNeededForMass(mass);
            massOfFuelIncFuel+=getFuelNeededForMassIncludingFuelWeight(mass);
        }
        System.out.println("Day01A: "+massOfFuel); // 3335787
        System.out.println("Day01B: "+massOfFuelIncFuel); // 5000812

    }
}
