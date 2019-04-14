package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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

	private ComparatorXViolationCode comparadorCodigo;

	private RedBlackBST<Integer, VOMovingViolations> arbolBalanceado;
	private ArregloDinamico<VOMovingViolations> arregloDinamico;
	
	public final static String mesEnero = "./data/Moving_Violations_Issued_in_January_2018.csv";

	public final static String mesFebrero = "./data/Moving_Violations_Issued_in_February_2018.csv";

	public final static String mesMarzo = "./data/Moving_Violations_Issued_in_March_2018.csv";

	public final static String mesAbril = "./data/Moving_Violations_Issued_in_April_2018.csv";

	public final static String mesMayo = "./data/Moving_Violations_Issued_in_May_2018.csv";

	public final static String mesJunio = "./data/Moving_Violations_Issued_in_June_2018.csv";

	public final static String mesJulio = "./data/Moving_Violations_Issued_in_July_2018.csv";

	public final static String mesAgosto = "./data/Moving_Violations_Issued_in_August_2018.csv";

	public final static String mesSeptiembre = "./data/Moving_Violations_Issued_in_September_2018.csv";

	public final static String mesOctubre = "./data/Moving_Violations_Issued_in_October_2018.csv";

	public final static String mesNomviembre = "./data/Moving_Violations_Issued_in_November_2018.csv";

	public final static String mesdiciembre= "./data/Moving_Violations_Issued_in_December_2018.csv";

	public static Double Xmin, Ymin, Xmax, Ymax;
	/**
	 * Metodo constructor
	 */
	public Controller()
	{
		view = new MovingViolationsManagerView();
		model = new MovingViolationsManager();
		arbolBalanceado = new RedBlackBST<Integer, VOMovingViolations>();
		arregloDinamico = new ArregloDinamico<VOMovingViolations>(50);
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
				view.printMessage("Ingrese semestre a cargar (1 o 2)");
				int semestre = sc.nextInt();
				int numCargados=controller.loadMovingViolations(semestre);
				/*
				try
				{
					controller.loadPorSemestre(semestre);
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}*/
				//EstadisticasCargaInfracciones resumenCarga = model.loadMovingViolations(semestre);

				//TODO Mostrar resultado de tipo EstadisticasCargaInfracciones con: 
				//     total de infracciones cargadas, numero de infracciones cargadas por mes y zona Minimax (Xmin, Ymin) y (Xmax, Ymax)
				//view.printResumenLoadMovingViolations( ... );
				break;

			case 1:
				view.printMessage("1A. Consultar las N franjas horarias con mas infracciones que desea ver. Ingresar valor de N: ");
				int numeroFranjas = sc.nextInt();

				//TODO Completar para la invocaci�n del metodo 1A
				//model.rankingNFranjas(int N)

				//TODO Mostrar resultado de tipo Cola con N InfraccionesFranjaHoraria
				//view.printReq1A( ...);
				break;

			case 2:
				view.printMessage("Ingrese la coordenada en X de la localizacion geografica (Ej. 1234,56): ");
				double xcoord = sc.nextDouble();
				view.printMessage("Ingrese la coordenada en Y de la localizacion geografica (Ej. 5678,23): ");
				double ycoord = sc.nextDouble();

				//TODO Completar para la invocaci�n del metodo 2A
				//model.consultarPorLocalizacionHash(double xCoord, double yCoord)

				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 
				//view.printReq2A( ... )
				break;

			case 3:
				view.printMessage("Ingrese la fecha inicial del rango. Formato a�o-mes-dia (ej. 2008-06-21)");
				String fechaInicialStr = sc.next();
				LocalDate fechaInicial = ManejoFechaHora.convertirFecha_LD( fechaInicialStr );

				view.printMessage("Ingrese la fecha final del rango. Formato a�o-mes-dia (ej. 2008-06-30)");
				String fechaFinalStr = sc.next();
				LocalDate fechaFinal = ManejoFechaHora.convertirFecha_LD( fechaFinalStr );

				//TODO Completar para la invocacion del metodo 3A
				//model.consultarInfraccionesPorRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)

				//TODO Mostrar resultado de tipo Cola de InfraccionesFecha
				//view.printReq3A( ... )
				break;


			case 4:
				view.printMessage("1B. Consultar los N Tipos con mas infracciones. Ingrese el valor de N: ");
				int numeroTipos = sc.nextInt();
				MaxColaPrioridad cola = reqFuncional1B();

				//TODO Completar para la invocaci�n del metodo 1B				
				//model.rankingNViolationCodes(int N)

				//TODO Mostrar resultado de tipo Cola con N InfraccionesViolationCode
				//view.printReq1B( ... )
				break;

			case 5:						
				view.printMessage("Ingrese la coordenada en X de la localizacion geografica (Ej. 1234,56): ");
				xcoord = sc.nextDouble();
				view.printMessage("Ingrese la coordenada en Y de la localizacion geografica (Ej. 5678,23): ");
				ycoord = sc.nextDouble();

				//TODO Completar para la invocaci�n del metodo 2B
				//model.consultarPorLocalizacionArbol(double xCoord, double yCoord)

				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 
				//view.printReq2B( ... )
				break;

			case 6:
				view.printMessage("Ingrese la cantidad minima de dinero que deben acumular las infracciones en sus rangos de fecha  (Ej. 1234,56)");
				double cantidadMinima = sc.nextDouble();

				view.printMessage("Ingrese la cantidad maxima de dinero que deben acumular las infracciones en sus rangos de fecha (Ej. 5678,23)");
				double cantidadMaxima = sc.nextDouble();

				//TODO Completar para la invocaci�n del metodo 3B
				//model.consultarFranjasAcumuladoEnRango(double valorInicial, double valorFinal)

				//TODO Mostrar resultado de tipo Cola con InfraccionesFechaHora 
				//view.printReq3B( ... )
				break;

			case 7:
				view.printMessage("1C. Consultar las infracciones con Address_Id. Ingresar el valor de Address_Id: ");
				int addressID = sc.nextInt();

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocaci�n del metodo 1C
				//model.consultarPorAddressId(int addressID)

				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 1C: " + duration + " milisegundos");

				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 	
				//view.printReq1C( ... )
				break;

			case 8:
				view.printMessage("Ingrese la hora inicial del rango. Formato HH:MM:SS (ej. 09:30:00)");
				String horaInicialStr = sc.next();
				LocalTime horaInicial = ManejoFechaHora.convertirHora_LT(horaInicialStr);

				view.printMessage("Ingrese la hora final del rango. Formato HH:MM:SS (ej. 16:00:00)");
				String horaFinalStr = sc.next();
				LocalTime horaFinal = ManejoFechaHora.convertirHora_LT(horaFinalStr);

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocacion del metodo 2C
				//model.consultarPorRangoHoras(LocalTime horaInicial, LocalTime horaFinal)

				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 2C: " + duration + " milisegundos");
				//TODO Mostrar resultado de tipo InfraccionesFranjaHorarioViolationCode
				//view.printReq2C( ... )
				break;

			case 9:
				view.printMessage("Consultar las N localizaciones geograficas con mas infracciones. Ingrese el valor de N: ");
				int numeroLocalizaciones = sc.nextInt();

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocaci�n del metodo 3C
				//model.rankingNLocalizaciones(int N)

				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 3C: " + duration + " milisegundos");
				//TODO Mostrar resultado de tipo Cola con InfraccionesLocalizacion
				//view.printReq3C( ... )
				break;

			case 10:

				System.out.println("Grafica ASCII con la informacion de las infracciones por ViolationCode");

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocacion del metodo 4C
				//model.ordenarCodigosPorNumeroInfracciones()

				//TODO Mostrar grafica a partir del resultado del metodo anterior
				//view.printReq4C( ... )
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
	public int loadMovingViolations(int numeroSemestre) 
	{
		int numCargados=0;
		if(numeroSemestre==1)
		{
			numCargados+= loadMovingViolationsXMes(mesEnero, false);
			numCargados+= loadMovingViolationsXMes(mesFebrero, false);
			numCargados+= loadMovingViolationsXMes(mesMarzo, false);
			numCargados+= loadMovingViolationsXMes(mesAbril, false);
			numCargados+= loadMovingViolationsXMes(mesMayo, false);
			numCargados+= loadMovingViolationsXMes(mesJunio, false);
		}

		else if(numeroSemestre==2)
		{
			numCargados+= loadMovingViolationsXMes(mesJulio, false);
			numCargados+= loadMovingViolationsXMes(mesAgosto, false);
			numCargados+= loadMovingViolationsXMes(mesSeptiembre, false);
			numCargados+= loadMovingViolationsXMes(mesOctubre, true);
			numCargados+= loadMovingViolationsXMes(mesNomviembre, true);
			numCargados+= loadMovingViolationsXMes(mesdiciembre, true);					

		}
		return numCargados;
	}
	public int loadMovingViolationsXMes(String movingViolationsFile, boolean otroAtributo) {
		System.out.println("");
		System.out.println("Se est� cargando: "+movingViolationsFile);
		int numCargados=0;
		int linea = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(movingViolationsFile));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);


			for (CSVRecord csvRecord : csvParser) {
				linea++;
				if (linea == 1)
					continue;

				{
					// Accessing Values by Column Index
					String OBJECTID =  csvRecord.get(0);
					if(OBJECTID.contains(","))
						continue;
					int pOBJECTID=OBJECTID.equals("")?0:Integer.parseInt(OBJECTID);
					//String ROW_ = csvRecord.get(1);
					String LOCATION = csvRecord.get(2);

					String STREETSEGID = csvRecord.get(4);  
					String ADDRESS_ID = csvRecord.get(3).equals("")?STREETSEGID:csvRecord.get(3); 

					int pADDRESS_ID =ADDRESS_ID.equals("")?0:Integer.parseInt(ADDRESS_ID);
					int pSTREETSEGID=STREETSEGID.equals("")?0:Integer.parseInt(STREETSEGID);

					String XCOORD = csvRecord.get(5);
					String YCOORD = csvRecord.get(6);
					Double x = XCOORD.equals("")?0.0:Double.parseDouble(XCOORD);
					Double y = YCOORD.equals("")?0.0:Double.parseDouble(YCOORD);
					/*int n=0;
					if(n<20)
					{
						System.out.println(x+","+y);
						n++;
					}

					int i=0;
					int j=0;
					int k=0;
					int l=0;*/
					
					//Rectangulo Min y Max
					if(x>Xmax){Xmax=x;/*if(i<20){System.out.println("xmax");i++;}*/}
					if(y>Ymax){Ymax=y;/*if(j<20){System.out.println("ymax");j++;}*/}
					if(x<Xmin){Xmin=x;/*if(k<20){System.out.println("xmin");k++;}*/}
					if(y<Ymin){Ymin=y;/*if(l<20){System.out.println("ymin");l++;}*/}
					//String TICKETTYPE = csvRecord.get(7);

					String FINEAMT = csvRecord.get(8);
					String TOTALPAID = csvRecord.get(9);

					Double pFINEAMT=FINEAMT.equals("")?0:Double.parseDouble(FINEAMT);
					Double pTOTALPAID=TOTALPAID.equals("")?0:Double.parseDouble(TOTALPAID);
					/*String PENALTY1 = csvRecord.get(10);
					String PENALTY2 = csvRecord.get(11);*/					
					String ACCIDENTINDICATOR = csvRecord.get(12);
					boolean pACCIDENTINDICATOR=ACCIDENTINDICATOR.equalsIgnoreCase("Yes")?true:false;
					String TICKETISSUEDATE =  !otroAtributo ? csvRecord.get(13).toString() : csvRecord.get(14);
					String VIOLATIONDESC = !otroAtributo ? csvRecord.get(15).toString() :  csvRecord.get(16);
					String VIOLATIONCODE = !otroAtributo ? csvRecord.get(14).toString() :  csvRecord.get(15);
					//System.out.println(OBJECTID + "," + LOCATION +  "," + ADDRESS_ID + "," + STREETSEGID);
					VOMovingViolations newMoving = new VOMovingViolations(pOBJECTID,LOCATION,pADDRESS_ID,pSTREETSEGID,pFINEAMT,pTOTALPAID,pACCIDENTINDICATOR,TICKETISSUEDATE,VIOLATIONCODE,VIOLATIONDESC, x, y);
					numCargados++;
					arbolBalanceado.put(pOBJECTID, newMoving);
					arregloDinamico.agregar(newMoving);
					//moving.add(listaa);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("AQUI - linea" + linea);
			System.out.println(e.getStackTrace().toString());
			System.out.println(e.getMessage());
		}
		System.out.println("El total de infracciones del mes fue: "+numCargados);
		return numCargados;
	}

	public void loadPorSemestre(int pSemestre) throws Exception {

		String meses[] = new String[4];
		String rutai = "src/data/Moving_Violations_Issued_in_";
		String rutaf = "_2018.csv";


		if(pSemestre == 1) {

			meses[0] = "January";
			meses[1] = "February";
			meses[2] = "March";
			meses[3] = "April";
			meses[0] = "May";
			meses[1] = "June";
		}
		else if(pSemestre == 2) {

			meses[2] = "July";
			meses[3] = "August";
			meses[0] = "September";
			meses[1] = "October";
			meses[2] = "November";
			meses[3] = "December";
		}

		else
			throw new Exception("Cuatrimestre inválido");

		for(int i = 0; i < 4; i++) {

			try {

				File f = new File(rutai + meses[i] + rutaf);
				System.out.println("Se cargo el mes: "+ i);
				loadMovingViolations(f);

			}
			catch(Exception e) {

				throw e;
			}
		}

	}

	/**
	 * Lee la información de un archivo que le llega por parámetro y se encarga de meterlo al arreglo 
	 * @param pArchivo
	 * @throws Exception
	 */
	public void loadMovingViolations(File pArchivo) throws Exception {

		FileReader lector = new FileReader(pArchivo);
		BufferedReader br = new BufferedReader(lector);
		br.readLine();
		String linea = br.readLine();

		System.out.println("Se va a cargar un mes");

		int contador = 0;

		while(linea != null) {

			String arreglo[] = linea.split(",");
			int id = Integer.parseInt(arreglo[0]);
			int adressid = Integer.parseInt(arreglo[3]);
			double xcoord = Double.parseDouble(arreglo[5]);
			double ycoord = Double.parseDouble(arreglo[6]);
			int fineamt = Integer.parseInt(arreglo[8]);
			int pagado = Integer.parseInt(arreglo[9]);
			int penalty = Integer.parseInt(arreglo[10]);

			LocalDateTime fechaHora = convertirFecha_Hora_LDT(arreglo[13]);

			/*VOMovingViolations infraccion = new VOMovingViolations(id, arreglo[2], adressid, arreglo[4], xcoord, ycoord, 
					fineamt, pagado, penalty, arreglo[12], fechaHora, arreglo[14], arreglo[15]); 


			arbolBalanceado.put(id, infraccion);
			arregloDinamico.agregar(infraccion);

			System.out.println("El tamano actual del arbol es: " + arbolBalanceado.size());
			System.out.println("El tamano actual del arreglo es: " + arregloDinamico.darTamano());
*/
			linea = br.readLine();


			contador ++;
			System.out.println(contador);
		}


		br.close();
	}

	/**
	 * Método que retorna los tipos de infracciones en una fila, cuyo promedio de multas está en un rango dado. 
	 * @param fechaInicial: La fecha inicial del rango
	 * @param fechaFinal: La fecha final del rango 
	 * @return Una fila con todas las infracciones. 
	 */
	public MaxColaPrioridad<VOViolationCode> reqFuncional1B() {

		//Ordena el arreglo con respecto al código para que sea más fácil
		arregloDinamico.ordenar(comparadorCodigo);

		//Crea la cola
		MaxColaPrioridad<VOViolationCode> cola = new MaxColaPrioridad<VOViolationCode>();

		String codigo = arregloDinamico.darElemento(0).getViolationCode();
		int cantidad = 0;

		//Recorre el arreglo de todas las infracciones ya ordenadas y para tipo de infracicón, encuentra la última, suma el total de sus multas y lo divide por la cantidad que hubo.
		for(int i = 0; i < arregloDinamico.darTamano(); i++) {

			VOMovingViolations actual = arregloDinamico.darElemento(i);

			if(codigo.equals(actual.getViolationCode())) {

				cantidad ++;
			}

			//Apenas encuentra uno diferente al que venía sucediendo, obtiene el total de sus multas, saca un promedio y crea una instancia de la clase VOViolationCode
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

}
