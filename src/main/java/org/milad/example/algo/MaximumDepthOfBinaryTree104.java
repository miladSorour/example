package org.milad.example.algo;

public class MaximumDepthOfBinaryTree104 {

    public static void main(String[] args) {
        // Build the tree manually

        TreeNode node7 = new TreeNode(7);
        TreeNode node5 = new TreeNode(5, node7, null);

        TreeNode node8 = new TreeNode(8);
        TreeNode node6 = new TreeNode(6, null, node8);

        TreeNode node3 = new TreeNode(3, node5, node6);

        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2, node4, null);

        TreeNode root = new TreeNode(1, node2, node3);

        // Call the method
        MaximumDepthOfBinaryTree104 solution = new MaximumDepthOfBinaryTree104();
        int depth = solution.maxDepth(root);

        // Print result
        System.out.println("Maximum depth of the tree: " + depth);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}