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
            String words[] = text.split(" +");
            if (words.length < 2) {
                continue;
            }

            counter.incrementCount("", words[0]);
            counter.incrementCount(words[0], words[1]);
            for (int i = 0; i < words.length - 2; i++) {
                counter.incrementCount(words[i] + " " + words[i + 1], words[i + 2]);
            }
            counter.incrementCount(words[words.length-2] + " " + words[words.length - 1], "");
        }
    }


    /**
     * Randomly select an option for words that have been selected following a bigram.
     * @param bigram
     * @return
     */
    public String select(String bigram) {
        Map<String, Integer> counts = counter.getWordsAfterBigram(bigram);
        if (counts.isEmpty()) {
            return "";
        }
        List<String> followers = new ArrayList<String>(counts.keySet());
        return followers.get(random.nextInt(followers.size()));
    }

    /**
     * Generates random text
     * @return
     */
    public String generate() {
        String first = select("");
        String second = select(first);
        String text = first + " " + second;
        String[] bigram = new String[] { first, second };
        while (true) {
            String next = select(bigram[0] + " " + bigram[1]);
            if (next.equals("")) {
                break;
            }
            text = text + " " + next;
            bigram[0] = bigram[1];
            bigram[1] = next;
        }
        return text;
    }

    /**
     * For testing.
     * @return
     */
    public BigramCounter getBigramCounter() {
        return counter;
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
