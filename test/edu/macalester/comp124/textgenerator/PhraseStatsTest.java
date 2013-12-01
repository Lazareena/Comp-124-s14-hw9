package edu.macalester.comp124.textgenerator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Shilad Sen
 */
public class PhraseStatsTest {
    @Test
    public void testConstruction() {
        PhraseStats ps = new PhraseStats("foo");
        assertEquals("foo", ps.getFrom());
        assertEquals(0, ps.getTotalCount());
    }

    @Test
    public void testSmall() {
        PhraseStats ps = new PhraseStats("foo");
        ps.increment("bar");
        ps.increment("baz");
        ps.increment("bar");
        ps.increment("bar");
        assertEquals(4, ps.getTotalCount());
        assertEquals(1, ps.getCount("baz"));
        assertEquals(3, ps.getCount("bar"));
    }

    @Test
    public void testRandomChoices() {
        PhraseStats ps = new PhraseStats("foo");
        ps.increment("bar");
        ps.increment("baz");
        ps.increment("bar");
        ps.increment("bar");
        ps.increment("fee");

        int numFee = 0;
        int numBar = 0;
        int numBaz = 0;
        for (int i = 0; i < 100000; i++) {
            String to = ps.pickRandomTo();
            assertTrue(to.equals("bar") || to.equals("baz") || to.equals("fee"));
            if (to.equals("bar")) { numBar++; }
            if (to.equals("baz")) { numBaz++; }
            if (to.equals("fee")) { numFee++; }
        }

        // A test of proportions confidence interval claims
        // each of these should pass >= 99.9% percent of the time.
        assertEquals(numFee / 100000.0, 0.333333, 0.01);
        assertEquals(numBar / 100000.0, 0.333333, 0.01);
        assertEquals(numBaz / 100000.0, 0.333333, 0.01);
    }
}
