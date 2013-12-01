package edu.macalester.comp124.textgenerator;

import java.util.*;

/**
 * Captures emission probabilities for a particular phrase.
 *
 * @author Shilad Sen
 */
public class PhraseStats {
    private String from;
    private Map<String, Integer> toCounts = new HashMap<String, Integer>();
    private int total;
    private Random random = new Random();

    public PhraseStats(String from) {
        this.from = from;
        this.total = 0;
    }

    /**
     * Increments the count of an outbound phrase.
     * @param to
     */
    public void increment(String to) {
        if (!toCounts.containsKey(to)) {
            toCounts.put(to, 1);
        } else {
            toCounts.put(to, toCounts.get(to) + 1);
        }
        total++;
    }

    /**
     * Gets the total number of occurrences of a particular outbound phrase.
     * @param to
     */
    public int getCount(String to) {
        return toCounts.get(to);
    }

    /**
     * Get the total count of all occurrences of a particular phrase.
     * @return
     */
    public int getTotalCount() {
        return total;
    }

    /**
     * Select a random outbound phrase based on occurrence probabilities.
     * @return
     */
    public String pickRandomTo() {
        List<String> keys = new ArrayList<String>(toCounts.keySet());
        return keys.get(random.nextInt(keys.size()));
    }

    public String getFrom() {
        return from;
    }
}
