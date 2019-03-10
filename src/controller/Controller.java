package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Controller {
	
	//TODO Completar view
	
	private MovingViolationsManagerView view;
	
	/**
	 * Metodo encargado de ejecutar los  metodos segun la opcion indicada por el usuario
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
				controller.loadMovingViolations(semestre);
				
				//TODO Mostrar las infracciones cargadas por mes
				//TODO Mostrar total de infracciones cargadas
				//TODO Mostrar zona Minimax (Xmin, Ymin) y (Xmax, Ymax)
				break;

			case 1:
				view.printMessage("1A. Consultar las N franjas horarias con mas infracciones que desea ver. Ingresar valor de N: ");
				int numeroFranjas = sc.nextInt();
				
				//TODO Completar para la invocación del metodo 1A
				
				//TODO Mostrar resultado de tipo Cola con N InfraccionesFranjaHoraria
				
				break;

			case 2:
				view.printMessage("Ingrese la coordenada en X de la localizacion geografica: ");
				double xcoord = sc.nextDouble();
				view.printMessage("Ingrese la coordenada en Y de la localizacion geografica: ");
				double ycoord = sc.nextDouble();
				
				//TODO Completar para la invocación del metodo 2A
				
				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 
				
				break;

			case 3:
				view.printMessage("Ingrese la fecha inicial del rango. Formato año-mes-dia (ej. 2008-06-21)");
				String fechaInicialStr = sc.nextLine();
				LocalDate fechaInicial = convertirFecha_LD( fechaInicialStr );
				
				view.printMessage("Ingrese la fecha final del rango. Formato año-mes-dia (ej. 2008-06-30)");
				String fechaFinalStr = sc.nextLine();
				LocalDate fechaFinal = convertirFecha_LD( fechaFinalStr );
				
				//TODO Completar para la invocacion del metodo 3A
				
				//TODO Mostrar resultado de tipo Cola de InfraccionesFecha
				
				break;


			case 4:
				view.printMessage("1B. Consultar los N Tipos con mas infracciones. Ingrese el valor de N: ");
				int numeroTipos = sc.nextInt();
				
				//TODO Completar para la invocación del metodo 1B				
				
				//TODO Mostrar resultado de tipo Cola con N InfraccionesViolationCode

				break;

			case 5:						
				view.printMessage("Ingrese la coordenada en X de la localizacion geografica: ");
				xcoord = sc.nextDouble();
				view.printMessage("Ingrese la coordenada en Y de la localizacion geografica: ");
				ycoord = sc.nextDouble();
				
				//TODO Completar para la invocación del metodo 2B
				
				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 
				
				break;

			case 6:
				view.printMessage("Ingrese la cantidad minima de dinero que deben acumular las infracciones en sus rangos de fecha");
				double cantidadMinima = sc.nextDouble();
				
				view.printMessage("Ingrese la cantidad maxima de dinero que deben acumular las infracciones en sus rangos de fecha");
				double cantidadMaxima = sc.nextDouble();
				
				//TODO Completar para la invocación del metodo 3B
				
				//TODO Mostrar resultado de tipo Cola con InfraccionesFechaHora 
				
				break;

			case 7:
				view.printMessage("1C. Consultar las infracciones con Address_Id. Ingresar el valor de Address_Id: ");
				int addressID = sc.nextInt();

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocación del metodo 1C
				
				
				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMensage("Tiempo requerimiento 1C: " + duration + " milisegundos");

				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 	
				
				break;

			case 8:
				view.printMessage("Ingrese la hora inicial del rango. Formato HH:MM:SS (ej. 09:30:00)");
				String horaInicialStr = sc.nextLine();
				LocalTime horaInicial = convertirHora_LT(horaInicialStr);
				
				view.printMessage("Ingrese la hora final del rango. Formato HH:MM:SS (ej. 16:00:00)");
				String horaFinalStr = sc.nextLine();
				LocalTime horaFinal = convertirHora_LT(horaFinalStr);
				
				startTime = System.currentTimeMillis();
				//TODO Completar para la invocacion del metodo 2C
				
				
				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMensage("Tiempo requerimiento 2C: " + duration + " milisegundos");
				//TODO Mostrar resultado de tipo InfraccionesFranjaHorarioViolationCode
				
				break;

			case 9:
				view.printMessage("Consultar las N localizaciones geograficas con mas infracciones. Ingrese el valor de N: ");
				int numeroLocalizaciones = sc.nextInt();
				
				startTime = System.currentTimeMillis();
				//TODO Completar para la invocación del metodo 3C
				
				
				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMensage("Tiempo requerimiento 3C: " + duration + " milisegundos");
				//TODO Mostrar resultado de tipo Cola con InfraccionesLocalizacion
				
				break;

			case 10:
				
				System.out.println("Grafica ASCII con la informacion de las infracciones por ViolationCode");
				
				startTime = System.currentTimeMillis();
				//TODO Completar para la invocacion del metodo 4C
				
				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMensage("Tiempo requerimiento 3C: " + duration + " milisegundos");
				break;
			
			case 11:	
				fin = true;
				sc.close();
				break;
			}
		}
	}
	
	/**
	 * Convertir fecha - hora a un objeto LocalDate
	 * @param fechaHora fecha - hora en formato yyyy-MM-dd'T'HH:mm:ss'.000Z' con yyyy-MM-dd para la fecha y  HH:mm:ss para la hora
	 * @return objeto LDT con fecha - hora
	 */
    private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora)
    {
        return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
    }

	/**
	 * Convertir hora a un objeto LocalTime
	 * @param hora hora en formato HH:mm:ss con HH para hora, mm para minutos y ss para segundos
	 * @return objeto LD con fecha
	 */
    private static LocalTime convertirHora_LT(String hora)
    {
        return LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

	/**
	 * Convertir fecha a un objeto LocalDate
	 * @param fecha fecha en formato yyyy-MM-dd con yyyy para agno, MM para mes y dd para dia
	 * @return objeto LD con fecha
	 */
    private static LocalDate convertirFecha_LD(String fecha)
    {
        return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
	
	
}
