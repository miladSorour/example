package org.milad.example.algo;

public class ClimbingStairs70 {

    public static void main(String[] args) {
        ClimbingStairs70 solution = new ClimbingStairs70();
        System.err.println(solution.climbStairs(45));
    }

    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int a = 1;
        int b = 2;
        int c;

        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
