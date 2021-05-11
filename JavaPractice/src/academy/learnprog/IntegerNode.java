package academy.learnprog;
public class IntegerNode {
	private Integer integerNode;
	private IntegerNode next;
	private IntegerNode previous;
	private Integer value;

	public IntegerNode(Integer integerVal) {
		super();
		this.integerNode = integerVal;
		this.value = integerVal;
	}

	/**
	 * @return the integerNode
	 */
	public Integer getIntegerNode() {
		return integerNode;
	}

	/**
	 * @return the next
	 */
	public IntegerNode getNext() {
		return next;
	}

	/**
	 * @return the previous
	 */
	public IntegerNode getPrevious() {
		return previous;
	}

	/**
	 * @param integerNode the integerNode to set
	 */
	public void setIntegerNode(Integer integerNode) {
		this.integerNode = integerNode;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(IntegerNode next) {
		this.next = next;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(IntegerNode previous) {
		this.previous = previous;
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}
}
