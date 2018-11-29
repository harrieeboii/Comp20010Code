package datastructures;
import java.util.*;
public class SinglyLinkedList<E> implements Iterable<E>, List<E> {

	private static class Node<E> {
		private E data;
		private Node<E> next;

		public Node(E e, Node<E> n) {
			this.data = e;
			this.next = n;
		}

		public E getData() {
			return this.data;
		}

		public Node<E> getNext() {
			return this.next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public String toString() {
			return this.data.toString();
		}
	}

	private Node<E> head;
	private Node<E> tail;
	
	public SinglyLinkedList() {
		head = null;
		tail = null;
	}// constructs an initial empty Linked List

	public int size() {
		return size(head);
	}// driver function

	private int size(Node<E> n) {
		if (n == null)
			return 0;
		return 1 + size(n.getNext());
	}// worker function

	public boolean isEmpty() {
		// TODO
		return (size() == 0);
	}

	// return the first element
	public E first() {
		// TODO
		if (isEmpty())
			return null;
		return head.getData();
	}

	public E last() {
		// TODO
		if (isEmpty())
			return null;
		Node<E> tmp = head;
		while (tmp.getNext() != null) {
			tmp = tmp.getNext();
		}
		return tmp.getData();
	}

	public void addFirst(E data) {
		// TODO
		Node<E> tmp = new Node<E>(data, head);
		this.head = tmp;
	}

	public void addLast(E data) {
		// TODO
		if (isEmpty())
			addFirst(data);
		else {
			Node<E> tmp = head;
			while (tmp.getNext() != null) {
				tmp = tmp.getNext();
			}
			Node<E> newTail = new Node<E>(data, null);
			tail= newTail;
			tmp.setNext(tail);
		}
	}

	public void addAfter(E key, E element) {
		Node<E> tmp = head;
		while (tmp != null && !tmp.getData().equals(key))
			tmp = tmp.next;
		if (tmp != null) {
			tmp.setNext(new Node<E>(element, tmp.getNext()));
		}
	}

	public void addBefore(E key, E element) {
		if (isEmpty()) {
			throw new RuntimeException("cannot add before as List is empty.");
		}
		if (head.getData().equals(key)) {
			addFirst(element);
			return;
		} // special case
		Node<E> prev = null;
		Node<E> curr = head;
		while (curr != null && !curr.getData().equals(key)) {
			prev = curr;
			curr = curr.getNext();
		}
		// insert between current and previous
		if (curr != null) {
			prev.setNext(new Node<E>(element, curr));
		}
	}
	private Node<E> getNode(int pos) {
		Node<E> curr = head;
		for (int i = 0; i < pos; ++i) {
			curr = curr.next;
		}
		return curr;
	}
	public void remove(E key) {
		if (isEmpty())
			throw new RuntimeException("cannot delete as List is empty.");
		if (head.getData().equals(key)) {
			head = head.next;
			return;
		}
		Node<E> curr = head;
		Node<E> prev = null;
		while (curr != null && !curr.getData().equals(key)) {
			prev = curr;
			curr = curr.next;
		}
		if (curr == null)
			throw new RuntimeException("cannot delete.\nKey isn't in the List.");
		// delete current node
		prev.next = curr.next;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node<E> walk = head;
		while (walk != null) {
			sb.append(walk.getData());
			if (walk != tail)
				sb.append(", ");
			walk = walk.getNext();
		}
		sb.append("]");
		return sb.toString();
	}

	public Iterator<E> iterator() {
		return new ListIterator(); // create a new instance of the inner class
	}

	private class ListIterator implements Iterator<E> {

		private Node<E> iterator;

		ListIterator() {
			this.iterator = head;
		}

		public boolean hasNext() {
			return (iterator.getNext() != null);
		}

		public E next() {
			E data = iterator.getData();
			iterator = iterator.getNext();
			return data;
		}

		public void remove() {
			// NOT IMPLEMENTED
		}

	}

	public Object copy() {
		SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
		Node<E> tmp = head;
		while (tmp != null) {
			twin.addLast(tmp.getData());
			tmp = tmp.next;
		}
		return twin;
	}

	public void reverse() {
		Node<E> prev = null;
		Node<E> curr = head;
		Node<E> next;
		while (curr != null) {
			next = curr.getNext();
			curr.setNext(prev);
			prev = curr;
			curr = next;
		}
		head = prev;
	}

	public boolean isPalindrome() {
		SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
		Node<E> tmp = head;
		while (tmp != null) {
			twin.addLast(tmp.getData());
			tmp = tmp.next;
		}
		Node<E> prev = null;
		Node<E> curr = this.head;
		Node<E> next;
		while (curr != null) {
			next = curr.getNext();
			curr.setNext(prev);
			prev = curr;
			curr = next;
		}
		this.head = prev;
		Node<E> a = this.head, b = twin.head;
		while (a != null && b != null) {
			if (a.data != b.data)
				return false;
			a = a.next;
			b = b.next;
		}
		return (a == null && b == null);
	}

	boolean detectLoop() {
		Node<E> slow = head;
		Node<E> fast = head;
		while (slow != null && fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (slow == fast) {
				System.out.println("Found loop");
				return true;
			}
		}
		return false;
	}
	/*
	 * This returns the element at position i
	 */
	@Override
	public E get(int pos) {
		return getNode(pos).getData();
	}

	@Override
	public void set(int i, E e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(int i, E e) {
		// TODO Auto-generated method stub

	}

	public E remove(int i) {
		Node<E> prev = getNode(i - 1);
		Node<E> curr = prev.getNext();
		E data = curr.getData();
		prev.setNext(curr.getNext()); // skip to the next node, curr is no longer in the list
		return data;
	}
	
	public E removeFirst() {
		if (isEmpty()) {
			return null; // nothing to remove
		}
		E res = head.getData();
		head = head.getNext(); // will become null if list had only one node
		return res;
	}

	public E removeLast() {
		if (isEmpty()) {
			return null;
		}
		return remove(size() - 1);
	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> linkedlist = new SinglyLinkedList<Integer>();
		SinglyLinkedList<Integer> linkedlist2 = new SinglyLinkedList<Integer>();
		linkedlist.addLast(1);
		linkedlist.addLast(2);
		linkedlist.addLast(3);
		linkedlist.addLast(2);
		linkedlist.addLast(1);
		System.out.println(linkedlist);
		System.out.println(linkedlist.isPalindrome());
		linkedlist2.addLast(2);
		linkedlist2.addLast(2);
		linkedlist2.addLast(3);
		linkedlist2.addLast(2);
		linkedlist2.addLast(1);
		System.out.println(linkedlist2);
		System.out.println(linkedlist2.isPalindrome());
	}
}