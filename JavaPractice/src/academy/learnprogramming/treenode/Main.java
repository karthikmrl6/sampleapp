package academy.learnprogramming.treenode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main implements Comparator<Integer> {
	public static void main(String[] args) {
		Tree intTree = new Tree();
		intTree.insert(25);
		intTree.insert(10);
		intTree.insert(5);
		intTree.insert(20);
		intTree.insert(27);
		intTree.insert(29);
		intTree.insert(26);
		intTree.insert(22);
		intTree.insert(32);
		intTree.traverseInorder();
		System.out.println("");
		System.out.println("Max Depth = " + intTree.maxDepth(intTree.get(25)));
		System.out.println("");
		System.out.println(intTree.min());
		System.out.println(intTree.max());
		intTree.delete(15);
		intTree.delete(27);
		Heap heap = new Heap(10);
		heap.insert(80);
		heap.insert(75);
		heap.insert(60);
		heap.insert(68);
		heap.insert(55);
		heap.insert(40);
		heap.insert(52);
		heap.insert(67);
		heap.printHeap();
		// heap.delete(1);
		// heap.delete(5);
		heap.delete(0);
		heap.printHeap();
		System.out.println(heap.peek());
		System.out.println("-------------------------------------------------------------");
		heap.sort();
		heap.printHeap();
		System.out.println("------------------------------------------------------------");
		// PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Main());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(25);
		pq.add(-22);
		pq.add(1343);
		pq.add(54);
		pq.add(0);
		pq.add(-3492);
		pq.add(429);
		System.out.println(pq.peek());
		System.out.println(pq.remove());
		System.out.println(pq.peek());
		System.out.println(pq.poll());
		System.out.println(pq.peek());
		System.out.println(pq.remove(54));
		Object[] ints = pq.toArray();
		for (Object num : ints) {
			System.out.println(num);
		}
		System.out.println(pq.peek());
		pq.add(-1);
		System.out.println(pq.peek());
	}

	@Override
	public int compare(Integer o1, Integer o2) {
		return o2.compareTo(o1);
	}
}
