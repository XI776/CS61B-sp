package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    WordNet wordnet;
    NGramMap map;
    public HyponymsHandler(WordNet wordnet, NGramMap map) {
        this.wordnet = wordnet;
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        Set<String> set = new HashSet<>();
        for(String word : words) {
            if(set.isEmpty()) {
                set.addAll(wordnet.getHyponyms(word));
            } else {
                set.retainAll(wordnet.getHyponyms(word));
            }
        }
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        if(list.size() < k || k == 0) {
            return list.toString();
        } else {
            List<wordAndRespectively> res = new ArrayList<>();
            for(String word : list) {
                TimeSeries t = map.countHistory(word, startYear, endYear);
                res.add(new wordAndRespectively(word, t));
            }
            Collections.sort(res);
            List<String> topK = new ArrayList<>();
            for (int i = 0; i < k && i < res.size(); i++) {
                topK.add(res.get(i).word);
            }
            Collections.sort(topK);
            return topK.toString();
        }

    }



}
