package edu.macalester.comp124.textgenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Reena on 5/7/14.
 */
public class WordsAfterBiagram extends BigramCounter {
    /**
     * Map of words and its respective frequencies
     */
    Map<String, Integer> bigramWords = new HashMap<String, Integer>();

    /**
     * Adds a word and its count to the map
     * @param word
     * @param num
     */
    public void addString(String word, Integer num) {
        bigramWords.put(word, num);
    }

    /**
     * Returns the frequency of a word
     * @param key
     * @return
     */
    public Integer getNum(String key) { return bigramWords.get(key); }

    /**
     * Adds the count of a word
     * @param key
     */
    public void incrementNum(String key)  {
        Integer newValue = bigramWords.get(key) + 1;
        bigramWords.put(key,newValue);
    }

    /**
     * Returns the map
     * @return
     */
    public Map<String, Integer> returnMap() { return bigramWords;}

}

