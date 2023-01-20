package wordStats;

import wordStats.analysisService.WordAnalysisService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1. Generate 10000 words by 10 worker threads. Each worker generates 1000 words
        int NUM_TASKS = 10;
        WordsGenerator wordsGenerator = new WordsGenerator(NUM_TASKS);
        wordsGenerator.generateWords();
        ArrayList<String> words = wordsGenerator.getWords();

        // Words Analysis
        // Keep num worker threads a multiple of 10
        WordAnalysisService analysisService = new WordAnalysisService(words,10);
        analysisService.startAnalysis();
        analysisService.getResult();
    }
}
