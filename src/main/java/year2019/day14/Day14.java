package year2019.day14;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day14 {


    static class Component {
        String name;
        long amount;

        Component(long amount, String name) {
            this.amount = amount;
            this.name = name;
        }

        Component(Component component) {
            this.amount = component.getAmount();
            this.name = component.getName();
        }


        public String getName() {
            return name;
        }

        public long getAmount() {
            return amount;
        }

        public void multiplyAmount(long factor) {
            this.amount *= factor;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;

            if (!(obj instanceof Component)) {
                return false;
            }

            Component o = (Component) obj;
            return this.getName().equals(o.getName()) &&
                    this.getAmount() == o.getAmount();
        }
    }

    static HashMap<String, Component> materialComponents = new HashMap<>();
    static HashMap<String, Long> leftoverMaterial = new HashMap<>();
    static HashMap<Component, List<Component>> transformations = new HashMap<>();

    static void sortInput(List<String> inputs) {
        materialComponents = new HashMap<>();
        leftoverMaterial = new HashMap<>();
        transformations = new HashMap<>();
        for (String transformation : inputs) {
            if (transformation.isEmpty()) continue;
            String parts = transformation.split(" => ")[0];
            String result = transformation.split(" => ")[1];

            String resultAmount = result.split(" ")[0];
            String resultName = result.split(" ")[1];

            Component resultComponent = new Component(Integer.parseInt(resultAmount), resultName);
            List<Component> ingredientComponents = new ArrayList<>();

            for (String ingredient : parts.split(", ")) {
                String amount = ingredient.split(" ")[0];
                String name = ingredient.split(" ")[1];
                ingredientComponents.add(new Component(Integer.parseInt(amount), name));
            }
            materialComponents.put(resultName, resultComponent);
            transformations.put(resultComponent, ingredientComponents);
        }
    }

    static List<Component> getComponentsToMake(long amountWanted, String material) {
        List<Component> thingsNeeded = new ArrayList<>();
        if (material.equals("ORE")) {
            thingsNeeded.add(new Component(amountWanted, material));
            return thingsNeeded;
        }
        long inLeftOverPile = leftoverMaterial.getOrDefault(material, 0L);
        if (inLeftOverPile >= amountWanted) {
            leftoverMaterial.put(material, inLeftOverPile - amountWanted);
            return new ArrayList<>();
        }
        leftoverMaterial.remove(material);
        amountWanted -= inLeftOverPile;


        Component component = materialComponents.get(material);
        long factor = amountWanted / component.getAmount();
        while (component.getAmount() * factor < amountWanted) {
            factor++;
        }
        long leftOver = component.getAmount() * factor - amountWanted;
        leftoverMaterial.put(material, leftOver);

        List<Component> components = transformations.get(component);

        for (Component c : components) {
            Component comp = new Component(c);
            comp.multiplyAmount(factor);
            thingsNeeded.add(comp);
        }

        return thingsNeeded;
    }

    static public long amountOfOreForOneFuel() {
        return amountOfOreForFuel(1);
    }

    static public long amountOfOreForFuel(long amountOfFuel) {
        List<Component> before = new ArrayList<>(getComponentsToMake(amountOfFuel, "FUEL"));
        List<Component> after;

        while (true) {
            after = transformComponents(before);
            if (after.equals(before)) break;
            before = after;
        }
        long amount = 0;
        for (Component c : after) {
            amount += c.getAmount();
        }
        return amount;
    }

    static public List<Component> transformComponents(List<Component> components) {
        List<Component> results = new ArrayList<>();
        for (Component c : components) {
            results.addAll(getComponentsToMake(c.getAmount(), c.getName()));
        }
        return results;
    }


    public static void main(String[] args) {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        sortInput(inputs);

        long oreForOneFuel = amountOfOreForOneFuel();
        System.out.println("Day14A: " + oreForOneFuel);


        long maxOre = 1000000000000L;

        long lowerLimit = maxOre / oreForOneFuel;
        long upperLimit = lowerLimit * 2;

        long fuel;
        long oreForFuel;
        while(true) {
            leftoverMaterial=new HashMap<>();
            fuel = (upperLimit+lowerLimit)/2;
            oreForFuel=amountOfOreForFuel(fuel);
            if (oreForFuel>maxOre) {
                upperLimit=fuel;
            }
            else {
                if (amountOfOreForFuel(fuel+1)>maxOre) {
                    break;
                }
                lowerLimit=fuel;
            }
        }
        System.out.println("Day14A: " + fuel);
    }
}