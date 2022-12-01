package year2021.day21;

import lombok.Data;
import utils.FileHelper;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day21 {

    static Object solveA(List<String> values) {
        int p1position = Integer.parseInt(values.get(0).substring(values.get(0).length()-1));
        int p2position = Integer.parseInt(values.get(1).substring(values.get(1).length()-1));

        int p1score=0, p2score=0;
        int dice=1;
        long timesRolled=0;
        int goal=1000;
        boolean p1Turn=true;

        while(p1score<goal && p2score<goal) {
            if(p1Turn) {
                p1position = (p1position+3*dice+3)%10;
                if (p1position == 0) p1position+=10;
                p1score+=p1position;
            } else {
                p2position = (p2position+3*dice+3)%10;
                if (p2position == 0) p2position+=10;

                p2score+=p2position;
            }
            p1Turn = !p1Turn;
            dice+=3;
            timesRolled+=3;
        }
        return Math.min(p1score, p2score)*timesRolled;

    }
    static Object solveB(List<String> values) {

        int p1position = Integer.parseInt(values.get(0).substring(values.get(0).length()-1));
        int p2position = Integer.parseInt(values.get(1).substring(values.get(1).length()-1));

        Game startGame = new Game(0, p1position, 0, p2position, true);

        HashMap<Game, Long> ongoingGamesMap = new HashMap<>();
        Queue<Game> ongoingGames = new LinkedList<>();

        ongoingGames.add(startGame);
        ongoingGamesMap.put(startGame,1L);

        long wonBy1 =0L, wonBy2=0L;
        while(!ongoingGames.isEmpty()) {
            Game current = ongoingGames.poll();
            Long numberOfGames = ongoingGamesMap.get(current);
            ongoingGamesMap.remove(current);

            if(current.isWonByPlayer1()) {
                wonBy1+=numberOfGames;
                continue;
            }
            if (current.isWonByPlayer2()) {
                wonBy2+=numberOfGames;
                continue;
            }
            List<Game> outcomes = current.getOutComes();
            for(Game g : outcomes) {
                if (ongoingGamesMap.containsKey(g)) {
                    ongoingGamesMap.put(g, numberOfGames+ongoingGamesMap.get(g));
                } else {
                    ongoingGamesMap.put(g, numberOfGames);
                    ongoingGames.add(g);
                }
            }
        }
        return Math.max(wonBy1, wonBy2);
    }
    @Data
    static class Game {
        private int p1Score,p2Score;
        private int p1Position, p2Position;

        boolean p1Turn;

        public Game(int p1Score, int p1Position, int p2Score, int p2Position, boolean p1Turn) {
            this.p1Score=p1Score;
            this.p1Position=p1Position;
            this.p2Score=p2Score;
            this.p2Position=p2Position;
            this.p1Turn=p1Turn;
        }

        boolean isWonByPlayer1() {
            return p1Score >= 21;
        }
        boolean isWonByPlayer2() {
            return p2Score >= 21;
        }
        List<Game> getOutComes() {
            List<Game> outcomes = new ArrayList<>();
            int newPos, newScore;
            if (p1Turn) {
                for(int die1 = 1; die1 < 4; die1++) {
                    for(int die2 = 1; die2 < 4; die2++) {
                        for (int die3 = 1; die3 < 4; die3++) {
                            newScore = p1Score;
                            newPos = p1Position;

                            newPos += die1;
                            newPos %= 10;
                            if (newPos == 0) newPos+=10;

                            newPos += die2;
                            newPos %= 10;
                            if (newPos == 0) newPos+=10;

                            newPos += die3;
                            newPos %= 10;
                            if (newPos == 0) newPos+=10;
                            newScore += newPos;


                            outcomes.add(new Game(newScore, newPos, p2Score, p2Position, false));
                        }
                    }
                }

            } else {

                for(int die1 = 1; die1 < 4; die1++) {
                    for(int die2 = 1; die2 < 4; die2++) {
                        for (int die3 = 1; die3 < 4; die3++) {
                            newScore = p2Score;
                            newPos = p2Position;

                            newPos += die1;
                            newPos %= 10;
                            if (newPos == 0) newPos+=10;

                            newPos += die2;
                            newPos %= 10;
                            if (newPos == 0) newPos+=10;

                            newPos += die3;
                            newPos %= 10;
                            if (newPos == 0) newPos+=10;
                            newScore += newPos;

                            outcomes.add(new Game(p1Score, p1Position, newScore , newPos, true));
                        }
                    }
                }
            }
            return outcomes;
        }
        @Override
        public boolean equals(Object o) {
            Game other = (Game) o;
            if (getP1Score() != other.getP1Score())
                return false;
            if (getP1Position() != other.getP1Position())
                return false;
            if (getP2Score() != other.getP2Score())
                return false;
            if (getP2Position() != other.getP2Position())
                return false;
            return isP1Turn() == other.isP1Turn();
        }
        @Override
        public int hashCode() {
            return getP1Score()*37+getP2Score()*11-getP1Position()*133+getP2Position()*5+(p1Turn?199999:-17)+12;
        }

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

        System.out.println(day + "A: ("+timePart1+" ms)\t"+ansA); //913560
        System.out.println(day + "B: ("+timePart2+" ms)\t"+ansB); //110271560863819
    }
}
