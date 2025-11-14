package org.milad.example.algo;

public class MergeMatrix {
    public static void main(String[] args) {
        char[][] q1 = {
            {'1', '1', '1'},
            {'1', '1', '1'},
            {'1', '1', '1'}
        };
        char[][] q2 = {
            {'2', '2', '2'},
            {'2', '2', '2'},
            {'2', '2', '2'}
        };
        char[][] q3 = {
            {'3', '3', '3'},
            {'3', '3', '3'},
            {'3', '3', '3'}
        };
        char[][] q4 = {
            {'4', '4', '4'},
            {'4', '4', '4'},
            {'4', '4', '4'}
        };

        char[][] merged = mergeQuadrants(q1, q2, q3, q4);
        printMatrix(merged);
    }

    public static char[][] mergeQuadrants(char[][] q1, char[][] q2, char[][] q3, char[][] q4) {
        int n = 3; // size of each quadrant
        char[][] merged = new char[n * 2][n * 2]; // 6x6 matrix

        // Top-left quadrant
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                merged[i][j] = q1[i][j];

        // Top-right quadrant
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                merged[i][j + n] = q2[i][j];

        // Bottom-left quadrant
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                merged[i + n][j] = q3[i][j];

        // Bottom-right quadrant
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                merged[i + n][j + n] = q4[i][j];

        return merged;
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
