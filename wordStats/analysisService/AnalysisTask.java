package wordStats.analysisService;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class AnalysisTask implements Runnable{
    private ArrayList<String> words;
    private Result result;
    private Result localResult;
    private CountDownLatch countDownLatch;
    public AnalysisTask(ArrayList<String> words, Result result,CountDownLatch countDownLatch) {
        this.words = words;
        this.result = result;
        this.localResult = new Result();
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for(String word : words){
            int len = word.length();
            if(len < localResult.getMinLength()) {
                localResult.setMinLength(len);
                localResult.setSmallestWord(word);
            }
            if(len > localResult.getMaxLength()){
                localResult.setMaxLength(len);
                localResult.setLongestWord(word);
            }
            localResult.incrementWordCount(word);
        }

        updateResult();
        countDownLatch.countDown();
    }

    public void updateResult(){
        // update global result Min length and smallest word
        if(result.getMinLength() > localResult.getMinLength()){
            result.setMinLength(localResult.getMinLength());
            result.setSmallestWord(localResult.getSmallestWord());
        }

        // update global result max length and longest word
        if(result.getMaxLength() < localResult.getMaxLength()){
            result.setMaxLength(localResult.getMaxLength());
            result.setLongestWord(localResult.getLongestWord());
        }

        // update global result word count dictionary/map
        for(String word : localResult.getWords()){
            result.incrementWordCount(word,localResult.getWordCount(word));
        }
    }
}
