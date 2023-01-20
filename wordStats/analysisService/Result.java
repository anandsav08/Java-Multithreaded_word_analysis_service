package wordStats.analysisService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Result {
    private String longestWord;
    private String smallestWord;
    private int minLength;
    private int maxLength;
    private HashMap<String,Integer> wordCount;

    public Result() {
        longestWord = "";
        smallestWord = "";
        minLength = Integer.MAX_VALUE;
        maxLength = Integer.MIN_VALUE;
        wordCount = new HashMap<>();
    }

    public String getLongestWord() {
        return longestWord;
    }

    public synchronized void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }

    public String getSmallestWord() {
        return smallestWord;
    }

    public synchronized void setSmallestWord(String smallestWord) {
        this.smallestWord = smallestWord;
    }

    public int getMinLength() {
        return minLength;
    }

    public synchronized void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public synchronized void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public synchronized void incrementWordCount(String word){
        wordCount.put(word,wordCount.getOrDefault(word,0)+1);
    }

    public Set<String> getWords(){
        return this.wordCount.keySet();
    }

    public int getWordCount(String word){
        return wordCount.getOrDefault(word,0);
    }

    public synchronized void incrementWordCount(String word,int count){
        wordCount.put(word,wordCount.getOrDefault(word,0)+count);
    }

    @Override
    public String toString() {
        return "RESULT{" +
                "\n\tlongestWord='" + longestWord + '\'' +
                "\n\tsmallestWord='" + smallestWord + '\'' +
                "\n\tminLength=" + minLength +
                "\n\tmaxLength=" + maxLength +
                "\n\twordCount=" + wordCount.toString() +
                "\n}";
    }
}
