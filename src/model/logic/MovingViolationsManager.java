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
	/**
	 * Metodo constructor
	 */
	public MovingViolationsManager()
	{
		//TODO inicializar los atributos
		arregloDinamico=new ArregloDinamico<VOMovingViolations>(300000);
		comparadorCoordenadas = new ComparatorXCoordenadas();
		comparadorFecha = new ComparatorXFecha();
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
	 * que tengan mï¿½s infracciones. 
	 * @param int N: Nï¿½mero de franjas horarias que tienen mï¿½s infracciones
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
			/*VioHour.setPorcentajeAccidentes(porcentajeAccidentes);
			VioHour.setPorcentajeNoAccidentes(porcentajeNoAccidentes);
			VioHour.setTotalInfracciones(totalInfracciones);
			VioHour.setValorTotal(valorTotal);*/
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
	 * Localizaciï¿½n  Geogrï¿½fica  (Xcoord, Ycoord) en Tabla Hash.
	 * @param  double xCoord : Coordenada X de la localizacion de la infracciï¿½n
	 *			double yCoord : Coordenada Y de la localizacion de la infracciï¿½n
	 * @return Objeto InfraccionesLocalizacion
	 */
	public InfraccionesLocalizacion consultarPorLocalizacionHash(double xCoord, double yCoord)
	{
		//Ordena el arreglo para que quede ordenado por localización geográfica. 
		arregloDinamico.quickSort(comparadorCoordenadas);
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
	 * @param  LocalDate fechaInicial: Fecha inicial del rango de bï¿½squeda
	 * 		LocalDate fechaFinal: Fecha final del rango de bï¿½squeda
	 * @return Cola con objetos InfraccionesFecha
	 */
	public RedBlackBST<String,InfraccionesFecha> consultarInfraccionesPorRangoFechas(LocalDateTime fechaInicial, LocalDateTime fechaFinal)
	{
		arregloDinamico.quickSort(comparadorFecha);

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

		return arbol;			
	}

	/**
	 * Requerimiento 1B: Obtener  el  ranking  de  las  N  tipos  de  infracciï¿½n
	 * (ViolationCode)  que  tengan  mï¿½s infracciones.
	 * @param  int N: Numero de los tipos de ViolationCode con mï¿½s infracciones.
	 * @return Cola con objetos InfraccionesViolationCode con top N infracciones
	 */
	public IQueue<InfraccionesViolationCode> rankingNViolationCodes(int N)
	{
		// TODO completar
		return null;		
	}


	/**
	 * Requerimiento 2B: Consultar las  infracciones  por  
	 * Localizaciï¿½n  Geogrï¿½fica  (Xcoord, Ycoord) en Arbol.
	 * @param  double xCoord : Coordenada X de la localizacion de la infracciï¿½n
	 *			double yCoord : Coordenada Y de la localizacion de la infracciï¿½n
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
	 * @param  double valorInicial: Valor mï¿½nimo acumulado de las infracciones
	 * 		double valorFinal: Valor mï¿½ximo acumulado de las infracciones.
	 * @return Cola con objetos InfraccionesFechaHora
	 */
	public IQueue<InfraccionesFechaHora> consultarFranjasAcumuladoEnRango(double valorInicial, double valorFinal)
	{
		// TODO completar
		return null;		
	}

	/**
	 * Requerimiento 1C: Obtener  la informaciï¿½n de  una  addressId dada
	 * @param  int addressID: Localizaciï¿½n de la consulta.
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
	 * @param  LocalTime horaInicial: Hora  inicial del rango de bï¿½squeda
	 * 		LocalTime horaFinal: Hora final del rango de bï¿½squeda
	 * @return Objeto InfraccionesFranjaHorariaViolationCode
	 */
	public InfraccionesFranjaHorariaViolationCode consultarPorRangoHoras(LocalTime horaInicial, LocalTime horaFinal)
	{
		// TODO completar
		return null;		
	}

	/**
	 * Requerimiento 3C: Obtener  el  ranking  de  las  N localizaciones geogrï¿½ficas
	 * (Xcoord,  Ycoord)  con  la mayor  cantidad  de  infracciones.
	 * @param  int N: Numero de las localizaciones con mayor nï¿½mero de infracciones
	 * @return Cola de objetos InfraccionesLocalizacion
	 */
	public IQueue<InfraccionesLocalizacion> rankingNLocalizaciones(int N)
	{
		// TODO completar
		return null;		
	}

	//	/**
	//	  * Requerimiento 4C: Obtener la  informaciï¿½n  de  los cï¿½digos (ViolationCode) ordenados por su numero de infracciones.
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
