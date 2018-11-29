package datastructures;

public interface Deque<E> {
	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return number of elements in the queue
	 */
	int size();
	
	/**
	 * Tests whether the queue is empty.
	 * 
	 * @return true if the queue is empty, false otherwise
	 */
	boolean isEmpty();
	
	E back();
	
	E front();
	
	E eraseBack();
	
	E eraseFront();
	
	void insertBack(E e);
	
	void insertFront(E e);
}
