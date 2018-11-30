package datastructures;

import java.util.Iterator;

/**
 * A basic doubly linked list implementation.
 */
public class DoublyLinkedList<E> implements List<E> {

	private static class Node<E> {

		/** The element stored at this node */
		private E data;

		/** A reference to the preceding node in the list */
		private Node<E> prev;

		/** A reference to the subsequent node in the list */
		private Node<E> next;

		/**
		 * Creates a node with the given element and next node.
		 *
		 * @param e the element to be stored
		 * @param p reference to a node that should precede the new node
		 * @param n reference to a node that should follow the new node
		 */
		public Node(E e, Node<E> p, Node<E> n) {
			this.data = e;
			this.prev = p;
			this.next = n;
		}

		/**
		 * Returns the element stored at the node.
		 * 
		 * @return the element stored at the node
		 */
		public E getData() {
			return this.data;
		}

		/**
		 * Sets the data in the node
		 * 
		 * @param e the element stored at the node
		 */
		public void setData(E e) {
			this.data = e;
		}

		/**
		 * Returns the node that precedes this one (or null if no such node).
		 * 
		 * @return the preceding node
		 */
		public Node<E> getPrev() {
			return this.prev;
		}

		/**
		 * Returns the node that follows this one (or null if no such node).
		 * 
		 * @return the following node
		 */
		public Node<E> getNext() {
			return this.next;
		}

		// Update methods
		/**
		 * Sets the node's previous reference to point to Node n.
		 * 
		 * @param p the node that should precede this one
		 */
		public void setPrev(Node<E> p) {
			this.prev = p;
		}

		/**
		 * Sets the node's next reference to point to Node n.
		 * 
		 * @param n the node that should follow this one
		 */
		public void setNext(Node<E> n) {
			this.next = n;
		}
	}

	/** Sentinel node at the beginning of the list */
	private Node<E> header;

	/** Sentinel node at the end of the list */
	private Node<E> trailer;

	/** Number of elements in the list (not including sentinels) */
	private int size = 0;

	/** Constructs a new empty list. */
	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}

	/**
	 * Returns the number of elements in the linked list.
	 * 
	 * @return number of elements in the linked list
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Tests whether the linked list is empty.
	 * 
	 * @return true if the linked list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	private Node<E> getNode(int pos) {
		Node<E> curr = header.getNext();
		for (int i = 0; i < pos; ++i) {
			curr = curr.next;
		}
		return curr;
	}

	@Override
	public E get(int i) {
		return getNode(i).getData();
	}

	/**
	 * Returns (but does not remove) the first element of the list.
	 * 
	 * @return element at the front of the list (or null if empty)
	 */
	public E first() {
		if (isEmpty())
			return null;
		return header.getNext().getData();
	}

	/**
	 * Returns (but does not remove) the last element of the list.
	 * 
	 * @return element at the end of the list (or null if empty)
	 */
	public E last() {
		if (isEmpty())
			return null;
		return trailer.getPrev().getData();
	}

	@Override
	public void set(int i, E e) {
		getNode(i).setData(e);

	}

	/**
	 * Adds an element to the front of the list.
	 * 
	 * @param data the new element to add
	 */
	public void addFirst(E data) {
		addBetween(data, header, header.getNext()); // place just after the header
	}

	/**
	 * Adds an element to the end of the list.
	 * 
	 * @param data the new element to add
	 */
	public void addLast(E data) {
		this.addBetween(data, trailer.getPrev(), trailer); // place just before the trailer
	}

	/**
	 * Removes and returns the first element of the list.
	 * 
	 * @return the removed element (or null if empty)
	 */
	public E removeFirst() {
		if (isEmpty())
			return null;
		return this.remove(header.getNext());
	}

	/**
	 * Removes and returns the last element of the list.
	 * 
	 * @return the removed element (or null if empty)
	 */
	public E removeLast() {
		if (isEmpty())
			return null;
		return this.remove(trailer.getPrev());
	}

	/**
	 * Adds an element to the linked list in between the given nodes. The given
	 * predecessor and successor should be neighboring each other prior to the call.
	 *
	 * @param predecessor node just before the location where the new element is
	 *                    inserted
	 * @param successor   node just after the location where the new element is
	 *                    inserted
	 */
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<E>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
		return;
	}

	/**
	 * Removes the given node from the list and returns its element.
	 * 
	 * @param node the node to be removed (must not be a sentinel)
	 */
	private E remove(Node<E> node) {
		Node<E> prev = node.getPrev();
		Node<E> succ = node.getNext();
		prev.setNext(succ);
		succ.setPrev(prev);
		size--;
		return node.getData();
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}

	private class ListIterator<T> implements Iterator<T> {

		@SuppressWarnings("unchecked")
		Node<T> curr = (Node<T>) header.getNext();

		@Override
		public boolean hasNext() {
			return curr != trailer;
		}

		@Override
		public T next() {
			T val = curr.getData();
			curr = curr.getNext();
			return val;
		}
	}

	@Override
	public void add(int i, E e) {
		Node<E> n = getNode(i);
		addBetween(e, n.getPrev(), n);
	}

	@Override
	public E remove(int i) {
		Node<E> n = getNode(i);
		E res = n.getData();
		remove(n);
		return res;
	}

	public String toString() {
		String output = new String();
		output += "size=" + size() + "\n";
		Node<E> curr = header.getNext();
		while (curr != trailer) {
			output += "> " + curr.getData() + "\n";
			curr = curr.getNext();
		}
		return output;
	}
}
