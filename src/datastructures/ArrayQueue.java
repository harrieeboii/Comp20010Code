package datastructures;

public class ArrayQueue<E> implements Queue<E> {
	private E[] data;
	private int MAX = 11;
	private int front = 0;
	private int rear = 0;

	// java cannot create an array of a generic type
	// must cast
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		data = (E[]) new Object[MAX];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return (MAX - front + rear) % MAX;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return front == rear;
	}

	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		data[rear] = e;
		rear = (rear + 1) % MAX;
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub
		return data[front];
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		E value = data[front];
		front = (front + 1) % MAX;
		return value;
	}

	public String toString() {
		String ret = new String("(");
		if (!isEmpty()) {
			int i = front;
			do {
				ret += data[i];
				ret += ",";
				i = ++i % data.length;
			} while (i != rear);
			ret += ")";
		}
		return ret;
	}
	
	public ArrayQueue<E> reverseQueue(ArrayQueue<E> q) 
	{ 
	    if (q.isEmpty()) 
	        return q;   
	    E data = q.dequeue();
	    // Reverse remaining queue   
	    q = reverseQueue(q); 
	    // Enqueue current item (to rear)   
	    q.enqueue(data);        
	    return q; 
	}

	public static void main(String[] args) {
		ArrayQueue<Integer> q = new ArrayQueue<Integer>();
		for (int i = 0; i < 10; ++i) {
			q.enqueue(i);
			System.out.println("front=" + q.front + " rear=" + q.rear);
		}

		System.out.println("q:" + q + " size =" + q.size()); // q:(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
		q.dequeue();
		System.out.println("q:" + q + " size =" + q.size()); // q:(1, 2, 3, 4, 5, 6, 7, 8, 9)
		q.dequeue();
		System.out.println("q:" + q + " size =" + q.size()); // q:(2, 3, 4, 5, 6, 7, 8, 9)
		q.enqueue(-1);
		System.out.println("q:" + q + " size =" + q.size()); // q:(2, 3, 4, 5, 6, 7, 8, 9, -1)
		System.out.println(q.reverseQueue(q));
	}
}
