package academy.learnprogramming.linkedstack;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedStack {
	private LinkedList<EmployeeNew> stack;

	public LinkedStack() {
		stack = new LinkedList<EmployeeNew>();
	}

	public void push(EmployeeNew employee) {
		stack.push(employee);
	}

	public EmployeeNew pop() {
		return stack.pop();
	}

	public EmployeeNew peek() {
		return stack.peek();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public void printStack() {
		ListIterator<EmployeeNew> iterator = stack.listIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
