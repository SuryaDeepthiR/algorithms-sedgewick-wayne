package chapter1.section3.linked.list;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Exercise29<Item> implements Iterable<Item> {
	
	private class Node {
		Item item;
		Node next;
	}
	
	Node last;
	int size;
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void enqueue(Item item) {
		if (isEmpty()){
			last = new Node();
			last.item = item;
			last.next = last;
		} else{
			Node node = new Node();
			node.item = item;
			
			if (size == 1) {
				last.next = node;
				node.next = last;
			} else {
				node.next = last.next;
				last.next = node;
			}
			last = node;
		}
		size++;
	}
	
	public Item dequeue() {
		if (isEmpty()) {
			return null;
		}
		
		Item item = null;
		
		if (size == 1) {
			item = last.item;
			last = null;
			
		} else {
			item = last.next.item;
			last.next = last.next.next;
		}
		size--;
		
		return item;
	}
	
	public Iterator<Item> iterator(){
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<Item>{
		
		private Node current;
		int count = 0;
		
		public QueueIterator() {
			if (last != null && size > 1) {
				current = last.next;
			} else {
				current = last;
			}
		}
		
		public Item next() {
			count++;
			
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public boolean hasNext(){
			return count < size;
		}
	}
	
	public static void main (String[] args){
		Exercise29<Integer> queue = new Exercise29<>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		
		for (int number : queue) {
			StdOut.println(number);
		}
	}
	
}
