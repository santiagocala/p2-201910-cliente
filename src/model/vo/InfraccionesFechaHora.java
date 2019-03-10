package model.vo;

import java.time.LocalDateTime;

import model.data_structures.IQueue;

public class InfraccionesFechaHora extends EstadisticaInfracciones {

	private LocalDateTime fecha_hora_inicial;
	
	private LocalDateTime fecha_hora_final;
	
	public InfraccionesFechaHora(LocalDateTime pFechaHoraIni, LocalDateTime pFechaHoraFin, IQueue<VOMovingViolations> lista) {
		super(lista);
		// TODO Auto-generated constructor stub
		fecha_hora_inicial = pFechaHoraIni; 
		fecha_hora_final = pFechaHoraFin; 
	}

}
