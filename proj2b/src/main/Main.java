package main;

import browser.NgordnetServer;
import demo.DummyHistoryHandler;
import demo.DummyHistoryTextHandler;
import ngrams.NGramMap;
import org.slf4j.LoggerFactory;

import static utils.Utils.LARGE_SYNSET_FILE;

public class Main {
    // ngrams files
    public static final String VERY_SHORT_WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    private static final String SMALL_WORDS_FILE = "data/ngrams/top_14377_words.csv";
    private static final String WORDS_FILE = "data/ngrams/top_49887_words.csv";
    private static final String RANDOM_WORDS_25 = "data/ngrams/random_freq_25.csv";
    private static final String RANDOM_WORDS_10 = "data/ngrams/random_freq_10.csv";

    // wordnet Files
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";
    private static final String HYPONYMS_FILE_SUBSET = "data/wordnet/hyponyms1000-subgraph.txt";
    private static final String SYNSETS_FILE_SUBSET = "data/wordnet/synsets1000-subgraph.txt";
    private static final String RANDOM_HYP_25 = "data/wordnet/random_hyp_25.txt";
    private static final String RANDOM_SYN_25 = "data/wordnet/random_syn_25.txt";
    private static final String RANDOM_HYP_10 = "data/wordnet/random_hyp_10.txt";
    private static final String RANDOM_SYN_10 = "data/wordnet/random_syn_10.txt";

    // EECS files
    private static final String FREQUENCY_EECS_FILE = "data/ngrams/frequency-EECS.csv";
    private static final String HYPONYMS_EECS_FILE = "data/wordnet/hyponyms-EECS.txt";
    private static final String SYNSETS_EECS_FILE = "data/wordnet/synsets-EECS.txt";

    static {
        LoggerFactory.getLogger(Main.class).info("\033[1;38mChanging text color to white");
    }
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();
        WordNet wordNet = new WordNet(LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        NGramMap map = new NGramMap(SMALL_WORDS_FILE , TOTAL_COUNTS_FILE);
        hns.startUp();
        hns.register("history", new HistoryHandler(map));
        hns.register("historytext", new HistoryTextHandler(map));
        hns.register("hyponyms", new HyponymsHandler(wordNet, map));

        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");
    }
}