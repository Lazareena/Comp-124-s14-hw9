package edu.macalester.comp124.textgenerator;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Shilad Sen
 */
public class TestBigramCounter {

    /**
     * This should be the easiest test to pass
     */
    @Test
    public void testSimple() {
        BigramCounter bc = new BigramCounter();
        bc.incrementCount("Hello, my", "world!");
        bc.incrementCount("Hello, my", "dog");
        bc.incrementCount("Hello, my", "cat");
        bc.incrementCount("Hello, my", "world!");

        Map<String, Integer> counts = bc.getWordsAfterBigram("Hello, my");

        assertEquals(3, counts.size());
        assertTrue(counts.containsKey("dog"));
        assertTrue(counts.containsKey("cat"));
        assertTrue(counts.containsKey("world!"));

        assertEquals(1, (int)counts.get("dog"));
        assertEquals(1, (int)counts.get("cat"));
        assertEquals(2, (int)counts.get("world!"));
    }



    /**
     * This should be the easiest test to pass
     */
    @Test
    public void testEmpty() {
        BigramCounter bc = new BigramCounter();
        bc.incrementCount("Hello, my", "world!");
        bc.incrementCount("Hello, my", "dog");
        bc.incrementCount("Hello, my", "cat");
        bc.incrementCount("Hello, my", "world!");

        Map<String, Integer> counts = bc.getWordsAfterBigram("foo");
        assertEquals(0, counts.size());
    }
}
