package org.milad.example.algo;

public class NumberOfIslands200 {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int islandCount = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Loop over each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If we find land
                if (grid[i][j] == '1') {
                    islandCount++;
                    dfs(grid, i, j);
                }
            }
        }
        return islandCount;
    }

    // Depth-First Search to "sink" the island
    private void dfs(char[][] grid, int i, int j) {
        // Boundary check or water
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return;

        // Mark as visited (sink)
        grid[i][j] = '0';

        // Explore neighbors
        dfs(grid, i - 1, j); // up
        dfs(grid, i + 1, j); // down
        dfs(grid, i, j - 1); // left
        dfs(grid, i, j + 1); // right
    }

    // Sample test
    public static void main(String[] args) {
        NumberOfIslands200 sol = new NumberOfIslands200();
        char[][] grid = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','1'}
        };
        System.out.println("Number of islands: " + sol.numIslands(grid)); // Output: 2
    }
}
