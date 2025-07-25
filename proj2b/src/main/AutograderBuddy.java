package main;
import browser.NgordnetServer;
import demo.DummyHistoryHandler;
import demo.DummyHistoryTextHandler;
import ngrams.NGramMap;
import utils.Utils.*;
import browser.NgordnetQueryHandler;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {

//        NgordnetServer hns = new NgordnetServer();
        WordNet wordNet = new WordNet(synsetFile, hyponymFile);
        NGramMap map = new NGramMap(wordFile, countFile);
//        hns.startUp();
//        hns.register("history", new DummyHistoryHandler());
//        hns.register("historytext", new DummyHistoryTextHandler());
//        hns.register("hyponyms", new HyponymsHandler(wordNet));

        return new HyponymsHandler(wordNet, map);
    }
}
