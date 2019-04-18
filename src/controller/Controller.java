package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.vo.*;
import model.data_structures.*;
import model.logic.ManejoFechaHora;
import model.logic.MovingViolationsManager;
import view.MovingViolationsManagerView;


public class Controller {

	// Componente vista (consola)
	private MovingViolationsManagerView view;
	
	// Componente modelo (logica de la aplicacion)
	private MovingViolationsManager model;
	
	// Comparadores para métodos
	private ComparatorXViolationCode comparadorCodigo;
	private ComparatorXCoordenadas comparadorCoordenadas;
	private ComparatorXDesc comparadorDesc;
	private ComparatorXFecha comparadorFecha;
	public static Double Xmin, Ymin, Xmax, Ymax;
	private ArregloDinamico<VOMovingViolations> arregloDinamico;

	/**
	 * Metodo constructor
	 */
	public Controller()
	{
		view = new MovingViolationsManagerView();
		model = new MovingViolationsManager();
		arregloDinamico = new ArregloDinamico<VOMovingViolations>(3000);
		comparadorCodigo = new ComparatorXViolationCode();
		comparadorCoordenadas = new ComparatorXCoordenadas();
		comparadorDesc = new ComparatorXDesc();
		comparadorFecha = new ComparatorXFecha();
		Xmin=393185.8;
		Ymin=138316.9;

		Xmax=0.0;
		Ymax=0.0;
	}
	
