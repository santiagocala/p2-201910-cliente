package controller;

import java.util.Scanner;

public class Controller {
	
	//TODO Completar view
	
	private MovingViolationsManagerView view;
	
	/**
	 * Metodo encargado de ejecutar los  metodos segun la opcion indicada por el usuario
	 */
	
	public void run(){
		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		Controller controller = new Controller();
		int datoUsuario = -1;

		while(!fin){
			view.printMenu();

			int option = sc.nextInt();

			switch(option){
			
			case 0:
				view.printMessage("Ingrese semestre a cargar (1, o 2)");
				datoUsuario = sc.nextInt();
				controller.loadMovingViolations(datoUsuario);
				break;

			case 1:
				view.printMessage("Ingrese el valor de las N franjas horarias con mas infracciones que desea ver");
				datoUsuario = sc.nextInt();
				
				//TODO Completar para la invocación del metodo 1A
				view.printMovingViolationsReq1(isUnique);
				break;

			case 2:
				double xcoord;
				double ycoord;				
				view.printMessage("Ingrese la coordenada en X de la localizacion geografica");
				xcoord = sc.nextDouble();
				view.printMessage("Ingrese la coordenada en Y de la localizacion geografica");
				ycoord = sc.nextDouble();
				
				//TODO Completar para la invocación del metodo 2A				
				break;

			case 3:
				String fechaInicial, fechaFinal;
				view.printMessage("Ingrese la fecha inicial del rango. Formato año/mes/dia");
				fechaInicial = sc.nextLine();
				
				view.printMessage("Ingrese la fecha final del rango. Formato año/mes/dia");
				fechaFinal = sc.nextLine();
				
				//TODO Completar para la invocacion del metodo 3A
				
				break;


			case 4:
				view.printMessage("Ingrese el valor de los N violation codes con mas infracciones que desea ver");
				datoUsuario = sc.nextInt();
				
				//TODO Completar para la invocación del metodo 1B				
				
				break;

			case 5:						
				view.printMessage("Ingrese la coordenada en X de la localizacion geografica");
				xcoord = sc.nextDouble();
				view.printMessage("Ingrese la coordenada en Y de la localizacion geografica");
				ycoord = sc.nextDouble();
				
				//TODO Completar para la invocación del metodo 2B				
				break;

			case 6:
				double cantidadMinima;
				double cantidadMaxima;
				
				view.printMessage("Ingrese la cantidad minima de dinero que deben acumular las infracciones en sus rangos de fecha");
				cantidadMinima = sc.nextDouble();
				
				view.printMessage("Ingrese la cantidad maxima de dinero que deben acumular las infracciones en sus rangos de fecha");
				cantidadMaxima = sc.nextDouble();
				
				//TODO Completar para la invocación del metodo 3B				
				break;

			case 7:
				view.printMessage("Ingrese el ADDRESS_ID sobre el que desea consultar todas las infracciones");
				String addressID = sc.nextLine();

				//TODO Completar para la invocación del metodo 1C
				
				break;

			case 8:
				String horaInicial, horaFinal;
				view.printMessage("Ingrese la hora inicial del rango. Formato HH:MM:SS");
				horaInicial = sc.nextLine();
				
				view.printMessage("Ingrese la hora final del rango. Formato HH:MM:SS");
				horaFinal = sc.nextLine();
				
				//TODO Completar para la invocacion del metodo 2C
				break;

			case 9:
				view.printMessage("Ingrese el valor de las N localizaciones geograficas con mas infracciones que desea ver");
				datoUsuario = sc.nextInt();
				
				//TODO Completar para la invocación del metodo 3C	

			case 10:
				
				System.out.println("Grafica ASCII con la informacion de las infracciones por ViolationCode");
				
				//TODO Completar para la invocacion del metodo 4C
				
				break;
			
			case 11:	
				fin = true;
				sc.close();
				break;
			}
		}
	}
	
	
}
