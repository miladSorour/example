package org.milad.example.algo.graph;

import java.util.*;

public class AdjacencyListGraph {

    Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Integer source,Integer destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
    }

    void dfs(int node) {
        HashSet<Integer> visited = new HashSet<>();
        dfsHelper(node, visited);
        System.out.printf("visited: %s\n", visited);
    }

    private void dfsHelper(int currentVertice, HashSet<Integer> visited) {
        visited.add(currentVertice);
        for (Integer node : adjacencyList.getOrDefault(currentVertice, new ArrayList<>())) {
            if (!visited.contains(node)) {
                dfsHelper(node, visited);
            }
        }
    }

    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);
        graph.addEdge(8, 6);

        graph.dfs(0);
    }
}
