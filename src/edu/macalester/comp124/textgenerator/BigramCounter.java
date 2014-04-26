package edu.macalester.comp124.textgenerator;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * A class that remembers the frequency of words that follow each bigram.
 *
 * @author Shilad Sen
 */
public class BigramCounter {
    // Add instance variables here for part 1

    /**
     * Increments the counts of a word following a bigram.
     *
     *  There are two special cases to consider:
     *
     * 1) The bigram may be empty "", in which case this method increments
     * an option for the first word
     *
     * 2) The bigram could contain only one word in which case this method
     * increments an option for the second word.
     *
     * @param bigram Two word bigram, with words separated by a space.
     * @param after Word following the bigram
     */
    public void incrementCount(String bigram, String after) {
        // Complete for part 1
    }

    /**
     * Given a string with two consecutive words separated by a space, returns
     * options for the next word. The returned data structure represents words
     * that follow the bigram and the number of times they follow the bigram.
     *
     * There are two special cases to consider:
     *
     * 1) The bigram may be empty "", in which case this method returns
     * options for the first word
     *
     * 2) The bigram could contain only one word in which case this method
     * returns options for the second word.
     *
     * @param bigram Two word bigram, with words separated by a space.
     *
     * @return
     */
    public Map<String, Integer> getWordsAfterBigram(String bigram) {
        // Complete for part 1
        return null;
    }

}
