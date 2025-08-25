package org.milad.example.algo;

public class LPSAlgorithm {
    public static int[] computeLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int length = 0; // length of the previous longest prefix suffix
        int i = 1;

        while (i < pattern.length()) {
            System.out.println("\n[Step " + i + "] Comparing: pattern[" + i + "] = '" + pattern.charAt(i) + "' with pattern[" + length + "] = '" + pattern.charAt(length) + "'");
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                System.out.println("Match found! lps[" + i + "] = " + lps[i]);
                printLPSArray(pattern, lps, i);
                i++;
            } else {
                if (length != 0) {
                    System.out.println("Mismatch. Backtracking length to lps[" + (length - 1) + "] = " + lps[length - 1]);
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    System.out.println("No match. lps[" + i + "] = 0");
                    printLPSArray(pattern, lps, i);
                    i++;
                }
            }
        }

        return lps;
    }

    private static void printLPSArray(String pattern, int[] lps, int currentIdx) {
        System.out.print("Pattern:    ");
        for (char c : pattern.toCharArray()) {
            System.out.printf("%-4c", c);
        }
        System.out.print("\nLPS:        ");
        for (int i = 0; i < lps.length; i++) {
            if (i == currentIdx) {
                System.out.printf("[%d] ", lps[i]);
            } else {
                System.out.printf("%-4d", lps[i]);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String pattern = "ABABAC";
        int[] lps = computeLPS(pattern);

        System.out.println("\nFinal LPS Array for \"" + pattern + "\":");
        for (int val : lps) {
            System.out.print(val + " ");
        }
    }
}