package academy.learnprogramming.treenode;

public class TreeNode {
	private int data;
	private TreeNode leftChild;
	private TreeNode rightChild;

	public TreeNode(int data) {
		this.data = data;
	}

	public void insert(int value) {
		if (value == data) {
			return;
		}
		if (value < data) {
			if (leftChild == null) {
				leftChild = new TreeNode(value);
			} else {
				leftChild.insert(value);
			}
		} else {
			if (rightChild == null) {
				rightChild = new TreeNode(value);
			} else {
				rightChild.insert(value);
			}
		}
	}

	public int min() {
		if (leftChild == null) {
			return this.data;
		} else {
			return leftChild.min();
		}
	}

	public int max() {
		if (rightChild == null) {
			return this.data;
		} else {
			return rightChild.max();
		}
	}

	public TreeNode get(int value) {
		if (value == data) {
			return this;
		}
		if (value < data) {
			if (leftChild != null) {
				return leftChild.get(value);
			}
		} else {
			if (rightChild != null) {
				return rightChild.get(value);
			}
		}
		return null;
	}

	public void traverseInorder() {
		if (leftChild != null) {
			leftChild.traverseInorder();
		}
		System.out.print("Data = " + data + " , ");
		if (rightChild != null) {
			rightChild.traverseInorder();
		}
	}

	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/**
	 * @return the leftChild
	 */
	public TreeNode getLeftChild() {
		return leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public TreeNode getRightChild() {
		return rightChild;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(int data) {
		this.data = data;
	}

	/**
	 * @param leftChild the leftChild to set
	 */
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}
}
