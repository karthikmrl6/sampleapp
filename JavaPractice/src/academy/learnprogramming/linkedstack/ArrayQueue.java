package academy.learnprogramming.linkedstack;

import java.util.NoSuchElementException;

public class ArrayQueue {
	private EmployeeNew[] queue;
	private int front;
	private int back;

	public ArrayQueue(int capacity) {
		queue = new EmployeeNew[capacity];
	}

	public void add(EmployeeNew employee) {
		if (back == queue.length) {
			EmployeeNew[] newArray = new EmployeeNew[2 * queue.length];
			System.arraycopy(queue, 0, newArray, 0, queue.length);
			queue = newArray;
		}
		queue[back] = employee;
		back++;
	}

	public EmployeeNew remove() {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		EmployeeNew employee = queue[front];
		queue[front] = null;
		front++;
		if (size() == 0) {
			front = 0;
			back = 0;
		}
		return employee;
	}

	public EmployeeNew peek() {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		return queue[front];
	}

	public void printQueue() {
		for (int i = front; i < back; i++) {
			System.out.println(queue[i]);
		}
	}

	public int size() {
		return back - front;
	}
}