	/**
	 * Metodo encargado de ejecutar los  requerimientos segun la opcion indicada por el usuario
	 */
	public void run(){

		long startTime;
		long endTime;
		long duration;

		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		Controller controller = new Controller();

		while(!fin){
			view.printMenu();

			int option = sc.nextInt();

			switch(option){

			case 0:
				view.printMessage("Ingrese el semestre (1 o 2)");
				int numeroSemestre = sc.nextInt();
				
				try 
				{
					
					controller.loadPorSemestre(numeroSemestre);
					model.setArregloDinamico(controller.arregloDinamico);
				}
				catch(Exception e) {
					
					System.out.println(e.getMessage());
				}
				System.out.println("");
				System.out.println("El total de infracciones del semestre fue: "+controller.darArregloDinamico().darTamano());
				System.out.println("La zona geográfica Minimax es: ("+Xmin+","+Ymin+") y ("+Xmax+","+Ymax+")");
				//EstadisticasCargaInfracciones resumenCarga = model.loadMovingViolations(semestre);

				//TODO Mostrar resultado de tipo EstadisticasCargaInfracciones con: 
				//     total de infracciones cargadas, numero de infracciones cargadas por mes y zona Minimax (Xmin, Ymin) y (Xmax, Ymax)
				//view.printResumenLoadMovingViolations( ... );
				break;

			case 1:
				view.printMessage("1A. Consultar las N franjas horarias con mas infracciones que desea ver. Ingresar valor de N: ");
				int numeroFranjas = sc.nextInt();

				//TODO Completar para la invocaciï¿½n del metodo 1A
				model.rankingNFranjas(numeroFranjas);
				
				//TODO Mostrar resultado de tipo Cola con N InfraccionesFranjaHoraria
				//Lo hace en el mismo método por eficiencia
				//view.printReq1A();
				break;

			case 2:
				view.printMessage("Ingrese la coordenada en X de la localizacion geografica (Ej. 1234,56): ");
				double xcoord = sc.nextDouble();
				view.printMessage("Ingrese la coordenada en Y de la localizacion geografica (Ej. 5678,23): ");
				double ycoord = sc.nextDouble();

				//TODO Completar para la invocaciï¿½n del metodo 2A
				//model.consultarPorLocalizacionHash(double xCoord, double yCoord)

				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 
				//view.printReq2A( ... )
				break;

			case 3:
				view.printMessage("Ingrese la fecha inicial del rango. Formato aï¿½o-mes-dia (ej. 2008-06-21)");
				String fechaInicialStr = sc.next();
				LocalDateTime fechaInicial = controller.convertirFecha_Hora_LDT(fechaInicialStr);

				view.printMessage("Ingrese la fecha final del rango. Formato aï¿½o-mes-dia (ej. 2008-06-30)");
				String fechaFinalStr = sc.next();
				LocalDateTime fechaFinal = controller.convertirFecha_Hora_LDT(fechaInicialStr);

				//TODO Completar para la invocacion del metodo 3A
				model.consultarInfraccionesPorRangoFechas(fechaInicial, fechaFinal);
				//TODO Mostrar resultado de tipo Cola de InfraccionesFecha
				//view.printReq3A( ... )
				break;


			case 4:
				view.printMessage("1B. Consultar los N Tipos con mas infracciones. Ingrese el valor de N: ");
				int numeroTipos = sc.nextInt();
				MaxColaPrioridad cola = controller.reqFuncional1B();

				//TODO Completar para la invocaciï¿½n del metodo 1B				
				//model.rankingNViolationCodes(int N)
				
				//TODO Mostrar resultado de tipo Cola con N InfraccionesViolationCode
				//view.printReq1B( ... )
				break;

			case 5:						
				view.printMessage("Ingrese la coordenada en X de la localizacion geografica (Ej. 1234,56): ");
				xcoord = sc.nextDouble();
				view.printMessage("Ingrese la coordenada en Y de la localizacion geografica (Ej. 5678,23): ");
				ycoord = sc.nextDouble();
				
				InfraccionesLocalizacion p = controller.reqFuncional2B(xcoord, ycoord);
				//TODO Completar para la invocaciï¿½n del metodo 2B
				//model.consultarPorLocalizacionArbol(double xCoord, double yCoord)

				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 
				//view.printReq2B( ... )
				break;

			case 6:
				
				System.out.println("el primer elemento tiene violation code : "+ controller.arregloDinamico.darElemento(1).getViolationCode());
				System.out.println("el 45 elemento tiene violation code : "+ controller.arregloDinamico.darElemento(45).getViolationCode());
				System.out.println("el 108 elemento tiene violation code : "+ controller.arregloDinamico.darElemento(108).getViolationCode());
				
				controller.arregloDinamico.quickSort(comparadorDesc);
				System.out.println("--------");
				
				System.out.println("el primer elemento tiene violation code : "+ controller.arregloDinamico.darElemento(1).getViolationCode());
				System.out.println("el 45 elemento tiene violation code : "+ controller.arregloDinamico.darElemento(45).getViolationCode());
				System.out.println("el 108 elemento tiene violation code : "+ controller.arregloDinamico.darElemento(108).getViolationCode());
				
				
//				view.printMessage("Ingrese la cantidad minima de dinero que deben acumular las infracciones en sus rangos de fecha  (Ej. 1234,56)");
//				double cantidadMinima = sc.nextDouble();
//
//				view.printMessage("Ingrese la cantidad maxima de dinero que deben acumular las infracciones en sus rangos de fecha (Ej. 5678,23)");
//				double cantidadMaxima = sc.nextDouble();

				//TODO Completar para la invocaciï¿½n del metodo 3B
				//model.consultarFranjasAcumuladoEnRango(double valorInicial, double valorFinal)

				//TODO Mostrar resultado de tipo Cola con InfraccionesFechaHora 
				//view.printReq3B( ... )
				break;

			case 7:
				view.printMessage("1C. Consultar las infracciones con Address_Id. Ingresar el valor de Address_Id: ");
				int addressID = sc.nextInt();

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocaciï¿½n del metodo 1C
				InfraccionesLocalizacion resp=model.consultarPorAddressId(addressID);

				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 1C: " + duration + " milisegundos");

				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 	
				System.out.println(resp.toString());
				break;

			case 8:
				view.printMessage("Ingrese la hora inicial del rango. Formato HH:MM:SS (ej. 09:30:00)");
				String horaInicialStr = sc.next();
				LocalDateTime horaInicial = ManejoFechaHora.convertirFecha_Hora_LDT(horaInicialStr);

				view.printMessage("Ingrese la hora final del rango. Formato HH:MM:SS (ej. 16:00:00)");
				String horaFinalStr = sc.next();
				LocalDateTime horaFinal = ManejoFechaHora.convertirFecha_Hora_LDT(horaFinalStr);

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocacion del metodo 2C
				//model.consultarPorRangoHoras(LocalTime horaInicial, LocalTime horaFinal)

				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 2C: " + duration + " milisegundos");
				controller.reqFuncional2C(horaInicial, horaFinal);
				break;

			case 9:
				view.printMessage("Consultar las N localizaciones geograficas con mas infracciones. Ingrese el valor de N: ");
				int numeroLocalizaciones = sc.nextInt();

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocaciï¿½n del metodo 3C
				//model.rankingNLocalizaciones(int N)

				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 3C: " + duration + " milisegundos");
				
				break;

			case 10:

				System.out.println("Grafica ASCII con la informacion de las infracciones por ViolationCode");

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocacion del metodo 4C
				Heap resp10=model.ordenarCodigosPorNumeroInfracciones();

				//TODO Mostrar grafica a partir del resultado del metodo anterior
				view.printReq4C( resp10 );
				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 4C: " + duration + " milisegundos");
				break;

			case 11:	
				fin = true;
				sc.close();
				break;
			}
		}
	}
	
