package org.milad.example.algo.tree;

import java.util.ArrayDeque;
import java.util.Queue;


public class TraversBinaryTree {

	public static void main(String[] args) {
		  /*
                 1
               /   \
              2     3
             / \     \
            4   5     6
        */

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(6);

		levelOrderTraversal(root);
		// Output: 1 2 3 4 5 6
	}

	public static void levelOrderTraversal(TreeNode root) {
		if (root == null) {
			return;
		}

		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			System.out.print(current.val + " ");

			if (current.left != null) {
				queue.offer(current.left);
			}

			if (current.right != null) {
				queue.offer(current.right);
			}
		}
	}
}