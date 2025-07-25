package ngrams;

import edu.princeton.cs.algs4.In;

import java.io.FileReader;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    Map<Integer, Long> counts;

    private Map<String, TimeSeries> words;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        int index = -1;
        counts = new HashMap<Integer, Long>();
        words = new HashMap<>();

        String lastWord = "";
        try{
            In wordsFileIn = new In(wordsFilename);
            while(wordsFileIn.hasNextLine()) {
                String line = wordsFileIn.readLine();
                String[] splited = line.split("\\t");
                if(!lastWord.equals(splited[0])) {
                    index++;
                    lastWord = splited[0];
                    words.put(lastWord, new TimeSeries());
                }
                words.get(lastWord).put(Integer.parseInt(splited[1]), Double.parseDouble(splited[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            In countsFileIn = new In(countsFilename);
            while(countsFileIn.hasNextLine()) {
                String line = countsFileIn.readLine();
                String[] splited = line.split(",");
                counts.put(Integer.parseInt(splited[0]), Long.parseLong(splited[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries ts = words.get(word);
        if (ts == null) return new TimeSeries();
        return new TimeSeries(ts, startYear, endYear); // defensive copy
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries ts = words.get(word);
        if (ts == null) return new TimeSeries();
        return ts; // defensive copy
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries total = new TimeSeries();
        for (Map.Entry<Integer, Long> entry : counts.entrySet()) {
            total.put(entry.getKey(), entry.getValue().doubleValue());
        }
        return total;
    }


    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries history = new TimeSeries();
        for(String key: words.keySet()) {
            if(key.equals(word)) {
                List<Integer> years = words.get(key).years();
                for(int year: years) {
                    if(year >= startYear && year <= endYear) {
                        history.put(year, words.get(key).get(year) / counts.get(year));
                    }
                }
            }
        }
        return history;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries history = new TimeSeries();
        for(String key: words.keySet()) {
            if(key.equals(word)) {
                List<Integer> years = words.get(key).years();
                for(int year: years) {
                    history.put(year, words.get(key).get(year) / counts.get(year));
                }
            }
        }
        return history;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries history = new TimeSeries();
        for(String word: words) {
            TimeSeries ts = weightHistory(word, startYear, endYear);
            history = history.plus(ts);
        }
        return history;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries history = new TimeSeries();
        for(String word: words) {
            TimeSeries ts = weightHistory(word);
            history = history.plus(ts);
        }
        return history;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
