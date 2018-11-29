package datastructures;
public class LinkedListQueue<E> implements Queue<E>{
	
	private SinglyLinkedList<E> list = new SinglyLinkedList<>(); // an empty list
	
	/**
	 * Constructs an initially empty stack.
	 * new queue relies on the initially empty list
	 */
	public LinkedListQueue() {
	} // 

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		list.addLast(e);
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub
		return list.first();
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return list.removeFirst();
	}
	
	public String toString() {
		return list.toString();
	}
	public static void main(String[] args) {
		LinkedListQueue<Integer> q = new LinkedListQueue<Integer>();
		for (int i = 0; i < 10; ++i) {
			q.enqueue(i);
		}
		System.out.println("q:" + q + " size =" + q.size()); // q:(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
		q.dequeue();
		System.out.println("q:" + q + " size =" + q.size()); // q:(1, 2, 3, 4, 5, 6, 7, 8, 9)
		q.dequeue();
		System.out.println("q:" + q + " size =" + q.size()); // q:(2, 3, 4, 5, 6, 7, 8, 9)
		q.enqueue(-1);
		System.out.println("q:" + q + " size =" + q.size()); // q:(2, 3, 4, 5, 6, 7, 8, 9, -1)
	}
}
