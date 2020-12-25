package year2020.day21;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

public class Day21 {


    static int solveA(List<String> values) {
        HashMap<String, List<String>> allergensMap = new HashMap<>();
        List<String> allIngredients = new ArrayList<>();
        for(var row : values) {
            List<String> ingredients = new ArrayList<>();
            List<String> allergens = new ArrayList<>();

            boolean firstPart=true;
            for(String part : row.split(" ")) {
                if(part.contains("contains")) {
                    firstPart=false;
                    continue;
                }
                if (firstPart) ingredients.add(part);
                else allergens.add(part.replaceAll("\\)", "").replaceAll(",", ""));
            }
            allIngredients.addAll(ingredients);
            for(var allergen : allergens) {
                List<String> filteredIngredients = allergensMap.getOrDefault(allergen, new ArrayList<>(ingredients));
                filteredIngredients.retainAll(ingredients);
                allergensMap.put(allergen, filteredIngredients);
            }
        }
        for(var ingredientsList : allergensMap.values()) {
            allIngredients.removeAll(ingredientsList);
        }
        return allIngredients.size();
    }


    static String solveB(List<String> values) {
        HashMap<String, List<String>> allergensMap = new HashMap<>();
        for (var row : values) {
            List<String> ingredients = new ArrayList<>();
            List<String> allergens = new ArrayList<>();

            boolean firstPart = true;
            for (String part : row.split(" ")) {
                if (part.contains("contains")) {
                    firstPart = false;
                    continue;
                }
                if (firstPart) ingredients.add(part);
                else allergens.add(part.replaceAll("\\)", "").replaceAll(",", ""));
            }
            for (var allergen : allergens) {
                List<String> filteredIngredients = allergensMap.getOrDefault(allergen, new ArrayList<>(ingredients));
                filteredIngredients.retainAll(ingredients);
                allergensMap.put(allergen, filteredIngredients);
            }
        }
        while (allergensMap.values().stream().anyMatch(l -> l.size() > 1)) {
            for (var list : allergensMap.values()) {
                if (list.size() == 1) {
                    String item = list.get(0);
                    for (var others : allergensMap.entrySet()) {
                        if (others.getValue().size() > 1) {
                            List<String> tmp = others.getValue();
                            tmp.remove(item);
                            others.setValue(tmp);
                        }
                    }
                }
            }
        }
        var newMap = new TreeMap<>(allergensMap);
        return newMap.values().stream().flatMap(Collection::stream).collect(Collectors.joining(","));

    }


    public static void main (String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2020/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var timePart1 = System.currentTimeMillis()-t0;
        var ansB = solveB(inputs);
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //2203
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //
    }
}
