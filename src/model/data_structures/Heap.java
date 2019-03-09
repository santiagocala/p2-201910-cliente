package model.data_structures;

/**
 * Permite implementar una cola de prioridad sobre un binary heap.
 */

public interface Heap <T> {

	/**
	 * Permite agregar un nuevo elemento al monticulo
	 */
	
	public void add (T elemento);
	
	/**
	 * Permite obtener el elemento con la mayor prioridad actual
	 */
	
	public T get();
	
	public int size();
}
