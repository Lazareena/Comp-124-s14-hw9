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
//            Complete for part 2
            if (text.split(" +").length > 1) {
                String singleWords[] = text.split(" +");
                String[] pairs = new String[singleWords.length];
                pairs[0] = "";
                pairs[1] = singleWords[0];
                for (int i = 2; i < singleWords.length; i++) {
                    pairs[i] = (singleWords[i - 2] + " " + singleWords[i - 1]);
                }
                for (int j = 0; j < pairs.length; j++) {
                    counter.incrementCount(pairs[j], singleWords[j]);
                }
            }
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
        if (counter.getWordsAfterBigram(bigram).size() == 0 ) {
            return "";
        }
        List<String> keys      = new ArrayList<String>(counter.getWordsAfterBigram(bigram).keySet());
        String       randomKey = keys.get(random.nextInt(keys.size()));
        return randomKey;
    }

    /**
     * Generates random text
     * @return
     */
    public String generate() {
        // Complete for part 2
        List<String> firstWords  = new ArrayList<String>(counter.getWordsAfterBigram("").keySet());
        String output = firstWords.get(random.nextInt(firstWords.size()));

        while(true) {
            String newWord= select(lastTwoWords(output));
            if(newWord.equals("")) {
                break;
            }
            output+= " " + newWord;
        }
        return output;
    }

    public String lastTwoWords(String text) {
        String[] words= text.split(" +");
        if (words.length <= 2) {
            return text;
        } else {
            return  words[words.length-2] + " " + words[words.length-1];
        }
    }
    /**
     * For testing.
     * @return
     */
    public BigramCounter getBigramCounter() {
        // Complete for part 2
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
