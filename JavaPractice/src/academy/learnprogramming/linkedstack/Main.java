package academy.learnprogramming.linkedstack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Main {
	public static void main(String args[]) {
		EmployeeNew janeJones = new EmployeeNew("Jane", "Jones", 123);
		EmployeeNew johnDoe = new EmployeeNew("John", "Doe", 4567);
		EmployeeNew marySmith = new EmployeeNew("Mary", "Smith", 22);
		EmployeeNew mikeWilson = new EmployeeNew("Mike", "Wilson", 3245);
		EmployeeNew billEnd = new EmployeeNew("Bill", "End", 78);
		LinkedStack stack = new LinkedStack();
		stack.push(janeJones);
		stack.push(johnDoe);
		stack.push(marySmith);
		stack.push(mikeWilson);
		stack.push(billEnd);
		stack.printStack();
		System.out.println(stack.peek());
		System.out.println("popped = " + stack.pop());
		System.out.println(stack.peek());
		ArrayStack stack1 = new ArrayStack(10);
		stack1.push(new EmployeeNew("Jane", "Jones", 123));
		stack1.push(new EmployeeNew("John", "Doe", 4567));
		stack1.push(new EmployeeNew("Mary", "Smith", 22));
		stack1.push(new EmployeeNew("Mike", "Wilson", 3245));
		stack1.push(new EmployeeNew("Bill", "End", 78));
		stack1.printstack();
		System.out.println(stack1.peek());
		System.out.println("Popped: " + stack1.pop());
		System.out.println(checkForPalindrome("abccba"));
		System.out.println(checkForPalindrome("Was it a car or a cat I saw?"));
		System.out.println(checkForPalindrome("I did, did I?"));
		System.out.println(checkForPalindrome("hello"));
		System.out.println(checkForPalindrome("Don't nod"));
		System.out.println(checkForPalindrome1("abccba"));
		System.out.println(checkForPalindrome1("Was it a car or a cat I saw?"));
		System.out.println(checkForPalindrome1("I did, did I?"));
		System.out.println(checkForPalindrome1("hello"));
		System.out.println(checkForPalindrome1("Don't nod"));
		ArrayQueue queue = new ArrayQueue(5);
		queue.add(janeJones);
		queue.add(johnDoe);
		queue.remove();
		queue.add(marySmith);
		queue.remove();
		queue.add(mikeWilson);
		queue.remove();
		queue.add(billEnd);
		queue.printQueue();
		System.out.println(queue.peek());
		queue.printQueue();
		System.out.println("----------------------------------------------------");
		ArrayQueueCircular queue1 = new ArrayQueueCircular(5);
		queue1.add(janeJones);
		queue1.add(johnDoe);
		queue1.remove();
		queue1.add(marySmith);
		queue1.remove();
		queue1.add(mikeWilson);
		queue1.remove();
		queue1.add(billEnd);
		queue1.remove();
		queue1.add(janeJones);
		queue1.printQueue();
		SimpleHashTable ht = new SimpleHashTable();
		ht.put("Jones", janeJones);
		ht.put("Doe", johnDoe);
		ht.put("Wilson", mikeWilson);
		ht.put("Smith", marySmith);
		System.out.println("----------------------------------------------------");
		ht.printHashtable();
		System.out.println("----------------------------------------------------");
		System.out.println("Retrieve key wilson: " + ht.get("Wilson"));
		System.out.println("Retrieve key smith: " + ht.get("Smith"));
		ht.remove("Wilson");
		ht.remove("Jones");
		System.out.println("----------------------------------------------------");
		ht.printHashtable();
		System.out.println("----------------------------------------------------");
		System.out.println("Retrieve key smith: " + ht.get("Smith"));
		System.out.println("----------------------------------------------------");
		ChainedHashtable htChained = new ChainedHashtable();
		htChained.put("Jones", janeJones);
		htChained.put("Doe", johnDoe);
		htChained.put("Wilson", mikeWilson);
		htChained.put("Smith", marySmith);
		// htChained.printHashtable();
		System.out.println("Retrieve key smith: " + ht.get("Smith"));
		ht.remove("Doe");
		ht.remove("Jones");
		ht.printHashtable();
		System.out.println("Retrieve key smith: " + ht.get("Smith"));
		System.out.println("----------------------------------------------------");
		Map<String, EmployeeNew> hashMap1 = new HashMap<String, EmployeeNew>();
		hashMap1.put("Jones", janeJones);
		hashMap1.put("Doe", johnDoe);
		hashMap1.put("Smith", marySmith);
		EmployeeNew employee = hashMap1.put("Doe", mikeWilson);
		System.out.println(employee);
		System.out.println("----------------------------------------------------");
		EmployeeNew employee1 = hashMap1.putIfAbsent("Doe", mikeWilson);
		System.out.println(employee1);
		System.out.println("----------------------------------------------------");
		System.out.println(hashMap1.containsKey("Doe"));
		System.out.println(hashMap1.containsValue(janeJones));
		/*Iterator<EmployeeNew> iterator = hashMap1.values().iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}*/
		System.out.println("----------------------------------------------------");
		hashMap1.forEach((k, v) -> System.out.println("Key = " + k + ", Employee = " + v));
		System.out.println("----------------------------------------------------");
		System.out.println(hashMap1.get("someone"));
		System.out.println("----------------------------------------------------");
		System.out.println(hashMap1.getOrDefault("someone", mikeWilson));
		System.out.println("----------------------------------------------------");
		System.out.println(hashMap1.remove("Jones"));
		// hashtable - synchronized
		// hashmap - not synchronized
		// concurrent hashmap - synchronized (similar to hashtable)
		// Collections.syncronizedHashmap(new linkedHashmap())
		// remove eldest entry in linkedhashmap class
		int[] nums = new int[10];
		int[] numsToAdd = { 59382, 43, 6894, 500, 99, -58 };
		for (int i = 0; i < numsToAdd.length; i++) {
			nums[hashFunc(numsToAdd[i])] = numsToAdd[i];
		}
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
		List<EmployeeNew> employees = new ArrayList<EmployeeNew>();
		employees.add(new EmployeeNew("Mary", "Smith", 5555));
		employees.add(new EmployeeNew("Jane", "Jones", 552));
		employees.add(new EmployeeNew("John", "Doe", 5533));
		employees.add(new EmployeeNew("Bill", "End", 5599));
		employees.add(new EmployeeNew("Bill", "Ende", 5500));
		employees.forEach(e -> System.out.println(e));
		HashMap<Integer, EmployeeNew> employeeTable = new HashMap<>();
		ListIterator<EmployeeNew> iterator = employees.listIterator();
		List<EmployeeNew> remove = new ArrayList<>();
		while (iterator.hasNext()) {
			EmployeeNew employeeNew = iterator.next();
			if (employeeTable.containsKey(employeeNew.getId())) {
				remove.add(employeeNew);
			} else {
				employeeTable.put(employeeNew.getId(), employeeNew);
			}
		}
		for (EmployeeNew employeeVal : employees) {
			employees.remove(employeeVal);
		}
		int[] intArrayVal = { 20, 35, -15, 7, 55, 1, -22 };
		// System.out.println(linearSearch(intArrayVal, -15));
		// System.out.println(linearSearch(intArrayVal, 1));
		// System.out.println(linearSearch(intArrayVal, 8888));
		// System.out.println(linearSearch(intArrayVal, -22));
		// System.out.println(iterativeBinarySearch(intArrayVal, -15));
		// System.out.println(iterativeBinarySearch(intArrayVal, 35));
		// System.out.println(iterativeBinarySearch(intArrayVal, 8888));
		// System.out.println(iterativeBinarySearch(intArrayVal, 1));
		System.out.println(recursiveBinarySearch(intArrayVal, -22));
		System.out.println(recursiveBinarySearch(intArrayVal, -15));
		System.out.println(recursiveBinarySearch(intArrayVal, 35));
		System.out.println(recursiveBinarySearch(intArrayVal, 8888));
		System.out.println(recursiveBinarySearch(intArrayVal, 1));
	}

	public static int hashFunc(int value) {
		return Math.abs(value % 10);
	}

	public static boolean checkForPalindrome(String string) {
		LinkedList<Character> stack = new LinkedList<Character>();
		StringBuilder stringNoPunctuation = new StringBuilder(string.length());
		String lowerCase = string.toLowerCase();
		for (int i = 0; i < lowerCase.length(); i++) {
			char c = lowerCase.charAt(i);
			if (c >= 'a' && c <= 'z') {
				stringNoPunctuation.append(c);
				stack.push(c);
			}
		}
		StringBuilder reversedString = new StringBuilder(string.length());
		while (!(stack.isEmpty())) {
			reversedString.append(stack.pop());
		}
		return reversedString.toString().equals(stringNoPunctuation.toString());
	}

	public static int linearSearch(int[] input, int value) {
		for (int i = 0; i < input.length; i++) {
			if (input[i] == value) {
				return i;
			}
		}
		return -1;
	}

	public static int recursiveBinarySearch(int[] input, int value) {
		return recursiveBinarySearch(input, 0, input.length, value);
	}

	public static int recursiveBinarySearch(int[] input, int start, int end, int value) {
		if (start >= end) {
			return -1;
		}
		int midpoint = (start + end) / 2;
		System.out.println("midpoint = " + midpoint);
		if (input[midpoint] == value) {
			return midpoint;
		} else if (input[midpoint] < value) {
			return recursiveBinarySearch(input, midpoint + 1, end, value);
		} else {
			return recursiveBinarySearch(input, start, midpoint, value);
		}
	}

	public static int iterativeBinarySearch(int[] input, int value) {
		int start = 0;
		int end = input.length;
		while (start < end) {
			int midpoint = (start + end) / 2;
			System.out.println("midpoint = " + midpoint);
			if (input[midpoint] == value) {
				return midpoint;
			} else if (input[midpoint] < value) {
				start = midpoint + 1;
			} else {
				end = midpoint;
			}
		}
		return -1;
	}

	public static boolean checkForPalindrome1(String string) {
		LinkedList<Character> stack = new LinkedList<Character>();
		LinkedList<Character> queue = new LinkedList<Character>();
		String lowercase = string.toLowerCase();
		for (int i = 0; i < lowercase.length(); i++) {
			char c = lowercase.charAt(i);
			if (c >= 'a' && c <= 'z') {
				queue.addLast(c);
				stack.push(c);
			}
		}
		while (!(stack.isEmpty())) {
			if (!(stack.pop().equals(queue.removeFirst()))) {
				return false;
			}
		}
		return true;
	}
}
