package main;

import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjList;

    public Graph(Map<Integer, List<Integer>> adjList) {
        this.adjList = adjList;
    }
    public Graph() {
        this.adjList = new HashMap<>();
    }

    public Map<Integer, List<Integer>> getAdjList() {
        return adjList;
    }
    public void addEdges(Integer from, Integer to) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.get(from).add(to);
    }
    public Set<Integer> getAllReachable(Integer from) {
        Set<Integer> result = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        dfs(from, result, visited);
        result.add(from);
        return result;
    }

    private void dfs(Integer node, Set<Integer> result, Set<Integer> visited) {
        if (!adjList.containsKey(node) || visited.contains(node)) {
            return;
        }
        visited.add(node);

        for (Integer neighbor : adjList.get(node)) {
            if (result.add(neighbor)) {  // 加入结果集（去重）
                dfs(neighbor, result, visited);
            }
        }
    }


    public List<Integer> getEdges(Integer from) {
        return adjList.get(from);
    }

}
