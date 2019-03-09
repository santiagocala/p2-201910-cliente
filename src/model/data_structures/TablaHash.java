package model.data_structures;
import java.util.*;

/**
 * Interfaz con los metodos a realizar por un HashMap 
 */

public interface TablaHash<Clave, Valor>{

	/**
	 * Permite colocar un nuevo elemento en el HashMap
	 * <b>pre: </b> La clave NO debe ser nulo.
	 * post: La pareja clave-valor ha sido agregada.
	 */
	
	public void put (Clave pClave, Valor pValor);
	
	/**
	 * Permite obtener un valor dado por medio de la clave.
	 * * <b>pre: </b> La clave NO debe ser nulo y debe existir dentro de la estructura.
	 * post: Retorna el valor asociado a la clave en caso que exista.
	 * @return El valor asociado a la clave.
	 */
	
	public Valor get (Clave pClave);

	/**
	 * Permite obtener un valor dado por medio de la clave y borrar la tupla asociada.
	 * * <b>pre: </b> La clave NO debe ser nulo.
	 * post: Retorna el valor asociado a la clave en caso que exista.
	 * @return El valor asociado a la clave, null si la clave no existia.
	 */
	
	public Valor delete (Clave pClave);
	
	/**
	 * Iterador sobre las tuplas por medio de las claves.
	 */
	
	public Iterator <Clave> claves();
	
	/**
	 * Devuelve el numero de tuplas clave-valor presentes en la tabla
	 * @return Numero de tuplas clave-valor
	 */
	
	public int size();	
}
