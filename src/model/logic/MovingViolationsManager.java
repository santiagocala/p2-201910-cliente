package model.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import model.data_structures.ArregloDinamico;
import model.data_structures.HeapPriorityQueue;
import model.data_structures.IQueue;
import model.data_structures.MaxColaPrioridad;
import model.data_structures.Queue;
import model.vo.EstadisticaInfracciones;
import model.vo.EstadisticasCargaInfracciones;
import model.vo.InfraccionesFecha;
import model.vo.InfraccionesFechaHora;
import model.vo.InfraccionesFranjaHoraria;
import model.vo.InfraccionesFranjaHorariaViolationCode;
import model.vo.InfraccionesLocalizacion;
import model.vo.InfraccionesViolationCode;
import model.vo.VOMovingViolations;
import model.data_structures.ComparatorXHora;
import model.data_structures.Heap;

public class MovingViolationsManager {

	//TODO Definir atributos necesarios
	private ArregloDinamico<VOMovingViolations>arregloDinamico;
	/**
	 * Metodo constructor
	 */
	public MovingViolationsManager()
	{
		//TODO inicializar los atributos
	}
	
	/**
	 * Cargar las infracciones de un semestre de 2018
	 * @param numeroSemestre numero del semestre a cargar (1 o 2)
	 * @return objeto con el resultado de la carga de las infracciones
	 */
	public EstadisticasCargaInfracciones loadMovingViolations(int numeroSemestre) {
		// TODO Realizar la carga de infracciones del semestre
		
		return null;
	}
	public void setArregloDinamico(ArregloDinamico that)
	{
		this.arregloDinamico=that;
	}

	/**
	  * Requerimiento 1A: Obtener el ranking de las N franjas horarias
	  * que tengan m�s infracciones. 
	  * @param int N: N�mero de franjas horarias que tienen m�s infracciones
	  * @return Cola con objetos InfraccionesFranjaHoraria
	  */
	public IQueue<InfraccionesFranjaHoraria> rankingNFranjas(int N)
	{
		// TODO completar
		Heap<InfraccionesFranjaHoraria> maxHeap = new Heap<InfraccionesFranjaHoraria>(24);
		Queue resp = new Queue();
		InfraccionesFranjaHoraria[] listaHoras= new InfraccionesFranjaHoraria[24];
		for(int i=0; i <arregloDinamico.darTamano();i++)
		{
			double porcentajeAccidentes=0;
			double porcentajeNoAccidentes=0;
			double valorTotal=0;
			int totalInfracciones=arregloDinamico.darTamano();
			VOMovingViolations actual= arregloDinamico.darElemento(i);
			if(actual.getAccidentIndicator().equals("Yes"))
			{
				porcentajeAccidentes++;
			}
			//Revisar lo de la hora y el 24
			LocalDateTime fecha=actual.getTicketIssueDate();
			int hora=fecha.getHour();
			Queue cola = new Queue();
			InfraccionesFranjaHoraria VioHour = new InfraccionesFranjaHoraria(fecha, fecha, cola);
			VioHour.setPorcentajeAccidentes(porcentajeAccidentes);
			VioHour.setPorcentajeNoAccidentes(porcentajeNoAccidentes);
			VioHour.setTotalInfracciones(totalInfracciones);
			VioHour.setValorTotal(valorTotal);
			listaHoras[hora]=VioHour;
			listaHoras[hora].agregarALista(actual);
		}
		for(int j=0; j<listaHoras.length;j++)
		{
			maxHeap.agregar(listaHoras[j]);
			
		}
		for(int i=0; i<N;i++)
		{
			//Cambiar maxHeap porque delMax no funciona
			InfraccionesFranjaHoraria actual= (InfraccionesFranjaHoraria) maxHeap.delMax();
			resp.enqueue(actual);
			//Se imprime de una vez por eficiencia
			System.out.println(actual.toString());
		}
		return (IQueue) resp;	
	}
	
	/**
	  * Requerimiento 2A: Consultar  las  infracciones  por
	  * Localizaci�n  Geogr�fica  (Xcoord, Ycoord) en Tabla Hash.
	  * @param  double xCoord : Coordenada X de la localizacion de la infracci�n
	  *			double yCoord : Coordenada Y de la localizacion de la infracci�n
	  * @return Objeto InfraccionesLocalizacion
	  */
	public InfraccionesLocalizacion consultarPorLocalizacionHash(double xCoord, double yCoord)
	{
		// TODO completar
		return null;		
	}
	
