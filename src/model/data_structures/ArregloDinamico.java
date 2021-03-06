package model.data_structures;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
		elementos = (T[]) new Comparable[max];
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
			elementos = (T[]) new Comparable[tamanoMax];
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
	 public T[] darElementos()
	 {
		 return elementos;
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
	
	private void mergeSort(T[] a, T[] aux, int lo, int hi, Comparator<T> comparador)
	{
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		mergeSort(a, aux, lo, mid, comparador);
		mergeSort(a, aux, mid+1, hi, comparador);
		merge(a, aux, lo, mid, hi, comparador);
	}
	public void mergeSort(Comparator<T> comparador)
	{
		T[] aux = (T[]) new Object[elementos.length];
		
		mergeSort(elementos, aux, 0, this.tamanoAct -1, comparador);
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
	
	public T[] shuffle(T[] array){
		Random rgen = new Random();  // Random number generator			

		for (int i=0; i<array.length; i++) {
			int randomPosition = rgen.nextInt(array.length);
			T temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
		}

		return array;
	}
	
	public void quickSort(Comparator<T> comparador) {
		
		if(comparador == null)
			System.out.println("el comparador es null");
		
		shuffle(elementos);
		quickSort(elementos, 0, elementos.length - 1, comparador);
	} 
	private void quickSort(T[] a, int lo, int hi, Comparator<T> comparador) {
		if (hi <= lo) return;
		int j = partition(a, lo, hi, comparador);
		quickSort(a, lo, j-1, comparador);
		quickSort(a, j+1, hi, comparador);
	}

	private int partition(T[] a, int lo, int hi, Comparator<T> comparador) {
		int i = lo, j = hi+1;
		while (true) {
			while (comparador.compare(a[++i], a[lo]) < 0)
				if (i == hi) break;
			while (comparador.compare(a[lo], a[--j]) < 0)
				if (j == lo) break; 
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);    return j;
	} 
	
	public void heapify(ArregloDinamico<T>arr, int n, int i, Comparator<T> comparador) 
	{ 
	    int largest = i; // Initialize largest as root 
	    int l = 2*i + 1; // left = 2*i + 1 
	    int r = 2*i + 2; // right = 2*i + 2 
	  
	    // If left child is larger than root 
	    if (l<n && comparador.compare(arr.darElemento(l), arr.darElemento(largest))>0) 
	        largest = l; 
	  
	    // If right child is larger than largest so far 
	    if (r < n &&  comparador.compare(arr.darElemento(r),arr.darElemento(largest))>0) 
	        largest = r; 
	  
	    // If largest is not root 
	    if (largest != i) 
	    { 
	        swap(arr.darElemento(i), arr.darElemento(largest)); 
	  
	        // Recursively heapify the affected sub-tree 
	        heapify(arr, n, largest, comparador); 
	    } 
	} 
	  
	// main function to do heap sort 
	public void heapSort(ArregloDinamico<T> arr, Comparator<T> comparador) 
	{ 
		
		int n=arr.darTamano();
	    // Build heap (rearrange array) 
	    for (int i = n / 2 - 1; i >= 0; i--) 
	        heapify(arr, n, i, comparador); 
	  
	    // One by one extract an element from heap 
	    for (int i=n-1; i>=0; i--) 
	    { 
	        // Move current root to end 
	    	swap(arr.darElemento(0), arr.darElemento(i)); 
	  
	        // call max heapify on the reduced heap 
	        heapify(arr, i, 0, comparador); 
	    } 
	} 
	
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
	
	/**
	 * Intercambia dos elementos dentro de un arreglo dado por par�metro
	 * @param a El arreglo que tiene dos posiciones que se quieren intercambiar
	 * @param i La primera posici�n que se quiere tener en cuenta
	 * @param j La segunda posici�n que se quiere tener en cuenta
	 */
	private void swap( T i, T j) {
		
		T temporal = i;
		i = j;
		j = temporal;
	}
	/**
	 * Intercambia dos elementos dentro de un arreglo dado por par�metro
	 * @param a El arreglo que tiene dos posiciones que se quieren intercambiar
	 * @param i La primera posici�n que se quiere tener en cuenta
	 * @param j La segunda posici�n que se quiere tener en cuenta
	 */
	private void exch(T[]a, int i, int j) {
		
		T temporal = a[i];
		a[i] = a[j];
		a[j] = temporal;
	}

}