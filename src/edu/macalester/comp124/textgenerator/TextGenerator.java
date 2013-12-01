package edu.macalester.comp124.textgenerator;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * A program to generate text.
 *
 * @author Shilad Sen
 */
public class TextGenerator {
    // TODO: add instance variables for part 3

    public TextGenerator() {}

    /**
     * Trains the text generator on a particular set of text.
     *
     * @param documents
     */
    public void train(List<String> documents) {
        // TODO: implement me for part 3
    }

    /**
     * Generates a new random text
     * @return
     */
    public String generate() {
        // TODO: implement me for part 4
        return "";
    }

    /**
     * Returns the PhraseStats object associated with a particular phrase.
     * @param phrase
     * @return The phrase stats object associated with the phrase, or null if it doesn't exist.
     */
    public PhraseStats getPhraseStats(String phrase) {
        // TODO: implement me for part 3
        return null;
    }

    /**
     * Splits a text into words.
     * @param text
     * @return
     */
    private List<String> splitIntoWords(String text) {
        return Arrays.asList(text.split(" +"));
    }

    public static void main(String args[]) {
        WikAPIdiaWrapper wrapper = new WikAPIdiaWrapper(Utils.PATH_DB);
        // TODO: implement me for part 4
    }
}
