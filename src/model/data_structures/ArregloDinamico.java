package model.data_structures;

import java.util.Comparator;

/**
 * Estructura de Datos Arreglo Dinamico 
 * Comentario de prueba
 */
public class ArregloDinamico<T extends Comparable<T>> implements IArregloDinamico<T> 
{
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
	public ArregloDinamico( int max )
	{
		elementos = (T [])new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	public int darTamano() 
	{
		return tamanoAct;
	}

	public T darElemento(int i)
	{
		return elementos[i];
	}


	@Override
	public void agregar(T dato) {
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos = (T [])new Object[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;

	}
	public void set(int i, T dato)
	{
		if(i<tamanoAct||i==tamanoAct)
		{
			elementos[i]=dato;
		}
		else
		{
			System.out.println(i);
		}
		
	}
	@Override
	public T buscar(T dato) 
	{
		T t= dato;
		T resp=null;
		for(int i =0; i<elementos.length;i++)
		{
			if(elementos[i].compareTo(t)==0)
			{
				resp=elementos[i];
			}
		}
		return resp;
	}

	@Override
	public T eliminar(T dato) {
		 
		T[] copia=elementos;
		if(buscar(dato)!=null)
		{
			elementos = (T [])new Comparable[tamanoAct-1];
			for(int i=0; i<elementos.length; i++)
			{
				if(copia[i].compareTo(dato)==0)
				{//No hace nada
				}
				else
				{
					elementos[i]=copia[i];
				}

			}
		}
		return dato;
	}
	/**
	 * Intercambiar los datos de las posicion i y j
	 * @param i posicion del 1er elemento a intercambiar
	 * @param j posicion del 2o elemento a intercambiar
	 */
	public void exchange( int i, int j)
	{
		T copia=elementos[i];
		elementos[i]=elementos[j];
		elementos[j]=copia;
	}
	
	/**
	 * Ordena el arreglo con base en un comparador que entra por parámetro 
	 * Utiliza el método de MergeSort para poder llevar a cabo el ordenamiento
	 * @param comparador: Comparador que define con que criterio se va a ordenr el arreglo 
	 */
	@SuppressWarnings("unchecked")
	public void ordenar( Comparator<T> comparador) {
				
		System.out.println("El primer objeto antes de ordenar" + elementos[0]);
		
		sort(elementos, comparador);
		
//		T[] aux = (T[]) new Object[tamanoAct];
//		sort(elementos, aux, 0, this.tamanoAct - 1, comparador); 
		
		System.out.println("El primer elemento después de ordenar" + elementos[0]);
		 
	}
	
	/**
	 * Método que primero genera una copia y después agrega una parte 
	 * @param a
	 * @param aux
	 * @param lo
	 * @param mid
	 * @param hi
	 */
	private void merge(T[] a, T[] aux, int lo, int mid, int hi, Comparator<T> comparador) {
		
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if(comparador.compare(aux[j], aux[i]) < 0)
				a[k] = aux[j++];
			else 
				a[k] = aux[i++];
		}
	} 

	
	public void sort(T[] a, Comparator<T> comparador)
	{	

		int N = a.length;
		for (int i = 0; i < N; i++)
			for (int j = i; j > 0; j--) {
				if(comparador.compare(a[j], a[j-1]) < 0){
					
					T temporal = a[j];
					a[j] = a[j-1];
					a[j-1] = temporal;
				}
				else break;
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
}
