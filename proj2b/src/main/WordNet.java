package main;

import edu.princeton.cs.algs4.In;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class WordNet {
    private final Map<Integer, Set<String>> idToWords;
    private final Map<String, Set<Integer>> wordToIds;
    private final Graph graph;
    public WordNet(String synsetsFile, String hyponymsFile) {
        idToWords = new HashMap<>();
        wordToIds = new HashMap<>();
        graph = new Graph();
        try {
            In fileIN = new In(synsetsFile);
            while(fileIN.hasNextLine()) {
                String line = fileIN.readLine();
                String[] splited = line.split(",");
                Integer id = Integer.parseInt(splited[0]);
                String[] words = splited[1].split(" ");
                idToWords.put(id, new HashSet<>(Arrays.asList(words)));
                for(String word : words) {
                    wordToIds.computeIfAbsent(word, k -> new HashSet<>()).add(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            In fileIN = new In(hyponymsFile);
            while(fileIN.hasNextLine()) {
                String[] parts = fileIN.readLine().split(",");
                Integer from = Integer.parseInt(parts[0]);
                for(int i = 1; i < parts.length; i++) {
                    Integer to = Integer.parseInt(parts[i]);
                    graph.addEdges(from, to);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Set<String> getHyponyms(String word) {
        Set<String> hyponyms = new HashSet<>();
        Set<Integer> ids = wordToIds.get(word);
        if(ids == null) {
            return hyponyms;
        }
        Set<Integer> result = new HashSet<>();
        for(Integer id: ids) {
            result.addAll(graph.getAllReachable(id));
        }
        for(Integer id: result) {
            hyponyms.addAll(idToWords.get(id));
        }
        return hyponyms;
    }
}
