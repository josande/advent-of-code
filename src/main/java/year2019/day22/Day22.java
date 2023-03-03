package year2019.day22;

import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static utils.ArrayHelper.reverseArray;

public class Day22 {

    enum Action {
        DEAL_WITH_INCREMENT("deal with increment "),
        DEAL_INTO_NEW_STACK("deal into new stack"),
        CUT("cut ");

        Action(String name) {
            this.name=name;
        }
        final String name;

        public static Action getFromText(String text) {
            return Arrays.stream(values()).filter(a -> text.startsWith(a.name)).findAny().orElseThrow(() -> new NoSuchElementException("Value Missing!"));
        }
    }

    static BigInteger deckSize;
    static BigInteger cardPosition;
    static long timesToShuffle= 1L;
    static void setCardToTrack(long card) {
        cardPosition = BigInteger.valueOf(card);
    }
    static void setDeckSize(long size) {
        deckSize = BigInteger.valueOf(size);
    }

    static void shuffle(List<String> commands) {
        for(long i=0; i<timesToShuffle; i++) {

            for (String command : commands) {
                String[] words = command.split(" ");
                if (words[0].equals("cut")) {
                    cut(Integer.parseInt(words[1]));
                }
                if (words[0].equals("deal")) {
                    if (words[1].equals("with")) {
                        deal(Integer.parseInt(words[3]));
                    } else {
                        dealIntoNewOrder();
                    }
                }
            }
        }
    }

    static void dealIntoNewOrder() {
        cardPosition=BigInteger.valueOf(deckSize.longValue()-cardPosition.longValue()-1);
    }

    static long getCardPosition() {
        return cardPosition.longValue();
    }
    static void deal(int increment) {
        cardPosition = BigInteger.valueOf(cardPosition.longValue()*increment%deckSize.longValue());
    }
    static void cut(int numberOfCards){
        long cardsToCut = numberOfCards;
        if(numberOfCards == 0){ return;}
        if(numberOfCards < 0) {
            cardsToCut = Math.abs( deckSize.longValue() + numberOfCards );
        }
        if (cardsToCut>cardPosition.longValue()){
            cardPosition = BigInteger.valueOf(deckSize.longValue() - cardsToCut + cardPosition.longValue());
        } else {
            cardPosition=BigInteger.valueOf(cardPosition.longValue() - cardsToCut);
            cardPosition=BigInteger.valueOf(cardPosition.longValue()%deckSize.longValue());
        }
    }
    public static void main(String[] args) {
        long t0=System.currentTimeMillis();
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");

        setDeckSize(10007L);
        setCardToTrack(2019);
        shuffle(inputs);
        System.out.println("Day22A "+ getCardPosition()); //4703
        System.out.println("Day22B "+ seekPosition(BigInteger.valueOf(119315717514047L), BigInteger.valueOf(101741582076661L), 2020, inputs));
        System.out.println("Time: "+(System.currentTimeMillis()-t0)+" ms");
    }



    static class Move {
        Action action;
        int amount;

        public Move(String s) {
            this.action = Action.getFromText(s);
            s = s.replace(action.name, "");
            if(!s.isEmpty())
                amount = Integer.parseInt(s);
            else amount = 0;
        }

        public BigInteger[] execute(BigInteger[] input, BigInteger deckSize) {
            switch (action) {
                case DEAL_INTO_NEW_STACK -> {
                    input[0] = input[0].multiply(BigInteger.valueOf(-1));
                    input[1] = input[1].add(BigInteger.valueOf(1)).multiply(BigInteger.valueOf(-1));
                }
                case CUT -> input[1] = input[1].add(BigInteger.valueOf(amount));
                case DEAL_WITH_INCREMENT -> {
                    BigInteger p = BigInteger.valueOf(amount).modPow(deckSize.subtract(BigInteger.valueOf(2)), deckSize);
                    input[0] = input[0].multiply(p);
                    input[1] = input[1].multiply(p);
                }
            }
            return input;
        }
    }

    private static BigInteger seekPosition(BigInteger deckSize, BigInteger timesShuffled, int position, List<String> input) {
        Move[] moves = input.stream().map(Move::new).toArray(Move[]::new);

        BigInteger[] calc = new BigInteger[] {BigInteger.valueOf(1), BigInteger.valueOf(0)}; //(increment, offset)
        for(Move move : reverseArray(moves)) {
            calc = move.execute(calc, deckSize);
            for(int i = 0; i<calc.length; i++)
                calc[i] = calc[i].mod(deckSize);
        }
        BigInteger pow = calc[0].modPow(timesShuffled, deckSize);
        return pow.multiply(BigInteger.valueOf(position))
                .add(calc[1]
                        .multiply(pow.add(deckSize).subtract(BigInteger.valueOf(1)))
                        .multiply(calc[0].subtract(BigInteger.valueOf(1))
                                .modPow(deckSize.subtract(BigInteger.valueOf(2)), deckSize)))
                .mod(deckSize);
    }
}
