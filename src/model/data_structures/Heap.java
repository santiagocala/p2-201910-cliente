package model.data_structures;

/**
 * Permite implementar una cola de prioridad sobre un binary heap.
 */

public interface Heap <T> {

	/**
	 * Permite agregar un nuevo elemento al heap
	 */
	
	public void add (T elemento);
	
	/**
	 * Permite obtener el elemento con la mayor prioridad actual
	 */
	
	public T get();
	
	/**
	 * Devuelve el numero de elementos que posee el heap
	 * @return Numero de elementos del heap.
	 */
	
	public int size();
}
