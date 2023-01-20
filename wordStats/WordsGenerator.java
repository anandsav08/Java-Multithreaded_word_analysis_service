package wordStats;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordsGenerator {
    private ArrayList<String> words;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int NUM_TASKS;
    private CountDownLatch countDownLatch;

    public WordsGenerator(int NUM_TASKS) {
        this.words = new ArrayList<>();
        this.NUM_TASKS = NUM_TASKS;
        this.countDownLatch = new CountDownLatch(NUM_TASKS);;
    }

    public void generateWords(){
        for(int i=0;i<NUM_TASKS;i++){
            executorService.execute(new WordGeneratorTask(this,countDownLatch));
        }
    }

    public synchronized void addWord(String word){
            words.add(word);
    }

    public ArrayList<String> getWords() throws InterruptedException {
        countDownLatch.await();
        System.out.println("Service finished");
        System.out.println("words generated : "+words.size());
        return words;
    }

}
