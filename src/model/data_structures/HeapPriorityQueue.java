package model.data_structures;

/*
 * Clase tomada del libro Algorithms 4th edition by Robert Sedgewick, Kevin Wayne.
 */

public class HeapPriorityQueue <Key extends Comparable<Key>> 
{

	private Key[] pq; // heap-ordered complete binary tree

	private int N = 0; // in pq[1..N] with pq[0] unused

	public HeapPriorityQueue(int maxN)
	{ 
		pq = (Key[]) new Comparable[maxN+1]; 
	}

	public boolean isEmpty()
	{ 
		return N == 0;
	}

	public int size()
	{ 
		return N; 
	}


	public void insert(Key v, Comparator2<Key> comparador)
	{
		pq[++N] = v;
		swim(N,comparador);
	}

	public Key delMax(Comparator2<Key> comparador)
	{
		Key max = pq[1]; // Retrieve max key from top.
		exch( 1, N--); // Exchange with last item.
		pq[N+1] = null; // Avoid loitering.
		sink(1, comparador); // Restore heap property.
		return max;
	}

	private boolean less(int i, int j, Comparator2<Key> comparador)
	{ 
		return comparador.compare(pq[i],pq[j]) < 0; 
	}

	private void exch(int i, int j)
	{ 
		Key t = pq[i]; 
		pq[i] = pq[j]; 
		pq[j] = t; 
	}

	private void swim(int k , Comparator2<Key> comparador)
	{
		while (k > 1 && less(k/2, k, comparador))
		{
			exch(k/2, k);
			k = k/2;
		}
	}

	private void sink(int k, Comparator2<Key> comparador)
	{
		while (2*k <= N)
		{
			int j = 2*k;
			if (j < N && less(j, j+1, comparador)) j++;
			if (!less(k, j, comparador)) break;
			exch(k, j);
			k = j;
		}
	}
}
