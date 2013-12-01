package edu.macalester.comp124.textgenerator;

import java.util.*;

/**
 * Captures emission probabilities for a particular phrase.
 *
 * @author Shilad Sen
 */
public class PhraseStats {

    // TODO: add instance variables for part 1

    public PhraseStats(String from) {
        // TODO: implement constructor for part 1
    }

    /**
     * Increments the count of an outbound phrase.
     * @param to
     */
    public void increment(String to) {
        // TODO: implement me for part 1
    }

    /**
     * Gets the total number of occurrences of a particular outbound phrase.
     * @param to
     */
    public int getCount(String to) {
        // TODO: implement me for part 1
        return 0;
    }

    /**
     * Get the total count of all occurrences of a particular phrase.
     * @return
     */
    public int getTotalCount() {
        // TODO: implement me for part 1
        return 0;
    }

    /**
     * Select a random outbound phrase based on occurrence probabilities.
     * @return
     */
    public String pickRandomTo() {
        // TODO: implement me for part 2
        return "";
    }

    public String getFrom() {
        // TODO: implement me for part 1
        return "";
    }
}
