package year2016.day10;

import utils.AdventOfCode;
import utils.FileHelper;
import utils.Reporter;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day10 implements AdventOfCode {

    static class Bot {
        private final Integer lowToBot;
        private final Integer highToBot;
        private final Integer lowToOutput;
        private final Integer highToOutput;

        private final ArrayList<Integer> values = new ArrayList<>();
        public Bot(Integer lowToBot, Integer highToBot, Integer lowToOutput, Integer highToOutput) {
            this.lowToBot = lowToBot;
            this.highToBot = highToBot;

            this.lowToOutput = lowToOutput;
            this.highToOutput = highToOutput;

        }
        public void addNumber(int number) {
            values.add(number);
        }
        public boolean distribute(HashMap<Integer, Bot> bots, HashMap<Integer, ArrayList<Integer>> outputs) {
            if(values.size()==2) {
                Collections.sort(values);

                if(lowToBot != null)
                    bots.get(lowToBot).addNumber(values.get(0));
                if(lowToOutput != null) {
                    if(!outputs.containsKey(lowToOutput))
                        outputs.put(lowToOutput, new ArrayList<>());
                    outputs.get(lowToOutput).add(values.get(0));
                }

                if(highToBot != null)
                    bots.get(highToBot).addNumber(values.get(1));
                if(highToOutput != null) {
                    if(!outputs.containsKey(highToOutput))
                        outputs.put(highToOutput, new ArrayList<>());
                    outputs.get(highToOutput).add(values.get(1));
                }
                values.clear();
                return true;
            }
            return false;
        }

    }
    @Override
    public Object solveA(List<String> values) {
        HashMap<Integer, Bot> bots = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> outputs = new HashMap<>();

        for(var val:values) {
            if(val.startsWith("bot")) {
                String[] words = val.split(" ");
                int botId = Integer.parseInt(words[1]);
                Integer lowToBot=null, lowToOutput=null;
                if(words[5].equals("bot")) {
                    lowToBot = Integer.parseInt(words[6]);
                } else {
                    lowToOutput = Integer.parseInt(words[6]);
                }
                Integer highToBot=null, highToOutput=null;
                if(words[10].equals("bot")) {
                    highToBot = Integer.parseInt(words[11]);
                } else {
                    highToOutput = Integer.parseInt(words[11]);
                }

                Bot bot = new Bot(lowToBot, highToBot, lowToOutput, highToOutput);
                bots.put(botId, bot);
            }
        }
        for(var val:values) {
            if(val.startsWith("value")) {
                String[] words = val.split(" ");
                int value =  Integer.parseInt(words[1]);
                int botId =  Integer.parseInt(words[5]);
                bots.get(botId).addNumber(value);
            }
        }
        boolean distribute = true;
        while (distribute) {
            distribute = false;
            for (Map.Entry<Integer, Bot> bot : bots.entrySet()) {
                if(bot.getValue().values.contains(17) && bot.getValue().values.contains(61) ) {
                    return bot.getKey();
                }
                distribute = distribute || bot.getValue().distribute(bots, outputs);
            }
        }
        return -1;
    }

    @Override
    public Object solveB(List<String> values) {
        HashMap<Integer, Bot> bots = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> outputs = new HashMap<>();

        for(var val:values) {
            if(val.startsWith("bot")) {
                String[] words = val.split(" ");
                int botId = Integer.parseInt(words[1]);
                Integer lowToBot=null, lowToOutput=null;
                if(words[5].equals("bot")) {
                    lowToBot = Integer.parseInt(words[6]);
                } else {
                    lowToOutput = Integer.parseInt(words[6]);
                }
                Integer highToBot=null, highToOutput=null;
                if(words[10].equals("bot")) {
                    highToBot = Integer.parseInt(words[11]);
                } else {
                    highToOutput = Integer.parseInt(words[11]);
                }

                Bot bot = new Bot(lowToBot, highToBot, lowToOutput, highToOutput);
                bots.put(botId, bot);
            }
        }
        for(var val:values) {
            if(val.startsWith("value")) {
                String[] words = val.split(" ");
                int value =  Integer.parseInt(words[1]);
                int botId =  Integer.parseInt(words[5]);
                bots.get(botId).addNumber(value);
            }
        }
        boolean distribute = true;
        while (distribute) {
            distribute = false;
            for (Map.Entry<Integer, Bot> bot : bots.entrySet()) {
                distribute = distribute || bot.getValue().distribute(bots, outputs);
            }
        }

        return outputs.get(0).get(0) * outputs.get(1).get(0) * outputs.get(2).get(0);
    }


    public static void main(){
        Reporter.report(new Day10());
    }

}
