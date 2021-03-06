package model.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import model.vo.Coordenadas;
import model.vo.EstadisticaInfracciones;
import model.vo.EstadisticasCargaInfracciones;
import model.vo.InfraccionesFecha;
import model.vo.InfraccionesFechaHora;
import model.vo.InfraccionesFranjaHoraria;
import model.vo.InfraccionesFranjaHorariaViolationCode;
import model.vo.InfraccionesLocalizacion;
import model.vo.InfraccionesViolationCode;
import model.vo.VOMovingViolations;
import model.data_structures.*;

public class MovingViolationsManager {

	//TODO Definir atributos necesarios
	private ArregloDinamico<VOMovingViolations>arregloDinamico;
	private ComparatorXCoordenadas comparadorCoordenadas;
	private ComparatorXFecha comparadorFecha;
	private ComparatorXViolationCode comparatorCode;
	/**
	 * Metodo constructor
	 */
	public MovingViolationsManager()
	{
		//TODO inicializar los atributos
		arregloDinamico=new ArregloDinamico<VOMovingViolations>(300000);
		comparadorCoordenadas = new ComparatorXCoordenadas();
		comparadorFecha = new ComparatorXFecha();
		comparatorCode = new ComparatorXViolationCode();
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
		Queue[] listaHoras= new Queue[24];
		Queue cola = new Queue();
		for(int i=0; i <arregloDinamico.darTamano();i++)
		{
			VOMovingViolations actual= arregloDinamico.darElemento(i);
			
			//Revisar lo de la hora y el 24
			LocalDateTime fecha=actual.getTicketIssueDate();
			int hora=fecha.getHour();
			
			if(i<24 && listaHoras[i]==null)
			{
				listaHoras[i]=cola;
			}
			
			listaHoras[hora]=listaHoras[hora]==null?cola:listaHoras[hora];
			
			/*VioHour.setPorcentajeAccidentes(porcentajeAccidentes);
			VioHour.setPorcentajeNoAccidentes(porcentajeNoAccidentes);
			VioHour.setTotalInfracciones(totalInfracciones);
			VioHour.setValorTotal(valorTotal);*/
			listaHoras[hora].enqueue(actual);
			
		}
		for(int j=0; j<listaHoras.length;j++)
		{
			VOMovingViolations act= listaHoras[j].size()==0?null:(VOMovingViolations)listaHoras[j].peek();
			InfraccionesFranjaHoraria VioHour = new InfraccionesFranjaHoraria(act==null?null:act.getTicketIssueDate(), act==null?null:act.getTicketIssueDate(), listaHoras[j]);
			maxHeap.agregar(VioHour);

		}
		for(int i=0; i<N;i++)
		{
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
		//VOMovingViolations[] arregloMoving = (VOMovingViolations[])arregloDinamico.darElementos();
		//Ordena el arreglo para que quede ordenado por localizaci�n geogr�fica. 
		arregloDinamico.heapSort(arregloDinamico, comparadorCoordenadas);
		Coordenadas coords = new Coordenadas(arregloDinamico.darElemento(0).getXCoord(), arregloDinamico.darElemento(0).getYCoord());
		TablaHashSC<Coordenadas, InfraccionesLocalizacion>tablaHash = new TablaHashSC<Coordenadas, InfraccionesLocalizacion>();
		Queue cola = new Queue<VOMovingViolations>();
		for(int i=0; i <arregloDinamico.darTamano();i++)
		{
			VOMovingViolations actual= arregloDinamico.darElemento(i);
			if(actual.getXCoord() == coords.getX() && actual.getYCoord() == coords.getY()) 
			{
				cola.enqueue(actual);
			}
			else
			{
				InfraccionesLocalizacion location= new InfraccionesLocalizacion(coords.getX(), coords.getY(),actual.getLocation(),actual.getAddressId(), actual.getStreetSegId(), cola);
				tablaHash.put(coords, location);
				//Se reinicia la cola
				cola = new Queue<VOMovingViolations>();
				if(i+1 < arregloDinamico.darTamano()) 
				{
					coords.cambiarX(arregloDinamico.darElemento(i+1).getXCoord());
					coords.cambiarY(arregloDinamico.darElemento(i+1).getYCoord());
				}
			}

		}

		Coordenadas coordGoal = new Coordenadas(xCoord, yCoord);
		InfraccionesLocalizacion resp= tablaHash.get(coordGoal);
		// TODO completar

		return resp;		
	}

	/**
	 * Requerimiento 3A: Buscar las infracciones por rango de fechas
	 * @param  LocalDate fechaInicial: Fecha inicial del rango de b�squeda
	 * 		LocalDate fechaFinal: Fecha final del rango de b�squeda
	 * @return Cola con objetos InfraccionesFecha
	 */
	public RedBlackBST<String,InfraccionesFecha> consultarInfraccionesPorRangoFechas(LocalDateTime fechaInicial, LocalDateTime fechaFinal)
	{
		arregloDinamico.heapSort(arregloDinamico, comparadorFecha);

		RedBlackBST<String,InfraccionesFecha> arbol = new RedBlackBST<String,InfraccionesFecha>();
		Queue cola = new Queue<VOMovingViolations>();
		LocalDateTime fecha= fechaInicial;
		int i=0;
		boolean limsup=false;
		while( (i <arregloDinamico.darTamano())&&!limsup)
		{
			while(arregloDinamico.darElemento(i).getTicketIssueDate().compareTo(fechaInicial)<0)
			{
				i++;
			}
			if(arregloDinamico.darElemento(i).getTicketIssueDate().compareTo(fechaFinal)>0)
			{
				limsup=true;
			}
			else
			{
				VOMovingViolations actual= arregloDinamico.darElemento(i);
				if(actual.getTicketIssueDate().compareTo(fecha)==0 ) 
				{
					cola.enqueue(actual);
				}
				else
				{
					InfraccionesFecha infrFecha= new InfraccionesFecha(cola,fecha);
					System.out.println(infrFecha.toString());
					arbol.put(fecha.toString(),infrFecha);
					//Se reinicia la cola
					cola = new Queue<VOMovingViolations>();
					if(i+1 < arregloDinamico.darTamano()) 
					{
						fecha = arregloDinamico.darElemento(i+1).getTicketIssueDate();
					}
				}
				i++;
			}

		}

		// TODO completar
		System.out.println("El tama�o del arbol es: "+arbol.size());
		return arbol;			
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
		VOMovingViolations uno=arregloDinamico.darElemento(0);
		Queue<VOMovingViolations> cola = new Queue<VOMovingViolations>();
		for(int i=0; i<arregloDinamico.darTamano();i++)
		{
			VOMovingViolations actual= arregloDinamico.darElemento(i);
			if(actual.getAddressId()==addressID)
			{
				uno= actual;
				cola.enqueue(actual);
			}
		}
		InfraccionesLocalizacion resp= new InfraccionesLocalizacion(uno.getXCoord(), uno.getYCoord(),uno.getLocation(), uno.getAddressId(), uno.getStreetSegId(), cola);
		return resp;		
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

	/**
	 * Requerimiento 4C: Obtener la  informaci�n  de  los c�digos (ViolationCode) ordenados por su numero de infracciones.
	 * @return Contenedora de objetos InfraccionesViolationCode.
		  // TODO Definir la estructura Contenedora
	 */
	public Heap ordenarCodigosPorNumeroInfracciones()
	{
		//<InfraccionesViolationCode> 
		arregloDinamico.heapSort(arregloDinamico, comparatorCode);
		Heap<InfraccionesViolationCode> maxHeap= new Heap<InfraccionesViolationCode>(20);
		String code = arregloDinamico.darElemento(0).getViolationCode();
		Queue cola = new Queue<VOMovingViolations>();
		for(int i=0; i <arregloDinamico.darTamano();i++)
		{
			VOMovingViolations actual= arregloDinamico.darElemento(i);
			if(actual.getViolationCode().equals(code)) 
			{
				cola.enqueue(actual);
			}
			else
			{
				InfraccionesViolationCode infrCode= new InfraccionesViolationCode(actual.getViolationCode(),cola);
				maxHeap.agregar(infrCode);
				//Se reinicia la cola
				cola = new Queue<VOMovingViolations>();
				if(i+1 < arregloDinamico.darTamano()) 
				{
					code =arregloDinamico.darElemento(i+1).getViolationCode();
				}
			}

		}
		return maxHeap;
	}


}
