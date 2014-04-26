package edu.macalester.comp124.textgenerator;

import java.io.IOException;
import java.util.*;

/**
 * Generates random text. The workflow is:
 * 1. Create new text generator.
 * 2. call the train method on a set of documents.
 * 3. call the generate to generate text.
 *
 * @author Shilad Sen
 */
public class TextGenerator {
    private Random random = new Random();
    private BigramCounter counter = new BigramCounter();

    /**
     * Builds a statistical model of words following each bigram.
     * @param texts
     */
    public void train(List<String> texts) {
        for (String text : texts) {
            // Complete for part 2
        }
    }


    /**
     * Randomly select an option for words that have been selected following a bigram.
     * Should return empty string ("") to indicate the text should be ended.
     * @param bigram
     * @return
     */
    public String select(String bigram) {
        // Complete for part 2
        return "";
    }

    /**
     * Generates random text
     * @return
     */
    public String generate() {
        // Complete for part 2
        return null;
    }

    /**
     * For testing.
     * @return
     */
    public BigramCounter getBigramCounter() {
        // Complete for part 2
        return null;
    }

    public static void main(String args[]) throws IOException {
        // Search for another project gutenberg book!

        String text = Utils.getText("http://www.gutenberg.org/cache/epub/1661/pg1661.txt");
        String[] paragraphs = text.split("[\\n\\r|\\n|\\r]{2}");

        TextGenerator generator = new TextGenerator();
        generator.train(Arrays.asList(paragraphs));
        System.out.println(generator.generate());
    }

}
