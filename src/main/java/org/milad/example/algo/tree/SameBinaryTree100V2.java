package org.milad.example.algo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class SameBinaryTree100V2 {

	public static void main(String[] args) {
		  /*
                 1
               /   \
              2     3
             / \     \
            4   5     6
        */

		TreeNode root1 = new TreeNode(1);


		TreeNode root2 = null;

		SameBinaryTree100V2 solution = new SameBinaryTree100V2();
		System.err.println(solution.isSameTree(root1, root2));
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		List<Integer> firstTree = levelOrderTraversal(p);
		List<Integer> secTree = levelOrderTraversal(q);
		if (firstTree.size() != secTree.size())
			return false;

		for (int i = 0; i < firstTree.size(); i++) {
			if (firstTree.get(i) != null && secTree.get(i) != null) {
				if (!firstTree.get(i).equals(secTree.get(i))) {
					return false;
				}
			} else if (firstTree.get(i) == null && secTree.get(i) == null) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public static List<Integer> levelOrderTraversal(TreeNode root) {
		List<Integer> tree = new ArrayList<>();
		if (root == null) {
			return tree;
		}

		Queue<TreeNode> queue = new LinkedList<>(); // allows null
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();

			if (current == null) {
				tree.add(null);
				continue;
			}

			tree.add(current.val);

			// enqueue children even if they are null
			queue.offer(current.left);
			queue.offer(current.right);
		}

		return tree;
	}
}