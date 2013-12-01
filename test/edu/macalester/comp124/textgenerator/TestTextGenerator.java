package edu.macalester.comp124.textgenerator;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        PhraseStats ps1 = tg.getPhraseStats("I is");
        assertEquals(1, ps1.getTotalCount());
        assertEquals(1, ps1.getCount("a"));

        PhraseStats ps2 = tg.getPhraseStats("a beautiful");
        assertEquals(2, ps2.getTotalCount());
        assertEquals(1, ps2.getCount("car"));
        assertEquals(1, ps2.getCount("day"));
    }

    /**
     * This test ensures you handle punctuation correctly.
     */
    @Test
    public void testPunctuation() {
        TextGenerator tg = new TextGenerator();
        tg.train(DOCUMENTS);
        PhraseStats ps1 = tg.getPhraseStats("document like");
        assertEquals(1, ps1.getTotalCount());
        assertEquals(1, ps1.getCount("this,"));
    }

    /**
     * This test ensures you treat upper and lower case differently.
     */
    @Test
    public void testCase() {
        TextGenerator tg = new TextGenerator();
        tg.train(DOCUMENTS);
        PhraseStats ps1 = tg.getPhraseStats("is a");
        assertEquals(4, ps1.getTotalCount());
        assertEquals(2, ps1.getCount("document"));
    }

    /**
     * This test ensures you treat starting of a document correctly.
     */
    @Test
    public void testStart() {
        TextGenerator tg = new TextGenerator();
        tg.train(DOCUMENTS);
        PhraseStats ps1 = tg.getPhraseStats("");
        assertEquals(5, ps1.getTotalCount());
        assertEquals(2, ps1.getCount("I"));
    }

    /**
     * This test ensures you treat starting of a document correctly.
     */
    @Test
    public void testEnd() {
        TextGenerator tg = new TextGenerator();
        tg.train(DOCUMENTS);
        PhraseStats ps1 = tg.getPhraseStats("nice day");
        assertNull(ps1);
        PhraseStats ps2 = tg.getPhraseStats("a document");
        assertNotNull(ps2);
    }
}
