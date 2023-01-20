package wordStats.analysisService;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordAnalysisService {
    private ArrayList<String> words;
    private int NUM_WORKER_THREADS;
    private ExecutorService threadPool;
    private Result result;
    private CountDownLatch countDownLatch;

    public WordAnalysisService(ArrayList<String> words,int num_worker_threads) {
        this.words = words;
        this.result = new Result();
        this.NUM_WORKER_THREADS = num_worker_threads;
        threadPool = Executors.newFixedThreadPool(NUM_WORKER_THREADS);
        this.countDownLatch = new CountDownLatch(NUM_WORKER_THREADS);
    }

    public void startAnalysis(){
        int taskSize = words.size() / NUM_WORKER_THREADS;
        for(int taskNum=0;taskNum<NUM_WORKER_THREADS;taskNum++){
            int startIndex = taskNum * taskSize;
            int endIndex = startIndex + taskSize;

            // create partition of the words for current task thread
            ArrayList<String> taskWords = new ArrayList<>();
            for(int i=startIndex;i<endIndex;i++){
                taskWords.add(new String(words.get(i)));
            }

            threadPool.execute(new AnalysisTask(taskWords,result,countDownLatch));
        }
    }

    public void getResult() throws InterruptedException{
        countDownLatch.await();
        System.out.println(result.toString());
    }
}
