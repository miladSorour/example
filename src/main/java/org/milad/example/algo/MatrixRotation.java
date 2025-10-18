package org.milad.example.algo;

public class MatrixRotation {

    public static void rotate90Clockwise(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        printMatrix(matrix);
        // Step 2: Reverse each row
        clockwise(matrix, n);
    }

    private static void clockwise(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                System.err.println("i: " + i + " j: " + j);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }

            System.err.println();
        }
    }

    private static void counterClockwise(int[][] matrix, int n) {
        for (int j = 0; j < n; j++) {          // iterate over each column
            for (int i = 0; i < n / 2; i++) {  // swap elements top <-> bottom
                System.err.println("i: " + i + " j: " + j);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
            System.err.println();
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25},
        };

        System.out.println("Original:");
        printMatrix(matrix);

        rotate90Clockwise(matrix);

        System.out.println("After 90° Clockwise Rotation:");
        printMatrix(matrix);
    }

}
