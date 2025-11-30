package year2021.day24;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day24 {

    static Object solveA(List<String> values) {
        for(int d1 = 1; d1 >= 1; d1--) {
            for (int d2 = 2; d2 >= 1; d2--) {
                for (int d3 = 9; d3 >= 8; d3--) {
                    for (int d4 = 9; d4 >= 4; d4--) {
                        for (int d5 = 6; d5 >= 1; d5--) {
                            for (int d6 = 9; d6 >= 2; d6--) {
                                for (int d7 = 9; d7 >= 3; d7--) {
                                    for (int d8 = 7; d8 >= 1; d8--) {
                                        for (int d9 = 8; d9 >= 1; d9--) {
                                            for (int d10 = 2; d10 >= 1; d10--) {
                                                for (int d11 = 9; d11 >= 7; d11--) {
                                                    for (int d12 = 3; d12 >= 1; d12--) {
                                                        for (int d13 = 9; d13 >= 8; d13--) {
                                                            for (int d14 = 9; d14 <= 9; d14--) {
                                                                List<Integer> digits = Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14);


                                                                if(getZ(digits).equals(0L))
                                                                    return asNumber(digits);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static Long getZ(List<Integer> digits) {
        Long zValue = 0L;

        zValue = zValue*26+digits.get(0)+12;
            zValue= zValue*26+digits.get(1)+7;
                zValue = zValue*26+digits.get(2)+1;
                    zValue = zValue*26+digits.get(3)+2;

                    // --> digits.get(4)+5 == digits.get(3)+2
                    if (digits.get(4)+5==zValue%26) { zValue = zValue/26; }
                    else { zValue = zValue-zValue%26+digits.get(4)+4; }

                    zValue = zValue*26+digits.get(5)+15;

                        zValue = zValue*26+digits.get(6)+11;
                        // --> digits.get(7)+13 == digits.get(6)+11
                        if (digits.get(7)+13==zValue%26) { zValue = zValue/26; }
                        else { zValue = zValue-zValue%26+digits.get(7)+5; }

                    // --> digits.get(8)+16 == digits.get(5)+15
                    if (digits.get(8)+16==zValue%26 ) { zValue = zValue/26; }
                    else { zValue = zValue-zValue%26+3+digits.get(8); }

                // --> digits.get(9)+8 == digits.get(2)+1
                if (digits.get(9)+8==zValue%26) { zValue = zValue/26; }
                else { zValue = zValue-zValue%26+9+digits.get(9); }

                zValue = zValue*26+digits.get(10)+2;

                // --> digits.get(11)+8 == digits.get(10)+2
                if (digits.get(11)+8==zValue%26) { zValue = zValue/26; }
                else { zValue = zValue-zValue%26+digits.get(11)+3; }

            // --> digits.get(12) == digits.get(1)+7
            if (digits.get(12)==zValue%26) { zValue = zValue/26; }
            else { zValue =zValue-zValue%26+digits.get(12)+3; }

        // --> digits.get(13)+4 == digits.get(0)+12
        if (digits.get(13)+4==zValue%26) { zValue = zValue/26; }
        else { zValue = zValue-zValue%26+digits.get(13)+11; }

        return zValue;
    }

    private static Long asNumber(List<Integer> digits) {
        Long number =0L;
        for(int digit : digits) {
            number = 10*number+digit;
        }
        return number;
    }

    static Object solveB(List<String> values) {
        for(int d1 = 1; d1 <= 1; d1++) {
            for (int d2 = 1; d2 <= 2; d2++) {
                for (int d3 = 8; d3 <= 9; d3++) {
                    for (int d4 = 4; d4 <= 9; d4++) {
                        for (int d5 = 1; d5 <= 6; d5++) {
                            for (int d6 = 2; d6 <=9; d6++) {
                                for (int d7 = 3; d7 <= 9; d7++) {
                                    for (int d8 = 1; d8 <= 7; d8++) {
                                        for (int d9 = 1; d9 <= 8; d9++) {
                                            for (int d10 = 1; d10 <= 2; d10++) {
                                                for (int d11 = 7; d11 <= 9; d11++) {
                                                    for (int d12 = 1; d12 <= 3; d12++) {
                                                        for (int d13 = 8; d13 <= 9; d13++) {
                                                            for (int d14 = 9; d14 <= 9; d14++) {
                                                                List<Integer> digits = Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14);


                                                                if(getZ(digits).equals(0L))
                                                                    return asNumber(digits);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //12996997829399
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //11841231117189L
    }


    static Long isLegalNumber(List<String> values, List<Integer> inputValues) {
        long x=0L,y=0L,z=0L,w=0L;
        int inputCounter=0;
        for(var val : values) {
            String[] parts = val.split(" ");
            try {
                switch (parts[0]) {
                    case "inp" -> {
                      //  System.out.println("Input("+(inputCounter+1)+"):"+inputValues.get(inputCounter)+" Values at: "+x+" "+y+" "+z+" "+w);
                        System.out.print("z="+z+" ");

                        w= inputValues.get(inputCounter);

                        //if (parts[1].equals("x")) x = inputValues.get(inputCounter);
                        //if (parts[1].equals("y")) y = inputValues.get(inputCounter);
                        //if (parts[1].equals("z")) z = inputValues.get(inputCounter);
                        //if (parts[1].equals("w")) w = inputValues.get(inputCounter);
                        inputCounter++;
                    }
                    case "add" -> {
                        long addWith;
                        if (parts[2].equals("x")) addWith = x;
                        else if (parts[2].equals("y")) addWith = y;
                        else if (parts[2].equals("z")) addWith = z;
                        else if (parts[2].equals("w"))  addWith = w;
                        else addWith = Integer.parseInt(parts[2]);

                        if (parts[1].equals("x")) x += addWith;
                        if (parts[1].equals("y")) y += addWith;
                        if (parts[1].equals("z")) z += addWith;
                        if (parts[1].equals("w")) w += addWith;
                    }
                    case "mul" -> {
                        long multiplyWith;
                        if (parts[2].equals("x")) multiplyWith = x;
                        else if (parts[2].equals("y")) multiplyWith = y;
                        else if (parts[2].equals("z")) multiplyWith = z;
                        else if (parts[2].equals("w")) multiplyWith = w;
                        else multiplyWith = Integer.parseInt(parts[2]);

                        if (parts[1].equals("x")) x *= multiplyWith;
                        if (parts[1].equals("y")) y *= multiplyWith;
                        if (parts[1].equals("z")) z *= multiplyWith;
                        if (parts[1].equals("w")) w *= multiplyWith;
                    }
                    case "div" -> {
                        long divideWith;
                        if (parts[2].equals("x")) divideWith = x;
                        else if (parts[2].equals("y")) divideWith = y;
                        else if (parts[2].equals("z")) divideWith = z;
                        else if (parts[2].equals("w")) divideWith = w;
                        else divideWith = Integer.parseInt(parts[2]);

                        if (divideWith == 0) throw new IllegalArgumentException("Div by Zero!");

                        if (parts[1].equals("x")) x /= divideWith;
                        if (parts[1].equals("y")) y /= divideWith;
                        if (parts[1].equals("z")) z /= divideWith;
                        if (parts[1].equals("w")) w /= divideWith;
                    }
                    case "mod" -> {
                        long modWith;
                        if (parts[2].equals( "x")) modWith = x;
                        else if (parts[2].equals("y")) modWith = y;
                        else if (parts[2].equals("z")) modWith = z;
                        else if (parts[2].equals("w")) modWith = w;
                        else modWith = Integer.parseInt(parts[2]);

                        if (modWith <= 0) throw new IllegalArgumentException("Mod by Zero or Negative!");

                        if (parts[1].equals("x") && x < 0) throw new IllegalArgumentException("Mod by Zero or Negative!");
                        if (parts[1].equals("y") && y < 0) throw new IllegalArgumentException("Mod by Zero or Negative!");
                        if (parts[1].equals("z") && z < 0) throw new IllegalArgumentException("Mod by Zero or Negative!");
                        if (parts[1].equals("w") && w < 0) throw new IllegalArgumentException("Mod by Zero or Negative!");


                        if (parts[1].equals("x")) x %= modWith;
                        if (parts[1].equals("y")) y %= modWith;
                        if (parts[1].equals("z")) z %= modWith;
                        if (parts[1].equals("w")) w %= modWith;
                    }
                    case "eql" -> {
                        long eqlWith;
                        if (parts[2].equals("x")) eqlWith = x;
                        else if (parts[2].equals("y")) eqlWith = y;
                        else if (parts[2].equals("z")) eqlWith = z;
                        else if (parts[2].equals("w")) eqlWith = w;
                        else eqlWith = Integer.parseInt(parts[2]);

                        if (parts[1].equals("x")) x = (Objects.equals(x, eqlWith) ? 1L : 0L);
                        if (parts[1].equals("y")) y = (Objects.equals(y, eqlWith) ? 1L : 0L);
                        if (parts[1].equals("z")) z = (Objects.equals(z, eqlWith) ? 1L : 0L);
                        if (parts[1].equals("w")) w = (Objects.equals(w, eqlWith) ? 1L : 0L);
                    }
                }


            } catch (IllegalArgumentException ex) {
                return -1L;
            }
        }


       // System.out.println(asNumber(inputValues)+" --> "+z);
        return z;
    }

}
