package model.data_structures;
 
/**
 * Permite crear la interfaz con las operaciones basicas que debe realizar un arbol 
 * @param <Key> Es la clase asociada para ser la clave en la estructura de datos
 * @param <Value> Es la clave asociada para ser el valor de la tupla clave-valor
 */

public interface Arbol <Key extends Comparable<? super Key>, Value> extends Iterable<Key>
{
	
	/**
	 * Permite conocer si la estructura esta vacia
	 * @return True si está vacia, False de lo contrario.
	 */
	
	public boolean isEmpty();
	
	/**
	 * Permite devolver el numero de elementos en el árbol
	 * @return Numeros de elementos del arbol
	 */
	
	public int size();	
	
	/**
	 * Permite conocer si existe una clave en el árbol que corresponda al valor pasado por parametro
	 * @param pClave Clave que se desea verificar si existe
	 * @return True si está vacia, False de lo contrario.
	 */
	
	public boolean contains(Key pClave);
	
	/**
	 * Permite devolver el valor de una clave que existe en el arbol
	 * @param pClave Es la clave que sobre la cual se desea obtener su valor.
	 * @return El valor asociado a la clave, null si no existe
	 */
	
	public Value get(Key pClave);
	
	/**
	 * Permite agregar una nueva tupla clave-valor sobre el árbol
	 * @param pClave Es la clave que se desea asociar a la tupla. pClave != null
	 * @param pValor Es el valor asociado que se desea agregar a la tupla.
	 */
	
	public void put(Key pClave, Value pValor);
	
	/**
	 * Permite eliminar una tupla clave-valor por medio de la clave asociada a ella
	 * @param pClave Es la clave del elemento que se desea borrar de la estructura
	 */
	
	public void delete(Key pClave);
	
	/**
	 * Permite devolver la clave valor al minimo nodo de la estructura
	 * @return Clave del nodo con el minimo valor de comparación
	 */
	
	public Key min();
	
	/**
	 * Permite devolver la clave al maximo nodo de la estructura
	 * @return Clave del nodo con el maximo valor de comparación
	 */
	
	public Key max();
}
