package year2019.day08;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class Day08 {

    public static void main() {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        int height = 6, width = 25;
        int numberOfLayers = inputs.get(0).length()/(height*width);

        List<String> layers = new ArrayList<>();
        for (int i=0;i<numberOfLayers;i++) {
            layers.add(inputs.get(0).substring(i*150, (i+1)*150));
        }

        int fewestZeroes =150;
        int checkSum=-1;
        for (String layer : layers) {
            int zeroes = 0, ones=0, twos=0;
                for (char c : layer.toCharArray()) {
                    if (c=='0') {
                        zeroes++;
                    }
                    if (c=='1') {
                        ones++;
                    }
                    if (c=='2') {
                        twos++;
                    }
                }
            if (zeroes < fewestZeroes) {
                fewestZeroes = zeroes;
                checkSum=ones*twos;
            }
        }

        System.out.println("Day08A: "+checkSum);
        System.out.println("Day08B: ");

        for(int h=0; h<6;h++) {
            for (int w=0;w<25;w++) {
                System.out.print(getTopChar(h*25+w, layers));
            }
            System.out.print("\n");
        }

    }
    static char getTopChar(int position, List<String> layers) {
        for (String layer : layers) {
            if (layer.charAt(position)=='1') {
                return '#';
            }
            if (layer.charAt(position)=='0') {
                return ' ';
            }
        }
        System.out.println("Unknown character at pos:"+position);
        return '?';

    }
}