	/**
	 * Determina cuÃ¡l de los cuatrimestres convoca al mÃ©todo que los carga en Ã³rdne 
	 * @param pCuatrimestre
	 * @throws Exception
	 */
	public void loadPorSemestre(int pSemestre) throws Exception {
		
		String meses[] = new String[6];
		String rutai = "src/data/Moving_Violations_Issued_in_";
		String rutaf = "_2018.csv";
		
		
		if(pSemestre == 1) {
			
			meses[0] = "January";
			meses[1] = "February";
			meses[2] = "March";
			meses[3] = "April";
			meses[4] = "May";
			meses[5] = "June";
		}

		else if(pSemestre == 2) {
			
			meses[0] = "July";
			meses[1] = "August";
			meses[2] = "September";
			meses[3] = "October";
			meses[4] = "November";
			meses[5] = "December";
		}
		else
			throw new Exception("Cuatrimestre invÃ¡lido");
		//Lo cambie para cargar solo 4 meses
		for(int i = 0; i < 2; i++) {
			
			try {
			
				File f = new File(rutai + meses[i] + rutaf);
				loadMovingViolations(f);
			}
			catch(Exception e) {
				
				throw e;
			}
		}
		
	}

	public ArregloDinamico<VOMovingViolations> darArregloDinamico()
	{
		return arregloDinamico;
	}
	/**
	 * Lee la informaciÃ³n de un archivo que le llega por parÃ¡metro y se encarga de meterlo al arreglo 
	 * @param pArchivo
	 * @throws Exception
	 */
	public int loadMovingViolations(File pArchivo) throws Exception {
		System.out.println("");
		System.out.println("Se está cargando: "+pArchivo);
		int numCargados=0;
		FileReader lector = new FileReader(pArchivo);
		BufferedReader br = new BufferedReader(lector);
		br.readLine();
		String linea = br.readLine();
		
		while(linea != null) {
			
			String arreglo[] = linea.split(",");
			int id = Integer.parseInt(arreglo[0]);
			int adressid = convertirInt(arreglo[3]);
			double xcoord = convertirDouble(arreglo[5]);
			double ycoord = convertirDouble(arreglo[6]);
			int fineamt = convertirInt(arreglo[8]);
			int pagado = convertirInt(arreglo[9]);
			int penalty = convertirInt(arreglo[10]);
			//Rectangulo Min y Max
			if(xcoord>Xmax){Xmax=xcoord;/*if(i<20){System.out.println("xmax");i++;}*/}
			if(ycoord>Ymax){Ymax=ycoord;/*if(j<20){System.out.println("ymax");j++;}*/}
			if(xcoord<Xmin){Xmin=xcoord;/*if(k<20){System.out.println("xmin");k++;}*/}
			if(ycoord<Ymin){Ymin=ycoord;/*if(l<20){System.out.println("ymin");l++;}*/}
			
			
			LocalDateTime fechaHora = convertirFecha_Hora_LDT(arreglo[13]);
			
			VOMovingViolations infraccion = new VOMovingViolations(id, arreglo[2], adressid, arreglo[4], xcoord, ycoord, 
					fineamt, pagado, penalty, arreglo[12], fechaHora, arreglo[14], arreglo[15]); 
			
			
			//arbolBalanceado.put(id, infraccion);
			arregloDinamico.agregar(infraccion);
			numCargados++;
			
			linea = br.readLine();

		}
		
		// TODO: Cuidado
		// Debido a que mi computador no cuenta con la memoria necesaria para hacer los procesos con todos los datos, le puse este lÃ­mite
		// Esto es con el fin de poder probar el programa y poder ver si estÃ¡ funcionando. 
		//arregloDinamico.cambiarTamano(200);
		System.out.println("El total de infracciones del mes fue: "+numCargados);
		System.out.println("La cantidad de elementos que se agregaron al arreglo hasta el momento es de " + arregloDinamico.darTamano());
		
		br.close();
		return numCargados;
	}
	
