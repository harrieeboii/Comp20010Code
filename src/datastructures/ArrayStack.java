package datastructures;

public class ArrayStack<E> implements Stack<E> {
	/** Default array capacity. */
	private int MAXSIZE = 512; // default array capacity
	/** Generic array used for storage of stack elements. */
	private E[] data; // generic array used for storage
	/** Index of the top element of the stack in the array. */
	private int t = -1; // index of the top element in stack

	/** Constructs an empty stack using the default array capacity. */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		data = (E[]) new Object[MAXSIZE];
	} // constructs stack with default capacity

	/**
	 * Constructs and empty stack with the given array capacity.
	 * 
	 * @param capacity length of the underlying array
	 */
	@SuppressWarnings({ "unchecked" })
	public ArrayStack(int capacity) { // constructs stack with given capacity
		data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return (t + 1);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (t == -1);
	}

	@Override
	public void push(E e) throws IllegalStateException {
		// TODO Auto-generated method stub
		if (size() == data.length)
			throw new IllegalStateException("Stack is full");
		data[++t] = e;
	}

	@Override
	public E top() {
		// TODO Auto-generated method stub
		if (isEmpty())
			return null;
		return data[t];
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		if (isEmpty())
			return null;
		E answer = data[t];
		data[t] = null;
		t--;
		return answer;
	}
}
