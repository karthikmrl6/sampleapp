package academy.learnprog;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		Employee janeJones = new Employee("Jane", "Jones", 123);
		Employee janeDoe = new Employee("John", "Doe", 4567);
		Employee marySmith = new Employee("Mary", "Smith", 22);
		Employee mikeWilson = new Employee("Mike", "Wilson", 3245);
		Employee mikeWilson1 = new Employee("Mike1", "Wilson1", 3245);
		EmployeeLinkedList list = new EmployeeLinkedList();
		System.out.println(list.isEmpty());
		list.addToFront(janeJones);
		list.addToFront(janeDoe);
		list.addToFront(marySmith);
		list.addToFront(mikeWilson);
		System.out.println(list.getSize());
		list.printList();
		list.removeFromFront();
		System.out.println(list.getSize());
		list.printList();
		EmployeeDoublyLinkedList listDoublyLinkedList = new EmployeeDoublyLinkedList();
		listDoublyLinkedList.addToFront(janeJones);
		listDoublyLinkedList.addToFront(janeDoe);
		listDoublyLinkedList.addToFront(marySmith);
		listDoublyLinkedList.addToFront(mikeWilson);
		listDoublyLinkedList.addBefore(mikeWilson1, janeDoe);
		listDoublyLinkedList.printList();
		System.out.println(listDoublyLinkedList.getSize());
		Employee billEnd = new Employee("Bill", "End", 78);
		listDoublyLinkedList.addToEnd(billEnd);
		listDoublyLinkedList.printList();
		System.out.println(listDoublyLinkedList.getSize());
		listDoublyLinkedList.removeFromFront();
		listDoublyLinkedList.printList();
		System.out.println(listDoublyLinkedList.getSize());
		listDoublyLinkedList.removeFromEnd();
		listDoublyLinkedList.printList();
		System.out.println(listDoublyLinkedList.getSize());
		LinkedList<Employee> list4 = new LinkedList<>();
		list4.addFirst(mikeWilson1);
		list4.add(marySmith);
		list4.removeLast();
		list4.removeFirst();
		Iterator iter = list4.iterator();
		System.out.println("HEAD -> ");
		while (iter.hasNext()) {
			System.out.println(iter.next());
			System.out.println("<=>");
		}
		System.out.println("null");
		Integer one = 1;
		Integer two = 2;
		Integer three = 3;
		Integer four = 4;
		IntegerLinkedList listofIntegers = new IntegerLinkedList();
		listofIntegers.insertSorted(one);
		listofIntegers.printList();
		listofIntegers.insertSorted(three);
		listofIntegers.printList();
		listofIntegers.insertSorted(two);
		listofIntegers.printList();
		listofIntegers.insertSorted(four);
		listofIntegers.printList();
	}
}
