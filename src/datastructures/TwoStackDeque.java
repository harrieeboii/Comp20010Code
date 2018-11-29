package datastructures;
public class TwoStackDeque<E> implements Deque<E> {
	java.util.Stack<E> front = new java.util.Stack<E>();
	java.util.Stack<E> back = new java.util.Stack<E>();

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return front.size() + back.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (front.isEmpty() && back.isEmpty());
	}

	@Override
	public E back() {
		// TODO Auto-generated method stub
		return back.peek();
	}

	public E front() {
		// TODO Auto-generated method stub
		return front.peek();
	}

	@Override
	public E eraseBack() {
		// TODO Auto-generated method stub
		return back.pop();
	}

	@Override
	public E eraseFront() {
		// TODO Auto-generated method stub
		return front.pop();
	}

	@Override
	public void insertBack(E e) {
		// TODO Auto-generated method stub
		back.push(e);
	}

	@Override
	public void insertFront(E e) {
		// TODO Auto-generated method stub
		front.push(e);
	}
}

