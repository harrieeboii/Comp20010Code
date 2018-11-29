package datastructures;
public class LinkedListStack<E> implements Stack<E> {
	/**
	 * The primary storage for elements of the stack
	 */
	private SinglyLinkedList<E> list = new SinglyLinkedList<>(); // an empty list

	/**
	 * Constructs an initially empty stack.
	 */
	public LinkedListStack() {
	} // new stack relies on the initially empty list

	/**
	 * Returns the number of elements in the stack.
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 */
	public E top() {
		return list.first();
	}

	/**
	 * Tests whether the stack is empty.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(E e) {
		// TODO Auto-generated method stub
		list.addFirst(e);
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		return list.removeFirst();
	}
	
	public void popAll() {

	     if(list.isEmpty()) {
	        //nothing to remove, return
	        return;
	     }
	     list.removeFirst();  // remove one stack element

	     popAll(); // recursive invocation of your method

	}
}
