package edu.macalester.comp124.textgenerator;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Shilad Sen
 */
public class TestTextGenerator {
    public static final List<String> DOCUMENTS = Arrays.asList(
            "I is a document",
            "What in the world is a document  like this, doing here?",
            "It is a beautiful day - yes.",
            "I say it IS a nice day",
            "That is a beautiful car I think."
    );

    /**
     * This should be the most straightforward test to pass
     */
    @Test
    public void testEasy() {
        TextGenerator tg = new TextGenerator();
        tg.train(DOCUMENTS);
        BigramCounter bc = tg.getBigramCounter();
        Map<String, Integer> counts = bc.getWordsAfterBigram("I is");
        assertEquals(1, counts.size());
        assertEquals(1, (int)counts.get("a"));

        counts = bc.getWordsAfterBigram("a beautiful");
        assertEquals(2, counts.size());
        assertEquals(1, (int)counts.get("car"));
        assertEquals(1, (int)counts.get("day"));
    }

    /**
     * This test ensures you handle punctuation correctly and upper / lower case correctly
     */
    @Test
    public void testPunctuation() {
        TextGenerator tg = new TextGenerator();
        tg.train(DOCUMENTS);
        BigramCounter bc = tg.getBigramCounter();

        Map<String, Integer> counts = bc.getWordsAfterBigram("document like");
        assertEquals(1, counts.size());
        assertEquals(1, (int)counts.get("this,"));

        counts = bc.getWordsAfterBigram("is a");
        assertEquals(2, counts.size());
        assertEquals(2, (int)counts.get("document"));
        assertEquals(2, (int)counts.get("beautiful"));
    }

    /**
     * This test ensures you treat starting off a document correctly.
     */
    @Test
    public void testStart() {
        TextGenerator tg = new TextGenerator();
        tg.train(DOCUMENTS);
        BigramCounter bc = tg.getBigramCounter();

        Map<String, Integer> counts = bc.getWordsAfterBigram("");
        assertEquals(4, counts.size());
        assertEquals(2, (int)counts.get("I"));


        counts = bc.getWordsAfterBigram("I");
        assertEquals(2, counts.size());
        assertEquals(1, (int)counts.get("say"));
        assertEquals(1, (int)counts.get("is"));
    }

    /**
     * This ensures that select is correct.
     */
    @Test
    public void testSelect() {
        TextGenerator tg = new TextGenerator();
        tg.train(DOCUMENTS);
        int numDocument = 0;
        int numBeautiful = 0;
        for (int i = 0; i < 10000; i++) {
            String next = tg.select("is a");
            if (next.equals("document")) {
                numDocument ++;
            } else if (next.equals("beautiful")) {
                numBeautiful++;
            } else {
                assert(false);
            }
        }
        assertTrue(numDocument > 4000);
        assertTrue(numDocument < 6000);
    }
}
