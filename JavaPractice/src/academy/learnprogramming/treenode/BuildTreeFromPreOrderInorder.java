package academy.learnprogramming.treenode;

import java.util.HashMap;

public class BuildTreeFromPreOrderInorder {
	int preorderIndex;
	HashMap<Integer, Integer> inorderIndexMap;

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		preorderIndex = 0;
		if (preorder == null || inorder == null) {
			return new TreeNode(-1);
		} else if (preorder[0] == -1) {
			return new TreeNode(-1);
		} else if (inorder[0] == -1) {
			return new TreeNode(-1);
		} else if (inorder.length != preorder.length) {
			return new TreeNode(-1);
		} else if (preorder.length < 1 || preorder.length > 3000) {
			return new TreeNode(-1);
		}
		int[] preordercopy = java.util.Arrays.copyOf(preorder, preorder.length);
		int[] inordercopy = java.util.Arrays.copyOf(inorder, inorder.length);
		java.util.Arrays.sort(preordercopy);
		java.util.Arrays.sort(inordercopy);
		for (int i = 0; i < preordercopy.length; i++) {
			if ((i != (preordercopy.length - 1)) && preordercopy[i] == preordercopy[i + 1]) {
				return new TreeNode(-1);
			} else if (preordercopy[i] < -3000 || preordercopy[i] > 3000) {
				return new TreeNode(-1);
			}
		}
		for (int i = 0; i < inordercopy.length; i++) {
			if ((i != (inordercopy.length - 1)) && inordercopy[i] == inordercopy[i + 1]) {
				return new TreeNode(-1);
			} else if (inordercopy[i] < -3000 || inordercopy[i] > 3000) {
				return new TreeNode(-1);
			}
		}
		inorderIndexMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			inorderIndexMap.put(inorder[i], i);
		}
		return buildTreeFromRoot(preorder, 0, preorder.length - 1);
	}

	public TreeNode buildTreeFromRoot(int[] preorder, int left, int right) {
		if (left > right) {
			return null;
		}
		int rootValue = preorder[preorderIndex++];
		TreeNode root = new TreeNode(rootValue);
		root.setLeftChild(buildTreeFromRoot(preorder, left, (inorderIndexMap.get(rootValue) - 1)));
		root.setRightChild(buildTreeFromRoot(preorder, inorderIndexMap.get(rootValue) + 1, right));
		return root;
	}

	public static void main(String[] args) {
		BuildTreeFromPreOrderInorder buildTreeFromPreOrderInorder = new BuildTreeFromPreOrderInorder();
		TreeNode treeNodeFinal = buildTreeFromPreOrderInorder.buildTree(new int[] { 3, 9, 20, 15, 7 }, new int[] { 9, 3, 15, 20, 7 });
		treeNodeFinal.traverseInorder();
	}
}
