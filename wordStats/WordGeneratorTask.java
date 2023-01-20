package wordStats;

import java.util.concurrent.CountDownLatch;

public class WordGeneratorTask implements Runnable {
    private CountDownLatch countDownLatch;
    WordsGenerator generator;

    public WordGeneratorTask(WordsGenerator wordsGenerator,CountDownLatch countDownLatch) {
        this.generator = wordsGenerator;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run(){
        for(int count=0;count<1000;count++){
            generator.addWord(RandomWordGenerator.getRandomWord());
        }
        countDownLatch.countDown();
    }
}