	/**
	  * Requerimiento 1A: Obtener el ranking de las N franjas horarias
	  * que tengan mï¿½s infracciones. 
	  * @param int N: Nï¿½mero de franjas horarias que tienen mï¿½s infracciones
	  * @return Cola con objetos InfraccionesFranjaHoraria
	  */
	/*public IQueue rankingNFranjas(int N)
	{
		// TODO completar
		MaxColaPrioridad maxHeap = new MaxColaPrioridad<InfraccionesFranjaHoraria>();
		Queue resp = new Queue();
		InfraccionesFranjaHoraria[] listaHoras= new InfraccionesFranjaHoraria[24];
		for(int i=0; i <arregloDinamico.darTamano();i++)
		{
			VOMovingViolations actual= arregloDinamico.darElemento(i);
			//Revisar lo de la hora y el 24
			listaHoras[actual.getTicketIssueDate().getHour()].agregarALista(actual);
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
	}*/
	
	/**
	  * Requerimiento 2A: Consultar  las  infracciones  por
	  * Localizaciï¿½n  Geogrï¿½fica  (Xcoord, Ycoord) en Tabla Hash.
	  * @param  double xCoord : Coordenada X de la localizacion de la infracciï¿½n
	  *			double yCoord : Coordenada Y de la localizacion de la infracciï¿½n
	  * @return Objeto InfraccionesLocalizacion
	  */
	/*public InfraccionesLocalizacion consultarPorLocalizacionHash(double xCoord, double yCoord)
	{
		// TODO completar
		return null;		
	}*/
	
	/**
	  * Requerimiento 3A: Buscar las infracciones por rango de fechas
	  * @param  LocalDate fechaInicial: Fecha inicial del rango de bï¿½squeda
	  * 		LocalDate fechaFinal: Fecha final del rango de bï¿½squeda
	  * @return Cola con objetos InfraccionesFecha
	  */
	/*public IQueue<InfraccionesFecha> consultarInfraccionesPorRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
	{
		// TODO completar
		return null;		
	}*/
	
	
	
	/**
	 * MÃ©todo que retorna los tipos de infracciones en una fila, cuyo promedio de multas estÃ¡ en un rango dado. 
	 * @param fechaInicial: La fecha inicial del rango
	 * @param fechaFinal: La fecha final del rango 
	 * @return Una fila con todas las infracciones. 
	 */
	public MaxColaPrioridad<VOViolationCode> reqFuncional1B() {

		//Ordena el arreglo con respecto al cÃ³digo para que sea mÃ¡s fÃ¡cil
		arregloDinamico.quickSort(comparadorCodigo);
		
		//Crea la cola
		MaxColaPrioridad<VOViolationCode> cola = new MaxColaPrioridad<VOViolationCode>();
		
		String codigo = arregloDinamico.darElemento(0).getViolationCode();
		int cantidad = 0;
		
		//Recorre el arreglo de todas las infracciones ya ordenadas y para tipo de infracicÃ³n, encuentra la Ãºltima, suma el total de sus multas y lo divide por la cantidad que hubo.
		for(int i = 0; i < arregloDinamico.darTamano(); i++) {
			
			VOMovingViolations actual = arregloDinamico.darElemento(i);
			
			if(codigo.equals(actual.getViolationCode())) {
				
				cantidad ++;
			}
			
			//Apenas encuentra uno diferente al que venÃ­a sucediendo, obtiene el total de sus multas, saca un promedio y crea una instancia de la clase VOViolationCode
			//Esta instancia posteriormente agraga a la cola y se lee el siguiente elemento
			else {
				
				cola.agregar(new VOViolationCode(codigo, cantidad));
				System.out.println(codigo + " con: "  + cantidad + " infracciones" );
				cantidad = 0;
				
				if(i+1 < arregloDinamico.darTamano())
					codigo = arregloDinamico.darElemento(i+1).getViolationCode();
					
			}
		}
		
		if(cantidad != 0) {
			
			cola.agregar(new VOViolationCode(codigo, cantidad));
			System.out.println(codigo + " con: " + cantidad + " infracciones");
		}
		
		return cola; 
		
	}
	
