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
			System.out.println("el puto comparador es null");
		
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
	
	/**
	 * MÃ©todo que invierte el arreglo y todos los elementos que estaban al comienzo. 
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
	 * Intercambia dos elementos dentro de un arreglo dado por parámetro
	 * @param a El arreglo que tiene dos posiciones que se quieren intercambiar
	 * @param i La primera posición que se quiere tener en cuenta
	 * @param j La segunda posición que se quiere tener en cuenta
	 */
	private void exch(T[] a, int i, int j) {
		
		T temporal = elementos[i];
		elementos[i] = elementos[j];
		elementos[j] = temporal;
	}

}