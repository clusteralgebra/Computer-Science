/**
 * Advanced Computer Science Principles: Minimum Heap
 * @author Alan Yan
 *
 */
public class MinHeap {
	
	public static final int MAX_SIZE = 10000;
	
	private Comparable[] heap;
	private int emptySlot;
	
	//Constructor
	public MinHeap() { 
		heap = new Comparable[MAX_SIZE];
		emptySlot = 1;
	}
	
	// Add an element
	public void add(Comparable item) { 
		heap[emptySlot] = item;
		heapifyUp(emptySlot);
		emptySlot++;
	}
	
	// return minimum
	public Comparable getMin() {
		return heap[1];
	}
	
	// pop the top element
	public Comparable pop() {
		Comparable ans = heap[1];
		remove();
		return ans;
	}
	
	// specified remove
	public void remove(int src) { 
		heap[src] = null;
		emptySlot--;
		heapifyDown(src);
	}
	
	// remove top
	public void remove() {
		remove(1);
	}
	
	/*
	 * Auxiliary Methods
	 */
	public void heapifyUp(int src) {
		if(src == 1 || heap[src/2].compareTo(heap[src]) <= 0)
			return;
		swap(src/2, src);		
		heapifyUp(src/2);
	}
	// this only called when src is null
	public void heapifyDown(int src) { 
		Comparable left = heap[2*src];
		Comparable right = heap[2*src + 1];
		
		if(left == null && right == null) return;
		if(left == null && right != null) {
			swap(src, 2*src+1);
			heapifyDown(2*src+1);
			return;
		}
		if(left != null && right == null) {
			swap(src, 2*src);
			heapifyDown(2*src);
			return;
		}
		if(left.compareTo(right) >= 0) { 
			swap(src, 2*src+1);
			heapifyDown(2*src+1);
			return;
		}
		swap(src, 2*src);
		heapifyDown(2*src);		
	}
	
	public void print() { 
		MinHeap x = new MinHeap();
		for(int i = 1; i < emptySlot; i++)
			x.add(heap[i]);
		String ret = "";
		while(x.getMin() != null)
			ret += x.pop() + ", ";
		System.out.println(ret.substring(0, ret.length() - 2));
	}
	
	
	public void swap(int i, int j) {
		Comparable temp = heap[j];
		heap[j] = heap[i];
		heap[i] = temp;
	}
}
