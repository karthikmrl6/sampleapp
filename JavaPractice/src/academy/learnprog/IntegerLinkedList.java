package academy.learnprog;
public class IntegerLinkedList {
	private IntegerNode head;
	private int size;

	public void addToFront(Integer integerVal) {
		IntegerNode node = new IntegerNode(integerVal);
		node.setNext(head);
		head = node;
		size++;
	}

	public void insertSorted(Integer value) {
		if (head == null || head.getValue() >= value) {
			addToFront(value);
			return;
		}
		// find the insertion point
		IntegerNode current = head.getNext();
		IntegerNode previous = head;
		while (current != null && current.getValue() < value) {
			previous = current;
			current = current.getNext();
		}
		IntegerNode newNode = new IntegerNode(value);
		newNode.setNext(current);
		previous.setNext(newNode);
		size++;
	}

	public void printList() {
		IntegerNode current = head;
		System.out.print("HEAD -> ");
		while (current != null) {
			System.out.print(current.getValue());
			System.out.print(" -> ");
			current = current.getNext();
		}
		System.out.println("null");
	}
}
