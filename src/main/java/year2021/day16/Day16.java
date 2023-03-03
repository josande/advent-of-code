package year2021.day16;

import lombok.Data;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class Day16 {

    @Data
    static class Package {
        int version;
        int type;
        long value=0;
        String inputString;
        int packageLength=0;

        List<Package> subpackages = new ArrayList<>();

        Package(String binaryString) {
            inputString = binaryString;
            version = getPacketVersion(binaryString);
            type = getPacketTypeId();

            if(isOperatorPackage()) {
                findSubPackages();
            } else {
                value = getLiteralValue();
            }
            inputString= inputString.substring(0, packageLength);
        }


        long getLiteralValue() {
            int currentPos = 6;

            StringBuilder newBinaryValue = new StringBuilder();
            int literalValueLength=0;
            while(inputString.length() >= currentPos+5 ) {
                literalValueLength+=5;
                newBinaryValue.append(inputString, currentPos + 1, currentPos + 5);
                if (inputString.charAt(currentPos) == '0') {
                    packageLength = 6 + literalValueLength;
                    return Long.parseLong(newBinaryValue.toString(),2);
                }
                currentPos+=5;
            }

            throw new IllegalArgumentException("Could not parse value!");
        }

        void findSubPackages() {
            if(getLengthTypeId(inputString) == 0) {
                //15-bit number representing the number of bits in the sub-packets.
                int startPos = 22;
                int totalPackageLengthForSubPackage= Integer.parseInt(inputString.substring(7, startPos),2);

                int currentPos=startPos;

                int packageLengthSoFar = 0;
                this.packageLength +=startPos;
                while (totalPackageLengthForSubPackage > packageLengthSoFar) {
                    Package child = new Package(inputString.substring(currentPos));
                    packageLengthSoFar+=child.getPackageLength();
                    subpackages.add(child);
                    this.packageLength += child.getPackageLength();
                    currentPos+=child.getPackageLength();
                }
                this.packageLength = startPos+totalPackageLengthForSubPackage;
            } else {
                //11-bit number representing the number of sub-packets
                int numberOfSubPackages = Integer.parseInt(inputString.substring(7,18),2);
                String remaining = inputString.substring(18);
                packageLength+=18;
                for(int packageNumber = 0; packageNumber < numberOfSubPackages; packageNumber++ ) {
                    Package child = new Package(remaining);
                    packageLength+= child.getPackageLength();
                    subpackages.add(child);
                    remaining=remaining.substring(child.getPackageLength());
                }
            }
        }
        boolean isOperatorPackage() {
            return getPacketTypeId() != 4;
        }
        int getPacketTypeId() {
            return Integer.parseInt(inputString.substring(3,6),2);
        }

        Long evaluate() {
            switch (type) {
                case 0 -> {
                    long res=0L;
                    for (Package sp : subpackages) {
                        res+=sp.evaluate();
                    }
                    return res;
                }
                case 1 -> {
                    long res=1L;
                    for (Package sp : subpackages) {
                        res*=sp.evaluate();
                    }
                    return res;
                }
                case 2 -> {
                    long res=Long.MAX_VALUE;
                    for (Package sp : subpackages) {
                        res=Math.min(res, sp.evaluate());
                    }
                    return res;
                }
                case 3 -> {
                    long res=0;
                    for (Package sp : subpackages) {
                        res=Math.max(res, sp.evaluate());
                    }
                    return res;
                }
                case 4 -> {
                    return value;
                }
                case 5 -> {
                    if (subpackages.get(0).evaluate() > subpackages.get(1).evaluate()) return 1L;
                    else return 0L;
                }
                case 6 -> {
                    if (subpackages.get(0).evaluate() < subpackages.get(1).evaluate()) return 1L;
                    else return 0L;
                }
                case 7 -> {
                    if (subpackages.get(0).evaluate().equals(subpackages.get(1).evaluate())) return 1L;
                    else return 0L;
                }
            }
            throw new IllegalArgumentException("Unknown type?!");

        }
    }


    static Object solveA(List<String> values) {
        String binaryString = hex2Bin(values.get(0));

        Package pack = new Package(binaryString);

        return getVersionRecursively(pack);
    }

    static Long getVersionRecursively(Package p) {
        long ver =0L;
        for(Package child : p.getSubpackages()) {
            ver+= getVersionRecursively(child);
        }

        return ver + p.getVersion();
    }


    static Object solveB(List<String> values) {

        String binaryString = hex2Bin(values.get(0));

        Package pack = new Package(binaryString);



        return pack.evaluate();
    }



    static String hex2Bin(String hexString) {
        StringBuilder result = new StringBuilder();
        for(char c : hexString.toCharArray()) {
           switch (c) {
               case '0' -> result.append("0000");
               case '1' -> result.append("0001");
               case '2' -> result.append("0010");
               case '3' -> result.append("0011");
               case '4' -> result.append("0100");
               case '5' -> result.append("0101");
               case '6' -> result.append("0110");
               case '7' -> result.append("0111");
               case '8' -> result.append("1000");
               case '9' -> result.append("1001");
               case 'A' -> result.append("1010");
               case 'B' -> result.append("1011");
               case 'C' -> result.append("1100");
               case 'D' -> result.append("1101");
               case 'E' -> result.append("1110");
               case 'F' -> result.append("1111");
           }
        }
        return result.toString();
    }




    static int getPacketVersion(String binaryString) {
        return Integer.parseInt(binaryString.substring(0,3),2);
    }

    static int getLengthTypeId(String binaryString) {
        return Integer.parseInt(binaryString.substring(6,7),2);
    }




    public static void main(String[] args){
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2021/"+day+".txt");

        var t0 = System.currentTimeMillis();
        var ansA = solveA(inputs);
        var t1 = System.currentTimeMillis();
        var ansB = solveB(inputs);
        var timePart1 = t1-t0;
        var timePart2 = System.currentTimeMillis()-t1;

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //1014
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //1922490999789
    }
}
