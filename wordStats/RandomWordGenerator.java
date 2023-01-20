package wordStats;

import java.util.Random;

public class RandomWordGenerator {
    private static String[] words;
    private static Random random = new Random();

    static{
        words = new String[]{
                "apple",
                "ability",
                "accept",
                "accident",
                "advantage",
                "all",
                "ab",
                "also",
                "annual",
                "banana",
                "ba",
                "Hello",
                "gecko",
                "tintin",
                "bulbul",
                "anand",
                "ballu",
                "devesh",
                "macintosh"

        };
    }

    public static String getRandomWord(){
        return words[random.nextInt(words.length)];
    }
}
