package model.data_structures;

import java.util.Comparator;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico<T> implements IArregloDinamico<T> {
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct; 
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	
	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	@SuppressWarnings("unchecked")
	public ArregloDinamico( int max )
	{
		elementos = (T[]) new Object[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	@SuppressWarnings("unchecked")
	public void agregar( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T[ ] copia = elementos;
			elementos = (T[]) new Object[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			}
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;

	}

	public void cambiarTamano( int pTamano)
	{
		tamanoAct = pTamano;
	}
	
	public int darTamano() {

		return tamanoAct;
	}

	public T darElemento(int i) {
		T respuesta = null;

		if(i <= tamanoAct){
			
			respuesta = elementos[i];
		}
		return respuesta;
	}

	@Override
	public T buscar(T dato, Comparator<T> comparador) {
		T respuesta = null;

		for (int i = 0; i < tamanoAct; i++) 
		{
			if(comparador.compare(dato, elementos[i]) == 0)
			{
				respuesta = elementos[i];
			}
		}
		return respuesta;
	}

	public T eliminar(T dato) {

		T respuesta = null;
		boolean eliminado = false;

		for(int  i=0; i < elementos.length && eliminado != true; i++) 
		{
			if(elementos[i]!= null && elementos[i].equals(dato)) 
			{
				respuesta = elementos [i];
				elementos[i] = null;
				eliminado = true;
			}	
		}
		return respuesta;
	}
	
	private void sort(T[] a, T[] aux, int lo, int hi, Comparator<T> comparador)
	{
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid, comparador);
		sort(a, aux, mid+1, hi, comparador);
		merge(a, aux, lo, mid, hi, comparador);
	}
	public void sort(Comparator<T> comparador)
	{
		T[] aux = (T[]) new Object[elementos.length];
		
		sort(elementos, aux, 0, this.tamanoAct -1, comparador);
	} 
	private void merge(T[] a, T[] aux, int lo, int mid, int hi, Comparator<T> comparador)
	{

		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++)
		{
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (comparador.compare(aux[j], aux[i]) <= 0) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	} 
//	/**
//	 * Ordena el arreglo con base en un comparador que entra por parámetro 
//	 * Utiliza el método de MergeSort para poder llevar a cabo el ordenamiento
//	 * @param comparador: Comparador que define con que criterio se va a ordenr el arreglo 
//	 */
//	@SuppressWarnings("unchecked")
//	public void ordenarPor( Comparator<T> comparador) {
//				
//		System.out.println("El primer objeto antes de ordenar" + elementos[0]);
//		
//		sort(elementos, comparador);
//		T[] aux = (T[]) new Object[tamanoAct];
//		sort(elementos, aux, 0, this.tamanoAct - 1, comparador); 
//		
//		System.out.println("El primer elemento después de ordenar" + elementos[0]);
//		 
//	}
//	
//	/**
//	 * Método que primero genera una copia y después agrega una parte 
//	 * @param a
//	 * @param aux
//	 * @param lo
//	 * @param mid
//	 * @param hi
//	 */
//	private void merge(T[] a, T[] aux, int lo, int mid, int hi, Comparator<T> comparador) {
//		for (int k = lo; k <= hi; k++)
//			 aux[k] = a[k];
//			 int i = lo, j = mid+1;
//			 for (int k = lo; k <= hi; k++)
//			 {
//			 if (i > mid) a[k] = aux[j++];
//			 else if (j > hi) a[k] = aux[i++];
//			 else if (comparador.compare(aux[j], aux[i]) < 0) a[k] = aux[j++];
//			 else a[k] = aux[i++];
//			 } 
//	} 
//
//	/**
//	 * Aplica la recursión a los elementos del arreglo 
//	 * @param a
//	 * @param aux
//	 * @param lo
//	 * @param hi
//	 */
//	private void sort(T[] a, T[] aux, int lo, int hi, Comparator<T> comparador) {
//		if (hi <= lo) return;
//		 int mid = lo + (hi - lo) / 2;
//		 sort(a, aux, lo, mid, comparador);
//		 sort(a, aux, mid+1, hi, comparador);
//		 merge(a, aux, lo, mid, hi, comparador); 
//	}
//	
//	public void sort(T[] a, Comparator<T> comparador) {
//		T[] aux = (T[]) new Object[a.length];
//		 sort(a, aux, 0, a.length - 1, comparador);
//	} 
	
	/**
	 * Método que invierte el arreglo y todos los elementos que estaban al comienzo. 
	 */
	public void invertirArreglo() {
			
		int i = 0;
		int j = tamanoAct;
		
		while(i < j) {
			
			T temporal = elementos[i];
			elementos[i] = elementos[j];
			elementos[j] = temporal;
			
			i++;
			j--;
		}	
	}

}