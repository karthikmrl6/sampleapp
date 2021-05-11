package academy.learnprogramming.linkedstack;

import java.util.EmptyStackException;

public class ArrayStack {
	private EmployeeNew[] stack;
	private int top;

	public ArrayStack(int capacity) {
		stack = new EmployeeNew[capacity];
	}

	public void push(EmployeeNew employee) {
		if (top == stack.length) {
			EmployeeNew[] newArray = new EmployeeNew[2 * stack.length];
			System.arraycopy(stack, 0, newArray, 0, stack.length);
			stack = newArray;
		}
		stack[top] = employee;
		top = top + 1;
	}

	public EmployeeNew pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		EmployeeNew employee = stack[--top];
		stack[top] = null;
		return employee;
	}

	public EmployeeNew peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack[top - 1];
	}

	public int size() {
		return top;
	}

	public boolean isEmpty() {
		return top == 0;
	}

	public void printstack() {
		for (int i = top - 1; i >= 0; i--) {
			System.out.println(stack[i]);
		}
	}
}