	/**
	 * Método que se encarga de crear un árbol que tenga como llave las coordenadas y guarde elementos de tipo InfraccionesLocalizacion
	 * Por parámetro también le entran dos coordenadas específicas que se quieran buscar dentro del árbol ya creado. 
	 * @param pCoordenadaX - La coordenada x que se quiere utilizar como llave para la búsqueda.
	 * @param pCoordenadaY - La coordenada y que se quiere utilizar como llave para la búsqueda.  
	 * @return un objeto d etipo InfraccionesLocalizacion con la información de esa ubicación específica. 
	 */
	public InfraccionesLocalizacion reqFuncional2B(double pCoordenadaX, double pCoordenadaY) {
		 
		//Ordena el arreglo para que quede ordenado por localización geográfica. 
		arregloDinamico.quickSort(comparadorCoordenadas);
		
		//Recorre el arreglo ordenado que ya está agrupado por ubicación geográfica. 
		RedBlackBST<Coordenadas,InfraccionesLocalizacion> arbolLocalizacion = new RedBlackBST<Coordenadas,InfraccionesLocalizacion>();
		
		Coordenadas coords = new Coordenadas(arregloDinamico.darElemento(0).getXCoord(), arregloDinamico.darElemento(0).getYCoord());
		int cantidad = 0;
		Queue<VOMovingViolations> cola = new Queue<VOMovingViolations>();
		
		//Recorre el arreglo de todas las infracciones ya ordenadas y para tipo de infracicÃ³n, encuentra la Ãºltima, suma el total de sus multas y lo divide por la cantidad que hubo.
		for(int i = 0; i < arregloDinamico.darTamano(); i++) {
			
			VOMovingViolations actual = arregloDinamico.darElemento(i);
			
			
			if(actual.getXCoord() == coords.getX() && actual.getYCoord() == coords.getY()) {
				
				cantidad ++;
				cola.enqueue(actual);
			}
			
			//Apenas encuentra uno diferente al que venÃ­a sucediendo, obtiene el total de sus multas, saca un promedio y crea una instancia de la clase VOViolationCode
			//Esta instancia posteriormente agraga a la cola y se lee el siguiente elemento
			else {
				
				arbolLocalizacion.put(coords, new InfraccionesLocalizacion(coords.getX(), coords.getY(), actual.getLocation(), actual.getAddressId(), actual.getStreetSegId(), cola));
				System.out.println("(" + coords.getX() + ", " + coords.getY() + ")  con: "  + cantidad + " infracciones" );
				cantidad = 0;
				cola = new Queue<VOMovingViolations>();
				
				//Se cambian los valores de x y y del comparador para que lea la siguiente ubicación geográfica. 
				if(i+1 < arregloDinamico.darTamano()) {
					coords.cambiarX(arregloDinamico.darElemento(i+1).getXCoord());
					coords.cambiarY(arregloDinamico.darElemento(i+1).getYCoord());
				}
					
			}
		}
		
		if(cantidad != 0) {
			
			VOMovingViolations actual = arregloDinamico.darElemento(arregloDinamico.darTamano()-1);
			arbolLocalizacion.put(coords, new InfraccionesLocalizacion(coords.getX(), coords.getY(), actual.getLocation(), actual.getAddressId(), actual.getStreetSegId(), null));
			System.out.println("(" + coords.getX() + ", " + coords.getY() + ")  con: "  + cantidad + " infracciones" );
		}
		
		Coordenadas aBuscar = new Coordenadas(pCoordenadaX, pCoordenadaY);
		return arbolLocalizacion.get(aBuscar);
	}
	
