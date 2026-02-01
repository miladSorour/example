package org.milad.example.algo.tree;

public class SameBinaryTree100V1 {

	public static void main(String[] args) {
		  /*
                 1
               /   \
              2     3
             / \     \
            4   5     6
        */

		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);
		root1.left.left = new TreeNode(4);
		root1.left.right = new TreeNode(5);
		root1.right.right = new TreeNode(6);

		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(3);
		root2.left.left = new TreeNode(4);
		root2.left.right = new TreeNode(5);
		root2.right.right = new TreeNode(6);

		SameBinaryTree100V1 solution = new SameBinaryTree100V1();
		System.err.println(solution.isSameTree(root1, root2));
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null) {
			return false;
		}
		if (p.val != q.val) {
			return false;
		}
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}
}