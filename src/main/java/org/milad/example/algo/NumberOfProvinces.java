package org.milad.example.algo;

public class NumberOfProvinces {

    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private static void dfs(int[][] graph, boolean[] visited, int node) {
        visited[node] = true;

        for (int neighbor = 0; neighbor < graph.length; neighbor++) {
            if (graph[node][neighbor] == 1 && !visited[neighbor]) {
                dfs(graph, visited, neighbor);
            }
        }
    }

    // Main method to test
    public static void main(String[] args) {
        int[][] isConnected = {
                {1, 1, 0, 0, 0, 0}, // City 0 connected to 1
                {1, 1, 1, 0, 0, 0}, // City 1 connected to 0 and 2
                {0, 1, 1, 0, 0, 0}, // City 2 connected to 1
                {0, 0, 0, 1, 1, 0}, // City 3 connected to 4
                {0, 0, 0, 1, 1, 0},  // City 4 connected to 3
                {0, 0, 0, 1, 1, 0}  // City 4 connected to 3
        };

        System.out.println("Number of provinces: " + findCircleNum(isConnected));
    }
}