	/**
	 * Método que se encarga de ordenar el arreglo de tal manera que pueda organizar la información por franjas y después meterlas a un árbol binario.
	 * Después retorna una cola ordenada (Priorityheap) con todas las franjas que contengan un valor entre el rango dado por parámetro. 
	 * @param pValorMinimo - valor inferior del rango de valores
	 * @param pValorMaximo -  valor superior del rango de valores
	 * @return Una cola de objetos tipo InfraccionesFechaHora que contiene la información de cada franja. 
	 */
	public MaxColaPrioridad<InfraccionesFechaHora> reqFuncional3B(int pValorMinimo, int pValorMaximo) {
		
		//Se crea la cola de prioridad donde se van a guardr las Infracciones Fecha-Hora que cumplen los criterios
		MaxColaPrioridad<InfraccionesFechaHora> cola = new MaxColaPrioridad<InfraccionesFechaHora>();
		RedBlackBST<FranjaFechaHora, InfraccionesFechaHora> arbolFranjas = new RedBlackBST<FranjaFechaHora,InfraccionesFechaHora>();
		
		//Ordena el arregloDinámico principal por hora.
		arregloDinamico.quickSort(comparadorFecha);
		
		//Escoje la primera infraccion del arreglo ya ordenado y saca la franja a la que pertenece. 
		
		int cantidad = 0;
		FranjaFechaHora franja = new FranjaFechaHora(arregloDinamico.darElemento(0).getTicketIssueDate().withMinute(0).withSecond(0));
		Queue<VOMovingViolations> fila = new Queue<VOMovingViolations>();
		
		//Recorre el arreglo de todas las infracciones ya ordenadas y para tipo de infracicÃ³n, encuentra la Ãºltima, suma el total de sus multas y lo divide por la cantidad que hubo.
		for(int i = 0; i < arregloDinamico.darTamano(); i++) {

			VOMovingViolations actual = arregloDinamico.darElemento(i);
			
			if(actual.getTicketIssueDate().getHour() == franja.hora.getHour() && actual.getTicketIssueDate().toLocalDate() == franja.hora.toLocalDate()) {

				cantidad ++;
				fila.enqueue(actual);
			}

			//Apenas encuentra uno diferente al que venÃ­a sucediendo, obtiene el total de sus multas, saca un promedio y crea una instancia de la clase VOViolationCode
			//Esta instancia posteriormente agraga a la cola y se lee el siguiente elemento
			else {

				InfraccionesFechaHora ifh = new InfraccionesFechaHora(franja.hora, fila);
				arbolFranjas.put(franja, ifh);
				cantidad = 0;
				fila = new Queue<VOMovingViolations>();

				//Se cambian los valores de x y y del comparador para que lea la siguiente ubicación geográfica. 
				if(i+1 < arregloDinamico.darTamano()) {
					franja = new FranjaFechaHora(actual.getTicketIssueDate().withMinute(0).withSecond(0));
				}
			}
		}
		return cola;
	}
	
	public void reqFuncional2C(LocalDateTime pFechaInicial, LocalDateTime pFechaFinal) {

		//Ordena el arregloDinámico principal por hora.
		//arregloDinamico.quickSort(comparadorFecha);

		//Escoje la primera infraccion del arreglo ya ordenado y saca la franja a la que pertenece. 
		Queue<VOMovingViolations> fila = new Queue<VOMovingViolations>();
		
		//Recorre el arreglo de todas las infracciones ya ordenadas y para tipo de infracicÃ³n, encuentra la Ãºltima, suma el total de sus multas y lo divide por la cantidad que hubo.
		for(int i = 0; i < arregloDinamico.darTamano(); i++) {

			VOMovingViolations actual = arregloDinamico.darElemento(i);

			if(actual.getTicketIssueDate().compareTo(pFechaInicial)<0 && actual.getTicketIssueDate().compareTo(pFechaFinal)>0 ) {
				fila.enqueue(actual);
			}
		}
		
		EstadisticaInfracciones ei = new EstadisticaInfracciones(fila);
		System.out.println(ei.toString());
	}
	
	
	
	/**
	 * Convertir fecha a un objeto LocalDate
	 * @param fecha fecha en formato dd/mm/aaaa con dd para dia, mm para mes y aaaa para agno
	 * @return objeto LD con fecha
	 */
	private static LocalDate convertirFecha(String fecha){
		
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	
	/**
	 * Convertir fecha y hora a un objeto LocalDateTime
	 * @param fecha fecha en formato dd/mm/aaaaTHH:mm:ss con dd para dia, mm para mes y aaaa para agno, HH para hora, mm para minutos y ss para segundos
	 * @return objeto LDT con fecha y hora integrados
	 */
	 private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora) {
		 
		 return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
     }
	 
	 /**
	  * Método que se encarga de convertir a entero las entradas que entran por CSV
	  * @param param - String leída del archivo que se quiere convertir
	  * @return El número ya de tipo entero que se puede ingresar a la clase. 
	  */
	 private static int convertirInt(String param)
	 {
		 int rta;
		 if (param.equals("")||param.equals("null")) {
			 rta = 0;
		 }
		 else{
			 double db = Double.parseDouble(param);
			 rta = (int) db;
		 }
		 
		 return rta;
	 }
	 
	 /**
	  * Método que se encarga de convertir a double las entradas que entran por CSV
	  * @param param - String leída del archivo que se quiere convertir
	  * @return El número ya de tipo double que se puede ingresar a la clase. 
	  */
	 private static double convertirDouble(String param)
	 {
		 double rta;
		 if (param.equals("")||param.equals("null")) {
			 rta = 0;
		 }
		 else 
		 {
			 rta = Double.parseDouble(param);
		 }
		 return rta;
	 }

}