	/**
	  * Requerimiento 3A: Buscar las infracciones por rango de fechas
	  * @param  LocalDate fechaInicial: Fecha inicial del rango de b�squeda
	  * 		LocalDate fechaFinal: Fecha final del rango de b�squeda
	  * @return Cola con objetos InfraccionesFecha
	  */
	public IQueue<InfraccionesFecha> consultarInfraccionesPorRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
	{
		// TODO completar
		return null;		
	}
	
	/**
	  * Requerimiento 1B: Obtener  el  ranking  de  las  N  tipos  de  infracci�n
	  * (ViolationCode)  que  tengan  m�s infracciones.
	  * @param  int N: Numero de los tipos de ViolationCode con m�s infracciones.
	  * @return Cola con objetos InfraccionesViolationCode con top N infracciones
	  */
	public IQueue<InfraccionesViolationCode> rankingNViolationCodes(int N)
	{
		// TODO completar
		return null;		
	}

	
	/**
	  * Requerimiento 2B: Consultar las  infracciones  por  
	  * Localizaci�n  Geogr�fica  (Xcoord, Ycoord) en Arbol.
	  * @param  double xCoord : Coordenada X de la localizacion de la infracci�n
	  *			double yCoord : Coordenada Y de la localizacion de la infracci�n
	  * @return Objeto InfraccionesLocalizacion
	  */
	public InfraccionesLocalizacion consultarPorLocalizacionArbol(double xCoord, double yCoord)
	{
		// TODO completar
		return null;		
	}
	
	/**
	  * Requerimiento 3B: Buscar las franjas de fecha-hora donde se tiene un valor acumulado
	  * de infracciones en un rango dado [US$ valor inicial, US$ valor final]. 
	  * @param  double valorInicial: Valor m�nimo acumulado de las infracciones
	  * 		double valorFinal: Valor m�ximo acumulado de las infracciones.
	  * @return Cola con objetos InfraccionesFechaHora
	  */
	public IQueue<InfraccionesFechaHora> consultarFranjasAcumuladoEnRango(double valorInicial, double valorFinal)
	{
		// TODO completar
		return null;		
	}
	
	/**
	  * Requerimiento 1C: Obtener  la informaci�n de  una  addressId dada
	  * @param  int addressID: Localizaci�n de la consulta.
	  * @return Objeto InfraccionesLocalizacion
	  */
	public InfraccionesLocalizacion consultarPorAddressId(int addressID)
	{
		// TODO completar
		return null;		
	}
	
	/**
	  * Requerimiento 2C: Obtener  las infracciones  en  un  rango de
	  * horas  [HH:MM:SS  inicial,HH:MM:SS  final]
	  * @param  LocalTime horaInicial: Hora  inicial del rango de b�squeda
	  * 		LocalTime horaFinal: Hora final del rango de b�squeda
	  * @return Objeto InfraccionesFranjaHorariaViolationCode
	  */
	public InfraccionesFranjaHorariaViolationCode consultarPorRangoHoras(LocalTime horaInicial, LocalTime horaFinal)
	{
		// TODO completar
		return null;		
	}
	
	/**
	  * Requerimiento 3C: Obtener  el  ranking  de  las  N localizaciones geogr�ficas
	  * (Xcoord,  Ycoord)  con  la mayor  cantidad  de  infracciones.
	  * @param  int N: Numero de las localizaciones con mayor n�mero de infracciones
	  * @return Cola de objetos InfraccionesLocalizacion
	  */
	public IQueue<InfraccionesLocalizacion> rankingNLocalizaciones(int N)
	{
		// TODO completar
		return null;		
	}
	
//	/**
//	  * Requerimiento 4C: Obtener la  informaci�n  de  los c�digos (ViolationCode) ordenados por su numero de infracciones.
//	  * @return Contenedora de objetos InfraccionesViolationCode.
//	  // TODO Definir la estructura Contenedora
//	  */
//	public Contenedora<InfraccionesViolationCode> ordenarCodigosPorNumeroInfracciones()
//	{
//		// TODO completar
//		// TODO Definir la Estructura Contenedora
//		return null;		
//	}


}
