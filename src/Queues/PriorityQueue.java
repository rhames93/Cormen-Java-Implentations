package Queues;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;

public class PriorityQueue<T> extends AbstractQueue<T>
{
	protected int size;
	protected Object queue[];
	protected Comparator<? super T> comparator;
	
	private static final int DEFAULT_SIZE = 1<<4;
	private static final int MAX_SIZE = 1<<30;
	
	public PriorityQueue() {
		this(DEFAULT_SIZE);
	}
	
	public PriorityQueue(int initialSize) {
		this(initialSize, (T t1, T t2) -> {
			Comparable<? super T> comparable = (Comparable<? super T>) t1;
			return comparable.compareTo(t2);
		});
	}
	
	public PriorityQueue(int initialSize, Comparator<? super T> comparator) {
		int length = 1;
		while(length < initialSize) {
			length <<= 1; 
		}
		if(length > PriorityQueue.MAX_SIZE) {
			length = PriorityQueue.MAX_SIZE;
		}
		queue = new Object[length];
		this.comparator = comparator;
		this.size = 0;
	}
	
	public PriorityQueue(Collection<? extends T> collection) {
		this(collection.size());
		int i = 0;
		for(Object obj: collection) {
			queue[i++] = obj;
		}
		this.size = collection.size();
		buildPriorityQueue(this.size - 1);
	}
	
	private void buildPriorityQueue(int startIndex) {
		int currentIndex = startIndex;
		while(currentIndex >= 0) {
			heapify(currentIndex);
			currentIndex--;
		}
	}
	
	private void heapify(int index) {
		T min = (T) queue[index];
		int minIndex = index;
		int leftChildIndex = (index << 1) + 1;
		int rightChildIndex = leftChildIndex + 1;
		if(leftChildIndex < queue.length && queue[leftChildIndex] != null) {
			T leftChild = (T) queue[leftChildIndex];
			if(comparator.compare(min, leftChild) > 1) {
				min = leftChild;
				minIndex = leftChildIndex;
			}
		}
		if(rightChildIndex < queue.length && queue[rightChildIndex] != null) {
			T rightChild = (T) queue[rightChildIndex];
			if(comparator.compare(min, rightChild) > 1) {
				min = rightChild;
				minIndex = rightChildIndex;
			}
		}
		queue[minIndex] = queue[index];
		queue[index] = min;
		if(minIndex == index) {
			return;
		}
		heapify(minIndex);
	}
	
	public void reOrderElements(int index) {
		while(index >= 0) {
			heapify(index);
			index = (index - 1) >> 1;
		}
	}

	@Override
	public boolean offer(T obj) {
		if(size() + 1 >= PriorityQueue.MAX_SIZE) {
			return false;
		}
		if(size() + 1 >= queue.length) {
			increaseQueueSize();
		}
		size++;
		queue[size - 1] = obj;
		reOrderElements(size  - 1);
		return false;
	}

	private void increaseQueueSize() {
		Object[] previousQueue = this.queue;
		this.queue = new Object[previousQueue.length << 1];
		for (int i = 0; i < previousQueue.length; i++) {
			queue[i] = previousQueue[i];
		}
	}

	@Override
	public boolean add(T t) {
		return offer(t);
	}

	@Override
	public T element() {
		return (T) queue[0];
	}

	@Override
	public T peek() {
		return (T) queue[0];
	}

	@Override
	public T poll() {
		if(size() == 0) {
			return null;
		}
		Object temp = queue[0];
		queue[0] = queue[size() - 1];
		queue[size() - 1] = null;
		size--;
		heapify(0);
		return (T) temp;
	}

	@Override
	public T remove() {
		return poll();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int currentIndex = 0;
			
			public T next() {
				currentIndex++;
				return (T) queue[currentIndex];
			}
			
			public boolean hasNext() {
				return currentIndex + 1 < size();
			}
		};
	}

	@Override
	public int size() {
		return size;
	}
	
	public static void main(String[] args) {
	}
}
