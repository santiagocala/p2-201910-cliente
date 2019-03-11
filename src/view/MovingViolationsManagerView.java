package view;

import model.data_structures.IQueue;
import model.vo.*;

public class MovingViolationsManagerView {

	/**
	 * Constante con el numero maximo de datos maximo que se deben imprimir en consola
	 */
	public static final int N = 20;
	
	public void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Proyecto 2----------------------");
		System.out.println("0. Cargar datos del semestre");
		System.out.println("1. Obtener el ranking de las N franjas horarias que tengan más infracciones. (REQ 1A)");
		System.out.println("2. Realizar  el  ordenamiento  de  las  infracciones  por  Localización  Geográfica. (REQ 2A)");
		System.out.println("3. Buscar las infracciones por rango de fechas (REQ 3A)");
		
		System.out.println("4. Obtener  el  ranking  de  las  N  tipos  de  infracción  (ViolationCode)  que  tengan  más infracciones. (REQ 1B)");		
		System.out.println("5. Realizar  el  ordenamiento  de  las  infracciones  por  Localización  Geográfica. (REQ 2B)");
		System.out.println("6. Buscar las franjas de fecha-hora donde se tiene un valor acumulado de infracciones en un rango dado. (REQ 3B)");
		
		System.out.println("7. Obtener  la información de  una  localización dada. (REQ 1C)");
		System.out.println("8. Obtener  las infracciones  en  un  rango de  horas. (REQ 2C)");
		System.out.println("9. Obtener  el  ranking  de  las  N localizacionesgeográficas con la mayor cantidad de infracciones. (REQ 3C)");
		System.out.println("10. Mostrar  una  gráfica ASCII con la  información  de  las infracciones  por  código (ViolationCode). (REQ 4C)");
		
		System.out.println("11. Salir");
		System.out.println("Digite el nï¿½mero de opciï¿½n para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
	
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}
	
	public void printReq1A(IQueue<InfraccionesFranjaHoraria> resultados) {
		for(InfraccionesFranjaHoraria vinfraFranjas: resultados) {
			System.out.println(vinfraFranjas.toString());
			for(VOMovingViolations vo: vinfraFranjas.getListaInfracciones()) {
				System.out.println(vo.toString());
			}
		}
	}
	
	public void printReq2A(InfraccionesLocalizacion resultado) {
		System.out.println(resultado.toString());
		for(VOMovingViolations v: resultado.getListaInfracciones()) {
			System.out.println(v.toString());
		}
	}
	
	public void printReq3A(IQueue<InfraccionesFecha> resultados) {
		for(InfraccionesFecha infraFechas: resultados) {
			System.out.println(infraFechas.toString());
			for(VOMovingViolations vo: infraFechas.getListaInfracciones()) {
				System.out.println(vo.toString());
			}
		}
	}
	
	public void printReq1B(IQueue<InfraccionesViolationCode> resultados) {
		for(InfraccionesViolationCode infraVioCode: resultados) {
			System.out.println(infraVioCode.toString());
			for(VOMovingViolations vo: infraVioCode.getListaInfracciones()) {
				System.out.println(vo.toString());
			}
		}
	}
	
	public void printReq2B(InfraccionesLocalizacion resultado) {
		System.out.println(resultado.toString());
		for(VOMovingViolations v: resultado.getListaInfracciones()) {
			System.out.println(v.toString());
		}
	}
	
	
	public void printReq3B(IQueue<InfraccionesFechaHora> resultados) {
		for(InfraccionesFechaHora infraFechas: resultados) {
			System.out.println(infraFechas.toString());
			for(VOMovingViolations vo: infraFechas.getListaInfracciones()) {
				System.out.println(vo.toString());
			}
		}
	}
	
	public void printReq1C(InfraccionesLocalizacion resultado) {
		System.out.println(resultado.toString());
		for(VOMovingViolations v: resultado.getListaInfracciones()) {
			System.out.println(v.toString());
		}
	}
	
	public void printReq2C(InfraccionesFranjaHorariaViolationCode resultado) {
		System.out.println(resultado.toString());
		for(VOMovingViolations v: resultado.getListaInfracciones()) {
			System.out.println(v.toString());
		}
	}
	
	
	public void printReq3C(IQueue<InfraccionesLocalizacion> resultados) {
		for(InfraccionesLocalizacion infraLoc: resultados) {
			System.out.println(infraLoc.toString());
			for(VOMovingViolations vo: infraLoc.getListaInfracciones()) {
				System.out.println(vo.toString());
			}
		}
	}
	
	
	
	
	
	
	
	
}
