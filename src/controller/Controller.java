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
	
	private RedBlackBST<Integer, VOMovingViolations> arbolBalanceado;
	private ArregloDinamico<VOMovingViolations> arregloDinamico;

	/**
	 * Metodo constructor
	 */
	public Controller()
	{
		view = new MovingViolationsManagerView();
		model = new MovingViolationsManager();
		arbolBalanceado = new RedBlackBST<Integer, VOMovingViolations>();
		arregloDinamico = new ArregloDinamico<VOMovingViolations>(50);
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
				try{
				controller.loadPorSemestre(semestre);
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
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
			
			VOMovingViolations infraccion = new VOMovingViolations(id, arreglo[2], adressid, arreglo[4], xcoord, ycoord, 
					fineamt, pagado, penalty, arreglo[12], fechaHora, arreglo[14], arreglo[15]); 
			
			
			arbolBalanceado.put(id, infraccion);
			arregloDinamico.agregar(infraccion);
			
			System.out.println("El tamano actual del arbol es: " + arbolBalanceado.size());
			System.out.println("El tamano actual del arreglo es: " + arregloDinamico.darTamano());
			
			linea = br.readLine();
			
			
			contador ++;
			System.out.println(contador);
		}
		
		
		br.close();
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
