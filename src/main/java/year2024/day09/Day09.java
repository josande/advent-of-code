package year2024.day09;

import utils.AdventOfCode;
import utils.Reporter;

import java.util.*;

public class Day09 implements AdventOfCode {

    private record Fragment(int value, int start, int length) implements Comparable<Fragment> {

        @Override
        public int compareTo(Fragment o) {
            return this.start-o.start;
        }
    }

    public static void main(String[] args){
        Reporter.report(new Day09());
    }

    @Override
    public Object solveA(List<String> input) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<Fragment> holes = new ArrayList<>();

        int pos=0;
        int id=0;
        for (int i = 0; i < input.getFirst().length(); i++) {
            int length= Integer.parseInt(""+input.getFirst().charAt(i));
            if(i%2==0) {
                fragments.add(new Fragment(id, pos, length));
                id++;
            } else {
                holes.add(new Fragment(-1, pos, length));
            }
            pos+=length;
        }
        Collections.sort(fragments);

        for (Fragment hole : holes) {
            if (hole.start > fragments.getLast().start)
                break;
            int lengthLeft = hole.length;
            int used = 0;
            while (lengthLeft > 0) {
                var tempFragment = fragments.getLast();

                if (tempFragment.length <= lengthLeft) {
                    fragments.removeLast();
                    fragments.add(new Fragment(tempFragment.value, hole.start + used, tempFragment.length));
                    used += tempFragment.length;
                    lengthLeft -= tempFragment.length;
                } else {
                    fragments.removeLast();
                    fragments.add(new Fragment(tempFragment.value, hole.start + used, lengthLeft));
                    fragments.add(new Fragment(tempFragment.value, tempFragment.start, tempFragment.length - lengthLeft));
                    lengthLeft = 0;
                }
                Collections.sort(fragments);
            }
        }

        long result = 0L;
        for(var f : fragments) {
            for(int l=0; l<f.length; l++) {
                result+= (long) f.value *(f.start+l);
            }
        }
        return result;
    }

    @Override
    public Object solveB(List<String> input) {
        HashMap<Integer, Fragment> fragments = new HashMap<>();
        ArrayList<Fragment> holes = new ArrayList<>();

        int pos=0;
        for (int i = 0; i < input.getFirst().length(); i++) {
            int length= Integer.parseInt(""+input.getFirst().charAt(i));
            if(i%2==0)
                fragments.put(i/2, new Fragment(i/2, pos, length));
            else
                holes.add(new Fragment(-1, pos, length));
            pos+=length;
        }

        for(int toMove = input.getFirst().length()/2; toMove>=0; toMove--) {
            Collections.sort(holes);
            for(int i=0; i<holes.size(); i++) {
                Fragment temp = new Fragment(toMove, fragments.get(toMove).start, fragments.get(toMove).length);
                if(holes.get(i).length>=fragments.get(toMove).length && holes.get(i).start<temp.start) {
                    fragments.put(toMove, new Fragment(toMove, holes.get(i).start, temp.length));
                    if(holes.get(i).length>temp.length)
                        holes.add(new Fragment(-1, holes.get(i).start+temp.length, holes.get(i).length-temp.length));

                    holes.remove(holes.get(i));
                    break;
                }
            }
        }

        long result = 0L;
        for(var f : fragments.values()) {
            for(int i=0; i<f.length; i++) {
                result+= (long) f.value *(f.start+i);
            }
        }

        return result;
    }
}
