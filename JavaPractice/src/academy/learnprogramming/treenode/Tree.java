package academy.learnprogramming.treenode;

public class Tree {
	private TreeNode root;

	public void insert(int value) {
		if (root == null) {
			root = new TreeNode(value);
		} else {
			root.insert(value);
		}
	}

	public void traverseInorder() {
		if (root != null) {
			root.traverseInorder();
		}
	}

	public TreeNode delete(int value) {
		return delete(root, value);
	}

	private TreeNode delete(TreeNode subtreeRoot, int value) {
		if (subtreeRoot == null) {
			return subtreeRoot;
		}
		if (value < subtreeRoot.getData()) {
			subtreeRoot.setLeftChild(delete(subtreeRoot.getLeftChild(), value));
		} else if (value > subtreeRoot.getData()) {
			subtreeRoot.setRightChild(delete(subtreeRoot.getRightChild(), value));
		} else {
			// cases 1 and 2: node to delete has 0 or 1 children
			if (subtreeRoot.getLeftChild() == null) {
				return subtreeRoot.getRightChild();
			} else if (subtreeRoot.getRightChild() == null) {
				return subtreeRoot.getLeftChild();
			}
			// case 3: node to delete has 2 children
			// replace the value in the subtree node with the smallest value from the right subtree
			subtreeRoot.setData(subtreeRoot.getRightChild().min());
			// delete the node with the smallest value in the right subtree
			subtreeRoot.setRightChild(delete(subtreeRoot.getRightChild(), subtreeRoot.getData()));
		}
		return subtreeRoot;
	}

	public int min() {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		return root.min();
	}

	public int max() {
		if (root == null) {
			return Integer.MAX_VALUE;
		}
		return root.max();
	}

	public int maxDepth(TreeNode roote) {
		if (roote == null) {
			return 0;
		} else {
			int left_height = maxDepth(roote.getLeftChild()) + 1;
			int right_height = maxDepth(roote.getRightChild()) + 1;
			return java.lang.Math.max(left_height, right_height);
		}
	}

	public TreeNode get(int value) {
		if (root != null) {
			return root.get(value);
		}
		return null;
	}
}
